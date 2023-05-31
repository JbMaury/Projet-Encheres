package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Encheres;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Encheres;

interface EncheresDAO {

    public void deleteEncheres(int numUtil) throws DALException;
    public void insertEncheres(Encheres enchere) throws DALException;
    public List<Encheres> selectByNoArticle (ArticleVendu art) throws DALException;

    // supprimer une enchere
    void deleteEnchere(int numUtil) throws DALException;

    void insertEnchere(Encheres enchere) throws DALException;
}