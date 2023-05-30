package fr.eni.projetencheres.dal;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Retrait;

public interface RetraitDAO {

    public void insertRetrait(Retrait retrait) throws DALException;

    public Retrait selectByNoArticle(Article art) throws DALException;
}