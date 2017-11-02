package com.skillcoders.diazfu.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jvier on 04/09/2017.
 */

public class Pagos implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdPrestamo")
    @Expose
    private Integer idPrestamo;
    @SerializedName("IdCliente")
    @Expose
    private Integer idCliente;
    @SerializedName("Cliente")
    @Expose
    private String cliente;
    @SerializedName("IdTipoPrestamo")
    @Expose
    private Integer idTipoPrestamo;
    @SerializedName("MontoAPagar")
    @Expose
    private Double montoAPagar;
    @SerializedName("MontoPagado")
    @Expose
    private Double montoPagado;
    @SerializedName("Plazo")
    @Expose
    private String plazo;
    @SerializedName("TipoPago")
    @Expose
    private String tipoPago;
    @SerializedName("FechaProgramada")
    @Expose
    private String fechaProgramada;
    @SerializedName("FechaPago")
    @Expose
    private String fechaPago;
    @SerializedName("Morosidad")
    @Expose
    private Double morosidad;
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
    public Pagos() {
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(Integer idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
    }

    public Double getMontoAPagar() {
        return montoAPagar;
    }

    public void setMontoAPagar(Double montoAPagar) {
        this.montoAPagar = montoAPagar;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(String fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMorosidad() {
        return morosidad;
    }

    public void setMorosidad(Double morosidad) {
        this.morosidad = morosidad;
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
        return "Pagos{" +
                "id=" + id +
                ", idPrestamo=" + idPrestamo +
                ", idCliente=" + idCliente +
                ", cliente='" + cliente + '\'' +
                ", idTipoPrestamo=" + idTipoPrestamo +
                ", montoAPagar=" + montoAPagar +
                ", montoPagado=" + montoPagado +
                ", plazo='" + plazo + '\'' +
                ", tipoPago='" + tipoPago + '\'' +
                ", fechaProgramada='" + fechaProgramada + '\'' +
                ", fechaPago='" + fechaPago + '\'' +
                ", morosidad=" + morosidad +
                ", descripcion='" + descripcion + '\'' +
                ", idEstatus=" + idEstatus +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
