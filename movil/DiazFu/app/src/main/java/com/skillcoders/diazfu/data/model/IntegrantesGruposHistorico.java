package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jvier on 19/10/2017.
 */

public class IntegrantesGruposHistorico implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdGrupoHistorico")
    @Expose
    private Integer idGrupoHistorico;
    @SerializedName("IdCliente")
    @Expose
    private Integer idCliente;
    @SerializedName("Cliente")
    @Expose
    private String Cliente;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    private Double cantidadOtorgada;

    public IntegrantesGruposHistorico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGrupoHistorico() {
        return idGrupoHistorico;
    }

    public void setIdGrupoHistorico(Integer idGrupoHistorico) {
        this.idGrupoHistorico = idGrupoHistorico;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getCantidadOtorgada() {
        return cantidadOtorgada;
    }

    public void setCantidadOtorgada(Double cantidadOtorgada) {
        this.cantidadOtorgada = cantidadOtorgada;
    }

    @Override
    public String toString() {
        return "IntegrantesGrupos{" +
                "id=" + id +
                ", idGrupoHistorico=" + idGrupoHistorico +
                ", idCliente=" + idCliente +
                ", Cliente='" + Cliente + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                ", cantidadOtorgada=" + cantidadOtorgada +
                '}';
    }
}
