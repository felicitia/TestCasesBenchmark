package com.akexorcist.roundcornerprogressbar.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.BaseSavedState;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.akexorcist.roundcornerprogressbar.R;

public abstract class BaseRoundCornerProgressBar extends LinearLayout {
    private int colorBackground;
    private int colorProgress;
    private int colorSecondaryProgress;
    private boolean isReverse;
    private LinearLayout layoutBackground;
    private LinearLayout layoutProgress;
    private LinearLayout layoutSecondaryProgress;
    private float max;
    private int padding;
    private float progress;
    private OnProgressChangedListener progressChangedListener;
    private int radius;
    private float secondaryProgress;
    private int totalWidth;

    public interface OnProgressChangedListener {
        void onProgressChanged(int i, float f, boolean z, boolean z2);
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int colorBackground;
        int colorProgress;
        int colorSecondaryProgress;
        boolean isReverse;
        float max;
        int padding;
        float progress;
        int radius;
        float secondaryProgress;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.max = parcel.readFloat();
            this.progress = parcel.readFloat();
            this.secondaryProgress = parcel.readFloat();
            this.radius = parcel.readInt();
            this.padding = parcel.readInt();
            this.colorBackground = parcel.readInt();
            this.colorProgress = parcel.readInt();
            this.colorSecondaryProgress = parcel.readInt();
            this.isReverse = parcel.readByte() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.max);
            parcel.writeFloat(this.progress);
            parcel.writeFloat(this.secondaryProgress);
            parcel.writeInt(this.radius);
            parcel.writeInt(this.padding);
            parcel.writeInt(this.colorBackground);
            parcel.writeInt(this.colorProgress);
            parcel.writeInt(this.colorSecondaryProgress);
            parcel.writeByte(this.isReverse ? (byte) 1 : 0);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void drawProgress(LinearLayout linearLayout, float f, float f2, float f3, int i, int i2, int i3, boolean z);

    /* access modifiers changed from: protected */
    public abstract int initLayout();

    /* access modifiers changed from: protected */
    public abstract void initStyleable(Context context, AttributeSet attributeSet);

    /* access modifiers changed from: protected */
    public abstract void initView();

    /* access modifiers changed from: protected */
    public abstract void onViewDraw();

    public BaseRoundCornerProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (isInEditMode()) {
            previewLayout(context);
        } else {
            setup(context, attributeSet);
        }
    }

    @TargetApi(11)
    public BaseRoundCornerProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (isInEditMode()) {
            previewLayout(context);
        } else {
            setup(context, attributeSet);
        }
    }

    private void previewLayout(Context context) {
        setGravity(17);
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(-1, -1));
        textView.setGravity(17);
        textView.setText(getClass().getSimpleName());
        textView.setTextColor(-1);
        textView.setBackgroundColor(-7829368);
        addView(textView);
    }

    public void setup(Context context, AttributeSet attributeSet) {
        setupStyleable(context, attributeSet);
        removeAllViews();
        LayoutInflater.from(context).inflate(initLayout(), this);
        this.layoutBackground = (LinearLayout) findViewById(R.id.layout_background);
        this.layoutProgress = (LinearLayout) findViewById(R.id.layout_progress);
        this.layoutSecondaryProgress = (LinearLayout) findViewById(R.id.layout_secondary_progress);
        initView();
    }

    public void setupStyleable(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundCornerProgress);
        this.radius = (int) obtainStyledAttributes.getDimension(R.styleable.RoundCornerProgress_rcRadius, dp2px(30.0f));
        this.padding = (int) obtainStyledAttributes.getDimension(R.styleable.RoundCornerProgress_rcBackgroundPadding, dp2px(0.0f));
        this.isReverse = obtainStyledAttributes.getBoolean(R.styleable.RoundCornerProgress_rcReverse, false);
        this.max = obtainStyledAttributes.getFloat(R.styleable.RoundCornerProgress_rcMax, 100.0f);
        this.progress = obtainStyledAttributes.getFloat(R.styleable.RoundCornerProgress_rcProgress, 0.0f);
        this.secondaryProgress = obtainStyledAttributes.getFloat(R.styleable.RoundCornerProgress_rcSecondaryProgress, 0.0f);
        this.colorBackground = obtainStyledAttributes.getColor(R.styleable.RoundCornerProgress_rcBackgroundColor, context.getResources().getColor(R.color.round_corner_progress_bar_background_default));
        this.colorProgress = obtainStyledAttributes.getColor(R.styleable.RoundCornerProgress_rcProgressColor, context.getResources().getColor(R.color.round_corner_progress_bar_progress_default));
        this.colorSecondaryProgress = obtainStyledAttributes.getColor(R.styleable.RoundCornerProgress_rcSecondaryProgressColor, context.getResources().getColor(R.color.round_corner_progress_bar_secondary_progress_default));
        obtainStyledAttributes.recycle();
        initStyleable(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!isInEditMode()) {
            this.totalWidth = i;
            drawAll();
            postDelayed(new Runnable() {
                public void run() {
                    BaseRoundCornerProgressBar.this.drawPrimaryProgress();
                    BaseRoundCornerProgressBar.this.drawSecondaryProgress();
                }
            }, 5);
        }
    }

    /* access modifiers changed from: protected */
    public void drawAll() {
        drawBackgroundProgress();
        drawPadding();
        drawProgressReverse();
        drawPrimaryProgress();
        drawSecondaryProgress();
        onViewDraw();
    }

    private void drawBackgroundProgress() {
        GradientDrawable createGradientDrawable = createGradientDrawable(this.colorBackground);
        float f = (float) (this.radius - (this.padding / 2));
        createGradientDrawable.setCornerRadii(new float[]{f, f, f, f, f, f, f, f});
        if (VERSION.SDK_INT >= 16) {
            this.layoutBackground.setBackground(createGradientDrawable);
        } else {
            this.layoutBackground.setBackgroundDrawable(createGradientDrawable);
        }
    }

    /* access modifiers changed from: protected */
    public GradientDrawable createGradientDrawable(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i);
        return gradientDrawable;
    }

    /* access modifiers changed from: private */
    public void drawPrimaryProgress() {
        drawProgress(this.layoutProgress, this.max, this.progress, (float) this.totalWidth, this.radius, this.padding, this.colorProgress, this.isReverse);
    }

    /* access modifiers changed from: private */
    public void drawSecondaryProgress() {
        drawProgress(this.layoutSecondaryProgress, this.max, this.secondaryProgress, (float) this.totalWidth, this.radius, this.padding, this.colorSecondaryProgress, this.isReverse);
    }

    private void drawProgressReverse() {
        setupReverse(this.layoutProgress);
        setupReverse(this.layoutSecondaryProgress);
    }

    private void setupReverse(LinearLayout linearLayout) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        removeLayoutParamsRule(layoutParams);
        if (this.isReverse) {
            layoutParams.addRule(11);
            if (VERSION.SDK_INT >= 17) {
                layoutParams.addRule(21);
            }
        } else {
            layoutParams.addRule(9);
            if (VERSION.SDK_INT >= 17) {
                layoutParams.addRule(20);
            }
        }
        linearLayout.setLayoutParams(layoutParams);
    }

    private void drawPadding() {
        this.layoutBackground.setPadding(this.padding, this.padding, this.padding, this.padding);
    }

    private void removeLayoutParamsRule(RelativeLayout.LayoutParams layoutParams) {
        if (VERSION.SDK_INT >= 17) {
            layoutParams.removeRule(11);
            layoutParams.removeRule(21);
            layoutParams.removeRule(9);
            layoutParams.removeRule(20);
            return;
        }
        layoutParams.addRule(11, 0);
        layoutParams.addRule(9, 0);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public float dp2px(float f) {
        return (float) Math.round(f * ((float) (getContext().getResources().getDisplayMetrics().densityDpi / 160)));
    }

    public void setReverse(boolean z) {
        this.isReverse = z;
        drawProgressReverse();
        drawPrimaryProgress();
        drawSecondaryProgress();
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int i) {
        if (i >= 0) {
            this.radius = i;
        }
        drawBackgroundProgress();
        drawPrimaryProgress();
        drawSecondaryProgress();
    }

    public int getPadding() {
        return this.padding;
    }

    public void setPadding(int i) {
        if (i >= 0) {
            this.padding = i;
        }
        drawPadding();
        drawPrimaryProgress();
        drawSecondaryProgress();
    }

    public float getMax() {
        return this.max;
    }

    public void setMax(float f) {
        if (f >= 0.0f) {
            this.max = f;
        }
        if (this.progress > f) {
            this.progress = f;
        }
        drawPrimaryProgress();
        drawSecondaryProgress();
    }

    public float getLayoutWidth() {
        return (float) this.totalWidth;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float f) {
        if (f < 0.0f) {
            this.progress = 0.0f;
        } else if (f > this.max) {
            this.progress = this.max;
        } else {
            this.progress = f;
        }
        drawPrimaryProgress();
        if (this.progressChangedListener != null) {
            this.progressChangedListener.onProgressChanged(getId(), this.progress, true, false);
        }
    }

    public float getSecondaryProgressWidth() {
        if (this.layoutSecondaryProgress != null) {
            return (float) this.layoutSecondaryProgress.getWidth();
        }
        return 0.0f;
    }

    public float getSecondaryProgress() {
        return this.secondaryProgress;
    }

    public void setSecondaryProgress(float f) {
        if (f < 0.0f) {
            this.secondaryProgress = 0.0f;
        } else if (f > this.max) {
            this.secondaryProgress = this.max;
        } else {
            this.secondaryProgress = f;
        }
        drawSecondaryProgress();
        if (this.progressChangedListener != null) {
            this.progressChangedListener.onProgressChanged(getId(), this.secondaryProgress, false, true);
        }
    }

    public int getProgressBackgroundColor() {
        return this.colorBackground;
    }

    public void setProgressBackgroundColor(int i) {
        this.colorBackground = i;
        drawBackgroundProgress();
    }

    public int getProgressColor() {
        return this.colorProgress;
    }

    public void setProgressColor(int i) {
        this.colorProgress = i;
        drawPrimaryProgress();
    }

    public int getSecondaryProgressColor() {
        return this.colorSecondaryProgress;
    }

    public void setSecondaryProgressColor(int i) {
        this.colorSecondaryProgress = i;
        drawSecondaryProgress();
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.progressChangedListener = onProgressChangedListener;
    }

    public void invalidate() {
        super.invalidate();
        drawAll();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.radius = this.radius;
        savedState.padding = this.padding;
        savedState.colorBackground = this.colorBackground;
        savedState.colorProgress = this.colorProgress;
        savedState.colorSecondaryProgress = this.colorSecondaryProgress;
        savedState.max = this.max;
        savedState.progress = this.progress;
        savedState.secondaryProgress = this.secondaryProgress;
        savedState.isReverse = this.isReverse;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.radius = savedState.radius;
        this.padding = savedState.padding;
        this.colorBackground = savedState.colorBackground;
        this.colorProgress = savedState.colorProgress;
        this.colorSecondaryProgress = savedState.colorSecondaryProgress;
        this.max = savedState.max;
        this.progress = savedState.progress;
        this.secondaryProgress = savedState.secondaryProgress;
        this.isReverse = savedState.isReverse;
    }
}
