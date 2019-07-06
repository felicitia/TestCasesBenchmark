package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.SampleQueue.UpstreamFormatChangedListener;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.Loader.Loadable;
import com.google.android.exoplayer2.upstream.Loader.ReleaseCallback;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

final class ExtractorMediaPeriod implements ExtractorOutput, MediaPeriod, UpstreamFormatChangedListener, Callback<ExtractingLoadable>, ReleaseCallback {
    private int actualMinLoadableRetryCount;
    private final Allocator allocator;
    /* access modifiers changed from: private */
    public MediaPeriod.Callback callback;
    /* access modifiers changed from: private */
    public final long continueLoadingCheckIntervalBytes;
    /* access modifiers changed from: private */
    public final String customCacheKey;
    private final DataSource dataSource;
    private long durationUs;
    private int enabledTrackCount;
    private final EventDispatcher eventDispatcher;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean haveAudioVideoTracks;
    private long lastSeekPositionUs;
    private long length;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final Loader loader = new Loader("Loader:ExtractorMediaPeriod");
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final int minLoadableRetryCount;
    private boolean notifyDiscontinuity;
    /* access modifiers changed from: private */
    public final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private long pendingResetPositionUs;
    private boolean prepared;
    /* access modifiers changed from: private */
    public boolean released;
    private int[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private boolean[] trackEnabledStates;
    private boolean[] trackFormatNotificationSent;
    private boolean[] trackIsAudioVideoFlags;
    private TrackGroupArray tracks;
    private final Uri uri;

    final class ExtractingLoadable implements Loadable {
        /* access modifiers changed from: private */
        public long bytesLoaded;
        private final DataSource dataSource;
        /* access modifiers changed from: private */
        public DataSpec dataSpec;
        private final ExtractorHolder extractorHolder;
        /* access modifiers changed from: private */
        public long length = -1;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private boolean pendingExtractorSeek = true;
        private final PositionHolder positionHolder = new PositionHolder();
        /* access modifiers changed from: private */
        public long seekTimeUs;
        private final Uri uri;

        public ExtractingLoadable(Uri uri2, DataSource dataSource2, ExtractorHolder extractorHolder2, ConditionVariable conditionVariable) {
            this.uri = (Uri) Assertions.checkNotNull(uri2);
            this.dataSource = (DataSource) Assertions.checkNotNull(dataSource2);
            this.extractorHolder = (ExtractorHolder) Assertions.checkNotNull(extractorHolder2);
            this.loadCondition = conditionVariable;
        }

        public void setLoadPosition(long j, long j2) {
            this.positionHolder.position = j;
            this.seekTimeUs = j2;
            this.pendingExtractorSeek = true;
        }

        public void cancelLoad() {
            this.loadCanceled = true;
        }

        public boolean isLoadCanceled() {
            return this.loadCanceled;
        }

        public void load() throws IOException, InterruptedException {
            DefaultExtractorInput defaultExtractorInput;
            int i = 0;
            while (i == 0 && !this.loadCanceled) {
                try {
                    long j = this.positionHolder.position;
                    DataSpec dataSpec2 = new DataSpec(this.uri, j, -1, ExtractorMediaPeriod.this.customCacheKey);
                    this.dataSpec = dataSpec2;
                    this.length = this.dataSource.open(this.dataSpec);
                    if (this.length != -1) {
                        this.length += j;
                    }
                    defaultExtractorInput = new DefaultExtractorInput(this.dataSource, j, this.length);
                    try {
                        Extractor selectExtractor = this.extractorHolder.selectExtractor(defaultExtractorInput, this.dataSource.getUri());
                        if (this.pendingExtractorSeek) {
                            selectExtractor.seek(j, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }
                        while (i == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            int read = selectExtractor.read(defaultExtractorInput, this.positionHolder);
                            try {
                                if (defaultExtractorInput.getPosition() > j + ExtractorMediaPeriod.this.continueLoadingCheckIntervalBytes) {
                                    j = defaultExtractorInput.getPosition();
                                    this.loadCondition.close();
                                    ExtractorMediaPeriod.this.handler.post(ExtractorMediaPeriod.this.onContinueLoadingRequestedRunnable);
                                }
                                i = read;
                            } catch (Throwable th) {
                                th = th;
                                i = read;
                                if (!(i == 1 || defaultExtractorInput == null)) {
                                    this.positionHolder.position = defaultExtractorInput.getPosition();
                                    this.bytesLoaded = this.positionHolder.position - this.dataSpec.absoluteStreamPosition;
                                }
                                Util.closeQuietly(this.dataSource);
                                throw th;
                            }
                        }
                        if (i == 1) {
                            i = 0;
                        } else if (defaultExtractorInput != null) {
                            this.positionHolder.position = defaultExtractorInput.getPosition();
                            this.bytesLoaded = this.positionHolder.position - this.dataSpec.absoluteStreamPosition;
                        }
                        Util.closeQuietly(this.dataSource);
                    } catch (Throwable th2) {
                        th = th2;
                        this.positionHolder.position = defaultExtractorInput.getPosition();
                        this.bytesLoaded = this.positionHolder.position - this.dataSpec.absoluteStreamPosition;
                        Util.closeQuietly(this.dataSource);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    defaultExtractorInput = null;
                    this.positionHolder.position = defaultExtractorInput.getPosition();
                    this.bytesLoaded = this.positionHolder.position - this.dataSpec.absoluteStreamPosition;
                    Util.closeQuietly(this.dataSource);
                    throw th;
                }
            }
        }
    }

    private static final class ExtractorHolder {
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] extractorArr, ExtractorOutput extractorOutput2) {
            this.extractors = extractorArr;
            this.extractorOutput = extractorOutput2;
        }

        public Extractor selectExtractor(ExtractorInput extractorInput, Uri uri) throws IOException, InterruptedException {
            if (this.extractor != null) {
                return this.extractor;
            }
            Extractor[] extractorArr = this.extractors;
            int length = extractorArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Extractor extractor2 = extractorArr[i];
                try {
                    if (extractor2.sniff(extractorInput)) {
                        this.extractor = extractor2;
                        extractorInput.resetPeekPosition();
                        break;
                    }
                    extractorInput.resetPeekPosition();
                    i++;
                } catch (EOFException unused) {
                } catch (Throwable th) {
                    extractorInput.resetPeekPosition();
                    throw th;
                }
            }
            if (this.extractor == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("None of the available extractors (");
                sb.append(Util.getCommaDelimitedSimpleClassNames(this.extractors));
                sb.append(") could read the stream.");
                throw new UnrecognizedInputFormatException(sb.toString(), uri);
            }
            this.extractor.init(this.extractorOutput);
            return this.extractor;
        }

        public void release() {
            if (this.extractor != null) {
                this.extractor.release();
                this.extractor = null;
            }
        }
    }

    interface Listener {
        void onSourceInfoRefreshed(long j, boolean z);
    }

    private final class SampleStreamImpl implements SampleStream {
        /* access modifiers changed from: private */
        public final int track;

        public SampleStreamImpl(int i) {
            this.track = i;
        }

        public boolean isReady() {
            return ExtractorMediaPeriod.this.isReady(this.track);
        }

        public void maybeThrowError() throws IOException {
            ExtractorMediaPeriod.this.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            return ExtractorMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, z);
        }

        public int skipData(long j) {
            return ExtractorMediaPeriod.this.skipData(this.track, j);
        }
    }

    public void reevaluateBuffer(long j) {
    }

    public ExtractorMediaPeriod(Uri uri2, DataSource dataSource2, Extractor[] extractorArr, int i, EventDispatcher eventDispatcher2, Listener listener2, Allocator allocator2, String str, int i2) {
        this.uri = uri2;
        this.dataSource = dataSource2;
        this.minLoadableRetryCount = i;
        this.eventDispatcher = eventDispatcher2;
        this.listener = listener2;
        this.allocator = allocator2;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = (long) i2;
        this.extractorHolder = new ExtractorHolder(extractorArr, this);
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new Runnable() {
            public void run() {
                ExtractorMediaPeriod.this.maybeFinishPrepare();
            }
        };
        this.onContinueLoadingRequestedRunnable = new Runnable() {
            public void run() {
                if (!ExtractorMediaPeriod.this.released) {
                    ExtractorMediaPeriod.this.callback.onContinueLoadingRequested(ExtractorMediaPeriod.this);
                }
            }
        };
        this.handler = new Handler();
        this.sampleQueueTrackIds = new int[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = -9223372036854775807L;
        this.length = -1;
        this.durationUs = -9223372036854775807L;
        if (i == -1) {
            i = 3;
        }
        this.actualMinLoadableRetryCount = i;
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue discardToEnd : this.sampleQueues) {
                discardToEnd.discardToEnd();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
    }

    public void onLoaderReleased() {
        for (SampleQueue reset : this.sampleQueues) {
            reset.reset();
        }
        this.extractorHolder.release();
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.loadCondition.open();
        startLoading();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        Assertions.checkState(this.prepared);
        int i = this.enabledTrackCount;
        int i2 = 0;
        for (int i3 = 0; i3 < trackSelectionArr.length; i3++) {
            if (sampleStreamArr[i3] != null && (trackSelectionArr[i3] == null || !zArr[i3])) {
                int access$300 = sampleStreamArr[i3].track;
                Assertions.checkState(this.trackEnabledStates[access$300]);
                this.enabledTrackCount--;
                this.trackEnabledStates[access$300] = false;
                sampleStreamArr[i3] = null;
            }
        }
        boolean z = !this.seenFirstTrackSelection ? j != 0 : i == 0;
        for (int i4 = 0; i4 < trackSelectionArr.length; i4++) {
            if (sampleStreamArr[i4] == null && trackSelectionArr[i4] != null) {
                TrackSelection trackSelection = trackSelectionArr[i4];
                Assertions.checkState(trackSelection.length() == 1);
                Assertions.checkState(trackSelection.getIndexInTrackGroup(0) == 0);
                int indexOf = this.tracks.indexOf(trackSelection.getTrackGroup());
                Assertions.checkState(!this.trackEnabledStates[indexOf]);
                this.enabledTrackCount++;
                this.trackEnabledStates[indexOf] = true;
                sampleStreamArr[i4] = new SampleStreamImpl(indexOf);
                zArr2[i4] = true;
                if (!z) {
                    SampleQueue sampleQueue = this.sampleQueues[indexOf];
                    sampleQueue.rewind();
                    z = sampleQueue.advanceTo(j, true, true) == -1 && sampleQueue.getReadIndex() != 0;
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length2 = sampleQueueArr.length;
                while (i2 < length2) {
                    sampleQueueArr[i2].discardToEnd();
                    i2++;
                }
                this.loader.cancelLoading();
            } else {
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length3 = sampleQueueArr2.length;
                while (i2 < length3) {
                    sampleQueueArr2[i2].reset();
                    i2++;
                }
            }
        } else if (z) {
            j = seekToUs(j);
            while (i2 < sampleStreamArr.length) {
                if (sampleStreamArr[i2] != null) {
                    zArr2[i2] = true;
                }
                i2++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j;
    }

    public void discardBuffer(long j, boolean z) {
        int length2 = this.sampleQueues.length;
        for (int i = 0; i < length2; i++) {
            this.sampleQueues[i].discardTo(j, z, this.trackEnabledStates[i]);
        }
    }

    public boolean continueLoading(long j) {
        if (this.loadingFinished || this.pendingDeferredRetry || (this.prepared && this.enabledTrackCount == 0)) {
            return false;
        }
        boolean open = this.loadCondition.open();
        if (!this.loader.isLoading()) {
            startLoading();
            open = true;
        }
        return open;
    }

    public long getNextLoadPositionUs() {
        if (this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        return getBufferedPositionUs();
    }

    public long readDiscontinuity() {
        if (!this.notifyDiscontinuity || (!this.loadingFinished && getExtractedSamplesCount() <= this.extractedSamplesCountAtStartOfLoad)) {
            return -9223372036854775807L;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    public long getBufferedPositionUs() {
        long j;
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            j = Long.MAX_VALUE;
            int length2 = this.sampleQueues.length;
            for (int i = 0; i < length2; i++) {
                if (this.trackIsAudioVideoFlags[i]) {
                    j = Math.min(j, this.sampleQueues[i].getLargestQueuedTimestampUs());
                }
            }
        } else {
            j = getLargestQueuedTimestampUs();
        }
        if (j == Long.MIN_VALUE) {
            j = this.lastSeekPositionUs;
        }
        return j;
    }

    public long seekToUs(long j) {
        if (!this.seekMap.isSeekable()) {
            j = 0;
        }
        this.lastSeekPositionUs = j;
        this.notifyDiscontinuity = false;
        if (!isPendingReset() && seekInsideBufferUs(j)) {
            return j;
        }
        this.pendingDeferredRetry = false;
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
        } else {
            for (SampleQueue reset : this.sampleQueues) {
                reset.reset();
            }
        }
        return j;
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        if (!this.seekMap.isSeekable()) {
            return 0;
        }
        SeekPoints seekPoints = this.seekMap.getSeekPoints(j);
        return Util.resolveSeekPositionUs(j, seekParameters, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    /* access modifiers changed from: 0000 */
    public boolean isReady(int i) {
        return !suppressRead() && (this.loadingFinished || this.sampleQueues[i].hasNextSample());
    }

    /* access modifiers changed from: 0000 */
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError(this.actualMinLoadableRetryCount);
    }

    /* access modifiers changed from: 0000 */
    public int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (suppressRead()) {
            return -3;
        }
        int read = this.sampleQueues[i].read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
        if (read == -4) {
            maybeNotifyTrackFormat(i);
        } else if (read == -3) {
            maybeStartDeferredRetry(i);
        }
        return read;
    }

    /* access modifiers changed from: 0000 */
    public int skipData(int i, long j) {
        int i2 = 0;
        if (suppressRead()) {
            return 0;
        }
        SampleQueue sampleQueue = this.sampleQueues[i];
        if (!this.loadingFinished || j <= sampleQueue.getLargestQueuedTimestampUs()) {
            int advanceTo = sampleQueue.advanceTo(j, true, true);
            if (advanceTo != -1) {
                i2 = advanceTo;
            }
        } else {
            i2 = sampleQueue.advanceToEnd();
        }
        if (i2 > 0) {
            maybeNotifyTrackFormat(i);
        } else {
            maybeStartDeferredRetry(i);
        }
        return i2;
    }

    private void maybeNotifyTrackFormat(int i) {
        if (!this.trackFormatNotificationSent[i]) {
            Format format = this.tracks.get(i).getFormat(0);
            this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(format.sampleMimeType), format, 0, null, this.lastSeekPositionUs);
            this.trackFormatNotificationSent[i] = true;
        }
    }

    private void maybeStartDeferredRetry(int i) {
        if (this.pendingDeferredRetry && this.trackIsAudioVideoFlags[i] && !this.sampleQueues[i].hasNextSample()) {
            this.pendingResetPositionUs = 0;
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = true;
            this.lastSeekPositionUs = 0;
            this.extractedSamplesCountAtStartOfLoad = 0;
            for (SampleQueue reset : this.sampleQueues) {
                reset.reset();
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    private boolean suppressRead() {
        return this.notifyDiscontinuity || isPendingReset();
    }

    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j, long j2) {
        if (this.durationUs == -9223372036854775807L) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            this.durationUs = largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : largestQueuedTimestampUs + 10000;
            this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap.isSeekable());
        }
        this.eventDispatcher.loadCompleted(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, j, j2, extractingLoadable.bytesLoaded);
        copyLengthFromLoader(extractingLoadable);
        this.loadingFinished = true;
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, j, j2, extractingLoadable.bytesLoaded);
        if (!z) {
            copyLengthFromLoader(extractingLoadable);
            for (SampleQueue reset : this.sampleQueues) {
                reset.reset();
            }
            if (this.enabledTrackCount > 0) {
                this.callback.onContinueLoadingRequested(this);
            }
        }
    }

    public int onLoadError(ExtractingLoadable extractingLoadable, long j, long j2, IOException iOException) {
        boolean z;
        ExtractingLoadable extractingLoadable2;
        boolean isLoadableExceptionFatal = isLoadableExceptionFatal(iOException);
        this.eventDispatcher.loadError(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, j, j2, extractingLoadable.bytesLoaded, iOException, isLoadableExceptionFatal);
        copyLengthFromLoader(extractingLoadable);
        if (isLoadableExceptionFatal) {
            return 3;
        }
        int extractedSamplesCount = getExtractedSamplesCount();
        int i = 0;
        if (extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad) {
            extractingLoadable2 = extractingLoadable;
            z = true;
        } else {
            extractingLoadable2 = extractingLoadable;
            z = false;
        }
        if (!configureRetry(extractingLoadable2, extractedSamplesCount)) {
            i = 2;
        } else if (z) {
            i = 1;
        }
        return i;
    }

    public TrackOutput track(int i, int i2) {
        int length2 = this.sampleQueues.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (this.sampleQueueTrackIds[i3] == i) {
                return this.sampleQueues[i3];
            }
        }
        SampleQueue sampleQueue = new SampleQueue(this.allocator);
        sampleQueue.setUpstreamFormatChangeListener(this);
        int i4 = length2 + 1;
        this.sampleQueueTrackIds = Arrays.copyOf(this.sampleQueueTrackIds, i4);
        this.sampleQueueTrackIds[length2] = i;
        this.sampleQueues = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i4);
        this.sampleQueues[length2] = sampleQueue;
        return sampleQueue;
    }

    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void seekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    /* access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (!this.released && !this.prepared && this.seekMap != null && this.sampleQueuesBuilt) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            int length2 = sampleQueueArr.length;
            int i = 0;
            while (i < length2) {
                if (sampleQueueArr[i].getUpstreamFormat() != null) {
                    i++;
                } else {
                    return;
                }
            }
            this.loadCondition.close();
            int length3 = this.sampleQueues.length;
            TrackGroup[] trackGroupArr = new TrackGroup[length3];
            this.trackIsAudioVideoFlags = new boolean[length3];
            this.trackEnabledStates = new boolean[length3];
            this.trackFormatNotificationSent = new boolean[length3];
            this.durationUs = this.seekMap.getDurationUs();
            int i2 = 0;
            while (true) {
                boolean z = true;
                if (i2 >= length3) {
                    break;
                }
                Format upstreamFormat = this.sampleQueues[i2].getUpstreamFormat();
                trackGroupArr[i2] = new TrackGroup(upstreamFormat);
                String str = upstreamFormat.sampleMimeType;
                if (!MimeTypes.isVideo(str) && !MimeTypes.isAudio(str)) {
                    z = false;
                }
                this.trackIsAudioVideoFlags[i2] = z;
                this.haveAudioVideoTracks = z | this.haveAudioVideoTracks;
                i2++;
            }
            this.tracks = new TrackGroupArray(trackGroupArr);
            if (this.minLoadableRetryCount == -1 && this.length == -1 && this.seekMap.getDurationUs() == -9223372036854775807L) {
                this.actualMinLoadableRetryCount = 6;
            }
            this.prepared = true;
            this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap.isSeekable());
            this.callback.onPrepared(this);
        }
    }

    private void copyLengthFromLoader(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            this.length = extractingLoadable.length;
        }
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            if (this.durationUs == -9223372036854775807L || this.pendingResetPositionUs < this.durationUs) {
                extractingLoadable.setLoadPosition(this.seekMap.getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
                this.pendingResetPositionUs = -9223372036854775807L;
            } else {
                this.loadingFinished = true;
                this.pendingResetPositionUs = -9223372036854775807L;
                return;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.eventDispatcher.loadStarted(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, this.loader.startLoading(extractingLoadable, this, this.actualMinLoadableRetryCount));
    }

    private boolean configureRetry(ExtractingLoadable extractingLoadable, int i) {
        if (this.length == -1 && (this.seekMap == null || this.seekMap.getDurationUs() == -9223372036854775807L)) {
            if (!this.prepared || suppressRead()) {
                this.notifyDiscontinuity = this.prepared;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue reset : this.sampleQueues) {
                    reset.reset();
                }
                extractingLoadable.setLoadPosition(0, 0);
                return true;
            }
            this.pendingDeferredRetry = true;
            return false;
        }
        this.extractedSamplesCountAtStartOfLoad = i;
        return true;
    }

    private boolean seekInsideBufferUs(long j) {
        int length2 = this.sampleQueues.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length2) {
                return true;
            }
            SampleQueue sampleQueue = this.sampleQueues[i];
            sampleQueue.rewind();
            if (sampleQueue.advanceTo(j, true, false) == -1) {
                z = false;
            }
            if (z || (!this.trackIsAudioVideoFlags[i] && this.haveAudioVideoTracks)) {
                i++;
            }
        }
        return false;
    }

    private int getExtractedSamplesCount() {
        int i = 0;
        for (SampleQueue writeIndex : this.sampleQueues) {
            i += writeIndex.getWriteIndex();
        }
        return i;
    }

    private long getLargestQueuedTimestampUs() {
        long j = Long.MIN_VALUE;
        for (SampleQueue largestQueuedTimestampUs : this.sampleQueues) {
            j = Math.max(j, largestQueuedTimestampUs.getLargestQueuedTimestampUs());
        }
        return j;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private static boolean isLoadableExceptionFatal(IOException iOException) {
        return iOException instanceof UnrecognizedInputFormatException;
    }
}
