package com.etsy.android.uikit.share;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.i;

public class SocialShareView extends RelativeLayout {
    /* access modifiers changed from: private */
    public boolean mAllowDismiss;
    /* access modifiers changed from: private */
    public boolean mAnimating = false;
    private TextView mMessage;
    private int mPopupHeight;
    private View mSharePopupContainer;
    private e mToast;

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        String a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.a);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readString();
        }
    }

    public SocialShareView(Context context) {
        super(context);
    }

    public SocialShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SocialShareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mSharePopupContainer = findViewById(i.popup_container);
        this.mMessage = (TextView) findViewById(i.message);
    }

    /* access modifiers changed from: 0000 */
    public void setSocialShareToast(e eVar) {
        this.mToast = eVar;
    }

    /* access modifiers changed from: 0000 */
    public void setMessage(CharSequence charSequence) {
        this.mMessage.setText(charSequence);
    }

    /* access modifiers changed from: 0000 */
    public void setOnShareListener(OnClickListener onClickListener) {
        this.mSharePopupContainer.setOnClickListener(onClickListener);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mAllowDismiss) {
            this.mToast.c();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mPopupHeight = findViewById(i.popup_container).getMeasuredHeight();
        hide();
    }

    public void show() {
        setAlpha(1.0f);
        setTranslationY(0.0f);
        onViewShown();
    }

    public void hide() {
        setAlpha(0.0f);
        setTranslationY((float) this.mPopupHeight);
        this.mAllowDismiss = false;
    }

    public void animateIn(final long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            post(new Runnable() {
                public void run() {
                    SocialShareView.this.animate().cancel();
                    SocialShareView.this.animate().translationY(0.0f).alpha(1.0f).setDuration(j).setListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            SocialShareView.this.mAnimating = false;
                            SocialShareView.this.setVisibility(0);
                        }
                    });
                }
            });
            onViewShown();
        }
    }

    public void animateOut(long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            animate().cancel();
            animate().translationY((float) this.mPopupHeight).alpha(0.0f).setDuration(j).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    SocialShareView.this.mAnimating = false;
                    SocialShareView.this.setVisibility(8);
                }
            });
            this.mAllowDismiss = false;
        }
    }

    private void onViewShown() {
        postDelayed(new Runnable() {
            public void run() {
                SocialShareView.this.mAllowDismiss = true;
            }
        }, 2000);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.mMessage.getText().toString();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setMessage(savedState.a);
    }
}
