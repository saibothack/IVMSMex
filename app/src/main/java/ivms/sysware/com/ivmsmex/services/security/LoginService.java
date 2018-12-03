package ivms.sysware.com.ivmsmex.services.security;

import java.util.ArrayList;

import com.google.gson.Gson;
import ivms.sysware.com.ivmsmex.services.configuration.configService;
import ivms.sysware.com.ivmsmex.services.networking.ivmsDummyApi;
import ivms.sysware.com.ivmsmex.services.networking.ivmsNetworking;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;
import ivms.sysware.com.ivmsmex.utils.delegates.configListener;
import ivms.sysware.com.ivmsmex.utils.delegates.loginListener;
import ivms.sysware.com.ivmsmex.utils.delegates.networkingListener;
import ivms.sysware.com.ivmsmex.utils.ivmsApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private static ArrayList<loginListener> listeners = new ArrayList<loginListener>();
    private static loginListener internalLoginListener;

    private static networkingListener internalNetworkListener;
    private static networkingListener externalNetworkListener;



    private static void handleEvents(){
        setInternalLoginListener(new loginListener() {
            @Override
            public void onSuccesLogin(User user) {
                User.setSessionUser(user);
                Gson g = new Gson();
                String data= g.toJson(user);
                ivmsApplication.getSharedPreference().put(SharedPreferenceUtil.Key.USER_DATA, data);
                for (loginListener login : listeners){
                    login.onSuccesLogin(user);
                }


            }

            @Override
            public void onLoginFailed(String errorMessage) {
                for (loginListener login : listeners){
                    login.onLoginFailed(errorMessage);
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

    public static void doProcessLogin(String username, String Password){
        handleEvents();
        loginRequest request = new loginRequest();
        request.setUsername(username);
        request.setPassword(Password);
        if (configService.getIsDummy()){
            ivmsDummyApi.iniciarSesion(request, internalNetworkListener, internalLoginListener);
            return;
        }
        internalNetworkListener.onLoading("Iniciando Sesión, espere por favor");
        ivmsNetworking.getWsConnection().iniciarSesion(request).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                internalNetworkListener.onLoaded();
                internalLoginListener.onSuccesLogin(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                internalNetworkListener.onError(t.getMessage());
                internalLoginListener.onLoginFailed(t.getMessage());
            }
        });
    }






    public static void addEventListener(loginListener listener){
        if (listeners == null)
            listeners = new ArrayList<loginListener>();
        listeners.add(listener);
    }

    public static void removeEventListener(loginListener  listener){
        listeners.remove(listener);
    }


    private static loginListener getInternalLoginListener() {
        return internalLoginListener;
    }

    private static void setInternalLoginListener(loginListener internalLoginListener) {
        LoginService.internalLoginListener = internalLoginListener;
    }

    private static networkingListener getInternalNetworkListener() {
        return internalNetworkListener;
    }

    private static void setInternalNetworkListener(networkingListener internalNetworkListener) {
        LoginService.internalNetworkListener = internalNetworkListener;
    }

    public static networkingListener getExternalNetworkListener() {
        return externalNetworkListener;
    }

    public static void setExternalNetworkListener(networkingListener externalNetworkListener) {
        LoginService.externalNetworkListener = externalNetworkListener;
    }
}
