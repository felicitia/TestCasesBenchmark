package com.contextlogic.wish.activity.productreview;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.camera.MediaInfo;
import com.contextlogic.wish.activity.productreview.ProductReviewPagerAdapter.ProductReviewSection;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import com.contextlogic.wish.util.BitmapUtil;
import com.contextlogic.wish.util.FileUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import java.util.ArrayList;

public class ProductReviewFragment extends UiFragment<ProductReviewActivity> {
    private AppBarLayout mImageContainer;
    /* access modifiers changed from: private */
    public MediaInfo mMediaInfo;
    private Button mNextButton;
    private LinearLayout mNextButtonContainer;
    /* access modifiers changed from: private */
    public OnPageChangeListener mPageChangeListener;
    /* access modifiers changed from: private */
    public ProductReviewPagerAdapter mPagerAdapter;
    /* access modifiers changed from: private */
    public Bundle mRatingInfo;
    private Bitmap mThumbnailBitmap;
    private AutoReleasableImageView mThumbnailImage;
    private AutoReleasableImageView mVideoIndicator;
    /* access modifiers changed from: private */
    public SafeViewPager mViewPager;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.product_review_fragment;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mRatingInfo = new Bundle();
        this.mImageContainer = (AppBarLayout) findViewById(R.id.product_review_fragment_image_container);
        this.mThumbnailImage = (AutoReleasableImageView) findViewById(R.id.product_review_fragment_thumbnail_image);
        this.mVideoIndicator = (AutoReleasableImageView) findViewById(R.id.video_indicator);
        this.mViewPager = (SafeViewPager) findViewById(R.id.product_review_fragment_viewpager);
        this.mNextButtonContainer = (LinearLayout) findViewById(R.id.product_review_fragment_next_button_container);
        this.mNextButton = (Button) findViewById(R.id.product_review_fragment_next_button);
        this.mViewPager.disableViewPager();
        this.mNextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductReviewFragment.this.onNextButtonClicked();
            }
        });
        this.mPageChangeListener = new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                KeyboardUtil.hideKeyboard((Fragment) ProductReviewFragment.this);
                ProductReviewFragment.this.mPagerAdapter.handlePageSelected(i);
            }
        };
        initializeValues();
    }

    private void initializeValues() {
        withActivity(new ActivityTask<ProductReviewActivity>() {
            public void performTask(ProductReviewActivity productReviewActivity) {
                ProductReviewFragment.this.mPagerAdapter = new ProductReviewPagerAdapter(productReviewActivity, ProductReviewFragment.this);
                ProductReviewFragment.this.mViewPager.setAdapter(ProductReviewFragment.this.mPagerAdapter);
                ProductReviewFragment.this.mMediaInfo = productReviewActivity.getMediaInfo();
                if (ProductReviewFragment.this.mMediaInfo == null) {
                    productReviewActivity.finishActivity();
                    return;
                }
                ProductReviewFragment.this.initThumbnailImage();
                if (ProductReviewFragment.this.mMediaInfo.getType() == 0) {
                    ProductReviewFragment.this.mRatingInfo.putString(ProductReviewPagerView.PRODUCT_UPLOADED_IMAGE_NAME_KEY, productReviewActivity.getUploadedImageName());
                } else {
                    ProductReviewFragment.this.mRatingInfo.putString(ProductReviewPagerView.PRODUCT_UPLOADED_VIDEO_ID_KEY, productReviewActivity.getUploadedVideoId());
                }
                if (productReviewActivity.getReviewItem() == null) {
                    ProductReviewFragment.this.loadProducts();
                    ProductReviewFragment.this.scrollTo(0, false);
                } else {
                    ProductReviewFragment.this.mRatingInfo.putParcelable(ProductReviewPagerView.PRODUCT_KEY, productReviewActivity.getReviewItem());
                    if (productReviewActivity.getReviewItem().hasRating()) {
                        ProductReviewFragment.this.scrollTo(ProductReviewSection.REVIEW_MORE.ordinal(), false);
                    } else {
                        ProductReviewFragment.this.scrollTo(ProductReviewSection.ITEM_RATING.ordinal(), false);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void onNextButtonClicked() {
        this.mPagerAdapter.onNextButtonClicked(this.mViewPager.getCurrentItem());
    }

    public void setButtonState(boolean z, boolean z2) {
        if (this.mNextButton != null) {
            this.mNextButtonContainer.setVisibility(z ? 0 : 8);
            this.mNextButton.setEnabled(z2);
        }
    }

    /* access modifiers changed from: private */
    public void initThumbnailImage() {
        this.mThumbnailImage.setImageBitmap(getThumbnailImage());
        if (this.mMediaInfo.getType() == 1) {
            this.mVideoIndicator.setVisibility(0);
        }
    }

    public Bitmap getThumbnailImage() {
        Bitmap bitmap;
        if (this.mThumbnailBitmap != null) {
            return this.mThumbnailBitmap;
        }
        int type = this.mMediaInfo.getType();
        Uri uri = this.mMediaInfo.getUri();
        if (type == 0) {
            bitmap = BitmapUtil.openImageUri(uri);
        } else {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(FileUtil.getPath(this.mMediaInfo.getUri()));
            bitmap = mediaMetadataRetriever.getFrameAtTime(0);
        }
        this.mThumbnailBitmap = cropAndResizeBitmap(bitmap, (int) WishApplication.getInstance().getResources().getDimension(R.dimen.product_review_upload_media_image_size), (int) WishApplication.getInstance().getResources().getDimension(R.dimen.product_review_upload_media_image_size));
        return this.mThumbnailBitmap;
    }

    public Bitmap cropAndResizeBitmap(Bitmap bitmap, int i, int i2) {
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height > width) {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, (height - width) / 2, width, width);
        } else {
            bitmap2 = Bitmap.createBitmap(bitmap, (width - height) / 2, 0, height, height);
        }
        return Bitmap.createScaledBitmap(bitmap2, i, i2, true);
    }

    public void releaseImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.restoreImages();
        }
    }

    public void loadProducts() {
        withServiceFragment(new ServiceTask<BaseActivity, ProductReviewServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductReviewServiceFragment productReviewServiceFragment) {
                productReviewServiceFragment.getItemsWithoutUgc();
            }
        });
    }

    public void updateRatingInfo(Bundle bundle) {
        this.mRatingInfo.putAll(bundle);
    }

    public void handleInitialItemsLoadingSuccess(ArrayList<WishProductReviewItem> arrayList) {
        this.mPagerAdapter.handleInitialItemsLoadingSuccess(arrayList);
    }

    public void handleInitialItemsLoadingFailure(String str) {
        this.mPagerAdapter.handleInitialItemsLoadingFailure(str);
    }

    public void handleReviewSubmissionSuccess(ArrayList<WishProductReviewItem> arrayList, int i) {
        this.mPagerAdapter.handleReviewSubmissionSuccess(arrayList, i);
    }

    public void handleReviewSubmissionFailure(String str) {
        this.mPagerAdapter.handleReviewSubmissionFailure(str);
    }

    public void goToNextPager() {
        scrollTo(this.mViewPager.getCurrentItem() + 1, true);
    }

    public void scrollTo(final int i, boolean z) {
        if (i < ProductReviewSection.values().length) {
            this.mViewPager.setCurrentItem(i, z);
            this.mViewPager.post(new Runnable() {
                public void run() {
                    ProductReviewFragment.this.mPageChangeListener.onPageSelected(i);
                }
            });
        }
    }

    public int getCurrentItem() {
        if (this.mViewPager != null) {
            return this.mViewPager.getCurrentItem();
        }
        return 0;
    }

    public WishProductReviewItem getSelectedItem() {
        return (WishProductReviewItem) this.mRatingInfo.getParcelable(ProductReviewPagerView.PRODUCT_KEY);
    }

    public void uploadRating() {
        withServiceFragment(new ServiceTask<BaseActivity, ProductReviewServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ProductReviewServiceFragment productReviewServiceFragment) {
                productReviewServiceFragment.uploadRating(ProductReviewFragment.this.mRatingInfo);
            }
        });
    }

    public void hideImageContainer(boolean z) {
        this.mImageContainer.setExpanded(false, z);
    }

    public void showImageContainer(boolean z) {
        this.mImageContainer.setExpanded(true, z);
    }

    public void resetHeader(View view) {
        this.mImageContainer.removeAllViews();
        if (view != null) {
            this.mImageContainer.addView(view);
        }
    }

    public boolean onBackPressed() {
        int currentItem = this.mViewPager.getCurrentItem();
        int i = currentItem - 1;
        switch (ProductReviewSection.fromInt(currentItem)) {
            case SELECT_ITEM:
            case REVIEW_MORE:
                return false;
            case ITEM_RATING:
                if (((ProductReviewActivity) getBaseActivity()).getReviewItem() == null) {
                    loadProducts();
                    this.mRatingInfo.clear();
                    this.mRatingInfo.putString(ProductReviewPagerView.PRODUCT_UPLOADED_IMAGE_NAME_KEY, ((ProductReviewActivity) getBaseActivity()).getUploadedImageName());
                    this.mRatingInfo.putString(ProductReviewPagerView.PRODUCT_UPLOADED_VIDEO_ID_KEY, ((ProductReviewActivity) getBaseActivity()).getUploadedVideoId());
                    break;
                } else {
                    return false;
                }
        }
        scrollTo(i, true);
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.cleanup();
        }
    }
}
