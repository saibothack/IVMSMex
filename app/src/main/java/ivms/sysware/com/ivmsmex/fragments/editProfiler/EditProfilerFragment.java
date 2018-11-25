package ivms.sysware.com.ivmsmex.fragments.editProfiler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.database.user.UserHandler;
import ivms.sysware.com.ivmsmex.fragments.profiler.ProfilerFragment;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;
import ivms.sysware.com.ivmsmex.utils.Validations;


public class EditProfilerFragment extends Fragment {
    private SharedPreferenceUtil sharedPreferences;

    NavigationActivity navigationActivity;

    Button btnEdit;
    Button btnBack;

    EditText txtName;
    EditText txtEmail;
    EditText txtPhone;
    EditText txtEmployeeNumber;

    public EditProfilerFragment() {
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
        View frame = inflater.inflate(R.layout.fragment_edit_profiler, container, false);
        initComponents(frame);
        return  frame;
    }

    private void initComponents(View frm) {
        navigationActivity = (NavigationActivity) getActivity();
        sharedPreferences = navigationActivity.sharedPreferences;

        txtName = frm.findViewById(R.id.txtName);
        txtEmail = frm.findViewById(R.id.txtEmail);
        txtPhone = frm.findViewById(R.id.txtPhone);
        txtEmployeeNumber = frm.findViewById(R.id.txtEmployeeNumber);

        txtName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        txtEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        txtPhone.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        txtEmployeeNumber.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);


        btnEdit = frm.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    UserHandler.saveUserEdit(txtName.getText().toString(), txtEmail.getText().toString(),
                            txtPhone.getText().toString(), txtEmployeeNumber.getText().toString(), sharedPreferences,
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

        UserHandler.initComponents(txtName, txtEmail, txtPhone, txtEmployeeNumber,
                sharedPreferences.getInt(SharedPreferenceUtil.Key.idUser));

    }

    private boolean validate() {
        boolean success = true;

        if (txtName.getText().toString().isEmpty()) {
            txtName.setError("Por favor ingrese su nombre.");
            success = false;
        }

        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.setError("Por favor ingrese su correo.");
            success = false;
        } else {
            if (!Validations.formatEmailValid(txtEmail.getText().toString())) {
                txtEmail.setError("Por favor un correo valido.");
                success = false;
            }
        }

        if (txtPhone.getText().toString().isEmpty()) {
            txtPhone.setError("Por favor ingrese su teléfono.");
            success = false;
        } else {
            if (!Validations.formatPhoneValid(txtPhone.getText().toString())) {
                txtEmail.setError("Por favor ingrese un teléfono valido.");
                success = false;
            }
        }

        if (txtEmployeeNumber.getText().toString().isEmpty()) {
            txtEmployeeNumber.setError("Por favor ingrese su número de empleado.");
            success = false;
        }

        return success;
    }

}
