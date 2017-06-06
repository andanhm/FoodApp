package app.andanhm.foodworld.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import app.andanhm.foodworld.R;


public class AppUtils {

    public static void emailDeveloper(@NonNull Context mContext) {
        String emailSubject = String.format(mContext.getString(R.string.email_subject),mContext.getString(R.string.app_name));
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "vickychijwani@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        } else {
            new Utility(mContext).toast(R.string.no_app_found);
        }
    }

}
