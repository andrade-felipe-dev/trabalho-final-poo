package br.edu.irati.ifpr.tads.controller;

import br.edu.irati.ifpr.tads.dao.CursoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Curso;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CursoController {
    public List<Curso> listarTodos() throws PersistenceException {
        Connection con = Conectar.get();
        CursoDAO cursoDAO = new CursoDAO(con);
        List<Curso> listaCurso = cursoDAO.buscarTodos();
        Conectar.offConection(con);
        return listaCurso;
    }
    
    public List<String> formatarCursoNome(List<Curso> listaCurso) {
        List<String> listaNome = new ArrayList<>();
        for (Curso curso : listaCurso) {
            String nome = curso.getNome();
            listaNome.add(nome);
        }
        return listaNome;
    }
    
    
    public Curso buscarCursoPorNome(String nome) throws PersistenceException {
        Connection con = Conectar.get();
        CursoDAO cursoDAO = new CursoDAO(con);
        Curso curso = cursoDAO.buscarPorNome(nome);
        Conectar.offConection(con);
        return curso;
    }
}
