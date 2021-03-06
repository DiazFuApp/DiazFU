package com.skillcoders.diazfu.helpers;

import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;

import java.util.List;

/**
 * Created by jvier on 16/10/2017.
 */

public class PrestamosIndividualesHelper {

    private PrestamosIndividuales prestamoIndividual;
    private ReferenciasPrestamos aval;
    private ReferenciasPrestamos primeraReferencia;
    private ReferenciasPrestamos segundaReferencia;
    private Pagos pagos;
    private List<RedesSociales> redesSociales;

    public PrestamosIndividualesHelper() {
    }

    public PrestamosIndividuales getPrestamoIndividual() {
        return prestamoIndividual;
    }

    public void setPrestamoIndividual(PrestamosIndividuales prestamoIndividual) {
        this.prestamoIndividual = prestamoIndividual;
    }

    public ReferenciasPrestamos getAval() {
        return aval;
    }

    public void setAval(ReferenciasPrestamos aval) {
        this.aval = aval;
    }

    public ReferenciasPrestamos getPrimeraReferencia() {
        return primeraReferencia;
    }

    public void setPrimeraReferencia(ReferenciasPrestamos primeraReferencia) {
        this.primeraReferencia = primeraReferencia;
    }

    public ReferenciasPrestamos getSegundaReferencia() {
        return segundaReferencia;
    }

    public void setSegundaReferencia(ReferenciasPrestamos segundaReferencia) {
        this.segundaReferencia = segundaReferencia;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public List<RedesSociales> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(List<RedesSociales> redesSociales) {
        this.redesSociales = redesSociales;
    }
}
