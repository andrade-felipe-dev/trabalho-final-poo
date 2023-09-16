/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.irati.tads.view;

import br.edu.irati.ifpr.tads.controller.ClienteController;
import br.edu.irati.ifpr.tads.controller.CompraController;
import br.edu.irati.ifpr.tads.dao.ProdutoDAO;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.external.Conectar;
import br.edu.irati.ifpr.tads.model.Cliente;
import br.edu.irati.ifpr.tads.model.Compra;
import br.edu.irati.ifpr.tads.model.CompraEstadoENUM;
import br.edu.irati.ifpr.tads.model.ItemCompra;
import br.edu.irati.ifpr.tads.model.Produto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author felipe
 */
public class Teste {
    public static void main(String[] args) {
        try {
            ClienteController clienteController = new ClienteController();
            Cliente cliente = clienteController.buscarPorId(3);
            CompraController compraController = new CompraController();
            ProdutoDAO produtoDAO = new ProdutoDAO(Conectar.get());
            Produto produto = produtoDAO.buscarPorId(1);
            List<ItemCompra> listaItemCompra = new ArrayList<>();
            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setPreco(produto.getPreco());
            itemCompra.setProduto(produto);
            listaItemCompra.add(itemCompra);
            
            Compra compra = new Compra(0, new Date(2023, 8, 2), CompraEstadoENUM.PAGO, cliente, listaItemCompra);
            compraController.salvar(compra);
        } catch (PersistenceException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
