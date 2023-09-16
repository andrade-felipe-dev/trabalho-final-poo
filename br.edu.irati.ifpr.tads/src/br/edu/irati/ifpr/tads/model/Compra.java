package br.edu.irati.ifpr.tads.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private int id;    
    private Date dataHora;
    private CompraEstadoENUM estado;
    private Cliente cliente;
    private List<ItemCompra> listaItemCompra;
    
    public Compra() {
        id = 0;
        dataHora = new Date();
        estado = CompraEstadoENUM.PENDENTE;
        cliente = new Cliente();
        listaItemCompra = new ArrayList<>();
    }
    
    public Compra(int id, Date dataHora, CompraEstadoENUM estado, Cliente cliente, List<ItemCompra> listaItemCompra) {
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

    public CompraEstadoENUM getEstado() {
        return estado;
    }

    public void setEstado(CompraEstadoENUM estado) {
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
