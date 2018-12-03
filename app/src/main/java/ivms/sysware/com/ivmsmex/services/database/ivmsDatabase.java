package ivms.sysware.com.ivmsmex.services.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import ivms.sysware.com.ivmsmex.services.database.entities.incidence;
import ivms.sysware.com.ivmsmex.services.database.entities.configuration;
import ivms.sysware.com.ivmsmex.services.database.entities.coordinatePoint;
import ivms.sysware.com.ivmsmex.utils.Converters;

@Database(entities = {configuration.class,coordinatePoint.class,incidence.class}, version=ivmsDatabase.VERSION)
@TypeConverters({Converters.class})
public abstract class ivmsDatabase extends RoomDatabase {
    static final int VERSION = 1;
    private static ivmsDatabase INSTANCE;
    public abstract ivmsDAO ivmsDAO();

    public static ivmsDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), ivmsDatabase.class, "ivms-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            //.allowMainThreadQueries()
                            //.fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }


}
