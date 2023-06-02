package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.*;
import fr.eni.projetencheres.dal.dao.ArticleVenduDAO;
import fr.eni.projetencheres.dal.dao.UtilisateurDAO;

import java.util.List;

public class UtilisateurManager {

    private static UtilisateurDAO utilisateurDAO;

   static {
       utilisateurDAO = DAOFactory.getUtilisateurDAO();
   }

    // methode d'authentification par identifiant et mot de passe
    public Utilisateur authentification(String identifiant, String motDePasse) throws Exception {
        return utilisateurDAO.selectConnexion(identifiant, motDePasse);
    }

    // methode pour creer un nouvel utilisateur
    public void nouvelUtilisateur(Utilisateur utilisateur)
            throws DALException {
        System.out.println("on est dans le manager");

        utilisateurDAO.newUtilisateur(utilisateur);
    }

    // methode pour chercher un utilisateur
    public Utilisateur chercherUtilisateur(int idUtilisateur) throws DALException {
        return utilisateurDAO.selectById(idUtilisateur);
    }

    // methode pour modifier un utilisateur
    public Utilisateur modifierUtilisateur(Utilisateur utilisateur)
            throws DALException {
        utilisateurDAO.updateUtilisateur(utilisateur);
        return utilisateur;
    }

    // methode pour verifier que le pseudo est unique
    public boolean verifUniquePseudo(String pseudo) throws DALException {
        return utilisateurDAO.selectUniquePseudo(pseudo);

    }

    // methode pour verifier que le mail est unique
    public boolean verifUniqueMail(String mail) throws DALException {
       return utilisateurDAO.selectUniqueMail(mail);
    }

    public void supressionUtilisateur(int idUtilisateur) throws DALException, BLLException {
        utilisateurDAO.deleteUtilisateur(idUtilisateur);

    }


}