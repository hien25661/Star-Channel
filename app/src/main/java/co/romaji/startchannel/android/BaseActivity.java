package co.romaji.startchannel.android;

import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import co.romaji.startchannel.android.utils.EventBusUtils;

/**
 * Created by nguyenvanhien on 4/4/18.
 */

public class BaseActivity extends AppCompatActivity {
    protected EventBusUtils.Holder eventBus = EventBusUtils.getDefault();
    @Override
    protected void onResume() {
        super.onResume();
        EventBusUtils.register(eventBus, this);
    }

    @Override
    protected void onStop() {
        recycleActivityView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        recycleActivityView();
        super.onDestroy();
        EventBusUtils.unregister(eventBus, this);
    }

    public void recycleActivityView(){
        try{
            Glide.get(this).getBitmapPool().clearMemory();
            Glide.get(this).clearDiskCache();
            Glide.get(this).clearMemory();
        }catch (Exception ex){}
    }
}