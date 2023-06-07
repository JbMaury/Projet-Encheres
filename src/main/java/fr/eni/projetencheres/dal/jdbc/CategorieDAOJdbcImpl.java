package fr.eni.projetencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.dal.ConnexionProvider;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.dao.CategorieDAO;

public class CategorieDAOJdbcImpl implements CategorieDAO {

    // insertion methodes SQL
    private final static String SELECT_ALL = "SELECT * FROM CATEGORIES;";
    private final static String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?;";

    @Override
    public List<Categorie> selectAll() throws DALException {

        Categorie categorie;
        List<Categorie> categories = new ArrayList<>();

        try {
            Connection cnx = ConnexionProvider.getConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            while (rs.next()) {
                // creation du numero de categorie et du libelle
                categorie = new Categorie(
                        rs.getInt("no_categorie"),
                        rs.getString("libelle"));
                // ajout de la categorie
                categories.add(categorie);
            }
            // fermeture de connection ...
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probl�me avec la m�thode selectAll de cat�gorie", e);
        }

        return categories;
    }

    @Override
    public void delete(int idCategorie) throws DALException {

    }

    @Override
    public void insert(Categorie categorie) throws DALException {

    }

    @Override
    public Categorie selectById(int idCategorie) throws DALException {
        System.out.println("dans le selecteur de cat by id JDBC");
        System.out.println("l'id de la cat a trouver :" + idCategorie);
        //declaration de mes variables
        Categorie categorie = null;
        Connection cnx;
        ResultSet rs;
        PreparedStatement pstmt;

        // recuperation de la connexion
        try {
            //hydratation de mes variables
            cnx = ConnexionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1, idCategorie);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                categorie = new Categorie(
                        rs.getInt("no_categorie"),
                        rs.getString("libelle")
                );
            }
            System.out.println("objet cat trouvé :" + categorie.getLibelle());
            // fermeture de connection...
            rs.close();
            pstmt.close();
            cnx.close();

        } catch (SQLException e) {
            throw new DALException("problème avec la méthode selectById de catégorie", e);
        }

        return categorie;
    }

}