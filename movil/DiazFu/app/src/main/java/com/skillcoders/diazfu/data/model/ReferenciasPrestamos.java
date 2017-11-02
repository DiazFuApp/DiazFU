package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jvier on 26/10/2017.
 */

public class ReferenciasPrestamos {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdPrestamo")
    @Expose
    private Integer idPrestamo;
    @SerializedName("IdTipoPrestamo")
    @Expose
    private Integer idTipoPrestamo;
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
    @SerializedName("ReferenciaDireccion")
    @Expose
    private String referenciaDireccion;
    @SerializedName("TelefonoCasa")
    @Expose
    private String telefonoCasa;
    @SerializedName("TelefonoCelular")
    @Expose
    private String telefonoCelular;
    @SerializedName("CorreoElectronico")
    @Expose
    private String correoElectronico;
    @SerializedName("Parentesco")
    @Expose
    private String parentesco;
    @SerializedName("URLFoto")
    @Expose
    private String uRLFoto;
    @SerializedName("Empresa")
    @Expose
    private String empresa;
    @SerializedName("PuestoEmpresa")
    @Expose
    private String puestoEmpresa;
    @SerializedName("DireccionEmpresa")
    @Expose
    private String direccionEmpresa;
    @SerializedName("AntiguedadEmpresa")
    @Expose
    private String antiguedadEmpresa;
    @SerializedName("TelefonoEmpresa")
    @Expose
    private String telefonoEmpresa;
    @SerializedName("NombreJefe")
    @Expose
    private String nombreJefe;
    @SerializedName("IdEstatus")
    @Expose
    private Integer idEstatus;
    @SerializedName("IdUsuario")
    @Expose
    private Integer idUsuario;

    /**
     * No args constructor for use in serialization
     */
    public ReferenciasPrestamos() {
    }

    /**
     * @param direccion
     * @param parentesco
     * @param idPrestamo
     * @param cURP
     * @param telefonoCasa
     * @param referenciaDireccion
     * @param antiguedadEmpresa
     * @param telefonoCelular
     * @param fechaNacimiento
     * @param direccionEmpresa
     * @param puestoEmpresa
     * @param uRLFoto
     * @param claveElector
     * @param id
     * @param nombre
     * @param correoElectronico
     * @param idUsuario
     * @param nombreJefe
     * @param telefonoEmpresa
     * @param rFC
     * @param empresa
     * @param idTipoPrestamo
     * @param idEstatus
     * @param idTipoReferencia
     */
    public ReferenciasPrestamos(Integer id, Integer idPrestamo, Integer idTipoPrestamo, Integer idTipoReferencia, String nombre, String rFC, String cURP, String fechaNacimiento, String claveElector, String direccion, String referenciaDireccion, String telefonoCasa, String telefonoCelular, String correoElectronico, String parentesco, String uRLFoto, String empresa, String puestoEmpresa, String direccionEmpresa, String antiguedadEmpresa, String telefonoEmpresa, String nombreJefe, Integer idEstatus, Integer idUsuario) {
        super();
        this.id = id;
        this.idPrestamo = idPrestamo;
        this.idTipoPrestamo = idTipoPrestamo;
        this.idTipoReferencia = idTipoReferencia;
        this.nombre = nombre;
        this.rFC = rFC;
        this.cURP = cURP;
        this.fechaNacimiento = fechaNacimiento;
        this.claveElector = claveElector;
        this.direccion = direccion;
        this.referenciaDireccion = referenciaDireccion;
        this.telefonoCasa = telefonoCasa;
        this.telefonoCelular = telefonoCelular;
        this.correoElectronico = correoElectronico;
        this.parentesco = parentesco;
        this.uRLFoto = uRLFoto;
        this.empresa = empresa;
        this.puestoEmpresa = puestoEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.antiguedadEmpresa = antiguedadEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.nombreJefe = nombreJefe;
        this.idEstatus = idEstatus;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(Integer idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
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

    public String getReferenciaDireccion() {
        return referenciaDireccion;
    }

    public void setReferenciaDireccion(String referenciaDireccion) {
        this.referenciaDireccion = referenciaDireccion;
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

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getURLFoto() {
        return uRLFoto;
    }

    public void setURLFoto(String uRLFoto) {
        this.uRLFoto = uRLFoto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getAntiguedadEmpresa() {
        return antiguedadEmpresa;
    }

    public void setAntiguedadEmpresa(String antiguedadEmpresa) {
        this.antiguedadEmpresa = antiguedadEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
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
        return "ReferenciasPrestamos{" +
                "id=" + id +
                ", idPrestamo=" + idPrestamo +
                ", idTipoPrestamo=" + idTipoPrestamo +
                ", idTipoReferencia=" + idTipoReferencia +
                ", nombre='" + nombre + '\'' +
                ", rFC='" + rFC + '\'' +
                ", cURP='" + cURP + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", claveElector='" + claveElector + '\'' +
                ", direccion='" + direccion + '\'' +
                ", referenciaDireccion='" + referenciaDireccion + '\'' +
                ", telefonoCasa='" + telefonoCasa + '\'' +
                ", telefonoCelular='" + telefonoCelular + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", parentesco='" + parentesco + '\'' +
                ", uRLFoto='" + uRLFoto + '\'' +
                ", empresa='" + empresa + '\'' +
                ", puestoEmpresa='" + puestoEmpresa + '\'' +
                ", direccionEmpresa='" + direccionEmpresa + '\'' +
                ", antiguedadEmpresa='" + antiguedadEmpresa + '\'' +
                ", telefonoEmpresa='" + telefonoEmpresa + '\'' +
                ", nombreJefe='" + nombreJefe + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
