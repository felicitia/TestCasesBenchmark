package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.homescreen.CategoryRecommendationCard;
import com.etsy.android.lib.util.af;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

@Deprecated
public class CategoryRecCardViewHolder extends BaseViewHolder<CategoryRecommendationCard> {
    /* access modifiers changed from: private */
    public b mCardViewItemClickHandler;
    private final c mImageBatch;
    private final int mImageCount;
    private final LinearLayout mLayoutImages = ((LinearLayout) findViewById(R.id.images));
    private final TextView mTextSubtitle = ((TextView) findViewById(R.id.subtitle));
    private final TextView mTextTitle = ((TextView) findViewById(R.id.title));

    public CategoryRecCardViewHolder(ViewGroup viewGroup, b bVar, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_category_rec, viewGroup, false));
        this.mImageBatch = cVar;
        this.mImageCount = this.itemView.getResources().getInteger(R.integer.card_item_list_count);
        this.mCardViewItemClickHandler = bVar;
    }

    public void bind(final CategoryRecommendationCard categoryRecommendationCard) {
        this.mTextTitle.setText(categoryRecommendationCard.getCategoryName());
        int listingCount = categoryRecommendationCard.getListingCount();
        StringBuilder sb = new StringBuilder();
        sb.append(af.a((double) listingCount));
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.itemView.getResources().getQuantityString(R.plurals.recommendations_plurals_nt, listingCount));
        this.mTextSubtitle.setText(sb.toString());
        bindImages(categoryRecommendationCard);
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (CategoryRecCardViewHolder.this.mCardViewItemClickHandler != null) {
                    CategoryRecCardViewHolder.this.mCardViewItemClickHandler.a(categoryRecommendationCard);
                }
            }
        });
    }

    private void bindImages(CategoryRecommendationCard categoryRecommendationCard) {
        this.mLayoutImages.removeAllViews();
        for (int i = 0; i < this.mImageCount; i++) {
            BaseModelImage baseModelImage = null;
            if (categoryRecommendationCard.getListings().size() > i) {
                baseModelImage = ((ListingCard) categoryRecommendationCard.getListings().get(i)).getListingImage();
            }
            ListingFullImageView listingFullImageView = new ListingFullImageView(this.itemView.getContext());
            listingFullImageView.setScaleType(ScaleType.CENTER_CROP);
            listingFullImageView.setUseStandardRatio(true);
            listingFullImageView.setLayoutParams(new LayoutParams(0, 0, 1.0f));
            if (baseModelImage != null) {
                listingFullImageView.setImageInfo(baseModelImage, this.mImageBatch);
            } else if (i == this.mImageCount - 1) {
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image);
            } else {
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image_right_divider);
            }
            this.mLayoutImages.addView(listingFullImageView);
        }
    }
}
