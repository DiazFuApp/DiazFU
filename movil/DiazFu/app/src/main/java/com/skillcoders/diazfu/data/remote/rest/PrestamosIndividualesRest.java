package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.PrestamosIndividuales;

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

public interface PrestamosIndividualesRest {

    @GET("prestamosindividuales")
    Call<List<PrestamosIndividuales>> getPrestamosIndividuales();

    @GET("prestamosindividuales/{id}")
    Observable<PrestamosIndividuales> getPrestamosIndividuales(@Path("id") long id);

    @POST("prestamosindividuales/agregar")
    Call<PrestamosIndividuales> agregarPrestamoIndividual(@Body PrestamosIndividuales grupo);

    @POST("prestamosindividuales/actualizar")
    Call<PrestamosIndividuales> editarPrestamoIndividual(@Body PrestamosIndividuales grupo);

    @POST("prestamosindividuales/eliminar")
    Call<PrestamosIndividuales> eliminarPrestamoIndividual(@Body PrestamosIndividuales grupo);
}
