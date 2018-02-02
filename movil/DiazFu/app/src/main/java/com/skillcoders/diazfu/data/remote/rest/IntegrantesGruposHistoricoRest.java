package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.IntegrantesGruposHistorico;

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

public interface IntegrantesGruposHistoricoRest {

    @GET("integrantesgruposhistorico")
    Call<List<IntegrantesGruposHistorico>> getIntegrantesGrupos();

    @GET("integrantesgruposhistorico/{id}")
    Observable<List<IntegrantesGruposHistorico>> getIntegrantesGrupo(@Path("id") long id);

    @POST("integrantesgruposhistorico/agregar")
    Call<IntegrantesGruposHistorico> agregarIntegrante(@Body IntegrantesGruposHistorico integranteGrupo);

    @POST("integrantesgruposhistorico/actualizar")
    Call<IntegrantesGruposHistorico> editarIntegrante(@Body IntegrantesGruposHistorico integranteGrupo);

    @POST("integrantesgruposhistorico/eliminar")
    Call<IntegrantesGruposHistorico> eliminarIntegrante(@Body IntegrantesGruposHistorico integranteGrupo);
}
