package fr.eni.projetencheres.dal.dao;

import fr.eni.projetencheres.bo.*;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Retrait;
import fr.eni.projetencheres.dal.DALException;

public interface RetraitDAO {
    void insertRetrait(Retrait retrait) throws DALException;
    Retrait selectByNoArticle(int idArticle) throws DALException;

}