package ivms.sysware.com.ivmsmex.activities.rastreo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import butterknife.ButterKnife;
//import butterknife.InjectView;
import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.BaseActivity;
import ivms.sysware.com.ivmsmex.services.location.FallbackLocationTracker;
import ivms.sysware.com.ivmsmex.services.location.LocationTracker;

public class RastreoActivity extends BaseActivity {


    //@InjectView(R.id.txt_debugRastreo)
    TextView txt_debugRastreo;

    int lineas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastreo);
        //ButterKnife.inject(this);
        //redirect();
        initComponents();
        initEvents();
    }

    @Override
    public void initComponents(){

    }

    @Override
    public void initEvents(){
        intentaRastrear();
    }

    public void intentaRastrear(){
        FallbackLocationTracker locationTracker = new FallbackLocationTracker(this);
        locationTracker.start(new LocationTracker.LocationUpdateListener() {
            @Override
            public void onUpdate(Location oldLoc, long oldTime, Location newLoc, long newTime) {
                if (lineas > 19)
                {
                    txt_debugRastreo.setText("");
                    lineas=0;
                }
                if (oldLoc != null) {
                    txt_debugRastreo.setText(txt_debugRastreo.getText() + "Se actualiza anterior speed:" + oldLoc.getSpeed() + " lat " + oldLoc.getLatitude() + " long " + oldLoc.getLongitude() + "\n");
                    lineas++;
                }if (newLoc != null) {
                    txt_debugRastreo.setText(txt_debugRastreo.getText() + "Se actualiza anterior speed:" + newLoc.getSpeed() + " lat " + newLoc.getLatitude() + " long " + newLoc.getLongitude() + "\n");
                    lineas++;
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        intentaRastrear();
                    } else {
                        //requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_CODE);
                    }
                }
            }
        }
    }
}
