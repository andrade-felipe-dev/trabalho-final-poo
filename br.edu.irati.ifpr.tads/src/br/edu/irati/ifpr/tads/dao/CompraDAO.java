package br.edu.irati.ifpr.tads.dao;

import br.edu.irati.ifpr.tads.entity.Compra;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.entity.Cliente;
import br.edu.irati.ifpr.tads.entity.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO implements InterfaceDAO<Compra> {
    private final Connection con;
    
    public CompraDAO(Connection con) {
        this.con = con;
    }
    
    public void salvar(Compra t) throws PersistenceException {
        try {
            if(t.getId() == 0) {
                PreparedStatement ps = this.con.prepareStatement("INSERT INTO compras (data_hora, estado, id_cliente) VALUES(?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDate(1, new java.sql.Date(t.getDataHora().getTime()));
                ps.setString(2, t.getEstado());
                ps.setInt(3, t.getCliente().getId());
                ps.execute();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getInt(1));
                }
                rs.close();
                ps.close();
            } else {
                PreparedStatement ps = this.con.prepareStatement("UPDATE compras SET data_hora = ?, estado = ? WHERE id = ?;");
                ps.setDate(1, new java.sql.Date(t.getDataHora().getTime()));
                ps.setString(2, t.getEstado());
                ps.setInt(3, t.getId());
                ps.execute();
                ps.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    
    public void excluir(Compra t) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM compras WHERE id = ?");
            ps.setInt(1, t.getId());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    public Compra buscarPorId(int id) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, data_hora, estado, id_cliente FROM cursos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Compra compra = new Compra();
            if (rs.next()) {
                id = rs.getInt(1);
                java.util.Date dataHora = new java.util.Date(rs.getDate(2).getTime());
                String estado = rs.getString(3);
                ClienteDAO clienteDAO = new ClienteDAO(con);
                Cliente cliente = clienteDAO.buscarPorId(rs.getInt(4));
                compra.setId(id);
                compra.setDataHora(dataHora);
                compra.setEstado(estado);
                compra.setCliente(cliente);
            }
            rs.close();
            ps.close();
                
            return compra;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    public List<Compra> buscarTodos() throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, data_hora, estado, id_cliente FROM compras");
            ResultSet rs = ps.executeQuery();
            List<Compra> listaCompras = new ArrayList<>();
            while (rs.next()) {
                Compra compra = new Compra();
                int id = rs.getInt(1);
                java.util.Date dataHora = new java.util.Date(rs.getDate(2).getTime());
                String estado = rs.getString(3);
                ClienteDAO clienteDAO = new ClienteDAO(con);
                Cliente cliente = clienteDAO.buscarPorId(rs.getInt(4));
                compra.setId(id);
                compra.setDataHora(dataHora);
                compra.setEstado(estado);
                compra.setCliente(cliente);
                
                listaCompras.add(compra);
            }
            rs.close();
            ps.close();
                
            return listaCompras;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    public List<Compra> buscarPorIdPagamento(int idPagamento) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, data_hora, estado, id_cliente FROM compras WHERE id_pagamento = ?");
            ps.setInt(1, idPagamento);
            ResultSet rs = ps.executeQuery();
            List<Compra> listaCompra = new ArrayList<>();
            while (rs.next()) {
                Compra compra = new Compra();
                int id = rs.getInt(1);
                java.util.Date dataHora = new java.util.Date(rs.getDate(2).getTime());
                String estado = rs.getString(3);
                ClienteDAO clienteDAO = new ClienteDAO(con);
                Cliente cliente = clienteDAO.buscarPorId(rs.getInt(4));
                compra.setId(id);
                compra.setDataHora(dataHora);
                compra.setEstado(estado);
                compra.setCliente(cliente);
                
                listaCompra.add(compra);
            }
            rs.close();
            ps.close();
                
            return listaCompra;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    public void alterarParaNuloIdPagamento (int idPagamento) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("UPDATE compras SET id_pagamento = NULL WHERE id_pagamento = ?;");
            ps.setInt(1, idPagamento);
            ps.execute();
            ps.close();
        } catch(SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
