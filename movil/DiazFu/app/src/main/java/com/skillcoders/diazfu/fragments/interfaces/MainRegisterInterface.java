package com.skillcoders.diazfu.fragments.interfaces;

import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.helpers.GruposHelper;
import com.skillcoders.diazfu.helpers.PrestamosGrupalesHelper;
import com.skillcoders.diazfu.helpers.PromotoresHelper;

/**
 * Created by jvier on 03/10/2017.
 */

public interface MainRegisterInterface {

    /**
     * Permite mostrar el dialogo de preguntas
     **/
    void showQuestion(String titulo, String mensage);

    /**
     * Permite transferir los valores seleccionados en DecodeItem
     */
    void setDecodeItem(DecodeItemHelper decodeItem);

    void registrarPromotor(PromotoresHelper promotoresHelper);

    void editarPromotor(PromotoresHelper promotoresHelper);

    void registrarCliente(ClientesHelper clientesHelper);

    void editarCliente(ClientesHelper clientesHelper);

    void registrarGrupo(GruposHelper gruposHelper);

    void editarGrupo(GruposHelper gruposHelper);

    void registrarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper);

    void editarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper);
}
