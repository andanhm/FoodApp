package app.andanhm.foodworld.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.andanhm.foodworld.database.client.table.CartTable;

/**
 * <h1>Database Helper</h1>
 * Called when the database needs to be upgraded. The implementation
 * should use this method to drop tables, add tables, or do anything else it
 * needs to upgrade to the new schema version.
 *
 * <p>
 * The SQLite ALTER TABLE documentation can be found
 * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
 * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
 * you can use ALTER TABLE to rename the old table, then create the new table and then
 * populate the new table with the contents of the old table.
 * </p><p>
 * This method executes within a transaction.  If an exception is thrown, all changes
 * will automatically be rolled back.
 * </p>
 *
 * DATABASE_NAME The database.
 * DATABASE_VERSION The old database version.
 */

class DBHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "foodies";      // Database Name
    private static final int DATABASE_VERSION = 1;              // Database Version



    private static DBHelper instance;

    static synchronized DBHelper getHelper(Context mContext) {
        if (instance == null) {
            instance = new DBHelper(mContext);
        }
        return instance;
    }

    /*------------------------------------------------------------------*/
    private DBHelper(Context mContext){
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CartTable.getCartTableQuery());
    }
    /**
     * Called when the database needs to be downgraded. This is strictly similar to
     * However, this method is not abstract, so it is not mandatory for a customer to
     * implement it. If not overridden, default implementation will reject downgrade and
     * throws SQLiteException
     *
     * <p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropQuery = String.format("DROP TABLE IF EXISTS %s",CartTable.CART_TABLE);
        db.execSQL(dropQuery);
        onCreate(db);
    }

}
