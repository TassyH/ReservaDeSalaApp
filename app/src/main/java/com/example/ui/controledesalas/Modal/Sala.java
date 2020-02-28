package com.example.ui.controledesalas.Modal;

import java.io.Serializable;

public class Sala implements Serializable {

    private int id;
    private int idOrganizao;

    private String nome;
    private String imagem;
    private String localizacao;
    private double longitude;
    private double latitude;

    private boolean possuiMultimidia;
    private double area;
    private boolean possuiArcon;
    private String descricao;
    private String quantidadePessoasSentadas;
    private String dataCriacao;
    private String dataAlteracao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrganizao() {
        return idOrganizao;
    }

    public void setIdOrganizao(int idOrganizao) {
        this.idOrganizao = idOrganizao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean getPossuiMultimidia() {
        return possuiMultimidia;
    }

    public void setPossuiMultimidia(boolean possuiMidia) {
        this.possuiMultimidia = possuiMultimidia;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean getPossuiArcon() {
        return possuiArcon;
    }

    public void setPossuiArcon(boolean possuiArcon) {
        this.possuiMultimidia = possuiArcon;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidadePessoasSentadas() {
        return quantidadePessoasSentadas;
    }

    public void setQuantidadePessoasSentadas(String quantidadePessoasSentadas) {
        this.quantidadePessoasSentadas = quantidadePessoasSentadas;
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

    /* public Sala(int id, String nome, String imagem, Boolean estado, String localizacao, String refrigeracao, String quantidadePessoasSentadas, String possuiMidia, String descricao, String longitude, String latitude) {
        this.nome = nome;
        this.imagem = imagem;
        this.localizacao = localizacao;
        this.id = id;
        this.quantidadePessoasSentadas = quantidadePessoasSentadas;
        this.possuiMidia = possuiMidia;
        this.refrigeracao = refrigeracao;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;*/

}

   /* public String getDescricao() {
        return descricao;
    }

    public String isRefrigeracao() {
        return refrigeracao;
    }

    public String getQuantidadePessoasSentadas() {
        return quantidadePessoasSentadas;
    }

    public void setQuantidadePessoasSentadas(String quantidadePessoasSentadas) {
        this.quantidadePessoasSentadas = quantidadePessoasSentadas;
    }

    public String getImagem() {
        return imagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String isPossuiMidia() {
        return possuiMidia;
    }

    public double getArea() {
        return area;
    }
*/



