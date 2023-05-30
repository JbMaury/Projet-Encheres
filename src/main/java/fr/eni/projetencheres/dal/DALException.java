package fr.eni.projetencheres.dal;

public class DALException extends Exception {

    // Constructors DALException
    public DALException() {
        super();
    }

    public DALException(String message) {
        super(message);
    }

    public DALException(String message, Throwable exception) {
        super(message, exception);
    }

    // Methods
    @Override
    public String getMessage() {
        return "Couches DAL - " + super.getMessage();
    }
}