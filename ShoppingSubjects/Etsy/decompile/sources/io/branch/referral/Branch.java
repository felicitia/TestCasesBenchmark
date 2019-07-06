package io.branch.referral;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import io.branch.referral.SharingHelper.SHARE_WITH;
import io.branch.referral.network.BranchRemoteInterface;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.LinkProperties;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Branch implements a, a, io.branch.referral.i.b {
    private static CUSTOM_REFERRABLE_SETTINGS C = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
    private static String F = "app.link";
    private static int G = 2500;
    private static final String[] H = {"extra_launch_uri", "branch_intent"};
    private static boolean M = true;
    static boolean a = false;
    static Boolean b = null;
    static boolean c = true;
    private static boolean j = false;
    private static long l = 1500;
    /* access modifiers changed from: private */
    public static Branch m = null;
    private static boolean w = false;
    private static boolean x = false;
    /* access modifiers changed from: private */
    public SESSION_STATE A = SESSION_STATE.UNINITIALISED;
    /* access modifiers changed from: private */
    public ShareLinkManager B;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, String> D;
    private boolean E = false;
    /* access modifiers changed from: private */
    public CountDownLatch I = null;
    /* access modifiers changed from: private */
    public CountDownLatch J = null;
    private boolean K = false;
    /* access modifiers changed from: private */
    public boolean L = false;
    private final ab N;
    final Object d;
    WeakReference<Activity> e;
    boolean f = false;
    String g;
    boolean h = false;
    private JSONObject i;
    private boolean k = false;
    /* access modifiers changed from: private */
    public BranchRemoteInterface n;
    /* access modifiers changed from: private */
    public m o;
    private final aa p;
    /* access modifiers changed from: private */
    public Context q;
    private Semaphore r;
    /* access modifiers changed from: private */
    public final u s;
    /* access modifiers changed from: private */
    public int t;
    /* access modifiers changed from: private */
    public boolean u;
    /* access modifiers changed from: private */
    public Map<d, String> v;
    /* access modifiers changed from: private */
    public INTENT_STATE y = INTENT_STATE.PENDING;
    /* access modifiers changed from: private */
    public boolean z = false;

    private enum CUSTOM_REFERRABLE_SETTINGS {
        USE_DEFAULT,
        REFERRABLE,
        NON_REFERRABLE
    }

    public enum CreditHistoryOrder {
        kMostRecentFirst,
        kLeastRecentFirst
    }

    private enum INTENT_STATE {
        PENDING,
        READY
    }

    private enum SESSION_STATE {
        INITIALISED,
        INITIALISING,
        UNINITIALISED
    }

    @TargetApi(14)
    private class a implements ActivityLifecycleCallbacks {
        private int b;

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private a() {
            this.b = 0;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            Branch.this.y = Branch.this.z ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            Branch.this.L = true;
            if (i.a().b(activity.getApplicationContext())) {
                i.a().a((Context) activity);
            }
        }

        public void onActivityStarted(Activity activity) {
            Branch.this.y = Branch.this.z ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            if (Branch.this.A == SESSION_STATE.INITIALISED) {
                try {
                    io.branch.indexing.b.a().a(activity, Branch.this.g);
                } catch (Exception unused) {
                }
            }
            if (this.b < 1) {
                if (Branch.this.A == SESSION_STATE.INITIALISED) {
                    Branch.this.A = SESSION_STATE.UNINITIALISED;
                }
                if (h.a(Branch.this.q)) {
                    Branch.this.o.F();
                }
                Branch.this.c(activity);
            } else if (Branch.this.a(activity.getIntent())) {
                Branch.this.A = SESSION_STATE.UNINITIALISED;
                Branch.this.c(activity);
            }
            this.b++;
            Branch.this.L = false;
        }

        public void onActivityResumed(Activity activity) {
            if (Branch.this.a(activity.getIntent())) {
                Branch.this.A = SESSION_STATE.UNINITIALISED;
                Branch.this.c(activity);
            }
            Branch.this.e = new WeakReference<>(activity);
            if (Branch.this.z) {
                Branch.this.y = INTENT_STATE.READY;
                Branch.this.a(activity, (activity.getIntent() == null || Branch.this.A == SESSION_STATE.INITIALISED) ? false : true);
            }
        }

        public void onActivityPaused(Activity activity) {
            if (Branch.this.B != null) {
                Branch.this.B.a(true);
            }
        }

        public void onActivityStopped(Activity activity) {
            io.branch.indexing.b.a().a(activity);
            this.b--;
            if (this.b < 1) {
                Branch.this.h = false;
                Branch.this.d();
            }
        }

        public void onActivityDestroyed(Activity activity) {
            if (Branch.this.e != null && Branch.this.e.get() == activity) {
                Branch.this.e.clear();
            }
            i.a().a(activity);
        }
    }

    public interface b {
        void a(String str, c cVar);
    }

    public interface c {
        void a();

        void a(String str);

        void a(String str, String str2, c cVar);

        void b();
    }

    public interface d {
        void a(JSONArray jSONArray, c cVar);
    }

    private class e extends BranchAsyncTask<Void, Void, z> {
        ServerRequest a;

        public e(ServerRequest serverRequest) {
            this.a = serverRequest;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.a.q();
            this.a.l();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public z doInBackground(Void... voidArr) {
            Branch branch = Branch.this;
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.f());
            sb.append("-");
            sb.append(Jsonkey.Queue_Wait_Time.getKey());
            branch.a(sb.toString(), String.valueOf(this.a.o()));
            this.a.m();
            if (Branch.this.a() && !this.a.t()) {
                return new z(this.a.f(), -117);
            }
            if (this.a.a()) {
                return Branch.this.n.a(this.a.g(), this.a.j(), this.a.f(), Branch.this.o.f());
            }
            return Branch.this.n.a(this.a.a(Branch.this.D), this.a.g(), this.a.f(), Branch.this.o.f());
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(z zVar) {
            boolean z;
            super.onPostExecute(zVar);
            if (zVar != null) {
                try {
                    int a2 = zVar.a();
                    Branch.this.u = true;
                    if (zVar.a() == -117) {
                        this.a.s();
                        Branch.this.s.b(this.a);
                    } else if (a2 != 200) {
                        if (this.a instanceof s) {
                            Branch.this.A = SESSION_STATE.UNINITIALISED;
                        }
                        if (a2 == 409) {
                            Branch.this.s.b(this.a);
                            if (this.a instanceof o) {
                                ((o) this.a).x();
                            } else {
                                Log.i("BranchSDK", "Branch API Error: Conflicting resource error code from API");
                                Branch.this.a(0, a2);
                            }
                        } else {
                            Branch.this.u = false;
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < Branch.this.s.a(); i++) {
                                arrayList.add(Branch.this.s.a(i));
                            }
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ServerRequest serverRequest = (ServerRequest) it.next();
                                if (serverRequest == null || !serverRequest.c()) {
                                    Branch.this.s.b(serverRequest);
                                }
                            }
                            Branch.this.t = 0;
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                ServerRequest serverRequest2 = (ServerRequest) it2.next();
                                if (serverRequest2 != null) {
                                    serverRequest2.a(a2, zVar.d());
                                    if (serverRequest2.c()) {
                                        serverRequest2.b();
                                    }
                                }
                            }
                        }
                    } else {
                        Branch.this.u = true;
                        if (this.a instanceof o) {
                            if (zVar.b() != null) {
                                Branch.this.v.put(((o) this.a).u(), zVar.b().getString("url"));
                            }
                        } else if (this.a instanceof t) {
                            Branch.this.v.clear();
                            Branch.this.s.d();
                        }
                        Branch.this.s.b();
                        if (!(this.a instanceof s)) {
                            if (!(this.a instanceof r)) {
                                this.a.a(zVar, Branch.m);
                            }
                        }
                        JSONObject b2 = zVar.b();
                        if (b2 != null) {
                            if (!Branch.this.a()) {
                                if (b2.has(Jsonkey.SessionID.getKey())) {
                                    Branch.this.o.d(b2.getString(Jsonkey.SessionID.getKey()));
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (b2.has(Jsonkey.IdentityID.getKey())) {
                                    if (!Branch.this.o.i().equals(b2.getString(Jsonkey.IdentityID.getKey()))) {
                                        Branch.this.v.clear();
                                        Branch.this.o.e(b2.getString(Jsonkey.IdentityID.getKey()));
                                        z = true;
                                    }
                                }
                                if (b2.has(Jsonkey.DeviceFingerprintID.getKey())) {
                                    Branch.this.o.c(b2.getString(Jsonkey.DeviceFingerprintID.getKey()));
                                    z = true;
                                }
                            } else {
                                z = false;
                            }
                            if (z) {
                                Branch.this.o();
                            }
                            if (this.a instanceof s) {
                                Branch.this.A = SESSION_STATE.INITIALISED;
                                this.a.a(zVar, Branch.m);
                                if (!Branch.this.f && !((s) this.a).a(zVar)) {
                                    Branch.this.t();
                                }
                                if (((s) this.a).v()) {
                                    Branch.this.f = true;
                                }
                                if (Branch.this.J != null) {
                                    Branch.this.J.countDown();
                                }
                                if (Branch.this.I != null) {
                                    Branch.this.I.countDown();
                                }
                            } else {
                                this.a.a(zVar, Branch.m);
                            }
                        }
                    }
                    Branch.this.t = 0;
                    if (Branch.this.u && Branch.this.A != SESSION_STATE.UNINITIALISED) {
                        Branch.this.n();
                    }
                } catch (JSONException e) {
                    com.google.a.a.a.a.a.a.a(e);
                }
            }
        }
    }

    public interface f {
        void a(JSONObject jSONObject, c cVar);
    }

    public interface g {
        void a(boolean z, c cVar);
    }

    public interface h extends c {
        boolean a(String str, BranchUniversalObject branchUniversalObject, LinkProperties linkProperties);
    }

    public interface i {
        boolean a();
    }

    public interface j {
        String a(String str);

        String b(String str);
    }

    public interface k {
        void a(boolean z, c cVar);
    }

    public static class l {
        e a;
        private final Activity b;
        private final Branch c;
        private String d;
        private String e;
        private c f;
        private j g;
        private ArrayList<SHARE_WITH> h;
        private String i;
        private Drawable j;
        private String k;
        private Drawable l;
        private String m;
        private String n;
        private int o;
        private boolean p;
        private int q;
        private int r;
        private String s;
        private View t;
        private int u;
        private List<String> v;
        private List<String> w;

        public l(Activity activity, JSONObject jSONObject) {
            this.f = null;
            this.g = null;
            this.r = -1;
            this.s = null;
            this.t = null;
            this.u = 50;
            this.v = new ArrayList();
            this.w = new ArrayList();
            this.b = activity;
            this.c = Branch.m;
            this.a = new e(activity);
            try {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    this.a.a(str, (String) jSONObject.get(str));
                }
            } catch (Exception unused) {
            }
            this.d = "";
            this.f = null;
            this.g = null;
            this.h = new ArrayList<>();
            this.i = null;
            this.j = h.a(activity.getApplicationContext(), 17301573);
            this.k = "More...";
            this.l = h.a(activity.getApplicationContext(), 17301582);
            this.m = "Copy link";
            this.n = "Copied link to clipboard!";
        }

        public l(Activity activity, e eVar) {
            this(activity, new JSONObject());
            this.a = eVar;
        }

        public l a(String str) {
            this.d = str;
            return this;
        }

        public l b(String str) {
            this.e = str;
            return this;
        }

        public l a(c cVar) {
            this.f = cVar;
            return this;
        }

        public l a(j jVar) {
            this.g = jVar;
            return this;
        }

        public l a(ArrayList<SHARE_WITH> arrayList) {
            this.h.addAll(arrayList);
            return this;
        }

        public l c(String str) {
            this.i = str;
            return this;
        }

        public l a(Drawable drawable, String str) {
            this.j = drawable;
            this.k = str;
            return this;
        }

        public l a(Drawable drawable, String str, String str2) {
            this.l = drawable;
            this.m = str;
            this.n = str2;
            return this;
        }

        public l a(boolean z) {
            this.p = z;
            return this;
        }

        public l a(@StyleRes int i2) {
            this.q = i2;
            return this;
        }

        public l b(int i2) {
            this.r = i2;
            return this;
        }

        public l d(String str) {
            this.s = str;
            return this;
        }

        public l a(View view) {
            this.t = view;
            return this;
        }

        public l c(int i2) {
            this.u = i2;
            return this;
        }

        public l a(@NonNull List<String> list) {
            this.w.addAll(list);
            return this;
        }

        public l b(@NonNull List<String> list) {
            this.v.addAll(list);
            return this;
        }

        public void d(@StyleRes int i2) {
            this.o = i2;
        }

        public void a(e eVar) {
            this.a = eVar;
        }

        public void a() {
            Branch.m.a(this);
        }

        public Activity b() {
            return this.b;
        }

        public ArrayList<SHARE_WITH> c() {
            return this.h;
        }

        /* access modifiers changed from: 0000 */
        public List<String> d() {
            return this.w;
        }

        /* access modifiers changed from: 0000 */
        public List<String> e() {
            return this.v;
        }

        public String f() {
            return this.d;
        }

        public String g() {
            return this.e;
        }

        public c h() {
            return this.f;
        }

        public j i() {
            return this.g;
        }

        public String j() {
            return this.i;
        }

        public Drawable k() {
            return this.j;
        }

        public String l() {
            return this.k;
        }

        public Drawable m() {
            return this.l;
        }

        public String n() {
            return this.m;
        }

        public String o() {
            return this.n;
        }

        public e p() {
            return this.a;
        }

        public boolean q() {
            return this.p;
        }

        public int r() {
            return this.q;
        }

        public int s() {
            return this.r;
        }

        public String t() {
            return this.s;
        }

        public View u() {
            return this.t;
        }

        public int v() {
            return this.o;
        }

        public int w() {
            return this.u;
        }
    }

    private class m extends AsyncTask<ServerRequest, Void, z> {
        private m() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public z doInBackground(ServerRequest... serverRequestArr) {
            BranchRemoteInterface h = Branch.this.n;
            JSONObject h2 = serverRequestArr[0].h();
            StringBuilder sb = new StringBuilder();
            sb.append(Branch.this.o.a());
            sb.append("v1/url");
            return h.a(h2, sb.toString(), RequestPath.GetURL.getPath(), Branch.this.o.f());
        }
    }

    public void b(String str, String str2) {
    }

    private Branch(@NonNull Context context) {
        this.o = m.a(context);
        this.N = new ab(context);
        this.n = BranchRemoteInterface.a(context);
        this.p = new aa(context);
        this.s = u.a(context);
        this.r = new Semaphore(1);
        this.d = new Object();
        this.t = 0;
        this.u = true;
        this.v = new HashMap();
        this.D = new ConcurrentHashMap<>();
        if (!this.N.a()) {
            this.E = this.p.a((a) this);
        }
        if (VERSION.SDK_INT >= 15) {
            this.z = true;
            this.y = INTENT_STATE.PENDING;
            return;
        }
        this.z = false;
        this.y = INTENT_STATE.READY;
    }

    public boolean a() {
        return this.N.a();
    }

    @TargetApi(14)
    public static Branch b() {
        if (m == null) {
            Log.e("BranchSDK", "Branch instance is not created yet. Make sure you have initialised Branch. [Consider Calling getInstance(Context ctx) if you still have issue.]");
        } else if (w && !x) {
            Log.e("BranchSDK", "Branch instance is not properly initialised. Make sure your Application class is extending BranchApp class. If you are not extending BranchApp class make sure you are initialising Branch in your Applications onCreate()");
        }
        return m;
    }

    private static Branch a(@NonNull Context context, boolean z2, String str) {
        boolean z3;
        if (m == null) {
            m = d(context);
            if (TextUtils.isEmpty(str)) {
                str = m.o.a(z2);
            }
            if (str == null || str.equalsIgnoreCase("bnc_no_value")) {
                String str2 = null;
                try {
                    Resources resources = context.getResources();
                    str2 = resources.getString(resources.getIdentifier("io.branch.apiKey", "string", context.getPackageName()));
                } catch (Exception unused) {
                }
                if (!TextUtils.isEmpty(str2)) {
                    z3 = m.o.b(str2);
                } else {
                    Log.i("BranchSDK", "Branch Warning: Please enter your branch_key in your project's Manifest file!");
                    z3 = m.o.b("bnc_no_value");
                }
            } else {
                z3 = m.o.b(str);
            }
            if (z3) {
                m.v.clear();
                m.s.d();
            }
            m.q = context.getApplicationContext();
            if (context instanceof Application) {
                w = true;
                m.a((Application) context);
            }
        }
        return m;
    }

    public static Branch a(@NonNull Context context) {
        return a(context, true, (String) null);
    }

    public static Branch b(@NonNull Context context) {
        return a(context, false, (String) null);
    }

    @TargetApi(14)
    public static Branch c(@NonNull Context context) {
        w = true;
        C = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        a(context, true ^ h.a(context), (String) null);
        return m;
    }

    private static Branch d(@NonNull Context context) {
        return new Branch(context.getApplicationContext());
    }

    public void a(int i2) {
        if (this.o != null && i2 >= 0) {
            this.o.b(i2);
        }
    }

    public void b(int i2) {
        if (this.o != null && i2 > 0) {
            this.o.a(i2);
        }
    }

    public static boolean c() {
        return j;
    }

    public boolean a(f fVar, Activity activity) {
        if (C == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            a(fVar, activity, true);
        } else {
            a(fVar, activity, C == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
        }
        return true;
    }

    public boolean a(f fVar, Uri uri, Activity activity) {
        b(uri, activity);
        return a(fVar, activity);
    }

    public boolean a(Uri uri, Activity activity) {
        b(uri, activity);
        return a((f) null, activity);
    }

    private void a(f fVar, Activity activity, boolean z2) {
        if (activity != null) {
            this.e = new WeakReference<>(activity);
        }
        if (!r() || !p() || this.A != SESSION_STATE.INITIALISED) {
            if (this.h && a(fVar)) {
                a(Jsonkey.InstantDeepLinkSession.getKey(), "true");
                this.h = false;
                t();
            }
            if (z2) {
                this.o.y();
            } else {
                this.o.z();
            }
            if (this.A != SESSION_STATE.INITIALISING) {
                this.A = SESSION_STATE.INITIALISING;
                b(fVar);
            } else if (fVar != null) {
                this.s.a(fVar);
            }
        } else {
            a(fVar);
            this.h = false;
        }
    }

    private boolean a(f fVar) {
        if (fVar != null) {
            if (!w) {
                fVar.a(new JSONObject(), null);
            } else if (!this.f) {
                fVar.a(i(), null);
                this.f = true;
            } else {
                fVar.a(new JSONObject(), null);
            }
        }
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        m();
        this.g = null;
        this.N.a(this.q);
    }

    private void m() {
        if (this.A != SESSION_STATE.UNINITIALISED) {
            if (!this.u) {
                ServerRequest c2 = this.s.c();
                if ((c2 != null && (c2 instanceof x)) || (c2 instanceof y)) {
                    this.s.b();
                }
            } else if (!this.s.e()) {
                a((ServerRequest) new w(this.q));
            }
            this.A = SESSION_STATE.UNINITIALISED;
        }
    }

    private boolean b(Uri uri, Activity activity) {
        String str;
        String[] strArr;
        if (!M && !((this.y != INTENT_STATE.READY && !this.L) || activity == null || activity.getIntent() == null || this.A == SESSION_STATE.INITIALISED || a(activity.getIntent()))) {
            Intent intent = activity.getIntent();
            if (intent.getData() == null || (!this.L && a(activity))) {
                if (!this.o.v().equals("bnc_no_value")) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(Jsonkey.Clicked_Branch_Link.getKey(), false);
                        jSONObject.put(Jsonkey.IsFirstSession.getKey(), false);
                        this.o.o(jSONObject.toString());
                        this.h = true;
                    } catch (JSONException e2) {
                        com.google.a.a.a.a.a.a.a(e2);
                    }
                }
            } else if (!TextUtils.isEmpty(intent.getStringExtra(Jsonkey.BranchData.getKey()))) {
                try {
                    JSONObject jSONObject2 = new JSONObject(intent.getStringExtra(Jsonkey.BranchData.getKey()));
                    jSONObject2.put(Jsonkey.Clicked_Branch_Link.getKey(), true);
                    this.o.o(jSONObject2.toString());
                    this.h = true;
                } catch (JSONException e3) {
                    com.google.a.a.a.a.a.a.a(e3);
                }
                intent.removeExtra(Jsonkey.BranchData.getKey());
                activity.setIntent(intent);
            }
        }
        if (this.y == INTENT_STATE.READY) {
            if (uri != null) {
                try {
                    if (!a(activity)) {
                        String a2 = ac.a(this.q).a(uri.toString());
                        this.g = a2;
                        this.o.h(a2);
                        if (!(a2 == null || !a2.equals(uri.toString()) || activity == null || activity.getIntent() == null || activity.getIntent().getExtras() == null)) {
                            Bundle extras = activity.getIntent().getExtras();
                            Set keySet = extras.keySet();
                            if (keySet.size() > 0) {
                                JSONObject jSONObject3 = new JSONObject();
                                for (String str2 : H) {
                                    if (keySet.contains(str2)) {
                                        jSONObject3.put(str2, extras.get(str2));
                                    }
                                }
                                if (jSONObject3.length() > 0) {
                                    this.o.i(jSONObject3.toString());
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
            if (activity != null) {
                try {
                    if (!(activity.getIntent() == null || activity.getIntent().getExtras() == null || a(activity))) {
                        String string = activity.getIntent().getExtras().getString(Jsonkey.AndroidPushNotificationKey.getKey());
                        if (string != null && string.length() > 0) {
                            this.o.n(string);
                            Intent intent2 = activity.getIntent();
                            intent2.putExtra(Jsonkey.BranchLinkUsed.getKey(), true);
                            activity.setIntent(intent2);
                            return false;
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (uri != null && uri.isHierarchical() && activity != null && !b(activity)) {
                try {
                    if (uri.getQueryParameter(Jsonkey.LinkClickID.getKey()) != null) {
                        this.o.j(uri.getQueryParameter(Jsonkey.LinkClickID.getKey()));
                        StringBuilder sb = new StringBuilder();
                        sb.append("link_click_id=");
                        sb.append(uri.getQueryParameter(Jsonkey.LinkClickID.getKey()));
                        String sb2 = sb.toString();
                        String str3 = null;
                        if (activity.getIntent() != null) {
                            str3 = activity.getIntent().getDataString();
                        }
                        if (uri.getQuery().length() == sb2.length()) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("\\?");
                            sb3.append(sb2);
                            str = sb3.toString();
                        } else if (str3 == null || str3.length() - sb2.length() != str3.indexOf(sb2)) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(sb2);
                            sb4.append("&");
                            str = sb4.toString();
                        } else {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("&");
                            sb5.append(sb2);
                            str = sb5.toString();
                        }
                        if (str3 != null) {
                            activity.getIntent().setData(Uri.parse(str3.replaceFirst(str, "")));
                            activity.getIntent().putExtra(Jsonkey.BranchLinkUsed.getKey(), true);
                        } else {
                            Log.w("BranchSDK", "Branch Warning. URI for the launcher activity is null. Please make sure that intent data is not set to null before calling Branch#InitSession ");
                        }
                        return true;
                    }
                    String scheme = uri.getScheme();
                    Intent intent3 = activity.getIntent();
                    if (!(scheme == null || intent3 == null || ((!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https")) || uri.getHost() == null || uri.getHost().length() <= 0 || a(activity)))) {
                        if (uri.toString().equalsIgnoreCase(ac.a(this.q).a(uri.toString()))) {
                            this.o.m(uri.toString());
                        }
                        intent3.putExtra(Jsonkey.BranchLinkUsed.getKey(), true);
                        activity.setIntent(intent3);
                        return false;
                    }
                } catch (Exception unused3) {
                }
            }
        }
        return false;
    }

    private boolean a(Activity activity) {
        return (activity == null || activity.getIntent() == null || !activity.getIntent().getBooleanExtra(Jsonkey.BranchLinkUsed.getKey(), false)) ? false : true;
    }

    private boolean b(Activity activity) {
        return (activity == null || activity.getIntent() == null || (activity.getIntent().getFlags() & 1048576) == 0) ? false : true;
    }

    public void e() {
        this.E = false;
        this.s.a(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        if (this.K) {
            s();
            this.K = false;
            return;
        }
        n();
    }

    public void f() {
        this.s.a(PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
        n();
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        ac.a(this.q).b(this.q);
    }

    public void a(@NonNull String str, JSONObject jSONObject) {
        a(str, jSONObject, (io.branch.referral.i.b) null);
    }

    public void a(@NonNull String str, JSONObject jSONObject, io.branch.referral.i.b bVar) {
        n nVar = new n(this.q, str, jSONObject, bVar);
        if (!nVar.e && !nVar.a(this.q)) {
            a((ServerRequest) nVar);
        }
    }

    public JSONObject h() {
        return a(a(this.o.v()));
    }

    public JSONObject i() {
        return a(a(this.o.u()));
    }

    private JSONObject a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (this.i != null) {
                    if (this.i.length() > 0) {
                        Log.w("BranchSDK", "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
                    }
                    Iterator keys = this.i.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        jSONObject.put(str, this.i.get(str));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    public JSONObject j() {
        if (this.i != null && this.i.length() > 0) {
            Log.w("BranchSDK", "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
        }
        return this.i;
    }

    /* access modifiers changed from: 0000 */
    public String a(o oVar) {
        if (!oVar.e && !oVar.a(this.q)) {
            if (this.v.containsKey(oVar.u())) {
                String str = (String) this.v.get(oVar.u());
                oVar.a(str);
                return str;
            } else if (!oVar.y()) {
                return b(oVar);
            } else {
                b((ServerRequest) oVar);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void a(l lVar) {
        if (this.B != null) {
            this.B.a(true);
        }
        this.B = new ShareLinkManager();
        this.B.a(lVar);
    }

    private String b(o oVar) {
        z zVar;
        if (this.N.a()) {
            return oVar.w();
        }
        String str = null;
        if (this.A == SESSION_STATE.INITIALISED) {
            try {
                int b2 = this.o.b() + 2000;
                zVar = (z) new m().execute(new ServerRequest[]{oVar}).get((long) b2, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException unused) {
                zVar = null;
            }
            if (oVar.v()) {
                str = oVar.w();
            }
            if (zVar != null && zVar.a() == 200) {
                try {
                    String string = zVar.b().getString("url");
                    try {
                        if (oVar.u() != null) {
                            this.v.put(oVar.u(), string);
                        }
                        str = string;
                    } catch (JSONException e2) {
                        e = e2;
                        str = string;
                        com.google.a.a.a.a.a.a.a(e);
                        return str;
                    }
                } catch (JSONException e3) {
                    e = e3;
                    com.google.a.a.a.a.a.a.a(e);
                    return str;
                }
            }
            return str;
        }
        Log.i("BranchSDK", "Branch Warning: User session has not been initialized");
        return null;
    }

    private void b(ServerRequest serverRequest) {
        a(serverRequest);
    }

    private JSONObject a(String str) {
        if (str.equals("bnc_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            try {
                return new JSONObject(new String(b.a(str.getBytes(), 2)));
            } catch (JSONException e2) {
                com.google.a.a.a.a.a.a.a(e2);
                return new JSONObject();
            }
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        try {
            this.r.acquire();
            if (this.t != 0 || this.s.a() <= 0) {
                this.r.release();
                return;
            }
            this.t = 1;
            ServerRequest c2 = this.s.c();
            this.r.release();
            if (c2 == null) {
                this.s.b((ServerRequest) null);
            } else if (c2.p()) {
                this.t = 0;
            } else if (!(c2 instanceof x) && !r()) {
                Log.i("BranchSDK", "Branch Error: User session has not been initialized!");
                this.t = 0;
                a(this.s.a() - 1, -101);
            } else if ((c2 instanceof s) || (p() && q())) {
                new e(c2).executeTask(new Void[0]);
            } else {
                this.t = 0;
                a(this.s.a() - 1, -101);
            }
        } catch (Exception e2) {
            com.google.a.a.a.a.a.a.a(e2);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3) {
        ServerRequest serverRequest;
        if (i2 >= this.s.a()) {
            serverRequest = this.s.a(this.s.a() - 1);
        } else {
            serverRequest = this.s.a(i2);
        }
        a(serverRequest, i3);
    }

    private void a(ServerRequest serverRequest, int i2) {
        if (serverRequest != null) {
            serverRequest.a(i2, "");
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        int i2 = 0;
        while (i2 < this.s.a()) {
            try {
                ServerRequest a2 = this.s.a(i2);
                if (a2 != null) {
                    JSONObject h2 = a2.h();
                    if (h2 != null) {
                        if (h2.has(Jsonkey.SessionID.getKey())) {
                            a2.h().put(Jsonkey.SessionID.getKey(), this.o.h());
                        }
                        if (h2.has(Jsonkey.IdentityID.getKey())) {
                            a2.h().put(Jsonkey.IdentityID.getKey(), this.o.i());
                        }
                        if (h2.has(Jsonkey.DeviceFingerprintID.getKey())) {
                            a2.h().put(Jsonkey.DeviceFingerprintID.getKey(), this.o.g());
                        }
                    }
                }
                i2++;
            } catch (JSONException e2) {
                com.google.a.a.a.a.a.a.a(e2);
                return;
            }
        }
    }

    private boolean p() {
        return !this.o.h().equals("bnc_no_value");
    }

    private boolean q() {
        return !this.o.g().equals("bnc_no_value");
    }

    private boolean r() {
        return !this.o.i().equals("bnc_no_value");
    }

    private void c(ServerRequest serverRequest) {
        if (this.t == 0) {
            this.s.a(serverRequest, 0);
        } else {
            this.s.a(serverRequest, 1);
        }
    }

    private void a(ServerRequest serverRequest, f fVar) {
        if (!this.s.f()) {
            c(serverRequest);
        } else {
            if (fVar != null) {
                this.s.a(fVar);
            }
            this.s.a(serverRequest, this.t, fVar);
        }
        n();
    }

    private void b(f fVar) {
        if (this.o.f() == null || this.o.f().equalsIgnoreCase("bnc_no_value")) {
            this.A = SESSION_STATE.UNINITIALISED;
            if (fVar != null) {
                fVar.a(null, new c("Trouble initializing Branch.", -114));
            }
            Log.i("BranchSDK", "Branch Warning: Please enter your branch_key in your project's res/values/strings.xml!");
            return;
        }
        if (this.o.f() != null && this.o.f().startsWith("key_test_")) {
            Log.i("BranchSDK", "Branch Warning: You are using your test app's Branch Key. Remember to change it to live Branch Key during deployment.");
        }
        if (!this.o.m().equals("bnc_no_value") || !this.k) {
            a(fVar, (PROCESS_WAIT_LOCK) null);
        } else if (j.a(this.q, new io.branch.referral.j.a() {
            public void a(String str) {
                Branch.this.o.a(Boolean.valueOf(true));
                if (str != null) {
                    String queryParameter = Uri.parse(str).getQueryParameter(Jsonkey.LinkClickID.getKey());
                    if (!TextUtils.isEmpty(queryParameter)) {
                        Branch.this.o.j(queryParameter);
                    }
                }
                Branch.this.s.a(PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
                Branch.this.n();
            }
        }).booleanValue()) {
            a(fVar, PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
        } else {
            a(fVar, (PROCESS_WAIT_LOCK) null);
        }
    }

    private void a(f fVar, PROCESS_WAIT_LOCK process_wait_lock) {
        ServerRequest c2 = c(fVar);
        c2.a(process_wait_lock);
        if (this.E) {
            c2.a(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        }
        if (this.y != INTENT_STATE.READY) {
            c2.a(PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        }
        if (c && (c2 instanceof x) && !InstallListener.unReportedReferrerAvailable) {
            c2.a(PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
            InstallListener.captureInstallReferrer(this.q, l, this);
        }
        a(c2, fVar);
    }

    private ServerRequest c(f fVar) {
        if (r()) {
            return new y(this.q, fVar, this.p);
        }
        return new x(this.q, fVar, this.p, InstallListener.getInstallationID());
    }

    /* access modifiers changed from: private */
    public void a(Activity activity, boolean z2) {
        this.s.a(PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        if (z2) {
            b(activity.getIntent().getData(), activity);
            if (a() || F == null || this.o.f() == null || this.o.f().equalsIgnoreCase("bnc_no_value")) {
                n();
            } else if (this.E) {
                this.K = true;
            } else {
                s();
            }
        } else {
            n();
        }
    }

    private void s() {
        if (!this.N.a()) {
            k a2 = k.a(this.o.G(), this.p, j);
            Activity activity = this.e != null ? (Activity) this.e.get() : null;
            Context applicationContext = activity != null ? activity.getApplicationContext() : null;
            if (applicationContext != null) {
                this.s.g();
                f.a().a(applicationContext, F, a2, this.o, this.p, new b() {
                    public void a() {
                        Branch.this.s.a(PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                        Branch.this.n();
                    }
                });
            }
        }
    }

    public void a(ServerRequest serverRequest) {
        if (this.N.a()) {
            serverRequest.s();
            return;
        }
        if (this.A != SESSION_STATE.INITIALISED && !(serverRequest instanceof s)) {
            if (serverRequest instanceof t) {
                serverRequest.a(-101, "");
                Log.i("BranchSDK", "Branch is not initialized, cannot logout");
                return;
            } else if (serverRequest instanceof w) {
                Log.i("BranchSDK", "Branch is not initialized, cannot close session");
                return;
            } else {
                Activity activity = this.e != null ? (Activity) this.e.get() : null;
                boolean z2 = true;
                if (C == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
                    a((f) null, activity, true);
                } else {
                    if (C != CUSTOM_REFERRABLE_SETTINGS.REFERRABLE) {
                        z2 = false;
                    }
                    a((f) null, activity, z2);
                }
            }
        }
        this.s.a(serverRequest);
        serverRequest.n();
        n();
    }

    @TargetApi(14)
    private void a(Application application) {
        try {
            a aVar = new a();
            application.unregisterActivityLifecycleCallbacks(aVar);
            application.registerActivityLifecycleCallbacks(aVar);
            x = true;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            x = false;
            w = false;
            Log.w("BranchSDK", new c("", -108).a());
        }
    }

    /* access modifiers changed from: private */
    public void c(Activity activity) {
        Uri data = activity.getIntent() != null ? activity.getIntent().getData() : null;
        this.f = false;
        a(data, activity);
    }

    /* access modifiers changed from: private */
    public boolean a(Intent intent) {
        boolean z2;
        if (intent == null) {
            return false;
        }
        try {
            z2 = intent.getBooleanExtra(Jsonkey.ForceNewBranchSession.getKey(), false);
        } catch (Throwable unused) {
            z2 = false;
        }
        if (z2) {
            intent.putExtra(Jsonkey.ForceNewBranchSession.getKey(), false);
        }
        return z2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0104, code lost:
        android.util.Log.i("BranchSDK", "Branch Warning: Please make sure Activity names set for auto deep link are correct!");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[ExcHandler: NameNotFoundException (unused android.content.pm.PackageManager$NameNotFoundException), SYNTHETIC, Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[ExcHandler: Exception (unused java.lang.Exception), SYNTHETIC, Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void t() {
        /*
            r9 = this;
            org.json.JSONObject r0 = r9.i()
            r1 = 0
            io.branch.referral.Defines$Jsonkey r2 = io.branch.referral.Defines.Jsonkey.Clicked_Branch_Link     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r2 = r2.getKey()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            boolean r2 = r0.has(r2)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r2 == 0) goto L_0x00ec
            io.branch.referral.Defines$Jsonkey r2 = io.branch.referral.Defines.Jsonkey.Clicked_Branch_Link     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r2 = r2.getKey()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            boolean r2 = r0.getBoolean(r2)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r2 != 0) goto L_0x001f
            goto L_0x00ec
        L_0x001f:
            int r2 = r0.length()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r2 <= 0) goto L_0x010b
            android.content.Context r2 = r9.q     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.content.Context r3 = r9.q     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.os.Bundle r3 = r2.metaData     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            r4 = 0
            if (r3 == 0) goto L_0x0047
            android.os.Bundle r2 = r2.metaData     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r3 = "io.branch.sdk.auto_link_disable"
            boolean r2 = r2.getBoolean(r3, r4)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r2 == 0) goto L_0x0047
            return
        L_0x0047:
            android.content.Context r2 = r9.q     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.content.Context r3 = r9.q     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            r5 = 129(0x81, float:1.81E-43)
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r5)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.content.pm.ActivityInfo[] r2 = r2.activities     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            r3 = 1501(0x5dd, float:2.103E-42)
            if (r2 == 0) goto L_0x009b
            int r5 = r2.length     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
        L_0x0060:
            if (r4 >= r5) goto L_0x009b
            r6 = r2[r4]     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r6 == 0) goto L_0x0098
            android.os.Bundle r7 = r6.metaData     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r7 == 0) goto L_0x0098
            android.os.Bundle r7 = r6.metaData     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r8 = "io.branch.sdk.auto_link_keys"
            java.lang.String r7 = r7.getString(r8)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r7 != 0) goto L_0x007e
            android.os.Bundle r7 = r6.metaData     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r8 = "io.branch.sdk.auto_link_path"
            java.lang.String r7 = r7.getString(r8)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r7 == 0) goto L_0x0098
        L_0x007e:
            boolean r7 = r9.a(r0, r6)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r7 != 0) goto L_0x008a
            boolean r7 = r9.b(r0, r6)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r7 == 0) goto L_0x0098
        L_0x008a:
            java.lang.String r2 = r6.name     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.os.Bundle r1 = r6.metaData     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x0096, Exception -> 0x010b }
            java.lang.String r4 = "io.branch.sdk.auto_link_request_code"
            int r3 = r1.getInt(r4, r3)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x0096, Exception -> 0x010b }
            r1 = r2
            goto L_0x009b
        L_0x0096:
            r1 = r2
            goto L_0x00ed
        L_0x0098:
            int r4 = r4 + 1
            goto L_0x0060
        L_0x009b:
            if (r1 == 0) goto L_0x010b
            java.lang.ref.WeakReference<android.app.Activity> r2 = r9.e     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r2 == 0) goto L_0x010b
            java.lang.ref.WeakReference<android.app.Activity> r2 = r9.e     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.Object r2 = r2.get()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            android.app.Activity r2 = (android.app.Activity) r2     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r2 == 0) goto L_0x00e4
            android.content.Intent r4 = new android.content.Intent     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.Class r5 = java.lang.Class.forName(r1)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            r4.<init>(r2, r5)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r5 = "io.branch.sdk.auto_linked"
            java.lang.String r6 = "true"
            r4.putExtra(r5, r6)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines.Jsonkey.ReferringData     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r5 = r5.getKey()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r6 = r0.toString()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            r4.putExtra(r5, r6)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.util.Iterator r5 = r0.keys()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
        L_0x00cc:
            boolean r6 = r5.hasNext()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            if (r6 == 0) goto L_0x00e0
            java.lang.Object r6 = r5.next()     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            java.lang.String r7 = r0.getString(r6)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            r4.putExtra(r6, r7)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            goto L_0x00cc
        L_0x00e0:
            r2.startActivityForResult(r4, r3)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            goto L_0x010b
        L_0x00e4:
            java.lang.String r0 = "BranchSDK"
            java.lang.String r2 = "No activity reference to launch deep linked activity"
            android.util.Log.w(r0, r2)     // Catch:{ NameNotFoundException -> 0x0104, ClassNotFoundException -> 0x00ed, Exception -> 0x010b }
            goto L_0x010b
        L_0x00ec:
            return
        L_0x00ed:
            java.lang.String r0 = "BranchSDK"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Branch Warning: Please make sure Activity names set for auto deep link are correct! Error while looking for activity "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            android.util.Log.i(r0, r1)
            goto L_0x010b
        L_0x0104:
            java.lang.String r0 = "BranchSDK"
            java.lang.String r1 = "Branch Warning: Please make sure Activity names set for auto deep link are correct!"
            android.util.Log.i(r0, r1)
        L_0x010b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.Branch.t():void");
    }

    private boolean a(JSONObject jSONObject, ActivityInfo activityInfo) {
        if (activityInfo.metaData.getString("io.branch.sdk.auto_link_keys") != null) {
            for (String has : activityInfo.metaData.getString("io.branch.sdk.auto_link_keys").split(",")) {
                if (jSONObject.has(has)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(org.json.JSONObject r5, android.content.pm.ActivityInfo r6) {
        /*
            r4 = this;
            r0 = 0
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.AndroidDeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            boolean r1 = r5.has(r1)     // Catch:{ JSONException -> 0x0030 }
            if (r1 == 0) goto L_0x0019
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.AndroidDeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r5 = r5.getString(r1)     // Catch:{ JSONException -> 0x0030 }
        L_0x0017:
            r0 = r5
            goto L_0x0030
        L_0x0019:
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.DeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            boolean r1 = r5.has(r1)     // Catch:{ JSONException -> 0x0030 }
            if (r1 == 0) goto L_0x0030
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.DeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r5 = r5.getString(r1)     // Catch:{ JSONException -> 0x0030 }
            goto L_0x0017
        L_0x0030:
            android.os.Bundle r5 = r6.metaData
            java.lang.String r1 = "io.branch.sdk.auto_link_path"
            java.lang.String r5 = r5.getString(r1)
            r1 = 0
            if (r5 == 0) goto L_0x0060
            if (r0 == 0) goto L_0x0060
            android.os.Bundle r5 = r6.metaData
            java.lang.String r6 = "io.branch.sdk.auto_link_path"
            java.lang.String r5 = r5.getString(r6)
            java.lang.String r6 = ","
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            r2 = r1
        L_0x004d:
            if (r2 >= r6) goto L_0x0060
            r3 = r5[r2]
            java.lang.String r3 = r3.trim()
            boolean r3 = r4.e(r3, r0)
            if (r3 == 0) goto L_0x005d
            r5 = 1
            return r5
        L_0x005d:
            int r2 = r2 + 1
            goto L_0x004d
        L_0x0060:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.Branch.b(org.json.JSONObject, android.content.pm.ActivityInfo):boolean");
    }

    private boolean e(String str, String str2) {
        boolean z2 = false;
        String[] split = str.split("\\?")[0].split("/");
        String[] split2 = str2.split("\\?")[0].split("/");
        if (split.length != split2.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 < split.length && i2 < split2.length) {
                String str3 = split[i2];
                if (!str3.equals(split2[i2]) && !str3.contains("*")) {
                    break;
                }
                i2++;
            } else {
                z2 = true;
            }
        }
        return z2;
    }

    public static void k() {
        b = Boolean.valueOf(true);
    }

    public void a(BranchUniversalObject branchUniversalObject, io.branch.indexing.BranchUniversalObject.b bVar) {
        if (this.q != null) {
            new io.branch.referral.util.a(BRANCH_STANDARD_EVENT.VIEW_ITEM).a(branchUniversalObject).a(this.q);
        }
    }

    public void a(String str, String str2) {
        this.D.put(str, str2);
    }

    public void c(String str, String str2) {
        if (s.a(str)) {
            t();
        }
    }

    public void d(String str, String str2) {
        if (s.a(str)) {
            t();
        }
    }

    public void a(int i2, String str, String str2) {
        if (s.a(str2)) {
            t();
        }
    }
}
