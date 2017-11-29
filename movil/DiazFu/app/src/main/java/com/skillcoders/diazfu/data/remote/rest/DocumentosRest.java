package com.skillcoders.diazfu.data.remote.rest;

import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.model.RedesSociales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jvier on 04/09/2017.
 */

public interface DocumentosRest {

    @POST("documentos/objeto")
    Observable<List<Documentos>> getDocumentos(@Body Documentos documento);

    @POST("documentos/agregar")
    Call<Documentos> agregarDocumento(@Body Documentos documentos);

    @POST("documentos/actualizar")
    Call<Documentos> editarDocumento(@Body Documentos documentos);

    @POST("documentos/eliminar")
    Call<Documentos> eliminarDocumento(@Body Documentos documento);
}
