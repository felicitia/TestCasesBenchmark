package com.etsy.android.uikit.adapter;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ListingImage;

public class ListingThumbnailAdapter extends BaseModelArrayAdapter<BaseModelImage> {
    private static final String TAG = f.a(ListingThumbnailAdapter.class);
    private final int mThumbHeight;
    private final int mThumbWidth;

    public ListingThumbnailAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, k.list_item_listing_thumbnail, cVar);
        Resources resources = fragmentActivity.getResources();
        this.mThumbHeight = resources.getDimensionPixelSize(a.f.listing_thumbnail_height);
        this.mThumbWidth = resources.getDimensionPixelSize(a.f.listing_thumbnail_width);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(i.img_thumbnail);
        BaseModelImage baseModelImage = (BaseModelImage) getItem(i);
        if (baseModelImage != null) {
            if (baseModelImage instanceof ListingImage) {
                i2 = baseModelImage.getImageColor();
            }
            int i3 = i2;
            getImageBatch().a(baseModelImage.getImageUrl().replace("570xN", "170x135"), imageView, this.mThumbWidth, this.mThumbHeight, i3);
        }
        return view;
    }
}
