package com.contextlogic.wish.util;

import android.content.Context;
import android.media.MediaFormat;
import android.net.Uri;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import java.io.IOException;
import org.m4m.android.VideoFormatAndroid;

public class VideoUtil {

    public static class CustomVideoFormat extends VideoFormatAndroid {
        private MediaFormat mediaFormat;

        CustomVideoFormat(String str, int i, int i2) {
            super(str, i, i2);
            this.mediaFormat = MediaFormat.createVideoFormat(str, i, i2);
            setVideoFrameSize(i, i2);
            setVideoCodec(str);
        }

        public MediaFormat getNativeFormat() {
            if (this.mediaFormat.containsKey("rotation-degrees")) {
                this.mediaFormat.setInteger("rotation-degrees", 0);
            }
            return this.mediaFormat;
        }

        public void setVideoBitRateInKBytes(int i) {
            setInteger("bitrate", i * 1024);
        }

        public void setInteger(String str, int i) {
            this.mediaFormat.setInteger(str, i);
        }

        public int getInteger(String str) {
            return this.mediaFormat.getInteger(str);
        }

        /* access modifiers changed from: protected */
        public long getLong(String str) {
            return this.mediaFormat.getLong(str);
        }

        /* access modifiers changed from: protected */
        public String getString(String str) {
            return this.mediaFormat.getString(str);
        }
    }

    public interface VideoParseCallback {
        void onVideoParseFailed();

        void onVideoParsed(String str);

        void onVideoTooLong();
    }

    public static class VideoSpecification {
        public int bitRate = 5000;
        public int frameRate = 30;
        public int iFrameInterval = 1;
        public long maxDuration = -1;
        public int maxHeight = -1;
        public int maxWidth = -1;
    }

    public static SimpleExoPlayer createLoopingMP4Player(Context context, String str) {
        return createLoopingMP4Player(context, str, true);
    }

    public static SimpleExoPlayer createLoopingMP4Player(Context context, String str, boolean z) {
        return createMP4Player(context, str, z, true);
    }

    public static SimpleExoPlayer createMP4Player(Context context, String str, boolean z) {
        return createMP4Player(context, str, z, false);
    }

    private static SimpleExoPlayer createMP4Player(Context context, String str, boolean z, boolean z2) {
        SimpleExoPlayer newSimpleInstance = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), new DefaultTrackSelector(new Factory(new DefaultBandwidthMeter())), new DefaultLoadControl());
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(context, NetworkUtil.getUserAgent());
        if (str != null) {
            MediaSource createMediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(Uri.parse(str));
            if (z2) {
                createMediaSource = new LoopingMediaSource(createMediaSource);
            }
            newSimpleInstance.prepare(createMediaSource);
        }
        if (z) {
            newSimpleInstance.setVolume(0.0f);
        }
        return newSimpleInstance;
    }

    public static SimpleExoPlayer createLocalVideoPlayer(Context context, Uri uri, boolean z) {
        return createDefaultVideoPlayer(context, z, new FileDataSource(), uri);
    }

    public static SimpleExoPlayer createRawVideoPlayer(Context context, int i, boolean z) {
        return createDefaultVideoPlayer(context, z, new RawResourceDataSource(context), RawResourceDataSource.buildRawResourceUri(i));
    }

    private static SimpleExoPlayer createDefaultVideoPlayer(Context context, boolean z, final DataSource dataSource, Uri uri) {
        SimpleExoPlayer newSimpleInstance = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), new DefaultTrackSelector(), new DefaultLoadControl());
        try {
            dataSource.open(new DataSpec(uri));
            newSimpleInstance.prepare(new ExtractorMediaSource.Factory(new DataSource.Factory() {
                public DataSource createDataSource() {
                    return dataSource;
                }
            }).createMediaSource(dataSource.getUri()));
            if (z) {
                newSimpleInstance.setVolume(0.0f);
            }
            return newSimpleInstance;
        } catch (IOException unused) {
            return null;
        }
    }
}
