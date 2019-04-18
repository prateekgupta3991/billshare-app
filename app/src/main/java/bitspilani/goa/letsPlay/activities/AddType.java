package bitspilani.goa.letsPlay.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddType {

    //class for creating and modifying database
    //This class would be called whenever database creation/updation
    //is to be done
    //setting up database variables required
    //rows in table
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_MAILADD = "persons_mail";
    public static final String KEY_ADD = "persons_add";


    //database variables
    public static final String DBMS_NAME = "info";
    public static final String DBMS_TABLE = "infotable";
    public static final int DBMS_VERSION = 1;

    private static DbHelper helpobj;
    private static Context classcontext;
    private static SQLiteDatabase dbms;

    public AddType(Context con) {
        classcontext = con;
    }

    public AddType open() throws SQLException {
        helpobj = new DbHelper(classcontext);
        dbms = helpobj.getWritableDatabase();
        return this;
    }

    public long createentry(String name, String mail, String add) {
        // TODO Auto-generated method stub
        //bundle type data exchange
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_MAILADD, mail);
        cv.put(KEY_ADD, add);
        return dbms.insert(DBMS_TABLE, null, cv);
    }

    public String getD() {
        // TODO Auto-generated method stub
        //reading data from database table using Cursor
        String[] column = new String[]{KEY_ROWID, KEY_NAME, KEY_MAILADD, KEY_ADD};
        Cursor c = dbms.query(DBMS_TABLE, column, null, null, null, null, null);
        String result = "";

        //loop for cursor movement along the column
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(0) + "  " + c.getString(1) + "  " + c.getString(2) + "  " + c.getString(3) + "\n";
        }
        return result;
    }

    public void close() {
        helpobj.close();
    }

    public String getName(long l) {
        // TODO Auto-generated method stub
        String[] column = new String[]{KEY_ROWID, KEY_NAME, KEY_MAILADD, KEY_ADD};
        Cursor c = dbms.query(DBMS_TABLE, column, KEY_ROWID + "=" + l, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        } else
            return null;
    }

    public String getemail(long l) {
        // TODO Auto-generated method stub
        String[] column = new String[]{KEY_ROWID, KEY_NAME, KEY_MAILADD, KEY_ADD};
        Cursor c = dbms.query(DBMS_TABLE, column, KEY_ROWID + "=" + l, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            String mail = c.getString(2);
            return mail;
        } else
            return null;
    }

    public String getaddr(long l) {
        // TODO Auto-generated method stub
        String[] column = new String[]{KEY_ROWID, KEY_NAME, KEY_MAILADD, KEY_ADD};
        Cursor c = dbms.query(DBMS_TABLE, column, KEY_ROWID + "=" + l, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
            String addr = c.getString(3);
            return addr;
        } else
            return null;
    }

    public void updateEntry(long l, String name, String mail, String add) {
        // TODO Auto-generated method stub
        ContentValues cvupd = new ContentValues();
        cvupd.put(KEY_NAME, name);
        cvupd.put(KEY_MAILADD, mail);
        cvupd.put(KEY_ADD, add);
        dbms.update(DBMS_TABLE, cvupd, KEY_ROWID + "=" + l, null);
    }

    public void deleteEnt(long l) {
        // TODO Auto-generated method stub
        dbms.delete(DBMS_TABLE, KEY_ROWID + "=" + l, null);
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DBMS_NAME, null, DBMS_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            //method called only at time of creation of dbms

            db.execSQL("CREATE TABLE " + DBMS_TABLE + " ( " +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_MAILADD + " TEXT NOT NULL, " +
                    KEY_ADD + " TEXT NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            //used for upgrading the dbms everytime
            db.execSQL("DROP TABLE IF EXISTS " + DBMS_TABLE);
            onCreate(db);
        }

    }
}