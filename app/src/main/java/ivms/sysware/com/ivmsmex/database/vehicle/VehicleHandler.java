package ivms.sysware.com.ivmsmex.database.vehicle;

import android.widget.TextView;

import ivms.sysware.com.ivmsmex.database.vehicle.entities.Vehicle;

public class VehicleHandler {

    public static void initComponents(final TextView lblTxtTypeVehicle, final TextView lblTxtPlates, final TextView lblTxtModel,
                                      final TextView lblTxtResponsible, final TextView lblTxtResponsibleEmail, final TextView lblTxtZone,
                                      final TextView lblLocation, int iUser) {

        Vehicle vehicle = VehicleHelper.getInstance().getVehicle(iUser);

        lblTxtTypeVehicle.setText(vehicle.getTypeVehicle());
        lblTxtPlates.setText(vehicle.getPlates());
        lblTxtModel.setText(vehicle.getModel());
        lblTxtResponsible.setText(vehicle.getResponsible());
        lblTxtResponsibleEmail.setText(vehicle.getEmail());
        lblTxtZone.setText(vehicle.getArea());
        lblLocation.setText(vehicle.getLocation());

    }
}
