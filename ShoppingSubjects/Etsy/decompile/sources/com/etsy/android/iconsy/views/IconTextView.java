package com.etsy.android.iconsy.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.etsy.android.iconsy.a;
import com.etsy.android.iconsy.c;
import com.etsy.android.iconsy.e;
import com.etsy.android.iconsy.e.b;

public class IconTextView extends TextView {
    public IconTextView(Context context) {
        super(context);
    }

    public IconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public IconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    @TargetApi(21)
    public IconTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.IconTextView);
        try {
            a iconForAttribute = getIconForAttribute(obtainStyledAttributes, b.IconTextView_iconCharLeft);
            a iconForAttribute2 = getIconForAttribute(obtainStyledAttributes, b.IconTextView_iconCharTop);
            a iconForAttribute3 = getIconForAttribute(obtainStyledAttributes, b.IconTextView_iconCharRight);
            a iconForAttribute4 = getIconForAttribute(obtainStyledAttributes, b.IconTextView_iconCharBottom);
            obtainStyledAttributes.recycle();
            if (!isInEditMode()) {
                setIconDrawablesWithIntrinsicBounds(iconForAttribute, iconForAttribute2, iconForAttribute3, iconForAttribute4);
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private IconDrawable createIconDrawable(@Nullable a aVar) {
        if (aVar == null) {
            return null;
        }
        return IconDrawable.a.a(getResources()).a(getCurrentTextColor()).a(aVar).a(getTextSize()).c(17).a();
    }

    @Nullable
    private a getIconForAttribute(TypedArray typedArray, int i) {
        int resourceId = typedArray.getResourceId(i, 0);
        if (isInEditMode()) {
            return c.a(e.a.ic_demo_example);
        }
        if (resourceId == 0) {
            return null;
        }
        return c.a(resourceId);
    }

    public void setIconDrawablesWithIntrinsicBounds(a aVar, a aVar2, a aVar3, a aVar4) {
        setCompoundDrawablesWithIntrinsicBounds(createIconDrawable(aVar), createIconDrawable(aVar2), createIconDrawable(aVar3), createIconDrawable(aVar4));
    }
}
