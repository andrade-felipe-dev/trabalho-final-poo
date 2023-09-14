package br.edu.irati.ifpr.tads.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private int id;    
    private Date dataHora;
    private String estado;
    private Cliente cliente;
    private List<ItemCompra> listaItemCompra;
    
    public Compra() {
        id = 0;
        dataHora = new Date();
        estado = "";
        cliente = new Cliente();
        listaItemCompra = new ArrayList<>();
    }
    
    public Compra(int id, Date dataHora, String estado, Cliente cliente, List<ItemCompra> listaItemCompra) {
        this.id = id;
        this.dataHora = dataHora;
        this.estado = estado;
        this.cliente = cliente;
        this.listaItemCompra = listaItemCompra;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemCompra> getListaItemCompra() {
        return listaItemCompra;
    }

    public void setListaItemCompra(List<ItemCompra> listaItemCompra) {
        this.listaItemCompra = listaItemCompra;
    }

}
