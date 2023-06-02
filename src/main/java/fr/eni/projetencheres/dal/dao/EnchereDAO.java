package fr.eni.projetencheres.dal.dao;

import java.util.List;

import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.dal.DALException;

public interface EnchereDAO {

    // CRUD Methods

    // C reate
    void insertEncheres(Enchere enchere) throws DALException;
    // R ead
    List<Enchere> selectByNoArticle (int idArticle) throws DALException;
    // U pdate

    // D elete
    void deleteEnchere(int idEnchere) throws DALException;



}