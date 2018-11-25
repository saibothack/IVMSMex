package ivms.sysware.com.ivmsmex.fragments.suggestions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;

public class SuggestionFragment extends Fragment {
    private SharedPreferenceUtil sharedPreferences;
    NavigationActivity navigationActivity;

    EditText txtTitle;
    EditText txtSuggestion;
    Button btnSend;

    public SuggestionFragment() {
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
        View fragment = inflater.inflate(R.layout.fragment_suggestion, container, false);
        initComponents(fragment);
        return fragment;
    }

    private void initComponents(View frm) {
        navigationActivity = (NavigationActivity) getActivity();
        sharedPreferences = navigationActivity.sharedPreferences;

        txtTitle = frm.findViewById(R.id.txtTitle);
        txtSuggestion = frm.findViewById(R.id.txtSuggestion);

        btnSend = frm.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                }
            }
        });
    }

    private boolean validate() {
        boolean success = true;

        if (txtTitle.getText().toString().isEmpty()) {
            txtTitle.setError("Es necesario ingresar un titulo");
            success = false;
        }

        if (txtSuggestion.getText().toString().isEmpty()) {
            txtSuggestion.setError("Es necesario ingresar su descripción");
            success = false;
        }

        return success;
    }
}
