package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Password;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DALException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.catalina.Session;

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

        // Encodage de la request/response
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Encodage du mot de passe
        String motDePasse = request.getParameter("motDePasse");
        String motDePasseHash = null;
        try {
            motDePasseHash = Password.getSaltedHash(motDePasse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Construction nouvel utilisateur
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        Utilisateur user = new Utilisateur(
                request.getParameter("pseudo"),
                request.getParameter("nom"),
                request.getParameter("prenom"),
                request.getParameter("email"),
                request.getParameter("telephone"),
                request.getParameter("rue"),
                request.getParameter("codePostal"),
                request.getParameter("ville"),
                motDePasseHash,
                0,
                false
        );
        try {
            System.out.println("Nous avons bien construit un objet utilisateur");
            System.out.println(user);
            utilisateurManager.nouvelUtilisateur(user);
        } catch (DALException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/index.jsp");
        HttpSession session = request.getSession();
        session.setAttribute("isConnected", true);
        session.setAttribute("pseudo", request.getParameter("pseudo"));
        rd.forward(request, response);

    }
}