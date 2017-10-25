package com.skillcoders.diazfu.data.remote;

import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.UsuariosRest;

/**
 * Created by jvier on 11/08/2017.
 */

public class ApiUtils {

    private ApiUtils() {
    }

    //public static final String BASE_URL = "http://diazfu-webapi.azurewebsites.net/api/";
    public static final String BASE_URL = "http://192.168.1.71:8081/api/";

    public static UsuariosRest getUsuariosRest() {
        return RetrofitClient.getClient(BASE_URL).create(UsuariosRest.class);
    }

    public static PromotoresRest getPromotoresRest() {
        return RetrofitClient.getClient(BASE_URL).create(PromotoresRest.class);
    }

    public static ClientesRest getClientesRest() {
        return RetrofitClient.getClient(BASE_URL).create(ClientesRest.class);
    }

    public static GruposRest getGruposRest() {
        return RetrofitClient.getClient(BASE_URL).create(GruposRest.class);
    }

    public static ReferenciasPromotoresRest getReferenciasPromotoresRest() {
        return RetrofitClient.getClient(BASE_URL).create(ReferenciasPromotoresRest.class);
    }

    public static IntegrantesGruposRest getIntegrantesGruposRest() {
        return RetrofitClient.getClient(BASE_URL).create(IntegrantesGruposRest.class);
    }

    public static PrestamosGrupalesRest getPrestamosGrupalesRest() {
        return RetrofitClient.getClient(BASE_URL).create(PrestamosGrupalesRest.class);
    }
}
