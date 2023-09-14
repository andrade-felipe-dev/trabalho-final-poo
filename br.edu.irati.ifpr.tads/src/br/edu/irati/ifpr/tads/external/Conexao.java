package br.edu.irati.ifpr.tads.external;


import br.edu.irati.ifpr.tads.exception.PersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection conectar() throws PersistenceException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/banco";
            
            String usuario = "root";
            
            String senha = "1234";
            
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException cnfe){
            System.out.println("Driver não carregado.");
            throw new PersistenceException("Driver não carregado.");
        } catch (SQLException sqle){
            System.out.println("Conexão não realizada");
            throw new PersistenceException("Driver não carregado.");
        }
    }
}
