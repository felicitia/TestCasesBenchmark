package com.etsy.android.uikit.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;

public class EtsySearchView extends FrameLayout {
    private SearchView mSearchView;

    static class SavedState extends BaseSavedState {
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
        int a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public EtsySearchView(Context context) {
        super(context);
        init();
    }

    public EtsySearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public EtsySearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public EtsySearchView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        inflate(getContext(), k.layout_search_view, this);
        setBackgroundResource(e.searchViewBackground);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EtsySearchView.this.showSearch(false);
            }
        });
        this.mSearchView = (SearchView) findViewById(i.internal_search_view);
        this.mSearchView.setOnCloseListener(new OnCloseListener() {
            public boolean onClose() {
                EtsySearchView.this.showSearch(false);
                return true;
            }
        });
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mSearchView.setOnQueryTextListener(onQueryTextListener);
    }

    public void setMenuItem(MenuItem menuItem) {
        menuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                EtsySearchView.this.showSearch(true);
                return true;
            }
        });
    }

    public boolean isShowing() {
        return getVisibility() == 0;
    }

    /* access modifiers changed from: private */
    public void showSearch(boolean z) {
        Animator animator;
        Animator ofFloat;
        if (z) {
            if (VERSION.SDK_INT >= 21) {
                animator = ViewAnimationUtils.createCircularReveal(this, getWidth(), 0, 0.0f, (float) Math.hypot((double) getWidth(), (double) getHeight()));
            } else {
                animator = ObjectAnimator.ofFloat(this, ALPHA, new float[]{0.0f, 1.0f});
            }
            this.mSearchView.setIconified(false);
            setVisibility(0);
        } else {
            this.mSearchView.setQuery("", false);
            if (VERSION.SDK_INT >= 21) {
                ofFloat = ViewAnimationUtils.createCircularReveal(this, getWidth(), 0, (float) Math.hypot((double) getWidth(), (double) getHeight()), 0.0f);
            } else {
                ofFloat = ObjectAnimator.ofFloat(this, ALPHA, new float[]{1.0f, 0.0f});
            }
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    EtsySearchView.this.setVisibility(4);
                }
            });
        }
        animator.start();
    }

    public void hide() {
        showSearch(false);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getVisibility();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setVisibility(savedState.a);
    }
}
