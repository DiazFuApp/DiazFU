package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Comisiones;

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

public interface ComisionesRest {

    @GET("comisiones")
    Call<List<Comisiones>> getComisiones();

    @GET("comisiones/{id}")
    Observable<Comisiones> getComision(@Path("id") long id);

    @POST("comisiones/agregar")
    Call<Comisiones> agregarComision(@Body Comisiones comision);

    @POST("comisiones/actualizar")
    Call<Comisiones> editarComision(@Body Comisiones comision);

    @POST("comisiones/eliminar")
    Call<Comisiones> eliminarComision(@Body Comisiones comision);
}
