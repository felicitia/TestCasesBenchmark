package io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.TextView;
import io.branch.referral.m;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ContentDiscoverer */
public class b {
    private static b a;
    /* access modifiers changed from: private */
    public Handler b = new Handler();
    /* access modifiers changed from: private */
    public WeakReference<Activity> c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public JSONObject e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public int g = 15;
    private final a h = new a();
    /* access modifiers changed from: private */
    public c i;
    private final Map<String, WeakReference<ViewTreeObserver>> j = new HashMap();
    /* access modifiers changed from: private */
    public ArrayList<String> k = new ArrayList<>();
    /* access modifiers changed from: private */
    public Runnable l = new Runnable() {
        public void run() {
            JSONArray jSONArray;
            boolean z;
            try {
                b.this.f = b.this.f + 1;
                if (b.this.i.a() && b.this.c != null && b.this.c.get() != null) {
                    Activity activity = (Activity) b.this.c.get();
                    b.this.e = new JSONObject();
                    b.this.e.put("ts", System.currentTimeMillis());
                    if (!TextUtils.isEmpty(b.this.d)) {
                        b.this.e.put("rl", b.this.d);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("/");
                    sb.append(activity.getClass().getSimpleName());
                    String sb2 = sb.toString();
                    b.this.e.put("v", sb2);
                    ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
                    if (viewGroup != null) {
                        a a2 = b.this.i.a(activity);
                        boolean z2 = a2 != null && a2.d();
                        if (a2 != null) {
                            boolean d = a2.d();
                            b.this.e.put("h", !d);
                            jSONArray = a2.c();
                            z = d;
                        } else {
                            z = z2;
                            jSONArray = null;
                        }
                        if (jSONArray != null && jSONArray.length() > 0) {
                            JSONArray jSONArray2 = new JSONArray();
                            b.this.e.put("ck", jSONArray2);
                            JSONArray jSONArray3 = new JSONArray();
                            b.this.e.put("cd", jSONArray3);
                            b.this.a(jSONArray, jSONArray3, jSONArray2, activity, z);
                        } else if (!b.this.k.contains(sb2)) {
                            JSONArray jSONArray4 = new JSONArray();
                            b.this.e.put("ck", jSONArray4);
                            b.this.a(viewGroup, jSONArray4, activity.getResources());
                        }
                        b.this.k.add(sb2);
                        m.a((Context) activity).a(b.this.e);
                        int a3 = b.this.i.a(activity).a();
                        b.this.g = b.this.i.a(activity).b();
                        if (b.this.f < b.this.g && a3 >= 500 && jSONArray != null && jSONArray.length() > 0) {
                            b.this.b.postDelayed(b.this.l, (long) a3);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    };
    private OnScrollChangedListener m = new OnScrollChangedListener() {
        public void onScrollChanged() {
            b.this.b.removeCallbacks(b.this.n);
            if (b.this.g > b.this.f) {
                b.this.b.postDelayed(b.this.n, 1500);
            }
        }
    };
    /* access modifiers changed from: private */
    public Runnable n = new Runnable() {
        public void run() {
            b.this.l.run();
        }
    };

    /* compiled from: ContentDiscoverer */
    private class a {
        MessageDigest a;

        a() {
            try {
                this.a = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException unused) {
            }
        }

        /* access modifiers changed from: 0000 */
        public String a(String str) {
            String str2 = "";
            if (this.a == null) {
                return str2;
            }
            this.a.reset();
            this.a.update(str.getBytes());
            return new String(this.a.digest());
        }
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    private b() {
    }

    public void a(Activity activity, String str) {
        this.i = c.a((Context) activity);
        this.d = str;
        a a2 = this.i.a(activity);
        if (a2 != null) {
            if (!a2.e()) {
                b(activity);
            }
        } else if (!TextUtils.isEmpty(this.d)) {
            b(activity);
        }
    }

    public void a(Activity activity) {
        if (!(this.c == null || this.c.get() == null || !((Activity) this.c.get()).getClass().getName().equals(activity.getClass().getName()))) {
            this.b.removeCallbacks(this.l);
            this.c = null;
        }
        b();
        for (WeakReference weakReference : this.j.values()) {
            ViewTreeObserver viewTreeObserver = (ViewTreeObserver) weakReference.get();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnScrollChangedListener(this.m);
            }
        }
        this.j.clear();
    }

    public void b(Activity activity, String str) {
        this.k = new ArrayList<>();
        a(activity, str);
    }

    private void b(Activity activity) {
        this.f = 0;
        if (this.k.size() < this.i.d()) {
            this.b.removeCallbacks(this.l);
            this.c = new WeakReference<>(activity);
            this.b.postDelayed(this.l, 1000);
        }
    }

    private void b() {
        try {
            if (this.e != null) {
                this.e.put("tc", System.currentTimeMillis());
            }
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void a(ViewGroup viewGroup, JSONArray jSONArray, Resources resources) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                if ((childAt instanceof AbsListView) || childAt.getClass().getSimpleName().equals("RecyclerView")) {
                    a((ViewGroup) childAt, resources, jSONArray);
                } else if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, jSONArray, resources);
                } else if (childAt instanceof TextView) {
                    jSONArray.put(a(childAt, resources));
                }
            }
        }
    }

    private void a(ViewGroup viewGroup, Resources resources, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        if (viewGroup != null && viewGroup.getChildCount() > -1) {
            int i2 = 1;
            if (viewGroup.getChildCount() <= 1) {
                i2 = 0;
            }
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != null) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    jSONObject.put(a((View) viewGroup, resources), jSONArray2);
                } catch (JSONException e2) {
                    com.google.a.a.a.a.a.a.a(e2);
                }
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, jSONArray2, resources);
                } else if (childAt instanceof TextView) {
                    jSONArray2.put(a(childAt, resources));
                }
                if (jSONObject.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("$");
                    sb.append(jSONObject);
                    jSONArray.put(sb.toString());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, Activity activity, boolean z) {
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            try {
                String string = jSONArray.getString(i2);
                if (string.startsWith("$")) {
                    a(string, activity, z, jSONArray2, jSONArray3);
                } else {
                    a(string, activity.findViewById(activity.getResources().getIdentifier(jSONArray.getString(i2), "id", activity.getPackageName())), z, jSONArray2, jSONArray3);
                }
                i2++;
            } catch (JSONException unused) {
                return;
            }
        }
    }

    private void a(String str, Activity activity, boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        JSONObject jSONObject = new JSONObject();
        jSONArray2.put(str);
        jSONArray.put(jSONObject);
        String replace = str.replace("$", "");
        try {
            JSONObject jSONObject2 = new JSONObject(replace);
            if (jSONObject2.length() > 0) {
                String str2 = (String) jSONObject2.keys().next();
                int identifier = activity.getResources().getIdentifier(str2, "id", activity.getPackageName());
                View findViewById = activity.getCurrentFocus() != null ? activity.getCurrentFocus().findViewById(identifier) : null;
                if (findViewById == null) {
                    findViewById = activity.findViewById(identifier);
                }
                if (findViewById != null && (findViewById instanceof ViewGroup)) {
                    ViewGroup viewGroup = (ViewGroup) findViewById;
                    JSONArray jSONArray3 = jSONObject2.getJSONArray(str2);
                    int[] iArr = new int[jSONArray3.length()];
                    boolean z2 = false;
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        iArr[i2] = activity.getResources().getIdentifier(jSONArray3.getString(i2), "id", activity.getPackageName());
                    }
                    int firstVisiblePosition = viewGroup instanceof AbsListView ? ((AbsListView) viewGroup).getFirstVisiblePosition() : 0;
                    for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                        if (viewGroup.getChildAt(i3) != null) {
                            JSONObject jSONObject3 = new JSONObject();
                            StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(i3 + firstVisiblePosition);
                            jSONObject.put(sb.toString(), jSONObject3);
                            for (int i4 = 0; i4 < iArr.length; i4++) {
                                if (viewGroup.getChildAt(i3) != null) {
                                    View findViewById2 = viewGroup.getChildAt(i3).findViewById(iArr[i4]);
                                    if (findViewById2 instanceof TextView) {
                                        jSONObject3.put(jSONArray3.getString(i4), a(findViewById2, z));
                                    }
                                }
                            }
                        }
                    }
                    if (jSONObject2.has("bnc_esw") && jSONObject2.getBoolean("bnc_esw")) {
                        z2 = true;
                    }
                    if (z2 && !this.j.containsKey(replace)) {
                        viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.m);
                        this.j.put(replace, new WeakReference(viewGroup.getViewTreeObserver()));
                    }
                }
            }
        } catch (JSONException e2) {
            com.google.a.a.a.a.a.a.a(e2);
        }
    }

    private String a(View view, Resources resources) {
        try {
            return resources.getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return String.valueOf(view.getId());
        }
    }

    private String a(View view, boolean z) {
        TextView textView = (TextView) view;
        if (textView.getText() == null) {
            return null;
        }
        String substring = textView.getText().toString().substring(0, Math.min(textView.getText().toString().length(), this.i.b()));
        return z ? substring : this.h.a(substring);
    }

    private void a(String str, View view, boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        if (view instanceof TextView) {
            jSONArray.put(a(view, z));
            jSONArray2.put(str);
        }
    }

    public JSONObject a(Context context) {
        JSONObject jSONObject;
        JSONObject C = m.a(context).C();
        if (C.length() <= 0 || C.toString().length() >= this.i.c()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("mv", c.a(context).e()).put("e", C);
                if (context != null) {
                    jSONObject.put("p", context.getPackageName());
                    jSONObject.put("p", context.getPackageName());
                }
            } catch (JSONException e2) {
                com.google.a.a.a.a.a.a.a(e2);
            }
        }
        m.a(context).D();
        return jSONObject;
    }
}
