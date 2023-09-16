package br.edu.irati.ifpr.tads.external;

import br.edu.irati.ifpr.tads.exception.PersistenceException;
import java.sql.Connection;
import java.sql.SQLException;

public class Conectar {     
    public static Connection get() throws PersistenceException {
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.conectar();
            return con;
        } catch (PersistenceException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    public static void offConection(Connection con) throws PersistenceException {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
}
