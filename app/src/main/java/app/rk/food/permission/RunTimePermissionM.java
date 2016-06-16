package app.rk.food.permission;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;

import app.rk.food.R;

/** Permission M at Run time
 *The public class allows to check permission at runtime
 */
public class RunTimePermissionM {

    public static final int PHONE_STATE_REQUEST_CODE=101;
    public static final int GET_ACCOUNTS_REQUEST_CODE=102;
    public static final int READ_SMS_REQUEST_CODE=103;
    public static final int CAMERA_REQUEST_CODE=104;
    public static final int CALL_PHONE_REQUEST_CODE=105;
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE=106;
    public static final int LOCATION_REQUEST_CODE=107;
    /**
     *  Method allows get the sdk version is M /or
     */
    public static boolean areExplicitPermissionsRequired() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     *  Method check phone state permission for M if not permission will be requested
     *  @param activity Activity context
     */
    public static void getPhoneStatePermission(Activity activity){
        try {
            if (areExplicitPermissionsRequired()) {
                int hasPermission = activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PHONE_STATE_REQUEST_CODE);
                }
            }
        } catch (Exception e) {
//            ApplicationTracker.getInstance().trackException(e);
        }
    }

    /**
     *  Method check account permission for M if not permission will be requested
     *  @param activity Activity context
     */
    public static void getAccountPermission(Activity activity){
        try {
            if (areExplicitPermissionsRequired()) {
                int hasPermission = activity.checkSelfPermission(Manifest.permission.GET_ACCOUNTS);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.GET_ACCOUNTS}, GET_ACCOUNTS_REQUEST_CODE);
                }
            }
        } catch (Exception e) {
//            ApplicationTracker.getInstance().trackException(e);
        }
    }
    /**
     *  Method check read sms permission for M if not permission will be requested
     *  @param activity Activity context
     */
    public static void getReadSMSPermission(Activity activity){
        try {
            if (areExplicitPermissionsRequired()) {
                int hasPermission = activity.checkSelfPermission(Manifest.permission.READ_SMS);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, READ_SMS_REQUEST_CODE);
                }
            }
        } catch (Exception e) {
//            ApplicationTracker.getInstance().trackException(e);
        }
    }
    /**
     *  Method check camera permission for M if not permission will be requested
     *  @param activity Activity context
     */
    public static void getCameraPermission(Activity activity){
        try {
            if (areExplicitPermissionsRequired()) {
                int hasPermission = activity.checkSelfPermission(Manifest.permission.CAMERA);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
                }
            }

        } catch (Exception e) {
//            ApplicationTracker.getInstance().trackException(e);
        }
    }
    /**
     *  Method check call permission for M if not permission will be requested
     *  @param activity Activity context
     */
    public static void getCallPermission(Activity activity){
        try {
            if (areExplicitPermissionsRequired()) {
                int hasPermission = activity.checkSelfPermission(Manifest.permission.CALL_PHONE);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_REQUEST_CODE);
                }
            }

        } catch (Exception e) {
//            ApplicationTracker.getInstance().trackException(e);
        }
    }
    /**
     *  Method check write permission for M if not permission will be requested
     *  @param activity Activity context
     */
    public static void getWriteExternalStoragePermission(Activity activity){
        try {
            if (areExplicitPermissionsRequired()) {
                int hasPermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                }
            }

        } catch (Exception e) {
//            ApplicationTracker.getInstance().trackException(e);
        }
    }
    /**
     *  Method check location permission for M if not permission will be requested
     *  @param activity Activity context
     */
    public static void getLocation(Activity activity){
        try {
            if (areExplicitPermissionsRequired()) {
                int hasPermission = activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                }
            }

        } catch (Exception e) {
//            ApplicationTracker.getInstance().trackException(e);
        }
    }
    /**
     *  Method check whether user granted phone state to read build id for M
     *  @param activity Activity context
     */
    public static void checkPhoneStatePermission(Activity activity){
        if (areExplicitPermissionsRequired()) {
            if (!isPermissionGranted(activity, Manifest.permission.READ_PHONE_STATE)) {
                show(activity, R.string.alert_permission_phone_state);
            }
        }
    }

    /**
     *  Method check whether user granted permission or not
     *  @param grantResults permission set granted by user
     */
    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    /**
     *  Method check permission granted
     *  @param activity Activity context
     *  @param permission permission that need to be checked
     */
    public static boolean isPermissionGranted(Activity activity,String permission){
        return PermissionChecker.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }
    /**
     *  Method allows to app permission intent
     *  @param activity Activity context
     */
    public static void appSettings(Activity activity){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }
    /**
     *  Method open pop up for requesting for permission again
     *  @param activity Activity context
     *  @param string_res_id Message to be displayed
     */
    public static void show(final Activity activity,int string_res_id) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(R.string.alert_permission)
                    .setCancelable(false)
                    .setMessage(string_res_id)
                    .setIcon(R.drawable.icon_error)
                    .setNegativeButton(R.string.alert_permission_open_setting,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    appSettings(activity);
                                    dialog.dismiss();
                                }
                            }).show();
        }catch (Exception e){
//            ApplicationTracker.getInstance().trackException(e);
        }
    }


    public static void getAllPermission(Activity activity){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int hasWriteContactsPermission = activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.CALL_PHONE}, 117);
                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.CALL_PHONE}, 117 );
                }
            }

        } catch (Exception e) {}
    }

    public static void checkCameraPermission(Activity activity){
        if (areExplicitPermissionsRequired()) {
            if (!isPermissionGranted(activity, Manifest.permission.CAMERA)) {
                show(activity, R.string.alert_permission_account);
            }
        }
    }
}
