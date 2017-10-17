package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Grupos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jvier on 26/09/2017.
 */

public interface GruposRest {

    @GET("grupos")
    Call<List<Grupos>> getGrupos();

    @GET("grupos/{id}")
    Observable<Grupos> getGrupo(@Path("id") long id);
}
