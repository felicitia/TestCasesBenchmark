package io.branch.referral;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import io.branch.referral.Defines.LinkParam;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BranchLinkData */
class d extends JSONObject {
    private Collection<String> a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private JSONObject h;
    private int i;

    public void a(Collection<String> collection) throws JSONException {
        if (collection != null) {
            this.a = collection;
            JSONArray jSONArray = new JSONArray();
            for (String put : collection) {
                jSONArray.put(put);
            }
            put(LinkParam.Tags.getKey(), jSONArray);
        }
    }

    public Collection<String> a() {
        return this.a;
    }

    public void a(String str) throws JSONException {
        if (str != null) {
            this.b = str;
            put(LinkParam.Alias.getKey(), str);
        }
    }

    public String b() {
        return this.b;
    }

    public void a(int i2) throws JSONException {
        if (i2 != 0) {
            this.c = i2;
            put(LinkParam.Type.getKey(), i2);
        }
    }

    public int c() {
        return this.c;
    }

    public void b(int i2) throws JSONException {
        if (i2 > 0) {
            this.i = i2;
            put(LinkParam.Duration.getKey(), i2);
        }
    }

    public int d() {
        return this.i;
    }

    public void b(String str) throws JSONException {
        if (str != null) {
            this.d = str;
            put(LinkParam.Channel.getKey(), str);
        }
    }

    public String e() {
        return this.d;
    }

    public void c(String str) throws JSONException {
        if (str != null) {
            this.e = str;
            put(LinkParam.Feature.getKey(), str);
        }
    }

    public String f() {
        return this.e;
    }

    public void d(String str) throws JSONException {
        if (str != null) {
            this.f = str;
            put(LinkParam.Stage.getKey(), str);
        }
    }

    public String g() {
        return this.f;
    }

    public void e(String str) throws JSONException {
        if (str != null) {
            this.g = str;
            put(LinkParam.Campaign.getKey(), str);
        }
    }

    public String h() {
        return this.g;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        this.h = jSONObject;
        put(LinkParam.Data.getKey(), jSONObject);
    }

    public JSONObject i() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.b == null) {
            if (dVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(dVar.b)) {
            return false;
        }
        if (this.d == null) {
            if (dVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(dVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (dVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(dVar.e)) {
            return false;
        }
        if (this.h == null) {
            if (dVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(dVar.h)) {
            return false;
        }
        if (this.f == null) {
            if (dVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(dVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (dVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(dVar.g)) {
            return false;
        }
        if (this.c != dVar.c || this.i != dVar.i) {
            return false;
        }
        if (this.a == null) {
            if (dVar.a != null) {
                return false;
            }
        } else if (!this.a.toString().equals(dVar.a.toString())) {
            return false;
        }
        return true;
    }

    @SuppressLint({"DefaultLocale"})
    public int hashCode() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = (this.c + 19) * 19;
        int i8 = 0;
        if (this.b == null) {
            i2 = 0;
        } else {
            i2 = this.b.toLowerCase().hashCode();
        }
        int i9 = (i7 + i2) * 19;
        if (this.d == null) {
            i3 = 0;
        } else {
            i3 = this.d.toLowerCase().hashCode();
        }
        int i10 = (i9 + i3) * 19;
        if (this.e == null) {
            i4 = 0;
        } else {
            i4 = this.e.toLowerCase().hashCode();
        }
        int i11 = (i10 + i4) * 19;
        if (this.f == null) {
            i5 = 0;
        } else {
            i5 = this.f.toLowerCase().hashCode();
        }
        int i12 = (i11 + i5) * 19;
        if (this.g == null) {
            i6 = 0;
        } else {
            i6 = this.g.toLowerCase().hashCode();
        }
        int i13 = (i12 + i6) * 19;
        if (this.h != null) {
            i8 = this.h.toString().toLowerCase().hashCode();
        }
        int i14 = ((i13 + i8) * 19) + this.i;
        if (this.a != null) {
            for (String lowerCase : this.a) {
                i14 = (i14 * 19) + lowerCase.toLowerCase().hashCode();
            }
        }
        return i14;
    }

    public JSONObject j() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.d)) {
                StringBuilder sb = new StringBuilder();
                sb.append("~");
                sb.append(LinkParam.Channel.getKey());
                jSONObject.put(sb.toString(), this.d);
            }
            if (!TextUtils.isEmpty(this.b)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("~");
                sb2.append(LinkParam.Alias.getKey());
                jSONObject.put(sb2.toString(), this.b);
            }
            if (!TextUtils.isEmpty(this.e)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("~");
                sb3.append(LinkParam.Feature.getKey());
                jSONObject.put(sb3.toString(), this.e);
            }
            if (!TextUtils.isEmpty(this.f)) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("~");
                sb4.append(LinkParam.Stage.getKey());
                jSONObject.put(sb4.toString(), this.f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("~");
                sb5.append(LinkParam.Campaign.getKey());
                jSONObject.put(sb5.toString(), this.g);
            }
            if (has(LinkParam.Tags.getKey())) {
                jSONObject.put(LinkParam.Tags.getKey(), getJSONArray(LinkParam.Tags.getKey()));
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("~");
            sb6.append(LinkParam.Type.getKey());
            jSONObject.put(sb6.toString(), this.c);
            StringBuilder sb7 = new StringBuilder();
            sb7.append("~");
            sb7.append(LinkParam.Duration.getKey());
            jSONObject.put(sb7.toString(), this.i);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
