package app.rk.food.device;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Class to get the device phone state/ telephone information
 * Allows to fetch the information about the TELEPHONY_SERVICE
 */

class TelephoneInfo {
    private TelephonyManager telephonyManager;

    /**
     * Constructor
     * @param mContext
     * To initialize telephony manager with context
     */

    TelephoneInfo(Context mContext) {
        telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

    int getCallState() {
        return telephonyManager.getCallState();
    }

    String getCellLocation() {
        return "" + telephonyManager.getCellLocation();
    }

    int getDataActivity() {
        return telephonyManager.getDataActivity();
    }

    int getDataState() {
        return telephonyManager.getDataState();
    }

    String getDeviceSoftwareVersion() {
        return telephonyManager.getDeviceSoftwareVersion();
    }

    String getLine1Number() {
        return telephonyManager.getLine1Number();
    }

    String getNetworkCountryIso() {
        return telephonyManager.getNetworkCountryIso();
    }

    String getNetworkOperator() {
        return telephonyManager.getNetworkOperator();
    }

    String getNetworkOperatorName() {
        return telephonyManager.getNetworkOperatorName();
    }

    int getNetworkType() {
        return telephonyManager.getNetworkType();
    }

    int getPhoneType() {
        return telephonyManager.getPhoneType();
    }

    String getSimCountryIso() {
        return  telephonyManager.getSimCountryIso();
    }

    String getSimOperator() {
        return telephonyManager.getSimOperator();
    }

    String getSimOperatorName() {
        return telephonyManager.getSimOperatorName();
    }

    String getSimSerialNumber() {
        return  telephonyManager.getSimSerialNumber();
    }

    int getSimState() {
        return  telephonyManager.getSimState();
    }

    String getSubscriberId() {
        return  telephonyManager.getSubscriberId();
    }

    String getVoiceMailAlphaTag() {
        return telephonyManager.getVoiceMailAlphaTag();
    }

    String getVoiceMailNumber() {
        return telephonyManager.getVoiceMailNumber();
    }

    boolean hasIccCard() {
        return telephonyManager.hasIccCard();
    }

    boolean isNetworkRoaming() {
        return telephonyManager.isNetworkRoaming();
    }
}
