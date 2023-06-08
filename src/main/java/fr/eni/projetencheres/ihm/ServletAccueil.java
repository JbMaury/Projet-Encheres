package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.ArticleVenduManager;
import fr.eni.projetencheres.bll.CategorieManager;
import fr.eni.projetencheres.bll.EncheresManager;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.dal.DALException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class ServletAccueil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategorieManager categorieManager = new CategorieManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        ServletContext context = getServletContext();
        try {
            if(request.getParameter("message") != null){
                request.setAttribute("message", "Article ajouté avec succès");
            }
            if(request.getAttribute("articlesFiltre") != null) {
                request.removeAttribute("encheresActuelles");

            }else {
                request.setAttribute("encheresActuelles", articleVenduManager.selectAll());
            }
            context.setAttribute("categories", categorieManager.getAllCategories());
            context.setAttribute("usersPseudos", utilisateurManager.selectUtilisateursWithCurrentAuction());
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("on est dans le get on renvoie vers la page");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    CategorieManager categorieManager = new CategorieManager();
    List<ArticleVendu> listeArticlesFiltre = new ArrayList<>();

        try {
            if(request.getParameter("categorie").equals("all")) {
                if(request.getParameter("rechercheArticle") != null){
                    listeArticlesFiltre = articleVenduManager.selectByKeywords(request.getParameter("rechercheArticle"));
                    if(listeArticlesFiltre.isEmpty()) {
                        System.out.println("dans l'erreur");
                        request.setAttribute("messageError", "Aucun article trouvé");
                    }
                }else {
                    listeArticlesFiltre = articleVenduManager.selectAll();
                }

            }else {
                int noCategorieFiltre = Integer.parseInt(request.getParameter("categorie"));
                Categorie categorieFiltre = categorieManager.getCategorieById(noCategorieFiltre);
                if(request.getParameter("rechercheArticle") != null){
                    List<ArticleVendu> listeArticlesMotsCles = articleVenduManager.selectByKeywords(request.getParameter("rechercheArticle"));
                    if(listeArticlesMotsCles.size() == 0) {
                        System.out.println("dans l'erreur");
                        request.setAttribute("messageError", "Aucun article trouvé");
                    }else {
                        for(ArticleVendu article :listeArticlesMotsCles) {
                            if(article.getNoCategorie() == noCategorieFiltre){
                                listeArticlesFiltre.add(article);
                            }
                        }
                    }
                }else {
                    listeArticlesFiltre = articleVenduManager.selectByCategorie(noCategorieFiltre);
                }
                request.setAttribute("categorieFiltre", categorieFiltre);
            }
            request.setAttribute("articlesFiltre", listeArticlesFiltre);
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}