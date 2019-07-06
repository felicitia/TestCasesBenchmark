package com.contextlogic.wish.ui.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import com.crashlytics.android.Crashlytics;

public class SafeImageView extends AspectRatioImageView {
    public SafeImageView(Context context) {
        super(context);
    }

    public SafeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SafeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            if (getDrawable() != null && (getDrawable() instanceof BitmapDrawable)) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
                if (bitmapDrawable.getBitmap() != null && bitmapDrawable.getBitmap().isRecycled()) {
                    setImageBitmap(null);
                }
            }
            super.onDraw(canvas);
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("SafeImageView: ");
            sb.append(th.toString());
            Crashlytics.logException(new Exception(sb.toString()));
        }
    }
}
