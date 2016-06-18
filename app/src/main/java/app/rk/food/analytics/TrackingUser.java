package app.rk.food.analytics;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LoginEvent;
import com.crashlytics.android.answers.SignUpEvent;

/**
 * Tacking user information
 */
public class TrackingUser {
    private static final String TAG = TrackingApp.class.getSimpleName();
    /**
     * Check where user signed as anonymous / Google SignIn
     *
     * @param logType User signed as Anonymous / Google SignIn
     * @param success Singed status("SUCCEEDED" : "FAILED")
     */
    public static void logLogin(@NonNull String logType, @NonNull boolean success) {
        String successStr = success ? "SUCCEEDED" : "FAILED";
        Crashlytics.log(Log.INFO, TAG, "LOGIN " + successStr + ", blog type = " + logType);
        Answers.getInstance().logLogin(new LoginEvent()
                .putMethod(logType)
                .putSuccess(success));
    }
    /**
     * Tracks the user logout
     */
    public static void logLogut() {
        Crashlytics.log(Log.INFO, TAG, "LOGOUT SUCCEEDED");
        Answers.getInstance().logCustom(new CustomEvent("Logout"));
    }

    /**
     * Users signing up for your app in real-time,
     * understand how many users are signing up with different methods and their success rate signing up.
     * @param  method The method used to sign up
     * @param  success Whether sign up succeeded or failed
     */
    public static void logUserSignUp(@NonNull String method,@NonNull boolean success) {
        String successStr = success ? "SUCCEEDED" : "FAILED";
        Crashlytics.log(Log.INFO, TAG, "USER " + successStr);
        Answers.getInstance().logSignUp(new SignUpEvent()
                .putMethod(method)
                .putSuccess(success));
    }

    /**
     * Users users logging into your app in real-time,
     * understand how many users are logging in with different methods and their success rate logging into your app
     * @param  method The method used to sign up
     * @param  success Whether sign up succeeded or failed
     */
    public static void logUserLogIn(@NonNull String method,@NonNull boolean success) {
        String successStr = success ? "SUCCEEDED" : "FAILED";
        Crashlytics.log(Log.INFO, TAG, "USER " + successStr);
        Answers.getInstance().logLogin(new LoginEvent()
                .putMethod(method)
                .putSuccess(success));
    }

}
