package com.contextlogic.wish.activity.imageviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishProductExtraImage.SourceType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ExpandableTextView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.ProfileImageView;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ImageViewerFragment extends UiFragment<ImageViewerActivity> {
    /* access modifiers changed from: private */
    public ImageViewerAdapter mAdapter;
    private LinearLayout mDetailsContainer;
    private View mDivider;
    private ImageHttpPrefetcher mImagePrefetcher;
    protected String mImageUrl;
    /* access modifiers changed from: private */
    public int mMediaLoadingType;
    /* access modifiers changed from: private */
    public ArrayList<WishProductExtraImage> mMediaSources;
    /* access modifiers changed from: private */
    public int mMediaSourcesNextOffset;
    /* access modifiers changed from: private */
    public boolean mNoMoreMediaSources;
    protected String mProductId;
    private HashSet<Integer> mSeenUserImages;
    /* access modifiers changed from: private */
    public Toolbar mToolbar;
    private ThemedTextView mUploadDate;
    private RelativeLayout mUploaderActions;
    /* access modifiers changed from: private */
    public ExpandableTextView mUploaderComment;
    private LinearLayout mUploaderCommentContainer;
    private ThemedTextView mUploaderCommentExpansionButton;
    private LinearLayout mUploaderContainer;
    private ProfileImageView mUploaderImageV2;
    private ThemedTextView mUploaderName;
    /* access modifiers changed from: private */
    public ThemedTextView mUpvoteCount;
    private AutoReleasableImageView mUpvoteIcon;
    private ThemedTextView mUpvoteText;
    private ViewPager mViewPager;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.image_viewer_fragment;
    }

    public void restoreImages() {
        if (this.mAdapter != null) {
            this.mAdapter.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void releaseImages() {
        if (this.mAdapter != null) {
            this.mAdapter.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        int i;
        initMediaSources();
        this.mSeenUserImages = new HashSet<>();
        this.mToolbar = (Toolbar) findViewById(R.id.image_viewer_fragment_toolbar);
        this.mToolbar.setNavigationIcon((int) R.drawable.action_bar_back);
        this.mToolbar.setTitleTextColor(WishApplication.getInstance().getResources().getColor(17170443));
        this.mToolbar.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.dark_translucent_toolbar));
        withActivity(new ActivityTask<ImageViewerActivity>() {
            public void performTask(ImageViewerActivity imageViewerActivity) {
                if (!imageViewerActivity.isShowingSingleImage()) {
                    ImageViewerFragment.this.mToolbar.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.photo_video_viewer_nav_bar));
                    imageViewerActivity.getActionBarManager().addActionBarItem(new ActionBarItem(WishApplication.getInstance().getString(R.string.view_all), 4001, (int) R.drawable.view_all_grid_icon));
                }
                imageViewerActivity.getActionBarManager().setBadgeVisible(false);
            }
        });
        this.mUploaderContainer = (LinearLayout) findViewById(R.id.fragment_product_photos_uploader_container);
        this.mUploaderImageV2 = (ProfileImageView) findViewById(R.id.fragment_product_photos_uploader_image_v2);
        this.mUploaderCommentContainer = (LinearLayout) findViewById(R.id.fragment_product_photos_comment_container);
        this.mUploaderComment = (ExpandableTextView) findViewById(R.id.fragment_product_photos_comment);
        this.mUploaderCommentExpansionButton = (ThemedTextView) findViewById(R.id.fragment_product_photos_comment_expansion_button);
        this.mUploaderName = (ThemedTextView) findViewById(R.id.fragment_product_photos_uploader_name);
        this.mUploadDate = (ThemedTextView) findViewById(R.id.fragment_product_photos_upload_date);
        this.mDivider = findViewById(R.id.fragment_product_photos_uploader_divider);
        this.mUpvoteText = (ThemedTextView) findViewById(R.id.fragment_product_photos_uploader_thanks_text);
        this.mUploaderActions = (RelativeLayout) findViewById(R.id.fragment_product_photos_uploader_actions);
        this.mUpvoteCount = (ThemedTextView) findViewById(R.id.fragment_product_photos_uploader_upvote_count);
        this.mUpvoteIcon = (AutoReleasableImageView) findViewById(R.id.fragment_product_photos_uploader_thanks_icon);
        this.mDetailsContainer = (LinearLayout) findViewById(R.id.fragment_product_photos_details_container);
        this.mDetailsContainer.setVisibility(8);
        this.mDivider.setVisibility(8);
        this.mUploaderActions.setVisibility(8);
        if (getSavedInstanceState() != null) {
            i = getSavedInstanceState().getInt("SavedStateIndex", 0);
        } else {
            i = ((ImageViewerActivity) getBaseActivity()).getStartIndex();
        }
        this.mViewPager = (ViewPager) findViewById(R.id.image_viewer_fragment_view_pager);
        this.mAdapter = new ImageViewerAdapter((ImageViewerActivity) getBaseActivity(), this, i);
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                ImageViewerFragment.this.updateToolbarTitle(i);
                ImageViewerFragment.this.refreshUploader();
                ImageViewerFragment.this.mAdapter.handlePageSelected(i);
                ImageViewerFragment.this.logUgcImpressions();
            }
        });
        logUgcImpressions();
        if (this.mMediaSources != null) {
            Iterator it = this.mMediaSources.iterator();
            while (it.hasNext()) {
                WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) it.next();
                if (wishProductExtraImage.getSourceType() == SourceType.Image) {
                    this.mImagePrefetcher.queueImage(wishProductExtraImage.getImage());
                }
            }
        }
        this.mViewPager.setCurrentItem(i);
        updateToolbarTitle(i);
    }

    public void initMediaSources() {
        withActivity(new ActivityTask<ImageViewerActivity>() {
            public void performTask(ImageViewerActivity imageViewerActivity) {
                ImageViewerFragment.this.mMediaSources = imageViewerActivity.getMediaSources();
                ImageViewerFragment.this.mImageUrl = imageViewerActivity.getImageUrl();
                ImageViewerFragment.this.mProductId = imageViewerActivity.getProductId();
                ImageViewerFragment.this.mMediaLoadingType = imageViewerActivity.getMediaLoadingType();
                ImageViewerFragment.this.mNoMoreMediaSources = imageViewerActivity.getNoMoreMediaSources();
                ImageViewerFragment.this.mMediaSourcesNextOffset = imageViewerActivity.getMediaSourcesNextOffset();
            }
        });
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putInt("SavedStateIndex", this.mViewPager.getCurrentItem());
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i != 4001) {
            return super.handleActionBarItemSelected(i);
        }
        handleViewAllButtonSelected();
        return true;
    }

    public void handleViewAllButtonSelected() {
        withActivity(new ActivityTask<ImageViewerActivity>() {
            public void performTask(ImageViewerActivity imageViewerActivity) {
                if (imageViewerActivity.wasOpenedFromGrid()) {
                    ImageViewerFragment.this.closeActivity();
                } else {
                    ImageViewerFragment.this.openGrid();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void logUgcImpressions() {
        int currentItem = this.mViewPager.getCurrentItem();
        if (this.mMediaSources != null && currentItem < this.mMediaSources.size()) {
            WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mMediaSources.get(currentItem);
            if (this.mSeenUserImages != null && !this.mSeenUserImages.contains(Integer.valueOf(currentItem))) {
                if (wishProductExtraImage.getRatingId() != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("rating_id", wishProductExtraImage.getRatingId());
                    if (wishProductExtraImage.getSourceType() == SourceType.Video) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_IMAGE_VIEWER_UGC_VIDEO, this.mProductId, hashMap);
                    } else {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_IMAGE_VIEWER_UGC_IMAGE, this.mProductId, hashMap);
                    }
                }
                this.mSeenUserImages.add(Integer.valueOf(currentItem));
            }
        }
    }

    public void openGrid() {
        withActivity(new ActivityTask<ImageViewerActivity>() {
            public void performTask(ImageViewerActivity imageViewerActivity) {
                Intent intent = new Intent();
                intent.setClass(imageViewerActivity, PhotoVideoViewerActivity.class);
                IntentUtil.putParcelableArrayListExtra(intent, "ArgExtraMediaSources", ImageViewerFragment.this.mMediaSources);
                intent.putExtra("ArgExtraMediaLoadingType", ImageViewerFragment.this.mMediaLoadingType);
                intent.putExtra("ArgExtraNoMoreMediaSources", ImageViewerFragment.this.mNoMoreMediaSources);
                intent.putExtra("ArgExtraMediaSourcesNextOffset", ImageViewerFragment.this.mMediaSourcesNextOffset);
                intent.putExtra("ExtraStartIndex", 0);
                if (ImageViewerFragment.this.mProductId != null) {
                    intent.putExtra("ArgExtraProductId", ImageViewerFragment.this.mProductId);
                }
                imageViewerActivity.startActivityForResult(intent, imageViewerActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 == -1 && intent != null) {
                            ArrayList parcelableArrayListExtra = IntentUtil.getParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources");
                            ImageViewerFragment.this.mNoMoreMediaSources = intent.getBooleanExtra("ArgExtraNoMoreMediaSources", true);
                            ImageViewerFragment.this.mMediaSourcesNextOffset = intent.getIntExtra("ArgExtraMediaSourcesNextOffset", 0);
                            if (parcelableArrayListExtra != null) {
                                ImageViewerFragment.this.mMediaSources = parcelableArrayListExtra;
                                ImageViewerFragment.this.mAdapter.notifyDataSetChanged();
                            }
                            ImageViewerFragment.this.closeActivity();
                        }
                    }
                }));
            }
        });
    }

    public boolean onBackPressed() {
        closeActivity();
        return true;
    }

    public void closeActivity() {
        withActivity(new ActivityTask<ImageViewerActivity>() {
            public void performTask(ImageViewerActivity imageViewerActivity) {
                Intent intent = new Intent();
                if (imageViewerActivity.wasOpenedFromGrid()) {
                    IntentUtil.putParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources", ImageViewerFragment.this.mMediaSources);
                } else {
                    IntentUtil.putParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources", ImageViewerFragment.this.mMediaSources);
                }
                intent.putExtra("ArgExtraMediaLoadingType", ImageViewerFragment.this.mMediaLoadingType);
                intent.putExtra("ArgExtraNoMoreMediaSources", ImageViewerFragment.this.mNoMoreMediaSources);
                intent.putExtra("ArgExtraMediaSourcesNextOffset", ImageViewerFragment.this.mMediaSourcesNextOffset);
                imageViewerActivity.setResult(-1, intent);
                imageViewerActivity.finishActivity();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.cleanup();
        }
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        super.handleResume();
        updateToolbarTitle(this.mViewPager.getCurrentItem());
        this.mAdapter.handlePageSelected(this.mViewPager.getCurrentItem());
        refreshUploader();
    }

    /* access modifiers changed from: private */
    public void updateToolbarTitle(int i) {
        if (!((ImageViewerActivity) getBaseActivity()).isShowingSingleImage()) {
            if (this.mImageUrl != null) {
                this.mToolbar.setTitle((CharSequence) getString(R.string.image_count_title, Integer.valueOf(1), Integer.valueOf(1)));
            } else if (this.mMediaSources != null && this.mMediaSources.size() > 0) {
                this.mToolbar.setTitle((CharSequence) getString(R.string.image_count_title, Integer.valueOf(i + 1), Integer.valueOf(this.mMediaSources.size())));
            }
            this.mToolbar.setNavigationOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ImageViewerFragment.this.onBackPressed();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void refreshUploader() {
        int currentItem = this.mViewPager.getCurrentItem();
        if (this.mMediaSources != null && this.mMediaSources.size() > currentItem) {
            WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) this.mMediaSources.get(currentItem);
            if (!(wishProductExtraImage == null || wishProductExtraImage.getUploader() == null || wishProductExtraImage.getUploader().getName() == null)) {
                setupUploaderLayout(wishProductExtraImage, currentItem);
                return;
            }
        }
        hideUploaderLayout();
    }

    private void setupUploaderLayout(WishProductExtraImage wishProductExtraImage, int i) {
        this.mDetailsContainer.setVisibility(0);
        if (!TextUtils.isEmpty(wishProductExtraImage.getComment())) {
            this.mUploaderCommentContainer.setVisibility(0);
            this.mUploaderComment.setText(wishProductExtraImage.getComment());
            formatCommentSection(wishProductExtraImage);
            setupOnCommentClickListener(wishProductExtraImage);
        } else {
            this.mUploaderImageV2.setVisibility(0);
            this.mUploaderCommentContainer.setVisibility(8);
            this.mUploaderContainer.setVisibility(0);
            this.mUploaderImageV2.setup(wishProductExtraImage.getUploader().getProfileImage(), wishProductExtraImage.getUploader().getName());
            this.mUploaderName.setText(wishProductExtraImage.getUploader().getName());
            if (wishProductExtraImage.getSize() != null) {
                ThemedTextView themedTextView = this.mUploadDate;
                StringBuilder sb = new StringBuilder();
                sb.append(wishProductExtraImage.getFormattedTimestamp());
                sb.append(", ");
                sb.append(getString(R.string.sizes_detail_lowercase, wishProductExtraImage.getSize()));
                themedTextView.setText(sb.toString());
            } else {
                this.mUploadDate.setText(wishProductExtraImage.getFormattedTimestamp());
            }
        }
        if (wishProductExtraImage.getUploader().isWishStar()) {
            this.mUploaderName.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.four_padding));
            this.mUploaderName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.wishstar_badge_16), null);
        } else {
            this.mUploaderName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        if (((ImageViewerActivity) getBaseActivity()).allowUpvote()) {
            this.mDivider.setVisibility(0);
            this.mUploaderActions.setVisibility(0);
            setUpvoteButtonStyle(wishProductExtraImage.hasUserUpvoted());
            setupUpvoteOnClickListener(wishProductExtraImage, i);
            this.mUpvoteCount.setText(wishProductExtraImage.getUpvoteString());
        }
    }

    private void setupUpvoteOnClickListener(final WishProductExtraImage wishProductExtraImage, final int i) {
        AnonymousClass8 r0 = new OnClickListener() {
            public void onClick(View view) {
                int i;
                ImageViewerFragment.this.trackUserUpvoteEvent(wishProductExtraImage);
                if (wishProductExtraImage.hasUserUpvoted()) {
                    ImageViewerFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ImageViewerServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, ImageViewerServiceFragment imageViewerServiceFragment) {
                            imageViewerServiceFragment.removeProductRatingUpvote(wishProductExtraImage.getRatingId());
                        }
                    });
                    i = -1;
                } else {
                    ImageViewerFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ImageViewerServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, ImageViewerServiceFragment imageViewerServiceFragment) {
                            imageViewerServiceFragment.productRatingUpvote(wishProductExtraImage.getRatingId());
                        }
                    });
                    ImageViewerFragment.this.animateThanksButton();
                    i = 1;
                }
                if (ImageViewerFragment.this.mMediaSources != null && ImageViewerFragment.this.mMediaSources.size() > i) {
                    int userUpvoteCount = wishProductExtraImage.getUserUpvoteCount() + i;
                    boolean z = !wishProductExtraImage.hasUserUpvoted();
                    Iterator it = ImageViewerFragment.this.mMediaSources.iterator();
                    while (it.hasNext()) {
                        WishProductExtraImage wishProductExtraImage = (WishProductExtraImage) it.next();
                        if (wishProductExtraImage.getRatingId() != null && wishProductExtraImage.getRatingId().equals(wishProductExtraImage.getRatingId())) {
                            wishProductExtraImage.setUpvoteCount(userUpvoteCount);
                            wishProductExtraImage.setHasUserUpvoted(z);
                        }
                    }
                    ImageViewerFragment.this.mUpvoteCount.setText(wishProductExtraImage.getUpvoteString());
                    ImageViewerFragment.this.setUpvoteButtonStyle(z);
                }
            }
        };
        this.mUpvoteIcon.setOnClickListener(r0);
        this.mUpvoteText.setOnClickListener(r0);
    }

    /* access modifiers changed from: private */
    public void trackUserUpvoteEvent(WishProductExtraImage wishProductExtraImage) {
        HashMap hashMap = new HashMap();
        hashMap.put("rating_id", wishProductExtraImage.getRatingId());
        if (wishProductExtraImage.getSourceType() == SourceType.Video) {
            if (!wishProductExtraImage.hasUserUpvoted()) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_VIEWER_VIDEO_UPVOTE, this.mProductId, hashMap);
            } else {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_VIEWER_UNDO_VIDEO_UPVOTE, this.mProductId, hashMap);
            }
        } else if (!wishProductExtraImage.hasUserUpvoted()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_VIEWER_IMAGE_UPVOTE, this.mProductId, hashMap);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_VIEWER_UNDO_IMAGE_UPVOTE, this.mProductId, hashMap);
        }
    }

    private void setupOnCommentClickListener(final WishProductExtraImage wishProductExtraImage) {
        this.mUploaderCommentContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImageViewerFragment.this.mUploaderComment.cycleExpandedTextView(view);
                ImageViewerFragment.this.formatCommentSection(wishProductExtraImage);
            }
        });
    }

    /* access modifiers changed from: private */
    public void animateThanksButton() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(50);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.1f, 1.0f, 1.1f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration(50);
        scaleAnimation2.setStartOffset(50);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(scaleAnimation2);
        this.mUpvoteText.startAnimation(animationSet);
    }

    /* access modifiers changed from: private */
    public void formatCommentSection(WishProductExtraImage wishProductExtraImage) {
        this.mUploaderImageV2.setVisibility(0);
        this.mUploaderImageV2.setup(wishProductExtraImage.getUploader().getProfileImage(), wishProductExtraImage.getUploader().getName());
        this.mUploaderName.setText(wishProductExtraImage.getUploader().getName());
        this.mDetailsContainer.setBackgroundResource(R.drawable.photo_video_viewer_uploader_section_gradient);
        this.mUploaderContainer.setVisibility(0);
        if (this.mUploaderComment.isTrimmable()) {
            this.mUploaderCommentExpansionButton.setVisibility(0);
            this.mUploaderCommentExpansionButton.setText(this.mUploaderComment.isTrimmed() ? R.string.more : R.string.less);
        } else {
            this.mUploaderCommentExpansionButton.setVisibility(8);
        }
        if (wishProductExtraImage.getSize() != null) {
            ThemedTextView themedTextView = this.mUploadDate;
            StringBuilder sb = new StringBuilder();
            sb.append(wishProductExtraImage.getFormattedTimestamp());
            sb.append(", ");
            sb.append(getString(R.string.sizes_detail_lowercase, wishProductExtraImage.getSize()));
            themedTextView.setText(sb.toString());
            return;
        }
        this.mUploadDate.setText(wishProductExtraImage.getFormattedTimestamp());
    }

    /* access modifiers changed from: private */
    public void setUpvoteButtonStyle(boolean z) {
        if (z) {
            this.mUpvoteIcon.setImageResource(R.drawable.like_btn);
            this.mUpvoteText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            return;
        }
        this.mUpvoteIcon.setImageResource(R.drawable.thanks_icon);
        this.mUpvoteText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
    }

    private void hideUploaderLayout() {
        this.mUploaderName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        this.mDetailsContainer.setVisibility(8);
    }

    public ArrayList<WishProductExtraImage> getMediaSources() {
        return this.mMediaSources;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }
}
