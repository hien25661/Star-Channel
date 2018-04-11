package co.romaji.startchannel.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nguyenvanhien on 4/11/18.
 */

public class VideoStatistic {
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public class Item {

        @SerializedName("kind")
        @Expose
        private String kind;
        @SerializedName("etag")
        @Expose
        private String etag;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("statistics")
        @Expose
        private Statistics statistics;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public void setStatistics(Statistics statistics) {
            this.statistics = statistics;
        }

        public class Statistics {

            @SerializedName("viewCount")
            @Expose
            private String viewCount;
            @SerializedName("likeCount")
            @Expose
            private String likeCount;
            @SerializedName("dislikeCount")
            @Expose
            private String dislikeCount;
            @SerializedName("favoriteCount")
            @Expose
            private String favoriteCount;
            @SerializedName("commentCount")
            @Expose
            private String commentCount;

            public String getViewCount() {
                return viewCount;
            }

            public void setViewCount(String viewCount) {
                this.viewCount = viewCount;
            }

            public String getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(String likeCount) {
                this.likeCount = likeCount;
            }

            public String getDislikeCount() {
                return dislikeCount;
            }

            public void setDislikeCount(String dislikeCount) {
                this.dislikeCount = dislikeCount;
            }

            public String getFavoriteCount() {
                return favoriteCount;
            }

            public void setFavoriteCount(String favoriteCount) {
                this.favoriteCount = favoriteCount;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

        }
    }
}
