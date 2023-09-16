package br.edu.irati.ifpr.tads.controller;

import br.edu.irati.ifpr.tads.dao.CompraDAO;
import br.edu.irati.ifpr.tads.dao.ItemCompraDAO;
import br.edu.irati.ifpr.tads.dao.PagamentoDAO;
import br.edu.irati.ifpr.tads.dao.ProdutoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Compra;
import br.edu.irati.ifpr.tads.model.CompraEstadoENUM;
import br.edu.irati.ifpr.tads.model.ItemCompra;
import br.edu.irati.ifpr.tads.model.Pagamento;
import br.edu.irati.ifpr.tads.model.PagamentoFormaENUM;
import br.edu.irati.ifpr.tads.model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraController {
    public void salvar(Compra compra) throws PersistenceException {
        if (validarCampos(compra)) {
            Connection con = Conectar.get();
            CompraDAO compraDAO = new CompraDAO(con);
            compraDAO.salvar(compra);
            
            ItemCompraDAO itemCompraDAO = new ItemCompraDAO(con);
            itemCompraDAO.salvar(compra, compra.getListaItemCompra().get(0));
            
            if (compra.getEstado().equals(CompraEstadoENUM.PAGO)) {
                List<Compra> listaCompra = new ArrayList<>();
                listaCompra.add(compra);
                
                Pagamento pagamento = new Pagamento();
                pagamento.setValor(compra.getListaItemCompra().get(0).getPreco());
                pagamento.setDataHora(new java.util.Date(2023, 8, 2));
                pagamento.setCliente(compra.getCliente());
                pagamento.setListaCompras(listaCompra);
                pagamento.setFormaPagamento(PagamentoFormaENUM.PIX);
                
                PagamentoController pagamentoController = new PagamentoController();
                pagamentoController.salvar(pagamento);
            }
        }
    }
    
    public void excluir (Compra compra) throws PersistenceException {
        CompraDAO compraDAO = new CompraDAO(Conectar.get());
        compraDAO.excluir(compra);
    }
    
    public List<Compra> buscarTodos() throws PersistenceException {
        CompraDAO compraDAO = new CompraDAO(Conectar.get());
        return compraDAO.buscarTodos();
    }
    
    public Compra buscarPorId(int id) throws PersistenceException {
        CompraDAO compraDAO = new CompraDAO(Conectar.get());
        return compraDAO.buscarPorId(id);
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
}
