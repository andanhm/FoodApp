package app.andanhm.foodworld.prefrences;
import android.content.Context;

/**
 * <h1>User session tracking activity</h1>
 */
public class UserSession {
    Context mContext;

    public static Boolean isSessionAvailable(Context mContext) {
        return new AppPreference(mContext).getSharedPreferences(Preferences.PREFERENCE_SESSION,Preferences.SESSION_STATUS_PREF,false);
    }
    public static String getAuthUser(Context mContext) {
        return new AppPreference(mContext).getSharedPreferences(Preferences.PREFERENCE_SESSION,Preferences.SESSION_EMAIL_ID_PREF,"");
    }

    public static String getAuthToken(Context mContext) {
        return new AppPreference(mContext).getSharedPreferences(Preferences.PREFERENCE_SESSION,Preferences.SESSION_TOKEN_PREF,"");
    }
    public static void setSession(Context mContext) {
         new AppPreference(mContext).getSharedPreferences(Preferences.PREFERENCE_SESSION,Preferences.SESSION_STATUS_PREF,true);
    }
    public static void setAuthUser(Context mContext,String mSessionEmail) {
        new AppPreference(mContext).getSharedPreferences(Preferences.PREFERENCE_SESSION,Preferences.SESSION_EMAIL_ID_PREF,mSessionEmail);
    }
    public static void setAuthToken(Context mContext,String mSessionToken) {
        new AppPreference(mContext).getSharedPreferences(Preferences.PREFERENCE_SESSION,Preferences.SESSION_TOKEN_PREF,mSessionToken);
    }
}
