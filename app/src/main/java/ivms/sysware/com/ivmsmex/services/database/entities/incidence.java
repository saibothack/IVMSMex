package ivms.sysware.com.ivmsmex.services.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import ivms.sysware.com.ivmsmex.utils.enums;

@Entity(tableName = "incidence")
public class incidence {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="incidence_timestamp")
    private Long incidence_timestamp;
    @ColumnInfo(name="userId")
    private String userId;
    @ColumnInfo(name="vehicleId")
    private String vehicleId;
    @ColumnInfo(name="incidenceType")
    private enums.IncidenceType incidenceType;
    @ColumnInfo(name="incidence_start")
    private Long incidence_start;
    @ColumnInfo(name="incidence_end")
    private Long incidence_end;


    public Long getIncidence_timestamp() {
        return incidence_timestamp;
    }

    public void setIncidence_timestamp(Long incidence_timestamp) {
        this.incidence_timestamp = incidence_timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public enums.IncidenceType getIncidenceType() {
        return incidenceType;
    }

    public void setIncidenceType(enums.IncidenceType incidenceType) {
        this.incidenceType = incidenceType;
    }

    public Long getIncidence_start() {
        return incidence_start;
    }

    public void setIncidence_start(Long incidence_start) {
        this.incidence_start = incidence_start;
    }

    public Long getIncidence_end() {
        return incidence_end;
    }

    public void setIncidence_end(Long incidence_end) {
        this.incidence_end = incidence_end;
    }
}
