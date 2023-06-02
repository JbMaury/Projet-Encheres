package fr.eni.projetencheres.ihm;

import jakarta.servlet.;
import jakarta.servlet.http.;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletAcceuil", value = "/ServletAcceuil")
public class ServletAcceuil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(/"WEB-INF/pages/index.html");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}