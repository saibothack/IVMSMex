package ivms.sysware.com.ivmsmex.services.backgroundThread;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import ivms.sysware.com.ivmsmex.activities.rastreo.RastreoActivity;

public class applicationService extends Service {

    private static boolean isRunning = false;
    private static final String TAG = "ivmsmex.RastreoService";

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        applicationService.isRunning = isRunning;
    }

    @Override
    public IBinder onBind(Intent intent) {

        Intent intents = new Intent(getBaseContext(),RastreoActivity.class);
        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intents);
        Toast.makeText(this, "Servicios de Rastreo Habilitados", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");
        applicationService.setIsRunning(true);
        return null;
    }
    public void onDestroy() {
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
        applicationService.setIsRunning(false);
    }

    @Override
    public void onStart(Intent intent, int startid)
    {
        Intent intents = new Intent(getBaseContext(),RastreoActivity.class);
        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intents);
        Toast.makeText(this, "Servicios de Rastreo Habilitados", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");
        applicationService.setIsRunning(true);

    }

    public static void startService(Context context){
        Intent intentApp = new Intent(context,applicationService.class);
        context.startService(intentApp);
        Log.i("Autostart", "started");
    }

}
