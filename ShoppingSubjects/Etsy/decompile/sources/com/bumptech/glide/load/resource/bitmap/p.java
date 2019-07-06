package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import java.io.IOException;

/* compiled from: VideoBitmapDecoder */
public class p implements a<ParcelFileDescriptor> {
    private static final a a = new a();
    private a b;
    private int c;

    /* compiled from: VideoBitmapDecoder */
    static class a {
        a() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    public String a() {
        return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }

    public p() {
        this(a, -1);
    }

    p(a aVar, int i) {
        this.b = aVar;
        this.c = i;
    }

    public Bitmap a(ParcelFileDescriptor parcelFileDescriptor, c cVar, int i, int i2, DecodeFormat decodeFormat) throws IOException {
        Bitmap bitmap;
        MediaMetadataRetriever a2 = this.b.a();
        a2.setDataSource(parcelFileDescriptor.getFileDescriptor());
        if (this.c >= 0) {
            bitmap = a2.getFrameAtTime((long) this.c);
        } else {
            bitmap = a2.getFrameAtTime();
        }
        a2.release();
        parcelFileDescriptor.close();
        return bitmap;
    }
}
