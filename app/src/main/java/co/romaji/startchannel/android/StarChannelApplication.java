package co.romaji.startchannel.android;

import android.app.Application;

/**
 * Created by nguyenvanhien on 4/3/18.
 */

public class StarChannelApplication extends Application {
    private static StarChannelApplication instance;

    public static StarChannelApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
