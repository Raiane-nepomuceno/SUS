package com.example.sus.model;

import java.util.Date;

public class Consulta {
    private String nome;
    private String telefone;
    private String dataConsulta;

    public Consulta(String nome, String telefone, String dataConsulta) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataConsulta = dataConsulta;
    }

    public Consulta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}


