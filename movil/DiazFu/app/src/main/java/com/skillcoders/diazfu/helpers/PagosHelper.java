package com.skillcoders.diazfu.helpers;

import com.skillcoders.diazfu.data.model.Pagos;

import java.util.List;

/**
 * Created by jvier on 07/11/2017.
 */

public class PagosHelper {
    private Pagos pago;
    private List<Pagos> pagos;

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
}
