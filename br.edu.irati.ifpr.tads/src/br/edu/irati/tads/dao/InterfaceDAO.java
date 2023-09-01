package br.edu.irati.tads.dao;

import br.edu.irati.tads.exception.PersistenceException;
import java.util.List;

public interface InterfaceDAO <T> {
    public void salvar(T t) throws PersistenceException;
    
    public void excluir(T t) throws PersistenceException;
    
    public T buscarPorId(int id) throws PersistenceException;
    
    public List<T> buscarTodos() throws PersistenceException;
}
