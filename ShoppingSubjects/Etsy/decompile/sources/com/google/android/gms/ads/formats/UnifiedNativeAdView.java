package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.zzqa;

public final class UnifiedNativeAdView extends FrameLayout {
    private final FrameLayout zzvp;
    private final zzqa zzvq = zzbf();

    public UnifiedNativeAdView(Context context) {
        super(context);
        this.zzvp = zzb(context);
    }

    public UnifiedNativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzvp = zzb(context);
    }

    public UnifiedNativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzvp = zzb(context);
    }

    @TargetApi(21)
    public UnifiedNativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zzvp = zzb(context);
    }

    private final void zza(String str, View view) {
        try {
            this.zzvq.zzb(str, ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            ka.b("Unable to call setAssetView on delegate", e);
        }
    }

    private final FrameLayout zzb(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    private final zzqa zzbf() {
        Preconditions.checkNotNull(this.zzvp, "createDelegate must be called after overlayFrame has been created");
        if (isInEditMode()) {
            return null;
        }
        return ajh.b().a(this.zzvp.getContext(), (FrameLayout) this, this.zzvp);
    }

    private final View zzp(String str) {
        try {
            IObjectWrapper zzak = this.zzvq.zzak(str);
            if (zzak != null) {
                return (View) ObjectWrapper.unwrap(zzak);
            }
        } catch (RemoteException e) {
            ka.b("Unable to call getAssetView on delegate", e);
        }
        return null;
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzvp);
    }

    public final void bringChildToFront(View view) {
        super.bringChildToFront(view);
        if (this.zzvp != view) {
            super.bringChildToFront(this.zzvp);
        }
    }

    public final void destroy() {
        try {
            this.zzvq.destroy();
        } catch (RemoteException e) {
            ka.b("Unable to destroy native ad view", e);
        }
    }

    public final AdChoicesView getAdChoicesView() {
        View zzp = zzp("3011");
        if (zzp instanceof AdChoicesView) {
            return (AdChoicesView) zzp;
        }
        return null;
    }

    public final View getAdvertiserView() {
        return zzp("3005");
    }

    public final View getBodyView() {
        return zzp("3004");
    }

    public final View getCallToActionView() {
        return zzp("3002");
    }

    public final View getHeadlineView() {
        return zzp("3001");
    }

    public final View getIconView() {
        return zzp("3003");
    }

    public final View getImageView() {
        return zzp("3008");
    }

    public final MediaView getMediaView() {
        View zzp = zzp("3010");
        if (zzp instanceof MediaView) {
            return (MediaView) zzp;
        }
        if (zzp != null) {
            ka.b("View is not an instance of MediaView");
        }
        return null;
    }

    public final View getPriceView() {
        return zzp("3007");
    }

    public final View getStarRatingView() {
        return zzp("3009");
    }

    public final View getStoreView() {
        return zzp("3006");
    }

    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.zzvq != null) {
            try {
                this.zzvq.zzb(ObjectWrapper.wrap(view), i);
            } catch (RemoteException e) {
                ka.b("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    public final void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzvp);
    }

    public final void removeView(View view) {
        if (this.zzvp != view) {
            super.removeView(view);
        }
    }

    public final void setAdChoicesView(AdChoicesView adChoicesView) {
        zza("3011", adChoicesView);
    }

    public final void setAdvertiserView(View view) {
        zza("3005", view);
    }

    public final void setBodyView(View view) {
        zza("3004", view);
    }

    public final void setCallToActionView(View view) {
        zza("3002", view);
    }

    public final void setClickConfirmingView(View view) {
        try {
            this.zzvq.zzc(ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            ka.b("Unable to call setClickConfirmingView on delegate", e);
        }
    }

    public final void setHeadlineView(View view) {
        zza("3001", view);
    }

    public final void setIconView(View view) {
        zza("3003", view);
    }

    public final void setImageView(View view) {
        zza("3008", view);
    }

    public final void setMediaView(MediaView mediaView) {
        zza("3010", mediaView);
    }

    public final void setNativeAd(h hVar) {
        try {
            this.zzvq.zza((IObjectWrapper) hVar.k());
        } catch (RemoteException e) {
            ka.b("Unable to call setNativeAd on delegate", e);
        }
    }

    public final void setPriceView(View view) {
        zza("3007", view);
    }

    public final void setStarRatingView(View view) {
        zza("3009", view);
    }

    public final void setStoreView(View view) {
        zza("3006", view);
    }
}
