package br.edu.irati.ifpr.tads.dao;

import br.edu.irati.ifpr.tads.model.Curso;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class CursoDAO implements InterfaceDAO<Curso>{
    private final Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void salvar(Curso t) throws PersistenceException {
        try {
            if (t.getId() == 0) {
                PreparedStatement ps = this.connection.prepareStatement("INSERT INTO (nome, turno) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, t.getNome());
                ps.setString(2, t.getTurno());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getInt(1));
                }
                rs.close();
                ps.close();
            } else {
                PreparedStatement ps = this.connection.prepareStatement("UPDATE cursos SET nome = ?, turno = ? WHERE id = ?");
                ps.setString(1, t.getNome());
                ps.setString(2, t.getTurno());
                ps.setInt(3, t.getId());
                ps.execute();
                ps.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void excluir(Curso t) throws PersistenceException {
        try {
            PreparedStatement ps = this.connection.prepareStatement("DELETE FROM cursos WHERE id = ?");
            ps.setInt(1, t.getId());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }   
    }

    @Override
    public Curso buscarPorId(int id) throws PersistenceException {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT id, nome, turno FROM cursos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Curso curso = new Curso();
            if (rs.next()) {
                id = rs.getInt(1);
                String nome = rs.getString(2);
                String turno = rs.getString(3);
                curso.setId(id);
                curso.setNome(nome);
                curso.setTurno(turno);
            }
            rs.close();
            ps.close();
                
            return curso;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Curso> buscarTodos() throws PersistenceException {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT id, nome, turno FROM cursos;");
            ResultSet rs = ps.executeQuery();
            
            List<Curso> cursos = new ArrayList<>();
            
            while(rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String turno = rs.getString(3);
                Curso curso = new Curso(id, nome, turno);
                
                cursos.add(curso);
            }
            rs.close();
            ps.close();
            
            return cursos;
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
