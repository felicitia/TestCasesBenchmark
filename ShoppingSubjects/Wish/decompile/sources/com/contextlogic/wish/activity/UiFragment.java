package com.contextlogic.wish.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.viewpager.BaseTabStripInterface;
import com.contextlogic.wish.util.ViewUtil;

public abstract class UiFragment<A extends BaseActivity> extends BaseFragment<A> implements ImageRestorable {
    private boolean mImagesReleased;
    private FrameLayout mRootLayout;

    /* access modifiers changed from: protected */
    public boolean canReleaseImagesOnStop() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutResourceId();

    public boolean handleActionBarItemSelected(int i) {
        return false;
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onKeyboardVisiblityChanged(boolean z) {
    }

    public abstract void releaseImages();

    public abstract void restoreImages();

    /* access modifiers changed from: protected */
    public boolean shouldDelayInflate() {
        return false;
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (shouldDelayInflate()) {
            this.mRootLayout = new FrameLayout(getActivity());
            return this.mRootLayout;
        }
        if (!(!hasTabStrip() || getBaseActivity() == null || getBaseActivity().getActionBarManager() == null)) {
            getBaseActivity().getActionBarManager().hideDivider();
        }
        return layoutInflater.inflate(getLayoutResourceId(), viewGroup, false);
    }

    public final void onResume() {
        super.onResume();
    }

    public final void onAuthenticationVerifiedResume() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                UiFragment.this.restoreImagesIfNeeded();
                UiFragment.this.handleResume();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.getActionBarManager().updateCartIcon(false);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void setupBaseActionBar() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.getActionBarManager().clearRightActionBarItems();
                a.getActionBarManager().addDefaultActionBarItems();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void removeCartItemInActionBar() {
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                a.getActionBarManager().clearCartInActionBarItems();
            }
        });
    }

    public void onStop() {
        super.onStop();
        if (canReleaseImagesOnStop()) {
            releaseImagesIfNeeded();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        releaseImagesIfNeeded();
        ViewUtil.unbindDrawables(getView());
    }

    /* access modifiers changed from: protected */
    public void restoreImagesIfNeeded() {
        if (this.mImagesReleased) {
            autoRestoreImages();
            restoreImages();
            this.mImagesReleased = false;
        }
    }

    private void autoRestoreImages() {
        if (getView() != null) {
            ViewUtil.restoreReleasableImageViews(getView());
        }
    }

    /* access modifiers changed from: protected */
    public void releaseImagesIfNeeded() {
        if (!this.mImagesReleased) {
            autoReleaseImages();
            releaseImages();
            this.mImagesReleased = true;
        }
    }

    private void autoReleaseImages() {
        if (getView() != null) {
            ViewUtil.releaseReleasableImageViews(getView());
        }
    }

    public void initializeIfNeccessary() {
        if (shouldDelayInflate() && this.mRootLayout.getChildCount() == 0) {
            ((LayoutInflater) getBaseActivity().getSystemService("layout_inflater")).inflate(getLayoutResourceId(), this.mRootLayout, true);
        }
        super.initializeIfNeccessary();
    }

    /* access modifiers changed from: protected */
    public <T extends View> T findViewById(int i) {
        return getView().findViewById(i);
    }

    private boolean hasTabStrip() {
        return this instanceof BaseTabStripInterface;
    }
}
