package ivms.sysware.com.ivmsmex.services.security;

import java.util.ArrayList;

import ivms.sysware.com.ivmsmex.services.configuration.configService;
import ivms.sysware.com.ivmsmex.services.networking.ivmsDummyApi;
import ivms.sysware.com.ivmsmex.services.networking.ivmsNetworking;
import ivms.sysware.com.ivmsmex.utils.delegates.loginListener;
import ivms.sysware.com.ivmsmex.utils.delegates.networkingListener;
import ivms.sysware.com.ivmsmex.utils.delegates.registerListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerService {
    private static ArrayList<registerListener> listeners = new ArrayList<registerListener>();
    private static registerListener internalRegisterListener;

    private static networkingListener internalNetworkListener;
    private static networkingListener externalNetworkListener;


    public static void handleEvents(){

        setInternalRegisterListener(new registerListener() {
            @Override
            public void onRegisterSuccess(registerResponse response) {
                for (registerListener reg:listeners){
                    reg.onRegisterSuccess(response);
                }
            }

            @Override
            public void onRegisterFailed(String messageError) {
                for (registerListener reg:listeners){
                    reg.onRegisterFailed(messageError);
                }
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


    public static void registerProcess(String username, String FullName, String SurName, String email, String cellPhone){
        handleEvents();
        registerRequest request = new registerRequest();
        request.setEmail(email);
        request.setName(FullName);
        request.setUsername(username);
        request.setCellNumber(cellPhone);
        request.setSurname(SurName);

        if (configService.getIsDummy()){
            ivmsDummyApi.registraUsuario(request,internalNetworkListener,internalRegisterListener);
            return;
        }
        internalNetworkListener.onLoading("Enviando datos, espere por favor");
        ivmsNetworking.getWsConnection().registraUsuario(request).enqueue(new Callback<registerResponse>() {
            @Override
            public void onResponse(Call<registerResponse> call, Response<registerResponse> response) {
                internalNetworkListener.onLoaded();
                internalRegisterListener.onRegisterSuccess(response.body());
            }

            @Override
            public void onFailure(Call<registerResponse> call, Throwable t) {
                internalNetworkListener.onError(t.getMessage());
                internalRegisterListener.onRegisterFailed(t.getMessage());
            }
        });


    }


    public static void addEventListener(registerListener listener){
        if (listeners == null)
            listeners = new ArrayList<registerListener>();
        listeners.add(listener);
    }

    public static void removeEventListener(registerListener  listener){
        listeners.remove(listener);
    }

    private static registerListener getInternalRegisterListener() {
        return internalRegisterListener;
    }

    private static void setInternalRegisterListener(registerListener internalRegisterListener) {
        registerService.internalRegisterListener = internalRegisterListener;
    }

    private static networkingListener getInternalNetworkListener() {
        return internalNetworkListener;
    }

    private static void setInternalNetworkListener(networkingListener internalNetworkListener) {
        registerService.internalNetworkListener = internalNetworkListener;
    }

    public static networkingListener getExternalNetworkListener() {
        return externalNetworkListener;
    }

    public static void setExternalNetworkListener(networkingListener externalNetworkListener) {
        registerService.externalNetworkListener = externalNetworkListener;
    }

}
