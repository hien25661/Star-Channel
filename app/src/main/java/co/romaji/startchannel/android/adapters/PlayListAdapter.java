package co.romaji.startchannel.android.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.romaji.startchannel.android.R;
import co.romaji.startchannel.android.model.ChannelInfoResult;
import co.romaji.startchannel.android.utils.EventBusUtils;
import co.romaji.startchannel.android.utils.YtcUtils;
import co.romaji.startchannel.android.utils.event.ShowDetailPlayListEvent;

/**
 * Created by nguyenvanhien on 4/3/18.
 */

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ItemViewHolder> {
    private Context mContext;
    private ArrayList<ChannelInfoResult.Item> items = new ArrayList<>();

    public PlayListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setItems(ArrayList<ChannelInfoResult.Item> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_playlist, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        mContext = v.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (position >= 0 && position < items.size()) {
            final ChannelInfoResult.Item mItem = items.get(position);
            if (mItem != null) {
                String photoUrl = mItem.getSnippet().getThumbnails().getDefault().getUrl();
                if(mItem.getSnippet().getThumbnails().getHigh()!=null){
                    photoUrl = mItem.getSnippet().getThumbnails().getHigh().getUrl();
                }else if(mItem.getSnippet().getThumbnails().getMedium()!=null){
                    photoUrl = mItem.getSnippet().getThumbnails().getMedium().getUrl();
                }
                if (photoUrl != null) {
                    int width = (int)((float)(YtcUtils.getScreenWidth()) / 2.3f);
                    int height = (int)(float)(360 * width/480.0f);
                    holder.imvThumbnail.getLayoutParams().width = width;
                    holder.imvThumbnail.getLayoutParams().height = height;
                    holder.itemView.getLayoutParams().height = height;
                    YtcUtils.fetSourceForImageView(holder.imvThumbnail,photoUrl);
                }
                holder.itemView.setBackgroundColor(Color.WHITE);
                if(position%2 != 0){
                    holder.itemView.setBackgroundColor(Color.parseColor("#cddfdf"));
                }
                if(mItem.getSnippet().getTitle()!=null){
                    holder.tvPlaylistName.setText(mItem.getSnippet().getTitle());
                }
                if(mItem.getSnippet().getDescription()!=null){
                    holder.tvPlaylistDescription.setText(mItem.getSnippet().getPublishedAt());
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBusUtils.getDefault().post(new ShowDetailPlayListEvent(mItem));
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
        @Bind(R.id.tvPlaylistName)
        TextView tvPlaylistName;
        @Bind(R.id.tvPlaylistDescription)
        TextView tvPlaylistDescription;
        @Bind(R.id.itemview)
        View itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
