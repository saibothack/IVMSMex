package ivms.sysware.com.ivmsmex.utils.delegates;

public interface networkingListener {
    void onLoaded();
    void onError(String messageError);
    void onLoading(String loadingMessage);

}
