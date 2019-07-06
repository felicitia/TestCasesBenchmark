package com.etsy.android.uikit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.Behavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v13.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.util.k;
import com.etsy.android.stylekit.e;
import com.etsy.android.uikit.behavior.EtsyAppBarBehavior;
import com.etsy.android.uikit.util.HardwareAnimationListener;

public class AppBarHelper implements OnItemSelectedListener {
    private ActionBar mAb;
    private AppBarLayout mAppBarLayout;
    private Spinner mNavSpinner;
    private a mNavigationListener;
    private TabLayout mTabLayout;
    private int mTabMode = 0;
    private Toolbar mToolbar;

    public static class AddToAppBarAnimation extends Animation {
        /* access modifiers changed from: private */
        public boolean mAddingView;
        private final int mAppBarHeight1x = this.mAppBarLayout.getContext().getResources().getDimensionPixelSize(f.actionbar_height);
        /* access modifiers changed from: private */
        public final ViewGroup mAppBarLayout;
        private int mInitialAppBarHeight = this.mAppBarLayout.getMeasuredHeight();
        private final int mInitialViewHeight;
        /* access modifiers changed from: private */
        public View mView;

        public boolean willChangeBounds() {
            return true;
        }

        public static AddToAppBarAnimation showView(View view, ViewGroup viewGroup) {
            if (viewGroup.getAnimation() instanceof AddToAppBarAnimation) {
                View view2 = ((AddToAppBarAnimation) viewGroup.getAnimation()).mView;
                view.getLayoutParams().height = view2.getLayoutParams().height;
                AddToAppBarAnimation addToAppBarAnimation = new AddToAppBarAnimation(view, viewGroup, true);
                viewGroup.removeView(view2);
                return addToAppBarAnimation;
            }
            view.getLayoutParams().height = 0;
            view.requestLayout();
            return new AddToAppBarAnimation(view, viewGroup, true);
        }

        public static AddToAppBarAnimation removeView(View view, ViewGroup viewGroup) {
            return new AddToAppBarAnimation(view, viewGroup, false);
        }

        @TargetApi(21)
        private AddToAppBarAnimation(View view, ViewGroup viewGroup, boolean z) {
            this.mAddingView = z;
            this.mView = view;
            this.mAppBarLayout = viewGroup;
            this.mInitialViewHeight = view.getLayoutParams().height;
            setDuration(300);
            if (this.mAddingView) {
                view.getLayoutParams().height = 0;
                view.requestLayout();
                this.mAppBarLayout.addView(this.mView);
            }
            setAnimationListener(new HardwareAnimationListener(this.mAppBarLayout) {
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    if (!AddToAppBarAnimation.this.mAddingView) {
                        AddToAppBarAnimation.this.mAppBarLayout.removeView(AddToAppBarAnimation.this.mView);
                    }
                }
            });
            if (k.c()) {
                setInterpolator(new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f));
            }
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            int i;
            int i2 = -2;
            if (!this.mAddingView) {
                i = (int) (((float) this.mInitialViewHeight) * (1.0f - f));
                LayoutParams layoutParams = this.mAppBarLayout.getLayoutParams();
                if (f != 1.0f) {
                    i2 = (this.mInitialAppBarHeight - this.mInitialViewHeight) + i;
                }
                layoutParams.height = i2;
            } else {
                i = (int) ((((float) this.mAppBarHeight1x) * f) + (((float) this.mInitialViewHeight) * (1.0f - f)));
                this.mAppBarLayout.getLayoutParams().height = -2;
            }
            this.mView.getLayoutParams().height = i;
            this.mAppBarLayout.requestLayout();
        }
    }

    public interface a {
        boolean a(int i, long j);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    AppBarHelper() {
    }

    /* access modifiers changed from: 0000 */
    public void init(AppCompatActivity appCompatActivity) {
        if (appCompatActivity != null) {
            this.mAppBarLayout = (AppBarLayout) appCompatActivity.findViewById(i.app_bar_layout);
            this.mToolbar = (Toolbar) appCompatActivity.findViewById(i.app_bar_toolbar);
            this.mTabLayout = (TabLayout) appCompatActivity.findViewById(i.app_bar_tablayout);
            if (this.mAppBarLayout == null || this.mToolbar == null) {
                boolean z = false;
                boolean z2 = this.mAppBarLayout == null;
                if (this.mToolbar == null) {
                    z = true;
                }
                if (z2 != z) {
                    com.etsy.android.lib.logger.f.a((RuntimeException) new IllegalStateException("Activities using app bar must include both AppBarLayout and Toolbar (include app_bar.xml"));
                } else if (this.mAb == null) {
                    this.mAb = appCompatActivity.getSupportActionBar();
                    setupDefaults();
                }
            } else {
                appCompatActivity.setSupportActionBar(this.mToolbar);
                this.mAb = appCompatActivity.getSupportActionBar();
                setupDefaults();
            }
        }
    }

    private void setupDefaults() {
        if (this.mAb != null) {
            this.mAb.setDisplayUseLogoEnabled(false);
            this.mAb.setDisplayHomeAsUpEnabled(true);
            this.mAb.setDisplayShowHomeEnabled(true);
            this.mAb.setDisplayShowCustomEnabled(false);
            setTitle((CharSequence) "");
        }
    }

    public void showCustomView(View view, ActionBar.LayoutParams layoutParams) {
        if (this.mAb != null) {
            this.mAb.setDisplayShowCustomEnabled(true);
            if (layoutParams != null) {
                this.mAb.setCustomView(view, layoutParams);
            } else {
                this.mAb.setCustomView(view);
            }
        }
    }

    public void showCustomView(View view) {
        showCustomView(view, null);
    }

    @Nullable
    @TargetApi(21)
    public FrameLayout addExtension(boolean z) {
        if (this.mAppBarLayout == null) {
            return null;
        }
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.mAppBarLayout.getContext()).inflate(com.etsy.android.lib.a.k.app_bar_extension, this.mAppBarLayout, false);
        addView(frameLayout, z);
        return frameLayout;
    }

    public void removeExtension(View view, boolean z) {
        removeView(view, z);
    }

    @Nullable
    public TabLayout addTabLayout() {
        if (this.mAppBarLayout == null) {
            return null;
        }
        if (this.mTabLayout != null) {
            this.mTabLayout.removeAllTabs();
            this.mTabLayout.setTabGravity(0);
            this.mTabLayout.clearOnTabSelectedListeners();
            return this.mTabLayout;
        }
        this.mTabLayout = (TabLayout) LayoutInflater.from(this.mAppBarLayout.getContext()).inflate(com.etsy.android.lib.a.k.toolbar_tabs_layout, this.mAppBarLayout, false);
        this.mTabLayout.setTabGravity(0);
        this.mTabLayout.setTabMode(this.mTabMode);
        addView(this.mTabLayout, ViewCompat.isLaidOut(this.mAppBarLayout));
        ((AppBarLayout.LayoutParams) this.mAppBarLayout.findViewById(i.app_bar_toolbar).getLayoutParams()).setScrollFlags(5);
        return this.mTabLayout;
    }

    @Nullable
    public TabLayout getTabLayout() {
        return this.mTabLayout;
    }

    public void setTabMode(int i) {
        this.mTabMode = i;
    }

    public void removeTabLayout() {
        if (this.mTabLayout != null) {
            this.mTabLayout.clearOnTabSelectedListeners();
            removeView(this.mTabLayout, true);
            this.mTabLayout = null;
        }
        ((AppBarLayout.LayoutParams) this.mAppBarLayout.findViewById(i.app_bar_toolbar).getLayoutParams()).setScrollFlags(0);
    }

    private void addView(View view, boolean z) {
        if (this.mAppBarLayout == null) {
            return;
        }
        if (z) {
            this.mAppBarLayout.startAnimation(AddToAppBarAnimation.showView(view, this.mAppBarLayout));
        } else {
            this.mAppBarLayout.addView(view);
        }
    }

    private void removeView(View view, boolean z) {
        if (this.mAppBarLayout != null && this.mAppBarLayout.indexOfChild(view) >= 0) {
            if (z) {
                this.mAppBarLayout.startAnimation(AddToAppBarAnimation.removeView(view, this.mAppBarLayout));
            } else {
                this.mAppBarLayout.removeView(view);
            }
        }
    }

    public void setIcon(int i) {
        if (this.mToolbar != null) {
            this.mToolbar.setLogo(i);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.mToolbar != null) {
            this.mToolbar.setLogo(drawable);
        }
    }

    public void setNavigationIcon(int i) {
        if (this.mToolbar != null) {
            this.mToolbar.setNavigationIcon(i);
        } else if (this.mAb != null) {
            this.mAb.setHomeAsUpIndicator(i);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (this.mToolbar != null) {
            this.mToolbar.setNavigationIcon(drawable);
        } else if (this.mAb != null) {
            this.mAb.setHomeAsUpIndicator(drawable);
        }
    }

    public void setCustomTitleView(int i) {
        if (this.mToolbar != null) {
            setTitle((CharSequence) null);
            LayoutInflater.from(this.mToolbar.getContext()).inflate(i, this.mToolbar);
        }
    }

    public void setCustomTitleView(View view) {
        if (this.mToolbar != null) {
            setTitle((CharSequence) null);
            this.mToolbar.addView(view, 0);
        }
    }

    public void setTitle(CharSequence charSequence) {
        setTitle(charSequence, (CharSequence) null);
    }

    public void setTitle(int i) {
        setTitle((CharSequence) getThemedContext().getString(i));
    }

    public void setTitle(CharSequence charSequence, int i) {
        setTitle(charSequence, (CharSequence) getThemedContext().getString(i));
    }

    public void setTitle(int i, int i2) {
        setTitle((CharSequence) getThemedContext().getString(i), (CharSequence) getThemedContext().getString(i2));
    }

    public void setTitle(int i, CharSequence charSequence) {
        setTitle((CharSequence) getThemedContext().getString(i), charSequence);
    }

    public void setTitle(CharSequence charSequence, CharSequence charSequence2) {
        if (this.mAb != null) {
            this.mAb.setDisplayShowTitleEnabled(true);
            if (charSequence == null) {
                this.mAb.setTitle((CharSequence) "");
                return;
            }
            SpannableString spannableString = new SpannableString(charSequence.toString());
            spannableString.setSpan(e.a(getThemedContext(), o.sk_typeface_bold), 0, charSequence.length(), 0);
            this.mAb.setTitle((CharSequence) spannableString);
            this.mAb.setSubtitle(charSequence2);
        }
    }

    public void hideTitle() {
        if (this.mAb != null) {
            this.mAb.setDisplayShowTitleEnabled(false);
        }
    }

    public void setHomeAsUpEnabled(boolean z) {
        if (this.mAb != null) {
            this.mAb.setDisplayHomeAsUpEnabled(z);
            this.mAb.setDisplayShowHomeEnabled(z);
            this.mAb.setHomeButtonEnabled(z);
        }
    }

    public void setupListNavigation(SpinnerAdapter spinnerAdapter, a aVar) {
        if (this.mAb != null) {
            hideTitle();
            createSpinnerInAppBar(spinnerAdapter);
            this.mNavigationListener = aVar;
        }
    }

    public void removeListNavigation() {
        if (this.mAb != null) {
            if (this.mNavSpinner != null) {
                this.mNavSpinner.setVisibility(8);
            }
            this.mNavigationListener = null;
        }
    }

    private void createSpinnerInAppBar(SpinnerAdapter spinnerAdapter) {
        this.mNavSpinner = (Spinner) ((LayoutInflater) getThemedContext().getSystemService("layout_inflater")).inflate(com.etsy.android.lib.a.k.app_bar_spinner, null);
        this.mNavSpinner.setAdapter(spinnerAdapter);
        this.mNavSpinner.setOnItemSelectedListener(this);
        showCustomView(this.mNavSpinner);
    }

    public void setSelectedNavigationItem(int i) {
        if (this.mAb != null && this.mNavSpinner != null) {
            this.mNavSpinner.setSelection(i);
        }
    }

    public Context getThemedContext() {
        if (this.mAb != null) {
            return this.mAb.getThemedContext();
        }
        return null;
    }

    public void hideDivider() {
        if (this.mAb != null) {
            this.mAb.setBackgroundDrawable(new ColorDrawable(getThemedContext().getResources().getColor(com.etsy.android.lib.a.e.sk_bg_white)));
        }
    }

    public void showAppBar() {
        if (this.mAb != null) {
            this.mAb.show();
        }
        if (this.mAppBarLayout != null) {
            this.mAppBarLayout.setVisibility(0);
        }
    }

    public void hideAppBar() {
        if (this.mAb != null) {
            this.mAb.hide();
        }
        if (this.mAppBarLayout != null) {
            this.mAppBarLayout.setVisibility(8);
        }
    }

    public static void showAppBar(AppCompatActivity appCompatActivity) {
        if (appCompatActivity != null) {
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.show();
            }
        }
    }

    @Deprecated
    public static void hideAppBar(AppCompatActivity appCompatActivity) {
        if (appCompatActivity != null) {
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.hide();
            }
        }
    }

    public ActionBar.LayoutParams buildLayoutParams(int i, int i2) {
        return new ActionBar.LayoutParams(i, i2);
    }

    public ActionBar.LayoutParams buildLayoutParams(int i, int i2, int i3) {
        return new ActionBar.LayoutParams(i, i2, i3);
    }

    public void addExtraUpPadding(Activity activity) {
        if (this.mAb != null && this.mAb.getNavigationMode() != 1 && this.mAb.getCustomView() == null) {
            ImageView imageView = (ImageView) activity.findViewById(16908332);
            if (imageView == null) {
                imageView = (ImageView) activity.findViewById(i.home);
            }
            if (imageView != null) {
                imageView.setPadding(imageView.getPaddingLeft(), imageView.getPaddingTop(), imageView.getPaddingRight() + activity.getResources().getDimensionPixelSize(f.actionbar_dropdown_offset), imageView.getPaddingBottom());
            }
        }
    }

    public void setToolbarScrollFlags(int i) {
        if (this.mToolbar != null) {
            LayoutParams layoutParams = this.mToolbar.getLayoutParams();
            if (layoutParams instanceof AppBarLayout.LayoutParams) {
                ((AppBarLayout.LayoutParams) layoutParams).setScrollFlags(i);
            }
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mNavigationListener != null) {
            this.mNavigationListener.a(i, j);
        }
    }

    public void resetPosition(CoordinatorLayout coordinatorLayout) {
        if (this.mAppBarLayout != null) {
            Behavior behavior = (Behavior) ((CoordinatorLayout.LayoutParams) this.mAppBarLayout.getLayoutParams()).getBehavior();
            if (behavior instanceof EtsyAppBarBehavior) {
                behavior.onNestedFling(coordinatorLayout, this.mAppBarLayout, null, 0.0f, (float) (-this.mAppBarLayout.getHeight()), true);
            }
        }
    }
}
