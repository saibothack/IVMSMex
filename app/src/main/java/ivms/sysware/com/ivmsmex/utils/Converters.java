package ivms.sysware.com.ivmsmex.utils;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static enums.IncidenceType incidenceFromString(String value){
        return enums.IncidenceType.valueOf(value);
    }

    @TypeConverter
    public static String stringFromIncidence(enums.IncidenceType incidenceType){
        return incidenceType.toString();
    }

    @TypeConverter
    public static enums.CatalogType catalogFromString(String value){
        return enums.CatalogType.valueOf(value);
    }

    @TypeConverter
    public static String stringFromCatalog(enums.CatalogType catalogType){
        return catalogType.toString();
    }




}