package com.contextlogic.wish.activity.imageviewer.photovideoviewer;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class PhotoVideoViewerActivity extends FullScreenActivity {
    public final boolean canHaveActionBar() {
        return false;
    }

    public boolean requiresAuthentication() {
        return false;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new PhotoVideoViewerFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ServiceFragment();
    }

    public int getMediaLoadingType() {
        return getIntent().getIntExtra("ArgExtraMediaLoadingType", 0);
    }

    public ArrayList<WishProductExtraImage> getMediaSources() {
        return IntentUtil.getParcelableArrayListExtra(getIntent(), "ArgExtraMediaSources");
    }

    public boolean getNoMoreMediaSources() {
        return getIntent().getBooleanExtra("ArgExtraNoMoreMediaSources", true);
    }

    public int getMediaSourcesNextOffset() {
        return getIntent().getIntExtra("ArgExtraMediaSourcesNextOffset", 0);
    }

    public String getProductId() {
        return getIntent().getStringExtra("ArgExtraProductId");
    }

    public int getStartIndex() {
        return getIntent().getIntExtra("ArgExtraStartIndex", 0);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.PHOTO_VIDEO_VIEWER;
    }

    public int getBackgroundColor() {
        return getResources().getColor(R.color.photo_video_viewer_background);
    }

    public String getActionBarTitle() {
        return getString(R.string.media);
    }
}
