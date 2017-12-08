package com.skillcoders.diazfu.helpers;

import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.model.Usuarios;

import java.util.List;

/**
 * Created by jvier on 16/10/2017.
 */

public class PromotoresHelper {

    private Promotores promotor;
    private Usuarios usuario;
    private ReferenciasPromotores primeraReferencia;
    private ReferenciasPromotores segundaReferencia;
    private List<RedesSociales> redesSocialesPromotor;
    private List<RedesSociales> redesSocialesPrimerReferencia;
    private List<RedesSociales> redesSocialesSegundaReferencia;

    public PromotoresHelper() {
    }

    public Promotores getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotores promotor) {
        this.promotor = promotor;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
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

    public List<RedesSociales> getRedesSocialesPromotor() {
        return redesSocialesPromotor;
    }

    public void setRedesSocialesPromotor(List<RedesSociales> redesSocialesPromotor) {
        this.redesSocialesPromotor = redesSocialesPromotor;
    }

    public List<RedesSociales> getRedesSocialesPrimerReferencia() {
        return redesSocialesPrimerReferencia;
    }

    public void setRedesSocialesPrimerReferencia(List<RedesSociales> redesSocialesPrimerReferencia) {
        this.redesSocialesPrimerReferencia = redesSocialesPrimerReferencia;
    }

    public List<RedesSociales> getRedesSocialesSegundaReferencia() {
        return redesSocialesSegundaReferencia;
    }

    public void setRedesSocialesSegundaReferencia(List<RedesSociales> redesSocialesSegundaReferencia) {
        this.redesSocialesSegundaReferencia = redesSocialesSegundaReferencia;
    }
}
