package br.edu.irati.ifpr.tads.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private float saldoAtual;
    private float limiteFiado;
    private List<Pagamento> listaPagamentos;
    private Curso curso;

    public Cliente() {
        id = 0;
        nome = "";
        telefone = "";
        email = "";
        endereco = "";
        saldoAtual = 0;
        limiteFiado = 0;
        listaPagamentos = new ArrayList<>();
        curso = new Curso();
    }

    public Cliente(int id, String nome, String telefone, String email, String endereco, float saldoAtual, float limiteFiado,List<Pagamento> listaPagamentos, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.saldoAtual = saldoAtual;
        this.limiteFiado = limiteFiado;
        this.listaPagamentos = listaPagamentos;
        this.curso = curso;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public float getLimiteFiado() {
        return limiteFiado;
    }

    public void setLimiteFiado(float limiteFiado) {
        this.limiteFiado = limiteFiado;
    } 

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Pagamento> getListaPagamentos() {
        return listaPagamentos;
    }

    public void setListaPagamentos(List<Pagamento> listaPagamentos) {
        this.listaPagamentos = listaPagamentos;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
