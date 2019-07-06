package com.etsy.android.stylekit.cards;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.etsy.android.stylekit.CompoundVectorTextView;
import com.etsy.android.stylekit.a.b;
import com.etsy.android.stylekit.a.d;
import com.etsy.android.stylekit.a.f;
import com.etsy.android.stylekit.a.g;
import com.etsy.android.stylekit.a.j;
import com.etsy.android.stylekit.views.AspectRatioImageView;
import com.etsy.android.stylekit.views.FavoriteView;
import com.etsy.android.stylekit.views.FavoriteView.a;
import com.etsy.android.stylekit.views.ratings.RatingView;
import java.text.NumberFormat;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ShopCardView extends CollectionCardLayout {
    private FavoriteView mFavorite;
    private CompoundVectorTextView mLocation;
    private RatingView mRatingView;
    private AspectRatioImageView mShopIcon;
    private TextView mShopName;

    public ShopCardView(Context context) {
        super(context);
        init(null);
    }

    public ShopCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ShopCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(@Nullable AttributeSet attributeSet) {
        if (this.mContentContainer.getChildCount() == 0) {
            setContent(g.layout_shop_card_bottom);
        }
        this.mShopIcon = (AspectRatioImageView) findViewById(f.shop_card_icon);
        this.mShopName = (TextView) findViewById(f.shop_card_title);
        this.mRatingView = (RatingView) findViewById(f.shop_card_rating);
        this.mLocation = (CompoundVectorTextView) findViewById(f.shop_card_location);
        this.mFavorite = (FavoriteView) findViewById(f.shop_card_favorite);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.sk_ShopCardView);
            if (obtainStyledAttributes.hasValue(j.sk_ShopCardView_sk_icon)) {
                setShopIconResource(obtainStyledAttributes.getResourceId(j.sk_ShopCardView_sk_icon, -1));
            }
            int i = 8;
            if (obtainStyledAttributes.hasValue(j.sk_ShopCardView_sk_showLocation)) {
                this.mLocation.setVisibility(obtainStyledAttributes.getBoolean(j.sk_ShopCardView_sk_showLocation, false) ? 0 : 8);
            }
            if (obtainStyledAttributes.hasValue(j.sk_ShopCardView_sk_showRating)) {
                this.mRatingView.setVisibility(obtainStyledAttributes.getBoolean(j.sk_ShopCardView_sk_showRating, false) ? 0 : 8);
            }
            if (obtainStyledAttributes.hasValue(j.sk_ShopCardView_sk_showFavorite)) {
                FavoriteView favoriteView = this.mFavorite;
                if (obtainStyledAttributes.getBoolean(j.sk_ShopCardView_sk_showFavorite, true)) {
                    i = 0;
                }
                favoriteView.setVisibility(i);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setShopLocation(@NonNull CharSequence charSequence) {
        this.mLocation.setText(charSequence);
        this.mLocation.setVisibility(0);
        this.mRatingView.setVisibility(8);
    }

    public void setShopIconResource(@DrawableRes int i) {
        this.mShopIcon.setImageResource(i);
    }

    public void loadShopIconUrl(@Nullable String str) {
        Glide.b(getContext()).a(str).a((com.bumptech.glide.load.f<Bitmap>[]) new com.bumptech.glide.load.f[]{new RoundedCornersTransformation(getContext(), getResources().getDimensionPixelSize(d.sk_cardview_corner_radius), 0)}).a((ImageView) this.mShopIcon);
    }

    public void setShopName(@Nullable CharSequence charSequence) {
        this.mShopName.setText(charSequence);
    }

    public void setRating(@FloatRange(from = 0.0d) float f) {
        this.mRatingView.setRating(f);
        this.mRatingView.setVisibility(0);
        this.mLocation.setVisibility(8);
    }

    public void setRating(@FloatRange(from = 0.0d) float f, @IntRange(from = 0) int i) {
        this.mRatingView.showReviewCount(true);
        this.mRatingView.setReviewCount(i);
        setRating(f);
    }

    public void setItemCount(@IntRange(from = 0) int i) {
        setBadgeText(getResources().getQuantityString(com.etsy.android.stylekit.d.d(getContext(), b.token_shop_card_items_count__plurals), i, new Object[]{NumberFormat.getIntegerInstance().format((long) i)}));
    }

    public void setFavorite(boolean z) {
        this.mFavorite.setFavoriteState(z, true);
    }

    public void setFavoriteListener(@NonNull a aVar) {
        this.mFavorite.setRequestFavoriteChangeListener(aVar);
    }

    public void setFavoriteVisibility(boolean z) {
        this.mFavorite.setVisibility(z ? 0 : 8);
    }
}
