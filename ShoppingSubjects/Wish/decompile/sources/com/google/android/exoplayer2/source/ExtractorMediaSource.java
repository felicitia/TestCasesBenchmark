package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource.Listener;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class ExtractorMediaSource implements Listener, MediaSource {
    private final int continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory;
    private final EventDispatcher eventDispatcher;
    private final ExtractorsFactory extractorsFactory;
    private final int minLoadableRetryCount;
    private Listener sourceListener;
    private long timelineDurationUs;
    private boolean timelineIsSeekable;
    private final Uri uri;

    public static final class Factory {
        private int continueLoadingCheckIntervalBytes = 1048576;
        private String customCacheKey;
        private final com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory;
        private ExtractorsFactory extractorsFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount = -1;

        public Factory(com.google.android.exoplayer2.upstream.DataSource.Factory factory) {
            this.dataSourceFactory = factory;
        }

        public ExtractorMediaSource createMediaSource(Uri uri) {
            return createMediaSource(uri, null, null);
        }

        public ExtractorMediaSource createMediaSource(Uri uri, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            this.isCreateCalled = true;
            if (this.extractorsFactory == null) {
                this.extractorsFactory = new DefaultExtractorsFactory();
            }
            ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource(uri, this.dataSourceFactory, this.extractorsFactory, this.minLoadableRetryCount, handler, mediaSourceEventListener, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
            return extractorMediaSource;
        }
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    private ExtractorMediaSource(Uri uri2, com.google.android.exoplayer2.upstream.DataSource.Factory factory, ExtractorsFactory extractorsFactory2, int i, Handler handler, MediaSourceEventListener mediaSourceEventListener, String str, int i2) {
        this.uri = uri2;
        this.dataSourceFactory = factory;
        this.extractorsFactory = extractorsFactory2;
        this.minLoadableRetryCount = i;
        this.eventDispatcher = new EventDispatcher(handler, mediaSourceEventListener);
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i2;
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, Listener listener) {
        this.sourceListener = listener;
        notifySourceInfoRefreshed(-9223372036854775807L, false);
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        ExtractorMediaPeriod extractorMediaPeriod = new ExtractorMediaPeriod(this.uri, this.dataSourceFactory.createDataSource(), this.extractorsFactory.createExtractors(), this.minLoadableRetryCount, this.eventDispatcher, this, allocator, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
        return extractorMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ExtractorMediaPeriod) mediaPeriod).release();
    }

    public void releaseSource() {
        this.sourceListener = null;
    }

    public void onSourceInfoRefreshed(long j, boolean z) {
        if (j == -9223372036854775807L) {
            j = this.timelineDurationUs;
        }
        if (this.timelineDurationUs != j || this.timelineIsSeekable != z) {
            notifySourceInfoRefreshed(j, z);
        }
    }

    private void notifySourceInfoRefreshed(long j, boolean z) {
        this.timelineDurationUs = j;
        this.timelineIsSeekable = z;
        this.sourceListener.onSourceInfoRefreshed(this, new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false), null);
    }
}
