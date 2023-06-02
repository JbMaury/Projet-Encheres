package fr.eni.projetencheres.ihm;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/CreationCompte.jsp");
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("nous sommes dans la servlet Inscription : doPost");
    }
}