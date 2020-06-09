package com.example.digitalmarketlist.objetos;

public class Lista {
    int id;
    int dono;
    String nome;
    String produto;
    Double preco;
    String criado_em;

    public Lista() {
    }

    public Lista(int dono, String nome, String produto, Double preco) {
        this.dono = dono;
        this.nome = nome;
        this.produto = produto;
        this.preco = preco;
    }

    public Lista(int id, int dono, String nome, String produto, Double preco) {
        this.id = id;
        this.dono = dono;
        this.nome = nome;
        this.produto = produto;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public int getDono() {
        return dono;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setCriadoEm(String criado_em) {
        this.criado_em = criado_em;
    }
}
