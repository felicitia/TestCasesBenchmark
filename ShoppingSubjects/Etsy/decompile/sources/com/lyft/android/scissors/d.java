package com.lyft.android.scissors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.h;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.c;

/* compiled from: GlideBitmapLoader */
public class d implements a {
    private final h a;
    private final com.bumptech.glide.load.resource.bitmap.d b;

    public d(@NonNull h hVar, @NonNull com.bumptech.glide.load.resource.bitmap.d dVar) {
        this.a = hVar;
        this.b = dVar;
    }

    public void a(@Nullable Object obj, @NonNull ImageView imageView) {
        this.a.a(obj).h().b(true).b(DiskCacheStrategy.SOURCE).a(this.b).a(imageView);
    }

    public static a a(@NonNull CropView cropView) {
        return a(cropView, Glide.b(cropView.getContext()), Glide.a(cropView.getContext()).a());
    }

    public static a a(@NonNull CropView cropView, @NonNull h hVar, @NonNull c cVar) {
        return new d(hVar, e.a(cVar, cropView.getViewportWidth(), cropView.getViewportHeight()));
    }
}
