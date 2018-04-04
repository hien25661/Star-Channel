package co.romaji.startchannel.android.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Created by nguyenvanhien on 4/2/18.
 */

public class VideoIdResult {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    public class Id {
        @SerializedName("videoId")
        @Expose
        private String videoId;

        public String getVideoId() {
            return this.videoId;
        }

        public void setVideoId(String str) {
            this.videoId = str;
        }
    }

    public class Item {
        @SerializedName("id")
        @Expose
        private Id id;

        public Id getId() {
            return this.id;
        }

        public void setId(Id id) {
            this.id = id;
        }
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public void setNextPageToken(String str) {
        this.nextPageToken = str;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> list) {
        this.items = list;
    }
}
