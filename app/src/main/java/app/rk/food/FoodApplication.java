package app.rk.food;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.StatFs;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import java.io.File;

import app.rk.food.utils.Log;
import io.fabric.sdk.android.Fabric;

/**
 * Includes one-time initialization
 *
 * This is a subclass of {@link Application} used to provide shared objects for this app,
 */

public class FoodApplication extends Application {
    public static final String TAG = FoodApplication.class.getSimpleName();
    public Activity mCurrentActivity;
    private static FoodApplication mFoodApplicationInstance;
    private static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024;     // in bytes
    private static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024;    // in bytes

    @Override
    public void onCreate() {
        super.onCreate();

        mFoodApplicationInstance=this;
        Fabric.with(this, new Crashlytics(), new Answers());
        Log.d(TAG, "APP LAUNCHED");
    }
    public static FoodApplication getInstance() {
        return mFoodApplicationInstance;
    }

    private static long calculateDiskCacheSize(File dir) {
        long size = MIN_DISK_CACHE_SIZE;
        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            long available;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                available = statFs.getBlockCountLong() * statFs.getBlockSizeLong();
            } else {
                // checked at runtime
                //noinspection deprecation
                available = statFs.getBlockCount() * statFs.getBlockSize();
            }
            // Target 2% of the total space.
            size = available / 50;
        } catch (IllegalArgumentException ignored) {
        }
        // Bound inside min/max size for disk cache.
        return Math.max(Math.min(size, MAX_DISK_CACHE_SIZE), MIN_DISK_CACHE_SIZE);
    }

    public Activity getmCurrentActivity(){
        return mCurrentActivity;
    }

    public void setmCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }

}
