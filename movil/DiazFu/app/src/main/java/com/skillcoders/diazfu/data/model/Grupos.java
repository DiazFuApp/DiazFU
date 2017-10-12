package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jvier on 26/09/2017.
 */

public class Grupos {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("IdClienteResponsable")
    @Expose
    private Integer idClienteResponsable;
    @SerializedName("IdPromotor")
    @Expose
    private Integer idPromotor;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdClienteResponsable() {
        return idClienteResponsable;
    }

    public void setIdClienteResponsable(Integer idClienteResponsable) {
        this.idClienteResponsable = idClienteResponsable;
    }

    public Integer getIdPromotor() {
        return idPromotor;
    }

    public void setIdPromotor(Integer idPromotor) {
        this.idPromotor = idPromotor;
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
        return "Grupos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idClienteResponsable=" + idClienteResponsable +
                ", idPromotor=" + idPromotor +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
