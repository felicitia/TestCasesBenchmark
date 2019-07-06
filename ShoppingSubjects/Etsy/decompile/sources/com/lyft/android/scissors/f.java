package com.lyft.android.scissors;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import java.io.File;

/* compiled from: PicassoBitmapLoader */
public class f implements a {
    private final Picasso a;
    private final Transformation b;

    public f(Picasso picasso, Transformation transformation) {
        this.a = picasso;
        this.b = transformation;
    }

    public void a(@Nullable Object obj, @NonNull ImageView imageView) {
        RequestCreator requestCreator;
        if ((obj instanceof Uri) || obj == null) {
            requestCreator = this.a.load((Uri) obj);
        } else if (obj instanceof String) {
            requestCreator = this.a.load((String) obj);
        } else if (obj instanceof File) {
            requestCreator = this.a.load((File) obj);
        } else if (obj instanceof Integer) {
            requestCreator = this.a.load(((Integer) obj).intValue());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported model ");
            sb.append(obj);
            throw new IllegalArgumentException(sb.toString());
        }
        requestCreator.skipMemoryCache().transform(this.b).into(imageView);
    }

    public static a a(CropView cropView) {
        return a(cropView, Picasso.with(cropView.getContext()));
    }

    public static a a(CropView cropView, Picasso picasso) {
        return new f(picasso, g.a(cropView.getViewportWidth(), cropView.getViewportHeight()));
    }
}
