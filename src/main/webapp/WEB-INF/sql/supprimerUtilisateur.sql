-- Script pour la suppression d'un utilisateur
USE PROJET_ENCHERES
GO

DROP USER utilisateur_encheres
GO
-- On peut egalement supprimer son compte au niveau du serveur
USE master
GO

DROP LOGIN utilisateur_encheres

-- Si la session de l'utilisateur est toujours active
-- On selectionne sa session_id (int)
SELECT * FROM sys.dm_exec_sessions WHERE login_name = 'utilisateur_encheres';
-- On met fin a la session_id
KILL 75;