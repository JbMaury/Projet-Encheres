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

        Utilisateur user = null;
        UtilisateurDAO uDao = null;
        uDao = DAOFactory.getUtilisateurDAO();
        user = uDao.selectConnexion(identifiant, motDePasse);

        return user;
    }

    // methode pour creer un nouvel utilisateur
    public void nouvelUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
                                  String codePostal, String ville, String motDePasse, int credit, boolean administrateur)
            throws DALException {

        Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse,
                credit, administrateur);
        UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
        userDAO.newUtilisateur(user);
    }

    // methode pour chercher un utilisateur
    public Utilisateur chercherUtilisateur(int id) throws DALException {
        Utilisateur user;
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        user = udao.selectById(id);
        return user;
    }

    // methode pour modifier un utilisateur
    public Utilisateur modifierUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
                                           String rue, String codePostal, String ville, String motDePasse, Utilisateur utilisateur)
            throws DALException {
        String oldPseudo = "";
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();

        // veriffier si les donnees correspondent
        utilisateur.setPseudo(pseudo);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setNumTel(telephone);
        utilisateur.setRue(rue);
        utilisateur.setCodePostal(codePostal);
        utilisateur.setVille(ville);

        udao.updateUtilisateur(utilisateur);
        return utilisateur;
    }

    // methode pour verifier les caracteres alphanumerique du pseudo nom et prenom
    public boolean verifPseudo(String str) {

        boolean ok = true;
        char[] c = str.toCharArray();

        for (int i=0; i<c.length; i++) {
            ok = Character.isLetterOrDigit(c[i]);
            if (!ok) {
                return ok;
            }
        }
        return ok;

    }

    public boolean verifNomPrenom(String str) {
        boolean ok = true;
        char[] c = str.toCharArray();

        for (int i=0; i<c.length; i++) {
            ok = Character.isLetter(c[i]);
            if (!ok) {
                return ok;
            }
        }
        return ok;
    }

    // methode pour verifier que le numero de telephone ne contient pas de lettre
    public boolean verifTel(String str) {

        boolean ok = true;
        char[] c = str.toCharArray();

        for (int i=0; i<c.length; i++) {
            ok = Character.isDigit(c[i]);
            if (!ok) {
                return ok;
            }
        }
        return ok;
    }

    // methode pour verifier que le code postal ne contient que des chiffres
    public boolean verifCP(String str) {

        boolean ok = true;
        char[] c = str.toCharArray();

        for (int i=0; i<c.length; i++) {
            ok = Character.isDigit(c[i]);
            System.out.println(i + " " + ok);
            if (!ok) {
                return ok;
            }
        }
        return ok;
    }

    // methode pour verifier que le pseudo est unique
    public boolean verifUniquePseudo(String pseudo) throws DALException {

        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        boolean ok;

        ok = udao.selectUniquePseudo(pseudo);

        return ok;
    }

    // methode pour verifier que le mail est unique
    public boolean verifUniqueMail(String mail) throws DALException {

        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        boolean ok;

        ok = udao.selectUniqueMail(mail);

        return ok;
    }

    public void supressionUtilisateur(Utilisateur utilisateur) throws DALException {
        List<ArticleVendu> articles;
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        udao.deleteUtilisateur(utilisateur);

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        articles =adao.selectByNoUtilisateur(utilisateur);
        for (ArticleVendu article : articles) {

            adao.deleteArticle(article);
        }
    }


}