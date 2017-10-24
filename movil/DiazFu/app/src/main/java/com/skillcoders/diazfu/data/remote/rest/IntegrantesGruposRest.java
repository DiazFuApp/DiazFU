package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;

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

public interface IntegrantesGruposRest {

    @GET("integrantesgrupos")
    Call<List<IntegrantesGrupos>> getIntegrantesGrupos();

    @GET("integrantesgrupos/{id}")
    Observable<List<IntegrantesGrupos>> getIntegrantesGrupo(@Path("id") long id);

    @POST("integrantesgrupos/agregar")
    Call<IntegrantesGrupos> agregarIntegrante(@Body IntegrantesGrupos integranteGrupo);

    @POST("integrantesgrupos/actualizar")
    Call<IntegrantesGrupos> editarIntegrante(@Body IntegrantesGrupos integranteGrupo);

    @POST("integrantesgrupos/eliminar")
    Call<IntegrantesGrupos> eliminarIntegrante(@Body IntegrantesGrupos integranteGrupo);
}
