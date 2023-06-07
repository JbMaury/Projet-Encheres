package fr.eni.projetencheres.ihm;

import fr.eni.projetencheres.bll.*;
import fr.eni.projetencheres.bo.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@WebServlet("/Encherir")
public class ServletEncheres extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        RetraitManager retraitManager = new RetraitManager();
        CategorieManager categorieManager = new CategorieManager();
        EncheresManager encheresManager = new EncheresManager();
        try {
            HttpSession session = request.getSession();
            ArticleVendu enchereArticle = articleVenduManager.selectById(Integer.parseInt(request.getParameter("noArticle")));
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("userInfos");
            if(utilisateur.getCredit() >= Integer.parseInt(request.getParameter("offreArticle"))) {

                if(Integer.parseInt(request.getParameter("offreArticle")) < enchereArticle.getMiseAPrix() || Integer.parseInt(request.getParameter("offreArticle")) < enchereArticle.getPrixVente()) {
                    System.out.println("votre offre est trop basse");
                    Retrait retraitArticle = retraitManager.selectByIdArticle(Integer.parseInt(request.getParameter("noArticle")));
                    ArticleVendu currentArticle = articleVenduManager.selectById(Integer.parseInt(request.getParameter("noArticle")));
                    Categorie currentCategorie = categorieManager.getCategorieById(currentArticle.getNoCategorie());
                    request.setAttribute("errorCredit", "Votre offre est trop basse" );
                    request.setAttribute("currentArticle", currentArticle);
                    request.setAttribute("currentRetrait", retraitArticle);
                    request.setAttribute("currentCategorie", currentCategorie);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/Detailvente.jsp");
                    rd.forward(request, response);
                    return;
                }else {
                    System.out.println("vous avez assez de crédits");
                    System.out.println("votre offre est supérieure à la meilleure offre");
                    Utilisateur user = utilisateurManager.chercherPseudo((String) session.getAttribute("pseudo"));
                    Retrait retraitArticle = retraitManager.selectByIdArticle(Integer.parseInt(request.getParameter("noArticle")));
                    ArticleVendu article = articleVenduManager.selectById(Integer.parseInt(request.getParameter("noArticle")));

                    // Création d'une enchère dans la DB
                    Enchere enchere = new Enchere(
                                        user.getNoUtilisateur(),
                                        article.getNoArticle(),
                                        LocalDate.now(),
                                        Integer.parseInt(request.getParameter("offreArticle"))
                                            );
                    encheresManager.createEnchere(enchere);
                    // Mise à jour des crédits de l'utilisateur
                    int newCredit = utilisateur.getCredit() - Integer.parseInt(request.getParameter("offreArticle"));
                    utilisateurManager.updateCredit(utilisateur.getNoUtilisateur(),newCredit );
                    // Mise à jour du prix de vente de l'article
                    articleVenduManager.updateCurrentPrice(enchereArticle.getNoArticle(), Integer.parseInt(request.getParameter("offreArticle")));
                    ArticleVendu currentArticle = articleVenduManager.selectById(Integer.parseInt(request.getParameter("noArticle")));
                    // On renvoie sur la page de l'article avec une session actualisée
                    List<Enchere> encheres = encheresManager.selectByNoArticle(currentArticle.getNoArticle());
                    Enchere bestEnchere = encheres.get(0);
                    String bestEncherisseur = utilisateurManager.chercherId(bestEnchere.getNoUtilisateur()).getPseudo();
                    Utilisateur currentUser = utilisateurManager.chercherPseudo((String) session.getAttribute("pseudo"));
                    Categorie currentCategorie = categorieManager.getCategorieById(currentArticle.getNoCategorie());

                    session.setAttribute("userInfos", currentUser);
                    request.setAttribute("bestEncherisseurName", bestEncherisseur);
                    request.setAttribute("messageEnchere", "Votre enchère a été ajoutée avec succès" );
                    request.setAttribute("currentArticle", currentArticle);
                    request.setAttribute("currentRetrait", retraitArticle);
                    request.setAttribute("currentCategorie", currentCategorie);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/Detailvente.jsp");
                    rd.forward(request, response);

                }
            }else {
                System.out.println("vous n'avez pas assez de crédits");
                Retrait retraitArticle = retraitManager.selectByIdArticle(Integer.parseInt(request.getParameter("noArticle")));
                ArticleVendu currentArticle = articleVenduManager.selectById(Integer.parseInt(request.getParameter("noArticle")));
                Categorie currentCategorie = categorieManager.getCategorieById(currentArticle.getNoCategorie());
                request.setAttribute("errorCredit", "Vous n'avez pas assez de crédits" );
                request.setAttribute("currentArticle", currentArticle);
                request.setAttribute("currentRetrait", retraitArticle);
                request.setAttribute("currentCategorie", currentCategorie);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/Detailvente.jsp");
                rd.forward(request, response);
            }
//            currentArticle.setPrixVente(Integer.parseInt(request.getParameter("offreArticle")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}