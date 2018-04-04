package co.romaji.startchannel.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.romaji.startchannel.android.BaseActivity;
import co.romaji.startchannel.android.R;
import co.romaji.startchannel.android.adapters.PlayListAdapter;
import co.romaji.startchannel.android.model.ChannelInfoResult;
import co.romaji.startchannel.android.networking.PasteBinAPI;
import co.romaji.startchannel.android.utils.Const;
import co.romaji.startchannel.android.utils.event.ShowDetailPlayListEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @Bind(R.id.rcvListVideo)
    RecyclerView rcvListVideo;
    private PlayListAdapter playListAdapter;
    private ChannelInfoResult channelInfoResult;
    private GridLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        loadChannel();
    }

    private void initView() {
        layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        rcvListVideo.setLayoutManager(layoutManager);
        rcvListVideo.setHasFixedSize(true);
    }

    private void loadChannel() {
        PasteBinAPI.getInstant().getChannelInfoResult().enqueue(new Callback<ChannelInfoResult>() {
            @Override
            public void onResponse(Call<ChannelInfoResult> call, Response<ChannelInfoResult> response) {
                if(response!=null && response.body()!=null){
                    channelInfoResult = response.body();
                    playListAdapter = new PlayListAdapter(MainActivity.this);
                    playListAdapter.setItems((ArrayList<ChannelInfoResult.Item>) channelInfoResult.getItems());
                    rcvListVideo.setAdapter(playListAdapter);
                }
            }

            @Override
            public void onFailure(Call<ChannelInfoResult> call, Throwable t) {

            }
        });
    }

    @Subscribe
    public void showDetailPlayList(ShowDetailPlayListEvent event){
        if(event!=null && event.getPlayListItem()!=null){
            Intent intent = new Intent(MainActivity.this,VideoActivity.class);
            intent.putExtra(Const.PLAYLIST_ID,event.getPlayListItem().getId());
            startActivity(intent);
        }
    }

}
