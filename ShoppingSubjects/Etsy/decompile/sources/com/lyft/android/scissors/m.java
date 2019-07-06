package com.lyft.android.scissors;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* compiled from: Utils */
class m {
    private static final ExecutorService a = Executors.newCachedThreadPool();

    public static void a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static Bitmap a(Drawable drawable, int i, int i2) {
        Rect rect = new Rect();
        drawable.copyBounds(rect);
        if (rect.isEmpty()) {
            rect.set(0, 0, Math.max(i, drawable.getIntrinsicWidth()), Math.max(i2, drawable.getIntrinsicHeight()));
            drawable.setBounds(rect);
        }
        Bitmap createBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Config.ARGB_8888);
        drawable.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Future<Void> a(Bitmap bitmap, CompressFormat compressFormat, int i, OutputStream outputStream, boolean z) {
        ExecutorService executorService = a;
        final Bitmap bitmap2 = bitmap;
        final CompressFormat compressFormat2 = compressFormat;
        final int i2 = i;
        final OutputStream outputStream2 = outputStream;
        final boolean z2 = z;
        AnonymousClass1 r1 = new Runnable() {
            public void run() {
                try {
                    bitmap2.compress(compressFormat2, i2, outputStream2);
                    outputStream2.flush();
                    if (!z2) {
                        return;
                    }
                } catch (Throwable th) {
                    if (z2) {
                        m.b(outputStream2);
                    }
                    throw th;
                }
                m.b(outputStream2);
            }
        };
        return executorService.submit(r1, null);
    }

    /* access modifiers changed from: private */
    public static void b(@Nullable OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
                Log.e("scissors.Utils", "Error attempting to close stream.", e);
            }
        }
    }
}
