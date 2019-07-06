package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import java.util.concurrent.TimeUnit;

@bu
@TargetApi(14)
public final class mi {
    private final long a = TimeUnit.MILLISECONDS.toNanos(((Long) ajh.f().a(akl.x)).longValue());
    private long b;
    private boolean c = true;

    mi() {
    }

    public final void a() {
        this.c = true;
    }

    public final void a(SurfaceTexture surfaceTexture, ma maVar) {
        if (maVar != null) {
            long timestamp = surfaceTexture.getTimestamp();
            if (this.c || Math.abs(timestamp - this.b) >= this.a) {
                this.c = false;
                this.b = timestamp;
                hd.a.post(new mj(this, maVar));
            }
        }
    }
}
