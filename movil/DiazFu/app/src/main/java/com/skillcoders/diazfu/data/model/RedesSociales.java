package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jvier on 22/11/2017.
 */

public class RedesSociales implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdTipoRedSocial")
    @Expose
    private Integer idTipoRedSocial;
    @SerializedName("IdActor")
    @Expose
    private Integer idActor;
    @SerializedName("IdTipoActor")
    @Expose
    private Integer idTipoActor;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    /**
     * No args constructor for use in serialization
     */
    public RedesSociales() {
    }

    public RedesSociales(Integer idTipoRedSocial, Integer idTipoActor, String uRL) {
        this.idTipoRedSocial = idTipoRedSocial;
        this.idTipoActor = idTipoActor;
        this.uRL = uRL;
    }

    /**
     * @param id
     * @param idTipoRedSocial
     * @param idUsuario
     * @param uRL
     * @param idTipoActor
     * @param idActor
     * @param idEstatus
     */
    public RedesSociales(Integer id, Integer idTipoRedSocial, Integer idActor, Integer idTipoActor, String uRL, Integer idEstatus, Integer idUsuario) {
        super();
        this.id = id;
        this.idTipoRedSocial = idTipoRedSocial;
        this.idActor = idActor;
        this.idTipoActor = idTipoActor;
        this.uRL = uRL;
        this.idEstatus = idEstatus;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTipoRedSocial() {
        return idTipoRedSocial;
    }

    public void setIdTipoRedSocial(Integer idTipoRedSocial) {
        this.idTipoRedSocial = idTipoRedSocial;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public Integer getIdTipoActor() {
        return idTipoActor;
    }

    public void setIdTipoActor(Integer idTipoActor) {
        this.idTipoActor = idTipoActor;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
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
        return "RedesSociales{" +
                "id=" + id +
                ", idTipoRedSocial=" + idTipoRedSocial +
                ", idActor=" + idActor +
                ", idTipoActor=" + idTipoActor +
                ", uRL='" + uRL + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
