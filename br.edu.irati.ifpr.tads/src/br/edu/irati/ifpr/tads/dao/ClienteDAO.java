package br.edu.irati.ifpr.tads.dao;

import br.edu.irati.ifpr.tads.model.Cliente;
import br.edu.irati.ifpr.tads.model.Curso;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO implements InterfaceDAO<Cliente> {
    
    private Connection con;
    
    public ClienteDAO(Connection con) {
        this.con = con;
    }
    
    @Override
    public void salvar(Cliente t) throws PersistenceException {
        try {
            if(t.getId() == 0) {
                PreparedStatement ps = this.con.prepareStatement("INSERT INTO clientes (nome, telefone, email, endereco, saldo_atual, limite_fiado, id_curso) VALUES(?,?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, t.getNome());
                ps.setString(2, t.getTelefone());
                ps.setString(3, t.getEmail());
                ps.setString(4, t.getEndereco());
                ps.setFloat(5, t.getSaldoAtual());
                ps.setFloat(6, t.getLimiteFiado());
                ps.setInt(7, t.getCurso().getId());
                ps.execute();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getInt(1));
                }
                rs.close();
                ps.close();
            } else {
                PreparedStatement ps = this.con.prepareStatement("UPDATE clientes SET nome = ?, telefone = ?, email = ?, endereco = ?, saldo_atual = ?, limite_fiado = ?, id_curso = ? WHERE id = ?;");
                ps.setString(1, t.getNome());
                ps.setString(2, t.getTelefone());
                ps.setString(3, t.getEmail());
                ps.setString(4, t.getEndereco());
                ps.setFloat(5, t.getSaldoAtual());
                ps.setFloat(6, t.getLimiteFiado());
                ps.setInt(7, t.getCurso().getId());
                ps.setInt(8, t.getId());

                ps.execute();
                System.out.println("Cheguei");

                ps.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void excluir(Cliente t) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM clientes WHERE ID = ?;");
            ps.setInt(1, t.getId());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Cliente buscarPorId(int id) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, nome, telefone, email, endereco, saldo_atual, limite_fiado, id_curso FROM clientes WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cliente cliente = null;
            if (rs.next()) {
                id = rs.getInt(1);
                String nome = rs.getString(2);
                String telefone = rs.getString(3);
                String email = rs.getString(4);
                String endereco = rs.getString(5);
                float saldoAtual = rs.getFloat(6);
                float limiteFiado = rs.getFloat(7);
                CursoDAO cursoDAO = new CursoDAO(this.con);
                Curso curso = cursoDAO.buscarPorId(rs.getInt(7));
                cliente = new Cliente(id, nome, telefone, email, endereco, saldoAtual, limiteFiado, new ArrayList<>(), curso);
            }
            rs.close();
            ps.close();
                
            return cliente;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Cliente> buscarTodos() throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, nome, telefone, email, endereco, saldo_atual, limite_fiado, id_curso FROM clientes ORDER BY nome ASC;");
            ResultSet rs = ps.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String telefone = rs.getString(3);
                String email = rs.getString(4);
                String endereco = rs.getString(5);
                float saldoAtual = rs.getFloat(6);
                float limiteFiado = rs.getFloat(7);
                CursoDAO cursoDAO = new CursoDAO(this.con);
                Curso curso = cursoDAO.buscarPorId(rs.getInt(8));
                
                Cliente cliente = new Cliente(id, nome, telefone, email, endereco, saldoAtual, limiteFiado,new ArrayList<>(), curso);
                clientes.add(cliente);
            }
            rs.close();
            ps.close();
                
            return clientes;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
}
