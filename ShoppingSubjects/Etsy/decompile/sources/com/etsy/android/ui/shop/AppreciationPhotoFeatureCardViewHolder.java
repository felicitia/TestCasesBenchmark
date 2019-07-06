package com.etsy.android.ui.shop;

import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.AppreciationPhotoFeature;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.util.l;
import com.etsy.android.stylekit.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.AppreciationPhotoEmbeddedListingCardViewHolder;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppreciationPhotoFeatureCardViewHolder extends BaseViewHolder<AppreciationPhotoFeature> {
    private final ImageView mAppreciationPhoto = ((ImageView) findViewById(R.id.appreciation_photo));
    private final ImageView mBuyerAvatar = ((ImageView) findViewById(R.id.buyer_avatar));
    /* access modifiers changed from: private */
    public final a mClickHandler;
    private final SimpleDateFormat mCreateDateFormat;
    private final TextView mDateText = ((TextView) findViewById(R.id.date_text));
    private final c mImageBatch;
    private AppreciationPhotoEmbeddedListingCardViewHolder mListingCardViewHolder;
    private final TextView mMessageText = ((TextView) findViewById(R.id.message_text));
    private final View mPrimaryListingCard = findViewById(R.id.listing_matte);

    public AppreciationPhotoFeatureCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, c cVar, a aVar) {
        super(layoutInflater.inflate(R.layout.list_item_card_view_ap_photo_feature_card, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickHandler = aVar;
        AppreciationPhotoEmbeddedListingCardViewHolder appreciationPhotoEmbeddedListingCardViewHolder = new AppreciationPhotoEmbeddedListingCardViewHolder(this.mPrimaryListingCard, this.mClickHandler, cVar, false, false);
        this.mListingCardViewHolder = appreciationPhotoEmbeddedListingCardViewHolder;
        this.mCreateDateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
        LayoutParams layoutParams = (LayoutParams) this.mPrimaryListingCard.getLayoutParams();
        if (new l(this.itemView.getContext()).f()) {
            layoutParams.width = -1;
        } else {
            layoutParams.width = this.itemView.getResources().getDimensionPixelSize(R.dimen.ap_landing_page_primary_listing_card_width);
        }
        layoutParams.setMargins(0, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
    }

    public void bind(AppreciationPhotoFeature appreciationPhotoFeature) {
        this.mImageBatch.a(appreciationPhotoFeature.getPhotoUrl640x640(), this.mAppreciationPhoto, (int) BaseModelImage.DEFAULT_LOADING_COLOR);
        this.mImageBatch.b(appreciationPhotoFeature.getBuyerAvatarUrl(), this.mBuyerAvatar, this.itemView.getResources().getDimensionPixelSize(R.dimen.ap_landing_page_avatar_size));
        final ListingCard listingCard = appreciationPhotoFeature.getListingCard();
        this.mAppreciationPhoto.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (AppreciationPhotoFeatureCardViewHolder.this.mClickHandler != null) {
                    AppreciationPhotoFeatureCardViewHolder.this.mClickHandler.a(listingCard);
                }
            }
        });
        this.mDateText.setText(this.mCreateDateFormat.format(new Date(((long) Integer.parseInt(appreciationPhotoFeature.getCreateDate())) * 1000)));
        bindLandingPageMessage(appreciationPhotoFeature);
        this.mListingCardViewHolder.bind(listingCard);
        this.mListingCardViewHolder.toggleAvailability(appreciationPhotoFeature.isPrimaryListingAvailable());
    }

    private void bindLandingPageMessage(final AppreciationPhotoFeature appreciationPhotoFeature) {
        String string = this.itemView.getResources().getString(R.string.appreciation_photo_landing_page_message);
        String buyerRealName = appreciationPhotoFeature.getBuyerRealName();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(buyerRealName);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (AppreciationPhotoFeatureCardViewHolder.this.mClickHandler != null) {
                    AppreciationPhotoFeatureCardViewHolder.this.mClickHandler.a(appreciationPhotoFeature.getBuyerUserId());
                }
            }

            public void updateDrawState(@NonNull TextPaint textPaint) {
                textPaint.setColor(AppreciationPhotoFeatureCardViewHolder.this.itemView.getResources().getColor(R.color.sk_orange_30));
                e.a(AppreciationPhotoFeatureCardViewHolder.this.itemView.getContext(), textPaint, R.string.sk_typeface_bold);
            }
        }, 0, buyerRealName.length(), 0);
        CharSequence concat = TextUtils.concat(new CharSequence[]{spannableStringBuilder, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, string});
        this.mMessageText.setMovementMethod(LinkMovementMethod.getInstance());
        this.mMessageText.setText(concat, BufferType.SPANNABLE);
        this.mMessageText.setHighlightColor(0);
    }
}
