package com.example.digitalmarketlist.objetos;

public class Lista {
    int id;
    String nome;
    String produto;
    int quantidade;
    Double preco;
    String criado_em;

    public Lista() {
    }

    public Lista(String nome, String produto, int quantidade, Double preco) {
        this.nome = nome;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.criado_em = criado_em;
    }

    public Lista(int id, String nome, String produto, int quantidade, Double preco) {
        this.id = id;
        this.nome = nome;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.criado_em = criado_em;
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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCriadoEm() {
        return criado_em;
    }

    public void setCriadoEm(String criado_em) {
        this.criado_em = criado_em;
    }
}
