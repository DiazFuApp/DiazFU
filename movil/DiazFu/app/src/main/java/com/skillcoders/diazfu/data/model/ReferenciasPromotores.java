package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jvier on 16/10/2017.
 */

public class ReferenciasPromotores {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdActor")
    @Expose
    private Integer idActor;
    @SerializedName("IdTipoReferencia")
    @Expose
    private Integer idTipoReferencia;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("RFC")
    @Expose
    private String rFC;
    @SerializedName("CURP")
    @Expose
    private String cURP;
    @SerializedName("FechaNacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("ClaveElector")
    @Expose
    private String claveElector;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("TelefonoCasa")
    @Expose
    private String telefonoCasa;
    @SerializedName("TelefonoCelular")
    @Expose
    private String telefonoCelular;
    @SerializedName("CorreoElectronico")
    @Expose
    private String correoElectronico;
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

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public Integer getIdTipoReferencia() {
        return idTipoReferencia;
    }

    public void setIdTipoReferencia(Integer idTipoReferencia) {
        this.idTipoReferencia = idTipoReferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRFC() {
        return rFC;
    }

    public void setRFC(String rFC) {
        this.rFC = rFC;
    }

    public String getCURP() {
        return cURP;
    }

    public void setCURP(String cURP) {
        this.cURP = cURP;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getClaveElector() {
        return claveElector;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
        return "ReferenciasPromotores{" +
                "id=" + id +
                ", idActor=" + idActor +
                ", idTipoReferencia=" + idTipoReferencia +
                ", nombre='" + nombre + '\'' +
                ", rFC='" + rFC + '\'' +
                ", cURP='" + cURP + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", claveElector='" + claveElector + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefonoCasa='" + telefonoCasa + '\'' +
                ", telefonoCelular='" + telefonoCelular + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
