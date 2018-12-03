package ivms.sysware.com.ivmsmex.services.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "coordinate_point",
        foreignKeys = @ForeignKey(entity = incidence.class, parentColumns = "incidence_timestamp", childColumns = "incidence_timestamp" , onDelete = CASCADE))
public class coordinatePoint {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="timestamp")
    private Long timestamp;

    @ColumnInfo (name="latitude")
    private Long latitude;

    @ColumnInfo (name="longitude")
    private Long longitude;

    @ColumnInfo (name="speed")
    private Long speed;

    @ColumnInfo(name="incidence_timestamp")
    private Long incidence_timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }


    public Long getIncidence_timestamp() {
        return incidence_timestamp;
    }

    public void setIncidence_timestamp(Long incidence_timestamp) {
        this.incidence_timestamp = incidence_timestamp;
    }
}
