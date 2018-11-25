package ivms.sysware.com.ivmsmex.fragments.tracking;

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
                Toast.makeText(getContext(),
                        "Toast por defecto", Toast.LENGTH_SHORT).show();
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

    }
}
