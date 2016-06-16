package app.rk.food.device;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import app.rk.food.utils.Log;
import org.json.JSONObject;

/**
 * Class for retrieving various kinds of information related to the application
 * packages that are currently installed on the device.
 *
 */
public class PackageInfo {

    public JSONObject getPackageInfo() {         //Done partially
        try {
            JSONObject packageInfo = new JSONObject();
            packageInfo.put("FEATURE_CAMERA", PackageManager.FEATURE_CAMERA);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                packageInfo.put("FEATURE_CAMERA_ANY", PackageManager.FEATURE_CAMERA_ANY);
            }
            packageInfo.put("FEATURE_CAMERA_AUTOFOCUS", PackageManager.FEATURE_CAMERA_AUTOFOCUS);
            packageInfo.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_CAMERA_FLASH);
            packageInfo.put("FEATURE_WIFI", PackageManager.FEATURE_WIFI);
            packageInfo.put("FEATURE_CAMERA_FAKETOUCH", PackageManager.FEATURE_FAKETOUCH);
            packageInfo.put("FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT", PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT);
            packageInfo.put("FEATURE_LOCATION", PackageManager.FEATURE_LOCATION);
//            if (Build.VERSION.SDK_INT>=18)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                packageInfo.put("FEATURE_INPUT_METHODS", PackageManager.FEATURE_INPUT_METHODS);
            }
            packageInfo.put("FEATURE_BLUETOOTH", PackageManager.FEATURE_BLUETOOTH);
            packageInfo.put("FEATURE_MICROPHONE", PackageManager.FEATURE_MICROPHONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                packageInfo.put("FEATURE_HIFI_SENSORS", PackageManager.FEATURE_HIFI_SENSORS);
            }
            packageInfo.put("FEATURE_SENSOR_LIGHT", PackageManager.FEATURE_SENSOR_LIGHT);
            packageInfo.put("FEATURE_USB_HOST", PackageManager.FEATURE_USB_HOST);
            packageInfo.put("FEATURE_SENSOR_PROXIMITY", PackageManager.FEATURE_SENSOR_PROXIMITY);
            packageInfo.put("FEATURE_LOCATION_GPS", PackageManager.FEATURE_LOCATION_GPS);
            packageInfo.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_TELEPHONY_GSM);
            packageInfo.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_CAMERA_FRONT);
            packageInfo.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_SENSOR_ACCELEROMETER);
            return packageInfo;
        }catch (Exception e){
            Log.printStackTrace(e);
        }
        return null;
    }

    /**
     * Return this app's PackageInfo containing info about version code, version name, etc.
     */
    @Nullable
    public static android.content.pm.PackageInfo getPackageInfo(@NonNull Context mContext) {
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
