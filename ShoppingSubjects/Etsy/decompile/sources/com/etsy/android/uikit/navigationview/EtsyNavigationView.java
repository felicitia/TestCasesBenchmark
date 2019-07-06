package com.etsy.android.uikit.navigationview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import com.etsy.android.lib.a.c;
import com.etsy.android.lib.a.p;
import com.etsy.android.lib.a.q;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@SuppressLint({"RestrictedApi"})
public class EtsyNavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    /* access modifiers changed from: private */
    public a mListener;
    private int mMaxWidth;
    private final MenuBuilder mMenu;
    private MenuInflater mMenuInflater;
    private final EtsyNavigationMenuPresenter mPresenter;

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public Bundle menuState;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.menuState = parcel.readBundle();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }
    }

    public interface a {
        boolean a(MenuItem menuItem);
    }

    public EtsyNavigationView(Context context) {
        this(context, null);
    }

    public EtsyNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EtsyNavigationView(Context context, AttributeSet attributeSet, int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        super(context, attributeSet, i);
        this.mMenu = new MenuBuilder(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.NavigationView, i, p.Widget_Design_NavigationView);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(q.NavigationView_android_background));
        if (obtainStyledAttributes.hasValue(q.NavigationView_elevation)) {
            ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(q.NavigationView_elevation, 0));
        }
        ViewCompat.setFitsSystemWindows(this, obtainStyledAttributes.getBoolean(q.NavigationView_android_fitsSystemWindows, false));
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(q.NavigationView_android_maxWidth, 0);
        if (obtainStyledAttributes.hasValue(q.NavigationView_itemIconTint)) {
            colorStateList = obtainStyledAttributes.getColorStateList(q.NavigationView_itemIconTint);
        } else {
            colorStateList = createDefaultColorStateList(16842808);
        }
        if (obtainStyledAttributes.hasValue(q.NavigationView_itemTextColor)) {
            colorStateList2 = obtainStyledAttributes.getColorStateList(q.NavigationView_itemTextColor);
        } else {
            colorStateList2 = createDefaultColorStateList(16842806);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(q.NavigationView_itemBackground);
        if (obtainStyledAttributes.hasValue(q.NavigationView_menu)) {
            inflateMenu(obtainStyledAttributes.getResourceId(q.NavigationView_menu, 0));
        }
        this.mMenu.setCallback(new Callback() {
            public void onMenuModeChange(MenuBuilder menuBuilder) {
            }

            public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                return EtsyNavigationView.this.mListener != null && EtsyNavigationView.this.mListener.a(menuItem);
            }
        });
        this.mPresenter = new EtsyNavigationMenuPresenter();
        this.mPresenter.setId(1);
        this.mPresenter.initForMenu(context, this.mMenu);
        this.mPresenter.setItemIconTintList(colorStateList);
        this.mPresenter.setItemTextColor(colorStateList2);
        this.mPresenter.setItemBackground(drawable);
        this.mMenu.addMenuPresenter(this.mPresenter);
        addView((View) this.mPresenter.getMenuView(this));
        if (obtainStyledAttributes.hasValue(q.NavigationView_headerLayout)) {
            inflateHeaderView(obtainStyledAttributes.getResourceId(q.NavigationView_headerLayout, 0));
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuState = new Bundle();
        this.mMenu.savePresenterStates(savedState.menuState);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mMenu.restorePresenterStates(savedState.menuState);
    }

    public void setNavigationItemSelectedListener(a aVar) {
        this.mListener = aVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(i), this.mMaxWidth), ErrorDialogData.SUPPRESSED);
        } else if (mode == 0) {
            i = MeasureSpec.makeMeasureSpec(this.mMaxWidth, ErrorDialogData.SUPPRESSED);
        }
        super.onMeasure(i, i2);
    }

    public void inflateMenu(int i) {
        getMenuInflater().inflate(i, this.mMenu);
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public View inflateHeaderView(@LayoutRes int i) {
        return this.mPresenter.inflateHeaderView(i);
    }

    public void addHeaderView(@NonNull View view) {
        this.mPresenter.addHeaderView(view);
    }

    public void removeHeaderView(@NonNull View view) {
        this.mPresenter.removeHeaderView(view);
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.mPresenter.getItemTintList();
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.mPresenter.setItemIconTintList(colorStateList);
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.mPresenter.getItemTextColor();
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.mPresenter.setItemTextColor(colorStateList);
    }

    public Drawable getItemBackground() {
        return this.mPresenter.getItemBackground();
    }

    public void setItemBackgroundResource(@DrawableRes int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemBackground(Drawable drawable) {
        this.mPresenter.setItemBackground(drawable);
    }

    private MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(getContext());
        }
        return this.mMenuInflater;
    }

    private ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = getResources().getColorStateList(typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(c.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        return new ColorStateList(new int[][]{DISABLED_STATE_SET, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(DISABLED_STATE_SET, defaultColor), i2, defaultColor});
    }
}
