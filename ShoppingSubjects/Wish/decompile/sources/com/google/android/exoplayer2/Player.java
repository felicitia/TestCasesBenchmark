package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

public interface Player {

    public static abstract class DefaultEventListener implements EventListener {
        public void onLoadingChanged(boolean z) {
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        }

        public void onPlayerStateChanged(boolean z, int i) {
        }

        public void onPositionDiscontinuity(int i) {
        }

        public void onSeekProcessed() {
        }

        @Deprecated
        public void onTimelineChanged(Timeline timeline, Object obj) {
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        }

        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            onTimelineChanged(timeline, obj);
        }
    }

    public interface EventListener {
        void onLoadingChanged(boolean z);

        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);

        void onPlayerError(ExoPlaybackException exoPlaybackException);

        void onPlayerStateChanged(boolean z, int i);

        void onPositionDiscontinuity(int i);

        void onSeekProcessed();

        void onTimelineChanged(Timeline timeline, Object obj, int i);

        void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray);
    }

    void addListener(EventListener eventListener);

    int getBufferedPercentage();

    long getBufferedPosition();

    long getCurrentPosition();

    Timeline getCurrentTimeline();

    TrackSelectionArray getCurrentTrackSelections();

    int getCurrentWindowIndex();

    long getDuration();

    boolean getPlayWhenReady();

    int getPlaybackState();

    int getRendererType(int i);

    void release();

    void removeListener(EventListener eventListener);

    void seekTo(int i, long j);

    void seekTo(long j);

    void setPlayWhenReady(boolean z);
}
