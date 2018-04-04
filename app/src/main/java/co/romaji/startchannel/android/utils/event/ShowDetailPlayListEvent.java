package co.romaji.startchannel.android.utils.event;

import co.romaji.startchannel.android.model.ChannelInfoResult;

/**
 * Created by nguyenvanhien on 4/4/18.
 */

public class ShowDetailPlayListEvent {
    private ChannelInfoResult.Item playListItem;

    public ShowDetailPlayListEvent(ChannelInfoResult.Item playListItem) {
        this.playListItem = playListItem;
    }

    public ChannelInfoResult.Item getPlayListItem() {
        return playListItem;
    }
}
