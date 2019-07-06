package com.contextlogic.wish.ui.drawer;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SelfClosingDrawerLayout extends DrawerLayout {
    public SelfClosingDrawerLayout(Context context) {
        super(context);
    }

    public SelfClosingDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SelfClosingDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        try {
            closeDrawer(8388611);
        } catch (IllegalArgumentException unused) {
        }
        try {
            closeDrawer(8388613);
        } catch (IllegalArgumentException unused2) {
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }
}
