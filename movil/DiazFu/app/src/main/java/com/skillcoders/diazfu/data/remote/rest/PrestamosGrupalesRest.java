package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.PrestamosGrupales;

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

public interface PrestamosGrupalesRest {

    @GET("prestamosgrupales")
    Call<List<PrestamosGrupales>> getPrestamosGrupales();

    @POST("prestamosgrupales/objeto")
    Call<List<PrestamosGrupales>> getPrestamosGrupales(@Body PrestamosGrupales prestamoGrupal);

    @GET("prestamosgrupales/{id}")
    Observable<PrestamosGrupales> getPrestamoGrupal(@Path("id") long id);

    @POST("prestamosgrupales/agregar")
    Call<PrestamosGrupales> agregarPrestamoGrupal(@Body PrestamosGrupales grupo);

    @POST("prestamosgrupales/actualizar")
    Call<PrestamosGrupales> editarPrestamoGrupal(@Body PrestamosGrupales grupo);

    @POST("prestamosgrupales/eliminar")
    Call<PrestamosGrupales> eliminarPrestamoGrupal(@Body PrestamosGrupales grupo);
}
