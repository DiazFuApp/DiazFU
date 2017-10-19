package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Grupos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jvier on 26/09/2017.
 */

public interface GruposRest {

    @GET("grupos")
    Call<List<Grupos>> getGrupos();

    @GET("grupos/{id}")
    Observable<Grupos> getGrupo(@Path("id") long id);

    @POST("grupos/agregar")
    Call<Grupos> agregarGrupo(@Body Grupos grupo);

    @POST("grupos/actualizar")
    Call<Grupos> editarGrupo(@Body Grupos grupo);

    @POST("grupos/eliminar")
    Call<Grupos> eliminarGrupo(@Body Grupos grupo);
}
