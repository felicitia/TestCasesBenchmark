package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.TextureView;

@bu
@TargetApi(14)
public abstract class zzapg extends TextureView implements ms {
    protected final mi zzcxk = new mi();
    protected final zzapz zzcxl;

    public zzapg(Context context) {
        super(context);
        this.zzcxl = new zzapz(context, this);
    }

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void pause();

    public abstract void play();

    public abstract void seekTo(int i);

    public abstract void setVideoPath(String str);

    public abstract void stop();

    public abstract void zza(float f, float f2);

    public abstract void zza(ma maVar);

    public abstract String zzsp();

    public abstract void zzst();
}
