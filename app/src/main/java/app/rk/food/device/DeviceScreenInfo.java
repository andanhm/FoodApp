package app.rk.food.device;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Class to get the device screen information about
 * screen resolution
 * DPI
 * xDPI
 * yDPI
 */
public class DeviceScreenInfo {
    Context mContext;
    DisplayMetrics metrics = new DisplayMetrics();

    /**
     * Constructor
     * On pressing context WindowManager will be initialized
     **/

    public DeviceScreenInfo(Context mContext){
        this.mContext=mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
    }

    public int getScreenWidth(){
        return metrics.widthPixels;
    }
    public int getScreenHeight(){
        return metrics.heightPixels;
    }
    public int getScreenDPI(){
        return metrics.densityDpi;
    }
    public float getLCDPixelDensityX() {
          return metrics.xdpi;
    }
    public float getLCDPixelDensityY() {
        return metrics.ydpi;
    }

    private String getScreenInfo() {   //Not Working

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width= metrics.widthPixels;
        int height= metrics.heightPixels;
        int dpi = metrics.densityDpi;
        float lcdDPIX= metrics.xdpi;
        float lcdDPIY= metrics.ydpi;

        return "\n\nSCREEN"
                +"\n\nScreen Width: "+width
                +"\n\nScreen Height: "+height
                +"\n\nDensity DPI:"+dpi
                +"\n\nActual LCD Pixel Density X: "+lcdDPIX
                +"\n\nActual LCD Pixel Density Y: "+lcdDPIY
                +"\n\n\n\n";

    }
}
