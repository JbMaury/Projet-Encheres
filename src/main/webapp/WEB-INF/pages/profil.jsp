<%--
  Created by IntelliJ IDEA.
  User: jeanb
  Date: 04/06/2023
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Mon profil</title>
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
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Pseudo :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.pseudo}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Prenom :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.prenom}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Nom :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.nom}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Email :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.email}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Telephone :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.numTel}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Rue :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.rue}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Code Postal :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.codePostal}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Ville :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.ville}</p>
    </div>
  </div>
  <div class="form-group row mt-md-4 justify-content-center">
    <p class="col-4 col-md-3 col-lg-2 col-form-label ">Crédit :</p>
    <div class="col-6 col-md-3">
      <p>${userInfos.credit}</p>
    </div>
  </div>
</section>
</body>
</html>