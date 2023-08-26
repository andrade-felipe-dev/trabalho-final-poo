package br.edu.irati.ifpr.tads.entity;

public class Curso {
    private String nome;
    private String turno;

    public Curso() {
        nome = "";
        turno = "";
    }

    public Curso(String nome, String turno) {
        this.nome = nome;
        this.turno = turno;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
