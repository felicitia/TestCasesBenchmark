package com.etsy.android.uikit.a;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.j;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: EtsyAnimator */
public class b {
    /* access modifiers changed from: private */
    public final View a;
    private ObjectAnimator b;
    private ArrayList<a> c = new ArrayList<>();
    private long d;
    private long e;
    private HardwareAnimatorListener f;
    private TimeInterpolator g;
    private OnGlobalLayoutListener h;
    private OnPreDrawListener i;

    /* compiled from: EtsyAnimator */
    private static class a {
        final int a;
        final PropertyValuesHolder b;

        public a(int i, PropertyValuesHolder propertyValuesHolder) {
            this.a = i;
            this.b = propertyValuesHolder;
        }
    }

    private b(View view) {
        this.a = view;
        e();
    }

    public static b a(View view) {
        return new b(view);
    }

    private void e() {
        this.d = 300;
        this.e = 0;
        this.f = new HardwareAnimatorListener(this.a);
        this.g = new AccelerateDecelerateInterpolator();
    }

    public b a(int i2) {
        this.d = (long) i2;
        return this;
    }

    public b b(int i2) {
        this.e = (long) i2;
        return this;
    }

    public b a(HardwareAnimatorListener hardwareAnimatorListener) {
        this.f = hardwareAnimatorListener;
        return this;
    }

    public b a(TimeInterpolator timeInterpolator) {
        this.g = timeInterpolator;
        return this;
    }

    public b a(float f2, float f3) {
        this.c.add(new a(5, PropertyValuesHolder.ofFloat("translationX", new float[]{f2, f3})));
        return this;
    }

    public b b(float f2, float f3) {
        this.c.add(new a(7, PropertyValuesHolder.ofFloat("scaleX", new float[]{f2, f3})));
        return this;
    }

    public b c(float f2, float f3) {
        this.c.add(new a(8, PropertyValuesHolder.ofFloat("scaleY", new float[]{f2, f3})));
        return this;
    }

    public b a(int i2, int i3) {
        this.c.add(new a(9, PropertyValuesHolder.ofInt(ResponseConstants.HEIGHT, new int[]{i2, i3})));
        return this;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public ObjectAnimator a() {
        if (this.c.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            switch (aVar.a) {
                case 0:
                    this.a.setAlpha(0.0f);
                    break;
                case 1:
                    this.a.setAlpha(1.0f);
                    break;
                case 3:
                    aVar.b.setFloatValues(new float[]{(float) (-this.a.getHeight())});
                    break;
                case 4:
                    aVar.b.setFloatValues(new float[]{(float) this.a.getHeight()});
                    break;
                case 9:
                    arrayList2.add(new AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int intValue = ((Integer) valueAnimator.getAnimatedValue(ResponseConstants.HEIGHT)).intValue();
                            LayoutParams layoutParams = b.this.a.getLayoutParams();
                            layoutParams.height = intValue;
                            b.this.a.setLayoutParams(layoutParams);
                        }
                    });
                    break;
            }
            if (aVar.b != null) {
                arrayList.add(aVar.b);
            }
        }
        if (!arrayList.isEmpty()) {
            this.b = ObjectAnimator.ofPropertyValuesHolder(this.a, (PropertyValuesHolder[]) arrayList.toArray(new PropertyValuesHolder[arrayList.size()]));
        } else {
            this.b = new ObjectAnimator();
            this.b.setTarget(this.a);
        }
        this.b.setDuration(this.d);
        this.b.setStartDelay(this.e);
        this.b.addListener(this.f);
        this.b.setInterpolator(this.g);
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            this.b.addUpdateListener((AnimatorUpdateListener) it2.next());
        }
        return this.b;
    }

    public void b() {
        ObjectAnimator a2 = a();
        if (a2 != null) {
            a2.start();
        }
    }

    public void c() {
        this.h = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                j.b(b.this.a.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                b.this.b();
            }
        };
        j.a(this.a.getViewTreeObserver(), this.h);
    }

    public void d() {
        this.c.clear();
        j.b(this.a.getViewTreeObserver(), this.h);
        j.b(this.a.getViewTreeObserver(), this.i);
        if (this.b != null) {
            this.b.removeAllListeners();
            this.b.cancel();
        }
    }
}
