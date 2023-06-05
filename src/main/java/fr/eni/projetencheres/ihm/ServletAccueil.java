package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.CategorieManager;
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
        ServletContext context = getServletContext();
        try {
            context.setAttribute("categories", categorieManager.getAllCategories());
        } catch (DALException e){
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}