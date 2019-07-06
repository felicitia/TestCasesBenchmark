package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;

public class DefaultLoadControl implements LoadControl {
    private final DefaultAllocator allocator;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final PriorityTaskManager priorityTaskManager;
    private final int targetBufferBytesOverwrite;
    private int targetBufferSize;

    public long getBackBufferDurationUs() {
        return 0;
    }

    public boolean retainBackBufferFromKeyframe() {
        return false;
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536));
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, 15000, 30000, 2500, 5000, -1, true);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z) {
        this(defaultAllocator, i, i2, i3, i4, i5, z, null);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z, PriorityTaskManager priorityTaskManager2) {
        this.allocator = defaultAllocator;
        this.minBufferUs = ((long) i) * 1000;
        this.maxBufferUs = ((long) i2) * 1000;
        this.bufferForPlaybackUs = ((long) i3) * 1000;
        this.bufferForPlaybackAfterRebufferUs = ((long) i4) * 1000;
        this.targetBufferBytesOverwrite = i5;
        this.prioritizeTimeOverSizeThresholds = z;
        this.priorityTaskManager = priorityTaskManager2;
    }

    public void onPrepared() {
        reset(false);
    }

    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        this.targetBufferSize = this.targetBufferBytesOverwrite == -1 ? calculateTargetBufferSize(rendererArr, trackSelectionArray) : this.targetBufferBytesOverwrite;
        this.allocator.setTargetBufferSize(this.targetBufferSize);
    }

    public void onStopped() {
        reset(true);
    }

    public void onReleased() {
        reset(true);
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    public boolean shouldContinueLoading(long j, float f) {
        boolean z = true;
        boolean z2 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize;
        boolean z3 = this.isBuffering;
        if (this.prioritizeTimeOverSizeThresholds) {
            if (j >= this.minBufferUs && (j > this.maxBufferUs || !this.isBuffering || z2)) {
                z = false;
            }
            this.isBuffering = z;
        } else {
            if (z2 || (j >= this.minBufferUs && (j > this.maxBufferUs || !this.isBuffering))) {
                z = false;
            }
            this.isBuffering = z;
        }
        if (!(this.priorityTaskManager == null || this.isBuffering == z3)) {
            if (this.isBuffering) {
                this.priorityTaskManager.add(0);
            } else {
                this.priorityTaskManager.remove(0);
            }
        }
        return this.isBuffering;
    }

    public boolean shouldStartPlayback(long j, float f, boolean z) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(j, f);
        long j2 = z ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        return j2 <= 0 || playoutDurationForMediaDuration >= j2 || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferSize);
    }

    /* access modifiers changed from: protected */
    public int calculateTargetBufferSize(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        int i = 0;
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            if (trackSelectionArray.get(i2) != null) {
                i += Util.getDefaultBufferSize(rendererArr[i2].getTrackType());
            }
        }
        return i;
    }

    private void reset(boolean z) {
        this.targetBufferSize = 0;
        if (this.priorityTaskManager != null && this.isBuffering) {
            this.priorityTaskManager.remove(0);
        }
        this.isBuffering = false;
        if (z) {
            this.allocator.reset();
        }
    }
}
