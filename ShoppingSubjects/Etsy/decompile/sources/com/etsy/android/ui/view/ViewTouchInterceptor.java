package com.etsy.android.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ViewTouchInterceptor extends View {
    private a mAlternateAction;
    private boolean mBlockDispatchTouch;
    private View mInterceptView;
    private boolean mIsSendingToInterceptView = true;

    public interface a {
        void a();
    }

    public ViewTouchInterceptor(Context context) {
        super(context);
    }

    public ViewTouchInterceptor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ViewTouchInterceptor(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setInterceptView(View view) {
        this.mInterceptView = view;
    }

    public void setBlockedAction(a aVar) {
        this.mAlternateAction = aVar;
    }

    public void setBlockDispatchTouch(boolean z) {
        this.mBlockDispatchTouch = z;
    }

    public boolean isBlockingDispatchTouch() {
        return this.mBlockDispatchTouch;
    }

    public boolean isSendingToInterceptView() {
        return this.mIsSendingToInterceptView;
    }

    public void setSendingToInterceptView(boolean z) {
        this.mIsSendingToInterceptView = z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mBlockDispatchTouch) {
            if (this.mAlternateAction != null) {
                this.mAlternateAction.a();
            }
            return true;
        } else if (this.mInterceptView == null || !this.mIsSendingToInterceptView) {
            return super.dispatchTouchEvent(motionEvent);
        } else {
            return this.mInterceptView.dispatchTouchEvent(motionEvent);
        }
    }
}
