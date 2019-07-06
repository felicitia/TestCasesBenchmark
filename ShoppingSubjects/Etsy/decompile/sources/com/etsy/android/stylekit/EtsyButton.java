package com.etsy.android.stylekit;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatButton;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import com.etsy.android.stylekit.a.c;
import com.etsy.android.stylekit.a.d;
import com.etsy.android.stylekit.a.j;

public class EtsyButton extends AppCompatButton {
    private static final int ICON_LEFT = 0;
    private static final int ICON_RIGHT = 1;
    /* access modifiers changed from: private */
    public boolean mDetached;
    private Drawable mIcon;
    Rect mIconBounds = new Rect();
    private float mIconHeight;
    private int mIconLocation;
    private float mIconPadding;
    private ColorStateList mIconTint;
    private float mIconWidth;
    private float mSpaceBetweenIconAndText;

    public EtsyButton(Context context) {
        super(context);
        init(context, null);
    }

    public EtsyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public EtsyButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.EtsyButton);
        if (obtainStyledAttributes.hasValue(j.EtsyButton_sk_btnIcon)) {
            this.mIcon = AppCompatResources.getDrawable(context, obtainStyledAttributes.getResourceId(obtainStyledAttributes.getIndex(j.EtsyButton_sk_btnIcon), 0)).mutate();
            registerDrawable(this.mIcon);
        }
        if (obtainStyledAttributes.hasValue(j.EtsyButton_sk_btnIconWidth) || obtainStyledAttributes.hasValue(j.EtsyButton_sk_btnIconHeight)) {
            this.mIconWidth = (float) obtainStyledAttributes.getDimensionPixelSize(j.EtsyButton_sk_btnIconWidth, getResources().getDimensionPixelSize(d.atom_btn_icon_size));
            this.mIconHeight = (float) obtainStyledAttributes.getDimensionPixelSize(j.EtsyButton_sk_btnIconHeight, getResources().getDimensionPixelSize(d.atom_btn_icon_size));
        } else {
            float dimensionPixelSize = (float) obtainStyledAttributes.getDimensionPixelSize(j.EtsyButton_sk_btnIconSize, getResources().getDimensionPixelSize(d.atom_btn_icon_size));
            this.mIconHeight = dimensionPixelSize;
            this.mIconWidth = dimensionPixelSize;
        }
        if (obtainStyledAttributes.hasValue(j.EtsyButton_sk_btnIconTint)) {
            this.mIconTint = obtainStyledAttributes.getColorStateList(j.EtsyButton_sk_btnIconTint);
        } else {
            this.mIconTint = null;
        }
        this.mIconPadding = (float) obtainStyledAttributes.getDimensionPixelSize(j.EtsyButton_sk_btnIconPadding, 0);
        this.mIconLocation = obtainStyledAttributes.getInt(j.EtsyButton_sk_btnIconLocation, 0);
        this.mSpaceBetweenIconAndText = (float) obtainStyledAttributes.getDimensionPixelSize(j.EtsyButton_sk_btnIconTextSpacing, getResources().getDimensionPixelSize(d.sk_space_1));
        obtainStyledAttributes.recycle();
        if (getTag() != null) {
            try {
                if ("secondary".equals((String) getTag())) {
                    setTextWithUnderline(getText());
                }
            } catch (ClassCastException unused) {
                Log.w(EtsyButton.class.getName(), "Wasn't able to set underline. Tag wasn't a String");
            }
        }
    }

    public void setTextWithUnderline(@StringRes int i) {
        setTextWithUnderline((CharSequence) getResources().getString(i));
    }

    public void setTextWithUnderline(CharSequence charSequence) {
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        setText(spannableString);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mIcon != null) {
            this.mIcon.setState(getDrawableState());
            if (this.mIconTint != null) {
                DrawableCompat.setTint(this.mIcon, this.mIconTint.getColorForState(getDrawableState(), getResources().getColor(c.sk_gray_50)));
            }
            this.mIcon.invalidateSelf();
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mIcon != null) {
            setMeasuredDimension((int) Math.max((float) getMeasuredWidth(), this.mIconWidth), (int) Math.max((float) getMeasuredHeight(), this.mIconHeight));
        }
    }

    public int getCompoundPaddingLeft() {
        if (this.mIcon == null || getText() == null || getText().length() <= 0 || !isIconLeft()) {
            return super.getCompoundPaddingLeft();
        }
        return (int) (((float) super.getCompoundPaddingLeft()) + this.mIconWidth + this.mSpaceBetweenIconAndText);
    }

    public int getCompoundPaddingRight() {
        if (this.mIcon == null || getText() == null || getText().length() <= 0 || !isIconRight()) {
            return super.getCompoundPaddingRight();
        }
        return (int) (((float) super.getCompoundPaddingRight()) + this.mIconWidth + this.mSpaceBetweenIconAndText);
    }

    public int getTotalPaddingLeft() {
        if (this.mIcon == null || getText() == null || getText().length() <= 0 || !isIconLeft()) {
            return super.getTotalPaddingLeft();
        }
        return (int) (((float) super.getTotalPaddingLeft()) + this.mIconWidth + this.mSpaceBetweenIconAndText);
    }

    public int getTotalPaddingRight() {
        if (this.mIcon == null || getText() == null || getText().length() <= 0 || !isIconRight()) {
            return super.getTotalPaddingRight();
        }
        return (int) (((float) super.getTotalPaddingRight()) + this.mIconWidth + this.mSpaceBetweenIconAndText);
    }

    public int getPaddingLeft() {
        if (this.mIcon == null || getText() == null || getText().length() <= 0 || !isIconLeft()) {
            return super.getPaddingLeft();
        }
        return (int) (((float) super.getPaddingLeft()) + this.mIconWidth + this.mSpaceBetweenIconAndText);
    }

    public int getPaddingRight() {
        if (this.mIcon == null || getText() == null || getText().length() <= 0 || !isIconRight()) {
            return super.getPaddingRight();
        }
        return (int) (((float) super.getPaddingRight()) + this.mIconWidth + this.mSpaceBetweenIconAndText);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mIcon != null) {
            float measureText = getPaint().measureText(getText().toString());
            if (measureText >= ((float) ((getWidth() - getPaddingLeft()) - getPaddingRight()))) {
                measureText = (float) ((getWidth() - getPaddingLeft()) - getPaddingRight());
            }
            float width = (((float) getWidth()) - ((this.mIconWidth + measureText) + ((getText() == null || getText().length() <= 0) ? 0.0f : this.mSpaceBetweenIconAndText))) / 2.0f;
            if (isIconRight()) {
                width += measureText + this.mSpaceBetweenIconAndText;
            }
            float height = ((float) (getHeight() / 2)) - (this.mIconHeight / 2.0f);
            this.mIconBounds.left = (int) (this.mIconPadding + width);
            this.mIconBounds.top = (int) (this.mIconPadding + height);
            this.mIconBounds.right = (int) ((width + this.mIconWidth) - this.mIconPadding);
            this.mIconBounds.bottom = (int) ((height + this.mIconHeight) - this.mIconPadding);
            this.mIcon.setBounds(this.mIconBounds);
            this.mIcon.draw(canvas);
        }
    }

    private void drawRedlines(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(SupportMenu.CATEGORY_MASK);
        Paint paint2 = paint;
        canvas.drawLine(0.0f, (float) (getHeight() / 2), (float) getWidth(), (float) (getHeight() / 2), paint2);
        canvas.drawLine((float) (getWidth() / 2), 0.0f, (float) (getWidth() / 2), (float) getHeight(), paint2);
        int paddingTop = getPaddingTop();
        int paddingRight = isIconLeft() ? getPaddingRight() : getPaddingLeft();
        float f = (float) paddingRight;
        Paint paint3 = paint;
        canvas.drawLine(f, 0.0f, f, (float) getHeight(), paint3);
        canvas.drawLine((float) (getWidth() - paddingRight), 0.0f, (float) (getWidth() - paddingRight), (float) getHeight(), paint3);
        float f2 = (float) paddingTop;
        canvas.drawLine(0.0f, f2, (float) getWidth(), f2, paint3);
        canvas.drawLine(0.0f, (float) (getHeight() - paddingTop), (float) getWidth(), (float) (getHeight() - paddingTop), paint3);
    }

    public void setIcon(@DrawableRes int i) {
        setIcon(AppCompatResources.getDrawable(getContext(), i).mutate());
    }

    public void setIcon(@NonNull Drawable drawable) {
        this.mIcon = drawable;
        registerDrawable(drawable);
    }

    public void clearIcon() {
        this.mIcon = null;
    }

    public void setIconPadding(@DimenRes int i) {
        this.mIconPadding = (float) getResources().getDimensionPixelSize(i);
    }

    public void setIconSize(@DimenRes int i) {
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(i);
        this.mIconHeight = dimensionPixelSize;
        this.mIconWidth = dimensionPixelSize;
    }

    public void setIconTint(@ColorInt int i) {
        setIconTint(ColorStateList.valueOf(i));
    }

    public void setIconTint(ColorStateList colorStateList) {
        this.mIconTint = colorStateList;
    }

    public void clearIconTint() {
        this.mIconTint = null;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDetached = true;
    }

    private void registerDrawable(@NonNull Drawable drawable) {
        drawable.setCallback(new Callback() {
            public void invalidateDrawable(@NonNull Drawable drawable) {
                EtsyButton.this.invalidate();
            }

            public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
                if (!EtsyButton.this.mDetached) {
                    EtsyButton.this.getHandler().postDelayed(runnable, j);
                }
            }

            public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
                if (!EtsyButton.this.mDetached) {
                    EtsyButton.this.getHandler().removeCallbacks(runnable);
                }
            }
        });
    }

    private boolean isIconLeft() {
        return this.mIconLocation == 0;
    }

    private boolean isIconRight() {
        return this.mIconLocation == 1;
    }
}
