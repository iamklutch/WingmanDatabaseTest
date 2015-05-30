package yukidev.com.wingmandatabasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by James on 5/29/2015.
 */
public class DatabaseMethods {

    private Context mContext;
    private DatabaseHelper mDatabaseHelper;

    public DatabaseMethods(Context context) {
        mContext = context;
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public ArrayList<Airman> readAirman(){
        SQLiteDatabase database = open();

        Cursor cursor = database.query(
                DatabaseHelper.AIRMAN_TABLE,
                new String[]{BaseColumns._ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_AGE, DatabaseHelper.COLUMN_RANK},
                null,  //selection
                null,  //selection args
                null,  //group by
                null,  //having
                null);  //order

        ArrayList<Airman> airmen = new ArrayList<Airman>();
        if (cursor.moveToFirst()) {
            do {
                Airman airman = new Airman(
                        getStringFromColumnName(cursor, DatabaseHelper.COLUMN_NAME),
                        getIntFromColumnName(cursor, DatabaseHelper.COLUMN_AGE),
                        getStringFromColumnName(cursor, DatabaseHelper.COLUMN_RANK));
                airmen.add(airman);
            }while(cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return airmen;
    }

    private int getIntFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);

    }

    private SQLiteDatabase open(){
        return mDatabaseHelper.getWritableDatabase();
    }

    private void close (SQLiteDatabase database) {
        database.close();
    }

    public void create(Airman airman) {
        SQLiteDatabase database = open();
        database.beginTransaction();

        ContentValues airmanValues = new ContentValues();
        airmanValues.put(DatabaseHelper.COLUMN_NAME, airman.getName());
        airmanValues.put(DatabaseHelper.COLUMN_AGE, airman.getAge());
        airmanValues.put(DatabaseHelper.COLUMN_RANK, airman.getRank());

        database.insert(DatabaseHelper.AIRMAN_TABLE, null, airmanValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }
}
