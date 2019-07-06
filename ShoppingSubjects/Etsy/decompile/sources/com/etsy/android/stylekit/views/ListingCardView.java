package com.etsy.android.stylekit.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.etsy.android.stylekit.a.C0085a;
import com.etsy.android.stylekit.a.b;
import com.etsy.android.stylekit.a.e;
import com.etsy.android.stylekit.a.f;
import com.etsy.android.stylekit.a.g;
import com.etsy.android.stylekit.a.i;
import com.etsy.android.stylekit.a.j;
import com.etsy.android.stylekit.d;
import com.etsy.android.stylekit.views.AspectRatioImageView.AspectRatio;

public class ListingCardView extends CardView {
    private TextView mAd;
    /* access modifiers changed from: private */
    public ImageView mFavorite;
    /* access modifiers changed from: private */
    public AspectRatioImageView mImage;
    private ImageView mList;
    private ViewGroup mListActions;
    private TextView mPrice;
    private TextView mShopName;
    private TextView mTitle;

    public interface a {
        void a(int i);
    }

    public ListingCardView(Context context) {
        super(context);
        init(null);
    }

    public ListingCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ListingCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(@Nullable AttributeSet attributeSet) {
        View inflate = inflate(getContext(), g.customview_listing_card, this);
        setForeground(AppCompatResources.getDrawable(getContext(), e.sk_touch_selector));
        this.mImage = (AspectRatioImageView) inflate.findViewById(f.cv_listingcard_image);
        this.mTitle = (TextView) inflate.findViewById(f.cv_listingcard_title);
        this.mShopName = (TextView) inflate.findViewById(f.cv_listingcard_shopname);
        this.mPrice = (TextView) inflate.findViewById(f.cv_listingcard_price);
        this.mListActions = (ViewGroup) inflate.findViewById(f.cv_listingcard_listactions);
        this.mFavorite = (ImageView) inflate.findViewById(f.cv_listingcard_favorite);
        this.mList = (ImageView) inflate.findViewById(f.cv_listingcard_list);
        this.mAd = (TextView) inflate.findViewById(f.cv_listingcard_ad);
        if (isInEditMode()) {
            setImageResource(e.listing_image_example);
            setTitle("Solid Walnut Bed Frame and Headboard");
            setShopName("hedgehouse");
            setPrice("$1,295.00");
            showListActions(true);
        }
        initAttributes(attributeSet);
    }

    private void initAttributes(@Nullable AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.CardView);
        setCardElevation(obtainStyledAttributes.getFloat(j.CardView_cardElevation, (float) d.c(getContext(), b.token_listing_card__elevation)));
        setRadius(obtainStyledAttributes.getFloat(j.CardView_cardCornerRadius, (float) d.c(getContext(), b.token_listing_card__corner_radius)));
        if (!obtainStyledAttributes.hasValue(j.CardView_cardBackgroundColor)) {
            setCardBackgroundColor(d.b(getContext(), b.token_listing_card__background));
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, j.sk_ListingCard);
        float f = obtainStyledAttributes2.getFloat(j.sk_ListingCard_sk_imageAspectRatio, AspectRatio.STANDARD_4_3.getAspectRatio());
        if (f == -1.0f) {
            this.mImage.setHeightAspectRatio(AspectRatio.STANDARD_4_3);
        } else {
            this.mImage.setHeightAspectRatio(f);
        }
        if (obtainStyledAttributes2.hasValue(j.sk_ListingCard_sk_title)) {
            this.mTitle.setText(obtainStyledAttributes2.getString(j.sk_ListingCard_sk_title));
        }
        if (obtainStyledAttributes2.hasValue(j.sk_ListingCard_sk_shopName)) {
            this.mShopName.setText(obtainStyledAttributes2.getString(j.sk_ListingCard_sk_shopName));
        }
        if (obtainStyledAttributes2.hasValue(j.sk_ListingCard_sk_price)) {
            this.mPrice.setText(obtainStyledAttributes2.getString(j.sk_ListingCard_sk_price));
        }
        if (obtainStyledAttributes2.hasValue(j.sk_ListingCard_sk_showListActions)) {
            showListActions(obtainStyledAttributes2.getBoolean(j.sk_ListingCard_sk_showListActions, false));
        }
        showAddToListIcon(obtainStyledAttributes2.getBoolean(j.sk_ListingCard_sk_showAddToListIcon, d.a(getContext(), b.token_listing_card__add_to_list__show, true)));
        if (obtainStyledAttributes2.hasValue(j.sk_ListingCard_sk_showAdIndicator)) {
            showAdIndicator(obtainStyledAttributes2.getBoolean(j.sk_ListingCard_sk_showAdIndicator, false));
        }
        obtainStyledAttributes2.recycle();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle.setText(charSequence);
    }

    public void setShopName(CharSequence charSequence) {
        this.mShopName.setText(charSequence);
    }

    public void setPrice(CharSequence charSequence) {
        this.mPrice.setText(charSequence);
    }

    public void setImageResource(@DrawableRes int i) {
        this.mImage.setImageResource(i);
    }

    public void setImageBackgroundColor(@ColorInt int i) {
        this.mImage.setBackgroundColor(i);
    }

    public void loadImageUrl(String str) {
        Glide.b(getContext()).a(str).a((ImageView) this.mImage);
    }

    public void addImageLayoutListener(final a aVar) {
        this.mImage.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                aVar.a(ListingCardView.this.mImage.getWidth());
                view.removeOnLayoutChangeListener(this);
            }
        });
    }

    public void setImageAspectRatio(float f) {
        this.mImage.setHeightAspectRatio(f);
    }

    public void setImageAspectRatio(float f, float f2) {
        this.mImage.setHeightAspectRatio(f2 / f);
    }

    public void showListActions(boolean z) {
        this.mListActions.setVisibility(z ? 0 : 8);
    }

    public void showAddToListIcon(boolean z) {
        this.mList.setVisibility(z ? 0 : 8);
    }

    public void setFavorited(boolean z, boolean z2) {
        if (this.mFavorite.isSelected() != z) {
            final String string = getResources().getString(z ? i.content_description_listing_favorited : i.content_description_listing_favorite);
            if (!z2 || !z) {
                this.mFavorite.setSelected(z);
                this.mFavorite.setContentDescription(string);
            } else {
                this.mFavorite.animate().cancel();
                AnonymousClass2 r4 = new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        ListingCardView.this.mFavorite.setSelected(true);
                        ListingCardView.this.mFavorite.setContentDescription(string);
                    }
                };
                Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), C0085a.favorite_grow);
                loadAnimator.setTarget(this.mFavorite);
                loadAnimator.addListener(r4);
                Animator loadAnimator2 = AnimatorInflater.loadAnimator(getContext(), C0085a.favorite_shrink);
                loadAnimator2.setTarget(this.mFavorite);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(new Animator[]{loadAnimator, loadAnimator2});
                animatorSet.start();
            }
        }
    }

    public void setOnFavoriteClickListener(OnClickListener onClickListener) {
        this.mFavorite.setOnClickListener(onClickListener);
    }

    public void setAddedToList(boolean z) {
        this.mList.setSelected(z);
        this.mList.setContentDescription(getResources().getString(z ? i.content_description_listing_list_added : i.content_description_listing_list));
    }

    public void setListClickListener(OnClickListener onClickListener) {
        this.mList.setOnClickListener(onClickListener);
    }

    public void showAdIndicator(boolean z) {
        this.mAd.setVisibility(z ? 0 : 8);
    }

    public boolean isFavorited() {
        return this.mFavorite.isSelected();
    }

    public boolean isAddedToList() {
        return this.mList.isSelected();
    }

    @NonNull
    public AspectRatioImageView getImageView() {
        return this.mImage;
    }

    @NonNull
    public TextView getTitleTextView() {
        return this.mTitle;
    }

    @NonNull
    public TextView getShopNameTextView() {
        return this.mShopName;
    }

    @NonNull
    public TextView getPriceTextView() {
        return this.mPrice;
    }

    @NonNull
    public ViewGroup getListActionsViewGroup() {
        return this.mListActions;
    }

    @NonNull
    public ImageView getFavoriteImageView() {
        return this.mFavorite;
    }

    @NonNull
    public ImageView getAddToListImageView() {
        return this.mList;
    }

    @NonNull
    public TextView getAdIndicatorTextView() {
        return this.mAd;
    }
}
