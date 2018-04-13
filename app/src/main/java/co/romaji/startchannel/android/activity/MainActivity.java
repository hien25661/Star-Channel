package co.romaji.startchannel.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.romaji.startchannel.android.BaseActivity;
import co.romaji.startchannel.android.R;
import co.romaji.startchannel.android.adapters.PlayListAdapter;
import co.romaji.startchannel.android.model.Channel;
import co.romaji.startchannel.android.model.ChannelInfoResult;
import co.romaji.startchannel.android.networking.VideoAPI;
import co.romaji.startchannel.android.utils.Const;
import co.romaji.startchannel.android.utils.Utils;
import co.romaji.startchannel.android.utils.event.ShowDetailPlayListEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @Bind(R.id.rcvListVideo)
    RecyclerView rcvListVideo;
    @Bind(R.id.imvAvatar)
    ImageView imvAvatar;
    @Bind(R.id.tvChannelName)
    TextView tvChannelName;
    @Bind(R.id.tvChannelDescription)
    TextView tvChannelDescription;
    @Bind(R.id.tvYoutube)
    TextView tvYoutube;
    private PlayListAdapter playListAdapter;
    private ChannelInfoResult channelInfoResult;
    private LinearLayoutManager layoutManager;

    @Bind(R.id.adView)
    AdView mAdView;

    private InterstitialAd mInterstitialAd;
    private AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        loadChannel();

        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_id));

        // Request for Ads
        adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvListVideo.setLayoutManager(layoutManager);
        rcvListVideo.setHasFixedSize(true);
        Utils.applyFontForView(tvChannelName);
        Utils.applyFontForView(tvChannelDescription);
        Utils.applyFontForViewYoutube(tvYoutube);
    }

    private void loadChannel() {
        VideoAPI.getInstant().getChannelAvatar().enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Call<Channel> call, Response<Channel> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().getItems()!=null && response.body().getItems().size()>0) {
                        String channelAvatarUrl = response.body().getItems().get(0)
                                .getSnippet()
                                .getThumbnails().getHigh().getUrl();
                        Utils.fetSourceForImageView(imvAvatar,channelAvatarUrl);
                    }
                }
            }

            @Override
            public void onFailure(Call<Channel> call, Throwable t) {

            }
        });
        VideoAPI.getInstant().getChannelInfoResult().enqueue(new Callback<ChannelInfoResult>() {
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
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            Intent intent = new Intent(MainActivity.this,VideoActivity.class);
            intent.putExtra(Const.PLAYLIST_ID,event.getPlayListItem().getId());
            startActivity(intent);
        }
    }

}
