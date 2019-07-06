package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.FacebookButtonBase;
import com.facebook.d;
import com.facebook.e;
import com.facebook.f;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.share.a;
import com.facebook.share.a.C0123a;
import com.facebook.share.b;
import com.facebook.share.model.ShareContent;

public final class DeviceShareButton extends FacebookButtonBase {
    private a dialog;
    private boolean enabledExplicitlySet;
    private int requestCode;
    private ShareContent shareContent;

    public DeviceShareButton(Context context) {
        this(context, null, 0);
    }

    public DeviceShareButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private DeviceShareButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0, "fb_device_share_button_create", "fb_device_share_button_did_tap");
        this.requestCode = 0;
        this.enabledExplicitlySet = false;
        this.dialog = null;
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

    public void registerCallback(d dVar, e<C0123a> eVar) {
        getDialog().a(dVar, eVar);
    }

    public void registerCallback(d dVar, e<C0123a> eVar, int i) {
        setRequestCode(i);
        getDialog().a(dVar, eVar, i);
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        setInternalOnClickListener(getShareOnClickListener());
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return b.a.com_facebook_button_share;
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return RequestCodeOffset.Share.toRequestCode();
    }

    /* access modifiers changed from: protected */
    public OnClickListener getShareOnClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                DeviceShareButton.this.callExternalOnClickListener(view);
                DeviceShareButton.this.getDialog().b(DeviceShareButton.this.getShareContent());
            }
        };
    }

    private void internalSetEnabled(boolean z) {
        setEnabled(z);
        this.enabledExplicitlySet = false;
    }

    private void setRequestCode(int i) {
        if (f.a(i)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Request code ");
            sb.append(i);
            sb.append(" cannot be within the range reserved by the Facebook SDK.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.requestCode = i;
    }

    private boolean canShare() {
        return new a(getActivity()).a(getShareContent());
    }

    /* access modifiers changed from: private */
    public a getDialog() {
        if (this.dialog != null) {
            return this.dialog;
        }
        if (getFragment() != null) {
            this.dialog = new a(getFragment());
        } else if (getNativeFragment() != null) {
            this.dialog = new a(getNativeFragment());
        } else {
            this.dialog = new a(getActivity());
        }
        return this.dialog;
    }
}
