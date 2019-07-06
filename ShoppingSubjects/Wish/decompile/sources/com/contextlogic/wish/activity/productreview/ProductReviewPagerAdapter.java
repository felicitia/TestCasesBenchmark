package com.contextlogic.wish.activity.productreview;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.viewpager.BasePagerViewInterface;
import java.util.ArrayList;

public class ProductReviewPagerAdapter extends PagerAdapter {
    private ProductReviewActivity mActivity;
    private ProductReviewFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher = new ImageHttpPrefetcher();
    private SparseArray<ProductReviewPagerView> mPagerViews = new SparseArray<>();
    private ReviewMoreView mReviewMoreView;
    private SelectItemView mSelectItemView;

    public enum ProductReviewSection {
        SELECT_ITEM,
        ITEM_RATING,
        RATING_DESCRIPTION,
        MERCHANT_RATING,
        REVIEW_MORE;

        public static ProductReviewSection fromInt(int i) {
            switch (i) {
                case 0:
                    return SELECT_ITEM;
                case 1:
                    return ITEM_RATING;
                case 2:
                    return RATING_DESCRIPTION;
                case 3:
                    return MERCHANT_RATING;
                case 4:
                    return REVIEW_MORE;
                default:
                    return null;
            }
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ProductReviewPagerAdapter(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment) {
        this.mActivity = productReviewActivity;
        this.mFragment = productReviewFragment;
    }

    public void handleInitialItemsLoadingSuccess(ArrayList<WishProductReviewItem> arrayList) {
        this.mSelectItemView.handleLoadingSuccess(arrayList);
    }

    public void handleInitialItemsLoadingFailure(String str) {
        this.mSelectItemView.handleLoadingFailure(str);
    }

    public void handleReviewSubmissionSuccess(ArrayList<WishProductReviewItem> arrayList, int i) {
        this.mReviewMoreView.handleLoadingSuccess(arrayList, i);
    }

    public void handleReviewSubmissionFailure(String str) {
        this.mReviewMoreView.handleLoadingFailure(str);
    }

    public int getCount() {
        return ProductReviewSection.values().length;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view;
        switch (ProductReviewSection.fromInt(i)) {
            case SELECT_ITEM:
                view = new SelectItemView(this.mActivity, this.mFragment, i, this.mImagePrefetcher);
                this.mSelectItemView = (SelectItemView) view;
                break;
            case ITEM_RATING:
                view = new ItemRatingView(this.mActivity, this.mFragment, i);
                break;
            case RATING_DESCRIPTION:
                view = new RatingDescriptionView(this.mActivity, this.mFragment, i);
                break;
            case MERCHANT_RATING:
                view = new MerchantRatingView(this.mActivity, this.mFragment, i);
                break;
            case REVIEW_MORE:
                view = new ReviewMoreView(this.mActivity, this.mFragment, i, this.mImagePrefetcher);
                this.mReviewMoreView = (ReviewMoreView) view;
                break;
            default:
                view = null;
                break;
        }
        this.mPagerViews.put(i, view);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ProductReviewPagerView productReviewPagerView = (ProductReviewPagerView) obj;
        ((ProductReviewPagerView) this.mPagerViews.get(i)).cleanup();
        viewGroup.removeView(productReviewPagerView);
        this.mPagerViews.delete(i);
    }

    public void handlePageSelected(int i) {
        if (this.mPagerViews.get(i) != null) {
            ((ProductReviewPagerView) this.mPagerViews.get(i)).handlePageSelected();
        }
    }

    public void onNextButtonClicked(int i) {
        if (this.mPagerViews.get(i) != null) {
            ((ProductReviewPagerView) this.mPagerViews.get(i)).onNextButtonClicked();
        }
    }

    public void releaseImages() {
        for (int i = 0; i < this.mPagerViews.size(); i++) {
            ((BasePagerViewInterface) this.mPagerViews.get(this.mPagerViews.keyAt(i))).releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mPagerViews.size(); i++) {
            ((BasePagerViewInterface) this.mPagerViews.get(this.mPagerViews.keyAt(i))).restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void cleanup() {
        for (int i = 0; i < this.mPagerViews.size(); i++) {
            ((BasePagerViewInterface) this.mPagerViews.get(this.mPagerViews.keyAt(i))).cleanup();
        }
    }
}
