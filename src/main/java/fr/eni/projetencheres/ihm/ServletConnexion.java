package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("doGet Connexion");
    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/PageConnexion.jsp");
    rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateurManager utilisateurManager = new UtilisateurManager();

        String identifiant = request.getParameter("identifiant");
        String mdp = request.getParameter("password");

        try {
            Utilisateur user = utilisateurManager.authentification(identifiant, mdp);
            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfos", user);
                session.setAttribute("isConnected", true);
                session.setAttribute("pseudo", user.getPseudo());
                response.sendRedirect(request.getContextPath() + "/");
            }else {
                request.setAttribute("erreur", "identifiant ou Mot de Passe incorrect");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/PageConnexion.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}