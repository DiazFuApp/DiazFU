package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jvier on 14/11/2017.
 */

public class Comisiones implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdPromotor")
    @Expose
    private Integer idPromotor;
    @SerializedName("Promotor")
    @Expose
    private String promotor;
    @SerializedName("Comision")
    @Expose
    private Double comision;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    /**
     * No args constructor for use in serialization
     */
    public Comisiones() {
    }

    /**
     * @param id
     * @param idUsuario
     * @param descripcion
     * @param promotor
     * @param idEstatus
     * @param comision
     * @param idPromotor
     */
    public Comisiones(Integer id, Integer idPromotor, String promotor, Double comision, String descripcion, Integer idEstatus, Integer idUsuario) {
        super();
        this.id = id;
        this.idPromotor = idPromotor;
        this.promotor = promotor;
        this.comision = comision;
        this.descripcion = descripcion;
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

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        return "Comisiones{" +
                "id=" + id +
                ", idPromotor=" + idPromotor +
                ", promotor='" + promotor + '\'' +
                ", comision=" + comision +
                ", descripcion='" + descripcion + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
