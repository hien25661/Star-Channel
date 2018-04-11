package co.romaji.startchannel.android.networking;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import co.romaji.startchannel.android.model.ChannelInfoResult;
import co.romaji.startchannel.android.model.PlayListItem;
import co.romaji.startchannel.android.model.VideoStatistic;
import co.romaji.startchannel.android.utils.Const;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nguyenvanhien on 4/2/18.
 */

public class PasteBinAPI {
    private static PasteBinAPI restAPI;
    private GithubApiInterface githubApiInterface = ((GithubApiInterface)
            new Builder().baseUrl("https://www.googleapis.com/youtube/v3/")
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(30000, TimeUnit.SECONDS)
                            .readTimeout(30000, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory
                            .create(new GsonBuilder()
                                    .setLenient()
                                    .create()))
                                    .build().create(GithubApiInterface.class));

    public static PasteBinAPI getInstant() {
        if (restAPI == null) {
            restAPI = new PasteBinAPI();
        }
        return restAPI;
    }

    public Call<ChannelInfoResult> getChannelInfoResult(){
        return this.githubApiInterface.getChannelInfoResult();
    }

    public Call<PlayListItem> getListVideoPlayList(String playlistId){
        return this.githubApiInterface.getListVideoPlayList(playlistId, Const.API_KEY);
    }

    public Call<VideoStatistic> getVideoStatistic(String videoID){
        return this.githubApiInterface.getVideoStatistic(videoID, Const.API_KEY,"statistics");
    }
}
