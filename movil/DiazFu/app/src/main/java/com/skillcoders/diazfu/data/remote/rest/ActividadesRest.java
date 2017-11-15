package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Actividades;

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

public interface ActividadesRest {

    @GET("actividades")
    Call<List<Actividades>> getActividades();

    @GET("actividades/{id}")
    Observable<Actividades> getCliente(@Path("id") long id);

    @POST("actividades/agregar")
    Call<Actividades> agregarActividad(@Body Actividades actividad);

    @POST("actividades/actualizar")
    Call<Actividades> editarActividad(@Body Actividades actividad);

    @POST("actividades/eliminar")
    Call<Actividades> eliminarActividad(@Body Actividades actividad);
}
