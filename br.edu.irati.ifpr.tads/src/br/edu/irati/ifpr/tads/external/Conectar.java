package br.edu.irati.ifpr.tads.external;

import br.edu.irati.ifpr.tads.exception.PersistenceException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conectar {     
    public static Connection get() {
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.conectar();
            return con;
        } catch (PersistenceException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
