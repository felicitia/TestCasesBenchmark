package com.contextlogic.wish.activity.ugcfeedback;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class UgcFeedbackCounterElementView extends FrameLayout {
    private ThemedTextView mCountTextView;
    private int mTextColor;

    public UgcFeedbackCounterElementView(Context context) {
        this(context, null);
    }

    public UgcFeedbackCounterElementView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTextColor = -1;
        init();
    }

    private void init() {
        this.mCountTextView = (ThemedTextView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.ugc_counter_element_view, this).findViewById(R.id.ugc_feedback_counter_element_text);
        if (this.mTextColor != -1) {
            this.mCountTextView.setTextColor(this.mTextColor);
        }
    }

    public void setCount(String str) {
        this.mCountTextView.setText(str);
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
        if (this.mCountTextView != null) {
            this.mCountTextView.setTextColor(i);
        }
    }
}
