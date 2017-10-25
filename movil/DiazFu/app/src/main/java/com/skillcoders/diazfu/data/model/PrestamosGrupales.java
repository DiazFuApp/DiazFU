package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jvier on 04/09/2017.
 */

public class PrestamosGrupales implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdGrupo")
    @Expose
    private Integer idGrupo;
    @SerializedName("Grupo")
    @Expose
    private String grupo;
    @SerializedName("Motivo")
    @Expose
    private String motivo;
    @SerializedName("CantidadSolicitada")
    @Expose
    private Double cantidadSolicitada;
    @SerializedName("CantidadOtorgada")
    @Expose
    private Double cantidadOtorgada;
    @SerializedName("Interes")
    @Expose
    private Double interes;
    @SerializedName("Garantia")
    @Expose
    private String garantia;
    @SerializedName("Anticipo")
    @Expose
    private Double anticipo;
    @SerializedName("FechaEntrega")
    @Expose
    private String fechaEntrega;
    @SerializedName("Observaciones")
    @Expose
    private String observaciones;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    /**
     * No args constructor for use in serialization
     */
    public PrestamosGrupales() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Double cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Double getCantidadOtorgada() {
        return cantidadOtorgada;
    }

    public void setCantidadOtorgada(Double cantidadOtorgada) {
        this.cantidadOtorgada = cantidadOtorgada;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public Double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(Double anticipo) {
        this.anticipo = anticipo;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    @Override
    public String toString() {
        return "PrestamosGrupales{" +
                "id=" + id +
                ", idGrupo=" + idGrupo +
                ", grupo='" + grupo + '\'' +
                ", motivo='" + motivo + '\'' +
                ", cantidadSolicitada=" + cantidadSolicitada +
                ", cantidadOtorgada=" + cantidadOtorgada +
                ", interes=" + interes +
                ", garantia=" + garantia +
                ", anticipo=" + anticipo +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
