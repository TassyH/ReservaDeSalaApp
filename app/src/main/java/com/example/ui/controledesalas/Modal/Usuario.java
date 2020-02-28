package com.example.ui.controledesalas.Modal;

import java.io.Serializable;

public class Usuario implements Serializable {
      private String nome;
      private String email;
      private String senha;
      private int id;
      private int id_organizacao;


    public Usuario(String nome, String email, String senha, int id, int id_organizacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.id_organizacao = id_organizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_organizacao() {
        return id_organizacao;
    }

    public void setId_organizacao(int id_organizacao) {
        this.id_organizacao = id_organizacao;
    }
}
