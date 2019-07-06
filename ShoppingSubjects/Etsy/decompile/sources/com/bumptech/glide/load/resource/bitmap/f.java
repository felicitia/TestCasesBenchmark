package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.android.volley.toolbox.DiskBasedCache;
import com.bumptech.glide.g.a;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

/* compiled from: Downsampler */
public abstract class f implements a<InputStream> {
    public static final f a = new f() {
        public String a() {
            return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
        }

        /* access modifiers changed from: protected */
        public int a(int i, int i2, int i3, int i4) {
            return Math.min(i2 / i4, i / i3);
        }
    };
    public static final f b = new f() {
        public String a() {
            return "AT_MOST.com.bumptech.glide.load.data.bitmap";
        }

        /* access modifiers changed from: protected */
        public int a(int i, int i2, int i3, int i4) {
            int ceil = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
            int i5 = 1;
            int max = Math.max(1, Integer.highestOneBit(ceil));
            if (max >= ceil) {
                i5 = 0;
            }
            return max << i5;
        }
    };
    public static final f c = new f() {
        /* access modifiers changed from: protected */
        public int a(int i, int i2, int i3, int i4) {
            return 0;
        }

        public String a() {
            return "NONE.com.bumptech.glide.load.data.bitmap";
        }
    };
    private static final Set<ImageType> d = EnumSet.of(ImageType.JPEG, ImageType.PNG_A, ImageType.PNG);
    private static final Queue<Options> e = h.a(0);

    /* access modifiers changed from: protected */
    public abstract int a(int i, int i2, int i3, int i4);

    public Bitmap a(InputStream inputStream, c cVar, int i, int i2, DecodeFormat decodeFormat) {
        Throwable th;
        Options options;
        int i3;
        Throwable th2;
        c cVar2 = cVar;
        a a2 = a.a();
        byte[] b2 = a2.b();
        byte[] b3 = a2.b();
        Options b4 = b();
        RecyclableBufferedInputStream recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, b3);
        com.bumptech.glide.g.c a3 = com.bumptech.glide.g.c.a(recyclableBufferedInputStream);
        com.bumptech.glide.g.f fVar = new com.bumptech.glide.g.f(a3);
        try {
            a3.mark(DiskBasedCache.DEFAULT_DISK_USAGE_BYTES);
            try {
                int c2 = new ImageHeaderParser(a3).c();
                try {
                    a3.reset();
                } catch (IOException e2) {
                    if (Log.isLoggable("Downsampler", 5)) {
                        Log.w("Downsampler", "Cannot reset the input stream", e2);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    options = b4;
                    a2.a(b2);
                    a2.a(b3);
                    a3.b();
                    a(options);
                    throw th;
                }
                i3 = c2;
            } catch (IOException e3) {
                IOException iOException = e3;
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot determine the image orientation from header", iOException);
                }
                a3.reset();
                i3 = 0;
            } catch (Throwable th4) {
                th2 = th4;
                options = b4;
                a3.reset();
                throw th2;
            }
        } catch (IOException e4) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot reset the input stream", e4);
            }
        } catch (Throwable th5) {
            th = th5;
            options = b4;
            th = th;
            a2.a(b2);
            a2.a(b3);
            a3.b();
            a(options);
            throw th;
        }
        b4.inTempStorage = b2;
        int[] a4 = a(fVar, recyclableBufferedInputStream, b4);
        int i4 = a4[0];
        int i5 = a4[1];
        Options options2 = b4;
        Options options3 = b4;
        int i6 = i3;
        try {
            Bitmap a5 = a(fVar, recyclableBufferedInputStream, options2, cVar2, i4, i5, a(o.a(i3), i4, i5, i, i2), decodeFormat);
            IOException a6 = a3.a();
            if (a6 != null) {
                try {
                    throw new RuntimeException(a6);
                } catch (Throwable th6) {
                    th = th6;
                    options = options3;
                    a2.a(b2);
                    a2.a(b3);
                    a3.b();
                    a(options);
                    throw th;
                }
            } else {
                Bitmap bitmap = null;
                if (a5 != null) {
                    bitmap = o.a(a5, cVar2, i6);
                    if (!a5.equals(bitmap) && !cVar2.a(a5)) {
                        a5.recycle();
                    }
                }
                a2.a(b2);
                a2.a(b3);
                a3.b();
                a(options3);
                return bitmap;
            }
        } catch (Throwable th7) {
            th = th7;
            options = options3;
            th = th;
            a2.a(b2);
            a2.a(b3);
            a3.b();
            a(options);
            throw th;
        }
    }

    private int a(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        if (i5 == Integer.MIN_VALUE) {
            i5 = i3;
        }
        if (i4 == Integer.MIN_VALUE) {
            i4 = i2;
        }
        if (i == 90 || i == 270) {
            i6 = a(i3, i2, i4, i5);
        } else {
            i6 = a(i2, i3, i4, i5);
        }
        if (i6 == 0) {
            i7 = 0;
        } else {
            i7 = Integer.highestOneBit(i6);
        }
        return Math.max(1, i7);
    }

    private Bitmap a(com.bumptech.glide.g.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options, c cVar, int i, int i2, int i3, DecodeFormat decodeFormat) {
        Config a2 = a((InputStream) fVar, decodeFormat);
        options.inSampleSize = i3;
        options.inPreferredConfig = a2;
        if ((options.inSampleSize == 1 || 19 <= VERSION.SDK_INT) && a((InputStream) fVar)) {
            double d2 = (double) i3;
            a(options, cVar.b((int) Math.ceil(((double) i) / d2), (int) Math.ceil(((double) i2) / d2), a2));
        }
        return b(fVar, recyclableBufferedInputStream, options);
    }

    private static boolean a(InputStream inputStream) {
        if (19 <= VERSION.SDK_INT) {
            return true;
        }
        inputStream.mark(1024);
        try {
            boolean contains = d.contains(new ImageHeaderParser(inputStream).b());
            try {
            } catch (IOException e2) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e2);
                }
            }
            return contains;
        } catch (IOException e3) {
            if (Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Cannot determine the image type from header", e3);
            }
            try {
            } catch (IOException e4) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e4);
                }
            }
            return false;
        } finally {
            try {
                inputStream.reset();
            } catch (IOException e5) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e5);
                }
            }
        }
    }

    private static Config a(InputStream inputStream, DecodeFormat decodeFormat) {
        Config config;
        if (decodeFormat == DecodeFormat.ALWAYS_ARGB_8888 || decodeFormat == DecodeFormat.PREFER_ARGB_8888 || VERSION.SDK_INT == 16) {
            return Config.ARGB_8888;
        }
        boolean z = false;
        inputStream.mark(1024);
        try {
            boolean a2 = new ImageHeaderParser(inputStream).a();
            try {
            } catch (IOException e2) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e2);
                }
            }
            z = a2;
        } catch (IOException e3) {
            if (Log.isLoggable("Downsampler", 5)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot determine whether the image has alpha or not from header for format ");
                sb.append(decodeFormat);
                Log.w("Downsampler", sb.toString(), e3);
            }
            try {
            } catch (IOException e4) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e4);
                }
            }
        } finally {
            try {
                inputStream.reset();
            } catch (IOException e5) {
                if (Log.isLoggable("Downsampler", 5)) {
                    Log.w("Downsampler", "Cannot reset the input stream", e5);
                }
            }
        }
        if (z) {
            config = Config.ARGB_8888;
        } else {
            config = Config.RGB_565;
        }
        return config;
    }

    public int[] a(com.bumptech.glide.g.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options) {
        options.inJustDecodeBounds = true;
        b(fVar, recyclableBufferedInputStream, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap b(com.bumptech.glide.g.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options) {
        if (options.inJustDecodeBounds) {
            fVar.mark(DiskBasedCache.DEFAULT_DISK_USAGE_BYTES);
        } else {
            recyclableBufferedInputStream.a();
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(fVar, null, options);
        try {
            if (options.inJustDecodeBounds) {
                fVar.reset();
            }
        } catch (IOException e2) {
            if (Log.isLoggable("Downsampler", 6)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Exception loading inDecodeBounds=");
                sb.append(options.inJustDecodeBounds);
                sb.append(" sample=");
                sb.append(options.inSampleSize);
                Log.e("Downsampler", sb.toString(), e2);
            }
        }
        return decodeStream;
    }

    @TargetApi(11)
    private static void a(Options options, Bitmap bitmap) {
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = bitmap;
        }
    }

    @TargetApi(11)
    private static synchronized Options b() {
        Options options;
        synchronized (f.class) {
            synchronized (e) {
                options = (Options) e.poll();
            }
            if (options == null) {
                options = new Options();
                b(options);
            }
        }
        return options;
    }

    private static void a(Options options) {
        b(options);
        synchronized (e) {
            e.offer(options);
        }
    }

    @TargetApi(11)
    private static void b(Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = null;
            options.inMutable = true;
        }
    }
}
