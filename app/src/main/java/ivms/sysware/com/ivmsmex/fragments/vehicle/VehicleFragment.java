package ivms.sysware.com.ivmsmex.fragments.vehicle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.database.vehicle.VehicleHandler;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;


public class VehicleFragment extends Fragment {
    private SharedPreferenceUtil sharedPreferences;
    NavigationActivity navigationActivity;

    TextView lblTxtTypeVehicle;
    TextView lblTxtPlates;
    TextView lblTxtModel;
    TextView lblTxtResponsible;
    TextView lblTxtResponsibleEmail;
    TextView lblTxtZone;
    TextView lblTxtLocation;

    public VehicleFragment() {
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
        View fragment = inflater.inflate(R.layout.fragment_vehicle, container, false);
        initComponents(fragment);
        return fragment;
    }

    private void initComponents(View frm) {
        navigationActivity = (NavigationActivity) getActivity();
        sharedPreferences = navigationActivity.sharedPreferences;

        lblTxtTypeVehicle = frm.findViewById(R.id.lblTxtTypeVehicle);
        lblTxtPlates = frm.findViewById(R.id.lblTxtPlates);
        lblTxtModel = frm.findViewById(R.id.lblTxtModel);
        lblTxtResponsible = frm.findViewById(R.id.lblTxtResponsible);
        lblTxtResponsibleEmail = frm.findViewById(R.id.lblTxtResponsibleEmail);
        lblTxtZone = frm.findViewById(R.id.lblTxtZone);
        lblTxtLocation = frm.findViewById(R.id.lblTxtLocation);

        VehicleHandler.initComponents(lblTxtTypeVehicle, lblTxtPlates, lblTxtModel, lblTxtResponsible, lblTxtResponsibleEmail,
                lblTxtZone, lblTxtLocation, sharedPreferences.getInt(SharedPreferenceUtil.Key.idUser));
    }
}
