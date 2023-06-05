package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DALException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Profil", value = "/Profil")
public class Profil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/profil.jsp");
        String pseudo = (String) session.getAttribute("pseudo");
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        try {
            Utilisateur user = utilisateurManager.chercherPseudo(pseudo);
            session.setAttribute("userInfos", user);
        } catch (DALException e) {
            e.printStackTrace();
        }
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/CreationCompte.jsp");
        rd.forward(request, response);
    }
}