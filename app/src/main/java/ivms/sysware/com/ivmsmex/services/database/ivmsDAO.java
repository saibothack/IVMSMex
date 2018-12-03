package ivms.sysware.com.ivmsmex.services.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ivms.sysware.com.ivmsmex.services.database.entities.configuration;
import ivms.sysware.com.ivmsmex.utils.enums;

@Dao
public interface ivmsDAO {

    @Query("SELECT * FROM configuration")
    LiveData<List<configuration>> obtieneConfiguraciones();

    @Query("SELECT * FROM configuration where tipoConfiguracion=:tipoConfig")
    LiveData<configuration> obtieneConfiguracion(enums.CatalogType tipoConfig);

    @Insert
    void guardaConfiguracion(configuration config);

    @Delete
    void eliminaConfiguracion(configuration config);


}
