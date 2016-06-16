package app.rk.food.api;

import android.content.Context;

import app.rk.food.prefrences.UserSession;
import app.rk.food.utils.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * An HTTP class used to send and receive data over the web. Data may be of any type and length. This class may
 * be used to send and receive streaming data whose length is not known in advance.
 *
 * The status code of the response obtained from the HTTP request.
 * <li>1xx: Informational</li>
 * <li>2xx: Success</li>
 * <li>3xx: Relocation/Redirection</li>
 * <li>4xx: Client Error</li>
 * <li>5xx: Server Error</li>
 * </p>
 */
public class HTTPMethod {
    private String mEmail,mToken;
    /**
     * Sets the maximum time in milliseconds to wait while connecting.
     * Connecting to a server will fail with a {@link SocketTimeoutException} if
     * the timeout elapses before a connection is established. The default value
     * of {@code 0} causes us to do a blocking connect. This does not mean we
     * will never time out, but it probably means you'll get a TCP timeout
     * after several minutes.
     * */
    private static int ConnectTimeout=8000;
    /**
     * Constructor
     * Encrypt the email , token for server identification
     */
    public HTTPMethod(Context mContext){
        mEmail= UserSession.getAuthUser(mContext);
        mToken= UserSession.getAuthToken(mContext);
    }
    /**
     * Method allows to HTTP GET request to the server to get data from a specified resource
     * @param REQUEST_URL URL of the API to be requested
     * returns response as a JSON object
     */
    public JSONObject get(String REQUEST_URL) {
        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-User", mEmail);
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
                    Log.printStackTrace(e);
                }
            }
        }
        return jsonObject;
    }
    /**
     * Method allows to HTTP POST request to the server to send data to a specified resource
     * @param REQUEST_URL URL of the API to be requested
     * @param PARAMS parameter that are to be send in the "body" of the request Ex: parameter=value&amp;also=another
     * returns response as a JSON object
     */
    public JSONObject post(String REQUEST_URL,Map<String, Object> PARAMS) {
        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(REQUEST_URL);
            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : PARAMS.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-User", mEmail);
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
                    Log.printStackTrace(e);
                }
            }
        }
        return jsonObject;
    }
    /**
     * Method allows to HTTP PUT request to the server to put data to a specified resource
     * @param REQUEST_URL URL of the API to be requested
     * @param PARAMS parameter that are to be send in the "body" of the request Ex: parameter=value&amp;also=another
     * returns response as a JSON object
     */
    public JSONObject put(String REQUEST_URL,Map<String, Object> PARAMS) {
        Log.e("----",REQUEST_URL);
        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(REQUEST_URL);
            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : PARAMS.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            Log.e("put",postData.toString());
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-User", mEmail);
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
                Log.e("1111",jsonObject.toString());
            }
            connection.disconnect();
        } catch (Exception e) {
            Log.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.printStackTrace(e);
                }
            }
        }
        return jsonObject;
    }
    /**
     * Method allows to HTTP DELETE request to the server
     * @param REQUEST_URL URL of the API to be requested
     * returns response as a JSON object
     */
    public JSONObject delete(String REQUEST_URL) {
        JSONObject jsonObject = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Auth-User", mEmail);
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
                    Log.printStackTrace(e);
                }
            }
        }
        return jsonObject;
    }
}
