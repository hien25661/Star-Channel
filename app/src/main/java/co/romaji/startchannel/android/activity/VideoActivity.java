package co.romaji.startchannel.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.romaji.startchannel.android.R;
import co.romaji.startchannel.android.adapters.VideoAdapter;
import co.romaji.startchannel.android.interfaces.SimpleCallback;
import co.romaji.startchannel.android.model.PlayListItem;
import co.romaji.startchannel.android.model.VideoStatistic;
import co.romaji.startchannel.android.networking.GetListVideoTask;
import co.romaji.startchannel.android.networking.PasteBinAPI;
import co.romaji.startchannel.android.utils.Const;
import co.romaji.startchannel.android.utils.YtcUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nguyenvanhien on 4/4/18.
 */

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, SimpleCallback {
    @Bind(R.id.rcvListVideoPlayList)
    RecyclerView rcvListVideoPlayList;
    @Bind(R.id.youtube_view)
    YouTubePlayerView youTubePlayerView;

    @Bind(R.id.tvViewCount)
    TextView tvVideoCount;
    @Bind(R.id.tvVideoName)
    TextView tvVideoName;

    private YouTubePlayer youTubePlayer;
    private boolean isFullScreen;
    private String playListID;

    private GetListVideoTask getListVideoTask;
    private List<PlayListItem.Item> listVideo = new ArrayList<>();
    private VideoAdapter videoAdapter;

    private String currentVideoId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        youTubePlayerView.initialize(Const.API_KEY, this);
        rcvListVideoPlayList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvListVideoPlayList.setHasFixedSize(true);
        playListID = getIntent().getStringExtra(Const.PLAYLIST_ID);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.youTubePlayer = youTubePlayer;
        this.youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {

            @Override
            public void onFullscreen(boolean arg0) {
                // TODO Auto-generated method stub
                isFullScreen = arg0;
            }
        });
        getVideoPlayList(playListID);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void success(Object... params) {
        if(params!=null && params[0] instanceof PlayListItem){
            listVideo = ((PlayListItem) params[0]).getItems();
            videoAdapter = new VideoAdapter(this);
            videoAdapter.setItems((ArrayList<PlayListItem.Item>) listVideo);
            rcvListVideoPlayList.setAdapter(videoAdapter);
            currentVideoId = listVideo.get(0).getSnippet().getResourceId().getVideoId();
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    getVideoStatistic(listVideo.get(0).getSnippet().getTitle(),listVideo.get(0).getSnippet().getResourceId().getVideoId());
                }
            });
            videoAdapter.setPlayVideoListener(new VideoAdapter.PlayVideoListener() {

                @Override
                public void playVideo(String videoName, String videoId) {
                    currentVideoId = videoId;
                    getVideoStatistic(videoName,videoId);
                }
            });

        }
    }

    @Override
    public void failed() {
        finish();
    }

    private void getVideoPlayList(String playListID) {
        if (playListID != null && !playListID.equals("")) {
            getListVideoTask = new GetListVideoTask(playListID, this);
            getListVideoTask.execute();
        }
    }

    private void getVideoStatistic(final String videoName,final String videoId){
        PasteBinAPI.getInstant().getVideoStatistic(videoId).enqueue(new Callback<VideoStatistic>() {
            @Override
            public void onResponse(Call<VideoStatistic> call, Response<VideoStatistic> response) {
                youTubePlayer.loadVideo(videoId);
                if(response!=null && response.body()!=null){
                    VideoStatistic videoStatistic = response.body();
                    tvVideoName.setText(videoName);
                    if(videoStatistic.getItems()!=null && videoStatistic.getItems().size()>0){
                       VideoStatistic.Item.Statistics statistics = videoStatistic.getItems().get(0).getStatistics();
                       if(statistics!=null){
                            tvVideoCount.setText(YtcUtils.convertNumberFormat(statistics.getViewCount()) + " views");
                       }
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoStatistic> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.imvShare)
    public void shareVideo(){
        if(currentVideoId!=null){
            try {
                String linkShare = "https://www.youtube.com/watch?v=" + currentVideoId;
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, linkShare);
                startActivity(Intent.createChooser(i, "Share Video"));
            }catch (Exception ex){

            }
        }
    }



}
