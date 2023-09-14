package br.edu.irati.ifpr.tads.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pagamento {
    private int id;
    private double valor;
    private Date dataHora;
    private String formaPagamento;
    private Cliente cliente;
    private List<Compra> listaCompras;

    public Pagamento() {
        id = 0;
        valor = 0;
        dataHora = new Date();
        formaPagamento = "";
        cliente = new Cliente();
        listaCompras = new ArrayList<>();
    }

    public Pagamento(int id, double valor, Date dataHora, String formaPagamento, Cliente cliente, List<Compra> listaCompras) {
        this.id = id;
        this.valor = valor;
        this.dataHora = dataHora;
        this.formaPagamento = formaPagamento;
        this.cliente = cliente;
        this.listaCompras = listaCompras;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }
}
