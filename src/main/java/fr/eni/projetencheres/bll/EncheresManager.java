package fr.eni.projetencheres.bll;

import java.time.LocalDate;
import java.util.List;


import fr.eni.projetencheres.bo.*;
import fr.eni.projetencheres.dal.dao.ArticleVenduDAO;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.dao.EnchereDAO;
import fr.eni.projetencheres.dal.dao.RetraitDAO;

public class EncheresManager {

    private static EnchereDAO enchereDAO;

    static {
        enchereDAO = DAOFactory.getEnchereDAO();
    }

    // C reate

    public void createEnchere(Enchere enchere) throws DALException {
        enchereDAO.insertEncheres(enchere);
    }
}