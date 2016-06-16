package app.rk.food.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


public class AnalyticsService {

    private static final String TAG = AnalyticsService.class.getSimpleName();

    private static void logGhostVersion(@Nullable String ghostVersion) {
        if (ghostVersion == null) {
            ghostVersion = "Unknown";
        }
    }

    private static void logLogin(String blogType, boolean success) {
        String successStr = success ? "SUCCEEDED" : "FAILED";
    }



    public static void logDbSchemaVersion(@NonNull String dbSchemaVersion) {
    }


    // post new food item
    public static void logNewFoodItemUploaded() {
        logAction("New food item uploaded", null);
    }


    private static void logAction(@NonNull String postAction, @Nullable String postUrl) {
    }

}
