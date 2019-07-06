package com.onfido.android.sdk.capture.validation;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.DoubleIntArrayEvaluator;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public final class CaptureValidationBubble extends RelativeLayout {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CaptureValidationBubble.class), "screenScaledDensity", "getScreenScaledDensity()F"))};
    private final DoubleIntArrayEvaluator b;
    private final Lazy c;
    private final ValueAnimator d;
    private HashMap e;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static final class a implements AnimatorUpdateListener {
        final /* synthetic */ CaptureValidationBubble a;

        a(CaptureValidationBubble captureValidationBubble) {
            this.a = captureValidationBubble;
        }

        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.IntArray");
            }
            int[] iArr = (int[]) animatedValue;
            LayoutParams layoutParams = ((LinearLayout) this.a._$_findCachedViewById(R.id.bubbleRoot)).getLayoutParams();
            layoutParams.width = iArr[0];
            layoutParams.height = iArr[1] + iArr[7];
            ((LinearLayout) this.a._$_findCachedViewById(R.id.bubbleRoot)).setLayoutParams(layoutParams);
            LayoutParams layoutParams2 = ((RelativeLayout) this.a._$_findCachedViewById(R.id.bubbleContainer)).getLayoutParams();
            layoutParams2.width = iArr[0];
            layoutParams2.height = iArr[1];
            ((RelativeLayout) this.a._$_findCachedViewById(R.id.bubbleContainer)).setLayoutParams(layoutParams2);
            LayoutParams layoutParams3 = ((ImageView) this.a._$_findCachedViewById(R.id.bubbleArrow)).getLayoutParams();
            layoutParams3.width = iArr[7];
            layoutParams3.height = iArr[8];
            ((ImageView) this.a._$_findCachedViewById(R.id.bubbleArrow)).setLayoutParams(layoutParams3);
            LayoutParams layoutParams4 = ((ImageView) this.a._$_findCachedViewById(R.id.warningIcon)).getLayoutParams();
            layoutParams4.width = iArr[2];
            layoutParams4.height = iArr[2];
            ((ImageView) this.a._$_findCachedViewById(R.id.warningIcon)).setLayoutParams(layoutParams4);
            LayoutParams layoutParams5 = ((ImageView) this.a._$_findCachedViewById(R.id.firstBullet)).getLayoutParams();
            if (layoutParams5 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) layoutParams5;
            layoutParams6.width = iArr[5];
            layoutParams6.height = iArr[5];
            layoutParams6.setMargins(layoutParams6.leftMargin, (((TextView) this.a._$_findCachedViewById(R.id.bubbleThirdTitle)).getLineHeight() / 2) - (iArr[5] / 2), iArr[6], layoutParams6.bottomMargin);
            LayoutParams layoutParams7 = layoutParams6;
            ((ImageView) this.a._$_findCachedViewById(R.id.firstBullet)).setLayoutParams(layoutParams7);
            ((ImageView) this.a._$_findCachedViewById(R.id.secondBullet)).setLayoutParams(layoutParams7);
            ((TextView) this.a._$_findCachedViewById(R.id.bubbleTitle)).setTextSize(0, (float) iArr[3]);
            ((TextView) this.a._$_findCachedViewById(R.id.bubbleSubtitle)).setTextSize(0, (float) iArr[4]);
        }
    }

    static final class b implements Runnable {
        final /* synthetic */ CaptureValidationBubble a;
        final /* synthetic */ boolean b;

        b(CaptureValidationBubble captureValidationBubble, boolean z) {
            this.a = captureValidationBubble;
            this.b = z;
        }

        public final void run() {
            this.a.animate(this.b);
        }
    }

    static final class c extends Lambda implements Function0<Float> {
        final /* synthetic */ CaptureValidationBubble a;

        c(CaptureValidationBubble captureValidationBubble) {
            this.a = captureValidationBubble;
            super(0);
        }

        public final float a() {
            return this.a.getResources().getDisplayMetrics().scaledDensity;
        }

        public /* synthetic */ Object invoke() {
            return Float.valueOf(a());
        }
    }

    public CaptureValidationBubble(Context context) {
        this(context, null, 0, 6, null);
    }

    public CaptureValidationBubble(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public CaptureValidationBubble(Context context, AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i);
        this.b = new DoubleIntArrayEvaluator();
        this.c = LazyKt.lazy(new c(this));
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.addUpdateListener(new a(this));
        this.d = valueAnimator;
        RelativeLayout.inflate(context, R.layout.onfido_post_capture_bubble, this);
    }

    public /* synthetic */ CaptureValidationBubble(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i);
    }

    private final void a(int i, int i2, Integer num) {
        ((TextView) _$_findCachedViewById(R.id.bubbleTitle)).setText(getContext().getString(i));
        ((TextView) _$_findCachedViewById(R.id.bubbleSubtitle)).setText(getContext().getString(i2));
        if (num != null) {
            ((TextView) _$_findCachedViewById(R.id.bubbleThirdTitle)).setText(getContext().getString(num.intValue()));
            int i3 = R.color.onfido_white;
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.firstBullet);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "firstBullet");
            a(i3, (View) imageView, R.drawable.onfido_circle);
            int i4 = R.color.onfido_white;
            ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.secondBullet);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "secondBullet");
            a(i4, (View) imageView2, R.drawable.onfido_circle);
        } else {
            ((TextView) _$_findCachedViewById(R.id.bubbleThirdTitle)).setText("");
        }
        boolean z = false;
        ViewExtensionsKt.visibleIf((ImageView) _$_findCachedViewById(R.id.firstBullet), num != null);
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.secondBulletContainer);
        if (num != null) {
            z = true;
        }
        ViewExtensionsKt.visibleIf(linearLayout, z);
    }

    private final void a(int i, View view, int i2) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), i2);
        if (drawable != null) {
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTint(mutate, ContextCompat.getColor(getContext(), i));
            if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(mutate);
                return;
            }
            view.setBackground(mutate);
        }
    }

    private final int getBulletWidth() {
        if (ViewExtensionsKt.isVisible((LinearLayout) _$_findCachedViewById(R.id.secondBulletContainer))) {
            return ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_bullet_size) + ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_bullet_margin_right);
        }
        return 0;
    }

    private final int getHorizontalMargin() {
        return ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_margin_horiz) * 2;
    }

    private final int getIconWidth() {
        return ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_icon_size) + ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_icon_margin_right);
    }

    private final float getScreenScaledDensity() {
        Lazy lazy = this.c;
        KProperty kProperty = a[0];
        return ((Number) lazy.getValue()).floatValue();
    }

    private final void setColor(int i) {
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.bubbleContainer);
        Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "bubbleContainer");
        a(i, (View) relativeLayout, R.drawable.onfido_rounded_red_shape);
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.bubbleArrow);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "bubbleArrow");
        a(i, (View) imageView, R.drawable.onfido_post_capture_bubble_arrow);
    }

    private final void setWarningIcon(int i) {
        ((ImageView) _$_findCachedViewById(R.id.warningIcon)).setImageDrawable(ContextCompat.getDrawable(getContext(), i));
    }

    public View _$_findCachedViewById(int i) {
        if (this.e == null) {
            this.e = new HashMap();
        }
        View view = (View) this.e.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.e.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void animate(boolean z) {
        if (this.d.isRunning()) {
            postDelayed(new b(this, z), 300);
        }
        if (((getWidth() == 0 && z) || (getWidth() > 0 && !z)) && !this.d.isRunning()) {
            if (z) {
                int[] iArr = {0, 0, 0, 0, 0, 0, 0, 0, 0};
                int[] iArr2 = new int[9];
                iArr2[0] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_width);
                iArr2[1] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_margin_top_text) + ViewExtensionsKt.getTextPixelsHeight((TextView) _$_findCachedViewById(R.id.bubbleTitle), getResources().getDimension(R.dimen.onfido_post_capture_bubble_title_size) / getScreenScaledDensity(), (ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_width) - getHorizontalMargin()) - getIconWidth()) + ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_title_margin_bottom) + ViewExtensionsKt.getTextPixelsHeight((TextView) _$_findCachedViewById(R.id.bubbleSubtitle), getResources().getDimension(R.dimen.onfido_post_capture_bubble_subtitle_size) / getScreenScaledDensity(), ((ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_width) - getHorizontalMargin()) - getIconWidth()) - getBulletWidth()) + (ViewExtensionsKt.isVisible((LinearLayout) _$_findCachedViewById(R.id.secondBulletContainer)) ? ViewExtensionsKt.getTextPixelsHeight((TextView) _$_findCachedViewById(R.id.bubbleThirdTitle), getResources().getDimension(R.dimen.onfido_post_capture_bubble_subtitle_size) / getScreenScaledDensity(), ((ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_width) - getHorizontalMargin()) - getIconWidth()) - getBulletWidth()) + ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_third_title_margin_top) : 0) + ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_margin_bottom);
                iArr2[2] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_icon_size);
                iArr2[3] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_title_size);
                iArr2[4] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_subtitle_size);
                iArr2[5] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_bullet_size);
                iArr2[6] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_bullet_margin_right);
                iArr2[7] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_bubble_arrow_width);
                iArr2[8] = ContextUtilsKt.dimen(getContext(), R.dimen.onfido_post_capture_bubble_bubble_arrow_height);
                ValueAnimator valueAnimator = this.d;
                valueAnimator.setObjectValues(new Object[]{iArr, iArr2});
                valueAnimator.setEvaluator(this.b);
                valueAnimator.start();
                return;
            }
            this.d.reverse();
        }
    }

    public final void setContent(int i, int i2, Integer num, int i3, int i4) {
        a(i, i2, num);
        setWarningIcon(i3);
        setColor(i4);
    }
}
