package br.edu.irati.ifpr.tads.controller;

import br.edu.irati.ifpr.tads.dao.ClienteDAO;
import br.edu.irati.ifpr.tads.dao.CursoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Cliente;
import br.edu.irati.ifpr.tads.model.Curso;
import java.util.List;
import java.sql.*;

public class ClienteController {
    public void salvar(Cliente cliente) throws PersistenceException {
        Connection con = Conectar.get();
        ClienteDAO clienteDAO = new ClienteDAO(con);
        if (cliente.getCurso().getId() == 0) {
            System.out.println("Não possui curso cadastrado neste cliente");
            return;
        } 
        CursoDAO cursoDAO = new CursoDAO(con);
        Curso curso = cursoDAO.buscarPorId(cliente.getCurso().getId());
        
        cliente.setCurso(curso);
        clienteDAO.salvar(cliente);
    }
    
    public List<Cliente> listarTodos() throws PersistenceException {
        ClienteDAO clienteDAO = new ClienteDAO(Conectar.get());
        List<Cliente> listaClientes = clienteDAO.buscarTodos();
        return listaClientes;
    }
    
    public Cliente buscarPorId(int id) throws PersistenceException {
        ClienteDAO clienteDAO = new ClienteDAO(Conectar.get());
        Cliente cliente = clienteDAO.buscarPorId(id);
        return cliente;
    }
    
    public void excluir(Cliente cliente) throws PersistenceException {
        ClienteDAO clienteDAO = new ClienteDAO(Conectar.get());
        clienteDAO.excluir(cliente);
    }
}
