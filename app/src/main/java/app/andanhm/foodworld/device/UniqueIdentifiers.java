package app.andanhm.foodworld.device;

import android.content.Context;
import android.telephony.TelephonyManager;
import app.andanhm.foodworld.utils.Log;
import java.util.UUID;

/**
 * <h1>Unique Identifiers</h1>
 * <p>Class allow to get the device information
 * Allows to fetch the information about the Device ID, Android Device id,UUID,SIM Serial No</p>
 */

public class  UniqueIdentifiers {
    Context mContext;
    final TelephonyManager telephonyManager;
    private String deviceID, androidId;

    /**
     * Constructor
     *
     * @param mContext To initialize telephony manager with context
     */
    public UniqueIdentifiers(Context mContext) {
        this.mContext = mContext;
        telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public String getDeviceID() {
        try {
            deviceID = telephonyManager.getDeviceId();
            return deviceID;
        } catch (Exception e) {
            Log.printStackTrace(e);
        }
        return "";
    }

    public String getAndroidID() {
        try {
            androidId = android.provider.Settings.Secure.getString(mContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            return androidId;
        } catch (Exception e) {
            Log.printStackTrace(e);
        }
        return "";
    }

    public String getDeviceUUID() {
        try {

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) deviceID.hashCode() << 32));
            return deviceUuid.toString();
        } catch (Exception e) {
            //
        }
        return "";
    }

    String getSimSerialNumber() {
        try {
            return telephonyManager.getSimSerialNumber();
        } catch (Exception e) {
            //
        }
        return "";
    }
}
