<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name= "viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-enchères</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
    <div>
        <c:choose>
            <c:when test="${isConnected}">
                <a href="<%=request.getContextPath()%>/">Enchères</a>
                <a href="<%=request.getContextPath()%>/NouvelleVente">Vendre un article</a>
                <a href="<%=request.getContextPath()%>/Profil">Profil de ${userInfos.pseudo}</a>
                <a href="<%=request.getContextPath()%>/Deconnexion">Deconnexion</a>
            </c:when>
            <c:otherwise>
                <a href="<%=request.getContextPath()%>/Inscription">S'inscrire</a>
                <a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
            </c:otherwise>
        </c:choose>

    </div>
</nav>
<section class="container">
    <h2>Liste des enchères</h2>
    <c:if test="${not empty message}">
        <h6>${message}</h6>
    </c:if>
        <form class="boxflex_row" action="<%=request.getContextPath()%>/" method="POST">
                <h3>Filtres</h3>
                <input class="search"  size="30" type="text" name="rechercheArticle" placeholder="Le nom de l'article contient">
                <label for="categorie" class="col-sm-2 col-form-label">Catégorie :</label>
                <select id="categorie" class="form-control col-sm-5" name="categorie">
                    <option value="all">Toutes</option>
                    <%--Gestion des différentes catégories--%>
                    <c:forEach items="${categories}" var="categorie">
                        <c:choose>
                            <c:when test="${categorie.noCategorie == errorArticle.noCategorie || categorie.noCategorie == categorieFiltre.noCategorie}">
                                <option selected value="${categorie.noCategorie}">${categorie.libelle}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${categorie.noCategorie}">${categorie.libelle}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
        <%--  Filtre pour les achats et les ventes persos (non fonctionnel) --%>
           <%-- <c:if test="${isConnected}">
                <input type="radio" name="option" value="achat" id="achatRadio" required>
                <label for="achatRadio">Achats</label>

                <input type="radio" name="option" value="vente" id="venteRadio">
                <label for="venteRadio">Mes ventes</label>

                <div id="achatItems" class="disabled">
                    <input type="checkbox" name="enchereOuverte" value="enchereOuverte"> Enchères ouvertes<br>
                    <input type="checkbox" name="mesEncheres" value="mesEncheres"> Mes enchères en cours<br>
                    <input type="checkbox" name="encheresEmportees" value="encheresEmportees"> Enchères remportées<br>
                </div>

                <div id="venteItems" class="disabled">
                    <input type="checkbox" name="mesVentes" value="mesVentes"> Mes ventes en cours<br>
                    <input type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees"> Ventes non débutées<br>
                    <input type="checkbox" name="ventesTerminees" value="ventesTerminees"> Ventes terminées<br>
                </div>
            </c:if>--%>
            <input class="button" type="submit" value="Rechercher">
        </form>
    <h2 class="text text-center">Catégorie : <span class="text-info">${not empty categorieFiltre ? categorieFiltre.libelle : "Toutes"}</span></h2>
    <c:if test="${not empty messageError}">
        <h3 class="text-danger">${messageError}</h3>
    </c:if>
    <div class="container d-flex flex-row flex-wrap justify-content-around">

        <c:forEach items="${not empty encheresActuelles ? encheresActuelles : articlesFiltre}" var="articleEnVente">
            <c:set var="formattedDateFin" value="${articleEnVente.dateFinEncheres.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}" />
            <div class="d-flex flex-column col-md-5 border border-dark mt-3 mb-3">

                    <h3>${articleEnVente.nomArticle}</h3>
                    <ul>
                        <li>Prix : ${articleEnVente.prixVente == 0 ? articleEnVente.miseAPrix : articleEnVente.prixVente}</li>
                        <li>Fin de l'enchère : ${formattedDateFin}</li>
                        <li>Vendeur : ${usersPseudos[articleEnVente.noArticle]}</li>
                       <c:if test="${isConnected}"> <a class="text-center" href="<%=request.getContextPath()%>/Detail?id=${articleEnVente.noArticle}"><button class="btn btn-info mb-3">Détail de l'article</button></a></c:if>
                    </ul>

            </div>
        </c:forEach>

    </div>
</section>
</body>
</html>
