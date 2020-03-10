package com.example.blackjack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String nome;
    private String senha;
    private String email;
    private String username;
    private int vitorias;
    private int derrotas;
    private List<Integer> handList = new ArrayList<>();

    public User(){

    }

    public User(String username, String senha, String nome, String email, int vitorias
    , int derrotas){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.username = username;
        this.vitorias = 0;
        this.derrotas = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = this.getVitorias() + 1;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = this.getDerrotas() +1;
    }

    public List<Integer> getHandList() {
        return handList;
    }

}
