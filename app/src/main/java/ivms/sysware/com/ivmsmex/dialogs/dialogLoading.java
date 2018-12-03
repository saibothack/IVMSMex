package ivms.sysware.com.ivmsmex.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import ivms.sysware.com.ivmsmex.R;

public class dialogLoading {
    private View view;
    private ProgressBar progressBar;
    private TextView msg;
    private LinearLayout ll;
    private AlertDialog.Builder builder;
    private Dialog dialog;

    public dialogLoading(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_loading, null);
        init();
    }

    void init() {
        msg = (TextView) view.findViewById(R.id.txt_loading_message);
        progressBar = ((ProgressBar) view.findViewById(R.id.ldr_progress));
        progressBar.setIndeterminate(true);
        ll = ((LinearLayout) view.findViewById(R.id.linear_layout_progress));
        builder = new AlertDialog.Builder(view.getContext()).setCancelable(false);
    }


    public dialogLoading setMessage(String message) {
        msg.setText(message);
        return this;
    }


    public dialogLoading setMessageColor(int color) {
        msg.setTextColor(color);
        return this;
    }

    public void show() {
        builder.setView(view);
        dialog = builder.create();
        dialog.show();


    }

    public void dismiss() {
        dialog.dismiss();

    }



}
