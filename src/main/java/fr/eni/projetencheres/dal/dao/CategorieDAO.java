package fr.eni.projetencheres.dal.dao;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.dal.DALException;

public interface CategorieDAO {

    // CRUD Methods

    // C reate
    void insert(Categorie categorie) throws DALException;
    // R ead
    Categorie selectById(int idCategorie) throws DALException;
    List<Categorie> selectAll() throws DALException;
    // Update

    // Delete
    void delete(int idCategorie) throws DALException;



}