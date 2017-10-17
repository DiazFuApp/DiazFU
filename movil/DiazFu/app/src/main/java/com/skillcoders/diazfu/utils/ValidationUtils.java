package com.skillcoders.diazfu.utils;

import android.support.design.widget.TextInputLayout;

/**
 * Created by jvier on 07/10/2017.
 */

public class ValidationUtils {

    public static boolean esNumeroValido(TextInputLayout txt, String numero) {

        txt.setError(null);
        txt.setErrorEnabled(false);

        if (numero.isEmpty()) {
            txt.setError("Número inválido");
            txt.setErrorEnabled(true);
            return false;
        }

        return true;
    }

    public static boolean esTextoValido(TextInputLayout txt, String texto) {

        txt.setError(null);
        txt.setErrorEnabled(false);

        if (texto.isEmpty()) {
            txt.setError("Texto inválido");
            txt.setErrorEnabled(true);
            return false;
        }

        return true;
    }
}
