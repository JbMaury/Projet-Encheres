<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"
         import="fr.eni.projetencheres.dal.*" import="fr.eni.projetencheres.bll.*"
         import="fr.eni.projetencheres.bo.*"
         import="java.time.format.DateTimeFormatter"
         import="java.time.format.FormatStyle"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Detail Vente</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
        crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
</nav>
<section class="container-fluid">
  <h1 class="mt-5 text-center">Détail Vente</h1>

  <div class="form-group row mt-md-4 justify-content-center">
    ${currentArticle.nomArticle}
  </div>

  <div class="form-group row mt-md-4 justify-content-center">
    ${currentArticle.description}
  </div>

  <div class="form-group row mt-md-4 justify-content-center">
    Catégorie :
    ${currentCategorie.libelle}
  </div>

  <div class="form-group row mt-md-4 justify-content-center">
    Mise à prix

    ${currentArticle.miseAPrix}
    points
  </div>

  <div class="form-group row mt-md-4 justify-content-center">
    <%--@declare id="meilleureoffre"--%><label for="meilleureOffre"
           class="col-4 col-md-3 col-lg-2 col-form-label ">Meilleure
      offre :</label>
    ${currentArticle.prixVente}
    points
  </div>

  <div class="form-group row mt-md-4 justify-content-center">
    <%--@declare id="finenchere"--%><label for="FinEnchere"
           class="col-4 col-md-3 col-lg-2 col-form-label ">Fin de
      l'enchère :</label>
      <c:set var="formattedDateFin" value="${currentArticle.dateFinEncheres.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}" />
    ${formattedDateFin}
  </div>

  <div class="form-group row mt-md-4 justify-content-center">
    <%--@declare id="retrait"--%><label for="Retrait" class="col-4 col-md-3 col-lg-2 col-form-label ">Retrait
      :</label>
   ${currentRetrait.rue} ${currentRetrait.codePostal} ${currentRetrait.ville}
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <label for="Retrait" class="col-4 col-md-3 col-lg-2 col-form-label ">Vendeur
      :</label>
    ${usersPseudos[currentArticle.noArticle]}
  </div>

  <form method="post" action="<%=request.getContextPath()%>/Encherir">
    <input type="hidden" name="noArticle"
           value="${currentArticle.noArticle}">
    <p>
      Ma proposition :
      <input class="form-control" type ="number" name="miseAPrix" id="miseAPrix" step="1" max="10000" value="" required>

      points
    </p>
    <button type="submit" class="btn btn-outline-success col-2">Enchérir</button>
  </form>
</section>
</body>
</html>