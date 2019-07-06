package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphRequest.OnProgressCallback;

class RequestProgress {
    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final GraphRequest request;
    private final long threshold = FacebookSdk.getOnProgressThreshold();

    RequestProgress(Handler handler, GraphRequest graphRequest) {
        this.request = graphRequest;
        this.callbackHandler = handler;
    }

    /* access modifiers changed from: 0000 */
    public void addProgress(long j) {
        this.progress += j;
        if (this.progress >= this.lastReportedProgress + this.threshold || this.progress >= this.maxProgress) {
            reportProgress();
        }
    }

    /* access modifiers changed from: 0000 */
    public void addToMax(long j) {
        this.maxProgress += j;
    }

    /* access modifiers changed from: 0000 */
    public void reportProgress() {
        if (this.progress > this.lastReportedProgress) {
            Callback callback = this.request.getCallback();
            if (this.maxProgress > 0 && (callback instanceof OnProgressCallback)) {
                final long j = this.progress;
                final long j2 = this.maxProgress;
                final OnProgressCallback onProgressCallback = (OnProgressCallback) callback;
                if (this.callbackHandler == null) {
                    onProgressCallback.onProgress(j, j2);
                } else {
                    Handler handler = this.callbackHandler;
                    AnonymousClass1 r2 = new Runnable() {
                        public void run() {
                            onProgressCallback.onProgress(j, j2);
                        }
                    };
                    handler.post(r2);
                }
                this.lastReportedProgress = this.progress;
            }
        }
    }
}
