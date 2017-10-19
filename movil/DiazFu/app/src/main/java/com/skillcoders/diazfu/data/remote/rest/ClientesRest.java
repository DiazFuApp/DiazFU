package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Clientes;

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

public interface ClientesRest {

    @GET("clientes")
    Call<List<Clientes>> getClientes();

    @GET("clientes/{id}")
    Observable<Clientes> getCliente(@Path("id") long id);

    @POST("clientes/agregar")
    Call<Clientes> agregarCliente(@Body Clientes cliente);

    @POST("clientes/actualizar")
    Call<Clientes> editarCliente(@Body Clientes cliente);

    @POST("clientes/eliminar")
    Call<Clientes> eliminarCliente(@Body Clientes cliente);
}
