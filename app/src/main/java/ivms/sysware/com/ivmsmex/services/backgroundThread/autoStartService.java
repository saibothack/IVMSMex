package ivms.sysware.com.ivmsmex.services.backgroundThread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class autoStartService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!applicationService.isIsRunning()){
            applicationService.startService(context);
        }
    }
}
