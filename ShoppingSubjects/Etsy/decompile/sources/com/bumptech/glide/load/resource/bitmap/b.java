package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import com.bumptech.glide.g.d;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.i;
import java.io.OutputStream;

/* compiled from: BitmapEncoder */
public class b implements e<Bitmap> {
    private CompressFormat a;
    private int b;

    public String a() {
        return "BitmapEncoder.com.bumptech.glide.load.resource.bitmap";
    }

    public b() {
        this(null, 90);
    }

    public b(CompressFormat compressFormat, int i) {
        this.a = compressFormat;
        this.b = i;
    }

    public boolean a(i<Bitmap> iVar, OutputStream outputStream) {
        Bitmap bitmap = (Bitmap) iVar.b();
        long a2 = d.a();
        CompressFormat a3 = a(bitmap);
        bitmap.compress(a3, this.b, outputStream);
        if (Log.isLoggable("BitmapEncoder", 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Compressed with type: ");
            sb.append(a3);
            sb.append(" of size ");
            sb.append(h.a(bitmap));
            sb.append(" in ");
            sb.append(d.a(a2));
            Log.v("BitmapEncoder", sb.toString());
        }
        return true;
    }

    private CompressFormat a(Bitmap bitmap) {
        if (this.a != null) {
            return this.a;
        }
        if (bitmap.hasAlpha()) {
            return CompressFormat.PNG;
        }
        return CompressFormat.JPEG;
    }
}
