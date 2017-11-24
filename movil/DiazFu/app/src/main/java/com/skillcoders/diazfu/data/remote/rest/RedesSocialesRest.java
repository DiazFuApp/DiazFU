package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.RedesSociales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jvier on 04/09/2017.
 */

public interface RedesSocialesRest {

    @POST("redessociales/objeto")
    Observable<List<RedesSociales>> getRedesSociales(@Body RedesSociales redSocial);

    @POST("redessociales/agregar")
    Call<RedesSociales> agregarRedSocial(@Body RedesSociales redSocial);

    @POST("redessociales/actualizar")
    Call<RedesSociales> editarRedSocial(@Body RedesSociales pago);

    @POST("redessociales/eliminar")
    Call<RedesSociales> eliminarRedSocial(@Body RedesSociales pago);
}
