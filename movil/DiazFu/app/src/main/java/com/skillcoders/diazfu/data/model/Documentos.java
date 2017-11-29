package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jvier on 27/11/2017.
 */

public class Documentos implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdTipoDocumento")
    @Expose
    private Integer idTipoDocumento;
    @SerializedName("IdTipoActor")
    @Expose
    private Integer idTipoActor;
    @SerializedName("IdActor")
    @Expose
    private Integer idActor;
    @SerializedName("URLDocumento")
    @Expose
    private String uRLDocumento;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    /**
     * No args constructor for use in serialization
     */
    public Documentos() {
    }

    /**
     * @param id
     * @param idUsuario
     * @param idTipoDocumento
     * @param idTipoActor
     * @param idActor
     * @param idEstatus
     * @param uRLDocumento
     */
    public Documentos(Integer id, Integer idTipoDocumento, Integer idTipoActor, Integer idActor, String uRLDocumento, Integer idEstatus, Integer idUsuario) {
        super();
        this.id = id;
        this.idTipoDocumento = idTipoDocumento;
        this.idTipoActor = idTipoActor;
        this.idActor = idActor;
        this.uRLDocumento = uRLDocumento;
        this.idEstatus = idEstatus;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getIdTipoActor() {
        return idTipoActor;
    }

    public void setIdTipoActor(Integer idTipoActor) {
        this.idTipoActor = idTipoActor;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public String getURLDocumento() {
        return uRLDocumento;
    }

    public void setURLDocumento(String uRLDocumento) {
        this.uRLDocumento = uRLDocumento;
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
        return "Documentos{" +
                "id=" + id +
                ", idTipoDocumento=" + idTipoDocumento +
                ", idTipoActor=" + idTipoActor +
                ", idActor=" + idActor +
                ", uRLDocumento='" + uRLDocumento + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
