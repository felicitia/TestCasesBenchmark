package com.lyft.android.scissors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;

/* compiled from: UILBitmapLoader */
public class k implements a {
    private final ImageLoader a;
    private final BitmapDisplayer b;

    public k(ImageLoader imageLoader, BitmapDisplayer bitmapDisplayer) {
        this.a = imageLoader;
        this.b = bitmapDisplayer;
    }

    public static a a(CropView cropView) {
        return a(cropView, ImageLoader.getInstance());
    }

    public static a a(CropView cropView, ImageLoader imageLoader) {
        return new k(imageLoader, l.a(cropView.getViewportWidth(), cropView.getViewportHeight()));
    }

    public void a(@Nullable Object obj, @NonNull ImageView imageView) {
        DisplayImageOptions build = new Builder().cacheInMemory(false).cacheOnDisk(false).displayer(this.b).build();
        if ((obj instanceof String) || obj == null) {
            this.a.displayImage((String) obj, imageView, build);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported model ");
        sb.append(obj);
        throw new IllegalArgumentException(sb.toString());
    }
}
