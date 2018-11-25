package ivms.sysware.com.ivmsmex.database.user.entities;

public class User {

    private  int id;

    private  String Role;

    private  String Name;

    private  String Email;

    private  String Phone;

    private  String EmployeeNumber;

    private  String Boss;

    private  String BossEmail;

    private  String Zone;

    private  String Location;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmployeeNumber() {
        return this.EmployeeNumber;
    }

    public void setEmployeeNumber(String EmployeeNumber) {
        this.EmployeeNumber = EmployeeNumber;
    }

    public String getBoss() {
        return this.Boss;
    }

    public void setBoss(String Boss) {
        this.Boss = Boss;
    }

    public String getBossEmail() {
        return this.BossEmail;
    }

    public void setBossEmail(String BossEmail) {
        this.BossEmail = BossEmail;
    }

    public String getZone() {
        return this.Zone;
    }

    public void setZone(String Zone) {
        this.Zone = Zone;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
}
