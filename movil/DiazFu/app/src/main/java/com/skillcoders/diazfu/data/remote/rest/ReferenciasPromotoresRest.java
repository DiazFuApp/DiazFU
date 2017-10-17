package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jvier on 04/09/2017.
 */

public interface ReferenciasPromotoresRest {

    @GET("referenciaspromotores")
    Call<List<ReferenciasPromotores>> getReferenciasPromotores();

    @POST("referenciaspromotores/objeto")
    Observable<ReferenciasPromotores> getReferenciaPromotor(@Body ReferenciasPromotores referenciasPromotores);

    @POST("referenciaspromotores/agregar")
    Call<ReferenciasPromotores> agregarReferenciaPromotor(@Body ReferenciasPromotores referenciaPromotor);

    @POST("referenciaspromotores/actualizar")
    Call<ReferenciasPromotores> editarReferenciaPromotor(@Body ReferenciasPromotores referenciaPromotor);

    @POST("referenciaspromotores/eliminar")
    Call<ReferenciasPromotores> eliminarPromotor(@Body Promotores promotor);
}
