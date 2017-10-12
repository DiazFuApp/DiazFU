package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Promotores;

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

public interface PromotoresRest {

    @GET("promotores")
    Call<List<Promotores>> getPromotores();

    @GET("promotores/{id}")
    Observable<Promotores> getPromotor(@Path("id") long id);

    @POST("promotores/agregar")
    Call<Promotores> agregarPromotor(@Body Promotores promotor);
}
