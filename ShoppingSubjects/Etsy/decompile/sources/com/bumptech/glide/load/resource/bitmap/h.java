package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import java.io.IOException;

/* compiled from: FileDescriptorBitmapDecoder */
public class h implements d<ParcelFileDescriptor, Bitmap> {
    private final p a;
    private final c b;
    private DecodeFormat c;

    public String a() {
        return "FileDescriptorBitmapDecoder.com.bumptech.glide.load.data.bitmap";
    }

    public h(c cVar, DecodeFormat decodeFormat) {
        this(new p(), cVar, decodeFormat);
    }

    public h(p pVar, c cVar, DecodeFormat decodeFormat) {
        this.a = pVar;
        this.b = cVar;
        this.c = decodeFormat;
    }

    public i<Bitmap> a(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) throws IOException {
        return c.a(this.a.a(parcelFileDescriptor, this.b, i, i2, this.c), this.b);
    }
}
