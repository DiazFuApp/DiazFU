package com.skillcoders.diazfu.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.utils.Constants;

/**
 * Created by jvier on 07/12/2017.
 */

public class SharedPreferencesService {

    public static Usuarios getUsuarioActual(Context context) {
        Usuarios usuario = new Usuarios();
        SharedPreferences credenciales = context.getSharedPreferences(Constants.KEY_PREF_CREDENCIALS, Context.MODE_PRIVATE);
        String username = credenciales.getString(Constants.KEY_PREF_CREDENCIALS_USERNAME, "");
        Integer idUsuario = credenciales.getInt(Constants.KEY_PREF_CREDENCIALS_USER_ID, 0);
        Integer idTipoActor = credenciales.getInt(Constants.KEY_PREF_CREDENCIALS_TIPO_USER, 0);

        usuario.setId(idUsuario);
        usuario.setIdTipoActor(idTipoActor);
        usuario.setNombre(username);

        return usuario;
    }
}
