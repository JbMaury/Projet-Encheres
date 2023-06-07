<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirmation suppression du compte</title>
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
<p>Etes-vous vraiment sûr de vouloir supprimer votre compte ?</p>
<a href="<%=request.getContextPath()%>/Supprimer?origine=confirm">Confirmer</a>
<a href="<%=request.getContextPath()%>/Profil">Annuler</a>
</body>
</html>