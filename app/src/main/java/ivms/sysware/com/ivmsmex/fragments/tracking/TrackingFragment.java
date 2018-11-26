package ivms.sysware.com.ivmsmex.fragments.tracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.services.ForegroundLocation;
import ivms.sysware.com.ivmsmex.utils.Constants;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;


public class TrackingFragment extends Fragment {
    private SharedPreferenceUtil sharedPreferences;

    NavigationActivity navigationActivity;
    TextView lblTxtPlates;
    TextView lblTxtAddress;
    TextView lblTxtSpeed;
    TextView lblTxtTime;
    TextView lblTxtIncidents;

    Button btnTracking;
    Button btnEmergency;

    public TrackingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_tracking, container, false);
        initComponents(fragment);
        return fragment;

    }

    private void initComponents(View frm) {
        navigationActivity = (NavigationActivity) getActivity();
        sharedPreferences = navigationActivity.sharedPreferences;
        lblTxtPlates = frm.findViewById(R.id.lblTxtPlates);
        lblTxtAddress = frm.findViewById(R.id.lblTxtAddress);
        lblTxtSpeed = frm.findViewById(R.id.lblTxtSpeed);
        lblTxtTime = frm.findViewById(R.id.lblTxtTime);
        lblTxtIncidents = frm.findViewById(R.id.lblTxtIncidents);

        btnTracking = frm.findViewById(R.id.btnTracking);
        btnTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTracking();
            }
        });

        btnEmergency = frm.findViewById(R.id.btnEmergency);
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        "Toast por defecto", Toast.LENGTH_SHORT).show();
            }
        });

        lblTxtPlates.setText(sharedPreferences.getString(SharedPreferenceUtil.Key.platesVehicle));

        ForegroundLocation.setUpdateListener(this);
    }

    private void initTracking() {
        switch (btnTracking.getText().toString()) {
            case "Iniciar Rastreo":
                btnTracking.setText(R.string.stop_tracking);
                Intent startIntent = new Intent(navigationActivity, ForegroundLocation.class);
                startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                navigationActivity.startService(startIntent);
                break;
            case "Detener Rastreo":
                btnTracking.setText(R.string.start_tracking);
                Intent stopIntent = new Intent(navigationActivity, ForegroundLocation.class);
                stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                navigationActivity.startService(stopIntent);
                break;

            default:
                break;
        }
    }

    public void updateUI(String sAddress, String sSpeed, String sTime)  {
        btnTracking.setText(R.string.stop_tracking);
        lblTxtAddress.setText(sAddress);
        lblTxtSpeed.setText(sSpeed);
        lblTxtTime.setText(sTime);
    }

    public void updateIncidentsUI(String sIncidents)  {
        lblTxtIncidents.setText(sIncidents);
    }

}
