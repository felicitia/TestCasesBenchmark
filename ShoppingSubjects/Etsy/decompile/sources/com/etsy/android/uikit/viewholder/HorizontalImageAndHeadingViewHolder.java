package com.etsy.android.uikit.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class HorizontalImageAndHeadingViewHolder extends BaseViewHolder<a> {
    /* access modifiers changed from: private */
    public final b<a> mClickHandler;
    private final TextView mHeading;
    private final ImageView mImage;
    private final c mImageBatch;
    private final int mImageSize;

    public interface a {
        @ColorInt
        int getBackgroundColor();

        @Nullable
        Drawable getDrawable(Context context);

        @Nullable
        CharSequence getHeading(Context context);

        int getHeadingGravity();

        @Nullable
        String getImageUrl();

        int getType();
    }

    public HorizontalImageAndHeadingViewHolder(@NonNull View view, @NonNull c cVar, b<a> bVar) {
        super(view);
        this.mImage = (ImageView) view.findViewById(i.image);
        this.mHeading = (TextView) view.findViewById(i.heading);
        this.mImageBatch = cVar;
        this.mImageSize = (int) view.getResources().getDimension(f.shop2_horizontal_circular_image_size);
        this.mClickHandler = bVar;
    }

    public void bind(@NonNull final a aVar) {
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                HorizontalImageAndHeadingViewHolder.this.mClickHandler.a(aVar);
            }
        });
        switch (aVar.getType()) {
            case 0:
                this.mImageBatch.b(aVar.getImageUrl(), this.mImage, this.mImageSize, BaseModelImage.DEFAULT_LOADING_COLOR);
                break;
            case 1:
                this.mImage.setImageDrawable(aVar.getDrawable(this.itemView.getContext()));
                break;
        }
        ((LayoutParams) this.mHeading.getLayoutParams()).gravity = aVar.getHeadingGravity();
        this.mHeading.setText(aVar.getHeading(this.itemView.getContext()));
        this.itemView.setBackgroundColor(aVar.getBackgroundColor());
    }

    public void recycle() {
        this.mImage.setImageDrawable(null);
        this.mHeading.setText(null);
    }
}
