package ivms.sysware.com.ivmsmex.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.fragments.tracking.TrackingFragment;
import ivms.sysware.com.ivmsmex.services.location.FallbackLocationTracker;
import ivms.sysware.com.ivmsmex.services.location.LocationTracker;
import ivms.sysware.com.ivmsmex.utils.Constants;

public class ForegroundLocation extends Service {
    private static final String LOG_TAG = "ForegroundService";
    private Timer timer = new Timer();
    private static final long updateInterval = 1000;
    public static TrackingFragment updateListener;
    private Handler handler;

    private int chronometer;
    private String sAddress = "";
    private String sSpeed = "";
    private String sTime = "";
    int lineas=0;

    public static void setUpdateListener(TrackingFragment trackingFragment) {
        updateListener = trackingFragment;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        updateUI();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Intent notificationIntent = new Intent(this, NavigationActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle(getResources().getString(R.string.app_name))
                    .setContentText(getResources().getString(R.string.in_tracking))
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true).build();

            startForeground(101, notification);
            startChronometer();

        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
            stopChronometer();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        return null;
    }

    private void startChronometer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                chronometer += 1;
                handler.sendEmptyMessage(0);
                sTime = formatTime(chronometer);
            }
        }, 0, updateInterval);
        starTracking();
    }

    private void stopChronometer() {
        if (timer != null)
            timer.cancel();
    }

    private void updateUI() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                updateListener.updateUI(sAddress, sSpeed, sTime);
            }
        };
    }

    private String formatTime(int time) {
        int hour = (time / 3600);
        int minute = ((time - (hour * 3600)) / 60);
        int seconds = (time - ((hour * 3600) + (minute * 60)));

        return String.valueOf((hour != 0) ? ((hour > 9) ? hour : ("0" + String.valueOf(hour)))  : "00") + ":" +
                String.valueOf((minute != 0) ? ((minute > 9) ? minute : ("0" + String.valueOf(minute)))  : "00") + ":" +
                String.valueOf((seconds != 0) ? ((seconds > 9) ? seconds : ("0" + String.valueOf(seconds)))  : "00") + " h";
    }

    public void starTracking(){
        FallbackLocationTracker locationTracker = new FallbackLocationTracker(updateListener.getActivity());
        locationTracker.start(new LocationTracker.LocationUpdateListener() {
            @Override
            public void onUpdate(Location oldLoc, long oldTime, Location newLoc, long newTime) {
                if (lineas > 19)
                {
                    sSpeed = "0 K/h";
                    lineas=0;
                }
                if (oldLoc != null) {
                    sSpeed = String.valueOf(oldLoc.getSpeed()) + " K/h";
                    //txt_debugRastreo.setText(txt_debugRastreo.getText() + "Se actualiza anterior speed:" + oldLoc.getSpeed() + " lat " + oldLoc.getLatitude() + " long " + oldLoc.getLongitude() + "\n");
                    lineas++;
                }if (newLoc != null) {
                    sSpeed = String.valueOf(newLoc.getSpeed()) + " K/h";
                    //txt_debugRastreo.setText(txt_debugRastreo.getText() + "Se actualiza anterior speed:" + newLoc.getSpeed() + " lat " + newLoc.getLatitude() + " long " + newLoc.getLongitude() + "\n");
                    lineas++;
                }

            }
        });
    }

}
