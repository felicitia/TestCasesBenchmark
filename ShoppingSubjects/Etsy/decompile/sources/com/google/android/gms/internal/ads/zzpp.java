package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@bu
public final class zzpp extends zzqg implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    @VisibleForTesting
    static final String[] zzbjs = {"2011", "1009", "3010"};
    private final Object mLock = new Object();
    @Nullable
    @VisibleForTesting
    private alk zzbij;
    @Nullable
    @VisibleForTesting
    private View zzbjx;
    @VisibleForTesting
    private Point zzbjz = new Point();
    @VisibleForTesting
    private Point zzbka = new Point();
    @Nullable
    @VisibleForTesting
    private WeakReference<zzfp> zzbkb = new WeakReference<>(null);
    private final WeakReference<View> zzbke;
    private final Map<String, WeakReference<View>> zzbkf = new HashMap();
    private final Map<String, WeakReference<View>> zzbkg = new HashMap();
    private final Map<String, WeakReference<View>> zzbkh = new HashMap();

    public zzpp(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        ao.A();
        lm.a(view, (OnGlobalLayoutListener) this);
        ao.A();
        lm.a(view, (OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzbke = new WeakReference<>(view);
        for (Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            View view2 = (View) entry.getValue();
            if (view2 != null) {
                this.zzbkf.put(str, new WeakReference(view2));
                if (!"1098".equals(str) && !"3011".equals(str)) {
                    view2.setOnTouchListener(this);
                    view2.setClickable(true);
                    view2.setOnClickListener(this);
                }
            }
        }
        this.zzbkh.putAll(this.zzbkf);
        for (Entry entry2 : hashMap2.entrySet()) {
            View view3 = (View) entry2.getValue();
            if (view3 != null) {
                this.zzbkg.put((String) entry2.getKey(), new WeakReference(view3));
                view3.setOnTouchListener(this);
            }
        }
        this.zzbkh.putAll(this.zzbkg);
        akl.a(view.getContext());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.alp r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            java.lang.String[] r1 = zzbjs     // Catch:{ all -> 0x003b }
            int r2 = r1.length     // Catch:{ all -> 0x003b }
            r3 = 0
        L_0x0007:
            if (r3 >= r2) goto L_0x001f
            r4 = r1[r3]     // Catch:{ all -> 0x003b }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5 = r6.zzbkh     // Catch:{ all -> 0x003b }
            java.lang.Object r4 = r5.get(r4)     // Catch:{ all -> 0x003b }
            java.lang.ref.WeakReference r4 = (java.lang.ref.WeakReference) r4     // Catch:{ all -> 0x003b }
            if (r4 == 0) goto L_0x001c
            java.lang.Object r1 = r4.get()     // Catch:{ all -> 0x003b }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x003b }
            goto L_0x0020
        L_0x001c:
            int r3 = r3 + 1
            goto L_0x0007
        L_0x001f:
            r1 = 0
        L_0x0020:
            boolean r2 = r1 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x003b }
            if (r2 != 0) goto L_0x0029
            r7.i()     // Catch:{ all -> 0x003b }
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x0029:
            com.google.android.gms.internal.ads.alz r2 = new com.google.android.gms.internal.ads.alz     // Catch:{ all -> 0x003b }
            r2.<init>(r6, r1)     // Catch:{ all -> 0x003b }
            boolean r3 = r7 instanceof com.google.android.gms.internal.ads.alj     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0036
            r7.b(r1, r2)     // Catch:{ all -> 0x003b }
            goto L_0x0039
        L_0x0036:
            r7.a(r1, r2)     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x003b:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.zza(com.google.android.gms.internal.ads.alp):void");
    }

    /* access modifiers changed from: private */
    public final boolean zza(String[] strArr) {
        for (String str : strArr) {
            if (this.zzbkf.get(str) != null) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (this.zzbkg.get(str2) != null) {
                return false;
            }
        }
        return false;
    }

    private final void zzl(@Nullable View view) {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                alk f = this.zzbij instanceof alj ? ((alj) this.zzbij).f() : this.zzbij;
                if (f != null) {
                    f.c(view);
                }
            }
        }
    }

    @VisibleForTesting
    private final int zzv(int i) {
        int b;
        synchronized (this.mLock) {
            ajh.a();
            b = jp.b(this.zzbij.m(), i);
        }
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.alk r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            return
        L_0x0009:
            java.lang.ref.WeakReference<android.view.View> r1 = r8.zzbke     // Catch:{ all -> 0x0090 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0090 }
            r7 = r1
            android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x0090 }
            if (r7 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            return
        L_0x0016:
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x0090 }
            r5.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "x"
            android.graphics.Point r2 = r8.zzbjz     // Catch:{ all -> 0x0090 }
            int r2 = r2.x     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "y"
            android.graphics.Point r2 = r8.zzbjz     // Catch:{ all -> 0x0090 }
            int r2 = r2.y     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "start_x"
            android.graphics.Point r2 = r8.zzbka     // Catch:{ all -> 0x0090 }
            int r2 = r2.x     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "start_y"
            android.graphics.Point r2 = r8.zzbka     // Catch:{ all -> 0x0090 }
            int r2 = r2.y     // Catch:{ all -> 0x0090 }
            int r2 = r8.zzv(r2)     // Catch:{ all -> 0x0090 }
            float r2 = (float) r2     // Catch:{ all -> 0x0090 }
            r5.putFloat(r1, r2)     // Catch:{ all -> 0x0090 }
            android.view.View r1 = r8.zzbjx     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x0087
            android.view.View r1 = r8.zzbjx     // Catch:{ all -> 0x0090 }
            boolean r1 = r1.equals(r9)     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x0087
            com.google.android.gms.internal.ads.alk r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            boolean r1 = r1 instanceof com.google.android.gms.internal.ads.alj     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x0080
            com.google.android.gms.internal.ads.alk r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.alj r1 = (com.google.android.gms.internal.ads.alj) r1     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.alk r1 = r1.f()     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x008e
            com.google.android.gms.internal.ads.alk r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.alj r1 = (com.google.android.gms.internal.ads.alj) r1     // Catch:{ all -> 0x0090 }
            com.google.android.gms.internal.ads.alk r2 = r1.f()     // Catch:{ all -> 0x0090 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzbkh     // Catch:{ all -> 0x0090 }
        L_0x007b:
            r3 = r9
            r2.a(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0090 }
            goto L_0x008e
        L_0x0080:
            com.google.android.gms.internal.ads.alk r2 = r8.zzbij     // Catch:{ all -> 0x0090 }
            java.lang.String r4 = "1007"
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r6 = r8.zzbkh     // Catch:{ all -> 0x0090 }
            goto L_0x007b
        L_0x0087:
            com.google.android.gms.internal.ads.alk r1 = r8.zzbij     // Catch:{ all -> 0x0090 }
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r2 = r8.zzbkh     // Catch:{ all -> 0x0090 }
            r1.a(r9, r2, r5, r7)     // Catch:{ all -> 0x0090 }
        L_0x008e:
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            return
        L_0x0090:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpp.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                View view = (View) this.zzbke.get();
                if (view != null) {
                    this.zzbij.c(view, this.zzbkh);
                }
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                View view = (View) this.zzbke.get();
                if (view != null) {
                    this.zzbij.c(view, this.zzbkh);
                }
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return false;
            }
            View view2 = (View) this.zzbke.get();
            if (view2 == null) {
                return false;
            }
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
            this.zzbjz = point;
            if (motionEvent.getAction() == 0) {
                this.zzbka = point;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation((float) point.x, (float) point.y);
            this.zzbij.a(obtain);
            obtain.recycle();
            return false;
        }
    }

    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        int i;
        View view;
        synchronized (this.mLock) {
            ViewGroup viewGroup = null;
            zzl(null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(unwrap instanceof alp)) {
                gv.e("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            alp alp = (alp) unwrap;
            if (!alp.b()) {
                gv.c("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            View view2 = (View) this.zzbke.get();
            if (!(this.zzbij == null || view2 == null)) {
                if (((Boolean) ajh.f().a(akl.bZ)).booleanValue()) {
                    this.zzbij.b(view2, this.zzbkh);
                }
            }
            synchronized (this.mLock) {
                i = 0;
                if (this.zzbij instanceof alp) {
                    alp alp2 = (alp) this.zzbij;
                    View view3 = (View) this.zzbke.get();
                    if (!(alp2 == null || alp2.m() == null || view3 == null || !ao.B().c(view3.getContext()))) {
                        fp n = alp2.n();
                        if (n != null) {
                            n.a(false);
                        }
                        zzfp zzfp = (zzfp) this.zzbkb.get();
                        if (!(zzfp == null || n == null)) {
                            zzfp.zzb(n);
                        }
                    }
                }
            }
            if (!(this.zzbij instanceof alj) || !((alj) this.zzbij).e()) {
                this.zzbij = alp;
                if (alp instanceof alj) {
                    ((alj) alp).a((alk) null);
                }
            } else {
                ((alj) this.zzbij).a((alk) alp);
            }
            String[] strArr = {"1098", "3011"};
            while (true) {
                if (i >= 2) {
                    view = null;
                    break;
                }
                WeakReference weakReference = (WeakReference) this.zzbkh.get(strArr[i]);
                if (weakReference != null) {
                    view = (View) weakReference.get();
                    break;
                }
                i++;
            }
            if (view == null) {
                gv.e("Ad choices asset view is not provided.");
            } else {
                if (view instanceof ViewGroup) {
                    viewGroup = (ViewGroup) view;
                }
                if (viewGroup != null) {
                    this.zzbjx = alp.a((OnClickListener) this, true);
                    if (this.zzbjx != null) {
                        this.zzbkh.put("1007", new WeakReference(this.zzbjx));
                        this.zzbkf.put("1007", new WeakReference(this.zzbjx));
                        viewGroup.removeAllViews();
                        viewGroup.addView(this.zzbjx);
                    }
                }
            }
            alp.a(view2, this.zzbkf, this.zzbkg, (OnTouchListener) this, (OnClickListener) this);
            hd.a.post(new aly(this, alp));
            zzl(view2);
            this.zzbij.b(view2);
            synchronized (this.mLock) {
                if (this.zzbij instanceof alp) {
                    alp alp3 = (alp) this.zzbij;
                    View view4 = (View) this.zzbke.get();
                    if (!(alp3 == null || alp3.m() == null || view4 == null || !ao.B().c(view4.getContext()))) {
                        zzfp zzfp2 = (zzfp) this.zzbkb.get();
                        if (zzfp2 == null) {
                            zzfp2 = new zzfp(view4.getContext(), view4);
                            this.zzbkb = new WeakReference<>(zzfp2);
                        }
                        zzfp2.zza((afn) alp3.n());
                    }
                }
            }
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzbij.a((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }
}
