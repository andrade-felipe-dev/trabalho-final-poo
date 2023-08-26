package br.edu.irati.ifpr.tads.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String telefone;
    private String email;
    private float saldoAtual;
    private float limiteFiado;
    private List<Pagamento> pagamentos;
    private List<Curso> listaCursos;

    public Cliente() {
        nome = "";
        telefone = "";
        email = "";
        saldoAtual = 0;
        limiteFiado = 0;
        pagamentos = new ArrayList<>();
        listaCursos = new ArrayList<>();
    }

    public Cliente(String nome, String telefone, String email, float saldoAtual, float limiteFiado, List<Pagamento> pagamentos, List<Curso> listaCursos) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.saldoAtual = saldoAtual;
        this.limiteFiado = limiteFiado;
        this.pagamentos = pagamentos;
        this.listaCursos = listaCursos;
    }

    public List<Pagamento> pagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
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
    
    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }    
}
