package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.util.b.a;

public class ListingImagesPagerAdapter extends ImagesPagerAdapter<ListingImage> {
    @NonNull
    private final ScaleType mImageViewScaleType;

    public ListingImagesPagerAdapter(@NonNull Activity activity, @NonNull c cVar, @NonNull w wVar, @NonNull ScaleType scaleType, @NonNull a aVar) {
        super(activity, wVar, k.imageview_loading, cVar, aVar);
        this.mImageViewScaleType = scaleType;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public ImageView setUpViewAndReturnImageView(@NonNull View view, int i) {
        ImageView upViewAndReturnImageView = super.setUpViewAndReturnImageView(view, i);
        upViewAndReturnImageView.setScaleType(this.mImageViewScaleType);
        return upViewAndReturnImageView;
    }
}
