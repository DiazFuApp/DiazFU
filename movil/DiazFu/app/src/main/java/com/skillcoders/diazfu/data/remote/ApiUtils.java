package com.skillcoders.diazfu.data.remote;

/**
 * Created by jvier on 11/08/2017.
 */

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL = "http://diazfu-webapi.azurewebsites.net/api/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
