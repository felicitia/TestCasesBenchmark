package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.g;
import com.etsy.android.lib.models.editable.EditableShareItem;
import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import com.etsy.android.lib.models.shopshare.ShareItem;
import com.etsy.android.lib.models.shopshare.ShareMedia;
import com.etsy.android.uikit.view.TaggableImageView.TaggableAdapter;
import com.etsy.android.uikit.view.TaggableImageView.d;

public class AnnotationAdapter extends ArrayAdapter<d> implements TaggableAdapter {
    /* access modifiers changed from: private */
    public Bitmap mBitmap;
    /* access modifiers changed from: private */
    public ImageView mImageView;
    private com.etsy.android.uikit.view.TaggableImageView.TaggableAdapter.a mListener;
    /* access modifiers changed from: private */
    public ShareMedia mShareMedia;
    final int offsetX;
    final int offsetY;

    public class a implements d {
        private ShareAnnotation b;

        a(ShareAnnotation shareAnnotation) {
            this.b = shareAnnotation;
        }

        public int a() {
            return com.etsy.android.lib.util.a.a.a(this.b.getCoordinates(), AnnotationAdapter.this.mShareMedia != null ? AnnotationAdapter.this.mShareMedia.getOriginalWidth() : AnnotationAdapter.this.mBitmap.getWidth(), AnnotationAdapter.this.mShareMedia != null ? AnnotationAdapter.this.mShareMedia.getOriginalHeight() : AnnotationAdapter.this.mBitmap.getHeight(), AnnotationAdapter.this.mImageView).x + AnnotationAdapter.this.offsetX;
        }

        public int b() {
            return com.etsy.android.lib.util.a.a.a(this.b.getCoordinates(), AnnotationAdapter.this.mShareMedia != null ? AnnotationAdapter.this.mShareMedia.getOriginalWidth() : AnnotationAdapter.this.mBitmap.getWidth(), AnnotationAdapter.this.mShareMedia != null ? AnnotationAdapter.this.mShareMedia.getOriginalHeight() : AnnotationAdapter.this.mBitmap.getHeight(), AnnotationAdapter.this.mImageView).y + AnnotationAdapter.this.offsetY;
        }

        public long c() {
            return this.b.getShareAnnotationId();
        }

        public ShareAnnotation d() {
            return this.b;
        }
    }

    public AnnotationAdapter(Context context, ImageView imageView, ShareItem shareItem) {
        super(context, 0);
        this.offsetX = com.etsy.android.lib.util.a.a.a(context);
        this.offsetY = com.etsy.android.lib.util.a.a.b(context);
        this.mImageView = imageView;
        this.mShareMedia = shareItem.getPrimaryMedia();
        addAllAnnotations(shareItem);
    }

    public AnnotationAdapter(Context context, ImageView imageView, EditableShareItem editableShareItem) {
        super(context, 0);
        this.offsetX = com.etsy.android.lib.util.a.a.a(context);
        this.offsetY = com.etsy.android.lib.util.a.a.b(context);
        this.mImageView = imageView;
        this.mBitmap = g.a(editableShareItem.getFile());
        addAllAnnotations(editableShareItem);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return LayoutInflater.from(getContext()).inflate(k.shop_share_annotation, null);
    }

    public void addAnnotation(ShareAnnotation shareAnnotation) {
        add(new a(shareAnnotation));
    }

    public AnnotationAdapter addAllAnnotations(EditableShareItem editableShareItem) {
        clear();
        ShareAnnotation primaryAnnotation = editableShareItem.getPrimaryAnnotation();
        if (primaryAnnotation != null) {
            add(new a(primaryAnnotation));
        }
        return this;
    }

    public AnnotationAdapter addAllAnnotations(ShareItem shareItem) {
        clear();
        for (ShareMedia annotations : shareItem.getMedia()) {
            for (ShareAnnotation aVar : annotations.getAnnotations()) {
                add(new a(aVar));
            }
        }
        return this;
    }

    public void addAnnotationCreate(ShareAnnotation shareAnnotation) {
        if (this.mListener != null) {
            this.mListener.a();
        }
        addAnnotation(shareAnnotation);
        if (this.mListener != null) {
            this.mListener.a(shareAnnotation.getShareAnnotationId());
        }
    }

    public void updateAnnotation(ShareAnnotation shareAnnotation) {
        d tagById = getTagById((long) Integer.parseInt(shareAnnotation.getObjectId().getId()));
        if (tagById != null) {
            updateAnnotation(tagById, shareAnnotation);
        }
    }

    public void updateAnnotation(d dVar, ShareAnnotation shareAnnotation) {
        remove(dVar);
        addAnnotation(shareAnnotation);
    }

    public void deleteAnnotation(long j) {
        d tagById = getTagById(j);
        if (tagById != null) {
            if (this.mListener != null) {
                this.mListener.b(j);
            }
            remove(tagById);
        }
    }

    public d getTagById(long j) {
        for (int i = 0; i < getCount(); i++) {
            d dVar = (d) getItem(i);
            if (dVar.c() == j) {
                return dVar;
            }
        }
        return null;
    }

    public void setListener(com.etsy.android.uikit.view.TaggableImageView.TaggableAdapter.a aVar) {
        this.mListener = aVar;
    }

    public void onStartDrag(ImageView imageView) {
        imageView.setColorFilter(e.black_transparent);
    }

    public void onEndDrag(ImageView imageView) {
        imageView.clearColorFilter();
    }

    public int getTagHeight() {
        return (int) getContext().getResources().getDimension(f.shop_share_tag_height);
    }

    public int getTagWidth() {
        return (int) getContext().getResources().getDimension(f.shop_share_tag_width);
    }
}
