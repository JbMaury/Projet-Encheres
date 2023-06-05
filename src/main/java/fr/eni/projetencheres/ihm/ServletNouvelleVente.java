package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.ArticleVenduManager;
import fr.eni.projetencheres.bll.CategorieManager;
import fr.eni.projetencheres.bll.RetraitManager;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Retrait;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DALException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategorieManager categorieManager = new CategorieManager();
        try {
            request.setAttribute("categories", categorieManager.getAllCategories());
        } catch (DALException e){
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Nouvellevente.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("userInfos");
        ArticleVendu article;
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        RetraitManager retraitManager = new RetraitManager();

        //Récupération des paramètres et construction Article à vendre
        article = new ArticleVendu(
                request.getParameter("article"),
                request.getParameter("description"),
                LocalDate.parse(request.getParameter("dateD")),
                LocalDate.parse(request.getParameter("dateF")),
                Integer.parseInt(request.getParameter("miseAPrix")),
                user.getNoUtilisateur(),
                Integer.parseInt(request.getParameter("categorie"))
        );

        // Vérification des dates d'enchères
        LocalDate dateJour = LocalDate.now();
        LocalDate debutEncheres = LocalDate.parse(request.getParameter("dateD"));
        LocalDate finEncheres = LocalDate.parse(request.getParameter("dateF"));
        if(debutEncheres.isEqual(dateJour) || debutEncheres.isAfter(dateJour)){
            if(finEncheres.isAfter(debutEncheres)) {
                try {

                    // Insertion de l'article dans la Db
                    articleVenduManager.newArticle(article);

                    // Récupération des paramètres et construction Point de Retrait
                    Retrait ret = new Retrait(
                            article.getNoArticle(),
                            request.getParameter("rue"),
                            request.getParameter("codePostal"),
                            request.getParameter("ville")
                    );
                    retraitManager.newRetrait(ret);
                    request.setAttribute("message", "Article ajouté avec succès");
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/index.jsp");
                    rd.forward(request, response);

                }catch(Exception e) {
                    e.printStackTrace();
                }
            } else {
                // On créé un attribut de requête dans le cas d'erreurs
                request.setAttribute("errorArticle", article);
                request.setAttribute("errorMessage", "Veuillez entrer une date de fin ultérieure à la date de début d'enchère");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/Nouvellevente.jsp");
                rd.forward(request, response);

            }
        }else {
            // On créé un attribut de requête dans le cas d'erreurs
            request.setAttribute("errorArticle", article);
            request.setAttribute("errorMessage", "La date de début d'enchères ne peut être antérieure à la date du jour");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/Nouvellevente.jsp");
            rd.forward(request, response);
        }


    }
}