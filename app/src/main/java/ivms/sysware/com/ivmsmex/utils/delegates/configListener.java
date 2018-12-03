package ivms.sysware.com.ivmsmex.utils.delegates;

import java.util.List;

import ivms.sysware.com.ivmsmex.services.database.entities.configuration;

public interface configListener {
    void onConfigLoadedWS(List<configuration> configurations);
    void onConfigLoadedBD(List<configuration> configurations);
    void onConfigFailed(String message);
    void onConfigSavedBD(configuration config);
    String configListenerId();
}
