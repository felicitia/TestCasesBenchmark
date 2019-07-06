package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.common.util.PlatformVersion;
import java.io.InputStream;

final class ao implements it<zzon> {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ double b;
    private final /* synthetic */ boolean c;
    private final /* synthetic */ String d;
    private final /* synthetic */ ai e;

    ao(ai aiVar, boolean z, double d2, boolean z2, String str) {
        this.e = aiVar;
        this.a = z;
        this.b = d2;
        this.c = z2;
        this.d = str;
    }

    /* access modifiers changed from: private */
    @TargetApi(19)
    /* renamed from: b */
    public final zzon a(InputStream inputStream) {
        Bitmap bitmap;
        Options options = new Options();
        options.inDensity = (int) (160.0d * this.b);
        if (!this.c) {
            options.inPreferredConfig = Config.RGB_565;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception e2) {
            gv.b("Error grabbing image.", e2);
            bitmap = null;
        }
        if (bitmap == null) {
            this.e.a(2, this.a);
            return null;
        }
        long uptimeMillis2 = SystemClock.uptimeMillis();
        if (PlatformVersion.isAtLeastKitKat() && gv.a()) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int allocationByteCount = bitmap.getAllocationByteCount();
            long j = uptimeMillis2 - uptimeMillis;
            boolean z = Looper.getMainLooper().getThread() == Thread.currentThread();
            StringBuilder sb = new StringBuilder(108);
            sb.append("Decoded image w: ");
            sb.append(width);
            sb.append(" h:");
            sb.append(height);
            sb.append(" bytes: ");
            sb.append(allocationByteCount);
            sb.append(" time: ");
            sb.append(j);
            sb.append(" on ui thread: ");
            sb.append(z);
            gv.a(sb.toString());
        }
        return new zzon(new BitmapDrawable(Resources.getSystem(), bitmap), Uri.parse(this.d), this.b);
    }

    public final /* synthetic */ Object a() {
        this.e.a(2, this.a);
        return null;
    }
}
