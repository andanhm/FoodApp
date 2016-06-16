package app.rk.food.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.widget.Toast;

import app.rk.food.R;
import app.rk.food.device.UniqueIdentifiers;


/** Share Intent
 *The public class allows to interact with other app
 */
public class ShareIntent {
    Activity activity;
    Utility utils;
    public ShareIntent(Activity activity){
        this.activity=activity;
        utils=new Utility(activity);
    }

    // Method allows to share burrow play store link to the user via some messaging app
    public void share(){
        try{
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareString = Html.fromHtml("<a href=\"http://\"> Food </a>").toString();
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "burrow");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareString);
            if (sharingIntent.resolveActivity(activity.getPackageManager()) != null)
                activity.startActivity(Intent.createChooser(sharingIntent, activity.getString(R.string.chooser_sharing_intent)));
            else {
                utils.noAppFound(activity);
            }
        }catch (Exception e){
            utils.noAppFound(activity);
        }
    }

    // Method allows to open burrow play store
    public void rateUs(){
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }

    // Method allows to open mail app to report a bug or feed back
    public void mail(String SUBJECT){
        try{
            if (SUBJECT.equals("Report a bug")){
                reportBug();
            }else {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mailIntent.setData(Uri.parse("mailto:"));
                //mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@burrow.co.in"});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
                if (mailIntent.resolveActivity(activity.getPackageManager()) != null)
                    activity.startActivity(Intent.createChooser(mailIntent, activity.getString(R.string.chooser_email_intent)));
                else
                    utils.noAppFound(activity);
            }
        }catch (Exception e){
            utils.noAppFound(activity);
        }
    }
    // Method allows to open mail app to report a bug or feed back with subject
    public void mail(Context context,String SUBJECT){
        try{
            if (SUBJECT.equals("Report a bug")){
                reportBug();
            }else {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mailIntent.setData(Uri.parse("mailto:"));
                //mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@burrow.co.in"});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
                if (mailIntent.resolveActivity(activity.getPackageManager()) != null)
                    activity.startActivity(Intent.createChooser(mailIntent, context.getString(R.string.chooser_email_intent)));
                else
                    Toast.makeText(activity, R.string.no_app_found, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(activity, R.string.no_app_found, Toast.LENGTH_SHORT).show();
        }
    }

    // Method allows to get some information about the android device
    public void reportBug(){
        try{
            Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
            mailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mailIntent.setData(Uri.parse("mailto:"));
            //mailIntent.setType("message/rfc822");
            mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@burrow.co.in"});
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report a bug");
            mailIntent.putExtra(Intent.EXTRA_TEXT, emailBody());
            if (mailIntent.resolveActivity(activity.getPackageManager())!=null)
                activity.startActivity(Intent.createChooser(mailIntent, activity.getString(R.string.chooser_email_intent)));
            else
                utils.noAppFound(activity);
        }catch (Exception e){
            utils.noAppFound(activity);
        }
    }
    // Method allows set email body android device info
    public String emailBody(){
        String body="\n\n--PLEASE DO NOT DELETE!--\n";
        body=body+"Platform: Android"+"\n";
        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            body = body + "Burrow Version: " + pInfo.versionName + "\n";
//            body = body + "Burrow Version: " + BuildConfig.VERSION_NAME + "\n";
            body = body + "Version Code: " + pInfo.versionCode + "\n";
  //          body = body + "Version Code: " + BuildConfig.VERSION_CODE + "\n";
            body = body + "Android ID: " + new UniqueIdentifiers(activity).getAndroidID() + "\n";
            body = body + "Build: " + Build.ID + "\n";
            body = body + "OS Version: " + Build.VERSION.RELEASE + "\n";
            body = body + "Brand: " + Build.BRAND + "\n";
            body = body + "Model: " + Build.MODEL + "\n";
            body = body + "Manufacturer: " + Build.MANUFACTURER + "\n";
            body = body + "Product: " + Build.PRODUCT + "\n";
        }catch (Exception e){
            Log.printStackTrace(e);
        }
        body=body+"------------------------------";
        return body;
    }
    private void gmail() {
        try{
            Intent sendIntent = new Intent();
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendIntent.setType("text/plain");
            sendIntent.setData(Uri.parse("feedback@foodiesdvg.co.in"));
            sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@burrow.co.in"});
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            if (sendIntent.resolveActivity(activity.getPackageManager())!=null)
                activity.startActivity(sendIntent);
            else
                utils.noAppFound(activity);
        }catch (Exception e){
            utils.noAppFound(activity);
        }
    }

    // Method allows to open browser
    public void faq(){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.foodiesdvg.co.in/faq"));
            if (browserIntent.resolveActivity(activity.getPackageManager())!=null)
                activity.startActivity(browserIntent);
            else
                utils.noAppFound(activity);
        }catch (Exception e){
            utils.noAppFound(activity);
        }
    }
}
