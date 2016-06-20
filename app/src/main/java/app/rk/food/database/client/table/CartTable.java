package app.rk.food.database.client.table;

/**
 * --------------------------CART table--------------------------
 */
public class CartTable {


    public static final String CART_TABLE = "db_cart";

    public static final String PRODUCT_ID = "pro_id";
    public static final String PRODUCT_NAME = "pro_name";
    public static final String PRODUCT_DESCRIPTION = "pro_description";
    public static final String PRODUCT_IMG_URL = "img_url";
    public static final String PRODUCT_TYPE = "pro_type";
    public static final String PRODUCT_PRICE = "pro_price";
    public static final String PRODUCT_QUANTITY = "pro_quantity";
    public static final String CART_ADD_TIME = "add_time";

    public static String getCartTableQuery() {
        return
                "CREATE TABLE " + CART_TABLE +
                        "(" +
                        PRODUCT_ID + " TEXT," +
                        PRODUCT_NAME + " TEXT NOT NULL," +
                        PRODUCT_DESCRIPTION + " TEXT," +
                        PRODUCT_IMG_URL + " TEXT," +
                        PRODUCT_TYPE + " TEXT," +
                        PRODUCT_PRICE + " REAL NOT NULL," +
                        PRODUCT_QUANTITY + " INT NOT NULL," +
                        CART_ADD_TIME + " CHAR(30)" +
                        ");";
    }
}
