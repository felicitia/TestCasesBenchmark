package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzpx;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class n {
    @Nullable
    public static View a(@Nullable ga gaVar) {
        if (gaVar == null) {
            gv.c("AdState is null");
            return null;
        } else if (b(gaVar) && gaVar.b != null) {
            return gaVar.b.getView();
        } else {
            try {
                IObjectWrapper view = gaVar.p != null ? gaVar.p.getView() : null;
                if (view != null) {
                    return (View) ObjectWrapper.unwrap(view);
                }
                gv.e("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                gv.c("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    @VisibleForTesting
    static ae<nn> a(@Nullable zzxz zzxz, @Nullable zzyc zzyc, c cVar) {
        return new s(zzxz, cVar, zzyc);
    }

    @Nullable
    private static zzpw a(Object obj) {
        if (obj instanceof IBinder) {
            return zzpx.zzh((IBinder) obj);
        }
        return null;
    }

    private static String a(@Nullable Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            gv.e("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        String valueOf2 = String.valueOf(encodeToString);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    @VisibleForTesting
    private static String a(@Nullable zzpw zzpw) {
        if (zzpw == null) {
            gv.e("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzpw.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException unused) {
            gv.e("Unable to get image uri. Trying data uri next");
        }
        return b(zzpw);
    }

    private static JSONObject a(@Nullable Bundle bundle, String str) throws JSONException {
        String valueOf;
        String str2;
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str3 = (String) keys.next();
            if (bundle.containsKey(str3)) {
                if (ResponseConstants.IMAGE.equals(jSONObject2.getString(str3))) {
                    Object obj = bundle.get(str3);
                    if (obj instanceof Bitmap) {
                        valueOf = a((Bitmap) obj);
                    } else {
                        str2 = "Invalid type. An image type extra should return a bitmap";
                        gv.e(str2);
                    }
                } else if (bundle.get(str3) instanceof Bitmap) {
                    str2 = "Invalid asset type. Bitmap should be returned only for image type";
                    gv.e(str2);
                } else {
                    valueOf = String.valueOf(bundle.get(str3));
                }
                jSONObject.put(str3, valueOf);
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void a(zzoo zzoo, String str, nn nnVar, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ResponseConstants.HEADLINE, zzoo.getHeadline());
            jSONObject.put("body", zzoo.getBody());
            jSONObject.put("call_to_action", zzoo.getCallToAction());
            jSONObject.put(ResponseConstants.PRICE, zzoo.getPrice());
            jSONObject.put("star_rating", String.valueOf(zzoo.getStarRating()));
            jSONObject.put("store", zzoo.getStore());
            jSONObject.put(ResponseConstants.ICON, a(zzoo.zzjz()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoo.getImages();
            if (images != null) {
                for (Object a : images) {
                    jSONArray.put(a(a(a)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", a(zzoo.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "2");
            nnVar.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            gv.c("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void a(zzoq zzoq, String str, nn nnVar, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ResponseConstants.HEADLINE, zzoq.getHeadline());
            jSONObject.put("body", zzoq.getBody());
            jSONObject.put("call_to_action", zzoq.getCallToAction());
            jSONObject.put("advertiser", zzoq.getAdvertiser());
            jSONObject.put("logo", a(zzoq.zzkg()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzoq.getImages();
            if (images != null) {
                for (Object a : images) {
                    jSONArray.put(a(a(a)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", a(zzoq.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "1");
            nnVar.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            gv.c("Exception occurred when loading assets", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(com.google.android.gms.internal.ads.nn r26, com.google.android.gms.internal.ads.arf r27, java.util.concurrent.CountDownLatch r28) {
        /*
            r1 = r26
            r2 = r27
            r7 = r28
            r8 = 0
            android.view.View r3 = r26.getView()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            if (r3 != 0) goto L_0x0014
            java.lang.String r1 = "AdWebView is null"
        L_0x000f:
            com.google.android.gms.internal.ads.gv.e(r1)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            goto L_0x0132
        L_0x0014:
            r4 = 4
            r3.setVisibility(r4)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.aqy r3 = r2.b     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.util.List<java.lang.String> r3 = r3.r     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            if (r3 == 0) goto L_0x0121
            boolean r4 = r3.isEmpty()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            if (r4 == 0) goto L_0x0026
            goto L_0x0121
        L_0x0026:
            java.lang.String r4 = "/nativeExpressAssetsLoaded"
            com.google.android.gms.ads.internal.q r5 = new com.google.android.gms.ads.internal.q     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r5.<init>(r7)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r1.zza(r4, r5)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r4 = "/nativeExpressAssetsLoadingFailed"
            com.google.android.gms.ads.internal.r r5 = new com.google.android.gms.ads.internal.r     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r5.<init>(r7)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r1.zza(r4, r5)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.zzxq r4 = r2.c     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.zzxz r4 = r4.zzmo()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.zzxq r5 = r2.c     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.zzyc r5 = r5.zzmp()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r6 = "2"
            boolean r6 = r3.contains(r6)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r9 = 0
            if (r6 == 0) goto L_0x00a9
            if (r4 == 0) goto L_0x00a9
            com.google.android.gms.internal.ads.zzoo r3 = new com.google.android.gms.internal.ads.zzoo     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r11 = r4.getHeadline()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.util.List r12 = r4.getImages()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r13 = r4.getBody()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.zzpw r14 = r4.zzjz()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r15 = r4.getCallToAction()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            double r16 = r4.getStarRating()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r18 = r4.getStore()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r19 = r4.getPrice()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r20 = 0
            android.os.Bundle r21 = r4.getExtras()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r22 = 0
            com.google.android.gms.dynamic.IObjectWrapper r5 = r4.zzmw()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            if (r5 == 0) goto L_0x008c
            com.google.android.gms.dynamic.IObjectWrapper r5 = r4.zzmw()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.Object r5 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r5)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r9 = r5
            android.view.View r9 = (android.view.View) r9     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
        L_0x008c:
            r23 = r9
            com.google.android.gms.dynamic.IObjectWrapper r24 = r4.zzke()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r25 = 0
            r10 = r3
            r10.<init>(r11, r12, r13, r14, r15, r16, r18, r19, r20, r21, r22, r23, r24, r25)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.aqy r4 = r2.b     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r4 = r4.q     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.oo r5 = r26.zzuf()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.ads.internal.o r6 = new com.google.android.gms.ads.internal.o     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r6.<init>(r3, r4, r1)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
        L_0x00a5:
            r5.zza(r6)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            goto L_0x0100
        L_0x00a9:
            java.lang.String r4 = "1"
            boolean r3 = r3.contains(r4)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            if (r3 == 0) goto L_0x011d
            if (r5 == 0) goto L_0x011d
            com.google.android.gms.internal.ads.zzoq r3 = new com.google.android.gms.internal.ads.zzoq     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r11 = r5.getHeadline()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.util.List r12 = r5.getImages()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r13 = r5.getBody()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.zzpw r14 = r5.zzkg()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r15 = r5.getCallToAction()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r16 = r5.getAdvertiser()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r17 = 0
            android.os.Bundle r18 = r5.getExtras()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r19 = 0
            com.google.android.gms.dynamic.IObjectWrapper r4 = r5.zzmw()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            if (r4 == 0) goto L_0x00e6
            com.google.android.gms.dynamic.IObjectWrapper r4 = r5.zzmw()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.Object r4 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r4)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r9 = r4
            android.view.View r9 = (android.view.View) r9     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
        L_0x00e6:
            r20 = r9
            com.google.android.gms.dynamic.IObjectWrapper r21 = r5.zzke()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r22 = 0
            r10 = r3
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.aqy r4 = r2.b     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r4 = r4.q     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.oo r5 = r26.zzuf()     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.ads.internal.p r6 = new com.google.android.gms.ads.internal.p     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            r6.<init>(r3, r4, r1)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            goto L_0x00a5
        L_0x0100:
            com.google.android.gms.internal.ads.aqy r3 = r2.b     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r3 = r3.o     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            com.google.android.gms.internal.ads.aqy r2 = r2.b     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            java.lang.String r2 = r2.p     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            if (r2 == 0) goto L_0x0113
            java.lang.String r4 = "text/html"
            java.lang.String r5 = "UTF-8"
            r6 = 0
            r1.loadDataWithBaseURL(r2, r3, r4, r5, r6)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
            goto L_0x011a
        L_0x0113:
            java.lang.String r2 = "text/html"
            java.lang.String r4 = "UTF-8"
            r1.loadData(r3, r2, r4)     // Catch:{ RemoteException -> 0x012b, RuntimeException -> 0x0125 }
        L_0x011a:
            r1 = 1
            r8 = r1
            goto L_0x0132
        L_0x011d:
            java.lang.String r1 = "No matching template id and mapper"
            goto L_0x000f
        L_0x0121:
            java.lang.String r1 = "No template ids present in mediation response"
            goto L_0x000f
        L_0x0125:
            r0 = move-exception
            r1 = r0
            r28.countDown()
            throw r1
        L_0x012b:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "Unable to invoke load assets"
            com.google.android.gms.internal.ads.gv.c(r2, r1)
        L_0x0132:
            if (r8 != 0) goto L_0x0137
            r28.countDown()
        L_0x0137:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.n.a(com.google.android.gms.internal.ads.nn, com.google.android.gms.internal.ads.arf, java.util.concurrent.CountDownLatch):boolean");
    }

    private static String b(zzpw zzpw) {
        try {
            IObjectWrapper zzjy = zzpw.zzjy();
            if (zzjy == null) {
                gv.e("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
            if (drawable instanceof BitmapDrawable) {
                return a(((BitmapDrawable) drawable).getBitmap());
            }
            gv.e("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException unused) {
            gv.e("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    /* access modifiers changed from: private */
    public static void b(nn nnVar) {
        OnClickListener onClickListener = nnVar.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(nnVar.getView());
        }
    }

    public static boolean b(@Nullable ga gaVar) {
        return (gaVar == null || !gaVar.n || gaVar.o == null || gaVar.o.o == null) ? false : true;
    }
}
