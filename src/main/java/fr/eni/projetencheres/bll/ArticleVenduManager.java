package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.ArticleVendu;
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

}
