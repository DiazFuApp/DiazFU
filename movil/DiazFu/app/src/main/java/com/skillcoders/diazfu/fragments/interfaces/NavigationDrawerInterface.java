package com.skillcoders.diazfu.fragments.interfaces;

import com.skillcoders.diazfu.helpers.DecodeItemHelper;

/**
 * Created by jvier on 04/09/2017.
 */

public interface NavigationDrawerInterface {
    /**Permite mostrar el dialogo de preguntas**/
    void showQuestion();
    /**Permite transferir los valores seleccionados en DecodeItem*/
    void setDecodeItem(DecodeItemHelper decodeItem);
    /**Permte abrir una actividad externa enviando parametros en el DecodeItem**/
    void openExternalActivity(int action, Class<?> externalActivity);
}
