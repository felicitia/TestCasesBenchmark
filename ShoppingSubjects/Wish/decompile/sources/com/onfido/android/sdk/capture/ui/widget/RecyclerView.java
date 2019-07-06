package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public final class RecyclerView extends FrameLayout {
    private HashMap a;

    public RecyclerView(Context context) {
        this(context, null, 0, 6, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            FrameLayout.inflate(context, R.layout.onfido_recycler_view, this);
            ((android.support.v7.widget.RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(context, 1, false));
            ((android.support.v7.widget.RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setVerticalScrollBarEnabled(true);
        }
    }

    public /* synthetic */ RecyclerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i);
    }

    public View _$_findCachedViewById(int i) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        View view = (View) this.a.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.a.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void enterLoadingState() {
        ViewExtensionsKt.toGone((android.support.v7.widget.RecyclerView) _$_findCachedViewById(R.id.recyclerView));
        ViewExtensionsKt.toVisible((ProgressBar) _$_findCachedViewById(R.id.progress));
    }

    public final void exitLoadingState() {
        ViewExtensionsKt.toGone((ProgressBar) _$_findCachedViewById(R.id.progress));
        ViewExtensionsKt.toVisible((android.support.v7.widget.RecyclerView) _$_findCachedViewById(R.id.recyclerView));
    }

    public final void scrollToPosition(int i) {
        ((android.support.v7.widget.RecyclerView) _$_findCachedViewById(R.id.recyclerView)).scrollToPosition(i);
    }

    public final void setAdapter(Adapter<ViewHolder> adapter) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        ((android.support.v7.widget.RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setAdapter(adapter);
    }
}
