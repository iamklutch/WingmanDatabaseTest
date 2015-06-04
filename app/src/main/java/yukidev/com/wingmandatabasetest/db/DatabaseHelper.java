package yukidev.com.wingmandatabasetest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by James   on 5/29/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "AIRMANDB";
    private static final int VERSION = 1;

    public static final String AIRMAN_TABLE = "airmen";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_RANK = "rank";
    public static final String COLUMN_RANK_VALUE = "rank_value";


    // when upgrading, add the new columns and increment the VERSION above
    private static final String CREATE_AIRMAN_TABLE =
            "CREATE TABLE " + AIRMAN_TABLE + " ( " + BaseColumns._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " +
                    COLUMN_AGE + " INTEGER, " + COLUMN_RANK + " TEXT)";

    public DatabaseHelper(Context context) {super(context, DB_NAME, null, VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_AIRMAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
