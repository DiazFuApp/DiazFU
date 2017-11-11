package com.skillcoders.diazfu.helpers;

import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;

import java.util.List;

/**
 * Created by jvier on 07/11/2017.
 */

public class PagosHelper {
    private Pagos pago;
    private List<Pagos> pagos;
    private PrestamosGrupales prestamosGrupales;

    public PagosHelper() {
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public List<Pagos> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pagos> pagos) {
        this.pagos = pagos;
    }

    public PrestamosGrupales getPrestamosGrupales() {
        return prestamosGrupales;
    }

    public void setPrestamosGrupales(PrestamosGrupales prestamosGrupales) {
        this.prestamosGrupales = prestamosGrupales;
    }
}
