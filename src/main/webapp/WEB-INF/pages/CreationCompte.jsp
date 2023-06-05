<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
  <c:if test="${isConnected}">
    <title>Modification du compte</title>
  </c:if>
  <c:if test="${!isConnected}">
    <title>Creation de compte</title>
  </c:if>

  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
        crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a href="<%=request.getContextPath()%>/accueil" class="navbar-brand">ENI-Encheres</a>
</nav>
<section class="container">
  <h1 class="mt-5 text-center">Mon profil</h1>
  <c:if test="${not empty message}">
    <h6 class="mt-5 text-center">(${message})</h6>
  </c:if>

  <form action="<%=request.getContextPath()%>${isConnected ? '/Modifier' : '/Inscription'}"
        method="post" class="mt-5">
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="pseudo" class="col-4 col-md-3 col-lg-2 col-form-label ">Pseudo :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" id="pseudo" name="pseudo" value="${not empty pseudoValue ? pseudoValue : not empty userInfos.pseudo ? userInfos.pseudo : ''}" required>
      </div>
      <label for="nom" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Nom :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" id="nom" name="nom" value="${not empty nomValue ? nomValue : not empty userInfos.nom ? userInfos.nom : ''}" required>
      </div>
    </div>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="prenom" class="col-4 col-md-3 col-lg-2 col-form-label">Prénom :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" id="prenom" name="prenom" value="${not empty prenomValue ? prenomValue : not empty userInfos.prenom ? userInfos.prenom : ''}" required>
      </div>
      <label for="email" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Email :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" type="email" id="email" name="email" value="${not empty emailValue ? emailValue : not empty userInfos.email ? userInfos.email : ''}"
               required>
      </div>
    </div>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="telephone" class="col-4 col-md-3 col-lg-2 col-form-label">Téléphone :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" type="tel" pattern="[0-9]{10}" id="telephone" name="telephone" value="${not empty telephoneValue ? telephoneValue : not empty userInfos.numTel ? userInfos.numTel : ''}" required>
      </div>
      <label for="rue" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Rue :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" id="rue" name="rue" value="${not empty rueValue ? rueValue : not empty userInfos.rue ? userInfos.rue : ''}"required>
      </div>
    </div>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="codePostal" class="col-4 col-md-3 col-lg-2 col-form-label">Code postal :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" id="codePostal" name="codePostal" value="${not empty codePostalValue ? codePostalValue : not empty userInfos.codePostal ? userInfos.codePostal : ''}" required>
      </div>
      <label for="ville" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Ville :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" id="ville" name="ville"  value="${not empty villeValue ? villeValue : not empty userInfos.ville ? userInfos.ville : ''}"required>
      </div>
    </div>
    <c:if test="${isConnected}">
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="currentMdp" class="col-4 col-md-3 col-lg-2 col-form-label">Mot de passe actuel :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" id="currentMdp" type="password" name="currentMdp">
      </div>
      <div class="col-4 col-md-3 col-lg-2 mt-3 mt-md-0"></div>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
      </div>
    </div>
    </c:if>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="motDePasse" class="col-4 col-md-3 col-lg-2 col-form-label">
        <c:choose>
          <c:when test="${isConnected}">
            Nouveau mot de passe
          </c:when>
          <c:otherwise>
          Mot de passe :
          </c:otherwise>
        </c:choose></label>
      <div class="col-6 col-md-3">
        <input class="form-control" type="password" id="motDePasse" name="motDePasse" <c:if test="${empty isConnected}">required</c:if>>
      </div>
      <label for="confirmationMotDePasse" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Confirmation : </label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" type="password" id="confirmationMotDePasse" name="confirmationMotDePasse" <c:if test="${empty isConnected}">required</c:if>>
       <%--Si confirmation du mot de passe échoue--%>
        <c:if test="${not empty errorMessage}">
          <p class="error">${errorMessage}</p>
        </c:if>
        <c:if test="${not empty error}">
          <p class="error">${error}</p>
        </c:if>
      </div>
    </div>
    <div class="row justify-content-center mt-5">

      <button type="submit" class="btn btn-outline-success col-4 col-md-2 p-3">
        <c:choose>
        <c:when test="${isConnected}">
          Modifier
        </c:when>
        <c:otherwise>
          Créer
        </c:otherwise>
      </c:choose></button>
      <c:choose>
        <c:when test="${isConnected}">
          <a href="<%=request.getContextPath()%>/Supprimer" class="col-4 col-md-2 offset-1 btn btn-outline-danger p-3" role="button">Supprimer compte</a>
        </c:when>
        <c:otherwise>
          <a href="<%=request.getContextPath()%>/" class="col-4 col-md-2 offset-1 btn btn-outline-danger p-3" role="button">Annuler</a>
        </c:otherwise>
      </c:choose>
    </div>
  </form>
</section>
</body>
</html>