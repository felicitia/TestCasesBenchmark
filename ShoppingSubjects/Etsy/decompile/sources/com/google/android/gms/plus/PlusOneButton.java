package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ViewUtils;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
public final class PlusOneButton extends FrameLayout {
    @Deprecated
    public static final int ANNOTATION_BUBBLE = 1;
    @Deprecated
    public static final int ANNOTATION_INLINE = 2;
    @Deprecated
    public static final int ANNOTATION_NONE = 0;
    @Deprecated
    public static final int DEFAULT_ACTIVITY_REQUEST_CODE = -1;
    @Deprecated
    public static final int SIZE_MEDIUM = 1;
    @Deprecated
    public static final int SIZE_SMALL = 0;
    @Deprecated
    public static final int SIZE_STANDARD = 3;
    @Deprecated
    public static final int SIZE_TALL = 2;
    private int mSize;
    /* access modifiers changed from: private */
    public View zzi;
    private int zzj;
    private String zzk;
    /* access modifiers changed from: private */
    public int zzl;
    private a zzm;

    @Deprecated
    protected class DefaultOnPlusOneClickListener implements OnClickListener, a {
        private final a zzn;

        @Deprecated
        public DefaultOnPlusOneClickListener(a aVar) {
            this.zzn = aVar;
        }

        @Deprecated
        public void onClick(View view) {
            Intent intent = (Intent) PlusOneButton.this.zzi.getTag();
            if (this.zzn != null) {
                this.zzn.onPlusOneClick(intent);
            } else {
                onPlusOneClick(intent);
            }
        }

        @Deprecated
        public void onPlusOneClick(Intent intent) {
            Context context = PlusOneButton.this.getContext();
            if ((context instanceof Activity) && intent != null) {
                ((Activity) context).startActivityForResult(intent, PlusOneButton.this.zzl);
            }
        }
    }

    @Deprecated
    public interface a {
        @Deprecated
        void onPlusOneClick(Intent intent);
    }

    @Deprecated
    public PlusOneButton(Context context) {
        this(context, null);
    }

    @Deprecated
    public PlusOneButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSize = getSize(context, attributeSet);
        this.zzj = getAnnotation(context, attributeSet);
        this.zzl = -1;
        zza(getContext());
        if (isInEditMode()) {
        }
    }

    @Deprecated
    protected static int getAnnotation(Context context, AttributeSet attributeSet) {
        String xmlAttributeString = ViewUtils.getXmlAttributeString("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, attributeSet, true, false, "PlusOneButton");
        if ("INLINE".equalsIgnoreCase(xmlAttributeString)) {
            return 2;
        }
        return "NONE".equalsIgnoreCase(xmlAttributeString) ? 0 : 1;
    }

    @Deprecated
    protected static int getSize(Context context, AttributeSet attributeSet) {
        String xmlAttributeString = ViewUtils.getXmlAttributeString("http://schemas.android.com/apk/lib/com.google.android.gms.plus", ResponseConstants.SIZE, context, attributeSet, true, false, "PlusOneButton");
        if ("SMALL".equalsIgnoreCase(xmlAttributeString)) {
            return 0;
        }
        if ("MEDIUM".equalsIgnoreCase(xmlAttributeString)) {
            return 1;
        }
        return "TALL".equalsIgnoreCase(xmlAttributeString) ? 2 : 3;
    }

    private final void zza(Context context) {
        if (this.zzi != null) {
            removeView(this.zzi);
        }
        this.zzi = com.google.android.gms.plus.internal.a.a(context, this.mSize, this.zzj, this.zzk, this.zzl);
        setOnPlusOneClickListener(this.zzm);
        addView(this.zzi);
    }

    @Deprecated
    public final void initialize(String str, int i) {
        Preconditions.checkState(getContext() instanceof Activity, "To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
        this.zzk = str;
        this.zzl = i;
        zza(getContext());
    }

    @Deprecated
    public final void initialize(String str, a aVar) {
        this.zzk = str;
        this.zzl = 0;
        zza(getContext());
        setOnPlusOneClickListener(aVar);
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.zzi.layout(0, 0, i3 - i, i4 - i2);
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        View view = this.zzi;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Deprecated
    @VisibleForTesting
    public final void plusOneClick() {
        this.zzi.performClick();
    }

    @Deprecated
    public final void setAnnotation(int i) {
        this.zzj = i;
        zza(getContext());
    }

    @Deprecated
    @VisibleForTesting
    public final void setIntent(Intent intent) {
        this.zzi.setTag(intent);
    }

    @Deprecated
    public final void setOnPlusOneClickListener(a aVar) {
        this.zzm = aVar;
        this.zzi.setOnClickListener(new DefaultOnPlusOneClickListener(aVar));
    }

    @Deprecated
    public final void setSize(int i) {
        this.mSize = i;
        zza(getContext());
    }
}
