package br.edu.irati.ifpr.tads.app;

import br.edu.irati.ifpr.tads.dao.ClienteDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conexao;
import br.edu.irati.ifpr.tads.model.Cliente;
import java.sql.*;


public class App {
    public static void main(String[] args) throws PersistenceException {
        Conexao conexao = new Conexao();
        Connection connection = conexao.conectar();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        Cliente cliente = clienteDAO.buscarPorId(1);
        
    }
}
