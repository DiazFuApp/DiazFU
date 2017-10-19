package com.skillcoders.diazfu.fragments.interfaces;

import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.PromotoresHelper;

/**
 * Created by jvier on 03/10/2017.
 */

public interface MainRegisterInterface {

    void registrarPromotor(PromotoresHelper promotoresHelper);

    void editarPromotor(PromotoresHelper promotoresHelper);

    void registrarCliente(ClientesHelper clientesHelper);

    void editarCliente(ClientesHelper clientesHelper);
}
