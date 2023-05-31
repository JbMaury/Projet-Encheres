package fr.eni.projetencheres.dal;


import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Utilisateur;

public interface ArticleVenduDAO {

    public void insertArticle(ArticleVendu article) throws DALException;

    public ArticleVendu selectById(int id) throws DALException;

    public List<ArticleVendu> selectByCat(int id) throws DALException;

    public List<ArticleVendu> selectByNoUtilisateur(Utilisateur user) throws DALException;

    public void deleteArticle(ArticleVendu article) throws DALException;

    public List<ArticleVendu> selectByMotsCles(String motsCles) throws DALException;

    public List<ArticleVendu> selectEncheresOuvertes(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectMesEncheresEnCours(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectMesEncheresremportees(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectVentesNonDebutees(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectVentesTerminees(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectDebutToday() throws DALException;
}