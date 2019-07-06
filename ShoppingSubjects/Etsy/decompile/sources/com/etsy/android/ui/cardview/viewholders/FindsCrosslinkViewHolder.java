package com.etsy.android.ui.cardview.viewholders;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.ui.cardview.clickhandlers.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class FindsCrosslinkViewHolder extends BaseViewHolder<FindsCard> {
    private static final String TAG = f.a(FindsCrosslinkViewHolder.class);
    /* access modifiers changed from: private */
    public final e mClickListener;
    protected View mDraftIndicator = findViewById(R.id.draft_indicator);
    private final c mImageBatch;
    protected ImageViewWithAspectRatio mMainImage = ((ImageViewWithAspectRatio) findViewById(R.id.main_image));
    protected TextView mPageTitle = ((TextView) findViewById(R.id.page_title));

    public FindsCrosslinkViewHolder(ViewGroup viewGroup, e eVar, c cVar, boolean z, boolean z2) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_finds_crosslink_card, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickListener = eVar;
        if (z) {
            getRootView().getLayoutParams().width = this.itemView.getResources().getDimensionPixelOffset(R.dimen.horizontal_editors_picks_section_item_width);
        }
        if (z2) {
            Resources resources = this.itemView.getContext().getResources();
            int applyDimension = (int) TypedValue.applyDimension(1, resources.getDimension(R.dimen.finds_listing_margin), resources.getDisplayMetrics());
            ((MarginLayoutParams) getRootView().getLayoutParams()).setMargins(applyDimension, applyDimension, applyDimension, applyDimension);
        }
    }

    public void bind(final FindsCard findsCard) {
        if (findsCard.getTitle() != null) {
            this.mPageTitle.setText(findsCard.getTitle());
        }
        List images = findsCard.getImages();
        int i = 0;
        if (images != null && images.size() > 0) {
            this.mMainImage.setImageInfo((BaseModelImage) images.get(0), this.mImageBatch);
        } else if (findsCard.getImg() != null) {
            this.mMainImage.setImageInfo(findsCard.getImg(), this.mImageBatch);
        }
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                FindsCrosslinkViewHolder.this.mClickListener.a(findsCard);
            }
        });
        View view = this.mDraftIndicator;
        if (findsCard.isPublic()) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void recycle() {
        this.mMainImage.setImageDrawable(null);
    }
}
