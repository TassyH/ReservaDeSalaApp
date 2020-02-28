package com.example.ui.controledesalas.Modal;

import java.io.Serializable;

public class Reserva implements Serializable {

    private String nomeOrganizador;
    private String horaIncial;
    private String horaFinal;
    private int id;
    private int id_sala;
    private int id_usuario;
    private String descricao;
    private String dataCriacao;
    private String dataAlteracao;

    public Reserva() {
        this.nomeOrganizador = nomeOrganizador;
        this.horaIncial = horaIncial;
        this.horaFinal = horaFinal;
        this.id = id;
        this.id_sala = id_sala;
        this.id_usuario = id_usuario;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNomeOrganizador() {
        return nomeOrganizador;
    }

    public void setNomeOrganizador(String nomeOrganizador) {
        this.nomeOrganizador = nomeOrganizador;
    }

    public String getHoraIncial() {
        return horaIncial;
    }

    public void setHoraIncial(String horaIncial) {
        this.horaIncial = horaIncial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }


}
