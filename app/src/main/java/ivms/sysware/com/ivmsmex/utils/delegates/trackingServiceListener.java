package ivms.sysware.com.ivmsmex.utils.delegates;

import ivms.sysware.com.ivmsmex.services.database.entities.incidence;
//import ivms.sysware.com.ivmsmex.services.rastreo.trackingResponse;
import ivms.sysware.com.ivmsmex.utils.enums;

public interface trackingServiceListener {
    void onIncidenciaEvent(incidence incidencia, enums.IncidenceType tipoIncidencia);
    void onStartDriving();
    void onStopDriving();
    //void onTrackingResponse(trackingResponse response);
    String listenerId();


}
