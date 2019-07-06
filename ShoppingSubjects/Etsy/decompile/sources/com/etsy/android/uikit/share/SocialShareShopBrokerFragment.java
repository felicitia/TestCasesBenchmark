package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.util.af;
import com.etsy.android.uikit.view.RatingIconView;

public class SocialShareShopBrokerFragment extends SocialShareBrokerFragment {
    private ShopLike mShop;

    /* access modifiers changed from: protected */
    public void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mSubject = getString(o.share_shop_subject);
        this.mShop = (ShopLike) bundle.getSerializable(ResponseConstants.SHOP);
        if (this.mShop == null) {
            f.a((RuntimeException) new IllegalArgumentException("No shop specified"));
            return;
        }
        this.mUrl = this.mShop.getUrl();
        this.mText = getString(o.social_share_shop_message, this.mShop.getUrl());
        if (this.mShop != null && !this.mShop.getCardListings().isEmpty()) {
            BaseModelImage listingImage = ((ListingLike) this.mShop.getCardListings().get(0)).getListingImage();
            if (listingImage != null && !TextUtils.isEmpty(listingImage.getFullSizedImage())) {
                this.mImageUrl = listingImage.getFullSizedImage();
            }
        }
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(k.fragment_social_share_shop_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        showShop(view);
        ((TextView) view.findViewById(i.social_share_title)).setText(o.share_prompt_shop_message);
    }

    public void showShop(View view) {
        if (view != null) {
            TextView textView = (TextView) view.findViewById(i.shop_owner_name);
            TextView textView2 = (TextView) view.findViewById(i.shop_description);
            ImageView imageView = (ImageView) view.findViewById(i.shop_avatar);
            RatingIconView ratingIconView = (RatingIconView) view.findViewById(i.rating);
            if (this.mShop == null) {
                textView.setVisibility(8);
                textView2.setVisibility(8);
                imageView.setVisibility(8);
                ratingIconView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            textView2.setVisibility(0);
            imageView.setVisibility(0);
            ratingIconView.setVisibility(0);
            textView.setText(this.mShop.getShopName());
            if (TextUtils.isEmpty(this.mShop.getHeadline())) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(this.mShop.getHeadline());
            }
            ratingIconView.setRating((float) this.mShop.getAverageRating());
            String avatarUrl = this.mShop.getAvatarUrl();
            if (af.a(avatarUrl)) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(a.f.shop_avatar);
                getImageBatch().b(avatarUrl, imageView, getResources().getDimensionPixelSize(a.f.gen_avatar_corners_large), dimensionPixelSize, dimensionPixelSize);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public void onIntentItemClick(int i) {
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        if (isTwitter(resolveInfo)) {
            this.mText = getString(o.share_shop_twitter, this.mShop.getShopName(), this.mUrl);
        } else if (isGmail(resolveInfo)) {
            this.mSubject = getString(o.share_shop_email_subject);
            this.mText = getString(o.share_shop_email_message, this.mUrl);
        } else if (isPinterest(resolveInfo) || isGooglePlus(resolveInfo)) {
            this.mText = getString(o.share_shop_pinterest, this.mShop.getShopName());
        }
        super.onIntentItemClick(i);
    }
}
