package co.romaji.startchannel.android.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.romaji.startchannel.android.R;
import co.romaji.startchannel.android.model.PlayListItem;
import co.romaji.startchannel.android.utils.YtcUtils;

/**
 * Created by nguyenvanhien on 4/3/18.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ItemViewHolder> {
    private Context mContext;
    private ArrayList<PlayListItem.Item> items = new ArrayList<>();
    private PlayVideoListener playVideoListener;

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setItems(ArrayList<PlayListItem.Item> items) {
        this.items = items;
    }

    public void setPlayVideoListener(PlayVideoListener playVideoListener) {
        this.playVideoListener = playVideoListener;
    }

    public interface PlayVideoListener{
        void playVideo(String videoName,String videoId);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        mContext = v.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (position >= 0 && position < items.size()) {
            final PlayListItem.Item mItem = items.get(position);
            if (mItem != null) {
                String photoUrl = mItem.getSnippet().getThumbnails().getHigh().getUrl();
                if (photoUrl != null) {
                    int width = (int)((float)(YtcUtils.getScreenWidth()) / 2);
                    int height = (int)(float)(360 * width/480.0f);
                    holder.imvThumbnail.getLayoutParams().height = height;
                    YtcUtils.fetSourceForImageView(holder.imvThumbnail,photoUrl);
                    holder.tvVideoName.setText(mItem.getSnippet().getTitle());
                }

                if(position%2 != 0){
                    holder.itemView.setBackgroundColor(Color.parseColor("#cddfdf"));
                }

                if(mItem.getSnippet().getDescription()!=null){
                    holder.tvVideoCount.setText(mItem.getSnippet().getDescription());
                }else {
                    holder.tvVideoCount.setText(mItem.getSnippet().getTitle());
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(playVideoListener != null){
                            if(mItem.getSnippet().getResourceId().getVideoId()!=null){
                                playVideoListener.playVideo
                                        (mItem.getSnippet().getTitle(),mItem.getSnippet().getResourceId().getVideoId());
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imvThumbnail)
        ImageView imvThumbnail;
        @Bind(R.id.itemview)
        View itemView;
        @Bind(R.id.viewContent)
        View viewContent;
        @Bind(R.id.tvVideoName)
        TextView tvVideoName;

        @Bind(R.id.tvVideoCount)
        TextView tvVideoCount;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
