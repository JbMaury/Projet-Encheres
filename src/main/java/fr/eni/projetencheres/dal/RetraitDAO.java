package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Retrait;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Retrait;

public interface RetraitDAO {

    void insertRetrait(Retrait retrait) throws DALException;

    Retrait selectByNoArticle(ArticleVendu art) throws DALException;

    default void insertRetrait(Retrait retrait) throws DALException {

    }

    default Retrait selectByNoArticle(ArticleVendu art) throws DALException {
        return null;
    }
}