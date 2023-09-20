package br.edu.irati.ifpr.tads.controller;

import br.edu.irati.ifpr.tads.dao.CompraDAO;
import br.edu.irati.ifpr.tads.dao.PagamentoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Compra;
import br.edu.irati.ifpr.tads.model.Pagamento;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PagamentoController {
        public void salvar(Pagamento pagamento) throws PersistenceException {
        Connection con = Conectar.get();
        PagamentoDAO pagamentoDAO = new PagamentoDAO(con);
        pagamentoDAO.salvar(pagamento);
        
        Conectar.offConection(con);
    }
    
    public void salvar(Pagamento pagamento, List<Compra> compras) throws PersistenceException {
            try {
                Connection con = Conectar.get();
                con.setAutoCommit(false);
                PagamentoDAO pagamentoDAO = new PagamentoDAO(con);
                pagamentoDAO.salvar(pagamento);
                
                CompraDAO compraDAO = new CompraDAO(con);
                for(Compra compra : compras) {
                    compraDAO.adicionarPagamentoACompra(pagamento, compra);
                }
                con.commit();
                Conectar.offConection(con);
            } catch (SQLException ex) {
                throw new PersistenceException(ex.getMessage());
            }
    }
        
    public void excluir (Pagamento pagamento) throws PersistenceException {
        try {
            Connection con = Conectar.get();
            con.setAutoCommit(false);
            CompraDAO compraDAO = new CompraDAO(con);
            for (Compra compra: pagamento.getListaCompras()) {
                compraDAO.desvincularPagamento(compra);
            }
            PagamentoDAO pagamentoDAO = new PagamentoDAO(Conectar.get());
            pagamentoDAO.excluir(pagamento);
            
            Conectar.offConection(con);
        } catch (SQLException ex) {
            throw new PersistenceException("Problema na operação de salvar pagamento!");
        }
        
    }
    
    public List<Pagamento> listarTodos() throws PersistenceException {
        PagamentoDAO pagamentoDAO = new PagamentoDAO(Conectar.get());
        return pagamentoDAO.buscarTodos();
    }
    
    public Pagamento buscarId(int id) throws PersistenceException {
        PagamentoDAO pagamentoDAO = new PagamentoDAO(Conectar.get());
        return pagamentoDAO.buscarPorId(id);
    }
    
   
}
