package br.edu.irati.ifpr.tads.entity;

public class Curso {
    private int id;
    private String nome;
    private String turno;

    public Curso() {
        nome = "";
        turno = "";
        id = 0;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
