package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.UtilisateurManager;
import fr.eni.projetencheres.bo.Password;
import fr.eni.projetencheres.bo.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.coyote.Request;

import java.io.IOException;

@WebServlet("/Modifier")
public class ServletModifierProfil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentMdp = request.getParameter("currentMdp");
        String identifiant = (String) session.getAttribute("pseudo");
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        try {
            // Tentative de modification du mot de passe
            if (!request.getParameter("motDePasse").isEmpty()) {

                // On vérifie que le mot de passe actuel et la confirmation du mot de passe ne soient pas vides
                if (!request.getParameter("currentMdp").isEmpty() && !request.getParameter("confirmationMotDePasse").isEmpty()) {
                    System.out.println("Tous les mots de passe sont entrés");
                    String motDePasse = request.getParameter("motDePasse");
                    String confirmMotDePasse = request.getParameter("confirmationMotDePasse");
                    // On vérifie que le mdp et sa confirmation sont identiques
                    if (!motDePasse.equals(confirmMotDePasse)){
                        System.out.println("si les deux mots de passe ne correspondent pas");
                        request.setAttribute("errorMessage", "Les mots de passe ne correspondent pas.");
                        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/CreationCompte.jsp");
                        rd.forward(request, response);
                        return;
                    }else {
                        // On vérifie le mot de passe actuel
                        Utilisateur user = utilisateurManager.authentification(identifiant, currentMdp);
                        System.out.println("on vient de vérifier l'authentification");
                        System.out.println(user);
                        if(user != null) {
                            // Hashage du nouveau mot de passe
                            String motDePasseHash = null;
                            System.out.println(request.getParameter("motDePasse"));
                            motDePasseHash = Password.getSaltedHash(request.getParameter("motDePasse"));
                            System.out.println(motDePasseHash);
                            Utilisateur userUpdated = new Utilisateur(
                                                    user.getNoUtilisateur(),
                                                    request.getParameter("pseudo"),
                                                    request.getParameter("nom"),
                                                    request.getParameter("prenom"),
                                                    request.getParameter("email"),
                                                    request.getParameter("telephone"),
                                                    request.getParameter("rue"),
                                                    request.getParameter("codePostal"),
                                                    request.getParameter("ville"),
                                                    motDePasseHash
                            );
                            // Mis à jour du profil avec le nouveau mot de passe et éventuellement les nouvelles infos
                            utilisateurManager.modifierUtilisateur(userUpdated);
                            // On enregistre dans la session les nouvelles infos de l'utilisateur en cours
                            session.setAttribute("userInfos", userUpdated);
                            // Message à afficher pour signifier la modification sur profil.jsp
                            session.setAttribute("message", "Profil Mis à jour - Mot de passe modifié");
                            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/profil.jsp");
                            rd.forward(request, response);
                        }else {
                            // Cas ou le mot de passe actuel est incorrect
                            request.setAttribute("errorMessage", "Votre mot de passe actuel est incorrect");
                            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/CreationCompte.jsp");
                            rd.forward(request, response);
                        }
                    }
                }else {
                    // Cas ou un des champs (mot de passe actuel ou confirmation est vide)
                    request.setAttribute("erreur", "Veuillez rentrer votre mot de passe actuel et confirmer le nouveau mot de passe");
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/CreationCompte.jsp");
                    rd.forward(request, response);
                }
            } else {
                System.out.println("le champ de mot de passe est vide");
                // Si l'utilisateur n'a rien rentré dans aucun des trois champs mot de passe
                // On met à jour uniquement les autres infos
                Utilisateur user = utilisateurManager.chercherPseudo((String) session.getAttribute("pseudo"));
                Utilisateur userUpdated = new Utilisateur(
                        user.getNoUtilisateur(),
                        request.getParameter("pseudo"),
                        request.getParameter("nom"),
                        request.getParameter("prenom"),
                        request.getParameter("email"),
                        request.getParameter("telephone"),
                        request.getParameter("rue"),
                        request.getParameter("codePostal"),
                        request.getParameter("ville")
                );
                utilisateurManager.modifierUtilisateur(userUpdated);
                session.setAttribute("userInfos", userUpdated);
                session.setAttribute("message", "Profil Mis à jour");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/profil.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}