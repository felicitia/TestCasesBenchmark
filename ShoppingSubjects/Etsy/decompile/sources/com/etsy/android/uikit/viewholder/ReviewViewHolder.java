package com.etsy.android.uikit.viewholder;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.MachineTranslationOneClickView;
import com.etsy.android.uikit.view.RatingIconView;

public class ReviewViewHolder extends ViewHolder {
    public int mAdHocItemType;
    public int mAdHocPosition;
    public ImageView mAppreciationPhoto;
    public ImageView mAvatar;
    public TextView mContactBuyer;
    public TextView mDate;
    public ImageView mImage;
    public View mInteractionArea;
    public View mListingClickArea;
    public MachineTranslationOneClickView mMachineTranslationView;
    /* access modifiers changed from: private */
    public a mOnClickListener;
    public RatingIconView mRating;
    public View mResponseView;
    public TextView mReviewMessage;
    public ImageView mSellerAvatar;
    public TextView mTitle;
    public TextView mTxtResponseMessage;
    public TextView mTxtShopOwnerName;
    public Button mUnhideAppreciationPhoto;
    public View mUserClickArea;
    private OnClickListener mViewClickListener = new TrackingOnClickListener() {
        public void onViewClick(View view) {
            if (view != null && ReviewViewHolder.this.mOnClickListener != null) {
                int adapterPosition = ReviewViewHolder.this.getAdapterPosition() >= 0 ? ReviewViewHolder.this.getAdapterPosition() : ReviewViewHolder.this.mAdHocPosition;
                if (view.getId() == i.review_appreciation_photo) {
                    ReviewViewHolder.this.mOnClickListener.onAppreciationPhotoClicked(adapterPosition);
                } else if (view.getId() == i.review_listing_click_area) {
                    ReviewViewHolder.this.mOnClickListener.onListingClicked(adapterPosition);
                } else if (view.getId() == i.review_user_click_area) {
                    ReviewViewHolder.this.mOnClickListener.onUserClicked(adapterPosition);
                } else if (view.getId() == i.translate_button) {
                    ReviewViewHolder.this.mOnClickListener.onTranslateReviewClicked(adapterPosition);
                } else if (view.getId() == i.shop_owner_response) {
                    ReviewViewHolder.this.mOnClickListener.onShopOwnerClicked(adapterPosition);
                } else if (view.getId() == i.review_contact_buyer) {
                    ReviewViewHolder.this.mOnClickListener.onContactBuyerClicked(adapterPosition);
                } else if (view.getId() == i.review_unhide_photo) {
                    ReviewViewHolder.this.mOnClickListener.onUnhidePhotoClicked(adapterPosition);
                }
            }
        }
    };
    public TextView reviewedByName;

    public interface a {
        void onAppreciationPhotoClicked(int i);

        void onContactBuyerClicked(int i);

        void onListingClicked(int i);

        void onShopOwnerClicked(int i);

        void onTranslateReviewClicked(int i);

        void onUnhidePhotoClicked(int i);

        void onUserClicked(int i);
    }

    public ReviewViewHolder(View view) {
        super(view);
        this.mTitle = (TextView) view.findViewById(i.listing_title);
        this.mImage = (ImageView) view.findViewById(i.item_image);
        this.mReviewMessage = (TextView) view.findViewById(i.review_message);
        this.mRating = (RatingIconView) view.findViewById(i.rating);
        this.mResponseView = view.findViewById(i.shop_owner_response);
        this.mResponseView.setOnClickListener(this.mViewClickListener);
        this.mTxtShopOwnerName = (TextView) view.findViewById(i.shop_owner_name);
        this.mTxtResponseMessage = (TextView) view.findViewById(i.response_message);
        this.mSellerAvatar = (ImageView) view.findViewById(i.review_seller_avatar);
        this.reviewedByName = (TextView) view.findViewById(i.reviewed_by_name);
        this.mDate = (TextView) view.findViewById(i.date);
        this.mAvatar = (ImageView) view.findViewById(i.avatar);
        this.mMachineTranslationView = (MachineTranslationOneClickView) view.findViewById(i.machine_translation_one_click);
        this.mAppreciationPhoto = (ImageView) view.findViewById(i.review_appreciation_photo);
        if (this.mAppreciationPhoto != null) {
            this.mAppreciationPhoto.setOnClickListener(this.mViewClickListener);
        }
        this.mUnhideAppreciationPhoto = (Button) view.findViewById(i.review_unhide_photo);
        if (this.mUnhideAppreciationPhoto != null) {
            Resources resources = view.getContext().getResources();
            this.mUnhideAppreciationPhoto.setOnClickListener(this.mViewClickListener);
            this.mUnhideAppreciationPhoto.setCompoundDrawablesWithIntrinsicBounds(com.etsy.android.iconsy.views.IconDrawable.a.a(resources).a(resources.getColor(e.text_mid_grey)).a((com.etsy.android.iconsy.a) StandardFontIcon.PICTURE).a(resources.getDimension(f.text_medium)).a(), null, null, null);
            this.mUnhideAppreciationPhoto.setCompoundDrawablePadding(resources.getDimensionPixelOffset(f.fixed_medium));
        }
        this.mListingClickArea = view.findViewById(i.review_listing_click_area);
        this.mListingClickArea.setOnClickListener(this.mViewClickListener);
        this.mUserClickArea = view.findViewById(i.review_user_click_area);
        this.mUserClickArea.setOnClickListener(this.mViewClickListener);
        this.mMachineTranslationView.setOnClickListener(this.mViewClickListener);
        this.mInteractionArea = view.findViewById(i.review_interaction_area);
        this.mContactBuyer = (TextView) view.findViewById(i.review_contact_buyer);
        if (this.mContactBuyer != null) {
            this.mContactBuyer.setOnClickListener(this.mViewClickListener);
        }
    }

    public void setOnClickListener(a aVar) {
        this.mOnClickListener = aVar;
    }
}
