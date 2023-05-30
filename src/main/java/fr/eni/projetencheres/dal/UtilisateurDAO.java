package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.Utilisateur;

public interface UtilisateurDAO {
    // Methode pour créer un utilisateur
    void createUtilisateur(Utilisateur utilisateur) throws DALException;

    // Methode de sélection par pseudo
    Utilisateur selectByPseudo(String pseudo) throws DALException;

    // Methode de sélection par identifiant
    Utilisateur selectById(int id) throws DALException;

    // Methode de modification du profile
    void updateUtilisateur(Utilisateur utilisateur) throws DALException;

    // Methode de connexion
    Utilisateur selectConnexion(String identifiant, String password) throws DALException, Exception;

    // Méthode de vérification de pseudo unique
    boolean checkUniquePseudo(String pseudo) throws DALException;

    // Méthode de vérification d'email unique
    boolean checkUniqueMail(String mail) throws DALException;

    // Méthode de suppression d'un utilisateur
    void deleteUtilisateur(Utilisateur utilisateur) throws DALException;
}
