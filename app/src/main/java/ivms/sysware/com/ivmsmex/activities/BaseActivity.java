package ivms.sysware.com.ivmsmex.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.utils.enums;

public class BaseActivity extends AppCompatActivity {
    public BaseActivity() {
        super();
    }


    public void initComponents(){

    }

    public void initEvents(){

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void message(String titulo, String mensaje, enums.MessageType type) {
        messageOptions(titulo, mensaje, type, null, null, null, false, "Aceptar", "Cancelar", null, null);
    }

    public void messageOptions(String titulo, String msj, enums.MessageType type, final Class view){
        messageOptions(titulo, msj, type, view, null, null, true, "Aceptar", "Cancelar", null, null);
    }

    public void messageOptions(String titulo, String msj, enums.MessageType type, final Class view, Boolean mostrarBotonCancelar){
        messageOptions(titulo, msj, type, view, null, null, mostrarBotonCancelar, "Aceptar", "Cancelar", null, null);
    }

    public void messageOptions(String titulo, String msj, enums.MessageType type, final Class view, final Runnable onAccept, final Runnable onCancel, Boolean muestraBotonCancelar, String labelAceptar, String labelCancelar){
        messageOptions(titulo, msj, type, view, onAccept, onCancel, muestraBotonCancelar, labelAceptar, labelCancelar, null, null);
    }
    public void messageOptions(String titulo, String msj, enums.MessageType type, final Class view, final Runnable onAccept, final Runnable onCancel, Boolean muestraBotonCancelar, String labelAceptar, String labelCancelar, final CharSequence[] items, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(titulo);

        if (items != null){
            builder.setSingleChoiceItems(items,0, clickListener);
        }else{
            builder.setMessage(msj);
        }
        if (type.equals(enums.MessageType.DANGER)) {
            builder.setIcon(R.mipmap.cancel);
        } else if (type.equals(enums.MessageType.WARNING)) {
            builder.setIcon(R.mipmap.attention);
        } else if (type.equals(enums.MessageType.SUCCESS)) {
            builder.setIcon(R.mipmap.ok);
        }else{
            builder.setIcon(R.mipmap.info);
        }
        //"Aceptar"
        builder.setPositiveButton(labelAceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onAccept != null){
                    onAccept.run();
                }else {
                    redirect(view);
                }
                dialog.dismiss();
            }
        });
        Boolean mostrarBotonCancelar=muestraBotonCancelar;

        if (mostrarBotonCancelar) {
            //"Cancelar"
            builder.setNegativeButton(labelCancelar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (onCancel != null) {
                        onCancel.run();
                    }
                    dialog.dismiss();

                }
            });
        }


        builder.show();
    }

    public void redirect(final Class clazz) {
        Intent i = new Intent(this, clazz);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
