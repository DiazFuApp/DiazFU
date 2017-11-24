package com.skillcoders.diazfu.helpers;

import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;

import java.util.List;

/**
 * Created by jvier on 16/10/2017.
 */

public class ClientesHelper {

    private Clientes cliente;
    private List<RedesSociales> redesSociales;
    //TODO DOCUMENTOS

    public ClientesHelper() {
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public List<RedesSociales> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(List<RedesSociales> redesSociales) {
        this.redesSociales = redesSociales;
    }
}
