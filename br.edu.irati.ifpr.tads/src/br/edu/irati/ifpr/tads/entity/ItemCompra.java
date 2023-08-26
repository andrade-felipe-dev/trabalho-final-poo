package br.edu.irati.ifpr.tads.entity;

public class ItemCompra {
    private Produto produto;
    private double preco;

    public ItemCompra() {
        produto = new Produto();
        preco = 0;
    }

    public ItemCompra(Produto produto, double preco) {
        this.produto = produto;
        this.preco = preco;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
