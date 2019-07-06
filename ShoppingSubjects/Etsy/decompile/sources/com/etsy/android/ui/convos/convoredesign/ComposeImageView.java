package com.etsy.android.ui.convos.convoredesign;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import com.etsy.android.R;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;
import kotlin.jvm.internal.p;

/* compiled from: ComposeImageView.kt */
public final class ComposeImageView extends FrameLayout {
    private HashMap _$_findViewCache;
    private final ImageView dismissView;
    private final ImageView imageView;
    private final ProgressBar loadingView;
    private a removeClickListener;

    /* compiled from: ComposeImageView.kt */
    public interface a {
        void a();
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

    public final a getRemoveClickListener() {
        return this.removeClickListener;
    }

    public final void setRemoveClickListener(a aVar) {
        this.removeClickListener = aVar;
    }

    public ComposeImageView(Context context) {
        super(context);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setLayoutParams(new LayoutParams(-1, -1));
        imageView2.setScaleType(ScaleType.CENTER_CROP);
        imageView2.setVisibility(8);
        this.imageView = imageView2;
        AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 8388661;
        appCompatImageView.setLayoutParams(layoutParams);
        appCompatImageView.setImageResource(R.drawable.sk_ic_close);
        Context context2 = appCompatImageView.getContext();
        p.a((Object) context2, ResponseConstants.CONTEXT);
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.sk_space_2);
        appCompatImageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        ImageView imageView3 = appCompatImageView;
        ImageViewCompat.setImageTintList(imageView3, ColorStateList.valueOf(-1));
        j.a(appCompatImageView, new ComposeImageView$$special$$inlined$apply$lambda$1(this));
        appCompatImageView.setVisibility(8);
        this.dismissView = imageView3;
        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setLayoutParams(new LayoutParams(-1, -1));
        progressBar.setVisibility(8);
        this.loadingView = progressBar;
        addView(this.imageView);
        addView(this.dismissView);
        addView(this.loadingView);
    }

    public ComposeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setLayoutParams(new LayoutParams(-1, -1));
        imageView2.setScaleType(ScaleType.CENTER_CROP);
        imageView2.setVisibility(8);
        this.imageView = imageView2;
        AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 8388661;
        appCompatImageView.setLayoutParams(layoutParams);
        appCompatImageView.setImageResource(R.drawable.sk_ic_close);
        Context context2 = appCompatImageView.getContext();
        p.a((Object) context2, ResponseConstants.CONTEXT);
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.sk_space_2);
        appCompatImageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        ImageView imageView3 = appCompatImageView;
        ImageViewCompat.setImageTintList(imageView3, ColorStateList.valueOf(-1));
        j.a(appCompatImageView, new ComposeImageView$$special$$inlined$apply$lambda$2(this));
        appCompatImageView.setVisibility(8);
        this.dismissView = imageView3;
        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setLayoutParams(new LayoutParams(-1, -1));
        progressBar.setVisibility(8);
        this.loadingView = progressBar;
        addView(this.imageView);
        addView(this.dismissView);
        addView(this.loadingView);
    }

    public ComposeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setLayoutParams(new LayoutParams(-1, -1));
        imageView2.setScaleType(ScaleType.CENTER_CROP);
        imageView2.setVisibility(8);
        this.imageView = imageView2;
        AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 8388661;
        appCompatImageView.setLayoutParams(layoutParams);
        appCompatImageView.setImageResource(R.drawable.sk_ic_close);
        Context context2 = appCompatImageView.getContext();
        p.a((Object) context2, ResponseConstants.CONTEXT);
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.sk_space_2);
        appCompatImageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        ImageView imageView3 = appCompatImageView;
        ImageViewCompat.setImageTintList(imageView3, ColorStateList.valueOf(-1));
        j.a(appCompatImageView, new ComposeImageView$$special$$inlined$apply$lambda$3(this));
        appCompatImageView.setVisibility(8);
        this.dismissView = imageView3;
        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setLayoutParams(new LayoutParams(-1, -1));
        progressBar.setVisibility(8);
        this.loadingView = progressBar;
        addView(this.imageView);
        addView(this.dismissView);
        addView(this.loadingView);
    }

    public final void showLoading() {
        this.loadingView.setVisibility(0);
        this.imageView.setVisibility(8);
        this.dismissView.setVisibility(8);
    }

    public final void showImage(Bitmap bitmap) {
        p.b(bitmap, "bitmap");
        this.imageView.setImageBitmap(bitmap);
        this.imageView.setVisibility(0);
        this.dismissView.setVisibility(0);
        this.loadingView.setVisibility(8);
    }

    public final void removeImage() {
        this.imageView.setImageBitmap(null);
        this.imageView.setVisibility(8);
        this.dismissView.setVisibility(8);
        this.loadingView.setVisibility(8);
    }
}
