package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.ArticleVenduManager;
import fr.eni.projetencheres.bll.CategorieManager;
import fr.eni.projetencheres.bll.RetraitManager;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Retrait;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/Detail")
public class ServletDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        RetraitManager retraitManager = new RetraitManager();
        CategorieManager categorieManager = new CategorieManager();
        int idArticle = Integer.parseInt(request.getParameter("id"));
        System.out.println("test");

        try {
            Retrait retraitArticle = retraitManager.selectByIdArticle(idArticle);
            System.out.println("après le retrait");
            ArticleVendu currentArticle = articleVenduManager.selectById(idArticle);
            System.out.println("après avoir selectionné l'article");
            System.out.println("Article recherché :" + currentArticle);
            System.out.println("Num cat de l'article recherché :" +currentArticle.getNoCategorie());


            request.setAttribute("currentArticle", currentArticle);
            request.setAttribute("currentRetrait", retraitArticle);
//            request.setAttribute("currentCategorie", currentCategorie);
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