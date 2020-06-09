package com.example.digitalmarketlist.objetos;

public class Produtos {
    int id;
    int listaId;
    String nome;
    String valor;
    String criado_em;

    public Produtos() {
    }

    public Produtos(int listaId, String nome, String valor) {
        this.listaId = listaId;
        this.nome = nome;
        this.valor = valor;
    }

    public Produtos(int id, int listaId, String nome, String valor) {
        this.id = id;
        this.listaId = listaId;
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListaId() {
        return listaId;
    }

    public void setListaId(int listaId) {
        this.listaId = listaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCriadoEm() {
        return criado_em;
    }

    public void setCriadoEm(String criado_em) {
        this.criado_em = criado_em;
    }
}
