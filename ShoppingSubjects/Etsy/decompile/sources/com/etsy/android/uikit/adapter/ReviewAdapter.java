package com.etsy.android.uikit.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.af;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.viewholder.ReviewViewHolder;
import com.etsy.android.uikit.viewholder.ReviewViewHolder.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ReviewAdapter extends EndlessRecyclerViewAdapter<ReceiptReview> {
    public static final int CARD_WITH_APP_PHOTO = 505;
    public static final int LIST_ITEM_WITH_APP_PHOTO = 507;
    private String mAnonymous;
    private int mAvatarWidth;
    private int mBlueColor;
    private final c mImageBatch;
    private int mImageHeight;
    private int mImageWidth;
    private final LayoutInflater mInflater;
    private boolean mIsMachineTranslationEnabled = false;
    private HashMap<Integer, MachineTranslationViewState> mMachineTranslationViewStates = new HashMap<>();
    private a mOnClickListener;
    private int mPhotoSize;
    private String mReviewedByFormat;
    private String mSellerAvatarUrl;
    private EtsyId mShopOwnerId;
    private String mShopOwnerName;

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        return CARD_WITH_APP_PHOTO;
    }

    public ReviewAdapter(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, cVar);
        this.mImageBatch = cVar;
        this.mInflater = LayoutInflater.from(fragmentActivity);
        Resources resources = fragmentActivity.getResources();
        this.mAvatarWidth = resources.getDimensionPixelOffset(f.review_card_avatar);
        this.mImageWidth = resources.getDimensionPixelOffset(f.review_image_width);
        this.mImageHeight = resources.getDimensionPixelOffset(f.review_image_height);
        this.mPhotoSize = resources.getDimensionPixelSize(f.review_appreciation_photo_size);
        this.mReviewedByFormat = fragmentActivity.getString(o.reviewed_by);
        this.mBlueColor = resources.getColor(e.blue);
        this.mAnonymous = fragmentActivity.getString(o.review_reviewer_name_anonymous);
    }

    public void addItems(Collection<? extends ReceiptReview> collection) {
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            for (ReceiptReview receiptReview : collection) {
                if (receiptReview.getReviews().size() > 1) {
                    for (Review cloneWithSingleReview : receiptReview.getReviews()) {
                        arrayList.add(ReceiptReview.cloneWithSingleReview(receiptReview, cloneWithSingleReview));
                    }
                } else {
                    arrayList.add(receiptReview);
                }
            }
        }
        super.addItems(arrayList);
    }

    public ReviewViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        View view = i != 505 ? i != 507 ? null : this.mInflater.inflate(k.list_item_review, viewGroup, false) : this.mInflater.inflate(k.card_review, viewGroup, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
        reviewViewHolder.mAdHocItemType = i;
        return reviewViewHolder;
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder != null && (viewHolder instanceof ReviewViewHolder) && getItem(i) != null) {
            bindReviewViewHolder((ReviewViewHolder) viewHolder, i);
        }
    }

    private void bindReviewViewHolder(ReviewViewHolder reviewViewHolder, int i) {
        ReceiptReview receiptReview = (ReceiptReview) getItem(i);
        Review review = (Review) receiptReview.getReviews().get(0);
        reviewViewHolder.setOnClickListener(this.mOnClickListener);
        reviewViewHolder.mAdHocPosition = i;
        if (!this.mMachineTranslationViewStates.containsKey(Integer.valueOf(i))) {
            this.mMachineTranslationViewStates.put(Integer.valueOf(i), new MachineTranslationViewState());
        }
        bindUserReviewArea(reviewViewHolder, receiptReview, review);
        bindMachineTranslationArea(reviewViewHolder, review, i);
        bindListingArea(reviewViewHolder, review);
        bindInteractionArea(reviewViewHolder);
        bindSellerResponse(reviewViewHolder, review);
    }

    private void bindInteractionArea(ReviewViewHolder reviewViewHolder) {
        if (reviewViewHolder.mInteractionArea != null && this.mShopOwnerId != null) {
            reviewViewHolder.mInteractionArea.setVisibility(0);
        } else if (reviewViewHolder.mInteractionArea != null) {
            reviewViewHolder.mInteractionArea.setVisibility(8);
        }
    }

    private void bindListingArea(ReviewViewHolder reviewViewHolder, Review review) {
        if (af.a(review.getListingTitle())) {
            reviewViewHolder.mTitle.setVisibility(0);
            reviewViewHolder.mTitle.setText(review.getListingTitle());
        } else {
            reviewViewHolder.mTitle.setVisibility(8);
        }
        this.mImageBatch.a(review.getListingImageUrl(), reviewViewHolder.mImage, this.mImageWidth, this.mImageHeight);
    }

    public void bindMachineTranslationArea(ReviewViewHolder reviewViewHolder, @NonNull Review review, int i) {
        if (!this.mIsMachineTranslationEnabled || !review.isTranslationEligible()) {
            reviewViewHolder.mMachineTranslationView.hideAllElements();
            return;
        }
        reviewViewHolder.mMachineTranslationView.showButtonElements();
        if (af.a(review.getTranslatedReviewMessage())) {
            reviewViewHolder.mMachineTranslationView.setTranslatedStateWithString(review.getTranslatedReviewMessage());
        } else {
            reviewViewHolder.mMachineTranslationView.setUntranslatedState();
        }
        if (((MachineTranslationViewState) this.mMachineTranslationViewStates.get(Integer.valueOf(i))).isLoadingTranslation()) {
            reviewViewHolder.mMachineTranslationView.showSpinner();
        } else {
            reviewViewHolder.mMachineTranslationView.hideSpinner();
        }
        if (((MachineTranslationViewState) this.mMachineTranslationViewStates.get(Integer.valueOf(i))).errorOccurredLoadingTranslation()) {
            reviewViewHolder.mMachineTranslationView.showErrorMessage();
        } else {
            reviewViewHolder.mMachineTranslationView.hideErrorMessage();
        }
    }

    private void bindSellerResponse(ReviewViewHolder reviewViewHolder, Review review) {
        if (review.hasResponse()) {
            reviewViewHolder.mResponseView.setVisibility(0);
            reviewViewHolder.mTxtShopOwnerName.setText(this.mShopOwnerName);
            reviewViewHolder.mTxtResponseMessage.setText(review.getResponse());
            if (!TextUtils.isEmpty(this.mSellerAvatarUrl)) {
                this.mImageBatch.b(this.mSellerAvatarUrl, reviewViewHolder.mSellerAvatar, this.mImageWidth, this.mImageHeight);
            } else {
                reviewViewHolder.mSellerAvatar.setVisibility(4);
            }
        } else {
            reviewViewHolder.mResponseView.setVisibility(8);
            reviewViewHolder.mTxtShopOwnerName.setText("");
            reviewViewHolder.mTxtResponseMessage.setText("");
            reviewViewHolder.mSellerAvatar.setVisibility(4);
        }
    }

    private void bindUserReviewArea(ReviewViewHolder reviewViewHolder, ReceiptReview receiptReview, Review review) {
        reviewViewHolder.mRating.setRating((float) review.getRating());
        if (af.a(review.getReviewMessage())) {
            reviewViewHolder.mReviewMessage.setVisibility(0);
            reviewViewHolder.mReviewMessage.setText(review.getReviewMessage());
        } else {
            reviewViewHolder.mReviewMessage.setVisibility(8);
        }
        String userDisplayName = receiptReview.getUserDisplayName();
        boolean b = af.b(userDisplayName);
        this.mImageBatch.b(receiptReview.getUserAvatarUrl(), reviewViewHolder.mAvatar, this.mAvatarWidth);
        reviewViewHolder.mDate.setText(af.a(receiptReview.getDate()));
        int itemViewType = reviewViewHolder.getItemViewType() >= 0 ? reviewViewHolder.getItemViewType() : reviewViewHolder.mAdHocItemType;
        if (itemViewType == 505 || itemViewType == 507) {
            if (!review.hasAppreciationPhoto()) {
                reviewViewHolder.mAppreciationPhoto.setVisibility(8);
            } else if (review.getAppreciationPhoto().isSellerApproved()) {
                reviewViewHolder.mUnhideAppreciationPhoto.setVisibility(8);
                reviewViewHolder.mAppreciationPhoto.setVisibility(0);
                this.mImageBatch.a(review.getAppreciationPhoto().getImageUrlForPixelWidth(this.mPhotoSize), reviewViewHolder.mAppreciationPhoto, this.mPhotoSize, this.mPhotoSize);
            } else if (this.mShopOwnerId != null) {
                reviewViewHolder.mUnhideAppreciationPhoto.setVisibility(0);
                reviewViewHolder.mAppreciationPhoto.setVisibility(8);
            } else {
                reviewViewHolder.mAppreciationPhoto.setVisibility(8);
                reviewViewHolder.mUnhideAppreciationPhoto.setVisibility(8);
            }
            if (b) {
                reviewViewHolder.reviewedByName.setText(userDisplayName);
            } else {
                reviewViewHolder.reviewedByName.setText(this.mAnonymous);
            }
        } else if (b) {
            reviewViewHolder.reviewedByName.setText(af.a(String.format(this.mReviewedByFormat, new Object[]{userDisplayName}), userDisplayName, this.mBlueColor));
        } else {
            reviewViewHolder.reviewedByName.setText(o.reviewed_by_anonymous);
        }
    }

    public void setOnClickListener(a aVar) {
        this.mOnClickListener = aVar;
    }

    public void setMachineTranslationEnabled(boolean z) {
        this.mIsMachineTranslationEnabled = z;
    }

    public void setSellerAvatarUrl(String str) {
        this.mSellerAvatarUrl = str;
    }

    public void setShopOwnerId(EtsyId etsyId) {
        this.mShopOwnerId = etsyId;
    }

    public void setShopOwnerName(String str) {
        this.mShopOwnerName = str;
    }

    public void onTranslationLoading(int i) {
        ((MachineTranslationViewState) this.mMachineTranslationViewStates.get(Integer.valueOf(i))).setLoadingTranslation();
    }

    public void onTranslationSuccess(int i) {
        ((MachineTranslationViewState) this.mMachineTranslationViewStates.get(Integer.valueOf(i))).setSuccessLoadingTranslation();
    }

    public void onTranslationError(int i) {
        ((MachineTranslationViewState) this.mMachineTranslationViewStates.get(Integer.valueOf(i))).setErrorLoadingTranslation();
    }
}
