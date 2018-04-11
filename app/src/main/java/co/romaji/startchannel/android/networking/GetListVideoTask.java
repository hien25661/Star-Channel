package co.romaji.startchannel.android.networking;

import android.os.AsyncTask;
import android.util.Log;

import co.romaji.startchannel.android.interfaces.SimpleCallback;
import co.romaji.startchannel.android.model.PlayListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nguyenvanhien on 4/2/18.
 */

public class GetListVideoTask extends AsyncTask<Void, Void, PlayListItem> {

    private SimpleCallback callback;
    private String playListId;
    private PlayListItem playListItem;
    public GetListVideoTask(String playListId, SimpleCallback listener) {
        this.playListId = playListId;
        this.callback = listener;
    }

    @Override
    protected PlayListItem doInBackground(Void... voids) {
        VideoAPI.getInstant().getListVideoPlayList(playListId).enqueue(new Callback<PlayListItem>() {
            @Override
            public void onResponse(Call<PlayListItem> call, Response<PlayListItem> response) {
                if(response!=null && response.body()!=null){
                    playListItem = response.body();
                    if(playListItem.getItems() !=null && playListItem.getItems().size() > 0) {
                        callback.success(playListItem);
                    }else {
                        callback.failed();
                    }
                }
            }

            @Override
            public void onFailure(Call<PlayListItem> call, Throwable t) {
                Log.e("Failed","failed");
                playListItem = null;
                callback.failed();
            }
        });

        return playListItem;
    }

    @Override
    protected void onPostExecute(PlayListItem listVideo) {
        super.onPostExecute(listVideo);


    }

}
