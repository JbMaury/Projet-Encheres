package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.Article;
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
        EncheresDAO EncheresDAO = null;
        return EncheresDAO;
    }

    // méthode de création d'un articleDAO
    public static ArticleVenduDAO getArticleDAO() {

        ArticleVenduDAO art = new ArticleVenduDAO() {
            @Override
            public void insertArticle(ArticleVendu article) throws DALException {

            }

            @Override
            public ArticleVendu selectById(int id) throws DALException {
                return null;
            }

            @Override
            public List<ArticleVendu> selectByCat(int id) throws DALException {
                return null;
            }

            @Override
            public List<ArticleVendu> selectByNoUtilisateur(Utilisateur user) throws DALException {
                return null;
            }

            @Override
            public void deleteArticle(ArticleVendu article) throws DALException {

            }

            @Override
            public List<ArticleVendu> selectByMotsCles(String motsCles) throws DALException {
                return null;
            }

            @Override
            public List<ArticleVendu> selectEncheresOuvertes(Utilisateur user1) throws DALException {
                return null;
            }

            @Override
            public List<ArticleVendu> selectMesEncheresEnCours(Utilisateur user1) throws DALException {
                return null;
            }

            @Override
            public List<Article> selectMesEncheresremportees(Utilisateur user1) throws DALException {
                return null;
            }

            @Override
            public List<Article> selectVentesNonDebutees(Utilisateur user1) throws DALException {
                return null;
            }

            @Override
            public List<ArticleVendu> selectVentesTerminees(Utilisateur user1) throws DALException {
                return null;
            }

            @Override
            public List<Article> selectDebutToday() throws DALException {
                return null;
            }
        }DaoImpl();
        return art;
    }

    private static void DaoImpl() {
    }

    public static RetraitDAO getRetraitDAO() {
        RetraitDAO rdao = new RetraitDaoImpl();
        return rdao;
    }

}