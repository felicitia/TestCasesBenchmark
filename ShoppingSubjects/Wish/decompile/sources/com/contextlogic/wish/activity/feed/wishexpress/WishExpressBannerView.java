package com.contextlogic.wish.activity.feed.wishexpress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class WishExpressBannerView extends BaseFeedHeaderView {
    private String mDefaultTitle;
    private ThemedTextView mSubtitle;
    private ThemedTextView mTitle;

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public WishExpressBannerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.wish_express_feed_banner, this);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.wish_express_banner_view_title);
        this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.wish_express_banner_view_subtitle);
        this.mDefaultTitle = getContext().getString(R.string.wish_express_banner_title);
    }

    public void setup(String str, boolean z) {
        if (str != null) {
            this.mTitle.setText(str);
        } else {
            this.mTitle.setText(this.mDefaultTitle);
        }
        if (z) {
            this.mSubtitle.setVisibility(0);
            this.mSubtitle.setText(R.string.wish_express_banner_subtitle);
            return;
        }
        this.mSubtitle.setVisibility(8);
    }
}
