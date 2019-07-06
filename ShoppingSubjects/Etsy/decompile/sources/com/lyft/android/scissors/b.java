package com.lyft.android.scissors;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.lyft.android.scissors.h.a;

/* compiled from: CropViewConfig */
class b {
    private float a = 0.0f;
    private float b = 10.0f;
    private float c = 0.0f;
    private int d = 0;
    private int e = -939524096;
    private boolean f;

    b() {
    }

    public int a() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        this.e = i;
    }

    public int b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public void b(int i) {
        this.d = i;
    }

    public float c() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public void a(float f2) {
        if (f2 <= 0.0f) {
            f2 = 0.0f;
        }
        this.a = f2;
    }

    public float d() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public void b(float f2) {
        if (f2 <= 0.0f) {
            f2 = 10.0f;
        }
        this.b = f2;
    }

    public float e() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public void c(float f2) {
        if (f2 <= 0.0f) {
            f2 = 0.0f;
        }
        this.c = f2;
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.f = z;
    }

    public boolean f() {
        return this.f;
    }

    public static b a(Context context, AttributeSet attributeSet) {
        b bVar = new b();
        if (attributeSet == null) {
            return bVar;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.CropView);
        bVar.a(obtainStyledAttributes.getFloat(a.CropView_cropviewViewportRatio, 0.0f));
        bVar.b(obtainStyledAttributes.getFloat(a.CropView_cropviewMaxScale, 10.0f));
        bVar.c(obtainStyledAttributes.getFloat(a.CropView_cropviewMinScale, 0.0f));
        bVar.a(obtainStyledAttributes.getColor(a.CropView_cropviewViewportOverlayColor, -939524096));
        bVar.b(obtainStyledAttributes.getDimensionPixelSize(a.CropView_cropviewViewportOverlayPadding, 0));
        bVar.a(obtainStyledAttributes.getBoolean(a.CropView_cropviewUseViewportOvalOverlay, false));
        obtainStyledAttributes.recycle();
        return bVar;
    }
}
