package app.rk.food;

import android.app.Activity;
import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

import app.rk.food.analytics.AnalyticsApplication;

/**
 * Includes one-time initialization
 *
 * This is a subclass of {@link Application} used to provide shared objects for this app, such as
 * the {@link Tracker}.
 */

public class FoodApplication extends Application {
    public static final String TAG = FoodApplication.class.getSimpleName();
    public Activity currentActivity;
    private static FoodApplication mFoodApplicationInstance;
    @Override
    public void onCreate() {
        super.onCreate();

        mFoodApplicationInstance=this;
        /**
         * Google Analytics will record any uncaught exception/ Tracking user functionality in your app.
         * */
        AnalyticsApplication.initialize(this);
        AnalyticsApplication.getInstance().get(AnalyticsApplication.Target.APP);
        AnalyticsApplication.getInstance().get(AnalyticsApplication.Target.APP).enableAutoActivityTracking(false);
    }

    public static synchronized FoodApplication getInstance() {
        return mFoodApplicationInstance;
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsApplication analyticsTrackers = AnalyticsApplication.getInstance();
        return analyticsTrackers.get(AnalyticsApplication.Target.APP);
    }

    /***
     * Tracking screen view
     *
     * @param screenName screen name to be displayed on GA dashboard
     */
    public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();
        t.setScreenName(screenName);
        t.send(new HitBuilders
                .ScreenViewBuilder()
                .build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    /***
     * Tracking exception
     *
     * @param e exception to be tracked
     */
    public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();

            t.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(
                            new StandardExceptionParser(this, null)
                                    .getDescription(Thread.currentThread().getName(), e))
                    .setFatal(false)
                    .build()
            );
        }
    }

    /***
     * Tracking event
     *
     * @param category event category
     * @param action   action of the event
     * @param label    label
     */
    public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();

        // Build and send an Event.
        t.send(new HitBuilders
                .EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    /***
     * Tracking timing hit
     *
     * @param category event category
     * @param label    label
     * @param elapsedTime    time
     */
    public void trackTime(String category,String label,long elapsedTime) {
        Tracker t = getGoogleAnalyticsTracker();

        // Send a screen view.
        t.send(new HitBuilders
                .TimingBuilder()
                .setCategory(category)
                .setLabel(label)
                .setValue(elapsedTime)
                .setVariable("Duration")
                .build());
        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    public Activity getCurrentActivity(){
        return  currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity){
        this.currentActivity = currentActivity;
    }

}
