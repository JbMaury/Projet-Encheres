package fr.eni.projetencheres.dal.jdbc;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.dao.EnchereDAO;
import fr.eni.projetencheres.dal.dao.UtilisateurDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncheresDAOJdbcImpl implements EnchereDAO {
    private static final String DELETE_ENCHERE = "DELETE FROM ENCHERES where no_utilisateur = ?;";
    private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?);";
    private final static String SELECT_BY_NO_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article=?";
    private final static String UPDATE_ENCHERE = "UPDATE ENCHERES SET montant_enchere=?, date_enchere=? WHERE no_article=? and no_utilisateur=?;";
    private final static String SELECT_BY_ARTICLE_USER = "SELECT * FROM ENCHERES WHERE no_article=? and no_utilisateur=?;";

    @Override
    // supprimer une enchere
    public void deleteEnchere(int numUtil) throws DALException {
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = fr.eni.projetencheres.util.ConnexionProvider.getConnection();
            pstmt.setInt(1, numUtil);
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
            cnx = fr.eni.projetencheres.util.ConnexionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE_USER);
            pstmt.setInt(1, enchere.getArt().getNoArticle());
            pstmt.setInt(2, enchere.getUtilisateur().getNoUtilisateur());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
                pstmt.setInt(1, enchere.getMontantEnchere());
                pstmt.setDate(2, Date.valueOf(enchere.getDateEnchere()));
                pstmt.setInt(3, enchere.getArt().getNoArticle());
                pstmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
            } else {
                pstmt = cnx.prepareStatement(INSERT_ENCHERE);
                pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
                pstmt.setInt(2, enchere.getArt().getNoArticle());
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
    public void deleteEncheres(int numUtil) throws DALException {

    }


    @Override
    public List<Enchere> selectByNoArticle(ArticleVendu art) throws DALException {

        Enchere enchere;
        List<Enchere> encheres = new ArrayList<Enchere>();
        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        Utilisateur user;

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE);
            stmt.setInt(1, art.getNoArticle());
            rs = stmt.executeQuery();

            while (rs.next()) {
                // recuperation des informations de l'utilisateur grace a son numero("no_utilisateur")
                user = udao.selectById(rs.getInt("no_utilisateur"));
                enchere = new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"), user,
                        art);
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
