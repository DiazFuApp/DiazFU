package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jvier on 04/09/2017.
 */

public class Clientes {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("TelefonoCasa")
    @Expose
    private String telefonoCasa;
    @SerializedName("TelefonoCelular")
    @Expose
    private String telefonoCelular;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("FechaNacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("CorreoElectronico")
    @Expose
    private String correoElectronico;
    @SerializedName("URLFoto")
    @Expose
    private String uRLFoto;
    @SerializedName("NombreEmpresa")
    @Expose
    private String nombreEmpresa;
    @SerializedName("PuestoEmpresa")
    @Expose
    private String puestoEmpresa;
    @SerializedName("DireccionEmpresa")
    @Expose
    private String direccionEmpresa;
    @SerializedName("HorarioEmpresa")
    @Expose
    private String horarioEmpresa;
    @SerializedName("Antiguedad")
    @Expose
    private String antiguedad;
    @SerializedName("TelefonoEmpresa")
    @Expose
    private String telefonoEmpresa;
    @SerializedName("SueldoMensual")
    @Expose
    private String sueldoMensual;
    @SerializedName("NombreJefe")
    @Expose
    private String nombreJefe;
    @SerializedName("TelefonoJefe")
    @Expose
    private String telefonoJefe;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getURLFoto() {
        return uRLFoto;
    }

    public void setURLFoto(String uRLFoto) {
        this.uRLFoto = uRLFoto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPuestoEmpresa() {
        return puestoEmpresa;
    }

    public void setPuestoEmpresa(String puestoEmpresa) {
        this.puestoEmpresa = puestoEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getHorarioEmpresa() {
        return horarioEmpresa;
    }

    public void setHorarioEmpresa(String horarioEmpresa) {
        this.horarioEmpresa = horarioEmpresa;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(String sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    public String getTelefonoJefe() {
        return telefonoJefe;
    }

    public void setTelefonoJefe(String telefonoJefe) {
        this.telefonoJefe = telefonoJefe;
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
        return "Clientes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefonoCasa='" + telefonoCasa + '\'' +
                ", telefonoCelular='" + telefonoCelular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", uRLFoto='" + uRLFoto + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", puestoEmpresa='" + puestoEmpresa + '\'' +
                ", direccionEmpresa='" + direccionEmpresa + '\'' +
                ", horarioEmpresa='" + horarioEmpresa + '\'' +
                ", antiguedad='" + antiguedad + '\'' +
                ", telefonoEmpresa='" + telefonoEmpresa + '\'' +
                ", sueldoMensual='" + sueldoMensual + '\'' +
                ", nombreJefe='" + nombreJefe + '\'' +
                ", telefonoJefe='" + telefonoJefe + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
