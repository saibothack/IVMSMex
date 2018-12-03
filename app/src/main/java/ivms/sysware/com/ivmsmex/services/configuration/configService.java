package ivms.sysware.com.ivmsmex.services.configuration;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ivms.sysware.com.ivmsmex.services.database.entities.configuration;
import ivms.sysware.com.ivmsmex.services.database.ivmsDatabase;
import ivms.sysware.com.ivmsmex.services.database.ivmsDatabaseHandler;
import ivms.sysware.com.ivmsmex.services.networking.ivmsDummyApi;
import ivms.sysware.com.ivmsmex.services.networking.ivmsNetworking;

import ivms.sysware.com.ivmsmex.services.security.User;
import ivms.sysware.com.ivmsmex.utils.delegates.configListener;

import ivms.sysware.com.ivmsmex.utils.delegates.networkingListener;

import ivms.sysware.com.ivmsmex.utils.ivmsApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class configService {

    private static networkingListener internalNetworkListener;
    private static configListener internalConfigListener;

    private static networkingListener externalNetworkListener;
    private static ArrayList<configListener> listeners = new ArrayList<configListener>();

    private static Boolean isDummy=true;



    private static long limiteVelocidadMaxima=0;
    private static long limiteVelocidadSostenida=0;
    private static long limiteKmAceleracion=0;
    private static long limiteSegundosAceleracion=0;
    private static long limiteKmFrenado=0;
    private static long limiteSegundosFrenado=0;
    private static long limiteMinutosFatiga=0;
    private static long segundosActualizacionEstatus=0;
    private static long milisegundosLecturaGPS=0;


    public static Boolean getIsDummy(){
        return isDummy;
    }

    public static long getLimiteVelocidadMaxima(){
        return limiteVelocidadMaxima;
    }
    public static long getLimiteVelocidadSostenida(){
        return limiteVelocidadSostenida;
    }
    public static long getLimiteKmAceleracion(){
        return limiteKmAceleracion;
    }
    public static long getLimiteSegundosAceleracion(){
        return limiteSegundosAceleracion;
    }
    public static long getLimiteKmFrenado(){
        return limiteKmFrenado;
    }
    public static long getLimiteSegundosFrenado(){
        return limiteSegundosFrenado;
    }
    public static long getLimiteMinutosFatiga(){
        return limiteMinutosFatiga;
    }
    public static long getSegundosActualizacionEstatus(){
        return segundosActualizacionEstatus;
    }
    public static long getMilisegundosLecturaGPS(){
        return milisegundosLecturaGPS;
    }


    private static void setLimiteVelocidadMaxima(long value){
        limiteVelocidadMaxima=value;
    }
    private static void setLimiteVelocidadSostenida(long value){
        limiteVelocidadSostenida=value;
    }
    private static void setLimiteKmAceleracion(long value){
        limiteKmAceleracion=value;
    }
    private static void setLimiteSegundosAceleracion(long value){
        limiteSegundosAceleracion=value;
    }
    private static void setLimiteKmFrenado(long value){
        limiteKmFrenado=value;
    }
    private static void setLimiteSegundosFrenado(long value){
        limiteSegundosFrenado=value;
    }
    private static void setLimiteMinutosFatiga(long value){
        limiteMinutosFatiga=value;
    }
    private static void setSegundosActualizacionEstatus(long value){
        segundosActualizacionEstatus=value;
    }
    private static void setMilisegundosLecturaGPS(long value){
        milisegundosLecturaGPS=value;
    }


    public void loadConfigfromWS(){
        handleListeners();

        if (configService.getIsDummy()){
            ivmsDummyApi.obtieneConfiguracion(User.getSessionUser(), internalNetworkListener, internalConfigListener);
            return;
        }
        if (internalNetworkListener != null)
            internalNetworkListener.onLoading("Cargando configuraciones espere por favor");

        ivmsNetworking.getWsConnection().obtieneConfiguracion(User.getSessionUser()).enqueue(new Callback<List<configuration>>() {
            @Override
            public void onResponse(Call<List<configuration>> call, Response<List<configuration>> response) {
                if (internalNetworkListener != null)
                    internalNetworkListener.onLoaded();

                internalConfigListener.onConfigLoadedWS(response.body());

            }

            @Override
            public void onFailure(Call<List<configuration>> call, Throwable t) {
                if (internalNetworkListener != null)
                    internalNetworkListener.onError(t.getMessage());

                internalConfigListener.onConfigFailed(t.getMessage());

            }
        });


    }


    public static void getConfigFromDatabase(){
        handleListeners();
        final LiveData<List<configuration>> configurationLiveData = ivmsDatabase.getAppDatabase(ivmsApplication.getAppContext()).ivmsDAO().obtieneConfiguraciones();
        configurationLiveData.observeForever(new Observer<List<configuration>>() {
            @Override
            public void onChanged(@Nullable List<configuration> configurations) {
                for (configuration config:configurations){
                    bindProperties(config);
                }

                configurationLiveData.removeObserver(this);
            }
        });


    }

    private static void bindProperties(configuration config){
        switch(config.getTipoConfiguracion()){
            case LIMITE_KM_ACELERACION:
                setLimiteKmAceleracion(Long.parseLong(config.getValor()));
                break;
            case LIMITE_KM_FRENADO:
                setLimiteKmFrenado(Long.parseLong(config.getValor()));
                break;
            case LIMITE_MINUTOS_FATIGA:
                setLimiteMinutosFatiga(Long.parseLong(config.getValor()));
                break;
            case LIMITE_SEGUNDOS_ACELERACION:
                setLimiteSegundosAceleracion(Long.parseLong(config.getValor()));
                break;
            case LIMITE_SEGUNDOS_FRENADO:
                setLimiteSegundosFrenado(Long.parseLong(config.getValor()));
                break;
            case LIMITE_VELOCIDAD_MAXIMA:
                setLimiteVelocidadMaxima(Long.parseLong(config.getValor()));
                break;
            case LIMITE_VELOCIDAD_SOSTENIDA:
                setLimiteVelocidadSostenida(Long.parseLong(config.getValor()));
                break;
            case MILISEGUNDOS_LECTURA_GPS:
                setMilisegundosLecturaGPS(Long.parseLong(config.getValor()));
                break;
            case SEGUNDOS_ACTUALIZACION_ESTATUS:
                setSegundosActualizacionEstatus(Long.parseLong(config.getValor()));
                break;

        }
    }



    public static void handleListeners(){
        setInternalListener(new configListener() {
            @Override
            public void onConfigLoadedWS(List<configuration> configurations) {

                for (configuration config:configurations){
                    ivmsDatabaseHandler.guardaDatosConfiguracion(config, internalConfigListener);
                }

                for (configListener cListener : listeners ){
                    cListener.onConfigLoadedWS(configurations);
                }

            }

            @Override
            public void onConfigLoadedBD(List<configuration> configurations) {
                for (configListener cListener : listeners ){
                    cListener.onConfigLoadedBD(configurations);
                }
            }

            @Override
            public void onConfigFailed(String message) {
                getConfigFromDatabase();
                for (configListener cListener : listeners ){
                    cListener.onConfigFailed(message);
                }
            }

            @Override
            public void onConfigSavedBD(configuration config) {
                bindProperties(config);
                for (configListener cListener : listeners ){
                    cListener.onConfigSavedBD(config);
                }
            }


            @Override
            public String configListenerId() {
                return "coreListener";
            }
        });
        setInternalNetworkListener(new networkingListener() {
            @Override
            public void onLoaded() {
                if (externalNetworkListener != null)
                    externalNetworkListener.onLoaded();
            }

            @Override
            public void onError(String messageError) {
                if (externalNetworkListener != null)
                    externalNetworkListener.onError(messageError);
            }

            @Override
            public void onLoading(String loadingMessage) {
                if (externalNetworkListener != null)
                    externalNetworkListener.onLoading(loadingMessage);

            }
        });
    }


    private static void setInternalListener(configListener listener){

        internalConfigListener=listener;
    }

    private static void setInternalNetworkListener(networkingListener listener){
        internalNetworkListener = listener;
    }

    public static void addEventListener(configListener listener){
        if (listeners == null)
            listeners = new ArrayList<configListener>();
        listeners.add(listener);
    }

    public static void removeEventListener(configListener listener){
        listeners.remove(listener);
    }


}
