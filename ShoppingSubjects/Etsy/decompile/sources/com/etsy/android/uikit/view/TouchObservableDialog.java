package com.etsy.android.uikit.view;

import android.app.Dialog;
import android.graphics.Rect;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.WindowManager.LayoutParams;
import com.etsy.android.lib.util.s;
import com.etsy.android.uikit.f;

public class TouchObservableDialog extends Dialog {
    private DialogFragment mDialogFragment;
    private boolean mDismissOnTouchOutside = true;
    private int mFragmentContainerResId;
    private Rect mTouchInterceptRect;

    public TouchObservableDialog(FragmentActivity fragmentActivity, DialogFragment dialogFragment, int i, int i2) {
        super(fragmentActivity, i);
        this.mDialogFragment = dialogFragment;
        this.mFragmentContainerResId = i2;
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.verticalMargin = 0.0f;
        attributes.horizontalMargin = 0.0f;
    }

    public void setTouchInterceptRect(Rect rect) {
        this.mTouchInterceptRect = rect;
    }

    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(false);
        this.mDismissOnTouchOutside = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!didHitOutsideDialogWindow(motionEvent) || !this.mDismissOnTouchOutside || hideKeyboard()) {
            return super.onTouchEvent(motionEvent);
        }
        this.mDialogFragment.dismiss();
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!didHitOutsideDialogWindow(motionEvent) || this.mTouchInterceptRect == null || !this.mTouchInterceptRect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return super.dispatchTouchEvent(motionEvent);
        }
        hideKeyboard();
        return true;
    }

    private boolean hideKeyboard() {
        return s.a(getCurrentFocus());
    }

    private boolean didHitOutsideDialogWindow(MotionEvent motionEvent) {
        Rect rect = new Rect();
        getWindow().getDecorView().getHitRect(rect);
        return !rect.contains((int) motionEvent.getX(), (int) motionEvent.getY()) && motionEvent.getAction() == 0;
    }

    public void onBackPressed() {
        FragmentManager childFragmentManager = this.mDialogFragment.getChildFragmentManager();
        if (childFragmentManager.getBackStackEntryCount() > 0) {
            f fVar = (f) childFragmentManager.findFragmentById(this.mFragmentContainerResId);
            if (fVar != null && fVar.handleBackPressed()) {
                return;
            }
        }
        this.mDialogFragment.dismiss();
    }
}
