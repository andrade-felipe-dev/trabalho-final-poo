package br.edu.irati.ifpr.tads.dao;

import br.edu.irati.ifpr.tads.entity.Produto;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements InterfaceDAO<Produto> {
    private final Connection con;

    public ProdutoDAO(Connection con) {
        this.con = con;
    }
    
    @Override
    public void salvar(Produto t) throws PersistenceException {
        try {
            if(t.getId() == 0) {
                PreparedStatement ps = this.con.prepareStatement("INSERT INTO produtos(nome, descricao, preco);", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, t.getNome());
                ps.setString(2, t.getDescricao());
                ps.setDouble(3, t.getPreco());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getInt(1));
                }
                rs.close();
                ps.close();
            } else {
                PreparedStatement ps = this.con.prepareStatement("UPDATE pagamentos set nome = ?, descricao = ?, preco = ? WHERE id = ?;");
                ps.setString(1, t.getNome());
                ps.setString(2, t.getDescricao());
                ps.setDouble(3, t.getPreco());
                ps.setInt(4, t.getId());
                ps.execute();
                ps.close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void excluir(Produto t) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM produtos WHERE id = ?");
            ps.setInt(1, t.getId());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Produto buscarPorId(int id) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, nome, descricao, preco FROM produtos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Produto produto = new Produto();
            if (rs.next()) {
                id = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                Double preco = rs.getDouble(4);
                produto.setId(id);
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
            }
            rs.close();
            ps.close();
                
            return produto;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public List<Produto> buscarTodos() throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, nome, descricao, preco FROM produtos");
 
            ResultSet rs = ps.executeQuery();
            List<Produto> listaProdutos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto();
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                Double preco = rs.getDouble(4);
                produto.setId(id);
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                
                listaProdutos.add(produto);
            }
            rs.close();
            ps.close();
                
            return listaProdutos;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
}
