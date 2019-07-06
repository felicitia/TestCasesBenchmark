package com.google.android.exoplayer2.source.hls.playlist;

import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public final class HlsPlaylistTracker implements Callback<ParsingLoadable<HlsPlaylist>> {
    /* access modifiers changed from: private */
    public final HlsDataSourceFactory dataSourceFactory;
    /* access modifiers changed from: private */
    public final EventDispatcher eventDispatcher;
    private long initialStartTimeUs;
    private boolean isLive;
    private final List<PlaylistEventListener> listeners;
    /* access modifiers changed from: private */
    public HlsMasterPlaylist masterPlaylist;
    /* access modifiers changed from: private */
    public final int minRetryCount;
    private final IdentityHashMap<HlsUrl, MediaPlaylistBundle> playlistBundles;
    /* access modifiers changed from: private */
    public final Parser<HlsPlaylist> playlistParser;
    /* access modifiers changed from: private */
    public final Handler playlistRefreshHandler;
    /* access modifiers changed from: private */
    public HlsUrl primaryHlsUrl;
    private final PrimaryPlaylistListener primaryPlaylistListener;
    private HlsMediaPlaylist primaryUrlSnapshot;

    private final class MediaPlaylistBundle implements Callback<ParsingLoadable<HlsPlaylist>>, Runnable {
        /* access modifiers changed from: private */
        public long blacklistUntilMs;
        private long earliestNextLoadTimeMs;
        private long lastSnapshotChangeMs;
        private long lastSnapshotLoadMs;
        private boolean loadPending;
        private final ParsingLoadable<HlsPlaylist> mediaPlaylistLoadable;
        private final Loader mediaPlaylistLoader = new Loader("HlsPlaylistTracker:MediaPlaylist");
        private IOException playlistError;
        private HlsMediaPlaylist playlistSnapshot;
        /* access modifiers changed from: private */
        public final HlsUrl playlistUrl;

        public MediaPlaylistBundle(HlsUrl hlsUrl) {
            this.playlistUrl = hlsUrl;
            this.mediaPlaylistLoadable = new ParsingLoadable<>(HlsPlaylistTracker.this.dataSourceFactory.createDataSource(4), UriUtil.resolveToUri(HlsPlaylistTracker.this.masterPlaylist.baseUri, hlsUrl.url), 4, HlsPlaylistTracker.this.playlistParser);
        }

        public void loadPlaylist() {
            this.blacklistUntilMs = 0;
            if (!this.loadPending && !this.mediaPlaylistLoader.isLoading()) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime < this.earliestNextLoadTimeMs) {
                    this.loadPending = true;
                    HlsPlaylistTracker.this.playlistRefreshHandler.postDelayed(this, this.earliestNextLoadTimeMs - elapsedRealtime);
                } else {
                    loadPlaylistImmediately();
                }
            }
        }

        public void onLoadCompleted(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2) {
            HlsPlaylist hlsPlaylist = (HlsPlaylist) parsingLoadable.getResult();
            if (hlsPlaylist instanceof HlsMediaPlaylist) {
                processLoadedPlaylist((HlsMediaPlaylist) hlsPlaylist);
                HlsPlaylistTracker.this.eventDispatcher.loadCompleted(parsingLoadable.dataSpec, 4, j, j2, parsingLoadable.bytesLoaded());
                return;
            }
            this.playlistError = new ParserException("Loaded playlist has unexpected type.");
        }

        public void onLoadCanceled(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, boolean z) {
            HlsPlaylistTracker.this.eventDispatcher.loadCanceled(parsingLoadable.dataSpec, 4, j, j2, parsingLoadable.bytesLoaded());
        }

        public int onLoadError(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, IOException iOException) {
            IOException iOException2 = iOException;
            boolean z = iOException2 instanceof ParserException;
            ParsingLoadable<HlsPlaylist> parsingLoadable2 = parsingLoadable;
            HlsPlaylistTracker.this.eventDispatcher.loadError(parsingLoadable2.dataSpec, 4, j, j2, parsingLoadable2.bytesLoaded(), iOException2, z);
            if (z) {
                return 3;
            }
            boolean z2 = true;
            if (ChunkedTrackBlacklistUtil.shouldBlacklist(iOException)) {
                z2 = blacklistPlaylist();
            }
            return z2 ? 0 : 2;
        }

        public void run() {
            this.loadPending = false;
            loadPlaylistImmediately();
        }

        private void loadPlaylistImmediately() {
            this.mediaPlaylistLoader.startLoading(this.mediaPlaylistLoadable, this, HlsPlaylistTracker.this.minRetryCount);
        }

        /* access modifiers changed from: private */
        public void processLoadedPlaylist(HlsMediaPlaylist hlsMediaPlaylist) {
            HlsMediaPlaylist hlsMediaPlaylist2 = this.playlistSnapshot;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.lastSnapshotLoadMs = elapsedRealtime;
            this.playlistSnapshot = HlsPlaylistTracker.this.getLatestPlaylistSnapshot(hlsMediaPlaylist2, hlsMediaPlaylist);
            if (this.playlistSnapshot != hlsMediaPlaylist2) {
                this.playlistError = null;
                this.lastSnapshotChangeMs = elapsedRealtime;
                HlsPlaylistTracker.this.onPlaylistUpdated(this.playlistUrl, this.playlistSnapshot);
            } else if (!this.playlistSnapshot.hasEndTag) {
                if (hlsMediaPlaylist.mediaSequence + ((long) hlsMediaPlaylist.segments.size()) < this.playlistSnapshot.mediaSequence) {
                    this.playlistError = new PlaylistResetException(this.playlistUrl.url);
                } else if (((double) (elapsedRealtime - this.lastSnapshotChangeMs)) > ((double) C.usToMs(this.playlistSnapshot.targetDurationUs)) * 3.5d) {
                    this.playlistError = new PlaylistStuckException(this.playlistUrl.url);
                    blacklistPlaylist();
                }
            }
            this.earliestNextLoadTimeMs = elapsedRealtime + C.usToMs(this.playlistSnapshot != hlsMediaPlaylist2 ? this.playlistSnapshot.targetDurationUs : this.playlistSnapshot.targetDurationUs / 2);
            if (this.playlistUrl == HlsPlaylistTracker.this.primaryHlsUrl && !this.playlistSnapshot.hasEndTag) {
                loadPlaylist();
            }
        }

        private boolean blacklistPlaylist() {
            this.blacklistUntilMs = SystemClock.elapsedRealtime() + 60000;
            HlsPlaylistTracker.this.notifyPlaylistBlacklisting(this.playlistUrl, 60000);
            return HlsPlaylistTracker.this.primaryHlsUrl == this.playlistUrl && !HlsPlaylistTracker.this.maybeSelectNewPrimaryUrl();
        }
    }

    public interface PlaylistEventListener {
        void onPlaylistBlacklisted(HlsUrl hlsUrl, long j);

        void onPlaylistChanged();
    }

    public static final class PlaylistResetException extends IOException {
        public final String url;

        private PlaylistResetException(String str) {
            this.url = str;
        }
    }

    public static final class PlaylistStuckException extends IOException {
        public final String url;

        private PlaylistStuckException(String str) {
            this.url = str;
        }
    }

    public interface PrimaryPlaylistListener {
        void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist);
    }

    public void onLoadCompleted(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2) {
        HlsMasterPlaylist hlsMasterPlaylist;
        HlsPlaylist hlsPlaylist = (HlsPlaylist) parsingLoadable.getResult();
        boolean z = hlsPlaylist instanceof HlsMediaPlaylist;
        if (z) {
            hlsMasterPlaylist = HlsMasterPlaylist.createSingleVariantMasterPlaylist(hlsPlaylist.baseUri);
        } else {
            hlsMasterPlaylist = (HlsMasterPlaylist) hlsPlaylist;
        }
        this.masterPlaylist = hlsMasterPlaylist;
        this.primaryHlsUrl = (HlsUrl) hlsMasterPlaylist.variants.get(0);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(hlsMasterPlaylist.variants);
        arrayList.addAll(hlsMasterPlaylist.audios);
        arrayList.addAll(hlsMasterPlaylist.subtitles);
        createBundles(arrayList);
        MediaPlaylistBundle mediaPlaylistBundle = (MediaPlaylistBundle) this.playlistBundles.get(this.primaryHlsUrl);
        if (z) {
            mediaPlaylistBundle.processLoadedPlaylist((HlsMediaPlaylist) hlsPlaylist);
        } else {
            mediaPlaylistBundle.loadPlaylist();
        }
        ParsingLoadable<HlsPlaylist> parsingLoadable2 = parsingLoadable;
        this.eventDispatcher.loadCompleted(parsingLoadable2.dataSpec, 4, j, j2, parsingLoadable2.bytesLoaded());
    }

    public void onLoadCanceled(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(parsingLoadable.dataSpec, 4, j, j2, parsingLoadable.bytesLoaded());
    }

    public int onLoadError(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, IOException iOException) {
        IOException iOException2 = iOException;
        boolean z = iOException2 instanceof ParserException;
        ParsingLoadable<HlsPlaylist> parsingLoadable2 = parsingLoadable;
        this.eventDispatcher.loadError(parsingLoadable2.dataSpec, 4, j, j2, parsingLoadable2.bytesLoaded(), iOException2, z);
        return z ? 3 : 0;
    }

    /* access modifiers changed from: private */
    public boolean maybeSelectNewPrimaryUrl() {
        List<HlsUrl> list = this.masterPlaylist.variants;
        int size = list.size();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (int i = 0; i < size; i++) {
            MediaPlaylistBundle mediaPlaylistBundle = (MediaPlaylistBundle) this.playlistBundles.get(list.get(i));
            if (elapsedRealtime > mediaPlaylistBundle.blacklistUntilMs) {
                this.primaryHlsUrl = mediaPlaylistBundle.playlistUrl;
                mediaPlaylistBundle.loadPlaylist();
                return true;
            }
        }
        return false;
    }

    private void createBundles(List<HlsUrl> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            HlsUrl hlsUrl = (HlsUrl) list.get(i);
            this.playlistBundles.put(hlsUrl, new MediaPlaylistBundle(hlsUrl));
        }
    }

    /* access modifiers changed from: private */
    public void onPlaylistUpdated(HlsUrl hlsUrl, HlsMediaPlaylist hlsMediaPlaylist) {
        if (hlsUrl == this.primaryHlsUrl) {
            if (this.primaryUrlSnapshot == null) {
                this.isLive = !hlsMediaPlaylist.hasEndTag;
                this.initialStartTimeUs = hlsMediaPlaylist.startTimeUs;
            }
            this.primaryUrlSnapshot = hlsMediaPlaylist;
            this.primaryPlaylistListener.onPrimaryPlaylistRefreshed(hlsMediaPlaylist);
        }
        int size = this.listeners.size();
        for (int i = 0; i < size; i++) {
            ((PlaylistEventListener) this.listeners.get(i)).onPlaylistChanged();
        }
    }

    /* access modifiers changed from: private */
    public void notifyPlaylistBlacklisting(HlsUrl hlsUrl, long j) {
        int size = this.listeners.size();
        for (int i = 0; i < size; i++) {
            ((PlaylistEventListener) this.listeners.get(i)).onPlaylistBlacklisted(hlsUrl, j);
        }
    }

    /* access modifiers changed from: private */
    public HlsMediaPlaylist getLatestPlaylistSnapshot(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (!hlsMediaPlaylist2.isNewerThan(hlsMediaPlaylist)) {
            return hlsMediaPlaylist2.hasEndTag ? hlsMediaPlaylist.copyWithEndTag() : hlsMediaPlaylist;
        }
        return hlsMediaPlaylist2.copyWith(getLoadedPlaylistStartTimeUs(hlsMediaPlaylist, hlsMediaPlaylist2), getLoadedPlaylistDiscontinuitySequence(hlsMediaPlaylist, hlsMediaPlaylist2));
    }

    private long getLoadedPlaylistStartTimeUs(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (hlsMediaPlaylist2.hasProgramDateTime) {
            return hlsMediaPlaylist2.startTimeUs;
        }
        long j = this.primaryUrlSnapshot != null ? this.primaryUrlSnapshot.startTimeUs : 0;
        if (hlsMediaPlaylist == null) {
            return j;
        }
        int size = hlsMediaPlaylist.segments.size();
        Segment firstOldOverlappingSegment = getFirstOldOverlappingSegment(hlsMediaPlaylist, hlsMediaPlaylist2);
        if (firstOldOverlappingSegment != null) {
            return hlsMediaPlaylist.startTimeUs + firstOldOverlappingSegment.relativeStartTimeUs;
        }
        return ((long) size) == hlsMediaPlaylist2.mediaSequence - hlsMediaPlaylist.mediaSequence ? hlsMediaPlaylist.getEndTimeUs() : j;
    }

    private int getLoadedPlaylistDiscontinuitySequence(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (hlsMediaPlaylist2.hasDiscontinuitySequence) {
            return hlsMediaPlaylist2.discontinuitySequence;
        }
        int i = this.primaryUrlSnapshot != null ? this.primaryUrlSnapshot.discontinuitySequence : 0;
        if (hlsMediaPlaylist == null) {
            return i;
        }
        Segment firstOldOverlappingSegment = getFirstOldOverlappingSegment(hlsMediaPlaylist, hlsMediaPlaylist2);
        return firstOldOverlappingSegment != null ? (hlsMediaPlaylist.discontinuitySequence + firstOldOverlappingSegment.relativeDiscontinuitySequence) - ((Segment) hlsMediaPlaylist2.segments.get(0)).relativeDiscontinuitySequence : i;
    }

    private static Segment getFirstOldOverlappingSegment(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        int i = (int) (hlsMediaPlaylist2.mediaSequence - hlsMediaPlaylist.mediaSequence);
        List<Segment> list = hlsMediaPlaylist.segments;
        if (i < list.size()) {
            return (Segment) list.get(i);
        }
        return null;
    }
}
