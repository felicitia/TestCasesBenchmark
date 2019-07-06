package com.firebase.jobdispatcher;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.firebase.jobdispatcher.q.a;
import com.firebase.jobdispatcher.q.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JobCoder */
final class m {
    private final String a;

    m(String str) {
        this.a = str;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Bundle a(@NonNull o oVar, @NonNull Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        Bundle b = oVar.b();
        if (b != null) {
            bundle.putAll(b);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("persistent");
        bundle.putInt(sb.toString(), oVar.g());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.a);
        sb2.append("recurring");
        bundle.putBoolean(sb2.toString(), oVar.h());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.a);
        sb3.append("replace_current");
        bundle.putBoolean(sb3.toString(), oVar.d());
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.a);
        sb4.append("tag");
        bundle.putString(sb4.toString(), oVar.e());
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.a);
        sb5.append(NotificationCompat.CATEGORY_SERVICE);
        bundle.putString(sb5.toString(), oVar.i());
        StringBuilder sb6 = new StringBuilder();
        sb6.append(this.a);
        sb6.append("constraints");
        bundle.putInt(sb6.toString(), a.a(oVar.a()));
        a(oVar.f(), bundle);
        a(oVar.c(), bundle);
        return bundle;
    }

    /* access modifiers changed from: 0000 */
    public n a(@NonNull Bundle bundle) {
        if (bundle == null) {
            Log.e("FJD.ExternalReceiver", "Unexpected null Bundle provided");
            return null;
        }
        Bundle bundle2 = bundle.getBundle("extras");
        if (bundle2 == null) {
            return null;
        }
        a b = b(bundle2);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("triggered_uris");
        if (parcelableArrayList != null) {
            b.a(new v(parcelableArrayList));
        }
        return b.a();
    }

    @Nullable
    public a b(@NonNull Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Unexpected null Bundle provided");
        }
        Bundle bundle2 = new Bundle(bundle);
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("recurring");
        boolean z = bundle2.getBoolean(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.a);
        sb2.append("replace_current");
        boolean z2 = bundle2.getBoolean(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.a);
        sb3.append("persistent");
        int i = bundle2.getInt(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.a);
        sb4.append("constraints");
        int[] a2 = a.a(bundle2.getInt(sb4.toString()));
        q c = c(bundle2);
        t d = d(bundle2);
        StringBuilder sb5 = new StringBuilder();
        sb5.append(this.a);
        sb5.append("tag");
        String string = bundle2.getString(sb5.toString());
        StringBuilder sb6 = new StringBuilder();
        sb6.append(this.a);
        sb6.append(NotificationCompat.CATEGORY_SERVICE);
        String string2 = bundle2.getString(sb6.toString());
        if (string == null || string2 == null || c == null || d == null) {
            return null;
        }
        a aVar = new a();
        aVar.a(string);
        aVar.b(string2);
        aVar.a(c);
        aVar.a(d);
        aVar.a(z);
        aVar.a(i);
        aVar.a(a2);
        aVar.b(z2);
        if (!TextUtils.isEmpty(this.a)) {
            Iterator it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                if (((String) it.next()).startsWith(this.a)) {
                    it.remove();
                }
            }
        }
        aVar.a(bundle2);
        return aVar;
    }

    @NonNull
    private q c(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("trigger_type");
        switch (bundle.getInt(sb.toString())) {
            case 1:
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.a);
                sb2.append("window_start");
                int i = bundle.getInt(sb2.toString());
                StringBuilder sb3 = new StringBuilder();
                sb3.append(this.a);
                sb3.append("window_end");
                return u.a(i, bundle.getInt(sb3.toString()));
            case 2:
                return u.a;
            case 3:
                StringBuilder sb4 = new StringBuilder();
                sb4.append(this.a);
                sb4.append("observed_uris");
                return u.a(Collections.unmodifiableList(a(bundle.getString(sb4.toString()))));
            default:
                if (Log.isLoggable("FJD.ExternalReceiver", 3)) {
                    Log.d("FJD.ExternalReceiver", "Unsupported trigger.");
                }
                return null;
        }
    }

    private void a(q qVar, Bundle bundle) {
        if (qVar == u.a) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("trigger_type");
            bundle.putInt(sb.toString(), 2);
        } else if (qVar instanceof b) {
            b bVar = (b) qVar;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.a);
            sb2.append("trigger_type");
            bundle.putInt(sb2.toString(), 1);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.a);
            sb3.append("window_start");
            bundle.putInt(sb3.toString(), bVar.a());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.a);
            sb4.append("window_end");
            bundle.putInt(sb4.toString(), bVar.b());
        } else if (qVar instanceof a) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.a);
            sb5.append("trigger_type");
            bundle.putInt(sb5.toString(), 3);
            String a2 = a(((a) qVar).a());
            StringBuilder sb6 = new StringBuilder();
            sb6.append(this.a);
            sb6.append("observed_uris");
            bundle.putString(sb6.toString(), a2);
        } else {
            throw new IllegalArgumentException("Unsupported trigger.");
        }
    }

    private t d(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("retry_policy");
        int i = bundle.getInt(sb.toString());
        if (i != 1 && i != 2) {
            return t.a;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.a);
        sb2.append("initial_backoff_seconds");
        int i2 = bundle.getInt(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.a);
        sb3.append("maximum_backoff_seconds");
        return new t(i, i2, bundle.getInt(sb3.toString()));
    }

    private void a(t tVar, Bundle bundle) {
        if (tVar == null) {
            tVar = t.a;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append("retry_policy");
        bundle.putInt(sb.toString(), tVar.a());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.a);
        sb2.append("initial_backoff_seconds");
        bundle.putInt(sb2.toString(), tVar.b());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.a);
        sb3.append("maximum_backoff_seconds");
        bundle.putInt(sb3.toString(), tVar.c());
    }

    @NonNull
    private static String a(@NonNull List<s> list) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        for (s sVar : list) {
            jSONArray.put(sVar.b());
            jSONArray2.put(sVar.a());
        }
        try {
            jSONObject.put("uri_flags", jSONArray);
            jSONObject.put("uris", jSONArray2);
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    private static List<s> a(@NonNull String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("uri_flags");
            JSONArray jSONArray2 = jSONObject.getJSONArray("uris");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(new s(Uri.parse(jSONArray2.getString(i)), jSONArray.getInt(i)));
            }
            return arrayList;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
