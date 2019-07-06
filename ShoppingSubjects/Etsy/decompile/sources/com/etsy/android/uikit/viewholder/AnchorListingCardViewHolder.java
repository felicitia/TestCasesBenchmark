package com.etsy.android.uikit.viewholder;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.m;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.util.af;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class AnchorListingCardViewHolder extends BaseViewHolder<ListingCard> {
    private final ImageView mCollectionsIcon = ((ImageView) findViewById(i.btn_lists));
    private final ImageView mFavIcon = ((ImageView) findViewById(i.btn_fav));
    private final boolean mHideListIcon = a.a().d().c(b.aX);
    private final c mImageBatch;
    private final ListingFullImageView mImageView = ((ListingFullImageView) findViewById(i.listing_image));
    /* access modifiers changed from: private */
    public final com.etsy.android.uikit.viewholder.a.a mListener;
    /* access modifiers changed from: private */
    public final LinearLayout mListingDetails = ((LinearLayout) findViewById(i.listing_details));
    /* access modifiers changed from: private */
    public final FrameLayout mListingImageRegion = ((FrameLayout) findViewById(i.listing_image_region));
    private final RatingIconView mRating = ((RatingIconView) findViewById(i.rating));
    private final TextView mTextAvailability = ((TextView) findViewById(i.listing_availability));
    private final TextView mTextPrice = ((TextView) findViewById(i.listing_price));
    private final TextView mTextRatingCount = ((TextView) findViewById(i.rating_count));
    private final TextView mTextShopName = ((TextView) findViewById(i.listing_shop));
    private final TextView mTextTitle = ((TextView) findViewById(i.listing_title));

    public AnchorListingCardViewHolder(ViewGroup viewGroup, @Nullable com.etsy.android.uikit.viewholder.a.a aVar, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_card_view_anchor_listing, viewGroup, false));
        this.mImageBatch = cVar;
        this.mListener = aVar;
        this.mImageView.setUseStandardRatio(true);
    }

    public void bind(final ListingCard listingCard) {
        this.mTextTitle.setText(listingCard.getTitle());
        this.mTextShopName.setText(listingCard.getShopName());
        Resources resources = this.itemView.getResources();
        if (listingCard.getShopAverageRating() > 0.0f) {
            this.mRating.setRating(listingCard.getShopAverageRating());
            this.mRating.setVisibility(0);
            if (listingCard.getShopTotalRatingCount() > 0) {
                this.mTextRatingCount.setText(resources.getString(o.parentheses, new Object[]{af.a((double) listingCard.getShopTotalRatingCount())}));
                this.mTextRatingCount.setVisibility(0);
            } else {
                this.mTextRatingCount.setVisibility(8);
            }
        } else {
            this.mRating.setVisibility(8);
            this.mTextRatingCount.setVisibility(8);
        }
        if (listingCard.isSoldOut()) {
            this.mTextPrice.setText(o.sold);
            this.mTextAvailability.setVisibility(8);
        } else {
            this.mTextPrice.setText(listingCard.getPrice().format());
            int quantity = listingCard.getQuantity();
            if (quantity > 0) {
                this.mTextAvailability.setText(resources.getQuantityString(m.n_items_available, quantity, new Object[]{Integer.valueOf(quantity)}));
                this.mTextAvailability.setVisibility(0);
            } else {
                this.mTextAvailability.setVisibility(8);
            }
        }
        this.mImageView.setImageInfo(listingCard.getListingImage(), this.mImageBatch);
        bindListingFavIcon(this.mFavIcon, listingCard, false);
        if (!this.mHideListIcon) {
            bindListingCollectionsIcon(this.mCollectionsIcon, listingCard);
        } else {
            this.mCollectionsIcon.setVisibility(8);
        }
        this.itemView.setOnClickListener(new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listingCard}) {
            public void onViewClick(View view) {
                if (AnchorListingCardViewHolder.this.mListener != null) {
                    AnchorListingCardViewHolder.this.mListener.a(listingCard);
                }
            }
        });
        j.a(this.mListingDetails.getViewTreeObserver(), (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                j.b(AnchorListingCardViewHolder.this.mListingDetails.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                int measuredHeight = AnchorListingCardViewHolder.this.mListingDetails.getMeasuredHeight();
                LayoutParams layoutParams = AnchorListingCardViewHolder.this.mListingImageRegion.getLayoutParams();
                layoutParams.height = measuredHeight;
                AnchorListingCardViewHolder.this.mListingImageRegion.setLayoutParams(layoutParams);
            }
        });
    }

    private void bindListingFavIcon(ImageView imageView, ListingCard listingCard, boolean z) {
        imageView.setImageResource(v.a().e() && listingCard.isFavorite() ? g.ic_favorited_selector : g.ic_favorite_selector);
        final boolean z2 = z;
        final ListingCard listingCard2 = listingCard;
        final ImageView imageView2 = imageView;
        AnonymousClass3 r3 = new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listingCard}) {
            public void onViewClick(View view) {
                if (z2) {
                    Toast.makeText(AnchorListingCardViewHolder.this.itemView.getContext(), o.favorite_own_item_message, 0).show();
                } else if (AnchorListingCardViewHolder.this.mListener != null) {
                    AnchorListingCardViewHolder.this.mListener.a(listingCard2, imageView2, AnchorListingCardViewHolder.this.getAdapterPosition());
                }
            }
        };
        imageView.setOnClickListener(r3);
    }

    private void bindListingCollectionsIcon(ImageView imageView, final ListingCard listingCard) {
        imageView.setImageResource(v.a().e() && listingCard.hasCollections() ? g.ic_listing_lists_added : g.ic_listing_lists);
        imageView.setOnClickListener(new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listingCard}) {
            public void onViewClick(View view) {
                if (AnchorListingCardViewHolder.this.mListener != null) {
                    AnchorListingCardViewHolder.this.mListener.b(listingCard);
                }
            }
        });
    }

    public void recycle() {
        this.mTextTitle.setText(null);
        this.mTextShopName.setText(null);
        this.mTextPrice.setText(null);
        this.mImageView.cleanUp();
    }
}
