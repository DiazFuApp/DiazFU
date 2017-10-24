package com.skillcoders.diazfu.helpers;

import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;

/**
 * Created by jvier on 16/10/2017.
 */

public class PromotoresHelper {

    private Promotores promotor;
    private ReferenciasPromotores primeraReferencia;
    private ReferenciasPromotores segundaReferencia;
    //TODO REDES SOCIALES
    //TODO DOCUMENTOS

    public PromotoresHelper() {
    }

    public Promotores getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotores promotor) {
        this.promotor = promotor;
    }

    public ReferenciasPromotores getPrimeraReferencia() {
        return primeraReferencia;
    }

    public void setPrimeraReferencia(ReferenciasPromotores primeraReferencia) {
        this.primeraReferencia = primeraReferencia;
    }

    public ReferenciasPromotores getSegundaReferencia() {
        return segundaReferencia;
    }

    public void setSegundaReferencia(ReferenciasPromotores segundaReferencia) {
        this.segundaReferencia = segundaReferencia;
    }
}
