package app.rk.food.prefrences;

import android.content.Context;
import android.content.SharedPreferences;


/**
 *
 */
public class AppPreference {
    private SharedPreferences pref;
    Context mContext;

    public AppPreference(Context mContext) {
        this.mContext = mContext;
    }
    /**
     * Create a new Editor for these preferences, through which you can make
     * modifications to the data in the preferences and atomically commit those
     * changes back to the SharedPreferences object.
     *
     * @param name Desired preferences file. If a preferences file by this name
     * does not exist, it will be created when you retrieve an
     * editor (SharedPreferences.edit()) and then commit changes (Editor.commit()).
     *      * @param key The name of the preference to retrieve.
     * @param value Value that need to stored.
     *
     */
    public void setSharedPreferences(String name,String key,String value) {
        pref = mContext.getSharedPreferences(name, android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setSharedPreferences(String name,String key,boolean value) {
        pref = mContext.getSharedPreferences(name, android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    /**
     * Retrieve and hold the contents of the preferences file 'name', returning
     * a SharedPreferences through which you can retrieve and modify its
     * values.  Only one instance of the SharedPreferences object is returned
     * to any callers for the same name, meaning they will see each other's
     * edits as soon as they are made.
     *
     * @param name Desired preferences file. If a preferences file by this name
     * does not exist, it will be created when you retrieve an
     * editor (SharedPreferences.edit()) and then commit changes (Editor.commit()).
     *      * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return The single {@link SharedPreferences} instance that can be used
     *         to retrieve and modify the preference values.
     */
    public Boolean getSharedPreferences(String name,String key,Boolean defValue) {
        pref = mContext.getSharedPreferences(name, android.content.Context.MODE_PRIVATE);
        return pref.getBoolean(key, defValue);
    }

    public String getSharedPreferences(String name,String key,String defValue) {
        pref = mContext.getSharedPreferences(name, android.content.Context.MODE_PRIVATE);
        return pref.getString(key, defValue);
    }
}
