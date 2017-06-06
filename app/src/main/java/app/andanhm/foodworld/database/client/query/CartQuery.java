package app.andanhm.foodworld.database.client.query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import app.andanhm.foodworld.database.DBHandler;
import app.andanhm.foodworld.database.client.table.CartTable;
import app.andanhm.foodworld.model.FoodData;
import app.andanhm.foodworld.utils.DateTimeUtils;
import app.andanhm.foodworld.utils.Log;

/**
 * <h1>Cart Table</h1>
 *<p> Cart table allows to store cart items</p>
 */
@SuppressWarnings("unused")
class CartQuery extends DBHandler {
    private static String TAG = CartQuery.class.getSimpleName();
    private static String TABLE=CartTable.CART_TABLE;

    public CartQuery(Context context) {
        super(context);
    }
    /**
     * Convenience method for get cart count from the database
     * @return int the number of rows in the building database.
     */
    public int getCartCount() {
        String countQuery = String.format("SELECT * FROM %s",TABLE);
        Cursor cursor = database.rawQuery(countQuery, null);
        int count=cursor.getCount();
        cursor.close();
        return count;
    }
    /**
     * Convenience method for checking product id is in the cart table or not
     * @param productId to check whether product id is available or not
     * @return No of rows if it available else 0
     */
    public int isProductInCart(String productId) {
        String selectQuery = String.format("SELECT * FROM %s WHERE %s = %s",TABLE,CartTable.PRODUCT_ID,productId);
        Cursor cursor = database.rawQuery(selectQuery, null);
        int count=cursor.getCount();
        cursor.close();
        return count;
    }
    /**
     * Convenience method for inserting a food details to the database
     * @param foodData this map contains the initial column values for the row.
     * @return boolean about the insert throws exception if Sql Constrains violated
     */
    public boolean addItemToCart(FoodData foodData) throws SQLIntegrityConstraintViolationException {
        boolean result=false;
        ContentValues values = new ContentValues();
        values.put(CartTable.PRODUCT_ID, foodData.getFoodId());             // Product ID
        values.put(CartTable.PRODUCT_NAME,foodData.getFoodName());          // Product Name
        values.put(CartTable.PRODUCT_DESCRIPTION,foodData.getFoodDescription());    // Product Description
        values.put(CartTable.PRODUCT_IMG_URL,foodData.getFoodImgURL());    // Product Image URL
        values.put(CartTable.PRODUCT_TYPE, foodData.getFoodType());          // Product Type
        values.put(CartTable.PRODUCT_PRICE, foodData.getFoodPrice());            // Product Price
        values.put(CartTable.PRODUCT_QUANTITY, foodData.getFoodQuantity());            // Quantity
        values.put(CartTable.CART_ADD_TIME, DateTimeUtils.getCurrentTime());            // To cart added time
        try {
            result = database.insert(TABLE, null, values) > 0;
        }catch (SQLiteException e){
            Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
            Log.printStackTrace(e);
        }
        return result;
    }
    /**
     * Convenience method for updating quantity of the product to the cart table
     * @param productId Product ID that need to be update.
     * @param quantity  Quantity size to be updated
     * @return boolean about the row update
     */
    public boolean updateFoodQuantity(String productId,int quantity){
        ContentValues values = new ContentValues();
        values.put(CartTable.PRODUCT_QUANTITY, quantity);            // Building URL
        return database.update(TABLE, values, CartTable.PRODUCT_ID + " = ?", new String[]{productId})>0;
    }

    /**
     * Convenience method for getting all chart stored information from the table
     * @return List<FoodData> List of cart items
     */
    public List<FoodData> getCart(){
        List<FoodData> foodDataList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM %s",TABLE);
        Cursor cursor = database.rawQuery(selectQuery, null);
        try{
            if (cursor.moveToFirst()) {
                do {
                    FoodData foodData = new FoodData();
                    foodData.setFoodId(cursor.getString(0));
                    foodData.setFoodName(cursor.getString(1));
                    foodData.setFoodDescription(cursor.getString(2));
                    foodData.setFoodType(cursor.getString(3));
                    foodData.setFoodImgURL(cursor.getString(4));
                    foodData.setFoodPrice(cursor.getDouble(5));
                    foodData.setFoodQuantity(cursor.getInt(6));
                    foodData.setCartAddedTime(cursor.getString(7));
                    foodDataList.add(foodData);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return foodDataList;         // return cart list
    }
    /**
     * Convenience method for getting building stored information by building id from the table
     * @param productId Product ID that need to be fetched from the table
     * @return Cart product details
     */
    public FoodData getCartDetailsByID(String productId){
        String selectQuery = String.format("SELECT * FROM %s WHERE %s = %s",TABLE,CartTable.PRODUCT_ID,productId);
        FoodData foodData = new FoodData();
        Cursor cursor = database.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                foodData.setFoodId(cursor.getString(0));
                foodData.setFoodName(cursor.getString(1));
                foodData.setFoodDescription(cursor.getString(2));
                foodData.setFoodType(cursor.getString(3));
                foodData.setFoodImgURL(cursor.getString(4));
                foodData.setFoodPrice(cursor.getDouble(5));
                foodData.setFoodQuantity(cursor.getInt(6));
            }
        }catch (Exception e){
            Log.printStackTrace(e);
            Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }
        return foodData;         // return cart product details
    }

    /**
     * Convenience method for delete cart information by product id from the database
     * @param productID Product ID that need to be deleted from the database
     * @return false if number of rows affected if a whereClause is passed in.
     * To remove all rows and get a count pass "true" as the whereClause.
     */
    public boolean deleteProductFromCart(String productID){
        return database.delete(CartTable.CART_TABLE, CartTable.PRODUCT_ID + " = ?",
                new String[]{String.valueOf(productID)})>0;
    }

    /**
     * Convenience method for deleting all cart table information from the database
     * @return false if number of rows affected if a whereClause is passed in.
     * true if removed all rows from the table
     */
    public boolean clearCart(){
        return database.delete(CartTable.CART_TABLE, null, null)>0;
    }
}
