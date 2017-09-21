package com.arbp.richard.proyectomunicipios;

import com.arbp.richard.proyectomunicipios.model.DatosRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DatosGobBoService {

    @GET("action/datastore_search")
    Call<DatosRespuesta> municipiosDepartamento(@Query("resource_id") String resourceId, @Query("q") String query);

}
