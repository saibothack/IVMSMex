package ivms.sysware.com.ivmsmex.utils;

public class enums {
    public enum MessageType {
        SUCCESS,
        INFO,
        WARNING,
        DANGER
    }
    public enum IncidenceType {
        VELOCIDAD_MAXIMA,
        VELOCIDAD_SOSTENIDA,
        ACELERACION,
        FRENADO,
        FATIGA
    }

    public enum CatalogType {
        LIMITE_VELOCIDAD_MAXIMA,
        LIMITE_VELOCIDAD_SOSTENIDA,
        LIMITE_KM_ACELERACION,
        LIMITE_SEGUNDOS_ACELERACION,
        LIMITE_KM_FRENADO,
        LIMITE_SEGUNDOS_FRENADO,
        LIMITE_MINUTOS_FATIGA,
        SEGUNDOS_ACTUALIZACION_ESTATUS,
        MILISEGUNDOS_LECTURA_GPS
    }
}
