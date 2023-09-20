package br.edu.irati.ifpr.tads.controller;

import br.edu.irati.ifpr.tads.dao.CompraDAO;
import br.edu.irati.ifpr.tads.dao.ItemCompraDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Compra;
import br.edu.irati.ifpr.tads.model.ItemCompra;
import java.sql.*;
import java.util.List;

public class CompraController {    
    public void salvar(Compra compra) throws PersistenceException {
        Connection con = Conectar.get();

        try {
            if (this.validarCampos(compra)) {
                con.setAutoCommit(false);
                CompraDAO compraDAO = new CompraDAO(con);
                compraDAO.salvar(compra);

                for (ItemCompra itemCompra : compra.getListaItemCompra()) {
                    ItemCompraDAO itemCompraDAO = new ItemCompraDAO(con);
                    itemCompraDAO.salvar(compra, itemCompra);
                }

                con.commit();
                Conectar.offConection(con);
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                throw new PersistenceException(ex.getMessage());
            } catch (SQLException ex1) {
                throw new PersistenceException(ex1.getMessage());
            }
        }
        
        
    }
    
    public void excluir (Compra compra) throws PersistenceException {
        Connection con = Conectar.get();
        ItemCompraDAO itemCompraDAO = new ItemCompraDAO(con);
        List<ItemCompra> listaItemCompra = itemCompraDAO.buscarPorCompra(compra);
        System.out.println(listaItemCompra.size());
        for (ItemCompra itemCompra : listaItemCompra) {
            itemCompraDAO.excluir(itemCompra);
        }
        
        CompraDAO compraDAO = new CompraDAO(con);
        compraDAO.excluir(compra);
    }
    
    public List<Compra> buscarTodos() throws PersistenceException {
        Connection con = Conectar.get();
        CompraDAO compraDAO = new CompraDAO(con);
        
        List<Compra> listaCompra = compraDAO.buscarTodos();
        Conectar.offConection(con);
        return listaCompra;
    }
    
    public Compra buscarPorId(int id) throws PersistenceException {
        Connection con = Conectar.get();
        CompraDAO compraDAO = new CompraDAO(con);
        Compra compra = compraDAO.buscarPorId(id);
        Conectar.offConection(con);
        return compra;
    }
    
    private boolean validarCampos(Compra compra) {
        if (compra.getListaItemCompra().isEmpty()) {
            System.out.println("Você não possui itens cadastros na compra");
            return false;
        }
        
        if (compra.getCliente().getId() == 0) {
            System.out.println("Não possui cliente cadastrado");
            return false;
        }
        
        return true;
    }
    
    public void desvincularPagamento(Compra compra) throws PersistenceException {
        System.out.println("Desvincular Pagamento");
        Connection con = Conectar.get();
        CompraDAO compraDAO = new CompraDAO(con);
        compraDAO.desvincularPagamento(compra);
        Conectar.offConection(con);
    } 
}
