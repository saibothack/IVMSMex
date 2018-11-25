package ivms.sysware.com.ivmsmex.fragments.profiler;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.database.user.UserHandler;
import ivms.sysware.com.ivmsmex.fragments.changePassword.ChangePasswordFragment;
import ivms.sysware.com.ivmsmex.fragments.editProfiler.EditProfilerFragment;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;


public class ProfilerFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private SharedPreferenceUtil sharedPreferences;
    NavigationActivity navigationActivity;

    ImageButton btnImageProfiler;
    ImageButton btnEditUser;
    ImageButton btnChangePassword;
    ImageView imgImageProfiler;

    TextView lblTxtRole;
    TextView lblTxtName;
    TextView lblTxtEmail;
    TextView lblTxtPhone;
    TextView lblTxtEmployeeNumber;
    TextView lblTxtBoss;
    TextView lblTxtBossEmail;
    TextView lblTxtZone;
    TextView lblTxtLocation;

    public ProfilerFragment() {
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
        View fragment = inflater.inflate(R.layout.fragment_profiler, container, false);
        initComponents(fragment);
        return fragment;
    }

    private void initComponents(View frm) {
        navigationActivity = (NavigationActivity) getActivity();
        sharedPreferences = navigationActivity.sharedPreferences;
        btnImageProfiler = frm.findViewById(R.id.btnImageProfiler);
        btnEditUser = frm.findViewById(R.id.btnEditUser);
        btnChangePassword = frm.findViewById(R.id.btnChangePassword);
        imgImageProfiler = frm.findViewById(R.id.imgImageProfiler);

        lblTxtRole = frm.findViewById(R.id.lblTxtRole);
        lblTxtName = frm.findViewById(R.id.lblTxtName);
        lblTxtEmail = frm.findViewById(R.id.lblTxtEmail);
        lblTxtPhone = frm.findViewById(R.id.lblTxtPhone);
        lblTxtEmployeeNumber = frm.findViewById(R.id.lblTxtEmployeeNumber);
        lblTxtBoss = frm.findViewById(R.id.lblTxtBoss);
        lblTxtBossEmail = frm.findViewById(R.id.lblTxtBossEmail);
        lblTxtZone = frm.findViewById(R.id.lblTxtZone);
        lblTxtLocation = frm.findViewById(R.id.lblTxtLocation);

        btnImageProfiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationActivity.setFrame(EditProfilerFragment.class, R.string.edit);
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationActivity.setFrame(ChangePasswordFragment.class, R.string.change_password);
            }
        });

        UserHandler.initComponents(lblTxtRole, lblTxtName, lblTxtEmail, lblTxtPhone, lblTxtEmployeeNumber,
                lblTxtBoss, lblTxtBossEmail, lblTxtZone, lblTxtLocation, sharedPreferences.getInt(SharedPreferenceUtil.Key.idUser));
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(navigationActivity.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
