package co.romaji.startchannel.android.networking;

import co.romaji.startchannel.android.model.Channel;
import co.romaji.startchannel.android.model.ChannelInfoResult;
import co.romaji.startchannel.android.model.PlayListItem;
import co.romaji.startchannel.android.model.VideoStatistic;
import co.romaji.startchannel.android.utils.Const;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nguyenvanhien on 4/2/18.
 */

public interface VideoApiInterface {

    @GET("playlists?part=snippet&maxResults=50&key="+ Const.API_KEY+"&channelId="+Const.CHANNEL_ID_EN)
    Call<ChannelInfoResult> getChannelInfoResult();

    @GET("playlistItems?part=snippet&maxResults=20")
    Call<PlayListItem> getListVideoPlayList(@Query("playlistId") String playlistID, @Query("key") String key);

    @GET("videos")
    Call<VideoStatistic> getVideoStatistic(@Query("id") String videoID,
                                           @Query("key") String key,
                                           @Query("part") String part);

    @GET("channels?part=snippet&id="+Const.CHANNEL_ID_EN
            +"&fields=items%2Fsnippet%2Fthumbnails&key="+Const.API_KEY)
    Call<Channel> getChannelAvatar();

}
