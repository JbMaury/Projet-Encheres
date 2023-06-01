package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Utilisateur;

import java.util.List;

public class DAOFactory {

    // methode de création d'un utilisateur
    public static UtilisateurDAO getUtilisateurDAO() {

        UtilisateurDAO utilisateurDAo = new UtilisateurDaoImpl();
        return utilisateurDAo;
    }

    // méthode de création d'un catégorie DAO
    public static CategorieDAO getCategorieDAO() {

        CategorieDAO categorieDAO = new CategorieDaoImpl();
        return categorieDAO;
    }

    // méthode de création d'un enchereDAO
    public static EncheresDAO getEnchereDAO() {

        EncheresDAO enchereDAO = new EncheresDaoImpl();
        return enchereDAO;
    }

    // méthode de création d'un articleDAO
    public static ArticleVenduDAO getArticleDAO() {

        ArticleVenduDAO art = new ArticleVenduDAOImpl();
        return art;
    }

    public static RetraitDAO getRetraitDAO() {
        RetraitDAO rdao = new RetraitDaoImpl();
        return rdao;
    }

}