package br.edu.irati.ifpr.tads.entity;

public class ItemCompra {
    private int id;
    private Produto produto;
    private double preco;
    private int quantidade;

    public ItemCompra() {
        produto = new Produto();
        preco = 0;
        id = 0;
        quantidade = 0;
    }

    public ItemCompra(int id, Produto produto, double preco, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
