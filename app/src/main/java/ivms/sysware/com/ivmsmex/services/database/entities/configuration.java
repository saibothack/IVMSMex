package ivms.sysware.com.ivmsmex.services.database.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import ivms.sysware.com.ivmsmex.utils.enums;

@Entity(tableName = "configuration")
public class configuration {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "tipoConfiguracion")
    @NonNull
    private enums.CatalogType tipoConfiguracion;

    @ColumnInfo(name = "valor")
    private String valor;

    @ColumnInfo(name = "lastUpdate")
    private Date lastUpdate;


    public enums.CatalogType getTipoConfiguracion() {
        return tipoConfiguracion;
    }

    public void setTipoConfiguracion(enums.CatalogType tipoConfiguracion) {
        this.tipoConfiguracion = tipoConfiguracion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }





}
