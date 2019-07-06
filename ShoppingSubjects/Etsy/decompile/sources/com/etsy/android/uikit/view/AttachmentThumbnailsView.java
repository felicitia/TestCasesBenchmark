package com.etsy.android.uikit.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.core.img.c;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AttachmentThumbnailsView extends LinearLayout {
    protected static final int mMaxImages = 3;
    protected int mDividerPadding = 0;
    protected c mImageBatch = null;
    private List<a> mImageViews;
    protected int mItemHeight = 0;
    protected int mItemWidth = 0;
    protected int mLoadingColor = 0;

    enum AttachmentType {
        UNKNOWN,
        FILE,
        URL
    }

    private static class a {
        ClickableImageView a;
        String b = null;
        AttachmentType c = AttachmentType.UNKNOWN;

        public a(ClickableImageView clickableImageView) {
            this.a = clickableImageView;
        }

        public void a() {
            this.c = AttachmentType.UNKNOWN;
            this.b = "";
            this.a.setImageDrawable(null);
            this.a.setVisibility(8);
        }

        public boolean b() {
            return this.c != AttachmentType.UNKNOWN && !TextUtils.isEmpty(this.b);
        }
    }

    public AttachmentThumbnailsView(Context context) {
        super(context);
        init(context, null);
    }

    public AttachmentThumbnailsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AttachmentThumbnailsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public void setImageBatch(c cVar) {
        this.mImageBatch = cVar;
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attributeSet) {
        this.mImageViews = new ArrayList(3);
        this.mDividerPadding = context.getResources().getDimensionPixelSize(f.padding_medium_large);
        this.mLoadingColor = context.getResources().getColor(e.lighter_grey);
        this.mItemWidth = (int) context.getResources().getDimension(f.attachment_thumbnail_size);
        this.mItemHeight = this.mItemWidth;
        for (int i = 0; i < 3; i++) {
            ClickableImageView createImageView = createImageView(context);
            this.mImageViews.add(i, new a(createImageView));
            addView(createImageView);
        }
    }

    /* access modifiers changed from: protected */
    public ClickableImageView createImageView(Context context) {
        ClickableImageView clickableImageView = new ClickableImageView(context);
        clickableImageView.setScaleType(ScaleType.CENTER_CROP);
        LayoutParams layoutParams = new LayoutParams(this.mItemWidth, this.mItemHeight);
        layoutParams.rightMargin = this.mDividerPadding;
        clickableImageView.setLayoutParams(layoutParams);
        return clickableImageView;
    }

    public void clear() {
        for (a a2 : this.mImageViews) {
            a2.a();
        }
    }

    public boolean isSlotAvailable() {
        for (a b : this.mImageViews) {
            if (!b.b()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public a getAvailableSlot() {
        for (a aVar : this.mImageViews) {
            if (!aVar.b()) {
                return aVar;
            }
        }
        return null;
    }

    public boolean addImage(File file) {
        return addImage(file, (OnClickListener) null);
    }

    public boolean addImage(File file, OnClickListener onClickListener) {
        a availableSlot = getAvailableSlot();
        StringBuilder sb = new StringBuilder();
        sb.append("file://");
        sb.append(file.getAbsolutePath());
        String sb2 = sb.toString();
        if (availableSlot == null || !URLUtil.isValidUrl(sb2)) {
            return false;
        }
        availableSlot.b = sb2;
        availableSlot.c = AttachmentType.FILE;
        availableSlot.a.setOnClickListener(onClickListener);
        loadImage(availableSlot);
        return true;
    }

    public boolean addImage(String str) {
        return addImage(str, (OnClickListener) null);
    }

    public boolean addImage(String str, OnClickListener onClickListener) {
        a availableSlot = getAvailableSlot();
        if (availableSlot == null || !URLUtil.isValidUrl(str)) {
            return false;
        }
        availableSlot.b = str;
        availableSlot.c = AttachmentType.URL;
        availableSlot.a.setOnClickListener(onClickListener);
        loadImage(availableSlot);
        return true;
    }

    /* access modifiers changed from: protected */
    public void loadImage(a aVar) {
        if (!aVar.b() || this.mImageBatch == null) {
            aVar.a.setVisibility(8);
            return;
        }
        aVar.a.setVisibility(0);
        this.mImageBatch.a(new com.etsy.android.lib.core.img.e(aVar.b, aVar.a).a(this.mItemWidth, this.mItemHeight).d(this.mLoadingColor).c(g.image_default_preview), true);
    }
}
