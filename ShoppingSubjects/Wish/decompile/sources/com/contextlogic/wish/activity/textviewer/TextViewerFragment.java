package com.contextlogic.wish.activity.textviewer;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class TextViewerFragment extends UiFragment<TextViewerActivity> {
    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.text_viewer_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        withActivity(new ActivityTask<TextViewerActivity>() {
            public void performTask(TextViewerActivity textViewerActivity) {
                ((ThemedTextView) TextViewerFragment.this.findViewById(R.id.text_viewer_fragment_content_text)).setText(textViewerActivity.getContextText());
            }
        });
    }
}
