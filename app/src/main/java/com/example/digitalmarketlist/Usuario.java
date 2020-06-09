package com.example.digitalmarketlist;

public class Usuario {
    int id;
    String email;
    String senha;
    String criado_em;

    // constructors
    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCriadoEm(String criado_em){
        this.criado_em = criado_em;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }
}
