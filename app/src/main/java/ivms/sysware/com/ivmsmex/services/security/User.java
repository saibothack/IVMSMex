package ivms.sysware.com.ivmsmex.services.security;

import java.util.ArrayList;
import java.util.List;

import ivms.sysware.com.ivmsmex.utils.genericResponse;

public class User extends genericResponse {
    private String userId;
    private String nameUser;
    private String applicationId;
    private String email;
    private ArrayList<Vehicle> vehicle;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }


    private static User sessionUser=null;
    public static User getSessionUser(){
        return sessionUser;
    }

    public static void setSessionUser(User u){
        sessionUser=u;
    }

    public ArrayList<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(ArrayList<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
