package com.etsy.android.uikit.viewholder;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.j;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.apiv3.FreeShippingData;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.util.l;
import com.etsy.android.stylekit.CompoundVectorTextView;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.TrackingOnLongClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.viewholder.a.a;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ListIterator;

public abstract class ExtensibleListingCardViewHolder extends BaseViewHolder<ListingCard> {
    private static final double HSCROLL_WIDTH_RATIO = 0.75d;
    private final CompoundVectorTextView mCartBadge = ((CompoundVectorTextView) findViewById(i.cart_badge));
    protected final a mClickHandler;
    final ImageView mFavIcon = ((ImageView) findViewById(i.btn_fav));
    private final c mImageBatch;
    private final ListingFullImageView mListingFullImageView = ((ListingFullImageView) findViewById(i.listing_image));
    final ImageView mMenuIcon = ((ImageView) findViewById(i.btn_menu));
    private final TextView mSearchAdsIndicator = ((TextView) findViewById(i.search_ads_indicator));
    private final TextView mTextDiscountInfo = ((TextView) findViewById(i.discount_info));
    final TextView mTextPrice = ((TextView) findViewById(i.listing_price));
    private final TextView mTextShopName = ((TextView) findViewById(i.listing_shop));
    private final TextView mTextTitle = ((TextView) findViewById(i.listing_title));

    /* access modifiers changed from: protected */
    @Nullable
    public TrackingOnLongClickListener getOnLongClickListener(ListingCard listingCard) {
        return null;
    }

    public ExtensibleListingCardViewHolder(@NonNull View view, @Nullable a aVar, @NonNull c cVar, boolean z, boolean z2) {
        super(view);
        this.mImageBatch = cVar;
        this.mClickHandler = aVar;
        if (z) {
            getRootView().getLayoutParams().width = getHorizontalScrollWidth(view.getContext());
        }
        if (this.mListingFullImageView != null) {
            this.mListingFullImageView.setUseStandardRatio(!z2);
        }
    }

    public void bind(ListingCard listingCard) {
        this.mTextTitle.setText(listingCard.getTitle());
        int i = 0;
        this.mTextShopName.setVisibility(0);
        this.mTextShopName.setText(listingCard.getShopName());
        if (this.mViewTracker.c().c(b.bB)) {
            bindListingPriceDiscountsAndSignals(listingCard);
        } else {
            bindListingPrice(listingCard);
        }
        this.mListingFullImageView.setImageInfo(listingCard.getListingImage(), this.mImageBatch);
        this.mSearchAdsIndicator.setVisibility(listingCard.isAd() ? 0 : 4);
        CompoundVectorTextView compoundVectorTextView = this.mCartBadge;
        if (!listingCard.isInCart()) {
            i = 4;
        }
        compoundVectorTextView.setVisibility(i);
        this.itemView.setOnClickListener(getOnClickListener(listingCard));
        TrackingOnLongClickListener onLongClickListener = getOnLongClickListener(listingCard);
        if (onLongClickListener != null) {
            this.itemView.setOnLongClickListener(onLongClickListener);
        }
        bindListingFavIcon(this.mFavIcon, listingCard);
        bindMenuIcon(this.mMenuIcon, listingCard);
        this.mListingFullImageView.setContentDescription(null);
    }

    /* access modifiers changed from: protected */
    public void bindMenuIcon(ImageView imageView, final ListingLike listingLike) {
        if (this.mClickHandler == null) {
            imageView.setVisibility(8);
        } else {
            imageView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    ExtensibleListingCardViewHolder.this.mClickHandler.d(listingLike);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public TrackingOnClickListener getOnClickListener(final ListingCard listingCard) {
        return new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listingCard}) {
            public void onViewClick(View view) {
                if (ExtensibleListingCardViewHolder.this.mClickHandler != null) {
                    ExtensibleListingCardViewHolder.this.mClickHandler.a(listingCard);
                }
            }
        };
    }

    private void bindListingPrice(ListingCard listingCard) {
        if (listingCard.isSoldOut()) {
            this.mTextPrice.setText(o.sold);
        } else {
            this.mTextPrice.setText(listingCard.getPrice().format());
        }
    }

    private void bindListingPriceDiscountsAndSignals(ListingCard listingCard) {
        String format = listingCard.getPrice().format();
        String formattedDiscountedPrice = listingCard.getFormattedDiscountedPrice();
        String formattedDiscountDescription = listingCard.getFormattedDiscountDescription();
        this.mTextDiscountInfo.setVisibility(0);
        if (listingCard.isSoldOut()) {
            this.mTextDiscountInfo.setText("");
            this.mTextPrice.setText(o.sold);
        } else {
            this.mTextPrice.setText(format);
            if (listingCard.getSignalPeckingOrderList() != null) {
                ListIterator listIterator = listingCard.getSignalPeckingOrderList().listIterator();
                while (listIterator.hasNext()) {
                    String str = (String) listIterator.next();
                    if (str.equals("free_shipping") && canShowFreeShippingCopy(listingCard.getFreeShippingData())) {
                        showFreeShippingSignal(listingCard.getFreeShippingData().getFreeShippingCopy());
                        return;
                    } else if (str.equals("promotion") && canShowPromotionSignal(formattedDiscountDescription, formattedDiscountedPrice)) {
                        showPromotionSignal(formattedDiscountedPrice, formattedDiscountDescription, format);
                        return;
                    }
                }
            }
            showNoSignal();
        }
    }

    private boolean canShowPromotionSignal(String str, String str2) {
        return com.etsy.android.lib.config.a.a().d().c(b.bB) && (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str));
    }

    private void showFreeShippingSignal(String str) {
        this.mTextDiscountInfo.setText(str);
        this.mTextDiscountInfo.setTextColor(this.mTextDiscountInfo.getResources().getColor(e.sk_gray_50));
    }

    private boolean canShowFreeShippingCopy(FreeShippingData freeShippingData) {
        return com.etsy.android.lib.config.a.a().d().c(b.bC) && freeShippingData != null && !TextUtils.isEmpty(freeShippingData.getFreeShippingCopy());
    }

    private void showNoSignal() {
        this.mTextDiscountInfo.setText("");
        this.mTextDiscountInfo.setVisibility(4);
    }

    private void showPromotionSignal(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str3);
            spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, spannableStringBuilder.length(), 33);
            if (!TextUtils.isEmpty(str2)) {
                spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str2);
            }
            this.mTextDiscountInfo.setText(spannableStringBuilder);
            this.mTextPrice.setText(str);
        } else if (!TextUtils.isEmpty(str2)) {
            this.mTextDiscountInfo.setText(str2);
        }
        this.mTextDiscountInfo.setTextColor(this.mTextDiscountInfo.getResources().getColor(e.sk_text_green));
    }

    private void bindListingFavIcon(final ImageView imageView, final ListingCard listingCard) {
        boolean z = v.a().e() && listingCard.isFavorite();
        imageView.setContentDescription(imageView.getResources().getString(z ? o.content_description_listing_favorited : o.content_description_listing_favorite));
        imageView.setImageResource(z ? g.ic_favorited_selector : g.ic_favorite_selector);
        imageView.setOnClickListener(new TrackingOnClickListener(new com.etsy.android.lib.logger.i[]{listingCard}) {
            public void onViewClick(View view) {
                if (ExtensibleListingCardViewHolder.this.mClickHandler != null) {
                    ExtensibleListingCardViewHolder.this.mClickHandler.a(listingCard, imageView, ExtensibleListingCardViewHolder.this.getAdapterPosition());
                }
            }
        });
    }

    public void recycle() {
        this.mTextTitle.setText(null);
        this.mTextShopName.setText(null);
        this.mTextPrice.setText(null);
        this.mListingFullImageView.cleanUp();
        this.mTextDiscountInfo.setText(null);
    }

    /* access modifiers changed from: 0000 */
    public void hideTitleAndShopName() {
        this.mTextTitle.setVisibility(8);
        this.mTextShopName.setVisibility(8);
        this.mListingFullImageView.setContentDescription(this.mTextTitle.getText());
    }

    public void hideShopName() {
        this.mTextShopName.setVisibility(8);
    }

    /* access modifiers changed from: 0000 */
    public void hideAllText() {
        hideTitleAndShopName();
        this.mTextPrice.setVisibility(8);
        this.mTextDiscountInfo.setVisibility(8);
        this.mMenuIcon.setVisibility(8);
    }

    private static int getHorizontalScrollWidth(Context context) {
        Resources resources = context.getResources();
        return l.a(context, (int) ((((double) resources.getConfiguration().screenWidthDp) * 0.75d) / ((double) (resources.getInteger(j.vespa_grid_layout_max_span) / resources.getInteger(j.vespa_listing_card_span)))));
    }

    public void hideFavIcon() {
        this.mFavIcon.setVisibility(8);
    }
}
