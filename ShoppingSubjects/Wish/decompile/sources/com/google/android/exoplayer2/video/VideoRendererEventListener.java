package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;

public interface VideoRendererEventListener {

    public static final class EventDispatcher {
        private final Handler handler;
        /* access modifiers changed from: private */
        public final VideoRendererEventListener listener;

        public EventDispatcher(Handler handler2, VideoRendererEventListener videoRendererEventListener) {
            this.handler = videoRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = videoRendererEventListener;
        }

        public void enabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onVideoEnabled(decoderCounters);
                    }
                });
            }
        }

        public void decoderInitialized(String str, long j, long j2) {
            if (this.listener != null) {
                Handler handler2 = this.handler;
                final String str2 = str;
                final long j3 = j;
                final long j4 = j2;
                AnonymousClass2 r1 = new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onVideoDecoderInitialized(str2, j3, j4);
                    }
                };
                handler2.post(r1);
            }
        }

        public void inputFormatChanged(final Format format) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onVideoInputFormatChanged(format);
                    }
                });
            }
        }

        public void droppedFrames(final int i, final long j) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onDroppedFrames(i, j);
                    }
                });
            }
        }

        public void videoSizeChanged(int i, int i2, int i3, float f) {
            if (this.listener != null) {
                Handler handler2 = this.handler;
                final int i4 = i;
                final int i5 = i2;
                final int i6 = i3;
                final float f2 = f;
                AnonymousClass5 r1 = new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onVideoSizeChanged(i4, i5, i6, f2);
                    }
                };
                handler2.post(r1);
            }
        }

        public void renderedFirstFrame(final Surface surface) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onRenderedFirstFrame(surface);
                    }
                });
            }
        }

        public void disabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    public void run() {
                        decoderCounters.ensureUpdated();
                        EventDispatcher.this.listener.onVideoDisabled(decoderCounters);
                    }
                });
            }
        }
    }

    void onDroppedFrames(int i, long j);

    void onRenderedFirstFrame(Surface surface);

    void onVideoDecoderInitialized(String str, long j, long j2);

    void onVideoDisabled(DecoderCounters decoderCounters);

    void onVideoEnabled(DecoderCounters decoderCounters);

    void onVideoInputFormatChanged(Format format);

    void onVideoSizeChanged(int i, int i2, int i3, float f);
}
