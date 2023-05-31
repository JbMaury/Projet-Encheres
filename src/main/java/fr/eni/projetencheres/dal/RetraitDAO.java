package fr.eni.projetencheres.dal;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Retrait;
import fr.eni.projetencheres.bo.Retrait;

public interface RetraitDAO {

    public void insertRetrait(Retrait retrait) throws DALException;

    public Retrait selectByNoArticle(Article art) throws DALException;

    void insertRetrait(Retrait retrait);
}