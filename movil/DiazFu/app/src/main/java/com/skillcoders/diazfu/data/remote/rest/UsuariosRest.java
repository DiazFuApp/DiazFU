package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jvier on 04/09/2017.
 */

public interface UsuariosRest {

    @GET("usuarios")
    Call<List<Usuarios>> getUsuarios();

    @GET("usuarios/{id}")
    Observable<Usuarios> getUsuario(@Path("id") long id);

    @POST("usuarios/login")
    Call<Usuarios> usuariosLogin(@Body Usuarios usuario);
}