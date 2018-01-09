package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.GruposHistorico;

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

public interface GruposHistoricoRest {

    @GET("gruposhistorico")
    Call<List<GruposHistorico>> getGrupos();

    @GET("gruposhistorico/{id}")
    Observable<GruposHistorico> getGrupo(@Path("id") long id);

    @POST("gruposhistorico/agregar")
    Call<GruposHistorico> agregarGrupo(@Body GruposHistorico grupo);

    @POST("gruposhistorico/actualizar")
    Call<GruposHistorico> editarGrupo(@Body GruposHistorico grupo);

    @POST("gruposhistorico/eliminar")
    Call<GruposHistorico> eliminarGrupo(@Body GruposHistorico grupo);
}
