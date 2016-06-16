package app.rk.food.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NetworkUtils {


    @StringDef({SCHEME_HTTP, SCHEME_HTTPS})
    public @interface Scheme {}

    public static final String SCHEME_HTTP = "http://";
    public static final String SCHEME_HTTPS = "https://";

    /**
     * Check whether there is any network with a usable connection.
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Returns details about the network available.
     * This may return {@code false} when there is no default network.
     * <p>This method requires the caller to hold the permission
     * {@link android.Manifest.permission#INTERNET}.
     *
     * @return a true if internet available
     *        or {@code false} if no default network is currently active
     */
    public static boolean isInternetAvailable() {

        class checkServer implements Callable<Boolean> {

            public Boolean call() throws Exception {
                boolean state = false;
                try {
                    InetAddress ipAddress = InetAddress.getByName(Constants.FOOD_API);
                    if (ipAddress != null) {
//                        Log.e("server-IP", ipAddress.toString());
                        state = true;
                    }
                } catch (Exception e) {
                    //                Log.printStackTrace(e);
                }
                return state;
            }
        }
        try {
            ExecutorService executor = Executors.newFixedThreadPool(1);
            Future<Boolean> f = executor.submit(new checkServer());
            return f.get();
        } catch (Exception e) {
            Log.printStackTrace(e);
        }

        return false;
    }
    /**To handling for protocol-relative URLs
     * @param baseUrl Base URL of the API
     * @param relativePath Path of the api
     * return AbsoluteUrl
     * */
    public static String makeAbsoluteUrl(@NonNull String baseUrl, @NonNull String relativePath) {
        // handling for protocol-relative URLs
        // can't remember which scenario actually produces these URLs except maybe the Markdown preview
        if (relativePath.startsWith("//")) {
            relativePath = "http:" + relativePath;
        }

        // maybe relativePath is already absolute
        if (relativePath.startsWith(SCHEME_HTTP) || relativePath.startsWith(SCHEME_HTTPS)) {
            return relativePath;
        }

        boolean baseHasSlash = baseUrl.endsWith("/");
        boolean relHasSlash = relativePath.startsWith("/");
        if (baseHasSlash && relHasSlash) {
            return baseUrl + relativePath.substring(1);
        } else if ((!baseHasSlash && relHasSlash) || (baseHasSlash && !relHasSlash)) {
            return baseUrl + relativePath;
        } else {
            return baseUrl + "/" + relativePath;
        }
    }

}
