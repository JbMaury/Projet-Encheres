package fr.eni.projetencheres.dal.dao;


import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DALException;

public interface ArticleVenduDAO {

    // CRUD methods

    //C reate
    void insertArticle(ArticleVendu article) throws DALException;

    //R ead
    ArticleVendu selectById(int idArticle) throws DALException;
    List<ArticleVendu> selectAll() throws DALException;
    List<ArticleVendu> selectByCat(int idCategorie) throws DALException;
    List<ArticleVendu> selectByNoUtilisateur(int idUtilisateur) throws DALException;
    List<ArticleVendu> selectByMotsCles(String motsCles) throws DALException;

    //U pdate
    void update(int idArticle) throws DALException;

    //D elete
    void deleteArticle(int idArticle) throws DALException;




   /* public List<ArticleVendu> selectEncheresOuvertes(int idUtilisateur) throws DALException;

    public List<ArticleVendu> selectMesEncheresEnCours(int idUtilisateur) throws DALException;

    public List<ArticleVendu> selectMesEncheresremportees(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectVentesNonDebutees(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectVentesTerminees(Utilisateur user1) throws DALException;

    public List<ArticleVendu> selectDebutToday() throws DALException;*/
}