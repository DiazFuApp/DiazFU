package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jvier on 04/09/2017.
 */

public interface PagosRest {

    @GET("pagos")
    Call<List<Pagos>> getPagos();

    @POST("pagos/objeto")
    Observable<List<Pagos>> getPago(@Body Pagos pago);

    @POST("pagos/proximospagos")
    Observable<List<Pagos>> getProximosPagos(@Body Pagos pago);

    @POST("pagos/agregar")
    Call<Pagos> agregarPago(@Body Pagos pago);

    @POST("pagos/actualizar")
    Call<Pagos> editarPago(@Body Pagos pago);

    @POST("pagos/eliminar")
    Call<Pagos> eliminarPago(@Body Pagos pago);
}
