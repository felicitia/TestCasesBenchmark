package com.etsy.android.uikit.viewholder;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class FullImageAndDescriptionViewHolder extends BaseViewHolder<a> {
    public static final int IMAGE_TYPE_DRAWABLE = 2;
    public static final int IMAGE_TYPE_URL = 1;
    public final TextView mDescription;
    public final FullImageView mImage;
    public final c mImageBatch;

    public interface a {
        @Nullable
        IFullImage a();

        @Nullable
        Drawable b();

        @Nullable
        CharSequence c();

        @Nullable
        CharSequence d();

        int e();

        int f();

        float g();

        ScaleType h();
    }

    public FullImageAndDescriptionViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup, @LayoutRes int i, @IdRes int i2, @IdRes int i3, @NonNull c cVar) {
        super(layoutInflater.inflate(i, viewGroup, false));
        this.mImage = (FullImageView) this.itemView.findViewById(i2);
        this.mDescription = (TextView) this.itemView.findViewById(i3);
        this.mImageBatch = cVar;
    }

    public void bind(a aVar) {
        this.mImage.cleanUp();
        this.mImage.setScaleType(aVar.h());
        this.mImage.setHeightRatio(aVar.g());
        if (aVar.e() == 2) {
            this.mImage.setImageDrawable(aVar.b());
        } else {
            this.mImage.setImageInfo(aVar.a(), this.mImageBatch, aVar.f());
        }
        this.mDescription.setText(aVar.c());
        this.mDescription.setHint(aVar.d());
    }
}
