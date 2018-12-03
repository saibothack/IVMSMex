package ivms.sysware.com.ivmsmex.services.networking;

import java.util.ArrayList;
import java.util.List;

import ivms.sysware.com.ivmsmex.services.database.entities.configuration;
import ivms.sysware.com.ivmsmex.services.database.entities.incidence;
//import ivms.sysware.com.ivmsmex.services.rastreo.trackingResponse;
import ivms.sysware.com.ivmsmex.services.security.User;
import ivms.sysware.com.ivmsmex.services.security.loginRequest;
import ivms.sysware.com.ivmsmex.services.security.passwordRequest;
import ivms.sysware.com.ivmsmex.services.security.passwordResponse;
import ivms.sysware.com.ivmsmex.services.security.registerRequest;
import ivms.sysware.com.ivmsmex.services.security.registerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ivmsAPI {
    String BASE_URL="http://www.sysware.com.mx/ivms/api/";

    @POST("login")
    Call<User> iniciarSesion(@Body loginRequest request);

    @POST("config")
    Call<List<configuration>> obtieneConfiguracion(@Body User request);

   // @POST("tracking")
   // Call<trackingResponse> movimientoRastreo(@Body ArrayList<incidence> incidencias);

    @POST("register")
    Call<registerResponse> registraUsuario(@Body registerRequest request);

    @POST("password")
    Call<passwordResponse> recuperaPassword(@Body passwordRequest request);

}
