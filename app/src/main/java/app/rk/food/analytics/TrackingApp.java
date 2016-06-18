package app.rk.food.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LoginEvent;

/**
 * Tacking custom training
 */
public class TrackingApp {

    private static final String TAG = TrackingApp.class.getSimpleName();

    /**
     * Check where user using which version of the database
     */
    public static void logDbSchemaVersion(@NonNull String dbSchemaVersion) {
        Crashlytics.log(Log.INFO, TAG, "DB SCHEMA VERSION = " + dbSchemaVersion);
        Answers.getInstance().logCustom(new CustomEvent("DB Schema Version").putCustomAttribute("version", dbSchemaVersion));
    }

    /**
     * Tracks how oftenly user adding the new food items
     */
    public static void logNewFoodItemUploaded() {
        Crashlytics.log(Log.INFO, TAG, "POST ACTION: Food Item");
        logPostAction("New food item uploaded", null);
    }
    /**
     * Track where welcome screens useful for the user or skipped
     * @param seenType User see the tutorial or not ("SEEN" : "FAILED")
     */
    public static void logWelcomeScreen(@NonNull boolean seenType) {
        String successStr = seenType ? "SEEN" : "SKIPPED";
        Crashlytics.log(Log.INFO, TAG, "WELCOME " + successStr);
        Answers.getInstance().logCustom(new CustomEvent("Welcome")
                .putCustomAttribute("Tutorial",successStr));
    }
    /**
     * Tracks how post action of user
     */
    private static void logPostAction(@NonNull String postAction, @Nullable String postUrl) {
        CustomEvent postStatsEvent = new CustomEvent("Post Actions")
                .putCustomAttribute("Scenario", postAction);
        if (postUrl != null) {
            postStatsEvent.putCustomAttribute("URL", postUrl);
        }
        Crashlytics.log(Log.INFO, TAG, "POST ACTION: " + postAction);
        Answers.getInstance().logCustom(postStatsEvent);
    }

}
