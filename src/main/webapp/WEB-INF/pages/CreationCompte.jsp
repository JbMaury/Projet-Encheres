<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
  <title>Creation de compte</title>
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

  <form action="<%=request.getContextPath()%>/Inscription"
        method="post" class="mt-5">
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="pseudo" class="col-4 col-md-3 col-lg-2 col-form-label ">Pseudo :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" id="pseudo" name="pseudo" value="${empty pseudoValue ? '' : pseudoValue}"required>
      </div>
      <label for="nom" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Nom :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" id="nom" name="nom" value="${empty nomValue ? '' : nomValue}" required>
      </div>
    </div>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="prenom" class="col-4 col-md-3 col-lg-2 col-form-label">Prénom :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" id="prenom" name="prenom" value="${empty prenomValue ? '' : prenomValue}" required>
      </div>
      <label for="email" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Email :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" type="email" id="email" name="email" value="${empty emailValue ? '' : emailValue}"
               required>
      </div>
    </div>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="telephone" class="col-4 col-md-3 col-lg-2 col-form-label">Téléphone :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" type="tel" pattern="[0-9]{10}" id="telephone" name="telephone" value="${empty telephoneValue ? '' : telephoneValue}" required>
      </div>
      <label for="rue" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Rue :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" id="rue" name="rue" value="${empty rueValue ? '' : rueValue}"required>
      </div>
    </div>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="codePostal" class="col-4 col-md-3 col-lg-2 col-form-label">Code postal :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" id="codePostal" name="codePostal" value="${empty codePostalValue ? '' : codePostalValue}" required>
      </div>
      <label for="ville" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Ville :</label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" id="ville" name="ville"  value="${empty villeValue ? '' : villeValue}"required>
      </div>
    </div>
    <div class="form-group row mt-md-4 justify-content-center">
      <label for="motDePasse" class="col-4 col-md-3 col-lg-2 col-form-label">Mot de passe :</label>
      <div class="col-6 col-md-3">
        <input class="form-control" type="password" id="motDePasse" name="motDePasse" required>
      </div>
      <label for="confirmationMotDePasse" class="col-4 col-md-3 col-lg-2 col-form-label mt-3 mt-md-0">Confirmation : </label>
      <div class="col-6 col-md-3 mt-3 mt-md-0">
        <input class="form-control" type="password" id="confirmationMotDePasse" name="confirmationMotDePasse" required>
       <%--Si confirmation du mot de passe échoue--%>
        <c:if test="${not empty errorMessage}">
          <p class="error">${errorMessage}</p>
        </c:if>
      </div>
    </div>
    <div class="row justify-content-center mt-5">
      <button type="submit" class="btn btn-outline-success col-4 col-md-2 p-3">Créer</button>
      <a href="<%=request.getContextPath()%>/" class="col-4 col-md-2 offset-1 btn btn-outline-danger p-3" role="button">Annuler</a>
    </div>
  </form>
</section>
</body>
</html>