package com.etsy.android.extensions;

import android.view.View;
import com.etsy.android.uikit.util.TrackingOnLongClickListener;
import kotlin.jvm.a.b;

/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt$trackingLongClick$1 extends TrackingOnLongClickListener {
    final /* synthetic */ b $listener;

    ViewExtensionsKt$trackingLongClick$1(b bVar) {
        this.$listener = bVar;
    }

    public boolean onViewLongClick(View view) {
        return ((Boolean) this.$listener.invoke(view)).booleanValue();
    }
}
