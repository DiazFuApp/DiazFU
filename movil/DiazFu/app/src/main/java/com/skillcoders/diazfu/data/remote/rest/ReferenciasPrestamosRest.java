package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jvier on 04/09/2017.
 */

public interface ReferenciasPrestamosRest {

    @GET("referenciasprestamos")
    Call<List<ReferenciasPrestamos>> getReferenciasPrestamos();

    @POST("referenciasprestamos/objeto")
    Observable<List<ReferenciasPrestamos>> getReferenciaPrestamos(@Body ReferenciasPrestamos referencia);

    @POST("referenciasprestamos/agregar")
    Call<ReferenciasPrestamos> agregarReferenciaPrestamo(@Body ReferenciasPrestamos referencia);

    @POST("referenciasprestamos/actualizar")
    Call<ReferenciasPrestamos> editarReferenciaPrestamo(@Body ReferenciasPrestamos referencia);

    @POST("referenciasprestamos/eliminar")
    Call<ReferenciasPrestamos> eliminarReferenciaPrestamo(@Body Promotores promotor);
}
