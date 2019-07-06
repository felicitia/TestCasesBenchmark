package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.util.Util;

@TargetApi(16)
public final class VideoFrameReleaseTimeHelper {
    private long adjustedLastFrameTimeNs;
    private final DefaultDisplayListener displayListener;
    private long frameCount;
    private boolean haveSync;
    private long lastFramePresentationTimeUs;
    private long pendingAdjustedFrameTimeNs;
    private long syncFramePresentationTimeNs;
    private long syncUnadjustedReleaseTimeNs;
    private long vsyncDurationNs;
    private long vsyncOffsetNs;
    private final VSyncSampler vsyncSampler;
    private final WindowManager windowManager;

    @TargetApi(17)
    private final class DefaultDisplayListener implements DisplayListener {
        private final DisplayManager displayManager;

        public void onDisplayAdded(int i) {
        }

        public void onDisplayRemoved(int i) {
        }

        public DefaultDisplayListener(DisplayManager displayManager2) {
            this.displayManager = displayManager2;
        }

        public void register() {
            this.displayManager.registerDisplayListener(this, null);
        }

        public void unregister() {
            this.displayManager.unregisterDisplayListener(this);
        }

        public void onDisplayChanged(int i) {
            if (i == 0) {
                VideoFrameReleaseTimeHelper.this.updateDefaultDisplayRefreshRateParams();
            }
        }
    }

    private static final class VSyncSampler implements Callback, FrameCallback {
        private static final VSyncSampler INSTANCE = new VSyncSampler();
        private Choreographer choreographer;
        private final HandlerThread choreographerOwnerThread = new HandlerThread("ChoreographerOwner:Handler");
        private final Handler handler;
        private int observerCount;
        public volatile long sampledVsyncTimeNs = -9223372036854775807L;

        public static VSyncSampler getInstance() {
            return INSTANCE;
        }

        private VSyncSampler() {
            this.choreographerOwnerThread.start();
            this.handler = new Handler(this.choreographerOwnerThread.getLooper(), this);
            this.handler.sendEmptyMessage(0);
        }

        public void addObserver() {
            this.handler.sendEmptyMessage(1);
        }

        public void removeObserver() {
            this.handler.sendEmptyMessage(2);
        }

        public void doFrame(long j) {
            this.sampledVsyncTimeNs = j;
            this.choreographer.postFrameCallbackDelayed(this, 500);
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    createChoreographerInstanceInternal();
                    return true;
                case 1:
                    addObserverInternal();
                    return true;
                case 2:
                    removeObserverInternal();
                    return true;
                default:
                    return false;
            }
        }

        private void createChoreographerInstanceInternal() {
            this.choreographer = Choreographer.getInstance();
        }

        private void addObserverInternal() {
            this.observerCount++;
            if (this.observerCount == 1) {
                this.choreographer.postFrameCallback(this);
            }
        }

        private void removeObserverInternal() {
            this.observerCount--;
            if (this.observerCount == 0) {
                this.choreographer.removeFrameCallback(this);
                this.sampledVsyncTimeNs = -9223372036854775807L;
            }
        }
    }

    public VideoFrameReleaseTimeHelper() {
        this(null);
    }

    public VideoFrameReleaseTimeHelper(Context context) {
        WindowManager windowManager2;
        DefaultDisplayListener defaultDisplayListener = null;
        if (context == null) {
            windowManager2 = null;
        } else {
            windowManager2 = (WindowManager) context.getSystemService("window");
        }
        this.windowManager = windowManager2;
        if (this.windowManager != null) {
            if (Util.SDK_INT >= 17) {
                defaultDisplayListener = maybeBuildDefaultDisplayListenerV17(context);
            }
            this.displayListener = defaultDisplayListener;
            this.vsyncSampler = VSyncSampler.getInstance();
        } else {
            this.displayListener = null;
            this.vsyncSampler = null;
        }
        this.vsyncDurationNs = -9223372036854775807L;
        this.vsyncOffsetNs = -9223372036854775807L;
    }

    public void enable() {
        this.haveSync = false;
        if (this.windowManager != null) {
            this.vsyncSampler.addObserver();
            if (this.displayListener != null) {
                this.displayListener.register();
            }
            updateDefaultDisplayRefreshRateParams();
        }
    }

    public void disable() {
        if (this.windowManager != null) {
            if (this.displayListener != null) {
                this.displayListener.unregister();
            }
            this.vsyncSampler.removeObserver();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long adjustReleaseTime(long r21, long r23) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r3 = r23
            r5 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 * r1
            boolean r7 = r0.haveSync
            if (r7 == 0) goto L_0x004e
            long r7 = r0.lastFramePresentationTimeUs
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0020
            long r7 = r0.frameCount
            r9 = 1
            long r11 = r7 + r9
            r0.frameCount = r11
            long r7 = r0.pendingAdjustedFrameTimeNs
            r0.adjustedLastFrameTimeNs = r7
        L_0x0020:
            long r7 = r0.frameCount
            r9 = 6
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r7 = 0
            if (r11 < 0) goto L_0x0046
            long r8 = r0.syncFramePresentationTimeNs
            long r10 = r5 - r8
            long r8 = r0.frameCount
            long r10 = r10 / r8
            long r8 = r0.adjustedLastFrameTimeNs
            long r12 = r8 + r10
            boolean r8 = r0.isDriftTooLarge(r12, r3)
            if (r8 == 0) goto L_0x003d
            r0.haveSync = r7
            goto L_0x004e
        L_0x003d:
            long r7 = r0.syncUnadjustedReleaseTimeNs
            long r9 = r7 + r12
            long r7 = r0.syncFramePresentationTimeNs
            long r14 = r9 - r7
            goto L_0x0050
        L_0x0046:
            boolean r8 = r0.isDriftTooLarge(r5, r3)
            if (r8 == 0) goto L_0x004e
            r0.haveSync = r7
        L_0x004e:
            r14 = r3
            r12 = r5
        L_0x0050:
            boolean r7 = r0.haveSync
            if (r7 != 0) goto L_0x005f
            r0.syncFramePresentationTimeNs = r5
            r0.syncUnadjustedReleaseTimeNs = r3
            r3 = 0
            r0.frameCount = r3
            r3 = 1
            r0.haveSync = r3
        L_0x005f:
            r0.lastFramePresentationTimeUs = r1
            r0.pendingAdjustedFrameTimeNs = r12
            com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper$VSyncSampler r1 = r0.vsyncSampler
            if (r1 == 0) goto L_0x008b
            long r1 = r0.vsyncDurationNs
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0073
            goto L_0x008b
        L_0x0073:
            com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper$VSyncSampler r1 = r0.vsyncSampler
            long r1 = r1.sampledVsyncTimeNs
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x007c
            return r14
        L_0x007c:
            long r3 = r0.vsyncDurationNs
            r16 = r1
            r18 = r3
            long r1 = closestVsync(r14, r16, r18)
            long r3 = r0.vsyncOffsetNs
            long r5 = r1 - r3
            return r5
        L_0x008b:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper.adjustReleaseTime(long, long):long");
    }

    @TargetApi(17)
    private DefaultDisplayListener maybeBuildDefaultDisplayListenerV17(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        if (displayManager == null) {
            return null;
        }
        return new DefaultDisplayListener(displayManager);
    }

    /* access modifiers changed from: private */
    public void updateDefaultDisplayRefreshRateParams() {
        Display defaultDisplay = this.windowManager.getDefaultDisplay();
        if (defaultDisplay != null) {
            this.vsyncDurationNs = (long) (1.0E9d / ((double) defaultDisplay.getRefreshRate()));
            this.vsyncOffsetNs = (this.vsyncDurationNs * 80) / 100;
        }
    }

    private boolean isDriftTooLarge(long j, long j2) {
        return Math.abs((j2 - this.syncUnadjustedReleaseTimeNs) - (j - this.syncFramePresentationTimeNs)) > 20000000;
    }

    private static long closestVsync(long j, long j2, long j3) {
        long j4;
        long j5 = j2 + (((j - j2) / j3) * j3);
        if (j <= j5) {
            j4 = j5;
            j5 -= j3;
        } else {
            j4 = j5 + j3;
        }
        return j4 - j < j - j5 ? j4 : j5;
    }
}
