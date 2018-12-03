package ivms.sysware.com.ivmsmex.utils.delegates;

import ivms.sysware.com.ivmsmex.services.security.User;

public interface loginListener {
    void onSuccesLogin(User user);
    void onLoginFailed(String errorMessage);

}
