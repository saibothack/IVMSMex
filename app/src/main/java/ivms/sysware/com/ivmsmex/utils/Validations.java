package ivms.sysware.com.ivmsmex.utils;

public class Validations {

    public static boolean formatPhoneValid(String phone) {
        return phone.matches("^[0-9]{10}");
    }

    public static boolean formatEmailValid(String email) {
        return email.matches("^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@(([0-9a-zA-Z])+([-\\w]*[0-9a-zA-Z])*\\.)+[a-zA-Z]{2,9})$");
    }

    public static boolean formatPasswordValid(String password) {
        return password.matches( "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$");
    }
}
