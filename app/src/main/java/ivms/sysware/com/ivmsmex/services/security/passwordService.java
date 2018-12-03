package ivms.sysware.com.ivmsmex.services.security;

import java.util.ArrayList;

import ivms.sysware.com.ivmsmex.utils.delegates.loginListener;
import ivms.sysware.com.ivmsmex.utils.delegates.networkingListener;
import ivms.sysware.com.ivmsmex.utils.delegates.passwordListener;
import ivms.sysware.com.ivmsmex.utils.delegates.passwordListener;

public class passwordService {
    private static ArrayList<passwordListener> listeners = new ArrayList<passwordListener>();
    private static passwordListener internalPasswordListener;

    private static networkingListener internalNetworkListener;
    private static networkingListener externalNetworkListener;


    public static void handleEvents(){


        setInternalPasswordListener(new passwordListener() {
            @Override
            public void onPasswordSuccess(passwordResponse response) {
                for (passwordListener pwd : listeners){
                    pwd.onPasswordSuccess(response);
                }
            }

            @Override
            public void onPasswordFailed(String messageError) {
                for (passwordListener pwd : listeners){
                    pwd.onPasswordFailed(messageError);
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

    public static void passwordProcess(String email, String password, String passwordConfirmation ){
        handleEvents();


    }



    public static void addEventListener(passwordListener listener){
        if (listeners == null)
            listeners = new ArrayList<passwordListener>();
        listeners.add(listener);
    }

    public static void removeEventListener(passwordListener  listener){
        listeners.remove(listener);
    }


    private static passwordListener getInternalPasswordListener() {
        return internalPasswordListener;
    }

    private static void setInternalPasswordListener(passwordListener internalPasswordListener) {
        passwordService.internalPasswordListener = internalPasswordListener;
    }

    private static networkingListener getInternalNetworkListener() {
        return internalNetworkListener;
    }

    private static void setInternalNetworkListener(networkingListener internalNetworkListener) {
        passwordService.internalNetworkListener = internalNetworkListener;
    }

    public static networkingListener getExternalNetworkListener() {
        return externalNetworkListener;
    }

    public static void setExternalNetworkListener(networkingListener externalNetworkListener) {
        passwordService.externalNetworkListener = externalNetworkListener;
    }



}
