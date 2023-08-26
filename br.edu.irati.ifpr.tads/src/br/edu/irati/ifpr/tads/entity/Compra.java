package br.edu.irati.ifpr.tads.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private Date dataHora;
    private String estado;
    private List<Cliente> listaClientes;
    private ItemCompra itemCompra;
    
    public Compra() {
        dataHora = new Date();
        estado = "";
        listaClientes = new ArrayList<>();
        itemCompra = new ItemCompra();
    }
    
    public Compra(Date dataHora, String estado, List<Cliente> listaClientes, ItemCompra itemCompra) {
        this.dataHora = dataHora;
        this.estado = estado;
        this.listaClientes = listaClientes;
        this.itemCompra = itemCompra;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
    public ItemCompra getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(ItemCompra itemCompra) {
        this.itemCompra = itemCompra;
    }

}
