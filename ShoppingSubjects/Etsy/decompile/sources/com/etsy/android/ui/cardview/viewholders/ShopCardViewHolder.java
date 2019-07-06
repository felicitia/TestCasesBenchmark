package com.etsy.android.ui.cardview.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.cardview.clickhandlers.m;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class ShopCardViewHolder extends BaseViewHolder<ShopCard> {
    private static final float FEED_IMAGE_HEIGHT_RATIO = 0.7941176f;
    /* access modifiers changed from: private */
    public final m mCardViewItemClickHandler;
    private ImageView mFavIcon;
    private final c mImageBatch;
    private ImageView mImageFeedActionAvatar;
    private int mImageHeight = 0;
    private int mImageWidth = 0;
    protected ImageView[] mListingImages;
    private RatingIconView mRatingIconShop;
    private TextView mShopName;
    public TextView mTextItemCount;
    private TextView mTextShopReviewCount;

    public static class a {
        private RatingIconView a;
        private TextView b;

        public a(Context context, RatingIconView ratingIconView, TextView textView) {
            this.a = ratingIconView;
            this.b = textView;
        }

        public void a(ShopCard shopCard) {
            int numRatings = shopCard.getNumRatings();
            if (numRatings == 0) {
                this.a.setVisibility(8);
                this.b.setVisibility(8);
                return;
            }
            this.a.setVisibility(0);
            this.a.setRating((float) shopCard.getAverageRating());
            this.b.setVisibility(0);
            this.b.setText(this.b.getResources().getQuantityString(R.plurals.review_counts, numRatings, new Object[]{Integer.valueOf(numRatings)}));
        }
    }

    public ShopCardViewHolder(ViewGroup viewGroup, m mVar, c cVar, boolean z) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_shop, viewGroup, false));
        this.mImageBatch = cVar;
        this.mCardViewItemClickHandler = mVar;
        this.mListingImages = new ImageView[4];
        this.mListingImages[0] = (ImageView) findViewById(R.id.img_item_1);
        this.mListingImages[1] = (ImageView) findViewById(R.id.img_item_2);
        this.mListingImages[2] = (ImageView) findViewById(R.id.img_item_3);
        this.mListingImages[3] = (ImageView) findViewById(R.id.img_item_4);
        this.mImageFeedActionAvatar = (ImageView) findViewById(R.id.object_avatar);
        this.mRatingIconShop = (RatingIconView) findViewById(R.id.rating);
        this.mTextShopReviewCount = (TextView) findViewById(R.id.review_count);
        this.mTextItemCount = (TextView) findViewById(R.id.item_count);
        this.mShopName = (TextView) findViewById(R.id.shop_name);
        this.mFavIcon = (ImageView) findViewById(R.id.btn_fav);
        if (z) {
            int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.horizontal_shop_card_width);
            getRootView().getLayoutParams().width = dimensionPixelSize;
            this.mImageWidth = dimensionPixelSize / 2;
        } else if (this.mImageWidth == 0 && this.mImageHeight == 0) {
            l lVar = new l(getRootView().getContext());
            this.mImageWidth = (int) (((double) Math.min(lVar.d(), lVar.e())) * ((((double) this.itemView.getResources().getInteger(R.integer.vespa_shop_card_span)) / ((double) this.itemView.getResources().getInteger(R.integer.vespa_grid_layout_max_span))) / 2.0d));
        }
        this.mImageHeight = (int) (((double) (((float) this.mImageWidth) * FEED_IMAGE_HEIGHT_RATIO)) + 0.5d);
    }

    public void bind(final ShopCard shopCard) {
        super.bind(shopCard);
        this.mShopName.setText(shopCard.getShopName());
        this.mImageBatch.a(shopCard.getAvatarUrl(), this.mImageFeedActionAvatar);
        int activeListingCount = shopCard.getActiveListingCount();
        this.mTextItemCount.setText(getRootView().getContext().getResources().getQuantityString(R.plurals.item_lowercase_quantity, activeListingCount, new Object[]{Integer.valueOf(activeListingCount)}));
        for (ImageView visibility : this.mListingImages) {
            visibility.setVisibility(8);
        }
        List cardListings = shopCard.getCardListings();
        for (int i = 0; i < cardListings.size(); i++) {
            BaseModelImage listingImage = ((ListingLike) cardListings.get(i)).getListingImage();
            bindImage(listingImage.get4to3ImageUrlForPixelWidth(this.mImageWidth), this.mListingImages[i], this.mImageWidth, this.mImageHeight, listingImage.getImageColor());
        }
        int numRatings = shopCard.getNumRatings();
        if (numRatings == 0) {
            this.mRatingIconShop.setVisibility(8);
            this.mTextShopReviewCount.setVisibility(8);
        } else {
            this.mRatingIconShop.setVisibility(0);
            this.mRatingIconShop.setRating((float) shopCard.getAverageRating());
            this.mTextShopReviewCount.setVisibility(0);
            this.mTextShopReviewCount.setText(getRootView().getContext().getResources().getQuantityString(R.plurals.review_counts, numRatings, new Object[]{Integer.valueOf(numRatings)}));
        }
        bindShopFavIcon(this.mFavIcon, shopCard);
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (ShopCardViewHolder.this.mCardViewItemClickHandler != null) {
                    ShopCardViewHolder.this.mCardViewItemClickHandler.a(shopCard, shopCard.getContentSource());
                }
            }
        });
    }

    private void bindImage(String str, ImageView imageView, int i, int i2, int i3) {
        if (str != null && imageView != null) {
            imageView.setVisibility(0);
            imageView.getLayoutParams().width = i;
            imageView.getLayoutParams().height = i2;
            this.mImageBatch.a(str, imageView, i, i2, i3);
        }
    }

    private void bindShopFavIcon(ImageView imageView, ShopCard shopCard) {
        final boolean isFavorite = shopCard.isFavorite();
        imageView.setVisibility(0);
        imageView.setImageResource(isFavorite ? R.drawable.ic_favorited_selector : R.drawable.ic_favorite_selector);
        final ShopCard shopCard2 = shopCard;
        final ImageView imageView2 = imageView;
        AnonymousClass2 r0 = new TrackingOnClickListener(new i[]{shopCard}) {
            public void onViewClick(View view) {
                if (ShopCardViewHolder.this.mCardViewItemClickHandler != null) {
                    ShopCardViewHolder.this.mCardViewItemClickHandler.a(shopCard2, imageView2, isFavorite);
                }
            }
        };
        imageView.setOnClickListener(r0);
    }

    public void recycle() {
        for (ImageView imageDrawable : this.mListingImages) {
            imageDrawable.setImageDrawable(null);
        }
    }
}
