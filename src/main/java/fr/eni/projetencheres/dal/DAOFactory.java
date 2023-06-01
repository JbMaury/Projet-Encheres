package fr.eni.projetencheres.dal;


import fr.eni.projetencheres.dal.dao.*;
import fr.eni.projetencheres.dal.jdbc.*;

public class DAOFactory {

    // methode de création d'un utilisateur
    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAOJdbcImpl();
    }
    // méthode de création d'un catégorie DAO
    public static CategorieDAO getCategorieDAO() {
        return new CategorieDAOJdbcImpl();
    }
    // méthode de création d'un enchereDAO
    public static EnchereDAO getEnchereDAO() {
        return new EncheresDAOJdbcImpl();
    }
    // méthode de création d'un articleDAO
    public static ArticleVenduDAO getArticleDAO() {
        return new ArticleVenduDAOJdbcImpl();
    }
    public static RetraitDAO getRetraitDAO() {
        return new RetraitDAOJdbcImpl();
    }
}