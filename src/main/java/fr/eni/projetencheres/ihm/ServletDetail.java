package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.*;
import fr.eni.projetencheres.bo.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/Detail")
public class ServletDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        RetraitManager retraitManager = new RetraitManager();
        CategorieManager categorieManager = new CategorieManager();
        EncheresManager encheresManager = new EncheresManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        int idArticle = Integer.parseInt(request.getParameter("id"));

        try {
            Retrait retraitArticle = retraitManager.selectByIdArticle(idArticle);
            ArticleVendu currentArticle = articleVenduManager.selectById(idArticle);
            Categorie currentCategorie = categorieManager.getCategorieById(currentArticle.getNoCategorie());
            List<Enchere> encheres = encheresManager.selectByNoArticle(currentArticle.getNoArticle());
            Enchere bestEnchere = new Enchere();
            if(!encheres.isEmpty()) {
                bestEnchere = encheres.get(0);
                String bestEncherisseur = utilisateurManager.chercherId(bestEnchere.getNoUtilisateur()).getPseudo();
                request.setAttribute("bestEncherisseurName", bestEncherisseur);
            }


            request.setAttribute("currentEncheres", encheres);
            request.setAttribute("currentArticle", currentArticle);
            request.setAttribute("currentRetrait", retraitArticle);
            request.setAttribute("currentCategorie", currentCategorie);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/Detailvente.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}