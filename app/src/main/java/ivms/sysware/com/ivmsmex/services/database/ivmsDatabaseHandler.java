package ivms.sysware.com.ivmsmex.services.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import ivms.sysware.com.ivmsmex.services.database.entities.configuration;
import ivms.sysware.com.ivmsmex.utils.delegates.configListener;
import ivms.sysware.com.ivmsmex.utils.ivmsApplication;

public class ivmsDatabaseHandler {
    public static void guardaDatosConfiguracion(final configuration config, final configListener configListener ){
        final Thread thread = new Thread() {
            @Override
            public void run() {
                ivmsDatabase.getAppDatabase(ivmsApplication.getAppContext()).ivmsDAO().guardaConfiguracion(config);
                final LiveData<configuration> configObserver = ivmsDatabase.getAppDatabase(ivmsApplication.getAppContext()).ivmsDAO().obtieneConfiguracion(config.getTipoConfiguracion());
                configObserver.observeForever(new Observer<configuration>() {
                    @Override
                    public void onChanged(@Nullable configuration configuration) {
                        if (configuration != null)
                            configListener.onConfigSavedBD(config);

                        configObserver.removeObserver(this);
                    }
                });

            }
        };

        thread.start();
    }
}
