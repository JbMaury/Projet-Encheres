package fr.eni.projetencheres.ihm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;



import fr.eni.projetencheres.bll.BLLException;
import fr.eni.projetencheres.bll.ArticleVenduManager;
import fr.eni.projetencheres.bll.CategorieManager;
import fr.eni.projetencheres.bll.EncheresManager;

import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Categorie;
import fr.eni.projetencheres.bo.Retrait;
import fr.eni.projetencheres.bo.Utilisateur;

@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNouvelleVente() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVente.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ArticleVendu article = new ArticleVendu();

        try {
            //Récupération des paramètres
            String nom = request.getParameter("article");
            String description = request.getParameter("description");
            LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("dated"));
            LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("datef"));
            int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
            int categerieId = Integer.parseInt(request.getParameter("categorie"));
            String rue = request.getParameter("rue");
            String codePostal = request.getParameter("codePostal");
            String ville = request.getParameter("ville");

            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
            Retrait retrait = new Retrait();







            //S'il y a des erreurs, renvoie la page de vente avec les erreurs
            List<Integer> listeErreurs = new ArrayList<>();
            if(listeErreurs.size()>0) {
                request.setAttribute("listeErreurs", listeErreurs);
                doGet(request, response);
            }
            else {
                try {
                    article.setNomArticle(nom);
                    article.setDescription(description);
                    article.setDateDebutEncheres(dateDebutEnchere);
                    article.setDateFinEncheres(dateFinEnchere);
                    article.setMiseAPrix(miseAPrix);

                    retrait = new Retrait();
                    retrait.setRue(rue);
                    retrait.setCodePostal(codePostal);
                    retrait.setVille(ville);
                    retrait.setNoArticle(noArticle);


                    session.setAttribute("utilisateur", utilisateur);
                    response.sendRedirect(request.getContextPath() + "/accueil");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
