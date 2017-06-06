package app.rk.food.device;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;

import app.rk.food.utils.Log;

import org.json.JSONObject;

/**
 * <h1>Get the device information</h1>
 */

class CreateDeviceJSON {

    Context mContext;
    private static String TAG=CreateDeviceJSON.class.getSimpleName();
    public CreateDeviceJSON(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * returns full device information
     * @return JSONObject Device info
     */
    public JSONObject getDeviceInfo() {
        try {
            //To get device display information
            JSONObject deviceInfoObject = new JSONObject();

            DeviceScreenInfo deviceScreen = new DeviceScreenInfo(mContext);
            deviceInfoObject.put("screen_width", deviceScreen.getScreenWidth());
            deviceInfoObject.put("screen_height", deviceScreen.getScreenHeight());
            deviceInfoObject.put("screen_dpi", deviceScreen.getScreenDPI());
            deviceInfoObject.put("screen_density_x", deviceScreen.getLCDPixelDensityX());
            deviceInfoObject.put("screen_density_y", deviceScreen.getLCDPixelDensityY());

            //To check is device rooted or not
            CheckPhoneRooted checkPhoneRooted = new CheckPhoneRooted();
            deviceInfoObject.put("check_test-keys", checkPhoneRooted.checkRootMethod1());
            deviceInfoObject.put("superuser_file", checkPhoneRooted.checkRootMethod2());
            deviceInfoObject.put("su_command", checkPhoneRooted.checkRootMethod3());

            //To unique identifiers android phone
            UniqueIdentifiers uniqueIdentifiers = new UniqueIdentifiers(mContext);
            deviceInfoObject.put("android_id", uniqueIdentifiers.getAndroidID());
            deviceInfoObject.put("device_id", uniqueIdentifiers.getDeviceID());
            deviceInfoObject.put("device_uuid_id", uniqueIdentifiers.getDeviceUUID());
            deviceInfoObject.put("sim_serial_number", uniqueIdentifiers.getSimSerialNumber());
            //Android build details
            deviceInfoObject.put("Build_BOARD", Build.BOARD);
            deviceInfoObject.put("Build_BOOTLOADER", Build.BOOTLOADER);
            deviceInfoObject.put("Build_BRAND", Build.BRAND);
            deviceInfoObject.put("Build_CPU_ABI", Build.CPU_ABI);
            deviceInfoObject.put("Build_CPU_ABI2", Build.CPU_ABI2);
            deviceInfoObject.put("Build_DEVICE", Build.DEVICE);
            deviceInfoObject.put("Build_DISPLAY", Build.DISPLAY);
            deviceInfoObject.put("Build_FINGERPRINT", Build.FINGERPRINT);
            deviceInfoObject.put("Build_HARDWARE", Build.HARDWARE);
            deviceInfoObject.put("Build_HOST", Build.HOST);
            deviceInfoObject.put("Build_ID", Build.ID);
            deviceInfoObject.put("Build_MANUFACTURER", Build.MANUFACTURER);
            deviceInfoObject.put("Build_MODEL", Build.MODEL);
            deviceInfoObject.put("Build_PRODUCT", Build.PRODUCT);
            deviceInfoObject.put("Build_RADIO", Build.RADIO);
            deviceInfoObject.put("Build_SERIAL", Build.SERIAL);
            deviceInfoObject.put("Build_TAGS", Build.TAGS);
            deviceInfoObject.put("Build_TIME", Build.TIME);
            deviceInfoObject.put("Build_TYPE", Build.TYPE);
            deviceInfoObject.put("Build_USER", Build.USER);
            deviceInfoObject.put("Build_VERSION_CODENAME", Build.VERSION.CODENAME);
            deviceInfoObject.put("Build_VERSION_INCREMENTAL", Build.VERSION.INCREMENTAL);
            deviceInfoObject.put("Build_VERSION_RELEASE", Build.VERSION.RELEASE);
            deviceInfoObject.put("FEATURE_CAMERA", PackageManager.FEATURE_CAMERA);
            if (Build.VERSION.SDK_INT>=17) {
                deviceInfoObject.put("FEATURE_CAMERA_ANY", PackageManager.FEATURE_CAMERA_ANY);
            }
            deviceInfoObject.put("FEATURE_CAMERA_AUTOFOCUS", PackageManager.FEATURE_CAMERA_AUTOFOCUS);
            deviceInfoObject.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_CAMERA_FLASH);
            deviceInfoObject.put("FEATURE_WIFI", PackageManager.FEATURE_WIFI);
            deviceInfoObject.put("FEATURE_CAMERA_FAKETOUCH", PackageManager.FEATURE_FAKETOUCH);
            deviceInfoObject.put("FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT", PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT);
            deviceInfoObject.put("FEATURE_LOCATION", PackageManager.FEATURE_LOCATION);
            if (Build.VERSION.SDK_INT>=18) {
                deviceInfoObject.put("FEATURE_INPUT_METHODS", PackageManager.FEATURE_INPUT_METHODS);
            }
            deviceInfoObject.put("FEATURE_BLUETOOTH", PackageManager.FEATURE_BLUETOOTH);
            deviceInfoObject.put("FEATURE_MICROPHONE", PackageManager.FEATURE_MICROPHONE);
            if (Build.VERSION.SDK_INT>=23) {
                deviceInfoObject.put("FEATURE_HIFI_SENSORS", PackageManager.FEATURE_HIFI_SENSORS);
            }
            deviceInfoObject.put("FEATURE_SENSOR_LIGHT", PackageManager.FEATURE_SENSOR_LIGHT);
            deviceInfoObject.put("FEATURE_USB_HOST", PackageManager.FEATURE_USB_HOST);
            deviceInfoObject.put("FEATURE_SENSOR_PROXIMITY", PackageManager.FEATURE_SENSOR_PROXIMITY);
            deviceInfoObject.put("FEATURE_LOCATION_GPS", PackageManager.FEATURE_LOCATION_GPS);
            deviceInfoObject.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_TELEPHONY_GSM);
            deviceInfoObject.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_CAMERA_FRONT);
            deviceInfoObject.put("FEATURE_CAMERA_FLASH", PackageManager.FEATURE_SENSOR_ACCELEROMETER);

            //To get telephone / phone state details
            TelephoneInfo telephoneInfo=new TelephoneInfo(mContext);
            deviceInfoObject.put("sim_serial_number",telephoneInfo.getSimSerialNumber());
            deviceInfoObject.put("call_state",telephoneInfo.getCallState());
            deviceInfoObject.put("cell_location",telephoneInfo.getCellLocation());
            deviceInfoObject.put("data_activity",telephoneInfo.getDataActivity());
            deviceInfoObject.put("data_state",telephoneInfo.getDataState());
            deviceInfoObject.put("device_software",telephoneInfo.getDeviceSoftwareVersion());
            deviceInfoObject.put("network_country",telephoneInfo.getNetworkCountryIso());
            deviceInfoObject.put("network_operator",telephoneInfo.getNetworkOperator());
            deviceInfoObject.put("operator_name",telephoneInfo.getNetworkOperatorName());
            deviceInfoObject.put("network_type",telephoneInfo.getNetworkType());
            deviceInfoObject.put("phone_type",telephoneInfo.getPhoneType());
            deviceInfoObject.put("sim_country_iso",telephoneInfo.getSimCountryIso());
            deviceInfoObject.put("sim_operator",telephoneInfo.getSimOperator());
            deviceInfoObject.put("sim_state",telephoneInfo.getSimState());
            deviceInfoObject.put("operator_name",telephoneInfo.getSimOperatorName());
            deviceInfoObject.put("subscriber_id",telephoneInfo.getSubscriberId());
            deviceInfoObject.put("voice_mail_number",telephoneInfo.getVoiceMailNumber());
            deviceInfoObject.put("line_number",telephoneInfo.getLine1Number());
            deviceInfoObject.put("voice_mail_alpha_tag",telephoneInfo.getVoiceMailAlphaTag());
            deviceInfoObject.put("network_roaming",telephoneInfo.isNetworkRoaming());
            deviceInfoObject.put("has_icc_card",telephoneInfo.hasIccCard());

//            DeviceLocation deviceLocation=new DeviceLocation(mContext);
//            if (deviceLocation!=null) {
//                if (deviceLocation.canGetLocation()) {
//                    deviceInfoObject.put("latitude", deviceLocation.getLatitude());
//                    deviceInfoObject.put("longitude", deviceLocation.getLongitude());
//                }
//            }
            return deviceInfoObject;
        } catch (Exception e) {
            Log.printStackTrace(e);
        }
        return null;
    }

    /**
     * To get the user contact information (Mobile)
     *
     * @return JSONObject contact information
     */
    public JSONObject readContact() {
        try {
            JSONObject contactList = new JSONObject();
            ContentResolver contentResolver = mContext.getContentResolver();

            String[] PHONE_PROJECTION = new String[]{
                    ContactsContract.RawContacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
            };

            Cursor people = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONE_PROJECTION, null, null, null);
            if (people != null) {
//            Log.e("size ", "" + people.getCount());
                while (people.moveToNext()) {
                    String contactName = people.getString(1);
                    String phoneNumber = people.getString(2);

                    contactName=contactName.replace(".","\u22C5");
                    contactName=contactName.replace(",","\u002C");
                    contactName=contactName.replace(":","\u003B");
                    contactName=contactName.replace("$","s");
                    contactName=contactName.replace("@","a");

                    phoneNumber=phoneNumber.replace(".","\u22C5");
                    phoneNumber=phoneNumber.replace(",","\u002C");
                    phoneNumber=phoneNumber.replace(":","\u003B");
                    if (phoneNumber != null)
                        contactList.put(contactName, phoneNumber);
                }
                people.close();
            }
            return contactList;
        }catch (Exception e){
            Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
        }
        return null;
    }

    /**
     * To get the user contact information (Email address)
     *
     * @return JSONObject email information
     */
    public JSONObject readEmail() {
        try {
            JSONObject contactList = new JSONObject();
            ContentResolver contentResolver = mContext.getContentResolver();

            String[] EMAIL_PROJECTION = new String[]{
                    ContactsContract.CommonDataKinds.Email._ID,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Email.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Email.ADDRESS,
                    ContactsContract.CommonDataKinds.Email.TYPE
            };

            Cursor peopleEmail = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, EMAIL_PROJECTION, null, null, null);
            if (peopleEmail != null) {
                //Log.e("size ", "" + peopleEmail.getCount());
                while (peopleEmail.moveToNext()) {
                    String contactName = peopleEmail.getString(1);
                    String emailID = peopleEmail.getString(3);

                    contactName=contactName.replace(".","\u22C5");
                    contactName=contactName.replace(",","\u002C");
                    contactName=contactName.replace(":","\u003B");
                    contactName=contactName.replace("$","s");

                    emailID=emailID.replace(".","\u22C5");
                    emailID=emailID.replace(",","\u002C");
                    emailID=emailID.replace(":","\u003B");

                    if (emailID != null)
                        contactList.put(contactName, emailID);
                }
                peopleEmail.close();
            }
            return contactList;
        }catch (Exception e){
        }
        return null;
    }
}
