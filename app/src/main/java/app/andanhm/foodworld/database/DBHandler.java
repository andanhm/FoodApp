package app.andanhm.foodworld.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * <h1>Owner Database Handler</h1>
 * <p> Owner DBHandler allows to open / close database </p>
 * Create and/or open a database that will be used for reading and writing.
 * The first time this is called, the database will be opened and
 * {onCreate}, {onUpgrade} and/or {onOpen} will be
 * called.
 *
 * <p>Once opened successfully, the database is cached, so you can
 * call this method every time you need to write to the database.
 * (Make sure to call {@link #close} when you no longer need the database.)
 * Errors such as bad permissions or a full disk may cause this method
 * to fail, but future attempts may succeed if the problem is fixed.</p>
 *
 * <p class="caution">Database upgrade may take a long time, you
 * should not call this method from the application main thread.
 */
public class DBHandler {
    protected SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context mContext;

    public DBHandler(Context context) {
        this.mContext = context;
        dbHelper = DBHelper.getHelper(mContext);
        open();

    }
    /**
     * Convenience method for open database read / writable
     * throws SQLiteException if the database cannot be opened for writing
     */
    private void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DBHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }
    /**
     * Convenience method for close the database
     * throws SQLiteException if the database cannot be opened for writing
     */
    private void close() throws SQLException {
        if(dbHelper == null)
            database.close();
    }
}
