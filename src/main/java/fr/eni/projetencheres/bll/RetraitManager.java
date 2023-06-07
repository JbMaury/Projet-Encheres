package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.Retrait;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.dao.RetraitDAO;
import fr.eni.projetencheres.dal.dao.UtilisateurDAO;

public class RetraitManager {

    private static RetraitDAO retraitDAO;

    static {
        retraitDAO = DAOFactory.getRetraitDAO();
    }
    public void newRetrait(Retrait retrait) throws Exception {
        retraitDAO.insertRetrait(retrait);
    }

    public Retrait selectByIdArticle(int idArticle) throws Exception {
        return retraitDAO.selectByNoArticle(idArticle);
    }
}
