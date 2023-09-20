package br.edu.irati.ifpr.tads.controller;

import br.edu.irati.ifpr.tads.dao.ProdutoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Produto;
import java.util.List;
import java.sql.*;

public class ProdutoController {
    public List<Produto> listarTodos() throws PersistenceException {
        Connection con = Conectar.get();
        ProdutoDAO produtoDAO = new ProdutoDAO(con);
        List<Produto> listaProduto = produtoDAO.buscarTodos();
        Conectar.offConection(con);
        return listaProduto;
    }
    
    public Produto buscarPorNome(String nome) throws PersistenceException {
       Connection con = Conectar.get();
       ProdutoDAO produtoDAO = new ProdutoDAO(con);
       Produto produto = produtoDAO.buscarPorNome(nome);
       Conectar.offConection(con);
       return produto;
    }
}
