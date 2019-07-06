package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.cardview.clickhandlers.g;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class ListingCollectionViewHolder extends BaseViewHolder<Collection> {
    /* access modifiers changed from: private */
    public final g mClickHandler;
    private final c mImageBatch;
    private LinearLayout mImageLayout = ((LinearLayout) findViewById(R.id.image_layout));
    private TextView mSubTitle = ((TextView) findViewById(R.id.subtitle));
    private TextView mTitle = ((TextView) findViewById(R.id.title));
    private ImageView mTitleIcon = ((ImageView) findViewById(R.id.title_icon));

    public ListingCollectionViewHolder(ViewGroup viewGroup, g gVar, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_listing_collection, viewGroup, false));
        this.mClickHandler = gVar;
        this.mImageBatch = cVar;
    }

    public void bind(final Collection collection) {
        if (collection != null) {
            this.mTitle.setText(collection.getName());
            if (collection.isPrivate()) {
                this.mTitleIcon.setVisibility(0);
            } else {
                this.mTitleIcon.setVisibility(8);
            }
            this.itemView.setOnClickListener(new TrackingOnClickListener(new i[]{collection}) {
                public void onViewClick(View view) {
                    if (ListingCollectionViewHolder.this.mClickHandler != null) {
                        ListingCollectionViewHolder.this.mClickHandler.a(collection);
                    }
                }
            });
            int listingsCount = collection.getListingsCount();
            this.mSubTitle.setText(this.itemView.getResources().getQuantityString(R.plurals.item_titlecase_quantity, listingsCount, new Object[]{af.a((double) listingsCount)}));
            setListingItemImageRow(this.mImageLayout, collection.getRepresentativeListings());
        }
    }

    private void setListingItemImageRow(LinearLayout linearLayout, List<? extends ListingLike> list) {
        linearLayout.removeAllViews();
        int integer = this.itemView.getResources().getInteger(R.integer.card_item_list_count);
        for (int i = 0; i < integer; i++) {
            BaseModelImage baseModelImage = null;
            if (list.size() > i) {
                baseModelImage = ((ListingLike) list.get(i)).getListingImage();
            }
            ListingFullImageView listingFullImageView = new ListingFullImageView(linearLayout.getContext());
            listingFullImageView.setScaleType(ScaleType.CENTER_CROP);
            listingFullImageView.setUseStandardRatio(true);
            listingFullImageView.setLayoutParams(new LayoutParams(0, 0, 1.0f));
            if (baseModelImage != null) {
                listingFullImageView.setImageInfo(baseModelImage, this.mImageBatch);
            } else if (i == integer - 1) {
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image);
            } else {
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image_right_divider);
            }
            linearLayout.addView(listingFullImageView);
        }
    }
}
