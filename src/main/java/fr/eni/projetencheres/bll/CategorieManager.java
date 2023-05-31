package fr.eni.projetencheres.bll;


import java.util.ArrayList;


import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.dal.CategorieDAO;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;

public class CategorieManager {

    private static CategorieManager instance;
    private static ArrayList<Categorie> categories = new ArrayList<>();
    private static CategorieDAO cDAO;

    private CategorieManager() {

    }

    public static CategorieManager getInstance() throws DALException {
        cDAO = DAOFactory.getCategorieDAO();
        categories = cDAO.selectAll();
        // parametrage de l'instance
        if (instance == null) {
            instance = new CategorieManager();
        }
        // renvoie de l'instance
        return instance;
    }

    // methode pour choisir une categorie
    public Categorie chercherCategorie(String libelle) {
        // initialisattion de categorie a null
        Categorie categorie = null;

        for (Categorie cat : categories) {
            if (cat.getLibelle().equals(libelle)) {
                categorie = cat;
                break;
            }
        }
        return categorie;
    }

}