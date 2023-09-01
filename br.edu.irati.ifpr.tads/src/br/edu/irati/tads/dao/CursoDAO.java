package br.edu.irati.tads.dao;

import br.edu.irati.ifpr.tads.entity.Curso;
import br.edu.irati.tads.exception.PersistenceException;
import java.util.List;
import java.sql.*;

public class CursoDAO implements InterfaceDAO<Curso>{
    private Connection connection;

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
            PreparedStatement ps = this.connection.prepareStatement("SELECT nome, turno WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Curso> buscarTodos() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
