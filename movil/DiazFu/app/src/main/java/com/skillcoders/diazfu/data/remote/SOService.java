package com.skillcoders.diazfu.data.remote;

import com.skillcoders.diazfu.data.model.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jvier on 11/08/2017.
 */

public interface SOService {

    @GET("usuarios/usuario/{id}")
    Observable<Usuarios> getUsuario(@Path("id") long id);

    @GET("usuarios")
    Call<List<Usuarios>> getUsuarios();

    @POST("usuarios/login")
    Call<Usuarios> usuariosLogin(@Body Usuarios usuario);
}
