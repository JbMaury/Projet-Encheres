package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.*;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Retrait;

public interface RetraitDAO {
    public void insertRetrait(Retrait retrait) throws DALException;
    public Retrait selectByNoArticle(ArticleVendu art) throws DALException;

}