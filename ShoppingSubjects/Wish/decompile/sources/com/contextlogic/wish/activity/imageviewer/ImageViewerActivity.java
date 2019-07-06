package com.contextlogic.wish.activity.imageviewer;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class ImageViewerActivity extends FullScreenActivity {
    public boolean requiresAuthentication() {
        return false;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ImageViewerFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ImageViewerServiceFragment();
    }

    public final boolean canHaveActionBar() {
        return wasOpenedFromGrid() || getMediaSources() != null;
    }

    public ArrayList<WishProductExtraImage> getMediaSources() {
        return IntentUtil.getParcelableArrayListExtra(getIntent(), "ExtraMediaSources");
    }

    public boolean getNoMoreMediaSources() {
        return getIntent().getBooleanExtra("ArgExtraNoMoreMediaSources", true);
    }

    public int getMediaLoadingType() {
        return getIntent().getIntExtra("ArgExtraMediaLoadingType", 0);
    }

    public int getMediaSourcesNextOffset() {
        return getIntent().getIntExtra("ArgExtraMediaSourcesNextOffset", 0);
    }

    public boolean wasOpenedFromGrid() {
        return getIntent().getBooleanExtra("ExtraOpenedFromGrid", false);
    }

    public String getProductId() {
        return getIntent().getStringExtra("ExtraProductId");
    }

    public int getStartIndex() {
        return getIntent().getIntExtra("ExtraStartIndex", 0);
    }

    public String getImageUrl() {
        return getIntent().getStringExtra("ExtraImageUrl");
    }

    public boolean isShowingSingleImage() {
        return getIntent().getBooleanExtra("ExtraShowSingleImage", false);
    }

    public boolean allowUpvote() {
        return getIntent().getBooleanExtra("ExtraAllowUpvote", true);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.IMAGE_VIEWER;
    }

    public WishAnalyticsEvent getWishAnalyticsPageViewType() {
        return WishAnalyticsEvent.IMPRESSION_MOBILE_PHOTO_ZOOMABLE_VIEWER;
    }

    public int getBackgroundColor() {
        return getResources().getColor(R.color.photo_video_viewer_background);
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
    }
}
