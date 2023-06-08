package fr.eni.projetencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Enchere;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.ConnexionProvider;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.dao.*;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

    // insertion des requettes SQL
    private static final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final static String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?;";
    private final static String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS;";
    private final static String SELECT_BY_CAT = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie=? ;";
    private final static String SELECT_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=? AND date_fin_encheres > GETDATE() AND date_debut_encheres <= GETDATE();";
    private final static String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?;";
    private final static String SELECT_BY_MOTS_CLES = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ?;";
    private final static String UPDATE_PRICE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?;";
    private final static String SELECT_ENCHERES_OUVERTES = "select * from ARTICLES_VENDUS where date_debut_encheres <= GETDATE() AND date_fin_encheres > GETDATE() AND no_utilisateur != ?;";
    private final static String SELECT_ENCHERES_EN_COURS = "select * from ARTICLES_VENDUS inner join ENCHERES on ARTICLES_VENDUS.no_article = ENCHERES.no_article where ENCHERES.no_utilisateur = ?;";
    private final static String SELECT_ENCHERES_REMPORTEES = "select * from ARTICLES_VENDUS av\r\n"
            + "inner join ENCHERES e on av.no_article = e.no_article\r\n"
            + "where e.no_utilisateur = ? AND av.date_fin_encheres < GETDATE() AND e.montant_enchere = (\r\n"
            + "SELECT MAX(montant_enchere) FROM ENCHERES e2 WHERE e2.no_article = av.no_article \r\n" + ");";
    private final static String SELECT_MES_VENTES_NON_DEBUTEES = "select * from ARTICLES_VENDUS where no_utilisateur = ? and date_debut_encheres > getdate();";
    private final static String SELECT_MES_VENTES_TERMINEES = "select * from ARTICLES_VENDUS where no_utilisateur = ? and date_fin_encheres < getdate();";
    private final static String SELECT_DEBUT_TODAY = "select * from ARTICLES_VENDUS where date_debut_encheres = getdate();";

    @Override
    public void insertArticle(ArticleVendu article) throws DALException {

        try {
            System.out.println("dans l'insertion d'article");
            ResultSet rs = null;
            Connection cnx = ConnexionProvider.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            System.out.println("test des valeurs :");
            System.out.print(article.getNomArticle());
            System.out.print(" "+ article.getDescription());
            System.out.print(" " + Date.valueOf(article.getDateDebutEncheres()));
            System.out.print(" " + Date.valueOf(article.getDateFinEncheres()));
            System.out.print(" " + article.getMiseAPrix());
            System.out.print(" " + article.getNoUtilisateur());
            System.out.print(" " + article.getNoCategorie());

            pstmt.setString(1, article.getNomArticle());
            pstmt.setString(2, article.getDescription());
            pstmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
            pstmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
            pstmt.setInt(5, article.getMiseAPrix());
            pstmt.setInt(6, article.getNoUtilisateur());
            pstmt.setInt(7, article.getNoCategorie());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                article.setNoArticle(rs.getInt(1));
            }

            rs.close();
            pstmt.close();
            cnx.close();

        } catch (SQLException e) {
            throw new DALException("problème dans la méthode insertArticle", e);
        }

    }

    @Override
    public ArticleVendu selectById(int idArticle) throws DALException {

        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        ArticleVendu article = null;
        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, idArticle);
            rs = stmt.executeQuery();

            if (rs.next()) {
                article = new ArticleVendu(
                        idArticle,
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(),
                        rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        rs.getString("etat_vente"),
                        rs.getInt("no_utilisateur"),
                        rs.getInt("no_categorie"));
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode selectById d'article", e);
        }
        return article;
    }

    @Override
    public List<ArticleVendu> selectAll() throws DALException {
        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        ArticleVendu article = null;
        List<ArticleVendu> listeArticle = new ArrayList<>();
        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                article = new ArticleVendu(
                        rs.getInt("no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(),
                        rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        rs.getString("etat_vente"),
                        rs.getInt("no_utilisateur"),
                        rs.getInt("no_categorie")
                );
                listeArticle.add(article);
            }
            rs.close();
            stmt.close();
            cnx.close();
            System.out.println("dans la dal selectALL Articles");
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode selectAll d'article", e);
        }
        return listeArticle;
    }

    @Override
    public List<ArticleVendu> selectByCat(int idCategorie) throws DALException {

        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ArticleVendu> selection = new ArrayList<>();
        ArticleVendu article = null;

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_BY_CAT);
            stmt.setInt(1, idCategorie);
            rs = stmt.executeQuery();

            while (rs.next()) {
                article = new ArticleVendu(
                        rs.getInt("no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(),
                        rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        rs.getString("etat_vente"),
                        rs.getInt("no_utilisateur"),
                        rs.getInt("no_categorie")
                );
                selection.add(article);
            }
            rs.close();
            stmt.close();
            cnx.close();

        } catch (SQLException e) {
            throw new DALException("probleme avec la methode selectByCat d'article", e);
        }

        return selection;
    }

    public List<ArticleVendu> selectByNoUtilisateur(int idUtilisateur) throws DALException {

        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        List<ArticleVendu> selection = new ArrayList<>();
        ArticleVendu article = null;

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_BY_NO_UTILISATEUR);
            stmt.setInt(1, idUtilisateur);
            rs = stmt.executeQuery();

            while (rs.next()) {


                article = new ArticleVendu(
                        rs.getInt("no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(),
                        rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        rs.getString("etat_vente"),
                        rs.getInt("no_utilisateur"),
                        rs.getInt("no_categorie")
                );
                selection.add(article);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }
        return selection;
    }

    public void deleteArticle(int idArticle) throws DALException {
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = ConnexionProvider.getConnection();
            pstmt = cnx.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1, idArticle);
            pstmt.executeQuery();
            pstmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("Probleme de la methode deleteArticle", e);
        }

    }

    @Override
    public List<ArticleVendu> selectByMotsCles(String motsCles) throws DALException {
        System.out.println(motsCles);
        Connection cnx;
        PreparedStatement pstmt;
        ResultSet rs;
        List<ArticleVendu> selection = new ArrayList<>();
        ArticleVendu art = null;

        try {
            cnx = ConnexionProvider.getConnection();
            pstmt = cnx.prepareStatement(SELECT_BY_MOTS_CLES);
            pstmt.setString(1, "%" + motsCles + "%");
            System.out.println("juste après le set string");
            rs = pstmt.executeQuery();
            System.out.println("on vient de préparer la requête");

            while (rs.next()) {
                System.out.println("on est dans la boucle du resultset");
                art = new ArticleVendu(
                        rs.getInt("no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(),
                        rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        rs.getString("etat_vente"),
                        rs.getInt("no_utilisateur"),
                        rs.getInt("no_categorie")
                );
                System.out.println("article construit");
                selection.add(art);
                System.out.println("article ajouté");
            }
            rs.close();
            pstmt.close();
            cnx.close();
            System.out.println("fin de la recherche par mots clés");
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }

        return selection;
    }

    @Override
    public void update(int idArticle) throws DALException {

    }
    @Override
    public void updateCurrentPrice(int noArticle, int newPrice) throws DALException {
        Connection cnx;
        PreparedStatement pstmt;
        try {
            cnx = ConnexionProvider.getConnection();

            pstmt = cnx.prepareStatement(UPDATE_PRICE);
            pstmt.setInt(1, newPrice);
            pstmt.setInt(2, noArticle);
            pstmt.executeUpdate();
            cnx.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    @Override
    public List<ArticleVendu> selectEncheresOuvertes(int idUtilisateur) throws DALException {
        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        List<ArticleVendu> selection = new ArrayList<ArticleVendu>();
        ArticleVendu art = null;
        Categorie cat;
        Utilisateur user;
        Enchere meilleureOffre = null;
        List<Enchere> encheres;
        CategorieDAO cdao = DAOFactory.getCategorieDAO();
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        EnchereDAO edao = DAOFactory.getEnchereDAO();
        RetraitDAO rdao = DAOFactory.getRetraitDAO();

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_ENCHERES_OUVERTES);
            stmt.setInt(1, user1.getNoUtilisateur());
            rs = stmt.executeQuery();

            while (rs.next()) {

                user = udao.selectById(rs.getInt("no_utilisateur"));
                cat = cdao.selectById(rs.getInt("no_categorie"));

                art = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"), user, cat);

                art.setNoArticle(rs.getInt("no_article"));
                art.setRetrait(rdao.selectByNoArticle(art));

                encheres = edao.selectByNoArticle(art);
                art.setEncheres(encheres);
                if (encheres != null) {
                    for (Enchere enchere : encheres) {
                        if (meilleureOffre == null) {
                            meilleureOffre = enchere;
                        } else if (enchere.getMontantEnchere() > meilleureOffre.getMontantEnchere()) {
                            meilleureOffre = enchere;
                        }
                    }
                    art.setAcheteur(meilleureOffre.getUtilisateur());
                    art.setPrixVentes(meilleureOffre.getMontantEnchere());
                } else {
                    art.setPrixVentes(art.getPrixInitial());
                }
                selection.add(art);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }
        return selection;
    }

    @Override
    public List<Article> selectMesEncheresEnCours(int idUtilisateur) throws DALException {

        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        List<Article> selection = new ArrayList<Article>();
        Article art = null;
        Categorie cat;
        Utilisateur user;
        Enchere meilleureOffre = null;
        List<Enchere> encheres;
        CategorieDAO cdao = DAOFactory.getCategorieDAO();
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        EnchereDAO edao = DAOFactory.getEnchereDAO();
        RetraitDAO rdao = DAOFactory.getRetraitDAO();

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_ENCHERES_EN_COURS);
            stmt.setInt(1, user1.getNoUtilisateur());
            rs = stmt.executeQuery();

            while (rs.next()) {

                user = udao.selectById(rs.getInt("no_utilisateur"));
                cat = cdao.selectById(rs.getInt("no_categorie"));

                art = new Article(rs.getString("nom_article"), rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"), user, cat);

                art.setNoArticle(rs.getInt("no_article"));
                art.setRetrait(rdao.selectByNoArticle(art));

                encheres = edao.selectByNoArticle(art);
                art.setEncheres(encheres);

                if (encheres != null) {
                    for (Enchere enchere : encheres) {
                        if (meilleureOffre == null) {
                            meilleureOffre = enchere;
                        } else if (enchere.getMontantEnchere() > meilleureOffre.getMontantEnchere()) {
                            meilleureOffre = enchere;
                        }
                    }
                    art.setAcheteur(meilleureOffre.getUtilisateur());
                    art.setPrixVentes(meilleureOffre.getMontantEnchere());
                } else {
                    art.setPrixVentes(art.getPrixInitial());
                }
                selection.add(art);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }
        return selection;
    }

    @Override
    public List<Article> selectMesEncheresremportees(Utilisateur user1) throws DALException {

        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        List<Article> selection = new ArrayList<Article>();
        Article art = null;
        Categorie cat;
        Utilisateur user;
        Enchere meilleureOffre = null;
        List<Enchere> encheres;
        CategorieDAO cdao = DAOFactory.getCategorieDAO();
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        EnchereDAO edao = DAOFactory.getEnchereDAO();
        RetraitDAO rdao = DAOFactory.getRetraitDAO();

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_ENCHERES_REMPORTEES);
            stmt.setInt(1, user1.getNoUtilisateur());
            rs = stmt.executeQuery();

            while (rs.next()) {

                user = udao.selectById(rs.getInt("no_utilisateur"));
                cat = cdao.selectById(rs.getInt("no_categorie"));

                art = new Article(rs.getString("nom_article"), rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"), user, cat);
                art.setNoArticle(rs.getInt("no_article"));
                art.setRetrait(rdao.selectByNoArticle(art));

                encheres = edao.selectByNoArticle(art);
                art.setEncheres(encheres);

                if (encheres != null) {
                    for (Enchere enchere : encheres) {
                        if (meilleureOffre == null) {
                            meilleureOffre = enchere;
                        } else if (enchere.getMontantEnchere() > meilleureOffre.getMontantEnchere()) {
                            meilleureOffre = enchere;
                        }
                    }
                    art.setAcheteur(meilleureOffre.getUtilisateur());
                    art.setPrixVentes(meilleureOffre.getMontantEnchere());
                } else {
                    art.setPrixVentes(art.getPrixInitial());
                }
                selection.add(art);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }
        return selection;
    }

    @Override
    public List<Article> selectVentesNonDebutees(Utilisateur user1) throws DALException {

        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        List<Article> selection = new ArrayList<Article>();
        Article art = null;
        Categorie cat;
        Utilisateur user;
        Enchere meilleureOffre = null;
        List<Enchere> encheres;
        CategorieDAO cdao = DAOFactory.getCategorieDAO();
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        EnchereDAO edao = DAOFactory.getEnchereDAO();
        RetraitDAO rdao = DAOFactory.getRetraitDAO();

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_MES_VENTES_NON_DEBUTEES);
            stmt.setInt(1, user1.getNoUtilisateur());
            rs = stmt.executeQuery();

            while (rs.next()) {

                user = udao.selectById(rs.getInt("no_utilisateur"));
                cat = cdao.selectById(rs.getInt("no_categorie"));

                art = new Article(rs.getString("nom_article"), rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"), user, cat);
                art.setNoArticle(rs.getInt("no_article"));
                art.setRetrait(rdao.selectByNoArticle(art));

                encheres = edao.selectByNoArticle(art);
                art.setEncheres(encheres);

                if (encheres != null) {
                    for (Enchere enchere : encheres) {
                        if (meilleureOffre == null) {
                            meilleureOffre = enchere;
                        } else if (enchere.getMontantEnchere() > meilleureOffre.getMontantEnchere()) {
                            meilleureOffre = enchere;
                        }
                    }
                    art.setAcheteur(meilleureOffre.getUtilisateur());
                    art.setPrixVentes(meilleureOffre.getMontantEnchere());
                } else {
                    art.setPrixVentes(art.getPrixInitial());
                }
                selection.add(art);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }
        return selection;
    }

    @Override
    public List<Article> selectVentesTerminees(Utilisateur user1) throws DALException {

        Connection cnx;
        PreparedStatement stmt;
        ResultSet rs;
        List<Article> selection = new ArrayList<Article>();
        Article art = null;
        Categorie cat;
        Utilisateur user;
        Enchere meilleureOffre = null;
        List<Enchere> encheres;
        CategorieDAO cdao = DAOFactory.getCategorieDAO();
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        EnchereDAO edao = DAOFactory.getEnchereDAO();
        RetraitDAO rdao = DAOFactory.getRetraitDAO();

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_MES_VENTES_TERMINEES);
            stmt.setInt(1, user1.getNoUtilisateur());
            rs = stmt.executeQuery();

            while (rs.next()) {

                user = udao.selectById(rs.getInt("no_utilisateur"));
                cat = cdao.selectById(rs.getInt("no_categorie"));

                art = new Article(rs.getString("nom_article"), rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"), user, cat);
                art.setNoArticle(rs.getInt("no_article"));
                art.setRetrait(rdao.selectByNoArticle(art));

                encheres = edao.selectByNoArticle(art);
                art.setEncheres(encheres);

                if (encheres != null) {
                    for (Enchere enchere : encheres) {
                        if (meilleureOffre == null) {
                            meilleureOffre = enchere;
                        } else if (enchere.getMontantEnchere() > meilleureOffre.getMontantEnchere()) {
                            meilleureOffre = enchere;
                        }
                    }
                    art.setAcheteur(meilleureOffre.getUtilisateur());
                    art.setPrixVentes(meilleureOffre.getMontantEnchere());
                } else {
                    art.setPrixVentes(art.getPrixInitial());
                }
                selection.add(art);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }
        return selection;
    }

    @Override
    public List<Article> selectDebutToday() throws DALException {

        Connection cnx;
        Statement stmt;
        ResultSet rs;
        List<Article> selection = new ArrayList<Article>();
        Article art = null;
        Categorie cat;
        Utilisateur user;
        Enchere meilleureOffre = null;
        List<Enchere> encheres;
        CategorieDAO cdao = DAOFactory.getCategorieDAO();
        UtilisateurDAO udao = DAOFactory.getUtilisateurDAO();
        EnchereDAO edao = DAOFactory.getEnchereDAO();
        RetraitDAO rdao = DAOFactory.getRetraitDAO();

        try {
            cnx = ConnexionProvider.getConnection();
            stmt = cnx.createStatement();
            rs = stmt.executeQuery(SELECT_DEBUT_TODAY);

            while (rs.next()) {

                user = udao.selectById(rs.getInt("no_utilisateur"));
                cat = cdao.selectById(rs.getInt("no_categorie"));

                art = new Article(rs.getString("nom_article"), rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"), user, cat);
                art.setNoArticle(rs.getInt("no_article"));
                art.setRetrait(rdao.selectByNoArticle(art));

                encheres = edao.selectByNoArticle(art);
                art.setEncheres(encheres);

                if (encheres != null) {
                    for (Enchere enchere : encheres) {
                        if (meilleureOffre == null) {
                            meilleureOffre = enchere;
                        } else if (enchere.getMontantEnchere() > meilleureOffre.getMontantEnchere()) {
                            meilleureOffre = enchere;
                        }
                    }
                    art.setAcheteur(meilleureOffre.getUtilisateur());
                    art.setPrixVentes(meilleureOffre.getMontantEnchere());
                } else {
                    art.setPrixVentes(art.getPrixInitial());
                }
                selection.add(art);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            throw new DALException("probleme avec la methode select by mots-cles de articles", e);
        }
        return selection;
    }*/
}