package ivms.sysware.com.ivmsmex.services.networking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ivms.sysware.com.ivmsmex.services.database.entities.configuration;
import ivms.sysware.com.ivmsmex.services.database.entities.incidence;
//import ivms.sysware.com.ivmsmex.services.rastreo.trackingResponse;
import ivms.sysware.com.ivmsmex.services.security.*;
import ivms.sysware.com.ivmsmex.utils.delegates.configListener;
import ivms.sysware.com.ivmsmex.utils.delegates.loginListener;
import ivms.sysware.com.ivmsmex.utils.delegates.networkingListener;
import ivms.sysware.com.ivmsmex.utils.delegates.passwordListener;
import ivms.sysware.com.ivmsmex.utils.delegates.registerListener;
import ivms.sysware.com.ivmsmex.utils.delegates.trackingServiceListener;
import ivms.sysware.com.ivmsmex.utils.enums;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;



public class ivmsDummyApi {


    public static void iniciarSesion(final loginRequest request, final networkingListener listener, final loginListener loginListener){
            //User
        listener.onLoading("Iniciando Sesion");
        final Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    User u = new User();
                    u.setUserId("1");
                    u.setNameUser("Josue Garcia");
                    u.setApplicationId("1");
                    u.setVehicle(new ArrayList<Vehicle>());
                    u.getVehicle().add(new Vehicle());
                    u.getVehicle().get(0).setVehicleId("1111");
                    u.getVehicle().get(0).setVehicleLocation("1111");
                    u.getVehicle().get(0).setVehicleModel("1111");
                    u.getVehicle().get(0).setVehiclePlates("1111");
                    u.getVehicle().get(0).setVehicleResponsible("1111");
                    u.getVehicle().get(0).setVehicleResponsibleEmail("okioki@hotmail.com");
                    u.getVehicle().get(0).setVehicleType("1111");
                    u.getVehicle().get(0).setVehicleZone("1111");

                    listener.onLoaded();
                    loginListener.onSuccesLogin(u);
                }catch(Exception ex){
                    ex.printStackTrace();
                    listener.onLoaded();
                    listener.onError(ex.getMessage());
                }
            }
        };

        final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        exec.schedule(new Runnable(){
            @Override
            public void run(){
                thread.run();
            }
        }, 15, TimeUnit.SECONDS);

    }


     public static void obtieneConfiguracion(final User request, final networkingListener listener, final configListener configListener){
         //List<configuration>
         listener.onLoading("Obteniendo Configuraciones");
         final Thread thread = new Thread(){
             @Override
             public void run(){
                 try {
                     this.wait(15000);
                     ArrayList<configuration> configs = new ArrayList<configuration>();
                     configuration config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.LIMITE_KM_ACELERACION);
                     config.setValor("100");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.LIMITE_KM_FRENADO);
                     config.setValor("100");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.LIMITE_MINUTOS_FATIGA);
                     config.setValor("100");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.LIMITE_SEGUNDOS_ACELERACION);
                     config.setValor("100");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.LIMITE_SEGUNDOS_FRENADO);
                     config.setValor("100");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.LIMITE_VELOCIDAD_MAXIMA);
                     config.setValor("100");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.LIMITE_VELOCIDAD_SOSTENIDA);
                     config.setValor("100");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.MILISEGUNDOS_LECTURA_GPS);
                     config.setValor("500");
                     configs.add(config);

                     config = new configuration();
                     config.setLastUpdate(new Date());
                     config.setTipoConfiguracion(enums.CatalogType.SEGUNDOS_ACTUALIZACION_ESTATUS);
                     config.setValor("10");
                     configs.add(config);

                     listener.onLoaded();

                     configListener.onConfigLoadedWS(configs);

                 }catch(Exception ex){
                     ex.printStackTrace();
                 }
             }
         };
         thread.run();
    }


    /*public static void movimientoRastreo(final ArrayList<incidence> incidencias, final networkingListener listener, final trackingServiceListener trackingListener){
        //trackingResponse


        listener.onLoading("Guardando rastreo");
        final Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    this.wait(5000);



                    trackingResponse response = new trackingResponse();
                    response.setMensaje("Carga exitosa");
                    response.setCargaExitosa(true);
                    response.setCargaCatalogo(Math.random() < 0.5);
                    listener.onLoaded();
                    trackingListener.onTrackingResponse(response);


                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };

    }*/


    public static void registraUsuario(final registerRequest request, final networkingListener listener, final registerListener regListener){
        //Call<registerResponse>
        listener.onLoading("registrando usuario");
        final Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    this.wait(5000);
                    registerResponse response = new registerResponse();
                    response.setMessage("Registro exitoso");
                    response.setSucccess(true);
                    listener.onLoaded();
                    regListener.onRegisterSuccess(response);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.run();

    }


    public static void  recuperaPassword(final passwordRequest request, final networkingListener listener, final passwordListener pwdListener ){
        //Call<passwordResponse>
        listener.onLoading("recuperando contraseña");
        final Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    this.wait(10000);
                    passwordResponse response = new passwordResponse();
                    response.setMessage("Recuperacion exitosa");
                    response.setSucccess(true);
                    listener.onLoaded();
                    pwdListener.onPasswordSuccess(response);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        thread.run();
    }
}
