package com.facebook.share.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.ab;
import com.facebook.internal.t;
import com.facebook.internal.v;
import com.facebook.internal.z;
import com.facebook.share.widget.LikeView.ObjectType;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* compiled from: LikeActionController */
public class c {
    /* access modifiers changed from: private */
    public static final String a = "c";
    /* access modifiers changed from: private */
    public static com.facebook.internal.l b;
    /* access modifiers changed from: private */
    public static final ConcurrentHashMap<String, c> c = new ConcurrentHashMap<>();
    private static ab d = new ab(1);
    private static ab e = new ab(1);
    private static Handler f;
    private static String g;
    private static boolean h;
    /* access modifiers changed from: private */
    public static volatile int i;
    private static com.facebook.c j;
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public ObjectType l;
    /* access modifiers changed from: private */
    public boolean m;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public String p;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public String r;
    /* access modifiers changed from: private */
    public String s;
    /* access modifiers changed from: private */
    public boolean t;
    /* access modifiers changed from: private */
    public boolean u;
    /* access modifiers changed from: private */
    public boolean v;
    private Bundle w;
    private AppEventsLogger x;

    /* renamed from: com.facebook.share.internal.c$4 reason: invalid class name */
    /* compiled from: LikeActionController */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[ObjectType.values().length];

        static {
            try {
                a[ObjectType.PAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* compiled from: LikeActionController */
    private abstract class a implements n {
        protected String a;
        protected ObjectType b;
        protected FacebookRequestError c;
        private GraphRequest e;

        /* access modifiers changed from: protected */
        public abstract void a(GraphResponse graphResponse);

        protected a(String str, ObjectType objectType) {
            this.a = str;
            this.b = objectType;
        }

        public void a(com.facebook.g gVar) {
            gVar.add(this.e);
        }

        public FacebookRequestError a() {
            return this.c;
        }

        /* access modifiers changed from: protected */
        public void a(GraphRequest graphRequest) {
            this.e = graphRequest;
            graphRequest.a(com.facebook.f.g());
            graphRequest.a((com.facebook.GraphRequest.b) new com.facebook.GraphRequest.b() {
                public void a(GraphResponse graphResponse) {
                    a.this.c = graphResponse.a();
                    if (a.this.c != null) {
                        a.this.a(a.this.c);
                    } else {
                        a.this.a(graphResponse);
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            t.a(LoggingBehavior.REQUESTS, c.a, "Error running request for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
        }
    }

    /* compiled from: LikeActionController */
    private static class b implements Runnable {
        private String a;
        private ObjectType b;
        private C0125c c;

        b(String str, ObjectType objectType, C0125c cVar) {
            this.a = str;
            this.b = objectType;
            this.c = cVar;
        }

        public void run() {
            c.c(this.a, this.b, this.c);
        }
    }

    @Deprecated
    /* renamed from: com.facebook.share.internal.c$c reason: collision with other inner class name */
    /* compiled from: LikeActionController */
    public interface C0125c {
        void a(c cVar, FacebookException facebookException);
    }

    /* compiled from: LikeActionController */
    private class d extends a {
        String e = c.this.n;
        String f = c.this.o;
        String g = c.this.p;
        String h = c.this.q;

        d(String str, ObjectType objectType) {
            super(str, objectType);
            Bundle bundle = new Bundle();
            bundle.putString("fields", "engagement.fields(count_string_with_like,count_string_without_like,social_sentence_with_like,social_sentence_without_like)");
            bundle.putString("locale", Locale.getDefault().toString());
            a(new GraphRequest(AccessToken.getCurrentAccessToken(), str, bundle, HttpMethod.GET));
        }

        /* access modifiers changed from: protected */
        public void a(GraphResponse graphResponse) {
            JSONObject b = z.b(graphResponse.b(), "engagement");
            if (b != null) {
                this.e = b.optString("count_string_with_like", this.e);
                this.f = b.optString("count_string_without_like", this.f);
                this.g = b.optString("social_sentence_with_like", this.g);
                this.h = b.optString("social_sentence_without_like", this.h);
            }
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            t.a(LoggingBehavior.REQUESTS, c.a, "Error fetching engagement for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
            c.this.a("get_engagement", facebookRequestError);
        }
    }

    /* compiled from: LikeActionController */
    private class e extends a {
        String e;

        e(String str, ObjectType objectType) {
            super(str, objectType);
            Bundle bundle = new Bundle();
            bundle.putString("fields", "og_object.fields(id)");
            bundle.putString("ids", str);
            a(new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, HttpMethod.GET));
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            if (facebookRequestError.getErrorMessage().contains("og_object")) {
                this.c = null;
                return;
            }
            t.a(LoggingBehavior.REQUESTS, c.a, "Error getting the FB id for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
        }

        /* access modifiers changed from: protected */
        public void a(GraphResponse graphResponse) {
            JSONObject b = z.b(graphResponse.b(), this.a);
            if (b != null) {
                JSONObject optJSONObject = b.optJSONObject("og_object");
                if (optJSONObject != null) {
                    this.e = optJSONObject.optString("id");
                }
            }
        }
    }

    /* compiled from: LikeActionController */
    private class f extends a implements i {
        private boolean f = c.this.m;
        private String g;
        private final String h;
        private final ObjectType i;

        f(String str, ObjectType objectType) {
            super(str, objectType);
            this.h = str;
            this.i = objectType;
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id,application");
            bundle.putString(ResponseConstants.OBJECT, this.h);
            a(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/og.likes", bundle, HttpMethod.GET));
        }

        /* access modifiers changed from: protected */
        public void a(GraphResponse graphResponse) {
            JSONArray c = z.c(graphResponse.b(), "data");
            if (c != null) {
                for (int i2 = 0; i2 < c.length(); i2++) {
                    JSONObject optJSONObject = c.optJSONObject(i2);
                    if (optJSONObject != null) {
                        this.f = true;
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("application");
                        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                        if (optJSONObject2 != null && AccessToken.isCurrentAccessTokenActive() && z.a(currentAccessToken.getApplicationId(), optJSONObject2.optString("id"))) {
                            this.g = optJSONObject.optString("id");
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            t.a(LoggingBehavior.REQUESTS, c.a, "Error fetching like status for object '%s' with type '%s' : %s", this.h, this.i, facebookRequestError);
            c.this.a("get_og_object_like", facebookRequestError);
        }

        public boolean b() {
            return this.f;
        }

        public String c() {
            return this.g;
        }
    }

    /* compiled from: LikeActionController */
    private class g extends a {
        String e;
        boolean f;

        g(String str, ObjectType objectType) {
            super(str, objectType);
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id");
            bundle.putString("ids", str);
            a(new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, HttpMethod.GET));
        }

        /* access modifiers changed from: protected */
        public void a(GraphResponse graphResponse) {
            JSONObject b = z.b(graphResponse.b(), this.a);
            if (b != null) {
                this.e = b.optString("id");
                this.f = !z.a(this.e);
            }
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            t.a(LoggingBehavior.REQUESTS, c.a, "Error getting the FB id for object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
        }
    }

    /* compiled from: LikeActionController */
    private class h extends a implements i {
        private boolean f = c.this.m;
        private String g;

        public String c() {
            return null;
        }

        h(String str) {
            super(str, ObjectType.PAGE);
            this.g = str;
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id");
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            StringBuilder sb = new StringBuilder();
            sb.append("me/likes/");
            sb.append(str);
            a(new GraphRequest(currentAccessToken, sb.toString(), bundle, HttpMethod.GET));
        }

        /* access modifiers changed from: protected */
        public void a(GraphResponse graphResponse) {
            JSONArray c = z.c(graphResponse.b(), "data");
            if (c != null && c.length() > 0) {
                this.f = true;
            }
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            t.a(LoggingBehavior.REQUESTS, c.a, "Error fetching like status for page id '%s': %s", this.g, facebookRequestError);
            c.this.a("get_page_like", facebookRequestError);
        }

        public boolean b() {
            return this.f;
        }
    }

    /* compiled from: LikeActionController */
    private interface i extends n {
        boolean b();

        String c();
    }

    /* compiled from: LikeActionController */
    private static class j implements Runnable {
        private static ArrayList<String> a = new ArrayList<>();
        private String b;
        private boolean c;

        j(String str, boolean z) {
            this.b = str;
            this.c = z;
        }

        public void run() {
            if (this.b != null) {
                a.remove(this.b);
                a.add(0, this.b);
            }
            if (this.c && a.size() >= 128) {
                while (64 < a.size()) {
                    c.c.remove((String) a.remove(a.size() - 1));
                }
            }
        }
    }

    /* compiled from: LikeActionController */
    private class k extends a {
        String e;

        k(String str, ObjectType objectType) {
            super(str, objectType);
            Bundle bundle = new Bundle();
            bundle.putString(ResponseConstants.OBJECT, str);
            a(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/og.likes", bundle, HttpMethod.POST));
        }

        /* access modifiers changed from: protected */
        public void a(GraphResponse graphResponse) {
            this.e = z.a(graphResponse.b(), "id");
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            if (facebookRequestError.getErrorCode() == 3501) {
                this.c = null;
                return;
            }
            t.a(LoggingBehavior.REQUESTS, c.a, "Error liking object '%s' with type '%s' : %s", this.a, this.b, facebookRequestError);
            c.this.a("publish_like", facebookRequestError);
        }
    }

    /* compiled from: LikeActionController */
    private class l extends a {
        private String f;

        /* access modifiers changed from: protected */
        public void a(GraphResponse graphResponse) {
        }

        l(String str) {
            super(null, null);
            this.f = str;
            a(new GraphRequest(AccessToken.getCurrentAccessToken(), str, null, HttpMethod.DELETE));
        }

        /* access modifiers changed from: protected */
        public void a(FacebookRequestError facebookRequestError) {
            t.a(LoggingBehavior.REQUESTS, c.a, "Error unliking object with unlike token '%s' : %s", this.f, facebookRequestError);
            c.this.a("publish_unlike", facebookRequestError);
        }
    }

    /* compiled from: LikeActionController */
    private interface m {
        void a();
    }

    /* compiled from: LikeActionController */
    private interface n {
        FacebookRequestError a();

        void a(com.facebook.g gVar);
    }

    /* compiled from: LikeActionController */
    private static class o implements Runnable {
        private String a;
        private String b;

        o(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            c.b(this.a, this.b);
        }
    }

    @Deprecated
    public boolean e() {
        return false;
    }

    @Deprecated
    public static boolean a(final int i2, final int i3, final Intent intent) {
        if (z.a(g)) {
            g = com.facebook.f.f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getString("PENDING_CONTROLLER_KEY", null);
        }
        if (z.a(g)) {
            return false;
        }
        a(g, ObjectType.UNKNOWN, (C0125c) new C0125c() {
            public void a(c cVar, FacebookException facebookException) {
                if (facebookException == null) {
                    cVar.b(i2, i3, intent);
                } else {
                    z.a(c.a, (Exception) facebookException);
                }
            }
        });
        return true;
    }

    @Deprecated
    public static void a(String str, ObjectType objectType, C0125c cVar) {
        if (!h) {
            j();
        }
        c a2 = a(str);
        if (a2 != null) {
            a(a2, objectType, cVar);
        } else {
            e.a((Runnable) new b(str, objectType, cVar));
        }
    }

    private static void a(c cVar, ObjectType objectType, C0125c cVar2) {
        FacebookException facebookException;
        ObjectType a2 = k.a(objectType, cVar.l);
        if (a2 == null) {
            facebookException = new FacebookException("Object with id:\"%s\" is already marked as type:\"%s\". Cannot change the type to:\"%s\"", cVar.k, cVar.l.toString(), objectType.toString());
            cVar = null;
        } else {
            cVar.l = a2;
            facebookException = null;
        }
        a(cVar2, cVar, facebookException);
    }

    /* access modifiers changed from: private */
    public static void c(String str, ObjectType objectType, C0125c cVar) {
        c a2 = a(str);
        if (a2 != null) {
            a(a2, objectType, cVar);
            return;
        }
        c b2 = b(str);
        if (b2 == null) {
            b2 = new c(str, objectType);
            l(b2);
        }
        a(str, b2);
        f.post(new Runnable(b2) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.o();
            }
        });
        a(cVar, b2, (FacebookException) null);
    }

    private static synchronized void j() {
        synchronized (c.class) {
            if (!h) {
                f = new Handler(Looper.getMainLooper());
                i = com.facebook.f.f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getInt("OBJECT_SUFFIX", 1);
                b = new com.facebook.internal.l(a, new com.facebook.internal.l.d());
                k();
                CallbackManagerImpl.a(RequestCodeOffset.Like.toRequestCode(), new com.facebook.internal.CallbackManagerImpl.a() {
                    public boolean a(int i, Intent intent) {
                        return c.a(RequestCodeOffset.Like.toRequestCode(), i, intent);
                    }
                });
                h = true;
            }
        }
    }

    private static void a(final C0125c cVar, final c cVar2, final FacebookException facebookException) {
        if (cVar != null) {
            f.post(new Runnable() {
                public void run() {
                    cVar.a(cVar2, facebookException);
                }
            });
        }
    }

    private static void k() {
        j = new com.facebook.c() {
            /* access modifiers changed from: protected */
            public void a(AccessToken accessToken, AccessToken accessToken2) {
                Context f = com.facebook.f.f();
                if (accessToken2 == null) {
                    c.i = (c.i + 1) % 1000;
                    f.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putInt("OBJECT_SUFFIX", c.i).apply();
                    c.c.clear();
                    c.b.a();
                }
                c.d((c) null, "com.facebook.sdk.LikeActionController.DID_RESET");
            }
        };
    }

    private static void a(String str, c cVar) {
        String d2 = d(str);
        d.a((Runnable) new j(d2, true));
        c.put(d2, cVar);
    }

    private static c a(String str) {
        String d2 = d(str);
        c cVar = (c) c.get(d2);
        if (cVar != null) {
            d.a((Runnable) new j(d2, false));
        }
        return cVar;
    }

    private static void l(c cVar) {
        String m2 = m(cVar);
        String d2 = d(cVar.k);
        if (!z.a(m2) && !z.a(d2)) {
            e.a((Runnable) new o(d2, m2));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(java.lang.String r2, java.lang.String r3) {
        /*
            r0 = 0
            com.facebook.internal.l r1 = b     // Catch:{ IOException -> 0x001c }
            java.io.OutputStream r2 = r1.b(r2)     // Catch:{ IOException -> 0x001c }
            byte[] r3 = r3.getBytes()     // Catch:{ IOException -> 0x0017, all -> 0x0014 }
            r2.write(r3)     // Catch:{ IOException -> 0x0017, all -> 0x0014 }
            if (r2 == 0) goto L_0x0029
            com.facebook.internal.z.a(r2)
            goto L_0x0029
        L_0x0014:
            r3 = move-exception
            r0 = r2
            goto L_0x002a
        L_0x0017:
            r3 = move-exception
            r0 = r2
            goto L_0x001d
        L_0x001a:
            r3 = move-exception
            goto L_0x002a
        L_0x001c:
            r3 = move-exception
        L_0x001d:
            java.lang.String r2 = a     // Catch:{ all -> 0x001a }
            java.lang.String r1 = "Unable to serialize controller to disk"
            android.util.Log.e(r2, r1, r3)     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0029
            com.facebook.internal.z.a(r0)
        L_0x0029:
            return
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            com.facebook.internal.z.a(r0)
        L_0x002f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.c.b(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r5 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        com.facebook.internal.z.a((java.io.Closeable) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        if (r5 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.facebook.share.internal.c b(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.String r5 = d(r5)     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            com.facebook.internal.l r1 = b     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            java.io.InputStream r5 = r1.a(r5)     // Catch:{ IOException -> 0x002a, all -> 0x0025 }
            if (r5 == 0) goto L_0x001f
            java.lang.String r1 = com.facebook.internal.z.a(r5)     // Catch:{ IOException -> 0x001d }
            boolean r2 = com.facebook.internal.z.a(r1)     // Catch:{ IOException -> 0x001d }
            if (r2 != 0) goto L_0x001f
            com.facebook.share.internal.c r1 = c(r1)     // Catch:{ IOException -> 0x001d }
            r0 = r1
            goto L_0x001f
        L_0x001d:
            r1 = move-exception
            goto L_0x002c
        L_0x001f:
            if (r5 == 0) goto L_0x0036
        L_0x0021:
            com.facebook.internal.z.a(r5)
            goto L_0x0036
        L_0x0025:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L_0x0038
        L_0x002a:
            r1 = move-exception
            r5 = r0
        L_0x002c:
            java.lang.String r2 = a     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = "Unable to deserialize controller from disk"
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x0037 }
            if (r5 == 0) goto L_0x0036
            goto L_0x0021
        L_0x0036:
            return r0
        L_0x0037:
            r0 = move-exception
        L_0x0038:
            if (r5 == 0) goto L_0x003d
            com.facebook.internal.z.a(r5)
        L_0x003d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.c.b(java.lang.String):com.facebook.share.internal.c");
    }

    private static c c(String str) {
        c cVar = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("com.facebook.share.internal.LikeActionController.version", -1) != 3) {
                return null;
            }
            c cVar2 = new c(jSONObject.getString(ResponseConstants.OBJECT_ID), ObjectType.fromInt(jSONObject.optInt(ResponseConstants.OBJECT_TYPE, ObjectType.UNKNOWN.getValue())));
            cVar2.n = jSONObject.optString("like_count_string_with_like", null);
            cVar2.o = jSONObject.optString("like_count_string_without_like", null);
            cVar2.p = jSONObject.optString("social_sentence_with_like", null);
            cVar2.q = jSONObject.optString("social_sentence_without_like", null);
            cVar2.m = jSONObject.optBoolean("is_object_liked");
            cVar2.r = jSONObject.optString("unlike_token", null);
            JSONObject optJSONObject = jSONObject.optJSONObject("facebook_dialog_analytics_bundle");
            if (optJSONObject != null) {
                cVar2.w = com.facebook.internal.c.a(optJSONObject);
            }
            cVar = cVar2;
            return cVar;
        } catch (JSONException e2) {
            Log.e(a, "Unable to deserialize controller from JSON", e2);
        }
    }

    private static String m(c cVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("com.facebook.share.internal.LikeActionController.version", 3);
            jSONObject.put(ResponseConstants.OBJECT_ID, cVar.k);
            jSONObject.put(ResponseConstants.OBJECT_TYPE, cVar.l.getValue());
            jSONObject.put("like_count_string_with_like", cVar.n);
            jSONObject.put("like_count_string_without_like", cVar.o);
            jSONObject.put("social_sentence_with_like", cVar.p);
            jSONObject.put("social_sentence_without_like", cVar.q);
            jSONObject.put("is_object_liked", cVar.m);
            jSONObject.put("unlike_token", cVar.r);
            if (cVar.w != null) {
                JSONObject a2 = com.facebook.internal.c.a(cVar.w);
                if (a2 != null) {
                    jSONObject.put("facebook_dialog_analytics_bundle", a2);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            Log.e(a, "Unable to serialize controller to JSON", e2);
            return null;
        }
    }

    private static String d(String str) {
        String token = AccessToken.isCurrentAccessTokenActive() ? AccessToken.getCurrentAccessToken().getToken() : null;
        if (token != null) {
            token = z.b(token);
        }
        return String.format(Locale.ROOT, "%s|%s|com.fb.sdk.like|%d", new Object[]{str, z.a(token, ""), Integer.valueOf(i)});
    }

    /* access modifiers changed from: private */
    public static void d(c cVar, String str) {
        c(cVar, str, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public static void c(c cVar, String str, Bundle bundle) {
        Intent intent = new Intent(str);
        if (cVar != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("com.facebook.sdk.LikeActionController.OBJECT_ID", cVar.a());
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(com.facebook.f.f()).sendBroadcast(intent);
    }

    private c(String str, ObjectType objectType) {
        this.k = str;
        this.l = objectType;
    }

    @Deprecated
    public String a() {
        return this.k;
    }

    @Deprecated
    public String b() {
        return this.m ? this.n : this.o;
    }

    @Deprecated
    public String c() {
        return this.m ? this.p : this.q;
    }

    @Deprecated
    public boolean d() {
        return this.m;
    }

    @Deprecated
    public void a(Activity activity, com.facebook.internal.m mVar, Bundle bundle) {
        boolean z = true;
        boolean z2 = !this.m;
        if (n()) {
            b(z2);
            if (this.v) {
                l().a("fb_like_control_did_undo_quickly", (Double) null, bundle);
            } else if (!a(z2, bundle)) {
                if (z2) {
                    z = false;
                }
                b(z);
                b(activity, mVar, bundle);
            }
        } else {
            b(activity, mVar, bundle);
        }
    }

    /* access modifiers changed from: private */
    public AppEventsLogger l() {
        if (this.x == null) {
            this.x = AppEventsLogger.a(com.facebook.f.f());
        }
        return this.x;
    }

    private boolean a(boolean z, Bundle bundle) {
        if (n()) {
            if (z) {
                c(bundle);
                return true;
            } else if (!z.a(this.r)) {
                d(bundle);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        b(z);
        Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Unable to publish the like/unlike action");
        c(this, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
    }

    private void b(boolean z) {
        a(z, this.n, this.o, this.p, this.q, this.r);
    }

    /* access modifiers changed from: private */
    public void a(boolean z, String str, String str2, String str3, String str4, String str5) {
        String a2 = z.a(str, (String) null);
        String a3 = z.a(str2, (String) null);
        String a4 = z.a(str3, (String) null);
        String a5 = z.a(str4, (String) null);
        String a6 = z.a(str5, (String) null);
        if (z != this.m || !z.a(a2, this.n) || !z.a(a3, this.o) || !z.a(a4, this.p) || !z.a(a5, this.q) || !z.a(a6, this.r)) {
            this.m = z;
            this.n = a2;
            this.o = a3;
            this.p = a4;
            this.q = a5;
            this.r = a6;
            l(this);
            d(this, "com.facebook.sdk.LikeActionController.UPDATED");
        }
    }

    private void b(Activity activity, com.facebook.internal.m mVar, Bundle bundle) {
        String str;
        String str2;
        if (d.e()) {
            str = "fb_like_control_did_present_dialog";
        } else if (d.f()) {
            str = "fb_like_control_did_present_fallback_dialog";
        } else {
            a("present_dialog", bundle);
            z.b(a, "Cannot show the Like Dialog on this device.");
            d((c) null, "com.facebook.sdk.LikeActionController.UPDATED");
            str = null;
        }
        if (str != null) {
            if (this.l != null) {
                str2 = this.l.toString();
            } else {
                str2 = ObjectType.UNKNOWN.toString();
            }
            LikeContent a2 = new com.facebook.share.internal.LikeContent.a().a(this.k).b(str2).a();
            if (mVar != null) {
                new d(mVar).b(a2);
            } else {
                new d(activity).b(a2);
            }
            b(bundle);
            l().a("fb_like_control_did_present_dialog", (Double) null, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void b(int i2, int i3, Intent intent) {
        k.a(i2, i3, intent, a(this.w));
        m();
    }

    private i a(final Bundle bundle) {
        return new i(null) {
            public void a(com.facebook.internal.a aVar, Bundle bundle) {
                String str;
                String str2;
                String str3;
                String str4;
                String f;
                if (bundle != null && bundle.containsKey("object_is_liked")) {
                    boolean z = bundle.getBoolean("object_is_liked");
                    String b2 = c.this.n;
                    String c = c.this.o;
                    if (bundle.containsKey("like_count_string")) {
                        str2 = bundle.getString("like_count_string");
                        str = str2;
                    } else {
                        str2 = b2;
                        str = c;
                    }
                    String d = c.this.p;
                    String e = c.this.q;
                    if (bundle.containsKey("social_sentence")) {
                        str4 = bundle.getString("social_sentence");
                        str3 = str4;
                    } else {
                        str4 = d;
                        str3 = e;
                    }
                    if (bundle.containsKey("object_is_liked")) {
                        f = bundle.getString("unlike_token");
                    } else {
                        f = c.this.r;
                    }
                    String str5 = f;
                    Bundle bundle2 = bundle == null ? new Bundle() : bundle;
                    bundle2.putString("call_id", aVar.c().toString());
                    c.this.l().a("fb_like_control_dialog_did_succeed", (Double) null, bundle2);
                    c.this.a(z, str2, str, str4, str3, str5);
                }
            }

            public void a(com.facebook.internal.a aVar, FacebookException facebookException) {
                t.a(LoggingBehavior.REQUESTS, c.a, "Like Dialog failed with error : %s", facebookException);
                Bundle bundle = bundle == null ? new Bundle() : bundle;
                bundle.putString("call_id", aVar.c().toString());
                c.this.a("present_dialog", bundle);
                c.c(c.this, "com.facebook.sdk.LikeActionController.DID_ERROR", v.a(facebookException));
            }

            public void a(com.facebook.internal.a aVar) {
                a(aVar, (FacebookException) new FacebookOperationCanceledException());
            }
        };
    }

    private void b(Bundle bundle) {
        e(this.k);
        this.w = bundle;
        l(this);
    }

    private void m() {
        this.w = null;
        e((String) null);
    }

    private static void e(String str) {
        g = str;
        com.facebook.f.f().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putString("PENDING_CONTROLLER_KEY", g).apply();
    }

    private boolean n() {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        return !this.t && this.s != null && AccessToken.isCurrentAccessTokenActive() && currentAccessToken.getPermissions() != null && currentAccessToken.getPermissions().contains("publish_actions");
    }

    private void c(final Bundle bundle) {
        this.v = true;
        a((m) new m() {
            public void a() {
                if (z.a(c.this.s)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Invalid Object Id");
                    c.c(c.this, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
                    return;
                }
                com.facebook.g gVar = new com.facebook.g();
                final k kVar = new k(c.this.s, c.this.l);
                kVar.a(gVar);
                gVar.a((com.facebook.g.a) new com.facebook.g.a() {
                    public void a(com.facebook.g gVar) {
                        c.this.v = false;
                        if (kVar.a() != null) {
                            c.this.a(false);
                            return;
                        }
                        c.this.r = z.a(kVar.e, (String) null);
                        c.this.u = true;
                        c.this.l().a("fb_like_control_did_like", (Double) null, bundle);
                        c.this.e(bundle);
                    }
                });
                gVar.h();
            }
        });
    }

    private void d(final Bundle bundle) {
        this.v = true;
        com.facebook.g gVar = new com.facebook.g();
        final l lVar = new l(this.r);
        lVar.a(gVar);
        gVar.a((com.facebook.g.a) new com.facebook.g.a() {
            public void a(com.facebook.g gVar) {
                c.this.v = false;
                if (lVar.a() != null) {
                    c.this.a(true);
                    return;
                }
                c.this.r = null;
                c.this.u = false;
                c.this.l().a("fb_like_control_did_unlike", (Double) null, bundle);
                c.this.e(bundle);
            }
        });
        gVar.h();
    }

    /* access modifiers changed from: private */
    public void o() {
        if (!AccessToken.isCurrentAccessTokenActive()) {
            p();
        } else {
            a((m) new m() {
                public void a() {
                    final i iVar;
                    if (AnonymousClass4.a[c.this.l.ordinal()] != 1) {
                        iVar = new f(c.this.s, c.this.l);
                    } else {
                        iVar = new h(c.this.s);
                    }
                    final d dVar = new d(c.this.s, c.this.l);
                    com.facebook.g gVar = new com.facebook.g();
                    iVar.a(gVar);
                    dVar.a(gVar);
                    gVar.a((com.facebook.g.a) new com.facebook.g.a() {
                        public void a(com.facebook.g gVar) {
                            if (iVar.a() == null && dVar.a() == null) {
                                c.this.a(iVar.b(), dVar.e, dVar.f, dVar.g, dVar.h, iVar.c());
                                return;
                            }
                            t.a(LoggingBehavior.REQUESTS, c.a, "Unable to refresh like state for id: '%s'", c.this.k);
                        }
                    });
                    gVar.h();
                }
            });
        }
    }

    private void p() {
        e eVar = new e(com.facebook.f.f(), com.facebook.f.j(), this.k);
        if (eVar.start()) {
            eVar.setCompletedListener(new com.facebook.internal.PlatformServiceClient.a() {
                public void a(Bundle bundle) {
                    String b;
                    String c;
                    String d;
                    String e;
                    String f;
                    if (bundle != null && bundle.containsKey("com.facebook.platform.extra.OBJECT_IS_LIKED")) {
                        boolean z = bundle.getBoolean("com.facebook.platform.extra.OBJECT_IS_LIKED");
                        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE")) {
                            b = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE");
                        } else {
                            b = c.this.n;
                        }
                        String str = b;
                        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE")) {
                            c = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE");
                        } else {
                            c = c.this.o;
                        }
                        String str2 = c;
                        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE")) {
                            d = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE");
                        } else {
                            d = c.this.p;
                        }
                        String str3 = d;
                        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE")) {
                            e = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE");
                        } else {
                            e = c.this.q;
                        }
                        String str4 = e;
                        if (bundle.containsKey("com.facebook.platform.extra.UNLIKE_TOKEN")) {
                            f = bundle.getString("com.facebook.platform.extra.UNLIKE_TOKEN");
                        } else {
                            f = c.this.r;
                        }
                        c.this.a(z, str, str2, str3, str4, f);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void e(Bundle bundle) {
        if (this.m != this.u && !a(this.m, bundle)) {
            a(!this.m);
        }
    }

    private void a(final m mVar) {
        if (!z.a(this.s)) {
            if (mVar != null) {
                mVar.a();
            }
            return;
        }
        final e eVar = new e(this.k, this.l);
        final g gVar = new g(this.k, this.l);
        com.facebook.g gVar2 = new com.facebook.g();
        eVar.a(gVar2);
        gVar.a(gVar2);
        gVar2.a((com.facebook.g.a) new com.facebook.g.a() {
            public void a(com.facebook.g gVar) {
                FacebookRequestError facebookRequestError;
                c.this.s = eVar.e;
                if (z.a(c.this.s)) {
                    c.this.s = gVar.e;
                    c.this.t = gVar.f;
                }
                if (z.a(c.this.s)) {
                    t.a(LoggingBehavior.DEVELOPER_ERRORS, c.a, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", c.this.k);
                    c cVar = c.this;
                    String str = "get_verified_id";
                    if (gVar.a() != null) {
                        facebookRequestError = gVar.a();
                    } else {
                        facebookRequestError = eVar.a();
                    }
                    cVar.a(str, facebookRequestError);
                }
                if (mVar != null) {
                    mVar.a();
                }
            }
        });
        gVar2.h();
    }

    /* access modifiers changed from: private */
    public void a(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString(ResponseConstants.OBJECT_ID, this.k);
        bundle2.putString(ResponseConstants.OBJECT_TYPE, this.l.toString());
        bundle2.putString("current_action", str);
        l().a("fb_like_control_error", (Double) null, bundle2);
    }

    /* access modifiers changed from: private */
    public void a(String str, FacebookRequestError facebookRequestError) {
        Bundle bundle = new Bundle();
        if (facebookRequestError != null) {
            JSONObject requestResult = facebookRequestError.getRequestResult();
            if (requestResult != null) {
                bundle.putString("error", requestResult.toString());
            }
        }
        a(str, bundle);
    }
}
