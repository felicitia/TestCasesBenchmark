package com.etsy.android.uikit.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.TrackedEtsyId;
import com.etsy.android.lib.models.datatypes.TrackedObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: TrackingViewListener */
public abstract class h {
    private static final String TAG = f.a(h.class);
    private ArrayList<i> mEventTrackedObjects;
    private ArrayList<i> mTrackedObjects;

    public void onPreTrack() {
    }

    public h() {
    }

    public h(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        this(new TrackedObject(analyticsLogAttribute, obj));
    }

    public h(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        this(new TrackedEtsyId(analyticsLogAttribute, etsyId));
    }

    public h(i... iVarArr) {
        addTrackedObjects(iVarArr);
    }

    private void onPostTrack() {
        if (this.mEventTrackedObjects != null) {
            this.mEventTrackedObjects.clear();
        }
    }

    /* access modifiers changed from: protected */
    public final void trackAction(@NonNull View view, @NonNull ViewAction viewAction) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("trackAction: ");
        sb.append(viewAction.name());
        f.c(str, sb.toString());
        if (a.a().d() != null && a.a().d().c(b.bd)) {
            w a = a.a(view);
            if (a != null) {
                String viewName = getViewName(view);
                onPreTrack();
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Tracking click on ");
                sb2.append(viewName);
                sb2.append(", in class: ");
                sb2.append(view.getClass().getSimpleName());
                sb2.append(" in: ");
                sb2.append(a.b());
                f.c(str2, sb2.toString());
                a.a(viewName, viewAction, getTrackingParameters());
                onPostTrack();
            }
        }
    }

    public final void addEventTrackedAttribute(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        addTrackedObjects(new TrackedObject(analyticsLogAttribute, obj));
    }

    public final void addEventTrackedAttribute(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable EtsyId etsyId) {
        addTrackedObjects(new TrackedEtsyId(analyticsLogAttribute, etsyId));
    }

    public final void addEventTrackedObjects(i... iVarArr) {
        if (this.mEventTrackedObjects == null) {
            this.mEventTrackedObjects = new ArrayList<>(iVarArr.length);
        } else {
            this.mEventTrackedObjects.ensureCapacity(this.mEventTrackedObjects.size() + iVarArr.length);
        }
        for (i iVar : iVarArr) {
            if (iVar != null) {
                this.mEventTrackedObjects.add(iVar);
            }
        }
    }

    private void addTrackedObjects(i... iVarArr) {
        if (this.mTrackedObjects == null) {
            this.mTrackedObjects = new ArrayList<>(iVarArr.length);
        } else {
            this.mTrackedObjects.ensureCapacity(this.mTrackedObjects.size() + iVarArr.length);
        }
        for (i iVar : iVarArr) {
            if (iVar != null) {
                this.mTrackedObjects.add(iVar);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return r3.getClass().getSimpleName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return r3.getResources().getResourceName(r3.getId());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getViewName(@android.support.annotation.NonNull android.view.View r3) {
        /*
            r2 = this;
            java.lang.String r0 = TAG
            java.lang.String r1 = "getViewName"
            com.etsy.android.lib.logger.f.c(r0, r1)
            android.content.res.Resources r0 = r3.getResources()     // Catch:{ NotFoundException -> 0x0014 }
            int r1 = r3.getId()     // Catch:{ NotFoundException -> 0x0014 }
            java.lang.String r0 = r0.getResourceEntryName(r1)     // Catch:{ NotFoundException -> 0x0014 }
            goto L_0x0029
        L_0x0014:
            android.content.res.Resources r0 = r3.getResources()     // Catch:{ NotFoundException -> 0x0021 }
            int r1 = r3.getId()     // Catch:{ NotFoundException -> 0x0021 }
            java.lang.String r0 = r0.getResourceName(r1)     // Catch:{ NotFoundException -> 0x0021 }
            goto L_0x0029
        L_0x0021:
            java.lang.Class r3 = r3.getClass()
            java.lang.String r0 = r3.getSimpleName()
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.uikit.util.h.getViewName(android.view.View):java.lang.String");
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap hashMap = null;
        if (this.mTrackedObjects == null && this.mEventTrackedObjects == null) {
            return null;
        }
        if (this.mTrackedObjects != null) {
            hashMap = new HashMap(this.mTrackedObjects.size());
            Iterator it = this.mTrackedObjects.iterator();
            while (it.hasNext()) {
                HashMap trackingParameters = ((i) it.next()).getTrackingParameters();
                if (trackingParameters != null) {
                    hashMap.putAll(trackingParameters);
                }
            }
        }
        if (this.mEventTrackedObjects != null) {
            if (hashMap == null) {
                hashMap = new HashMap(this.mEventTrackedObjects.size());
            }
            Iterator it2 = this.mEventTrackedObjects.iterator();
            while (it2.hasNext()) {
                HashMap trackingParameters2 = ((i) it2.next()).getTrackingParameters();
                if (trackingParameters2 != null) {
                    hashMap.putAll(trackingParameters2);
                }
            }
        }
        return hashMap;
    }
}
