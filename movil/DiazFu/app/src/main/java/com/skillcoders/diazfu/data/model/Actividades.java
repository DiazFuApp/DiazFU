package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jvier on 14/11/2017.
 */

public class Actividades implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdPromotor")
    @Expose
    private Integer idPromotor;
    @SerializedName("Promotor")
    @Expose
    private String promotor;
    @SerializedName("Titulo")
    @Expose
    private String titulo;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("IdPrioridad")
    @Expose
    private Integer idPrioridad;
    @SerializedName("Prioridad")
    @Expose
    private String prioridad;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    /**
     * No args constructor for use in serialization
     */
    public Actividades() {
    }

    public Actividades(Integer id, Integer idPromotor, String promotor, String titulo, String descripcion, Integer idPrioridad, String prioridad, Integer idEstatus, Integer idUsuario) {
        this.id = id;
        this.idPromotor = idPromotor;
        this.promotor = promotor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idPrioridad = idPrioridad;
        this.prioridad = prioridad;
        this.idEstatus = idEstatus;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPromotor() {
        return idPromotor;
    }

    public void setIdPromotor(Integer idPromotor) {
        this.idPromotor = idPromotor;
    }

    public String getPromotor() {
        return promotor;
    }

    public void setPromotor(String promotor) {
        this.promotor = promotor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
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
        return "Actividades{" +
                "id=" + id +
                ", idPromotor=" + idPromotor +
                ", promotor='" + promotor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idPrioridad=" + idPrioridad +
                ", prioridad='" + prioridad + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
