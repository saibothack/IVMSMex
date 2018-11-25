package ivms.sysware.com.ivmsmex.fragments.changePassword;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.database.user.UserHandler;
import ivms.sysware.com.ivmsmex.fragments.profiler.ProfilerFragment;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;

public class ChangePasswordFragment extends Fragment {
    private SharedPreferenceUtil sharedPreferences;
    NavigationActivity navigationActivity;

    TextView txtPassword;
    TextView txtPasswordConfirm;

    Button btnChangePassword;
    Button btnBack;

    public ChangePasswordFragment() {
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
        View frame = inflater.inflate(R.layout.fragment_change_password, container, false);
        initComponents(frame);
        return frame;
    }

    private void initComponents(View frm) {
        navigationActivity = (NavigationActivity) getActivity();
        sharedPreferences = navigationActivity.sharedPreferences;

        txtPassword = frm.findViewById(R.id.txtPassword);
        txtPasswordConfirm = frm.findViewById(R.id.txtPasswordConfirm);

        btnChangePassword = frm.findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    UserHandler.saveChangePassword(txtPassword.getText().toString(),
                            sharedPreferences.getInt(SharedPreferenceUtil.Key.idUser));
                }
            }
        });

        btnBack = frm.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationActivity.setFrame(ProfilerFragment.class, R.string.profiler);
            }
        });
    }

    private boolean validate() {
        boolean suceess = true;

        if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Por favor ingrese su contraseña.");
            suceess = false;
        }

        if (txtPasswordConfirm.getText().toString().isEmpty()) {
            txtPasswordConfirm.setError("Por favor ingrese su contraseña.");
            suceess = false;
        }

        if (txtPassword.getText().toString() != txtPassword.getText().toString()) {
            txtPasswordConfirm.setError("Sus contraseñas no coinciden.");
            suceess = false;
        }

        return suceess;
    }
}
