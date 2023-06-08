package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.dao.ArticleVenduDAO;

import java.util.List;

public class ArticleVenduManager {
    private static ArticleVenduDAO articleVenduDAO;
    static {
        articleVenduDAO = DAOFactory.getArticleDAO();
    }

    public void newArticle(ArticleVendu article) throws Exception{
        articleVenduDAO.insertArticle(article);
    }

    public List<ArticleVendu> selectAll() throws Exception {
        return articleVenduDAO.selectAll();
    }

    public ArticleVendu selectById(int idArticle) throws Exception {
        return articleVenduDAO.selectById(idArticle);
    }

    public void updateCurrentPrice(int noArticle, int newPrice) throws DALException {
        articleVenduDAO.updateCurrentPrice(noArticle, newPrice);
    }

    public List<ArticleVendu> selectByCategorie(int noCategorie) throws DALException {
        return articleVenduDAO.selectByCat(noCategorie);
    }
    public List<ArticleVendu> selectByKeywords(String motCle) throws DALException {
        return articleVenduDAO.selectByMotsCles(motCle);
    }

}
