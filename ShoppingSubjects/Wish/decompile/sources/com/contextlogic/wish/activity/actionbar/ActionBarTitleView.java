package com.contextlogic.wish.activity.actionbar;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.FontUtil;

public class ActionBarTitleView extends LinearLayout {
    private Context mContext;
    private String mText;
    private ThemedTextView mTextView;
    LayoutParams mTextViewParams;

    public ActionBarTitleView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        setOrientation(0);
        this.mTextViewParams = new LayoutParams(-2, -1);
        this.mTextViewParams.gravity = 8388627;
        this.mTextView = new ThemedTextView(this.mContext);
        this.mTextView.setLayoutParams(this.mTextViewParams);
        this.mTextView.setSingleLine(true);
        this.mTextView.setTypeface(FontUtil.getTypefaceForStyle(1));
        this.mTextView.setGravity(8388627);
        this.mTextView.setEllipsize(TruncateAt.END);
        this.mTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        this.mTextView.setTextSize(0, WishApplication.getInstance().getResources().getDimension(R.dimen.action_bar_title_size));
        this.mTextView.setFontResizable(true);
        addView(this.mTextView);
    }

    public String getText() {
        return this.mText;
    }

    public void setText(String str) {
        this.mText = str;
        this.mTextView.setText(this.mText);
    }

    public void setTextColor(int i) {
        this.mTextView.setTextColor(i);
    }

    public void setFontResizable(boolean z) {
        this.mTextView.setFontResizable(z);
    }
}
