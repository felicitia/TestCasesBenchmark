package com.etsy.android.ui.cardview.viewholders;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.cardview.clickhandlers.m;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class WideShopCardViewHolder extends BaseViewHolder<ShopLike> {
    private ImageView mAvatar;
    private final int mAvatarSize;
    /* access modifiers changed from: private */
    public final m mClickHandler;
    private final c mImageBatch;
    private LinearLayout mImageLayout;
    private int mImageNum = 4;
    private Drawable mLocationIcon;
    private final RatingIconView mRating;
    private final TextView mRatingCount;
    private final int mShopAvatarCornerRadius;
    private TextView mSubTitle;
    private TextView mTitle;

    public WideShopCardViewHolder(ViewGroup viewGroup, m mVar, c cVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_view_shop_wide, viewGroup, false));
        this.mClickHandler = mVar;
        this.mImageBatch = cVar;
        this.mTitle = (TextView) findViewById(R.id.title);
        this.mSubTitle = (TextView) findViewById(R.id.subtitle);
        this.mRating = (RatingIconView) findViewById(R.id.rating);
        this.mRatingCount = (TextView) findViewById(R.id.rating_count);
        this.mImageLayout = (LinearLayout) findViewById(R.id.image_layout);
        this.mAvatar = (ImageView) findViewById(R.id.avatar);
        Resources resources = this.itemView.getResources();
        this.mAvatarSize = resources.getDimensionPixelOffset(R.dimen.card_avatar_small);
        this.mShopAvatarCornerRadius = resources.getDimensionPixelOffset(R.dimen.gen_avatar_corners_small);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.sk_size_icon_smaller);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.fixed_one);
        this.mLocationIcon = com.etsy.android.uikit.c.a(viewGroup.getContext(), R.drawable.sk_ic_location, R.color.sk_gray_50);
        this.mLocationIcon.setBounds(0, dimensionPixelOffset2, dimensionPixelOffset, dimensionPixelOffset2 + dimensionPixelOffset);
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

    public void bind(@NonNull final ShopLike shopLike) {
        String avatarUrl;
        this.mTitle.setText(shopLike.getShopName());
        if (this.mClickHandler != null) {
            this.itemView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    WideShopCardViewHolder.this.mClickHandler.a(shopLike);
                }
            });
        }
        if (this.mImageNum > 0) {
            setListingItemImageRow(this.mImageLayout, shopLike.getCardListings());
        }
        String location = shopLike.getLocation();
        this.mAvatar.setVisibility(0);
        if (af.a(shopLike.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
            avatarUrl = shopLike.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
        } else {
            avatarUrl = shopLike.getAvatarUrl();
        }
        this.mImageBatch.b(avatarUrl, this.mAvatar, this.mShopAvatarCornerRadius, this.mAvatarSize, this.mAvatarSize);
        this.mRating.setVisibility(8);
        this.mRatingCount.setVisibility(8);
        if (af.a(location)) {
            this.mSubTitle.setText(location);
            this.mSubTitle.setTextColor(this.itemView.getResources().getColor(R.color.sk_gray_50));
            this.mSubTitle.setCompoundDrawables(this.mLocationIcon, null, null, null);
            this.mSubTitle.setVisibility(0);
            return;
        }
        this.mSubTitle.setVisibility(8);
    }
}
