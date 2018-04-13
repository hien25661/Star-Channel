package co.romaji.startchannel.android;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.ajts.androidmads.fontutils.FontUtils;

/**
 * Created by nguyenvanhien on 4/3/18.
 */

public class StarChannelApplication extends MultiDexApplication {
    private static StarChannelApplication instance;
    private static Typeface typeface, typeFaceYoutube;
    private static FontUtils fontUtils;

    public static StarChannelApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Regular.ttf");
        typeFaceYoutube = Typeface.createFromAsset(getAssets(), "fonts/league-gothic-regular.ttf");
        // Init Library
        fontUtils = new FontUtils();
    }

    public static Typeface getTypeface() {
        return typeface;
    }

    public static Typeface getTypeFaceYoutube() {
        return typeFaceYoutube;
    }

    public static FontUtils getFontUtils() {
        return fontUtils;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
