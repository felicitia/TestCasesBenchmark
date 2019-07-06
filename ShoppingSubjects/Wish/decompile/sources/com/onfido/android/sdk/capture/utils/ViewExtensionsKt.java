package com.onfido.android.sdk.capture.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;

public final class ViewExtensionsKt {
    public static final ViewPropertyAnimator alphaAnimator(View view, float f, long j) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        ViewPropertyAnimator duration = view.animate().alpha(f).setDuration(j);
        Intrinsics.checkExpressionValueIsNotNull(duration, "animate().alpha(alpha)\n …setDuration(milisseconds)");
        return duration;
    }

    public static /* synthetic */ ViewPropertyAnimator alphaAnimator$default(View view, float f, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 200;
        }
        return alphaAnimator(view, f, j);
    }

    public static final void animateToAlpha(View view, float f, long j) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        alphaAnimator(view, f, j).start();
    }

    public static /* synthetic */ void animateToAlpha$default(View view, float f, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 200;
        }
        animateToAlpha(view, f, j);
    }

    public static final int getTextPixelsHeight(TextView textView, float f, int i) {
        Intrinsics.checkParameterIsNotNull(textView, "$receiver");
        CharSequence text = textView.getText();
        TextPaint paint = textView.getPaint();
        textView.setTextSize(f);
        StaticLayout staticLayout = new StaticLayout(text, paint, i, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        return staticLayout.getHeight();
    }

    public static final boolean isVisible(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        return view.getVisibility() == 0;
    }

    public static final Bitmap toBitmap(Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "$receiver");
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "bitmap");
        return createBitmap;
    }

    public static final void toGone(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        view.setVisibility(8);
    }

    public static final void toVisible(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        view.setVisibility(0);
    }

    public static final void visibleIf(View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        if (z) {
            toVisible(view);
        } else {
            toGone(view);
        }
    }
}
