package co.romaji.startchannel.android;

import android.app.Application;
import android.graphics.Typeface;

import com.ajts.androidmads.fontutils.FontUtils;

/**
 * Created by nguyenvanhien on 4/3/18.
 */

public class StarChannelApplication extends Application {
    private static StarChannelApplication instance;
    private static Typeface typeface;
    private static FontUtils fontUtils;

    public static StarChannelApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Regular.ttf");
        // Init Library
        fontUtils = new FontUtils();
    }

    public static Typeface getTypeface() {
        return typeface;
    }

    public static FontUtils getFontUtils() {
        return fontUtils;
    }
}
