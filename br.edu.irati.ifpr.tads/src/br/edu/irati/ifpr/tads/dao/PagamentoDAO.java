package br.edu.irati.ifpr.tads.dao;

import br.edu.irati.ifpr.tads.model.Cliente;
import br.edu.irati.ifpr.tads.model.Pagamento;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.model.PagamentoFormaENUM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO implements InterfaceDAO<Pagamento>{
    private final Connection con;
    
    public PagamentoDAO(Connection con) {
        this.con = con;
    }
    
    public void salvar(Pagamento t) throws PersistenceException {
        try {
            if(t.getId() == 0) {
                PreparedStatement ps = this.con.prepareStatement("INSERT INTO pagamentos(data_hora, forma_pagamento, id_cliente) values (?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDate(1, new java.sql.Date(t.getDataHora().getTime()));
                ps.setString(2, t.getFormaPagamento().toString());
                ps.setInt(3, t.getCliente().getId());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getInt(1));
                }
                rs.close();
                ps.close();
            } else {
                PreparedStatement ps = this.con.prepareStatement("UPDATE pagamentos set data_hora = ?, forma_pagamento = ?, id_cliente = ? WHERE id = ?;");
                ps.setDate(1, new java.sql.Date(t.getDataHora().getTime()));
                ps.setString(2, t.getFormaPagamento().toString());
                ps.setInt(3, t.getCliente().getId());
                ps.setInt(4, t.getId());
                ps.execute();
                ps.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void excluir(Pagamento t) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM pagamentos WHERE id = ?");
            ps.setInt(1, t.getId());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Pagamento buscarPorId(int id) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, data_hora, forma_pagamento, id_cliente FROM pagamentos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Pagamento pagamento = new Pagamento();
            if (rs.next()) {
                id = rs.getInt(1);
                java.util.Date dataHora = new java.util.Date(rs.getDate(2).getTime());
                String formaPagamento = rs.getString(3);
                ClienteDAO clienteDAO = new ClienteDAO(con);
                Cliente cliente = clienteDAO.buscarPorId(rs.getInt(4));
                pagamento.setId(id);
                pagamento.setDataHora(dataHora);
                pagamento.setFormaPagamento(getFormaPagamento(formaPagamento));
                pagamento.setCliente(cliente);
            }
            rs.close();
            ps.close();
                
            return pagamento;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Pagamento> buscarTodos() throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, data_hora, forma_pagamento, id_cliente FROM pagamentos WHERE id = ?");
            ResultSet rs = ps.executeQuery();
            List<Pagamento> listaPagamentos = new ArrayList<>();
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                int id = rs.getInt(1);
                java.util.Date dataHora = new java.util.Date(rs.getDate(2).getTime());
                String formaPagamento = rs.getString(3);
                ClienteDAO clienteDAO = new ClienteDAO(con);
                Cliente cliente = clienteDAO.buscarPorId(rs.getInt(4));
                pagamento.setId(id);
                pagamento.setDataHora(dataHora);
                pagamento.setFormaPagamento(getFormaPagamento(formaPagamento));
                pagamento.setCliente(cliente);
                
                listaPagamentos.add(pagamento);
            }
            rs.close();
            ps.close();
                
            return listaPagamentos;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    private PagamentoFormaENUM getFormaPagamento(String formaPagamento) {
        switch (formaPagamento) {
            case "DINHEIRO":
                return PagamentoFormaENUM.DINHEIRO;
            case "PIX":
                return PagamentoFormaENUM.PIX;
            case "CARTAO":
                return PagamentoFormaENUM.CARTAO;
        }
        
        return PagamentoFormaENUM.DINHEIRO;
    }
}
