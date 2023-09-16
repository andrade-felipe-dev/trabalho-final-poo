package br.edu.irati.ifpr.tads.dao;

import br.edu.irati.ifpr.tads.model.Compra;
import br.edu.irati.ifpr.tads.model.ItemCompra;
import br.edu.irati.ifpr.tads.model.Produto;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCompraDAO {
     private final Connection con;

    public ItemCompraDAO(Connection con) {
        this.con = con;
    }
   
    public void salvar(Compra compra, ItemCompra itemCompra) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("INSERT INTO item_compras (preco, id_compra, id_produto) VALUES(?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, itemCompra.getPreco());
            ps.setInt(2, compra.getId());
            ps.setInt(3, itemCompra.getProduto().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                itemCompra.setId(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
    
    public void excluir(ItemCompra itemCompra) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM item_compras WHERE id = ?");
            ps.setInt(1, itemCompra.getId());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    public ItemCompra buscarPorId(int id) throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, id_produto FROM pagamentos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ItemCompra itemCompra = new ItemCompra();
            if (rs.next()) {
                id = rs.getInt(1);
                ProdutoDAO produtoDAO = new ProdutoDAO(con);
                Produto produto = produtoDAO.buscarPorId(rs.getInt(2));
                
                itemCompra.setId(id);
                itemCompra.setProduto(produto);
                
            }
            rs.close();
            ps.close();
                
            return itemCompra;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    public List<ItemCompra> buscarTodos() throws PersistenceException {
        try {
            PreparedStatement ps = this.con.prepareStatement("SELECT id, id_produto FROM item_compras");
            ResultSet rs = ps.executeQuery();
            List<ItemCompra> listaItemCompras = new ArrayList<>();
            while (rs.next()) {
                ItemCompra itemCompra = new ItemCompra();

                int id = rs.getInt(1);
                ProdutoDAO produtoDAO = new ProdutoDAO(con);
                Produto produto = produtoDAO.buscarPorId(rs.getInt(2));
                
                itemCompra.setId(id);
                itemCompra.setProduto(produto);
                listaItemCompras.add(itemCompra);
            }
            rs.close();
            ps.close();
                
            return listaItemCompras;
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
}
