package com.etsy.android.stylekit.alerts;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.stylekit.a.c;
import com.etsy.android.stylekit.a.d;
import com.etsy.android.stylekit.a.e;
import com.etsy.android.stylekit.a.f;
import com.etsy.android.stylekit.a.g;
import com.etsy.android.stylekit.a.j;

public class AlertLayout extends LinearLayout {
    private float anchorCornerRadius;
    int anchorDirection = 2;
    private float anchorHeight;
    private float anchorOffset;
    private float anchorWidth;
    AlertBackgroundDrawable backgroundDrawable;
    FrameLayout content;
    boolean didLoadDefaultLayout = false;
    ImageView dismissView;
    ImageView iconView;
    TextView messageView;

    public AlertLayout(Context context) {
        super(context);
        init(context, null);
    }

    public AlertLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AlertLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    @TargetApi(21)
    public AlertLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    public void init(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        int i = 0;
        setOrientation(0);
        setGravity(16);
        LayoutInflater.from(context).inflate(g.sk_alert, this, true);
        this.iconView = (ImageView) findViewById(f.alert_icon);
        this.messageView = (TextView) findViewById(f.alert_text);
        this.content = (FrameLayout) findViewById(f.alert_content);
        this.dismissView = (ImageView) findViewById(f.alert_dismiss);
        this.didLoadDefaultLayout = true;
        this.anchorCornerRadius = (float) getResources().getDimensionPixelSize(d.atom_alert_bg_corner_radius);
        this.anchorHeight = (float) getResources().getDimensionPixelSize(d.atom_alert_anchor_height);
        this.anchorWidth = (float) getResources().getDimensionPixelSize(d.atom_alert_anchor_width);
        this.anchorOffset = (float) getResources().getDimensionPixelSize(d.atom_alert_anchor_offset);
        Drawable drawable = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.sk_AlertLayout);
            if (obtainStyledAttributes.hasValue(j.sk_AlertLayout_sk_alert_bgColor)) {
                this.anchorDirection = obtainStyledAttributes.getInt(j.sk_AlertLayout_sk_alert_anchorDirection, 2);
                AlertBackgroundDrawable alertBackgroundDrawable = new AlertBackgroundDrawable(obtainStyledAttributes.getColor(j.sk_AlertLayout_sk_alert_bgColor, context.getResources().getColor(c.sk_secondary_one_year_old_gouda)), this.anchorCornerRadius, this.anchorWidth, this.anchorHeight, this.anchorOffset, this.anchorDirection);
                this.backgroundDrawable = alertBackgroundDrawable;
                setBackground(this.backgroundDrawable);
                setAnchorDirection(this.anchorDirection);
            }
            if (obtainStyledAttributes.hasValue(j.sk_AlertLayout_sk_alert_icon)) {
                int resourceId = obtainStyledAttributes.getResourceId(j.sk_AlertLayout_sk_alert_icon, -1);
                if (resourceId != -1) {
                    drawable = AppCompatResources.getDrawable(context, resourceId).mutate();
                }
            }
            if (obtainStyledAttributes.hasValue(j.sk_AlertLayout_sk_alert_iconTint) && drawable != null) {
                int color = obtainStyledAttributes.getColor(j.sk_AlertLayout_sk_alert_iconTint, context.getResources().getColor(c.sk_orange_20));
                Drawable wrap = DrawableCompat.wrap(drawable);
                DrawableCompat.setTint(drawable, color);
                drawable = wrap;
            }
            if (obtainStyledAttributes.hasValue(j.sk_AlertLayout_sk_alert_textColor)) {
                int color2 = obtainStyledAttributes.getColor(j.sk_AlertLayout_sk_alert_textColor, context.getResources().getColor(c.sk_secondary_one_year_old_gouda));
                this.messageView.setTextColor(color2);
                Drawable wrap2 = DrawableCompat.wrap(AppCompatResources.getDrawable(getContext(), e.sk_ic_close));
                DrawableCompat.setTint(wrap2.mutate(), color2);
                this.dismissView.setImageDrawable(wrap2);
            }
            if (obtainStyledAttributes.hasValue(j.sk_AlertLayout_sk_alert_messageText)) {
                this.messageView.setText(obtainStyledAttributes.getString(j.sk_AlertLayout_sk_alert_messageText));
            }
            if (obtainStyledAttributes.hasValue(j.sk_AlertLayout_sk_alert_showDismiss)) {
                ImageView imageView = this.dismissView;
                if (!obtainStyledAttributes.getBoolean(j.sk_AlertLayout_sk_alert_showDismiss, false)) {
                    i = 8;
                }
                imageView.setVisibility(i);
            }
            obtainStyledAttributes.recycle();
        }
        this.iconView.setImageDrawable(drawable);
    }

    public void addView(View view) {
        if (this.didLoadDefaultLayout) {
            this.content.addView(view);
            this.messageView.setPadding(this.messageView.getPaddingLeft(), getResources().getDimensionPixelSize(d.sk_space_4), this.messageView.getPaddingRight(), this.messageView.getPaddingBottom());
            return;
        }
        super.addView(view);
    }

    public void addView(View view, int i) {
        if (this.didLoadDefaultLayout) {
            this.content.addView(view, i);
            this.messageView.setPadding(this.messageView.getPaddingLeft(), getResources().getDimensionPixelSize(d.sk_space_4), this.messageView.getPaddingRight(), this.messageView.getPaddingBottom());
            return;
        }
        super.addView(view, i);
    }

    public void addView(View view, int i, int i2) {
        if (this.didLoadDefaultLayout) {
            this.content.addView(view, i, i2);
            this.messageView.setPadding(this.messageView.getPaddingLeft(), getResources().getDimensionPixelSize(d.sk_space_4), this.messageView.getPaddingRight(), this.messageView.getPaddingBottom());
            return;
        }
        super.addView(view, i, i2);
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (this.didLoadDefaultLayout) {
            this.content.addView(view, layoutParams);
            this.messageView.setPadding(this.messageView.getPaddingLeft(), getResources().getDimensionPixelSize(d.sk_space_4), this.messageView.getPaddingRight(), this.messageView.getPaddingBottom());
            return;
        }
        super.addView(view, layoutParams);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (this.didLoadDefaultLayout) {
            this.content.addView(view, i, layoutParams);
            this.messageView.setPadding(this.messageView.getPaddingLeft(), getResources().getDimensionPixelSize(d.sk_space_4), this.messageView.getPaddingRight(), this.messageView.getPaddingBottom());
            return;
        }
        super.addView(view, i, layoutParams);
    }

    public View setContent(@LayoutRes int i) {
        return setContent(LayoutInflater.from(getContext()).inflate(i, this.content, false));
    }

    public View setContent(@NonNull View view) {
        this.content.removeAllViews();
        addView(view);
        return view;
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    public void setMessageText(CharSequence charSequence) {
        this.messageView.setText(charSequence);
    }

    public void setIcon(@DrawableRes int i) {
        this.iconView.setImageDrawable(AppCompatResources.getDrawable(getContext(), i).mutate());
    }

    public void setIcon(@DrawableRes int i, @ColorRes int i2) {
        Drawable mutate = AppCompatResources.getDrawable(getContext(), i).mutate();
        DrawableCompat.setTint(mutate, ColorStateList.valueOf(getResources().getColor(i2)).getColorForState(getDrawableState(), getResources().getColor(c.sk_gray_50)));
        this.iconView.setImageDrawable(mutate);
    }

    public void setDismissListener(@NonNull OnClickListener onClickListener) {
        this.dismissView.setOnClickListener(onClickListener);
    }

    private void setAnchorDirection(@IntRange(from = 0, to = 2) int i) {
        if (this.backgroundDrawable != null) {
            this.anchorDirection = i;
            this.backgroundDrawable.setAnchorDirection(i);
            switch (i) {
                case 0:
                    setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
                    return;
                case 1:
                    setPadding(getPaddingLeft(), getPaddingTop() + ((int) this.anchorHeight), getPaddingRight(), getPaddingBottom());
                    return;
                default:
                    setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + ((int) this.anchorHeight));
                    return;
            }
        }
    }

    private int getAnchorDirection() {
        return this.anchorDirection;
    }
}
