package ivms.sysware.com.ivmsmex.database.vehicle;

import ivms.sysware.com.ivmsmex.database.vehicle.entities.Vehicle;

public class VehicleHelper {

    private static final VehicleHelper ourInstance = new VehicleHelper();

    public static VehicleHelper getInstance() {
        return ourInstance;
    }

    public Vehicle getVehicle(int iUser) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setPlates("MAF8899");
        vehicle.setModel("2006");
        vehicle.setResponsible("Gad Arenas");
        vehicle.setEmail("garenas@sysware.com.mx");
        vehicle.setTypeVehicle("Sedan");
        vehicle.setArea("Centro");
        vehicle.setLocation("Ixtapaluca");

        return  vehicle;
    }
}
