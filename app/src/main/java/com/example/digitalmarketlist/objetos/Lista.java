package com.example.digitalmarketlist.objetos;

public class Lista {
    int id;
    int dono;
    String nome;
    String criado_em;

    public Lista() {
    }

    public Lista(int dono, String nome) {
        this.dono = dono;
        this.nome = nome;
    }

    public Lista(int id, int dono, String nome) {
        this.id = id;
        this.dono = dono;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDono() {
        return dono;
    }

    public void setDono(int dono) {
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCriadoEm() {
        return criado_em;
    }

    public void setCriadoEm(String criado_em) {
        this.criado_em = criado_em;
    }
}
