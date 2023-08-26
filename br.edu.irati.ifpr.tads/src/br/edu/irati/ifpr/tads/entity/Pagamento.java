package br.edu.irati.ifpr.tads.entity;

import java.util.Date;

public class Pagamento {
    private double valor;
    private Date dataHora;
    private String formaPagamento;
    private Cliente cliente;
    private Compra compra;

    public Pagamento() {
        valor = 0;
        dataHora = new Date();
        formaPagamento = "";
        cliente = new Cliente();
        compra = new Compra();
    }

    public Pagamento(double valor, Date dataHora, String formaPagamento, Cliente cliente, Compra compra) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.formaPagamento = formaPagamento;
        this.cliente = cliente;
        this.compra = compra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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
}
