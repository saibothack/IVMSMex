package ivms.sysware.com.ivmsmex.fragments.tracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
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
    Button btnDelay;
    Button btnArrival;
    Button btnDelivery;
    Button btnHomecoming;

    RelativeLayout lyDelay;
    RelativeLayout lyArrival;
    RelativeLayout lyDelivery;
    RelativeLayout lyTracking;
    RelativeLayout lyHomecoming;

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

        lyDelay = frm.findViewById(R.id.lyDelay);
        lyArrival = frm.findViewById(R.id.lyArrival);
        lyDelivery = frm.findViewById(R.id.lyDelivery);
        lyTracking = frm.findViewById(R.id.lyTracking);
        lyHomecoming = frm.findViewById(R.id.lyHomecoming);

        btnTracking = frm.findViewById(R.id.btnTracking);
        btnDelay = frm.findViewById(R.id.btnDelay);
        btnArrival = frm.findViewById(R.id.btnArrival);
        btnDelivery = frm.findViewById(R.id.btnDelivery);
        btnHomecoming = frm.findViewById(R.id.btnHomecoming);

        btnTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTracking();
            }
        });

        btnDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delay();
            }
        });

        btnArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrival();
            }
        });

        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivery();
            }
        });

        btnHomecoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homecoming();
            }
        });

        lblTxtPlates.setText(sharedPreferences.getString(SharedPreferenceUtil.Key.platesVehicle));

        ForegroundLocation.setUpdateListener(this);
    }

    public void delay() {

    }

    public void arrival() {
        lyDelay.setVisibility(View.GONE);
        lyArrival.setVisibility(View.GONE);
        lyDelivery.setVisibility(View.VISIBLE);

        btnTracking.setText(R.string.start_tracking);
        Intent stopIntent = new Intent(navigationActivity, ForegroundLocation.class);
        stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        navigationActivity.startService(stopIntent);
    }

    public void delivery() {
        lyDelivery.setVisibility(View.GONE);
        lyHomecoming.setVisibility(View.VISIBLE);
    }

    public void homecoming() {
        lyHomecoming.setVisibility(View.GONE);
        lyTracking.setVisibility(View.VISIBLE);
    }

    private void initTracking() {
        lyTracking.setVisibility(View.GONE);

        lyDelay.setVisibility(View.VISIBLE);
        lyArrival.setVisibility(View.VISIBLE);

        btnTracking.setText(R.string.stop_tracking);
        Intent startIntent = new Intent(navigationActivity, ForegroundLocation.class);
        startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        navigationActivity.startService(startIntent);
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
