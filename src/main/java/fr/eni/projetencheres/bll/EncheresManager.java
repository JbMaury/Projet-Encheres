package fr.eni.projetencheres.bll;

import java.time.LocalDate;
import java.util.List;


import fr.eni.projetencheres.bo.*;
import fr.eni.projetencheres.dal.ArticleVenduDAO;
import fr.eni.projetencheres.dal.DALException;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.EncheresDAO;
import fr.eni.projetencheres.dal.RetraitDAO;

public class EncheresManager {

    private static EncheresManager instance;

    private EncheresManager() {

    }

    public static EncheresManager getInstance() {
        if (instance == null) {
            instance = new EncheresManager();
        }
        return instance;
    }

    public List<Article> listerArticles() throws DALException {

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        // insertion d'une liste d'article
        List<Article> liste = adao.selectDebutToday();

        return liste;
    }

    // methode de listage d'article en fonction de la categorie
    public List<ArticleVendu> triByCategorie(int id) throws DALException {

        List<ArticleVendu> selection = null;
        ArticleVenduDAO adao = null;

        adao = DAOFactory.getArticleDAO();
        selection = adao.selectByCat(id);

        return selection;
    }

    public List<ArticleVendu> triByEncheresOuvertes(Utilisateur user) throws DALException {

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        List<ArticleVendu> selection = adao.selectEncheresOuvertes(user);

        return selection;
    }

    public List<ArticleVendu> triByEncheresEnCours(Utilisateur user) throws DALException {

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        List<ArticleVendu> selection = adao.selectMesEncheresEnCours(user);

        return selection;
    }

    public List<Article> triByEncheresRemportees(Utilisateur user) throws DALException {

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        List<Article> selection = adao.selectMesEncheresremportees(user);

        return selection;
    }

    public List<ArticleVendu> triByVenteEnCours(Utilisateur user) throws DALException {

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        List<ArticleVendu> selection = adao.selectByNoUtilisateur(user);

        return selection;
    }

    public List<ArticleVendu> triByVentesNonDebutees(Utilisateur user) throws DALException {

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        List<ArticleVendu> selection = adao.selectVentesNonDebutees(user);

        return selection;
    }

    public List<ArticleVendu> triByVentesTerminees(Utilisateur user) throws DALException {

        ArticleVenduDAO adao = DAOFactory.getArticleDAO();
        List<ArticleVendu> selection = adao.selectVentesTerminees(user);

        return selection;
    }

    public List<ArticleVendu> triByMotsCles(String motsCles) throws DALException {

        List<ArticleVendu> selection = null;
        ArticleVenduDAO adao = null;

        adao = DAOFactory.getArticleDAO();
        selection = adao.selectByMotsCles(motsCles);

        return selection;
    }

    // methode pour creer une nouvelle vente
    public void nouvelleVente(String nom, String description, LocalDate debut, LocalDate fin, int prixInitial,
                              Utilisateur utilisateur, Categorie categorie, String rue, String cp, String ville) throws DALException {

        ArticleVendu article = new ArticleVendu(nom, description, debut, fin, prixInitial, utilisateur, categorie);
        ArticleVenduDAO aDAO = DAOFactory.getArticleDAO();
        aDAO.insertArticle(article);
        Retrait retrait = new Retrait(rue, cp, ville, article);
        RetraitDAO rdao = DAOFactory.getRetraitDAO();
        rdao.insertRetrait(retrait);
    }

    public ArticleVendu obtenirArticle(int id) throws DALException {

        ArticleVendu article;
        ArticleVenduDAO adao;
        EncheresDAO edao;
        List<Enchere> encheres;
        Enchere meilleurOffre = null;

        // recuperation de l'article en BDD
        adao = DAOFactory.getArticleDAO();
        article = adao.selectById(id);

        // recuperation des encheres sur cet article en BDD
        edao = DAOFactory.getEnchereDAO();
        encheres = edao.selectByNoArticle((Article) article);

        // ajout de la liste d'encheres � l'instance d'article
        article.setEncheres(encheres);

        // recherche de la meilleure offre et modification des parametres de l'article
        for (Enchere enchere : encheres) {
            if (meilleurOffre == null) {
                meilleurOffre = enchere;
            } else if (enchere.getMontantEnchere() > meilleurOffre.getMontantEnchere()) {
                meilleurOffre = enchere;
            }
        }
        if (meilleurOffre != null) {
            article.setEncheres(meilleurOffre.getUtilisateur());
            article.setPrixVentes(meilleurOffre.getMontantEnchere());
        } else {
            article.setPrixVentes(article.getPrixInitial());
        }

        return article;
    }

    public void encherir(Utilisateur user, ArticleVendu art, int montantEnchere) throws DALException {
        Enchere enchere = new Enchere(LocalDate.now(), montantEnchere, user, art);
        art.ajouterEncheres(user, enchere);
        EncheresDAO edao = DAOFactory.getEnchereDAO();
        edao.insertEncheres(enchere);
    }

    public boolean verifEnchereNombre(String str) {
        boolean ok;
        char[] c = str.toCharArray();

        for (int i=0; i<c.length; i++) {
            ok = Character.isDigit(c[i]);
            if (!ok) {
                return ok;
            }
        }
        ok = true;
        return ok;
    }

    public boolean verifEnchere(ArticleVendu art, int enchere) {

        boolean ok;

        if (enchere <= art.getPrixVentes()) {
            ok = false;
        } else {
            ok = true;
        }

        return ok;
    }

    public boolean verifBudget(Utilisateur user, int enchere) {

        boolean ok;

        if(enchere > user.getCredit()) {
            ok = false;
        } else {
            ok = true;
        }
        return ok;
    }

}