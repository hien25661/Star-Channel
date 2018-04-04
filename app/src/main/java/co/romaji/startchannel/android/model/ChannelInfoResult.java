package co.romaji.startchannel.android.model;

/**
 * Created by nguyenvanhien on 4/2/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ChannelInfoResult {
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;

    public class ContentDetails {
        @SerializedName("relatedPlaylists")
        @Expose
        private RelatedPlaylists relatedPlaylists;

        public RelatedPlaylists getRelatedPlaylists() {
            return this.relatedPlaylists;
        }

        public void setRelatedPlaylists(RelatedPlaylists relatedPlaylists) {
            this.relatedPlaylists = relatedPlaylists;
        }
    }

    public class Default {
        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    public class High {
        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    public class Item {
        @SerializedName("contentDetails")
        @Expose
        private ContentDetails contentDetails;
        @SerializedName("etag")
        @Expose
        private String etag;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("kind")
        @Expose
        private String kind;
        @SerializedName("snippet")
        @Expose
        private Snippet snippet;

        public String getKind() {
            return this.kind;
        }

        public void setKind(String str) {
            this.kind = str;
        }

        public String getEtag() {
            return this.etag;
        }

        public void setEtag(String str) {
            this.etag = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public Snippet getSnippet() {
            return this.snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public ContentDetails getContentDetails() {
            return this.contentDetails;
        }

        public void setContentDetails(ContentDetails contentDetails) {
            this.contentDetails = contentDetails;
        }
    }

    public class Localized {
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("title")
        @Expose
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }
    }

    public class Medium {
        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    public class PageInfo {
        @SerializedName("resultsPerPage")
        @Expose
        private Integer resultsPerPage;
        @SerializedName("totalResults")
        @Expose
        private Integer totalResults;

        public Integer getTotalResults() {
            return this.totalResults;
        }

        public void setTotalResults(Integer num) {
            this.totalResults = num;
        }

        public Integer getResultsPerPage() {
            return this.resultsPerPage;
        }

        public void setResultsPerPage(Integer num) {
            this.resultsPerPage = num;
        }
    }

    public class RelatedPlaylists {
        @SerializedName("uploads")
        @Expose
        private String uploads;
        @SerializedName("watchHistory")
        @Expose
        private String watchHistory;
        @SerializedName("watchLater")
        @Expose
        private String watchLater;

        public String getUploads() {
            return this.uploads;
        }

        public void setUploads(String str) {
            this.uploads = str;
        }

        public String getWatchHistory() {
            return this.watchHistory;
        }

        public void setWatchHistory(String str) {
            this.watchHistory = str;
        }

        public String getWatchLater() {
            return this.watchLater;
        }

        public void setWatchLater(String str) {
            this.watchLater = str;
        }
    }

    public class Snippet {
        @SerializedName("customUrl")
        @Expose
        private String customUrl;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("localized")
        @Expose
        private Localized localized;
        @SerializedName("publishedAt")
        @Expose
        private String publishedAt;
        @SerializedName("thumbnails")
        @Expose
        private Thumbnails thumbnails;
        @SerializedName("title")
        @Expose
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getCustomUrl() {
            return this.customUrl;
        }

        public void setCustomUrl(String str) {
            this.customUrl = str;
        }

        public String getPublishedAt() {
            return this.publishedAt;
        }

        public void setPublishedAt(String str) {
            this.publishedAt = str;
        }

        public Thumbnails getThumbnails() {
            return this.thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }

        public Localized getLocalized() {
            return this.localized;
        }

        public void setLocalized(Localized localized) {
            this.localized = localized;
        }
    }

    public class Thumbnails {
        @SerializedName("default")
        @Expose
        private Default _default;
        @SerializedName("high")
        @Expose
        private High high;
        @SerializedName("medium")
        @Expose
        private Medium medium;

        public Default getDefault() {
            return this._default;
        }

        public void setDefault(Default defaultR) {
            this._default = defaultR;
        }

        public Medium getMedium() {
            return this.medium;
        }

        public void setMedium(Medium medium) {
            this.medium = medium;
        }

        public High getHigh() {
            return this.high;
        }

        public void setHigh(High high) {
            this.high = high;
        }
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String str) {
        this.kind = str;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> list) {
        this.items = list;
    }
}
