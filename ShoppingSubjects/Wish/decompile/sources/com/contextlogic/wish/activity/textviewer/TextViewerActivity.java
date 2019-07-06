package com.contextlogic.wish.activity.textviewer;

import android.os.Bundle;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class TextViewerActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
    }

    public String getActionBarTitle() {
        return getIntent().getStringExtra("ExtraTitle");
    }

    /* access modifiers changed from: protected */
    public String getContextText() {
        return getIntent().getStringExtra("ExtraContent");
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new TextViewerFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.TEXT_VIEWER;
    }
}
