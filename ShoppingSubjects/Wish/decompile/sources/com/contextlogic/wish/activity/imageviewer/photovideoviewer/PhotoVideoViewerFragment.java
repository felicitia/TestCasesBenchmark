package com.contextlogic.wish.activity.imageviewer.photovideoviewer;

import android.content.Intent;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.imageviewer.ImageViewerActivity;
import com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerAdapter.OnViewVisibleListener;
import com.contextlogic.wish.activity.imageviewer.photovideoviewer.PhotoVideoViewerPagerViewItem.ImageClickListener;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.api.model.WishProductExtraImage.SourceType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.loading.PrimaryProgressBar;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class PhotoVideoViewerFragment extends UiFragment<PhotoVideoViewerActivity> implements OnViewVisibleListener, ImageClickListener {
    private PrimaryProgressBar mLoadingView;
    /* access modifiers changed from: private */
    public int mMediaLoadingType;
    /* access modifiers changed from: private */
    public ArrayList<WishProductExtraImage> mMediaSources;
    /* access modifiers changed from: private */
    public int mMediaSourcesNextOffset;
    /* access modifiers changed from: private */
    public boolean mNoMoreMediaSources;
    protected PhotoVideoViewerPagerAdapter mPagerAdapter;
    protected String mProductId;
    /* access modifiers changed from: private */
    public PagerSlidingTabStrip mTabStrip;
    private Toolbar mToolbar;
    private boolean mUpdatingMediaSources;
    private SafeViewPager mViewPager;

    private boolean isMediaSourcesRequestPending() {
        return false;
    }

    public int getLayoutResourceId() {
        return R.layout.image_viewer_photo_summary;
    }

    public void initialize() {
        initMediaSources();
        setResult();
        this.mToolbar = (Toolbar) findViewById(R.id.photo_video_viewer_fragment_toolbar);
        this.mToolbar.setNavigationIcon((int) R.drawable.action_bar_back);
        this.mToolbar.setTitle((CharSequence) WishApplication.getInstance().getResources().getString(R.string.media));
        this.mToolbar.setTitleTextColor(WishApplication.getInstance().getResources().getColor(17170443));
        this.mToolbar.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.photo_video_viewer_nav_bar));
        this.mToolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PhotoVideoViewerFragment.this.withActivity(new ActivityTask<PhotoVideoViewerActivity>() {
                    public void performTask(PhotoVideoViewerActivity photoVideoViewerActivity) {
                        Intent intent = new Intent();
                        if (PhotoVideoViewerFragment.this.mMediaSources != null) {
                            IntentUtil.putParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources", PhotoVideoViewerFragment.this.mMediaSources);
                            photoVideoViewerActivity.setResult(-1, intent);
                        }
                        photoVideoViewerActivity.finishActivity();
                    }
                });
            }
        });
        this.mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.photo_video_viewer_fragment_tab_strip);
        this.mTabStrip.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.photo_video_viewer_nav_bar));
        this.mViewPager = (SafeViewPager) findViewById(R.id.photo_video_viewer_fragment_view_pager);
        this.mPagerAdapter = new PhotoVideoViewerPagerAdapter(getContext(), this);
        this.mPagerAdapter.setAllExtraImages(this.mMediaSources);
        this.mPagerAdapter.setOnViewVisibleListener(this);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mTabStrip.setViewPager(this.mViewPager);
        int startIndex = ((PhotoVideoViewerActivity) getBaseActivity()).getStartIndex();
        this.mViewPager.setCurrentItem(startIndex);
        this.mPagerAdapter.trackPageChange(startIndex, false, this.mProductId);
        customizeTabStrip();
        this.mTabStrip.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                PhotoVideoViewerFragment.this.handlePageChanged();
                PhotoVideoViewerFragment.this.mPagerAdapter.trackPageChange(i, true, PhotoVideoViewerFragment.this.mProductId);
            }
        });
        refreshTabFontColors();
        if (this.mPagerAdapter.getExtraVideosSize() == 0 || this.mPagerAdapter.getExtraImagesSize() == 0) {
            this.mTabStrip.setEnabled(false);
            this.mTabStrip.setVisibility(8);
            this.mViewPager.disableViewPager();
        }
        this.mLoadingView = (PrimaryProgressBar) findViewById(R.id.photo_video_viewer_loading_view);
        this.mLoadingView.setColorFilter(R.color.white);
    }

    /* access modifiers changed from: private */
    public void handlePageChanged() {
        refreshTabFontColors();
    }

    private void refreshTabFontColors() {
        LinearLayout linearLayout = (LinearLayout) this.mTabStrip.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            if (i == this.mViewPager.getCurrentItem()) {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
                textView.setTypeface(null, 1);
            } else {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.tab_unselected_white));
                textView.setTypeface(null, 0);
            }
        }
    }

    private void customizeTabStrip() {
        withActivity(new ActivityTask<PhotoVideoViewerActivity>() {
            public void performTask(PhotoVideoViewerActivity photoVideoViewerActivity) {
                int dimensionPixelOffset = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height);
                int dimensionPixelOffset2 = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.tab_strip_text_size);
                PhotoVideoViewerFragment.this.mTabStrip.setAlignJustify(true);
                PhotoVideoViewerFragment.this.mTabStrip.setBackgroundResource(R.color.photo_video_viewer_nav_bar);
                PhotoVideoViewerFragment.this.mTabStrip.setIndicatorColorResource(R.color.white);
                PhotoVideoViewerFragment.this.mTabStrip.setTextColorResource(R.color.white);
                PhotoVideoViewerFragment.this.mTabStrip.setUnderlineHeight(0);
                PhotoVideoViewerFragment.this.mTabStrip.setIndicatorHeight(dimensionPixelOffset);
                PhotoVideoViewerFragment.this.mTabStrip.setTextSize(dimensionPixelOffset2);
            }
        });
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

    public void onImageClick(final int i) {
        if (this.mMediaSources != null && i < this.mMediaSources.size()) {
            HashMap hashMap = null;
            if (((WishProductExtraImage) this.mMediaSources.get(i)).getRatingId() != null) {
                hashMap = new HashMap();
                hashMap.put("rating_id", ((WishProductExtraImage) this.mMediaSources.get(i)).getRatingId());
            }
            if (((WishProductExtraImage) this.mMediaSources.get(i)).getSourceType() == SourceType.Video) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_GRID_VIDEO, this.mProductId, hashMap);
            } else {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PHOTO_VIDEO_GRID_PHOTO, this.mProductId, hashMap);
            }
            withActivity(new ActivityTask<PhotoVideoViewerActivity>() {
                public void performTask(PhotoVideoViewerActivity photoVideoViewerActivity) {
                    Intent intent = new Intent();
                    intent.setClass(photoVideoViewerActivity, ImageViewerActivity.class);
                    IntentUtil.putParcelableArrayListExtra(intent, "ExtraMediaSources", PhotoVideoViewerFragment.this.mMediaSources);
                    intent.putExtra("ExtraStartIndex", i);
                    intent.putExtra("ExtraOpenedFromGrid", true);
                    intent.putExtra("ExtraProductId", PhotoVideoViewerFragment.this.mProductId);
                    intent.putExtra("ArgExtraMediaLoadingType", PhotoVideoViewerFragment.this.mMediaLoadingType);
                    intent.putExtra("ArgExtraNoMoreMediaSources", PhotoVideoViewerFragment.this.mNoMoreMediaSources);
                    intent.putExtra("ArgExtraMediaSourcesNextOffset", PhotoVideoViewerFragment.this.mMediaSourcesNextOffset);
                    photoVideoViewerActivity.startActivityForResult(intent, photoVideoViewerActivity.addResultCodeCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            if (i2 == -1) {
                                ArrayList parcelableArrayListExtra = IntentUtil.getParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources");
                                if (parcelableArrayListExtra != null) {
                                    PhotoVideoViewerFragment.this.mMediaSources = parcelableArrayListExtra;
                                }
                            }
                        }
                    }));
                }
            });
        }
    }

    public void initMediaSources() {
        withActivity(new ActivityTask<PhotoVideoViewerActivity>() {
            public void performTask(PhotoVideoViewerActivity photoVideoViewerActivity) {
                PhotoVideoViewerFragment.this.mMediaSources = photoVideoViewerActivity.getMediaSources();
                PhotoVideoViewerFragment.this.mProductId = photoVideoViewerActivity.getProductId();
                PhotoVideoViewerFragment.this.mMediaLoadingType = photoVideoViewerActivity.getMediaLoadingType();
                PhotoVideoViewerFragment.this.mNoMoreMediaSources = photoVideoViewerActivity.getNoMoreMediaSources();
                PhotoVideoViewerFragment.this.mMediaSourcesNextOffset = photoVideoViewerActivity.getMediaSourcesNextOffset();
                if (PhotoVideoViewerFragment.this.mMediaSources == null) {
                    PhotoVideoViewerFragment.this.mMediaSources = new ArrayList();
                }
            }
        });
    }

    public void onViewVisible(int i, int i2, View view) {
        if ((!this.mNoMoreMediaSources && !isMediaSourcesRequestPending() && !this.mUpdatingMediaSources) && i2 > this.mMediaSources.size() - 5) {
            loadNextPageMediaSources();
        }
    }

    /* access modifiers changed from: protected */
    public void loadNextPageMediaSources() {
        int i = this.mMediaLoadingType;
    }

    public void setResult() {
        withActivity(new ActivityTask<PhotoVideoViewerActivity>() {
            public void performTask(PhotoVideoViewerActivity photoVideoViewerActivity) {
                Intent intent = new Intent();
                IntentUtil.putParcelableArrayListExtra(intent, "ArgExtraUpdatedMediaSources", PhotoVideoViewerFragment.this.mMediaSources);
                intent.putExtra("ArgExtraMediaLoadingType", PhotoVideoViewerFragment.this.mMediaLoadingType);
                intent.putExtra("ArgExtraNoMoreMediaSources", PhotoVideoViewerFragment.this.mNoMoreMediaSources);
                intent.putExtra("ArgExtraMediaSourcesNextOffset", PhotoVideoViewerFragment.this.mMediaSourcesNextOffset);
                photoVideoViewerActivity.setResult(-1, intent);
            }
        });
    }

    public boolean onBackPressed() {
        setResult();
        withActivity(new ActivityTask<PhotoVideoViewerActivity>() {
            public void performTask(PhotoVideoViewerActivity photoVideoViewerActivity) {
                photoVideoViewerActivity.finishActivity();
            }
        });
        return true;
    }
}
