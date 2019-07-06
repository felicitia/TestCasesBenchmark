package defpackage;

import android.os.Build.VERSION;
import android.os.HandlerThread;
import android.util.SparseArray;
import io.ag.internal.manager.ntProcessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: p reason: default package */
/* compiled from: GA */
public final class p {
    public l a = null;
    public SparseArray<r> b = new SparseArray<>();
    public SparseArray<r> c = new SparseArray<>();
    public ArrayList<HandlerThread> d = new ArrayList<>();
    List<String> e = Collections.synchronizedList(new ArrayList());

    public p(l lVar) {
        if (lVar != null) {
            this.a = lVar;
        }
    }

    private JSONObject a(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            iArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        }
        JSONObject jSONObject = new JSONObject();
        a();
        try {
            for (int i : iArr) {
                r rVar = (r) this.b.get(i, null);
                if (rVar == null) {
                    rVar = (r) this.c.get(i, null);
                }
                JSONArray jSONArray = new JSONArray();
                if (rVar == null) {
                    jSONArray.put(c.m);
                } else if (!rVar.b() || rVar.a == null || rVar.a.size() <= 0) {
                    jSONArray.put("");
                } else {
                    for (String put : rVar.a) {
                        jSONArray.put(put);
                    }
                }
                jSONObject.accumulate(Integer.toString(i), jSONArray);
            }
            if (this.e != null && !this.e.isEmpty()) {
                jSONObject.put(c.w, new JSONArray(this.e));
            }
        } catch (JSONException unused) {
            al.b("M25: ");
        }
        return jSONObject;
    }

    public final JSONObject a(int[] iArr, int[] iArr2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject a2 = a(iArr);
            if (iArr2 != null) {
                if (iArr2.length != 0) {
                    char[] charArray = a2.toString().toCharArray();
                    int a3 = ntProcessor.a(charArray, iArr2, this.a.g, this.a.h);
                    String a4 = a(charArray);
                    if (a3 < 0 || a4.isEmpty()) {
                        JSONObject a5 = a((int[]) null);
                        jSONObject.put(c.s, 0);
                        jSONObject.put(c.n, a5);
                        jSONObject.put(c.v, a3);
                    } else {
                        jSONObject.put(c.s, 1);
                        jSONObject.put(c.u, a4);
                    }
                    jSONObject.put(c.t, 1);
                    return jSONObject;
                }
            }
            jSONObject.put(c.s, 0);
            jSONObject.put(c.n, a2);
            jSONObject.put(c.t, 1);
        } catch (JSONException unused) {
            al.b("M25: ");
        }
        return jSONObject;
    }

    public final void a() {
        for (int i = 0; i < this.c.size(); i++) {
            ((r) this.c.valueAt(i)).a();
        }
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            HandlerThread handlerThread = (HandlerThread) this.d.get(i2);
            if (VERSION.SDK_INT >= 18) {
                handlerThread.quitSafely();
            } else {
                handlerThread.quit();
            }
        }
        this.d.clear();
    }

    private static String a(char[] cArr) {
        StringBuilder sb = new StringBuilder();
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 < 55296 || c2 > 57343) {
                    sb.append(c2);
                } else {
                    sb.append(al.a(c2));
                }
            }
        }
        return sb.toString();
    }
}
