package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.dao.ArticleVenduDAO;

public class ArticleVenduManager {
    private static ArticleVenduDAO articleVenduDAO;
    static {
        articleVenduDAO = DAOFactory.getArticleDAO();
    }


}
