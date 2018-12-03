package ivms.sysware.com.ivmsmex.utils.delegates;

import ivms.sysware.com.ivmsmex.services.security.registerResponse;

public interface registerListener {
    void onRegisterSuccess(registerResponse response);
    void onRegisterFailed(String messageError);

}
