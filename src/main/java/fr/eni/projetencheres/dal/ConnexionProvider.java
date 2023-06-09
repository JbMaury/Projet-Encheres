package fr.eni.projetencheres.dal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnexionProvider {

    private static DataSource dataSource;

    static {
        try {
            Context context = new InitialContext();
            ConnexionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/EncheresCNXPool");
        } catch (NamingException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {

        return ConnexionProvider.dataSource.getConnection();
    }
}