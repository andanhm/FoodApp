package app.andanhm.foodworld.database.client.table;

/**
 * --------------------------CART table--------------------------
 */
public class CartTable {


    public static final String CART_TABLE = "tblCart";

    public static final String PRODUCT_ID = "pro_id";
    public static final String PRODUCT_NAME = "pro_name";
    public static final String PRODUCT_DESCRIPTION = "pro_description";
    public static final String PRODUCT_IMG_URL = "img_url";
    public static final String PRODUCT_TYPE = "pro_type";
    public static final String PRODUCT_PRICE = "pro_price";
    public static final String PRODUCT_QUANTITY = "pro_quantity";
    public static final String CART_ADD_TIME = "add_time";

    public static String getCartTableQuery() {

        return String.format("CREATE TABLE %s (%s TEXT, %s TEXT NOT NULL, %s TEXT,%s TEXT,%s TEXT,%s REAL TEXT NOT TEXT,%s INT NOT NULL,%s TEXT",
                        CART_TABLE,
                        PRODUCT_ID,
                        PRODUCT_NAME,
                        PRODUCT_DESCRIPTION,
                        PRODUCT_IMG_URL,
                        PRODUCT_TYPE,
                        PRODUCT_PRICE,
                        PRODUCT_QUANTITY,
                        CART_ADD_TIME);
    }
}
