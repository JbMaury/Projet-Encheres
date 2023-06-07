package fr.eni.projetencheres.dal.dao;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.bo.Utilisateur;
import fr.eni.projetencheres.dal.DALException;

import java.util.HashMap;

public interface UtilisateurDAO {
    //methode pour cr�ation d'utlisateur
    void newUtilisateur(Utilisateur utilisateur) throws DALException;

    // methode de selection par pseudo
    Utilisateur selectByPseudo(String pseudo) throws DALException;

    // methode de selection par pseudo
    Utilisateur selectById(int idUtilisateur) throws DALException;

    // methode de modification du profil
    void updateUtilisateur(Utilisateur utilisateur) throws DALException;

    // methode de connection
    Utilisateur selectConnexion(String identifiant, String password) throws DALException, Exception;

    // methode de verification de pseudo unique
    boolean selectUniquePseudo(String pseudo) throws DALException;

    // methode de verification d'email unique
    boolean selectUniqueMail(String mail) throws DALException;

    // methode de anonymiser  d'utilisateur
    void deleteUtilisateur(int idUtilisateur) throws DALException;
    public HashMap<Integer, String> selectUtilisateursWithCurrentAuction() throws DALException;

    public void updateCredit(int noUtilisateur, int newCredit) throws DALException;
}