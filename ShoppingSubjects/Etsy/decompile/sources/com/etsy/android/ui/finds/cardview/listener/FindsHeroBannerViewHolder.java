package com.etsy.android.ui.finds.cardview.listener;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.cardviewelement.FindsHeroBanner;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class FindsHeroBannerViewHolder extends BaseViewHolder<FindsHeroBanner> {
    private l mDisplayUtil;
    private final c mImageBatch;
    private final FullImageView mImageView = ((FullImageView) findViewById(R.id.image));
    private final TextView mSubTitle = ((TextView) findViewById(R.id.txt_subtitle));
    private final TextView mTitle = ((TextView) findViewById(R.id.txt_title));
    private int mViewType;

    public FindsHeroBannerViewHolder(ViewGroup viewGroup, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_finds_hero_banner_tall, viewGroup, false));
        this.mImageBatch = cVar;
        this.mDisplayUtil = new l(viewGroup.getContext());
    }

    public void bind(FindsHeroBanner findsHeroBanner) {
        super.bind(findsHeroBanner);
        this.mTitle.setText(findsHeroBanner.getTitle());
        String subtitle = findsHeroBanner.getSubtitle();
        if (!TextUtils.isEmpty(subtitle)) {
            this.mSubTitle.setText(subtitle);
            this.mSubTitle.setVisibility(0);
        } else {
            this.mSubTitle.setVisibility(8);
        }
        this.mImageView.setImageInfo(findsHeroBanner.getBannerImage(this.mDisplayUtil.c(), this.mDisplayUtil.f()), this.mImageBatch);
    }

    public void recycle() {
        this.mImageView.cleanUp();
    }
}
