package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public class alp implements alk {
    @VisibleForTesting
    boolean a;
    @VisibleForTesting
    boolean b;
    private final Object c = new Object();
    private final alm d;
    private final Context e;
    private final zzok f;
    @Nullable
    private final JSONObject g;
    @Nullable
    private final az h;
    @Nullable
    private final aln i;
    private final ack j;
    @Nullable
    private final zzang k;
    @Nullable
    private String l;
    @Nullable
    private fp m;
    private WeakReference<View> n = null;

    public alp(Context context, alm alm, @Nullable az azVar, ack ack, @Nullable JSONObject jSONObject, @Nullable aln aln, @Nullable zzang zzang, @Nullable String str) {
        this.e = context;
        this.d = alm;
        this.h = azVar;
        this.j = ack;
        this.g = jSONObject;
        this.i = aln;
        this.k = zzang;
        this.l = str;
        this.f = new zzok(this.h);
    }

    @VisibleForTesting
    private final int a(int i2) {
        ajh.a();
        return jp.b(this.e, i2);
    }

    private final JSONObject a(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResponseConstants.WIDTH, a(rect.right - rect.left));
        jSONObject.put(ResponseConstants.HEIGHT, a(rect.bottom - rect.top));
        jSONObject.put(EtsyDialogFragment.OPT_X_BUTTON, a(rect.left));
        jSONObject.put("y", a(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private final JSONObject a(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (map == null || view == null) {
            return jSONObject2;
        }
        int[] f2 = f(view);
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view2 = (View) ((WeakReference) entry.getValue()).get();
                if (view2 != null) {
                    int[] f3 = f(view2);
                    JSONObject jSONObject3 = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    try {
                        jSONObject4.put(ResponseConstants.WIDTH, a(view2.getMeasuredWidth()));
                        jSONObject4.put(ResponseConstants.HEIGHT, a(view2.getMeasuredHeight()));
                        jSONObject4.put(EtsyDialogFragment.OPT_X_BUTTON, a(f3[0] - f2[0]));
                        jSONObject4.put("y", a(f3[1] - f2[1]));
                        jSONObject4.put("relative_to", "ad_view");
                        jSONObject3.put("frame", jSONObject4);
                        Rect rect = new Rect();
                        if (view2.getLocalVisibleRect(rect)) {
                            jSONObject = a(rect);
                        } else {
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put(ResponseConstants.WIDTH, 0);
                            jSONObject5.put(ResponseConstants.HEIGHT, 0);
                            jSONObject5.put(EtsyDialogFragment.OPT_X_BUTTON, a(f3[0] - f2[0]));
                            jSONObject5.put("y", a(f3[1] - f2[1]));
                            jSONObject5.put("relative_to", "ad_view");
                            jSONObject = jSONObject5;
                        }
                        jSONObject3.put("visible_bounds", jSONObject);
                        if (view2 instanceof TextView) {
                            TextView textView = (TextView) view2;
                            jSONObject3.put(ResponseConstants.TEXT_COLOR, textView.getCurrentTextColor());
                            jSONObject3.put("font_size", (double) textView.getTextSize());
                            jSONObject3.put("text", textView.getText());
                        }
                        jSONObject2.put((String) entry.getKey(), jSONObject3);
                    } catch (JSONException unused) {
                        gv.e("Unable to get asset views information");
                    }
                }
            }
        }
        return jSONObject2;
    }

    private final void a(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.g);
            if (jSONObject2 != null) {
                jSONObject7.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject7.put("ad_view_signal", jSONObject);
            }
            if (jSONObject5 != null) {
                jSONObject7.put("click_signal", jSONObject5);
            }
            if (jSONObject3 != null) {
                jSONObject7.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject7.put("lock_screen_signal", jSONObject4);
            }
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("asset_id", str);
            jSONObject8.put("template", this.i.zzkb());
            ao.g();
            jSONObject8.put("is_privileged_process", hj.e());
            boolean z = true;
            if (((Boolean) ajh.f().a(akl.ck)).booleanValue() && this.f.zzjw() != null && this.g.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject8.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject8.put("timestamp", ao.l().currentTimeMillis());
            jSONObject8.put("has_custom_click_handler", this.d.zzr(this.i.getCustomTemplateId()) != null);
            String str2 = "has_custom_click_handler";
            if (this.d.zzr(this.i.getCustomTemplateId()) == null) {
                z = false;
            }
            jSONObject7.put(str2, z);
            try {
                JSONObject optJSONObject = this.g.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                jSONObject8.put("click_signals", this.j.a().a(this.e, optJSONObject.optString("click_string"), view));
            } catch (Exception e2) {
                gv.b("Exception obtaining click signals", e2);
            }
            jSONObject7.put("click", jSONObject8);
            if (jSONObject6 != null) {
                jSONObject7.put("provided_signals", jSONObject6);
            }
            jSONObject7.put("ads_id", this.l);
            kg.a(this.h.b(jSONObject7), "NativeAdEngineImpl.performClick");
        } catch (JSONException e3) {
            gv.b("Unable to create click JSON.", e3);
        }
    }

    private final boolean a(String str) {
        JSONObject optJSONObject = this.g == null ? null : this.g.optJSONObject("allow_pub_event_reporting");
        if (optJSONObject == null) {
            return false;
        }
        return optJSONObject.optBoolean(str, false);
    }

    private final boolean a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (this.a) {
            return true;
        }
        this.a = true;
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.g);
            jSONObject6.put("ads_id", this.l);
            if (jSONObject2 != null) {
                jSONObject6.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject6.put("ad_view_signal", jSONObject);
            }
            if (jSONObject3 != null) {
                jSONObject6.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject6.put("lock_screen_signal", jSONObject4);
            }
            if (jSONObject5 != null) {
                jSONObject6.put("provided_signals", jSONObject5);
            }
            kg.a(this.h.c(jSONObject6), "NativeAdEngineImpl.recordImpression");
            this.d.zza((alk) this);
            this.d.zzbv();
            j();
            return true;
        } catch (JSONException e2) {
            gv.b("Unable to create impression JSON.", e2);
            return false;
        }
    }

    private static boolean e(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
    }

    @VisibleForTesting
    private static int[] f(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    private final JSONObject g(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (view == null) {
            return jSONObject2;
        }
        try {
            int[] f2 = f(view);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(ResponseConstants.WIDTH, a(view.getMeasuredWidth()));
            jSONObject3.put(ResponseConstants.HEIGHT, a(view.getMeasuredHeight()));
            jSONObject3.put(EtsyDialogFragment.OPT_X_BUTTON, a(f2[0]));
            jSONObject3.put("y", a(f2[1]));
            jSONObject3.put("relative_to", "window");
            jSONObject2.put("frame", jSONObject3);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                jSONObject = a(rect);
            } else {
                jSONObject = new JSONObject();
                jSONObject.put(ResponseConstants.WIDTH, 0);
                jSONObject.put(ResponseConstants.HEIGHT, 0);
                jSONObject.put(EtsyDialogFragment.OPT_X_BUTTON, a(f2[0]));
                jSONObject.put("y", a(f2[1]));
                jSONObject.put("relative_to", "window");
            }
            jSONObject2.put("visible_bounds", jSONObject);
            return jSONObject2;
        } catch (Exception unused) {
            gv.e("Unable to get native ad view bounding box");
            return jSONObject2;
        }
    }

    private static JSONObject h(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            ao.e();
            jSONObject.put("contained_in_scroll_view", hd.d(view) != -1);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private final JSONObject i(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        String str = "can_show_on_lock_screen";
        try {
            ao.e();
            jSONObject.put(str, hd.c(view));
            ao.e();
            jSONObject.put("is_keyguard_locked", hd.j(this.e));
            return jSONObject;
        } catch (JSONException unused) {
            gv.e("Unable to get lock screen information");
            return jSONObject;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        r1.addRule(11);
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View a(android.view.View.OnClickListener r7, boolean r8) {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.aln r0 = r6.i
            com.google.android.gms.internal.ads.zzoj r0 = r0.zzkc()
            if (r0 != 0) goto L_0x000a
            r7 = 0
            return r7
        L_0x000a:
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r2 = -2
            r1.<init>(r2, r2)
            if (r8 != 0) goto L_0x0038
            int r8 = r0.zzju()
            r2 = 9
            r3 = 10
            if (r8 == 0) goto L_0x0032
            r4 = 12
            r5 = 11
            switch(r8) {
                case 2: goto L_0x002e;
                case 3: goto L_0x002a;
                default: goto L_0x0023;
            }
        L_0x0023:
            r1.addRule(r3)
        L_0x0026:
            r1.addRule(r5)
            goto L_0x0038
        L_0x002a:
            r1.addRule(r4)
            goto L_0x0035
        L_0x002e:
            r1.addRule(r4)
            goto L_0x0026
        L_0x0032:
            r1.addRule(r3)
        L_0x0035:
            r1.addRule(r2)
        L_0x0038:
            com.google.android.gms.internal.ads.zzom r8 = new com.google.android.gms.internal.ads.zzom
            android.content.Context r2 = r6.e
            r8.<init>(r2, r0, r1)
            r8.setOnClickListener(r7)
            com.google.android.gms.internal.ads.akb<java.lang.String> r7 = com.google.android.gms.internal.ads.akl.ce
            com.google.android.gms.internal.ads.akj r0 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r7 = r0.a(r7)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r8.setContentDescription(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.alp.a(android.view.View$OnClickListener, boolean):android.view.View");
    }

    public final void a(MotionEvent motionEvent) {
        this.j.a(motionEvent);
    }

    public void a(View view) {
        if (((Boolean) ajh.f().a(akl.ck)).booleanValue()) {
            if (!this.g.optBoolean("custom_one_point_five_click_enabled", false)) {
                gv.e("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
                return;
            }
            zzok zzok = this.f;
            if (view != null) {
                view.setOnClickListener(zzok);
                view.setClickable(true);
                zzok.zzbhq = new WeakReference<>(view);
            }
        }
    }

    public final void a(View view, ali ali) {
        if (!b(view, ali)) {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            ((FrameLayout) view).removeAllViews();
            if (this.i instanceof alo) {
                alo alo = (alo) this.i;
                if (alo.getImages() != null && alo.getImages().size() > 0) {
                    Object obj = alo.getImages().get(0);
                    zzpw zzh = obj instanceof IBinder ? zzpx.zzh((IBinder) obj) : null;
                    if (zzh != null) {
                        try {
                            IObjectWrapper zzjy = zzh.zzjy();
                            if (zzjy != null) {
                                Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
                                ImageView imageView = new ImageView(this.e);
                                imageView.setImageDrawable(drawable);
                                imageView.setScaleType(ScaleType.CENTER_INSIDE);
                                ((FrameLayout) view).addView(imageView, layoutParams);
                            }
                        } catch (RemoteException unused) {
                            gv.e("Could not get drawable from image");
                        }
                    }
                }
            }
        }
    }

    public final void a(View view, String str, @Nullable Bundle bundle, Map<String, WeakReference<View>> map, View view2) {
        JSONObject jSONObject;
        JSONObject a2 = a(map, view2);
        JSONObject g2 = g(view2);
        JSONObject h2 = h(view2);
        JSONObject i2 = i(view2);
        JSONObject jSONObject2 = null;
        try {
            JSONObject a3 = ao.e().a(bundle, (JSONObject) null);
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("click_point", a3);
                jSONObject3.put("asset_id", str);
                jSONObject = jSONObject3;
            } catch (Exception e2) {
                e = e2;
                jSONObject2 = jSONObject3;
                gv.b("Error occurred while grabbing click signals.", e);
                jSONObject = jSONObject2;
                a(view, g2, a2, h2, i2, str, jSONObject, null);
            }
        } catch (Exception e3) {
            e = e3;
            gv.b("Error occurred while grabbing click signals.", e);
            jSONObject = jSONObject2;
            a(view, g2, a2, h2, i2, str, jSONObject, null);
        }
        a(view, g2, a2, h2, i2, str, jSONObject, null);
    }

    public void a(View view, Map<String, WeakReference<View>> map) {
        a(g(view), a(map, view), h(view), i(view), (JSONObject) null);
    }

    public void a(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        String str;
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (map != null) {
            synchronized (map) {
                for (Entry entry : map.entrySet()) {
                    if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                        a(view, (String) entry.getKey(), bundle, map, view2);
                        return;
                    }
                }
            }
        }
        if ("6".equals(this.i.zzkb())) {
            str = "3099";
        } else if ("2".equals(this.i.zzkb())) {
            str = "2099";
        } else {
            if ("1".equals(this.i.zzkb())) {
                a(view, "1099", bundle, map, view2);
            }
            return;
        }
        a(view, str, bundle, map, view2);
    }

    public void a(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        if (((Boolean) ajh.f().a(akl.cb)).booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            if (map != null) {
                synchronized (map) {
                    for (Entry value : map.entrySet()) {
                        View view2 = (View) ((WeakReference) value.getValue()).get();
                        if (view2 != null) {
                            view2.setOnTouchListener(onTouchListener);
                            view2.setClickable(true);
                            view2.setOnClickListener(onClickListener);
                        }
                    }
                }
            }
            if (map2 != null) {
                synchronized (map2) {
                    for (Entry value2 : map2.entrySet()) {
                        View view3 = (View) ((WeakReference) value2.getValue()).get();
                        if (view3 != null) {
                            view3.setOnTouchListener(onTouchListener);
                        }
                    }
                }
            }
        }
    }

    public void a(zzro zzro) {
        if (((Boolean) ajh.f().a(akl.ck)).booleanValue()) {
            if (!this.g.optBoolean("custom_one_point_five_click_enabled", false)) {
                gv.e("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            } else {
                this.f.zza(zzro);
            }
        }
    }

    public final void a(Map<String, WeakReference<View>> map) {
        if (this.i.zzkd() != null) {
            if ("2".equals(this.i.zzkb())) {
                ao.i().l().a(this.d.getAdUnitId(), this.i.zzkb(), map.containsKey("2011"));
            } else if ("1".equals(this.i.zzkb())) {
                ao.i().l().a(this.d.getAdUnitId(), this.i.zzkb(), map.containsKey("1009"));
            }
        }
    }

    public boolean a() {
        zzoj zzkc = this.i.zzkc();
        return zzkc != null && zzkc.zzjv();
    }

    public final boolean a(Bundle bundle) {
        if (!a("impression_reporting")) {
            gv.c("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
            return false;
        }
        return a((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, ao.e().a(bundle, (JSONObject) null));
    }

    public final void b(Bundle bundle) {
        if (bundle == null) {
            gv.b("Click data is null. No click is reported.");
        } else if (!a("click_reporting")) {
            gv.c("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        } else {
            a(null, null, null, null, null, bundle.getBundle("click_signal").getString("asset_id"), null, ao.e().a(bundle, (JSONObject) null));
        }
    }

    public final void b(View view) {
        if (((Boolean) ajh.f().a(akl.bG)).booleanValue() && this.j != null) {
            acg a2 = this.j.a();
            if (a2 != null) {
                a2.a(view);
            }
        }
    }

    public void b(View view, Map<String, WeakReference<View>> map) {
        if (!((Boolean) ajh.f().a(akl.ca)).booleanValue()) {
            view.setOnTouchListener(null);
            view.setClickable(false);
            view.setOnClickListener(null);
            if (map != null) {
                synchronized (map) {
                    for (Entry value : map.entrySet()) {
                        View view2 = (View) ((WeakReference) value.getValue()).get();
                        if (view2 != null) {
                            view2.setOnTouchListener(null);
                            view2.setClickable(false);
                            view2.setOnClickListener(null);
                        }
                    }
                }
            }
        }
    }

    public boolean b() {
        return this.g != null && this.g.optBoolean("allow_pub_owned_ad_view", false);
    }

    public final boolean b(View view, ali ali) {
        LayoutParams layoutParams = new LayoutParams(-2, -2, 17);
        View zzkd = this.i.zzkd();
        if (zzkd == null) {
            return false;
        }
        ViewParent parent = zzkd.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(zzkd);
        }
        FrameLayout frameLayout = (FrameLayout) view;
        frameLayout.removeAllViews();
        frameLayout.addView(zzkd, layoutParams);
        this.d.zza(ali);
        return true;
    }

    public void c() {
        if (((Boolean) ajh.f().a(akl.ck)).booleanValue()) {
            if (!this.g.optBoolean("custom_one_point_five_click_enabled", false)) {
                gv.e("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            } else {
                this.f.cancelUnconfirmedClick();
            }
        }
    }

    public final void c(Bundle bundle) {
        if (bundle == null) {
            gv.b("Touch event data is null. No touch event is reported.");
        } else if (!a("touch_reporting")) {
            gv.c("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
        } else {
            this.j.a().a((int) bundle.getFloat(EtsyDialogFragment.OPT_X_BUTTON), (int) bundle.getFloat("y"), bundle.getInt("duration_ms"));
        }
    }

    public final void c(View view) {
        this.n = new WeakReference<>(view);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(android.view.View r4, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.c
            monitor-enter(r0)
            boolean r1 = r3.a     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0009:
            boolean r1 = e(r4)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0014
            r3.a(r4, r5)     // Catch:{ all -> 0x005e }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0014:
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.akl.cj     // Catch:{ all -> 0x005e }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x005e }
            java.lang.Object r1 = r2.a(r1)     // Catch:{ all -> 0x005e }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x005e }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x005c
            if (r5 == 0) goto L_0x005c
            monitor-enter(r5)     // Catch:{ all -> 0x005e }
            java.util.Set r1 = r5.entrySet()     // Catch:{ all -> 0x0059 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0059 }
        L_0x0031:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0059 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x0059 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0059 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x0059 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0059 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0031
            boolean r2 = e(r2)     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0031
            r3.a(r4, r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0057:
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            goto L_0x005c
        L_0x0059:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005c:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x005e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.alp.c(android.view.View, java.util.Map):void");
    }

    public void d() {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (!this.b) {
            this.b = true;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ad", this.g);
                jSONObject.put("ads_id", this.l);
                kg.a(this.h.d(jSONObject), "NativeAdEngineImpl.recordDownloadedImpression");
            } catch (JSONException e2) {
                ka.b("", e2);
            }
        }
    }

    public final void d(View view) {
        this.d.zzi(view);
    }

    public nn g() throws zzarg {
        if (this.g == null || this.g.optJSONObject("overlay") == null) {
            return null;
        }
        ao.f();
        Context context = this.e;
        zzjn zzf = zzjn.zzf(this.e);
        nn a2 = nt.a(context, ot.a(zzf), zzf.zzarb, false, false, this.j, this.k, null, null, null, ahh.a());
        if (a2 != null) {
            a2.getView().setVisibility(8);
            new alr(a2).a(this.h);
        }
        return a2;
    }

    public void h() {
        this.h.a();
    }

    public void i() {
        this.d.zzct();
    }

    public void j() {
        this.d.zzcr();
    }

    public void k() {
        this.d.zzcs();
    }

    public final View l() {
        if (this.n != null) {
            return (View) this.n.get();
        }
        return null;
    }

    public final Context m() {
        return this.e;
    }

    @Nullable
    public final fp n() {
        if (!ao.B().c(this.e)) {
            return null;
        }
        if (this.m == null) {
            this.m = new fp(this.e, this.d.getAdUnitId());
        }
        return this.m;
    }
}
