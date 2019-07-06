package com.etsy.android.extensions;

import android.view.View;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import kotlin.jvm.a.b;

/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt$trackingClick$1 extends TrackingOnClickListener {
    final /* synthetic */ b $listener;

    ViewExtensionsKt$trackingClick$1(b bVar) {
        this.$listener = bVar;
    }

    public void onViewClick(View view) {
        this.$listener.invoke(view);
    }
}
