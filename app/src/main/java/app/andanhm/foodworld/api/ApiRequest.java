package app.andanhm.foodworld.api;

import android.content.Context;

import app.andanhm.foodworld.R;
import app.andanhm.foodworld.prefrences.UserSession;
import app.andanhm.foodworld.utils.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * An Api Request HTTP class used to send and receive data over the web. Data may be of any type and length. This class may
 * be used to send and receive streaming data whose length is not known in advance.
 * <p>
 * The status code of the response obtained from the HTTP request.
 * <li>1xx: Informational</li>
 * <li>2xx: Success</li>
 * <li>3xx: Relocation/Redirection</li>
 * <li>4xx: Client Error</li>
 * <li>5xx: Server Error</li>
 * </p>
 */
@SuppressWarnings("unused")
class ApiRequest {
    private static String TAG = ApiRequest.class.getSimpleName();
    private String mToken;
    private Context mContext;
    /**
     * Sets the maximum time in milliseconds to wait while connecting.
     * Connecting to a server will fail with a {@link SocketTimeoutException} if
     * the timeout elapses before a connection is established. The default value
     * of {@code 0} causes us to do a blocking connect. This does not mean we
     * will never time out, but it probably means you'll get a TCP timeout
     * after several minutes.
     */
    private static int ConnectTimeout = 8000;

    /**
     * Constructor
     * Encrypt the email , token for server identification
     */
    public ApiRequest(Context mContext) {
        this.mContext = mContext;
        mToken = UserSession.getAuthToken(mContext);
    }

    /**
     * Method allows to HTTP GET request to the server to get data from a specified resource
     *
     * @param requestUrl URL of the API to be requested
     *                   returns response as a JSON object
     */
    public JSONObject GET(String requestUrl) {
        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-Token", mToken);
            connection.setConnectTimeout(ConnectTimeout);
            connection.setRequestMethod("GET");
            connection.connect();
            StringBuilder sb;
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jsonObject = new JSONObject(sb.toString());
            }
            connection.disconnect();
        } catch (Exception e) {
            Log.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
                }
            }
        }
        return jsonObject != null ? jsonObject : errorObj();
    }

    /**
     * Method allows to HTTP POST request to the server to send data to a specified resource
     *
     * @param requestUrl URL of the API to be requested
     * @param params     parameter that are to be send in the "body" of the request Ex: parameter=value&amp;also=another
     *                   returns response as a JSON object
     */
    public JSONObject POST(String requestUrl, Map<String, Object> params) {
        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(requestUrl);
            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-Token", mToken);
            connection.setConnectTimeout(ConnectTimeout);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);
            connection.connect();
            StringBuilder sb;
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jsonObject = new JSONObject(sb.toString());
            }
            connection.disconnect();
        } catch (Exception e) {
            Log.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
                }
            }
        }
        return jsonObject != null ? jsonObject : errorObj();
    }

    /**
     * Method allows to HTTP PUT request to the server to put data to a specified resource
     *
     * @param requestUrl URL of the API to be requested
     * @param params     parameter that are to be send in the "body" of the request Ex: parameter=value&amp;also=another
     *                   returns response as a JSON object
     */
    public JSONObject PUT(String requestUrl, Map<String, Object> params) {
        Log.e("----", requestUrl);
        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(requestUrl);
            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            Log.e("put", postData.toString());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-Token", mToken);
            connection.setConnectTimeout(ConnectTimeout);
            connection.setRequestMethod("PUT");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);
            connection.connect();
            StringBuilder sb;
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jsonObject = new JSONObject(sb.toString());
            }
            connection.disconnect();
        } catch (Exception e) {
            Log.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
                }
            }
        }
        return jsonObject != null ? jsonObject : errorObj();
    }

    /**
     * Method allows to HTTP DELETE request to the server
     *
     * @param requestUrl URL of the API to be requested
     *                   returns response as a JSON object
     */
    public JSONObject DELETE(String requestUrl) {

        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-Token", mToken);
            connection.setConnectTimeout(ConnectTimeout);
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(8000);
            connection.setUseCaches(false);
            connection.connect();
            StringBuilder sb;
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jsonObject = new JSONObject(sb.toString());
            }
            connection.disconnect();
        } catch (Exception e) {
            Log.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
                }
            }
        }
        return jsonObject != null ? jsonObject : errorObj();
    }

    private JSONObject errorObj() {
        JSONObject errObj = new JSONObject();
        try {
            errObj.put("status", false);
            errObj.put("code", 500);
            errObj.put("message", mContext.getString(R.string.server_error) );
        } catch (JSONException e) {
            Log.e(TAG, String.format("%s : %s", new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage()));
        }
        return errObj;
    }
}
