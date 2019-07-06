package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.internal.Asserts;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@bu
public final class zzapi extends FrameLayout implements ma {
    private final mo zzcxm;
    private final FrameLayout zzcxn;
    private final aky zzcxo;
    private final mq zzcxp;
    private final long zzcxq;
    @Nullable
    private zzapg zzcxr;
    private boolean zzcxs;
    private boolean zzcxt;
    private boolean zzcxu;
    private boolean zzcxv;
    private long zzcxw;
    private long zzcxx;
    private String zzcxy;
    private Bitmap zzcxz;
    private ImageView zzcya;
    private boolean zzcyb;

    public zzapi(Context context, mo moVar, int i, boolean z, aky aky, mn mnVar) {
        Context context2 = context;
        super(context2);
        mo moVar2 = moVar;
        this.zzcxm = moVar2;
        aky aky2 = aky;
        this.zzcxo = aky2;
        this.zzcxn = new FrameLayout(context2);
        addView(this.zzcxn, new LayoutParams(-1, -1));
        Asserts.checkNotNull(moVar2.zzbi());
        this.zzcxr = moVar2.zzbi().b.a(context2, moVar2, i, z, aky2, mnVar);
        if (this.zzcxr != null) {
            this.zzcxn.addView(this.zzcxr, new LayoutParams(-1, -1, 17));
            if (((Boolean) ajh.f().a(akl.w)).booleanValue()) {
                zztd();
            }
        }
        this.zzcya = new ImageView(context2);
        this.zzcxq = ((Long) ajh.f().a(akl.A)).longValue();
        this.zzcxv = ((Boolean) ajh.f().a(akl.y)).booleanValue();
        if (this.zzcxo != null) {
            this.zzcxo.a("spinner_used", this.zzcxv ? "1" : "0");
        }
        this.zzcxp = new mq(this);
        if (this.zzcxr != null) {
            this.zzcxr.zza(this);
        }
        if (this.zzcxr == null) {
            zzg("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public static void zza(mo moVar) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "no_video_view");
        moVar.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    public static void zza(mo moVar, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
        hashMap.put("error", str);
        moVar.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    public static void zza(mo moVar, Map<String, List<Map<String, Object>>> map) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
        hashMap.put("mimeTypes", map);
        moVar.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    /* access modifiers changed from: private */
    public final void zza(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                hashMap.put(str2, str3);
                str2 = null;
            }
        }
        this.zzcxm.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    private final boolean zztf() {
        return this.zzcya.getParent() != null;
    }

    private final void zztg() {
        if (this.zzcxm.zzto() != null && this.zzcxt && !this.zzcxu) {
            this.zzcxm.zzto().getWindow().clearFlags(128);
            this.zzcxt = false;
        }
    }

    public final void destroy() {
        this.zzcxp.a();
        if (this.zzcxr != null) {
            this.zzcxr.stop();
        }
        zztg();
    }

    public final void finalize() throws Throwable {
        try {
            this.zzcxp.a();
            if (this.zzcxr != null) {
                zzapg zzapg = this.zzcxr;
                Executor executor = kz.a;
                zzapg.getClass();
                executor.execute(mc.a(zzapg));
            }
        } finally {
            super.finalize();
        }
    }

    public final void onPaused() {
        zza("pause", new String[0]);
        zztg();
        this.zzcxs = false;
    }

    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        if (i == 0) {
            this.zzcxp.b();
            z = true;
        } else {
            this.zzcxp.a();
            this.zzcxx = this.zzcxw;
            z = false;
        }
        hd.a.post(new mf(this, z));
    }

    public final void pause() {
        if (this.zzcxr != null) {
            this.zzcxr.pause();
        }
    }

    public final void play() {
        if (this.zzcxr != null) {
            this.zzcxr.play();
        }
    }

    public final void seekTo(int i) {
        if (this.zzcxr != null) {
            this.zzcxr.seekTo(i);
        }
    }

    public final void setVolume(float f) {
        if (this.zzcxr != null) {
            zzapg zzapg = this.zzcxr;
            zzapg.zzcxl.setVolume(f);
            zzapg.zzst();
        }
    }

    public final void zza(float f, float f2) {
        if (this.zzcxr != null) {
            this.zzcxr.zza(f, f2);
        }
    }

    public final void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            LayoutParams layoutParams = new LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzcxn.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public final void zzdn(String str) {
        this.zzcxy = str;
    }

    public final void zzf(int i, int i2) {
        if (this.zzcxv) {
            int max = Math.max(i / ((Integer) ajh.f().a(akl.z)).intValue(), 1);
            int max2 = Math.max(i2 / ((Integer) ajh.f().a(akl.z)).intValue(), 1);
            if (this.zzcxz == null || this.zzcxz.getWidth() != max || this.zzcxz.getHeight() != max2) {
                this.zzcxz = Bitmap.createBitmap(max, max2, Config.ARGB_8888);
                this.zzcyb = false;
            }
        }
    }

    @TargetApi(14)
    public final void zzf(MotionEvent motionEvent) {
        if (this.zzcxr != null) {
            this.zzcxr.dispatchTouchEvent(motionEvent);
        }
    }

    public final void zzg(String str, @Nullable String str2) {
        zza("error", "what", str, "extra", str2);
    }

    public final void zzsu() {
        this.zzcxp.b();
        hd.a.post(new md(this));
    }

    public final void zzsv() {
        if (this.zzcxr != null && this.zzcxx == 0) {
            zza("canplaythrough", "duration", String.valueOf(((float) this.zzcxr.getDuration()) / 1000.0f), "videoWidth", String.valueOf(this.zzcxr.getVideoWidth()), "videoHeight", String.valueOf(this.zzcxr.getVideoHeight()));
        }
    }

    public final void zzsw() {
        if (this.zzcxm.zzto() != null && !this.zzcxt) {
            this.zzcxu = (this.zzcxm.zzto().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzcxu) {
                this.zzcxm.zzto().getWindow().addFlags(128);
                this.zzcxt = true;
            }
        }
        this.zzcxs = true;
    }

    public final void zzsx() {
        zza("ended", new String[0]);
        zztg();
    }

    public final void zzsy() {
        if (this.zzcyb && this.zzcxz != null && !zztf()) {
            this.zzcya.setImageBitmap(this.zzcxz);
            this.zzcya.invalidate();
            this.zzcxn.addView(this.zzcya, new LayoutParams(-1, -1));
            this.zzcxn.bringChildToFront(this.zzcya);
        }
        this.zzcxp.a();
        this.zzcxx = this.zzcxw;
        hd.a.post(new me(this));
    }

    public final void zzsz() {
        if (this.zzcxs && zztf()) {
            this.zzcxn.removeView(this.zzcya);
        }
        if (this.zzcxz != null) {
            long elapsedRealtime = ao.l().elapsedRealtime();
            if (this.zzcxr.getBitmap(this.zzcxz) != null) {
                this.zzcyb = true;
            }
            long elapsedRealtime2 = ao.l().elapsedRealtime() - elapsedRealtime;
            if (gv.a()) {
                StringBuilder sb = new StringBuilder(46);
                sb.append("Spinner frame grab took ");
                sb.append(elapsedRealtime2);
                sb.append("ms");
                gv.a(sb.toString());
            }
            if (elapsedRealtime2 > this.zzcxq) {
                gv.e("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzcxv = false;
                this.zzcxz = null;
                if (this.zzcxo != null) {
                    this.zzcxo.a("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    public final void zzta() {
        if (this.zzcxr != null) {
            if (!TextUtils.isEmpty(this.zzcxy)) {
                this.zzcxr.setVideoPath(this.zzcxy);
            } else {
                zza("no_src", new String[0]);
            }
        }
    }

    public final void zztb() {
        if (this.zzcxr != null) {
            zzapg zzapg = this.zzcxr;
            zzapg.zzcxl.setMuted(true);
            zzapg.zzst();
        }
    }

    public final void zztc() {
        if (this.zzcxr != null) {
            zzapg zzapg = this.zzcxr;
            zzapg.zzcxl.setMuted(false);
            zzapg.zzst();
        }
    }

    @TargetApi(14)
    public final void zztd() {
        if (this.zzcxr != null) {
            TextView textView = new TextView(this.zzcxr.getContext());
            String str = "AdMob - ";
            String valueOf = String.valueOf(this.zzcxr.zzsp());
            textView.setText(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzcxn.addView(textView, new LayoutParams(-2, -2, 17));
            this.zzcxn.bringChildToFront(textView);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzte() {
        if (this.zzcxr != null) {
            long currentPosition = (long) this.zzcxr.getCurrentPosition();
            if (this.zzcxw != currentPosition && currentPosition > 0) {
                zza("timeupdate", "time", String.valueOf(((float) currentPosition) / 1000.0f));
                this.zzcxw = currentPosition;
            }
        }
    }
}
