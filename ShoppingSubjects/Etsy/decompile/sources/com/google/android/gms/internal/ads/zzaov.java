package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.ads.internal.ao;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@bu
@TargetApi(14)
public final class zzaov extends zzapg implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzcwo = new HashMap();
    private final mp zzcwp;
    private final boolean zzcwq;
    private int zzcwr = 0;
    private int zzcws = 0;
    private MediaPlayer zzcwt;
    private Uri zzcwu;
    private int zzcwv;
    private int zzcww;
    private int zzcwx;
    private int zzcwy;
    private int zzcwz;
    private zzapu zzcxa;
    private boolean zzcxb;
    private int zzcxc;
    /* access modifiers changed from: private */
    public ma zzcxd;

    static {
        if (VERSION.SDK_INT >= 17) {
            zzcwo.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
            zzcwo.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
            zzcwo.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
            zzcwo.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
            zzcwo.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzcwo.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzcwo.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzcwo.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzcwo.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzcwo.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
        zzcwo.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
        zzcwo.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
        zzcwo.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzcwo.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        if (VERSION.SDK_INT >= 19) {
            zzcwo.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzcwo.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzaov(Context context, boolean z, boolean z2, mn mnVar, mp mpVar) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzcwp = mpVar;
        this.zzcxb = z;
        this.zzcwq = z2;
        this.zzcwp.a(this);
    }

    private final void zza(float f) {
        if (this.zzcwt != null) {
            try {
                this.zzcwt.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            gv.e("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private final void zzag(int i) {
        if (i == 3) {
            this.zzcwp.c();
            this.zzcxl.zztt();
        } else if (this.zzcwr == 3) {
            this.zzcwp.d();
            this.zzcxl.zztu();
        }
        this.zzcwr = i;
    }

    private final void zzag(boolean z) {
        gv.a("AdMediaPlayerView release");
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
            this.zzcxa = null;
        }
        if (this.zzcwt != null) {
            this.zzcwt.reset();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            if (z) {
                this.zzcws = 0;
                this.zzcws = 0;
            }
        }
    }

    private final void zzsq() {
        gv.a("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzcwu != null && surfaceTexture != null) {
            zzag(false);
            try {
                ao.v();
                this.zzcwt = new MediaPlayer();
                this.zzcwt.setOnBufferingUpdateListener(this);
                this.zzcwt.setOnCompletionListener(this);
                this.zzcwt.setOnErrorListener(this);
                this.zzcwt.setOnInfoListener(this);
                this.zzcwt.setOnPreparedListener(this);
                this.zzcwt.setOnVideoSizeChangedListener(this);
                this.zzcwx = 0;
                if (this.zzcxb) {
                    this.zzcxa = new zzapu(getContext());
                    this.zzcxa.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzcxa.start();
                    SurfaceTexture zztj = this.zzcxa.zztj();
                    if (zztj != null) {
                        surfaceTexture = zztj;
                    } else {
                        this.zzcxa.zzti();
                        this.zzcxa = null;
                    }
                }
                this.zzcwt.setDataSource(getContext(), this.zzcwu);
                ao.w();
                this.zzcwt.setSurface(new Surface(surfaceTexture));
                this.zzcwt.setAudioStreamType(3);
                this.zzcwt.setScreenOnWhilePlaying(true);
                this.zzcwt.prepareAsync();
                zzag(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzcwu);
                StringBuilder sb = new StringBuilder(36 + String.valueOf(valueOf).length());
                sb.append("Failed to initialize MediaPlayer at ");
                sb.append(valueOf);
                gv.c(sb.toString(), e);
                onError(this.zzcwt, 1, 0);
            }
        }
    }

    private final void zzsr() {
        if (this.zzcwq && zzss() && this.zzcwt.getCurrentPosition() > 0 && this.zzcws != 3) {
            gv.a("AdMediaPlayerView nudging MediaPlayer");
            zza(0.0f);
            this.zzcwt.start();
            int currentPosition = this.zzcwt.getCurrentPosition();
            long currentTimeMillis = ao.l().currentTimeMillis();
            while (zzss() && this.zzcwt.getCurrentPosition() == currentPosition) {
                if (ao.l().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzcwt.pause();
            zzst();
        }
    }

    private final boolean zzss() {
        return (this.zzcwt == null || this.zzcwr == -1 || this.zzcwr == 0 || this.zzcwr == 1) ? false : true;
    }

    public final int getCurrentPosition() {
        if (zzss()) {
            return this.zzcwt.getCurrentPosition();
        }
        return 0;
    }

    public final int getDuration() {
        if (zzss()) {
            return this.zzcwt.getDuration();
        }
        return -1;
    }

    public final int getVideoHeight() {
        if (this.zzcwt != null) {
            return this.zzcwt.getVideoHeight();
        }
        return 0;
    }

    public final int getVideoWidth() {
        if (this.zzcwt != null) {
            return this.zzcwt.getVideoWidth();
        }
        return 0;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzcwx = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        gv.a("AdMediaPlayerView completion");
        zzag(5);
        this.zzcws = 5;
        hd.a.post(new ls(this));
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzcwo.get(Integer.valueOf(i));
        String str2 = (String) zzcwo.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(38 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(str2);
        gv.e(sb.toString());
        zzag(-1);
        this.zzcws = -1;
        hd.a.post(new lt(this, str, str2));
        return true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzcwo.get(Integer.valueOf(i));
        String str2 = (String) zzcwo.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(37 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(str2);
        gv.a(sb.toString());
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        if ((r5.zzcwv * r7) > (r5.zzcww * r6)) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006a, code lost:
        if (r1 > r6) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r1 > r6) goto L_0x0047;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.zzcwv
            int r0 = getDefaultSize(r0, r6)
            int r1 = r5.zzcww
            int r1 = getDefaultSize(r1, r7)
            int r2 = r5.zzcwv
            if (r2 <= 0) goto L_0x0085
            int r2 = r5.zzcww
            if (r2 <= 0) goto L_0x0085
            com.google.android.gms.internal.ads.zzapu r2 = r5.zzcxa
            if (r2 != 0) goto L_0x0085
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != r2) goto L_0x004f
            if (r1 != r2) goto L_0x004f
            int r0 = r5.zzcwv
            int r0 = r0 * r7
            int r1 = r5.zzcww
            int r1 = r1 * r6
            if (r0 >= r1) goto L_0x003f
            int r6 = r5.zzcwv
            int r6 = r6 * r7
            int r0 = r5.zzcww
            int r0 = r6 / r0
            r6 = r0
            goto L_0x0087
        L_0x003f:
            int r0 = r5.zzcwv
            int r0 = r0 * r7
            int r1 = r5.zzcww
            int r1 = r1 * r6
            if (r0 <= r1) goto L_0x0087
        L_0x0047:
            int r7 = r5.zzcww
            int r7 = r7 * r6
            int r0 = r5.zzcwv
            int r1 = r7 / r0
            goto L_0x0086
        L_0x004f:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L_0x0060
            int r0 = r5.zzcww
            int r0 = r0 * r6
            int r2 = r5.zzcwv
            int r0 = r0 / r2
            if (r1 != r3) goto L_0x005e
            if (r0 <= r7) goto L_0x005e
            goto L_0x0087
        L_0x005e:
            r7 = r0
            goto L_0x0087
        L_0x0060:
            if (r1 != r2) goto L_0x006f
            int r1 = r5.zzcwv
            int r1 = r1 * r7
            int r2 = r5.zzcww
            int r1 = r1 / r2
            if (r0 != r3) goto L_0x006d
            if (r1 <= r6) goto L_0x006d
            goto L_0x0087
        L_0x006d:
            r6 = r1
            goto L_0x0087
        L_0x006f:
            int r2 = r5.zzcwv
            int r4 = r5.zzcww
            if (r1 != r3) goto L_0x007e
            if (r4 <= r7) goto L_0x007e
            int r1 = r5.zzcwv
            int r1 = r1 * r7
            int r2 = r5.zzcww
            int r1 = r1 / r2
            goto L_0x0080
        L_0x007e:
            r1 = r2
            r7 = r4
        L_0x0080:
            if (r0 != r3) goto L_0x006d
            if (r1 <= r6) goto L_0x006d
            goto L_0x0047
        L_0x0085:
            r6 = r0
        L_0x0086:
            r7 = r1
        L_0x0087:
            r5.setMeasuredDimension(r6, r7)
            com.google.android.gms.internal.ads.zzapu r0 = r5.zzcxa
            if (r0 == 0) goto L_0x0093
            com.google.android.gms.internal.ads.zzapu r0 = r5.zzcxa
            r0.zzh(r6, r7)
        L_0x0093:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 != r1) goto L_0x00b0
            int r0 = r5.zzcwy
            if (r0 <= 0) goto L_0x00a1
            int r0 = r5.zzcwy
            if (r0 != r6) goto L_0x00a9
        L_0x00a1:
            int r0 = r5.zzcwz
            if (r0 <= 0) goto L_0x00ac
            int r0 = r5.zzcwz
            if (r0 == r7) goto L_0x00ac
        L_0x00a9:
            r5.zzsr()
        L_0x00ac:
            r5.zzcwy = r6
            r5.zzcwz = r7
        L_0x00b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaov.onMeasure(int, int):void");
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        gv.a("AdMediaPlayerView prepared");
        zzag(2);
        this.zzcwp.a();
        hd.a.post(new lr(this));
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcxc != 0) {
            seekTo(this.zzcxc);
        }
        zzsr();
        int i = this.zzcwv;
        int i2 = this.zzcww;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        gv.d(sb.toString());
        if (this.zzcws == 3) {
            play();
        }
        zzst();
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        gv.a("AdMediaPlayerView surface created");
        zzsq();
        hd.a.post(new lv(this));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        gv.a("AdMediaPlayerView surface destroyed");
        if (this.zzcwt != null && this.zzcxc == 0) {
            this.zzcxc = this.zzcwt.getCurrentPosition();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
        }
        hd.a.post(new lx(this));
        zzag(true);
        return true;
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        gv.a("AdMediaPlayerView surface changed");
        boolean z = false;
        boolean z2 = this.zzcws == 3;
        if (this.zzcwv == i && this.zzcww == i2) {
            z = true;
        }
        if (this.zzcwt != null && z2 && z) {
            if (this.zzcxc != 0) {
                seekTo(this.zzcxc);
            }
            play();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzh(i, i2);
        }
        hd.a.post(new lw(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzcwp.b(this);
        this.zzcxk.a(surfaceTexture, this.zzcxd);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        gv.a(sb.toString());
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcwv != 0 && this.zzcww != 0) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder sb = new StringBuilder(58);
        sb.append("AdMediaPlayerView window visibility changed to ");
        sb.append(i);
        gv.a(sb.toString());
        hd.a.post(new lq(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final void pause() {
        gv.a("AdMediaPlayerView pause");
        if (zzss() && this.zzcwt.isPlaying()) {
            this.zzcwt.pause();
            zzag(4);
            hd.a.post(new lz(this));
        }
        this.zzcws = 4;
    }

    public final void play() {
        gv.a("AdMediaPlayerView play");
        if (zzss()) {
            this.zzcwt.start();
            zzag(3);
            this.zzcxk.a();
            hd.a.post(new ly(this));
        }
        this.zzcws = 3;
    }

    public final void seekTo(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        gv.a(sb.toString());
        if (zzss()) {
            this.zzcwt.seekTo(i);
            this.zzcxc = 0;
            return;
        }
        this.zzcxc = i;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzhl zzd = zzhl.zzd(parse);
        if (zzd != null) {
            parse = Uri.parse(zzd.url);
        }
        this.zzcwu = parse;
        this.zzcxc = 0;
        zzsq();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        gv.a("AdMediaPlayerView stop");
        if (this.zzcwt != null) {
            this.zzcwt.stop();
            this.zzcwt.release();
            this.zzcwt = null;
            zzag(0);
            this.zzcws = 0;
        }
        this.zzcwp.b();
    }

    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(1 + String.valueOf(name).length() + String.valueOf(hexString).length());
        sb.append(name);
        sb.append("@");
        sb.append(hexString);
        return sb.toString();
    }

    public final void zza(float f, float f2) {
        if (this.zzcxa != null) {
            this.zzcxa.zzb(f, f2);
        }
    }

    public final void zza(ma maVar) {
        this.zzcxd = maVar;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzah(int i) {
        if (this.zzcxd != null) {
            this.zzcxd.onWindowVisibilityChanged(i);
        }
    }

    public final String zzsp() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzcxb ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public final void zzst() {
        zza(this.zzcxl.getVolume());
    }
}
