package ivms.sysware.com.ivmsmex.database.vehicle.entities;

public class Vehicle {

    private  int id;

    private  String Plates;

    private  String Model;

    private  String Responsible;

    private  String Email;

    private  String TypeVehicle;

    private  String Area;       

    private  String Location;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlates() {
        return this.Plates;
    }

    public void setPlates(String Plates) {
        this.Plates = Plates;
    }

    public String getModel() {
        return this.Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getResponsible() {
        return this.Responsible;
    }

    public void setResponsible(String Responsible) {
        this.Responsible = Responsible;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTypeVehicle() {
        return this.TypeVehicle;
    }

    public void setTypeVehicle(String TypeVehicle) {
        this.TypeVehicle = TypeVehicle;
    }

    public String getArea() {
        return this.Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
}
