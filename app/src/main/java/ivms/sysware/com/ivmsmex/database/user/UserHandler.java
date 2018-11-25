package ivms.sysware.com.ivmsmex.database.user;

import android.widget.EditText;
import android.widget.TextView;

import ivms.sysware.com.ivmsmex.database.user.entities.User;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;

public class UserHandler {

    public static void initComponents(final TextView lblTxtRole, final TextView lblTxtName, final TextView lblTxtEmail,
                                      final TextView lblTxtPhone, final TextView lblTxtEmployeeNumber, final TextView lblTxtBoss,
                                      final TextView lblTxtBossEmail, final TextView lblTxtZone, final TextView lblTxtLocation, int iUser) {

        User user = UserHelper.getInstance().getUserProfiler(iUser);

        lblTxtRole.setText(user.getRole());
        lblTxtName.setText(user.getName());
        lblTxtEmail.setText(user.getEmail());
        lblTxtPhone.setText(user.getPhone());
        lblTxtEmployeeNumber.setText(user.getEmployeeNumber());
        lblTxtBoss.setText(user.getBoss());
        lblTxtBossEmail.setText(user.getBossEmail());
        lblTxtZone.setText(user.getZone());
        lblTxtLocation.setText(user.getLocation());

    }

    public static void initComponents(final EditText txtName, final EditText txtEmail, final EditText txtPhone, final EditText txtEmployeeNumber, int iUser) {

        User user = UserHelper.getInstance().getUserProfiler(iUser);

        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtEmployeeNumber.setText(user.getEmployeeNumber());

    }

    public static void saveUserEdit(String Name, String Email, String Phone, String EmployeeNumber, final SharedPreferenceUtil sharedPreferences, int iUser) {
        sharedPreferences.put(SharedPreferenceUtil.Key.nameUser, Name);
    }

    public static void saveChangePassword(String password, int iUser) {

    }
}
