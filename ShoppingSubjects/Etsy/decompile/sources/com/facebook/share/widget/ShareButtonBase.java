package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.FacebookButtonBase;
import com.facebook.d;
import com.facebook.e;
import com.facebook.f;
import com.facebook.internal.g;
import com.facebook.share.c.a;
import com.facebook.share.internal.k;
import com.facebook.share.model.ShareContent;

public abstract class ShareButtonBase extends FacebookButtonBase {
    private boolean enabledExplicitlySet = false;
    private int requestCode = 0;
    private ShareContent shareContent;

    /* access modifiers changed from: protected */
    public abstract g<ShareContent, a> getDialog();

    protected ShareButtonBase(Context context, AttributeSet attributeSet, int i, String str, String str2) {
        super(context, attributeSet, i, 0, str, str2);
        this.requestCode = isInEditMode() ? 0 : getDefaultRequestCode();
        internalSetEnabled(false);
    }

    public ShareContent getShareContent() {
        return this.shareContent;
    }

    public void setShareContent(ShareContent shareContent2) {
        this.shareContent = shareContent2;
        if (!this.enabledExplicitlySet) {
            internalSetEnabled(canShare());
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.enabledExplicitlySet = true;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    /* access modifiers changed from: protected */
    public void setRequestCode(int i) {
        if (f.a(i)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Request code ");
            sb.append(i);
            sb.append(" cannot be within the range reserved by the Facebook SDK.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.requestCode = i;
    }

    public void registerCallback(d dVar, e<a> eVar) {
        k.a(getRequestCode(), dVar, eVar);
    }

    public void registerCallback(d dVar, e<a> eVar, int i) {
        setRequestCode(i);
        registerCallback(dVar, eVar);
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        setInternalOnClickListener(getShareOnClickListener());
    }

    /* access modifiers changed from: protected */
    public boolean canShare() {
        return getDialog().a(getShareContent());
    }

    /* access modifiers changed from: protected */
    public OnClickListener getShareOnClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                ShareButtonBase.this.callExternalOnClickListener(view);
                ShareButtonBase.this.getDialog().b(ShareButtonBase.this.getShareContent());
            }
        };
    }

    private void internalSetEnabled(boolean z) {
        setEnabled(z);
        this.enabledExplicitlySet = false;
    }
}
