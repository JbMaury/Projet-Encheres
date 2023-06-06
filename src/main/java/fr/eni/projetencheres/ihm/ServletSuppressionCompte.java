package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.BLLException;
import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DALException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/Supprimer")
public class ServletSuppressionCompte extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origine = request.getParameter("origine");

        if(origine != null && origine.equals("sup")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/ConfirmationSuppression.jsp");
            rd.forward(request, response);
        }else if(origine != null && origine.equals("confirm")) {

            UtilisateurManager utilisateurManager = new UtilisateurManager();
            HttpSession session = request.getSession();
            Utilisateur user = (Utilisateur) session.getAttribute("userInfos");
            try {
                utilisateurManager.supressionUtilisateur(user.getNoUtilisateur());
                // Si ça marche
                session.invalidate();
                request.setAttribute("message", "Votre compte a bien été supprimé");
                response.sendRedirect(request.getContextPath() + "/");
            } catch (DALException e) {
                e.printStackTrace();
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}