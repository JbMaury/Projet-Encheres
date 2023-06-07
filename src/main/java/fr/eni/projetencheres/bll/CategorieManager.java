package fr.eni.projetencheres.bll;


import java.util.ArrayList;
import java.util.List;


import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.dal.dao.CategorieDAO;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;

public class CategorieManager {
    private static CategorieDAO categorieDAO;

   static {
       categorieDAO = DAOFactory.getCategorieDAO();
   }

    // C reate
    public void createCategorie(Categorie categorie) throws DALException, BLLException {
       // A faire : Check de la taille du nouveau libelle
       // A faire : Check de l'unicité dans la DB
       categorieDAO.insert(categorie);
    }
    // R ead
    public Categorie getCategorieById(int idCategorie) throws DALException {
        System.out.println("dans le manager cat");
       return categorieDAO.selectById(idCategorie);
    }
    public List<Categorie> getAllCategories() throws DALException {
       return categorieDAO.selectAll();
    }

}