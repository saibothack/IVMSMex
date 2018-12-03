package ivms.sysware.com.ivmsmex.services.security;

public class passwordResponse {
    private boolean succcess=false;
    private String message="";

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
