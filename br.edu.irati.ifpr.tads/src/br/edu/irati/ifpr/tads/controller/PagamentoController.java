package br.edu.irati.ifpr.tads.controller;

import br.edu.irati.ifpr.tads.dao.PagamentoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Pagamento;
import br.edu.irati.ifpr.tads.model.PagamentoFormaENUM;
import java.util.List;

public class PagamentoController {
    public void salvar(Pagamento pagamento) throws PersistenceException {
        PagamentoDAO pagamentoDAO = new PagamentoDAO(Conectar.get());
        pagamentoDAO.salvar(pagamento);
    }
    
    public void excluir (Pagamento pagamento) throws PersistenceException {
        PagamentoDAO pagamentoDAO = new PagamentoDAO(Conectar.get());
        pagamentoDAO.excluir(pagamento);
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
