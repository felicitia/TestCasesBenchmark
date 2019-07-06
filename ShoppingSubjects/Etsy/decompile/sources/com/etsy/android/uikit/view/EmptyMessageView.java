package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class EmptyMessageView extends LinearLayout {
    private static final int MISSING_IMAGE_ID = 0;
    protected ImageView mImageView;
    protected Button mLinkButton;
    protected TextView mTextSubtitle;
    protected TextView mTextTitle;

    public EmptyMessageView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public EmptyMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public EmptyMessageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public EmptyMessageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, k.list_item_card_view_message, this);
        this.mTextTitle = (TextView) findViewById(i.txt_title);
        this.mTextSubtitle = (TextView) findViewById(i.txt_subtitle);
        this.mImageView = (ImageView) findViewById(i.image);
        this.mLinkButton = (Button) findViewById(i.btn_link);
    }

    public void setTitle(@StringRes int i) {
        setTitle(getResources().getString(i));
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTextTitle.setVisibility(8);
            return;
        }
        this.mTextTitle.setVisibility(0);
        this.mTextTitle.setText(str);
    }

    public void setSubtitle(@StringRes int i) {
        setSubtitle(getResources().getString(i));
    }

    public void setSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTextSubtitle.setVisibility(8);
            return;
        }
        this.mTextSubtitle.setVisibility(0);
        this.mTextSubtitle.setText(str);
    }

    public void setTryAgain() {
        this.mLinkButton.setText(o.try_again);
    }

    public void setImage(@DrawableRes int i) {
        if (ContextCompat.getDrawable(getContext(), i) != null) {
            this.mImageView.setVisibility(0);
            this.mImageView.setImageResource(i);
            return;
        }
        this.mImageView.setVisibility(8);
    }

    public void setButtonText(@StringRes int i) {
        setButtonText(getResources().getString(i));
    }

    public void setButtonText(String str) {
        this.mLinkButton.setText(str);
    }

    public void bind(MessageCard messageCard) {
        if (getDrawableForImageType(messageCard.getImageName()) != 0) {
            setImage(getDrawableForImageType(messageCard.getImageName()));
        }
        setTitle(messageCard.getTitle());
        setSubtitle(messageCard.getSubtitle());
        this.mLinkButton.setVisibility(8);
        if (messageCard.isTryAgain()) {
            setTryAgain();
        } else {
            setButtonText(messageCard.getLinkTitle());
        }
    }

    @DrawableRes
    public int getDrawableForImageType(@NonNull String str) {
        if ("empty_recommmendations".equals(str)) {
            str = "empty_recommendations";
        } else if ("empty_heart".equals(str)) {
            str = "empty_favorites";
        } else if ("face_chair".equals(str)) {
            return g.empty_activity;
        }
        return getResources().getIdentifier(str, "drawable", getContext().getPackageName());
    }

    public void setButtonClickListener(TrackingOnClickListener trackingOnClickListener) {
        this.mLinkButton.setOnClickListener(trackingOnClickListener);
        this.mLinkButton.setVisibility(trackingOnClickListener == null ? 8 : 0);
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
