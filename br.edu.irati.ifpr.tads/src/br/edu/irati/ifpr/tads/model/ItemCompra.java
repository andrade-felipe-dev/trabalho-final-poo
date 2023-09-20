package br.edu.irati.ifpr.tads.model;

public class ItemCompra {
    private int id;
    private Produto produto;
    private double preco;
    private int quantidade;

    public ItemCompra() {
        produto = new Produto();
        preco = 0;
        id = 0;
    }

    public ItemCompra(int id, Produto produto, double preco) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
