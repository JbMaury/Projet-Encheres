<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import=" fr.eni.projetencheres.bo.Categorie" %>
  <%@page import="fr.eni.projetencheres.bll.CategorieManager"%>
  <%@page import="fr.eni.projetencheres.bo.Utilisateur"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <meta charset="ISO-8859-1">
    <title>Nouvelle vente</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
</nav>
<section class="container">
    <h1 class="mt-5 text-center">Nouvelle vente</h1>
    <form action="<%=request.getContextPath()%>/NouvelleVente"
          method="post" class="form-group mt-md-4 justify-content-center">
        <div class="form-group row justify-content-md-center">
            <label for="article" class="col-sm-2 col-form-label">Article :</label>
            <div class="col-sm-5">
                <input class="form-control" id="article" name="article" value="${not empty errorArticle ? errorArticle.nomArticle:''}" required>
            </div>
        </div>
        <div class="form-group row justify-content-md-center">
            <label for="description" class="col-sm-2 col-form-label">Description :</label>
            <div class="col-sm-5">
                <textarea class="form-control" id="description" name="description"  required>${not empty errorArticle ? errorArticle.description:''}</textarea>
            </div>
        </div>
        <div class="form-group row justify-content-md-center">
            <label for="categorie" class="col-sm-2 col-form-label">Catégorie :</label>
            <select id="categorie" class="form-control col-sm-5" name="categorie">
                <%--Gestion des différentes catégories--%>
                <c:forEach items="${categories}" var="categorie">
                    <c:choose>
                        <c:when test="${categorie.noCategorie == errorArticle.noCategorie}">
                            <option selected value="${categorie.noCategorie}">${categorie.libelle}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${categorie.noCategorie}">${categorie.libelle}</option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>
        </div>
        <div class="form-group row justify-content-md-center">

            <label for="upload" class="col-sm-2 col-form-label">Photo de l'article :</label>
            <div class="col-sm-5">
                <input type="file" class="form-control-file col-sm" id="upload">
            </div>
        </div>
        <div class="form-group row justify-content-md-center">

            <label class="col-sm-2 col-form-label" for="miseAPrix">Mise à prix :</label>
            <div class="col-sm-5">
                <input class="form-control" type ="number" name="miseAPrix" id="miseAPrix" step="1" max="10000" value="${not empty errorArticle ? errorArticle.miseAPrix:1}" required>
            </div>
        </div>
        <c:if test="${not empty errorMessage}">
            <p class="text-danger text-center">${errorMessage}</p>
        </c:if>
        <div class="form-group row justify-content-md-center">

            <label for="dateD" class="col-sm-2 col-form-label ">Début de l'enchère :</label>
            <div class="col-sm-5">
                <input type="date" class="form-control" id="dateD" name="dateD" required>
            </div>
        </div>


        <div class="form-group row justify-content-md-center">

            <label for="dateF" class="col-sm-2 col-form-label ">Fin de l'enchère :</label>
            <div class="col-sm-5">
                <input type="date" class="form-control" id="dateF" name="dateF" required>
            </div>
        </div>




        <fieldset name="Retrait" class="form-group justify-content-lg-center border p-3">
            <legend class="w-auto">Retrait</legend>

            <div class="form-group row justify-content-md-center">

                <label for="rue" class="col-sm-2 col-form-label ">Rue :</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="rue" name="rue" value="${userInfos.rue}" required>
                </div>
            </div>

            <div class="form-group row justify-content-md-center">

                <label for="codePostal" class="col-sm-2 col-form-label ">Code Postal :</label>
                <div class="col-sm-5">
                    <input class="form-control" id="codePostal" name="codePostal" value="${userInfos.codePostal}" required>
                </div>
            </div>

            <div class="form-group row justify-content-md-center">

                <label for="ville" class="col-sm-2 col-form-label ">Ville :</label>
                <div class="col-sm-5">
                    <input class="form-control" id="ville" name="ville" value ="${userInfos.ville}" required>
                </div>
            </div>

        </fieldset>


        <div class="row justify-content-center mt-5">
            <button type="submit" class="btn btn-outline-success col-2">Enregistrer</button>
            <a href="<%=request.getContextPath()%>/accueil" class="col-2 offset-1 btn btn-outline-danger" role="button">Annuler</a>
        </div>

    </form>
</section>




</body>
</html>
