package ivms.sysware.com.ivmsmex.fragments.tracking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.navigation.NavigationActivity;
import ivms.sysware.com.ivmsmex.services.ForegroundLocation;
import ivms.sysware.com.ivmsmex.utils.Constants;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;


public class TrackingFragment extends Fragment {
    private int corx, cory;
    private Lienzo fondo;
    static final int REQUEST_IMAGE_CAPTURE = 1;
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(navigationActivity);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_delay, null);
        builder.setView(v);

        final EditText txtDelay = v.findViewById(R.id.txtDelay);
        Button btnContinue = v.findViewById(R.id.btnContinue);

        final AlertDialog alertNotification = builder.create();

        btnContinue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtDelay.getText().toString().isEmpty()) {
                            txtDelay.setError("Por favor ingrese su motivo del retraso");
                        } else {
                            alertNotification.dismiss();
                        }
                    }
                }
        );

        alertNotification.show();
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(navigationActivity);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_delivery, null);
        builder.setView(v);

        final EditText txtDelay = v.findViewById(R.id.txtDelay);
        Button btnContinue = v.findViewById(R.id.btnContinue);
        Button btnEvidence = v.findViewById(R.id.btnEvidence);
        final AlertDialog alertNotification = builder.create();

        btnContinue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertNotification.dismiss();
                        setSignature();
                    }
                }
        );

        btnEvidence.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dispatchTakePictureIntent();
                    }
                }
        );

        alertNotification.show();

    }

    public void setSignature() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(navigationActivity);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_signature, null);
        builder.setView(v);

        Button btnClear = v.findViewById(R.id.btnClear);
        Button btnContinue = v.findViewById(R.id.btnContinue);

        corx = 100;
        cory = 100;
        RelativeLayout layout1 = (RelativeLayout) v.findViewById(R.id.lySignature);


        final AlertDialog alertNotification = builder.create();

        btnContinue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lyDelivery.setVisibility(View.GONE);
                        lyHomecoming.setVisibility(View.VISIBLE);
                        alertNotification.dismiss();
                    }
                }
        );

        btnClear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }
        );

        alertNotification.show();
    }

    public void homecoming() {
        lyHomecoming.setVisibility(View.GONE);
        lyTracking.setVisibility(View.VISIBLE);
    }

    private void initTracking() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(navigationActivity);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_remission, null);
        builder.setView(v);


        final EditText txtRemision = v.findViewById(R.id.txtRemision);
        Button btnContinue = v.findViewById(R.id.btnContinue);

        final AlertDialog alertNotification = builder.create();

        btnContinue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (txtRemision.getText().toString().isEmpty()) {
                            txtRemision.setError("Por favor ingrese su número de remisión");
                        } else {
                            lyTracking.setVisibility(View.GONE);
                            lyDelay.setVisibility(View.VISIBLE);
                            lyArrival.setVisibility(View.VISIBLE);

                            btnTracking.setText(R.string.stop_tracking);
                            Intent startIntent = new Intent(navigationActivity, ForegroundLocation.class);
                            startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                            navigationActivity.startService(startIntent);
                            alertNotification.dismiss();
                        }
                    }
                }
        );

        alertNotification.show();
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(navigationActivity.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        corx = (int) event.getX();
        cory = (int) event.getY();
        fondo.invalidate();
        return true;
    }
    class Lienzo extends View {

        public Lienzo(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255, 255, 0);
            Paint pincel1 = new Paint();
            pincel1.setARGB(255, 255, 0, 0);
            pincel1.setStrokeWidth(4);
            pincel1.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(corx, cory, 20, pincel1);
        }
    }
}
