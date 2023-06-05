<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name= "viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-enchères</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<header>
    <h1>ENI - Enchères</h1>
    <div>
        <c:choose>
            <c:when test="${isConnected}">
                <a href="<%=request.getContextPath()%>/">Enchères</a>
                <a href="<%=request.getContextPath()%>/NouvelleVente">Vendre un article</a>
                <a href="<%=request.getContextPath()%>/Profil">Profil de ${pseudo}</a>
                <a href="<%=request.getContextPath()%>/Deconnexion">Deconnexion</a>
            </c:when>
            <c:otherwise>
                <a href="<%=request.getContextPath()%>/Inscription">S'inscrire</a>
                <a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
            </c:otherwise>
        </c:choose>

    </div>


</header>
<main>
    <h2>Liste des enchères</h2>
    <c:if test="${not empty message}">
        <h6>${message}</h6>
    </c:if>
        <form class="boxflex_row" action="<%=request.getContextPath()%>/ServletAccueil" method="POST">
            <fieldset>
                <legend>Filtres</legend>
                <input class="search" type="text" name="rechercheArticle" placeholder="Le nom de l'article contient">
                <label for="Categories">Catégories : </label>
                <select id="Categories">
                    <option value="Tout" selected="selected">Tout</option>
                    <option value="Informatique">Informatique</option>
                    <option value="Ameublement">Ameublement</option>
                    <option value="Vetements">Vêtements</option>
                    <option value="SportsEtLoisirs">Sports & Loisirs</option>
                </select>
            </fieldset>
            <input class="button" type="button" value="Rechercher">
        </form>
    <div class="encheres">
        <article>
            <img src="" alt="">
            <div class="card_text">
                <h3>PC Gamer pour travailler</h3>
                <ul>
                    <li>Prix : 210 points</li>
                    <li>Fin de l'enchère : 10/06/2023</li>
                    <li>Vendeur : Jojo72</li>
                </ul>
            </div>
        </article>
        <article>
            <img src="" alt="">
            <div class="card_text">
                <h3>Rocket Stove pour riz et pâtes</h3>
                <ul>
                    <li>Prix : 45 points</li>
                    <li>Fin de l'enchère : 14/06/2023</li>
                    <li>Vendeur : Leila34</li>
                </ul>
            </div>

        </article>
        <article>

        </article>
    </div>
</main>
<footer>

</footer>
</body>
</html>
