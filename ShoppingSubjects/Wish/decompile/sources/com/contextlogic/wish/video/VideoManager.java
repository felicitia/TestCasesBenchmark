package com.contextlogic.wish.video;

import android.content.Context;
import com.contextlogic.wish.util.VideoUtil;
import com.google.android.exoplayer2.SimpleExoPlayer;
import java.util.HashMap;

public class VideoManager {
    private static VideoManager sInstance = new VideoManager();
    private HashMap<String, SimpleExoPlayer> mVideoPlayers = new HashMap<>();

    private VideoManager() {
    }

    public SimpleExoPlayer requestVideoPlayer(Context context, String str, String str2) {
        SimpleExoPlayer simpleExoPlayer = null;
        if (str2 == null) {
            return null;
        }
        if (this.mVideoPlayers.get(str) != null) {
            simpleExoPlayer = (SimpleExoPlayer) this.mVideoPlayers.get(str);
        } else if (this.mVideoPlayers.isEmpty()) {
            simpleExoPlayer = VideoUtil.createLoopingMP4Player(context, str2);
            this.mVideoPlayers.put(str, simpleExoPlayer);
        }
        return simpleExoPlayer;
    }

    public void releaseVideoPlayer(String str) {
        if (this.mVideoPlayers.containsKey(str)) {
            ((SimpleExoPlayer) this.mVideoPlayers.get(str)).release();
            this.mVideoPlayers.remove(str);
        }
    }

    public static VideoManager getInstance() {
        return sInstance;
    }
}
