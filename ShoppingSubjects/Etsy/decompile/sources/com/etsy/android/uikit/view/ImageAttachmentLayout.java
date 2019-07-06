package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.etsy.android.lib.a.h;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.q;
import com.etsy.android.lib.core.img.g;
import com.etsy.android.lib.logger.f;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ImageAttachmentLayout extends LinearLayout implements OnClickListener {
    private static final String STATE_ATTACHMENTS = "attachments";
    private static final String STATE_SUPER = "super";
    private static final String TAG = "ConvoImageAttachment";
    private static final int mNumberImageLayouts = 3;
    private a mCallback;
    private int mDividerPadding = 0;
    private Set<File> mImageAttachments;
    private int mImageHeight = Math.round(((float) this.mImageWidth) * this.mImageRatio);
    private LayoutParams mImageLayoutParams;
    private float mImageRatio = 0.5625f;
    private List<b> mImageViews;
    private int mImageWidth = 200;
    private boolean mImagesToAttach = false;
    private int mPaddingLeft;
    private int mPaddingRight;

    public interface a {
        void onRemove();
    }

    public static class b {
        View a;
        View b;
        ImageView c;
        ImageButton d;
        public File e;
        public boolean f;
    }

    public ImageAttachmentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ImageAttachmentLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(Context context, AttributeSet attributeSet) {
        this.mImageAttachments = Collections.synchronizedSet(new HashSet(3));
        this.mImageViews = new ArrayList(3);
        for (int i = 0; i < 3; i++) {
            this.mImageViews.add(createHolder(inflate(context, k.convo_image, null)));
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, q.ImageAttachmentLayout, 0, 0);
        try {
            this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(q.ImageAttachmentLayout_attachmentPadding, 0);
            obtainStyledAttributes.recycle();
            this.mPaddingLeft = getPaddingLeft();
            this.mPaddingRight = getPaddingRight();
            this.mImageRatio = getResources().getFraction(h.convo_image_attachments_ratio, 1, 1);
            this.mImageLayoutParams = new LayoutParams(this.mImageWidth, this.mImageHeight);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void refreshRatio() {
        this.mImageRatio = getResources().getFraction(h.convo_image_attachments_ratio, 1, 1);
    }

    public void calcSizes(int i) {
        int i2 = this.mDividerPadding / 2;
        int i3 = this.mPaddingRight - i2;
        int size = (MeasureSpec.getSize(i) - (this.mPaddingLeft - i2)) - i3;
        if (size != 0) {
            this.mImageWidth = Math.round(((float) size) / 3.0f);
            this.mImageHeight = Math.round(((float) (this.mImageWidth - this.mDividerPadding)) * this.mImageRatio);
        }
    }

    private b createHolder(View view) {
        b bVar = new b();
        bVar.a = view;
        bVar.c = (ImageView) view.findViewById(i.convo_image_attachment);
        bVar.d = (ImageButton) view.findViewById(i.convo_image_attachment_remove_button);
        bVar.d.setOnClickListener(this);
        bVar.d.setTag(bVar);
        bVar.b = view.findViewById(i.activity_indicator);
        return bVar;
    }

    public void setImageAttachmentCallback(a aVar) {
        this.mCallback = aVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        calcSizes(i);
        int i3 = this.mDividerPadding / 2;
        setPadding(this.mPaddingLeft - i3, getPaddingTop(), this.mPaddingRight - i3, getPaddingBottom());
        this.mImageLayoutParams.width = this.mImageWidth;
        this.mImageLayoutParams.height = this.mImageHeight;
        for (int i4 = 0; i4 < this.mImageViews.size(); i4++) {
            b bVar = (b) this.mImageViews.get(i4);
            bVar.a.setLayoutParams(this.mImageLayoutParams);
            bVar.a.setPadding(i3, 0, i3, 0);
        }
        if (this.mImageAttachments != null && this.mImagesToAttach) {
            this.mImagesToAttach = false;
            for (File file : this.mImageAttachments) {
                if (file != null && file.exists()) {
                    b findUnattachedView = findUnattachedView();
                    if (findUnattachedView != null) {
                        addScaledBitmap(findUnattachedView, g.a(file.getAbsolutePath(), this.mImageWidth, this.mImageHeight), file);
                        super.addView(findUnattachedView.a);
                    }
                }
            }
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        ArrayList arrayList = new ArrayList();
        for (File absolutePath : this.mImageAttachments) {
            arrayList.add(absolutePath.getAbsolutePath());
        }
        bundle.putStringArrayList(STATE_ATTACHMENTS, arrayList);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
        clear();
        ArrayList stringArrayList = bundle.getStringArrayList(STATE_ATTACHMENTS);
        if (stringArrayList != null) {
            Iterator it = stringArrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                b startLoading = startLoading();
                Pair b2 = g.b(str);
                addBitmap(startLoading, g.a(str, ((Integer) b2.first).intValue(), ((Integer) b2.second).intValue()), new File(str));
            }
        }
    }

    public void setImages(List<File> list) {
        clearSaved();
        if (list != null) {
            for (File file : list) {
                if (file != null && file.exists() && !this.mImageAttachments.contains(file)) {
                    this.mImageAttachments.add(file);
                    this.mImagesToAttach = true;
                }
            }
        }
        invalidate();
    }

    public void clearSaved() {
        for (int i = 0; i < this.mImageViews.size(); i++) {
            if (!isLoading((b) this.mImageViews.get(i))) {
                removeImage((b) this.mImageViews.get(i));
            }
        }
        this.mImageAttachments.clear();
    }

    public void clear() {
        for (int i = 0; i < this.mImageViews.size(); i++) {
            removeImage((b) this.mImageViews.get(i));
        }
        this.mImageAttachments.clear();
    }

    public void removeImage(b bVar) {
        removeImage(bVar, true);
    }

    private void removeImage(b bVar, boolean z) {
        if (bVar != null) {
            bVar.c.setImageBitmap(null);
            if (bVar.e != null && z) {
                this.mImageAttachments.remove(bVar.e);
            }
            bVar.f = false;
            super.removeView(bVar.a);
        }
    }

    public void removeImage(File file) {
        for (int i = 0; i < this.mImageViews.size(); i++) {
            b bVar = (b) this.mImageViews.get(i);
            if (!(bVar == null || bVar.e == null || !bVar.e.equals(file))) {
                StringBuilder sb = new StringBuilder();
                sb.append("Removing existing file image: ");
                sb.append(file.getAbsolutePath());
                f.c("ConvoMessages", sb.toString());
                removeImage(bVar, false);
            }
        }
    }

    private void addScaledBitmap(b bVar, Bitmap bitmap, File file) {
        removeImage(file);
        bVar.c.setImageBitmap(bitmap);
        bVar.e = file;
        this.mImageAttachments.add(file);
        bVar.f = true;
        bVar.a.setVisibility(0);
        bVar.b.setVisibility(8);
    }

    public void addBitmap(b bVar, Bitmap bitmap, File file) {
        if (bVar != null && file.exists() && bitmap != null) {
            Bitmap a2 = g.a(bitmap, this.mImageWidth, this.mImageHeight);
            bitmap.recycle();
            addScaledBitmap(bVar, a2, file);
        }
    }

    public void onAbort(b bVar, File file) {
        if (bVar != null) {
            super.removeView(bVar.a);
            bVar.c.setImageBitmap(null);
            bVar.f = false;
        }
        if (file != null) {
            file.delete();
        }
    }

    public synchronized b startLoading() {
        b findUnattachedView = findUnattachedView();
        if (findUnattachedView == null) {
            return null;
        }
        findUnattachedView.b.setVisibility(0);
        findUnattachedView.a.setVisibility(0);
        super.addView(findUnattachedView.a);
        findUnattachedView.f = true;
        return findUnattachedView;
    }

    public synchronized boolean isLoading(b bVar) {
        return bVar.b.getVisibility() == 0;
    }

    private b findUnattachedView() {
        for (int i = 0; i < this.mImageViews.size(); i++) {
            b bVar = (b) this.mImageViews.get(i);
            if (!bVar.f) {
                return bVar;
            }
        }
        return null;
    }

    public boolean hasSpaceAvailable() {
        if (!this.mImagesToAttach) {
            for (int i = 0; i < this.mImageViews.size(); i++) {
                if (!((b) this.mImageViews.get(i)).f) {
                    return true;
                }
            }
        } else if (this.mImageAttachments.size() < 3) {
            return true;
        } else {
            for (File file : this.mImageAttachments) {
                if (file == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<File> getImageFiles() {
        return new ArrayList(this.mImageAttachments);
    }

    public void onClick(View view) {
        if (view.getId() == i.convo_image_attachment_remove_button) {
            b bVar = (b) view.getTag();
            if (bVar != null) {
                removeImage(bVar);
                if (this.mCallback != null) {
                    this.mCallback.onRemove();
                }
            }
        }
    }
}
