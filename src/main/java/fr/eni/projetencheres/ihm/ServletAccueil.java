package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.ArticleVenduManager;
import fr.eni.projetencheres.bll.CategorieManager;
import fr.eni.projetencheres.bll.EncheresManager;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.dal.DALException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

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
            context.setAttribute("encheresActuelles", articleVenduManager.selectAll());
            context.setAttribute("categories", categorieManager.getAllCategories());
            context.setAttribute("usersPseudos", utilisateurManager.selectUtilisateursWithCurrentAuction());
        } catch (Exception e){
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}