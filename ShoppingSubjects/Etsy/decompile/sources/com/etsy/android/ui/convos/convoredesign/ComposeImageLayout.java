package com.etsy.android.ui.convos.convoredesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.etsy.android.R;
import com.etsy.android.extensions.g;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.o;
import kotlin.h;
import kotlin.jvm.internal.p;

/* compiled from: ComposeImageLayout.kt */
public final class ComposeImageLayout extends LinearLayout {
    public static final a Companion = new a(null);
    private static final int NUM_IMAGES = 3;
    private HashMap _$_findViewCache;
    private final List<ComposeImageView> composeImageViews;
    private kotlin.jvm.a.b<? super Integer, h> removeListener;

    /* compiled from: ComposeImageLayout.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    /* compiled from: ComposeImageLayout.kt */
    public static final class b implements com.etsy.android.ui.convos.convoredesign.ComposeImageView.a {
        final /* synthetic */ ComposeImageView a;
        final /* synthetic */ ComposeImageLayout b;
        final /* synthetic */ int c;

        b(ComposeImageView composeImageView, ComposeImageLayout composeImageLayout, int i) {
            this.a = composeImageView;
            this.b = composeImageLayout;
            this.c = i;
        }

        public void a() {
            this.a.setVisibility(8);
            kotlin.jvm.a.b removeListener = this.b.getRemoveListener();
            if (removeListener != null) {
                h hVar = (h) removeListener.invoke(Integer.valueOf(this.c));
            }
        }
    }

    public ComposeImageLayout(Context context) {
        this(context, null, 0, 6, null);
    }

    public ComposeImageLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ ComposeImageLayout(Context context, AttributeSet attributeSet, int i, int i2, o oVar) {
        if ((i2 & 2) != 0) {
            attributeSet = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        this(context, attributeSet, i);
    }

    public ComposeImageLayout(Context context, AttributeSet attributeSet, int i) {
        p.b(context, ResponseConstants.CONTEXT);
        super(context, attributeSet, i);
        List arrayList = new ArrayList();
        for (int i2 = 0; i2 < 3; i2++) {
            ComposeImageView buildComposeImageView = buildComposeImageView(i2);
            arrayList.add(i2, buildComposeImageView);
            addView(buildComposeImageView);
        }
        this.composeImageViews = o.f((Iterable) arrayList);
    }

    public final kotlin.jvm.a.b<Integer, h> getRemoveListener() {
        return this.removeListener;
    }

    public final void setRemoveListener(kotlin.jvm.a.b<? super Integer, h> bVar) {
        this.removeListener = bVar;
    }

    public final void addLoading(int i) {
        checkPositionInRange(i);
        ComposeImageView composeImageView = (ComposeImageView) this.composeImageViews.get(i);
        composeImageView.setVisibility(0);
        composeImageView.showLoading();
    }

    public final void removeLoading(int i) {
        checkPositionInRange(i);
        j.b((View) this.composeImageViews.get(i));
    }

    public final void addImage(Bitmap bitmap, int i) {
        p.b(bitmap, "bitmap");
        checkPositionInRange(i);
        ((ComposeImageView) this.composeImageViews.get(i)).showImage(bitmap);
    }

    public final void removeImage(int i) {
        checkPositionInRange(i);
        ((ComposeImageView) this.composeImageViews.get(i)).removeImage();
        j.b((View) this.composeImageViews.get(i));
    }

    private final ComposeImageView buildComposeImageView(int i) {
        ComposeImageView composeImageView = new ComposeImageView(getContext());
        Number valueOf = Integer.valueOf(75);
        Context context = composeImageView.getContext();
        p.a((Object) context, ResponseConstants.CONTEXT);
        int a2 = g.a(valueOf, context);
        Number valueOf2 = Integer.valueOf(75);
        Context context2 = composeImageView.getContext();
        p.a((Object) context2, ResponseConstants.CONTEXT);
        composeImageView.setLayoutParams(new LayoutParams(a2, g.a(valueOf2, context2)));
        composeImageView.setVisibility(8);
        Context context3 = composeImageView.getContext();
        p.a((Object) context3, ResponseConstants.CONTEXT);
        int dimensionPixelSize = context3.getResources().getDimensionPixelSize(R.dimen.sk_space_2);
        Context context4 = composeImageView.getContext();
        p.a((Object) context4, ResponseConstants.CONTEXT);
        int dimensionPixelSize2 = context4.getResources().getDimensionPixelSize(R.dimen.sk_space_0);
        composeImageView.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        composeImageView.setRemoveClickListener(new b(composeImageView, this, i));
        return composeImageView;
    }

    private final void checkPositionInRange(int i) {
        if (i < 0 || 3 <= i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Provided position ");
            sb.append(i);
            sb.append(" is less than zero or greater than 2");
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
