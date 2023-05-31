-- Creation de la base de donnee
CREATE DATABASE PROJET_ENCHERES;
GO

-- Creation du compte de connexion "utilisateur_encheres" au niveau du serveur
USE master
GO

CREATE LOGIN utilisateur_encheres WITH PASSWORD = 'Pa$$w0rd';
GO

-- Creation de l'utilisateur sur la base de donnee PROJET_ENCHERES
USE PROJET_ENCHERES;
GO
CREATE USER utilisateur_encheres FOR LOGIN utilisateur_encheres;
GO

-- Attribution des droits en lecture et ecriture a l'utilisateur
USE PROJET_ENCHERES;
GO
ALTER ROLE [db_datareader] ADD MEMBER utilisateur_encheres;
GO
ALTER ROLE [db_datawriter] ADD MEMBER utilisateur_encheres;
GO