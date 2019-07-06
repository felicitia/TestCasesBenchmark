package com.etsy.android.ui.finds.cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.finds.FindsSearchCategory;
import com.etsy.android.ui.finds.cardview.listener.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class FindsCategoryViewHolder extends BaseViewHolder<FindsSearchCategory> {
    /* access modifiers changed from: private */
    public a mClickListener;
    private c mImageBatch;
    private ImageViewWithAspectRatio mImageView = ((ImageViewWithAspectRatio) findViewById(R.id.listing_image));
    private TextView mTitleView = ((TextView) findViewById(R.id.txt_title));

    public FindsCategoryViewHolder(ViewGroup viewGroup, a aVar, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.finds_category_card, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickListener = aVar;
    }

    public void bind(final FindsSearchCategory findsSearchCategory) {
        this.mTitleView.setText(findsSearchCategory.getTitle());
        Listing listing = findsSearchCategory.getListing();
        if (!(listing == null || listing.getImage() == null)) {
            this.mImageView.setImageInfo(listing.getImage(), this.mImageBatch);
        }
        getRootView().setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (FindsCategoryViewHolder.this.mViewTracker.c().c(b.A)) {
                    FindsCategoryViewHolder.this.mClickListener.a(findsSearchCategory.getUrl());
                } else {
                    FindsCategoryViewHolder.this.mClickListener.a(findsSearchCategory.getSearchUrl());
                }
            }
        });
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
