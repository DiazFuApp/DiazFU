package com.skillcoders.diazfu.utils;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

    public static boolean esSpinnerValido(Spinner spinner) {

        if (spinner.getSelectedItemId() <= 0L) {
            TextView errorTextSE = (TextView) spinner.getSelectedView();
            errorTextSE.setError("El campo es obligatorio");
            errorTextSE.setTextColor(Color.RED);
            errorTextSE.setText("El campo es obligatorio");
            errorTextSE.requestFocus();

            return false;
        }

        return true;
    }
}
