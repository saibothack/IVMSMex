package ivms.sysware.com.ivmsmex.utils.delegates;

import ivms.sysware.com.ivmsmex.services.security.passwordResponse;
import ivms.sysware.com.ivmsmex.services.security.registerResponse;

public interface passwordListener {
    void onPasswordSuccess(passwordResponse response);
    void onPasswordFailed(String messageError);


}
