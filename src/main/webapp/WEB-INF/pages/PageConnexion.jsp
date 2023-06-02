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
<form action="/Connexion" method="POST">


<label>Identifiant</label>
<input class="" type="text">
<label>Mot de passe</label>
<input class="" type="password">
<label>Se souvenir de moi</label>
<input type="checkbox">
<a href="#">Mot de passe oublié ? Cliquez ici!</a>
    <input class="button" type="submit" value="Connexion">
</form>


<a href="CreationCompte.html">Créer un compte</a>

<footer>
    Il y a rien encore ici
</footer>

</body>
</html>