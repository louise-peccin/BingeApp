package com.example.bingeapp.model;

public class Filme {
    private int id;
    private String nome;
    private String genero;
    private int ano;
    private String status;
    private int nota;

    public Filme() {
    }

    public Filme(int id, String nome, String genero, int ano, String status, int nota) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
        this.status = status;
        this.nota = nota;
    }

    public Filme(String nome, String genero, int ano, String status, int nota) {
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
        this.status = status;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    public String getStatus() {
        return status;
    }

    public int getNota() {
        return nota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}