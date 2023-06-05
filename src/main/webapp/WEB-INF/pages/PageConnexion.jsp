<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Indentification</title>
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
        <h1 class="mt-5 text-center">Connexion</h1>
        <form action="${pageContext.request.contextPath}/Connexion" method="POST" class="form-group mt-md-4 justify-content-center">
            <div class="form-group row mt-md-4 justify-content-center">
                <label for="identifiant" class="col-4 col-md-3 col-lg-2 col-form-label ">Identifiant(Email ou Pseudo)</label>
                <div class="col-6 col-md-3">
                    <input class="" type="text" id="identifiant" name="identifiant">
                </div>
            </div>
            <div class="form-group row mt-md-4 justify-content-center">
                <label for="mdp" class="col-4 col-md-3 col-lg-2 col-form-label ">Mot de passe</label>
                <div class="col-6 col-md-3">
                    <input class="" id="mdp" type="password" name="password">
                    <c:if test="${not empty erreur}">
                        <p>${erreur}</p>
                    </c:if>
                </div>
            </div>
            <div class="form-group row mt-md-4 justify-content-center">
                <a href="#">Mot de passe oublié ? Cliquez ici!</a>

                <div class="col-6 col-md-3">
                    <a href="<%=request.getContextPath()%>/Inscription">Créer un compte</a>
                </div>
            </div>
            <div class="form-group row mt-md-4 justify-content-center">
                <div class="col-6 col-md-3">
                    <label for="rememberMe" class="col-form-label ">Se souvenir de moi</label>
                    <input id="rememberMe" type="checkbox">
                    <input class="button" type="submit" value="Connexion">
                </div>
            </div>
        </form>



    </section>

</body>
</html>