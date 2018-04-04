package co.romaji.startchannel.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.romaji.startchannel.android.R;
import co.romaji.startchannel.android.adapters.VideoAdapter;
import co.romaji.startchannel.android.interfaces.SimpleCallback;
import co.romaji.startchannel.android.model.PlayListItem;
import co.romaji.startchannel.android.networking.GetListVideoTask;
import co.romaji.startchannel.android.utils.Const;

/**
 * Created by nguyenvanhien on 4/4/18.
 */

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, SimpleCallback {
    @Bind(R.id.rcvListVideoPlayList)
    RecyclerView rcvListVideoPlayList;
    @Bind(R.id.youtube_view)
    YouTubePlayerView youTubePlayerView;

    private YouTubePlayer youTubePlayer;
    private boolean isFullScreen;
    private String playListID;

    private GetListVideoTask getListVideoTask;
    private List<PlayListItem.Item> listVideo = new ArrayList<>();
    private VideoAdapter videoAdapter;

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
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    youTubePlayer.loadVideo(listVideo.get(0).getSnippet().getResourceId().getVideoId());
                }
            });
            videoAdapter.setPlayVideoListener(new VideoAdapter.PlayVideoListener() {
                @Override
                public void playVideo(String videoId) {
                    youTubePlayer.loadVideo(videoId);
                }
            });

        }
    }

    @Override
    public void failed() {

    }

    private void getVideoPlayList(String playListID) {
        if (playListID != null && !playListID.equals("")) {
            getListVideoTask = new GetListVideoTask(playListID, this);
            getListVideoTask.execute();
        }
    }
}