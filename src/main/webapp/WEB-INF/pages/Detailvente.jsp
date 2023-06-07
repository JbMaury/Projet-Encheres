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
      <div>
        <c:choose>
          <c:when test="${isConnected}">
            <a href="<%=request.getContextPath()%>/">Enchères</a>
            <a href="<%=request.getContextPath()%>/NouvelleVente">Vendre un article</a>
            <a href="<%=request.getContextPath()%>/Profil">Profil de ${userInfos.pseudo} Crédits : ${userInfos.credit}</a>
            <a href="<%=request.getContextPath()%>/Deconnexion">Deconnexion</a>
          </c:when>
          <c:otherwise>
            <a href="<%=request.getContextPath()%>/Inscription">S'inscrire</a>
            <a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
          </c:otherwise>
        </c:choose>
      </div>

</nav>
<section class="container-fluid">
  <h1 class="mt-5 text-center">Détail Vente</h1>
  <c:if test="${not empty messageEnchere}">
    <p>${messageEnchere}</p>
  </c:if>


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
    <c:choose>
      <c:when test="${not empty bestEncherisseurName}">
        ${currentArticle.prixVente}
        points par ${bestEncherisseurName}
      </c:when>
      <c:otherwise>
        Pas encore d'offres
      </c:otherwise>
    </c:choose>

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

  <c:if test="${!userInfos.pseudo.equals(usersPseudos[currentArticle.noArticle])}">
    <form method="post" action="<%=request.getContextPath()%>/Encherir">
      <input type="hidden" name="noArticle"
             value="${currentArticle.noArticle}">

      <c:if test="${not empty errorCredit}">
          <p class="text-danger">${errorCredit}</p>
      </c:if>
      <c:if test="${userInfos.credit < currentArticle.miseAPrix || userInfos.credit < currentArticle.prixVente}">
        <p class="text-danger">Vous n'avez plus assez de crédits pour enchérir sur cet article</p>
      </c:if>
      </p>
      <c:if test="${userInfos.credit > currentArticle.miseAPrix && userInfos.credit > currentArticle.prixVente}">
      <p>
        <label for="miseAPrix">Ma proposition :</label>
        <input class="form-control" type ="number" name="offreArticle" id="miseAPrix" min="${currentArticle.prixVente == null ? (currentArticle.miseAPrix <= userInfos.credit ? currentArticle.miseAPrix : "0") : (currentArticle.prixVente+1 <= userInfos.credit ? currentArticle.prixVente+1 : "0")}" max="${userInfos.credit}" step="1" max="10000" value="${currentArticle.prixVente == null ? (currentArticle.miseAPrix <= userInfos.credit ? currentArticle.miseAPrix : '') : (currentArticle.prixVente+1 <= userInfos.credit ? currentArticle.prixVente+1 : '')}" required>
        <button type="submit" class="btn btn-outline-success col-2">Enchérir</button>
        points
      </c:if>
    </form>
  </c:if>

</section>
</body>
</html>