package fr.eni.projetencheres.dal.jdbc;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.ConnexionProvider;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.dao.EnchereDAO;
import fr.eni.projetencheres.dal.dao.UtilisateurDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncheresDAOJdbcImpl implements EnchereDAO {
    private static final String DELETE_ENCHERE = "DELETE FROM ENCHERES where no_enchere = ?;";
    private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?);";
    private final static String SELECT_BY_NO_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article=?";
    private final static String UPDATE_ENCHERE = "UPDATE ENCHERES SET montant_enchere=?, date_enchere=? WHERE no_article=? and no_utilisateur=?;";
    private final static String SELECT_BY_ARTICLE_USER = "SELECT * FROM ENCHERES WHERE no_article=? and no_utilisateur=?;";

    @Override
    // supprimer une enchere
    public void deleteEnchere(int idEnchere) throws DALException {
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnexionProvider.getConnection();
            pstmt = cnx.prepareStatement(DELETE_ENCHERE);
            pstmt.setInt(1, idEnchere);
            pstmt.executeUpdate();

            pstmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme de deleteEnchere ", e);
        }
    }

    @Override
    // creer une enchere
    public void insertEncheres(Enchere enchere) throws DALException {

        Connection cnx;
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            // preparer les parametres
            cnx = ConnexionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE_USER);
            pstmt.setInt(1, enchere.getNoArticle());
            pstmt.setInt(2, enchere.getNoUtilisateur());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
                pstmt.setInt(1, enchere.getMontantEnchere());
                pstmt.setDate(2, Date.valueOf(enchere.getDateEnchere()));
                pstmt.setInt(3, enchere.getNoArticle());
                pstmt.setInt(4, enchere.getNoUtilisateur());
            } else {
                pstmt = cnx.prepareStatement(INSERT_ENCHERE);
                pstmt.setInt(1, enchere.getNoUtilisateur());
                pstmt.setInt(2, enchere.getNoArticle());
                pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
                pstmt.setInt(4, enchere.getMontantEnchere());
            }
            pstmt.executeUpdate();
            rs.close();
            pstmt.close();
            cnx.close();

        } catch (SQLException e) {
            throw new DALException("problème avec la méthode insertEnchere", e);
        }
    }

   @Override
    public List<Enchere> selectByNoArticle(int idArticle) throws DALException {

        Enchere enchere;
        List<Enchere> encheres = new ArrayList<Enchere>();
        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        Utilisateur user;

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
            stmt.setInt(1, idArticle);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // recuperation des informations de l'utilisateur grace a son numero("no_utilisateur")
                enchere = new Enchere(
                        rs.getInt("no_enchere"),
                        rs.getInt("no_utilisateur"),
                        rs.getInt("no_article"),
                        rs.getDate("date_enchere").toLocalDate(),
                        rs.getInt("montant_enchere")
                );
                // ajout de l'enchere
                encheres.add(enchere);
            }

            rs.close();
            stmt.close();
            cnx.close();

        } catch (SQLException e) {
            throw new DALException("problème avec la méthode selectByNoArticle de EnchereDAO", e);
        }

        return encheres;
    }

}
