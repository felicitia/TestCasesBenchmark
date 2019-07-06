package com.contextlogic.wish.activity.feed.tag;

import android.view.View;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment.DataMode;

public class TagFeedFragment extends ProductFeedFragment {
    private String mTagId;

    public boolean canShowFeedCategories() {
        return false;
    }

    public boolean isFeedFilterable() {
        return false;
    }

    public void initializeLoadingContentView(View view) {
        super.initializeLoadingContentView(view);
        this.mTagId = ((TagFeedActivity) getBaseActivity()).getTagId();
    }

    public DataMode getDataMode() {
        return DataMode.Tag;
    }

    /* access modifiers changed from: protected */
    public String getMainRequestId() {
        return this.mTagId;
    }
}
