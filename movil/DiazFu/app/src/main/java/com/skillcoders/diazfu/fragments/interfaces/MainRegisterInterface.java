package com.skillcoders.diazfu.fragments.interfaces;

import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.helpers.ActividadesHelper;
import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.ComisionesHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.helpers.GruposHelper;
import com.skillcoders.diazfu.helpers.PagosHelper;
import com.skillcoders.diazfu.helpers.PrestamosGrupalesHelper;
import com.skillcoders.diazfu.helpers.PrestamosIndividualesHelper;
import com.skillcoders.diazfu.helpers.PromotoresHelper;

/**
 * Created by jvier on 03/10/2017.
 */

public interface MainRegisterInterface {

    void openExternalActivity(int action, Class<?> externalActivity);

    void showQuestion(String titulo, String mensage);

    void setDecodeItem(DecodeItemHelper decodeItem);

    void registrarPromotor(PromotoresHelper promotoresHelper);

    void editarPromotor(PromotoresHelper promotoresHelper);

    void registrarCliente(ClientesHelper clientesHelper);

    void editarCliente(ClientesHelper clientesHelper);

    void registrarGrupo(GruposHelper gruposHelper);

    void editarGrupo(GruposHelper gruposHelper);

    void registrarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper);

    void autorizarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper);

    void entregarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper);

    void registrarPagoGrupal(PagosHelper helper);

    void registrarPagoIndividual(PagosHelper helper);

    void registrarPrestamoIndividual(PrestamosIndividualesHelper helper);

    void autorizarPrestamoIndividual(PrestamosIndividualesHelper helper);

    void entregarPrestamoIndividual(PrestamosIndividualesHelper helper);

    void registrarActividad(ActividadesHelper helper);

    void editarActividad(ActividadesHelper helper);

    void registrarComision(ComisionesHelper helper);

    void pagarComision(ComisionesHelper helper);

    void showProgressDialog();

    void stopProgressDialog();
}
