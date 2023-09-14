package br.edu.irati.ifpr.tads.app;

import br.edu.irati.ifpr.tads.dao.ClienteDAO;
import br.edu.irati.ifpr.tads.dao.CursoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conexao;
import java.sql.*;


public class App {
    public static void main(String[] args) throws PersistenceException {
        Conexao conexao = new Conexao();
        Connection connection = conexao.conectar();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        System.err.println(clienteDAO.buscarPorId(1));
    }
}
