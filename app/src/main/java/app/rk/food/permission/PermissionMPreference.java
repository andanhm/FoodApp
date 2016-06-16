package app.rk.food.permission;

import android.content.Context;
import android.content.SharedPreferences;

/** Permission M Preference
 *The public class allows to check whether permission asked or not by seating preference status
 */
public class PermissionMPreference {

    protected static final String PREFERENCE_NAME="com.rk.food.permission";

    public static final String PHONE_STATE_REQUEST_CODE="PHONE_STATE_REQUEST";
    public static final String GET_ACCOUNTS_REQUEST_CODE="GET_ACCOUNTS_REQUEST";
    public static final String READ_SMS_REQUEST_CODE="READ_SMS_REQUEST";
    public static final String CAMERA_REQUEST_CODE="CAMERA_REQUEST";
    public static final String CALL_PHONE_REQUEST_CODE="CALL_PHONE_REQUEST";
    public static final String LOCATION_REQUEST_CODE="LOCATION_PHONE_REQUEST";
    public static final String WRITE_EXTERNAL_STORAGE_REQUEST_CODE="WRITE_EXTERNAL_STORAGE_REQUEST_CODE";

    public static void setPreference(Context mContext,String KEY) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY, true);
        editor.apply();
    }

    public static boolean getPreference(Context mContext, String KEY) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY, false);
    }
}
