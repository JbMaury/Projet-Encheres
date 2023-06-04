<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Indentification</title>
    <link rel="stylesheet" href="<%request.getContextPath();%>/css/StylePageConnexion.css">

</head>


<body>
<header>
<h1>ENI - Enchères</h1>

</header>
<form action="${pageContext.request.contextPath}/Connexion" method="POST">
<label for="identifiant">Identifiant(Email ou Pseudo)</label>
<input class="" type="text" id="identifiant" name="identifiant">
<label for="mdp">Mot de passe</label>
<input class="" id="mdp" type="password" name="password">
    <c:if test="${not empty erreur}">
    <p>${erreur}</p>
    </c:if>

<label>Se souvenir de moi</label>
<input type="checkbox">
<a href="#">Mot de passe oublié ? Cliquez ici!</a>
    <input class="button" type="submit" value="Connexion">
</form>


<a href="<%=request.getContextPath()%>/Inscription">Créer un compte</a>

<footer>
    Il y a rien encore ici
</footer>

</body>
</html>