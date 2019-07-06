package com.salesforce.marketingcloud;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.b.b;
import com.salesforce.marketingcloud.c.e;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.c.g;
import java.util.EnumSet;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public class d implements b, com.salesforce.marketingcloud.c.f.a, g {
    private static final String a = j.a(d.class);
    private final com.salesforce.marketingcloud.b.c b;
    private final f c;
    private final SharedPreferences d;
    private final String e;
    private final b f;
    private a g;
    private c h = null;
    private c i = c.NONE;

    static abstract class a {
        a() {
        }

        /* access modifiers changed from: 0000 */
        public abstract void b(int i);
    }

    @VisibleForTesting
    public enum c {
        RTBF(4095),
        ROP(4094),
        DNT(1888),
        NONE(0);
        
        public final int e;

        private c(int i) {
            this.e = i;
        }

        public static c a(String str) {
            try {
                return valueOf(str);
            } catch (Exception unused) {
                return NONE;
            }
        }
    }

    d(String str, b bVar, SharedPreferences sharedPreferences, f fVar, com.salesforce.marketingcloud.b.c cVar) {
        this.e = str;
        this.f = bVar;
        this.d = sharedPreferences;
        this.b = cVar;
        this.c = fVar;
        String string = sharedPreferences.getString("cc_state", null);
        if (string != null) {
            this.i = c.a(string);
        }
        if (this.i != c.RTBF) {
            fVar.a(com.salesforce.marketingcloud.c.d.SYNC, (com.salesforce.marketingcloud.c.f.a) this);
            cVar.a((b) this, EnumSet.of(com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_PUSH_RECEIVED));
        }
    }

    private synchronized void a(int i2) {
        c cVar = b(i2, c.RTBF.e) ? c.RTBF : b(i2, c.ROP.e) ? c.ROP : b(i2, c.DNT.e) ? c.DNT : c.NONE;
        j.a(a, "Control Channel blocked value %d received", Integer.valueOf(i2));
        this.d.edit().putString("cc_state", cVar.name()).apply();
        if (cVar != this.i) {
            if (this.g != null) {
                this.i = cVar;
                this.g.b(this.i.e);
            } else {
                this.h = cVar;
            }
        }
    }

    private void a(@NonNull JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (jSONObject.getString(ResponseConstants.NAME).equals("blocked") && jSONObject.optInt(ResponseConstants.VERSION, -1) == 1) {
                a(jSONObject.getJSONObject(ResponseConstants.ITEMS).getInt("blocked"));
            }
        }
    }

    public static boolean a(int i2, int i3) {
        return !b(i2, i3);
    }

    public static boolean b(int i2, int i3) {
        return (i2 & i3) == i3;
    }

    private boolean c() {
        return this.i != c.RTBF && System.currentTimeMillis() > this.d.getLong("next_sync_time_millis", 0);
    }

    public static boolean c(int i2, int i3) {
        if (a(i2, i3)) {
            return false;
        }
        if (i3 != 2) {
            if (!(i3 == 4 || i3 == 8 || i3 == 16 || i3 == 32 || i3 == 64 || i3 == 128)) {
                if (!(i3 == 256 || i3 == 512 || i3 == 2048)) {
                    return false;
                }
            }
            return true;
        }
        if (c.ROP.e == i2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.i.e;
    }

    public void a(com.salesforce.marketingcloud.b.a aVar, @NonNull Bundle bundle) {
        switch (aVar) {
            case BEHAVIOR_APP_FOREGROUNDED:
                if (c()) {
                    this.c.a(com.salesforce.marketingcloud.c.d.SYNC.a(this.f, com.salesforce.marketingcloud.c.d.a(this.f.b(), this.e), "{}"));
                    break;
                }
                break;
            case BEHAVIOR_SDK_PUSH_RECEIVED:
                String string = bundle.getString("_nodes");
                if (string != null) {
                    try {
                        a(new JSONArray(string));
                        return;
                    } catch (Exception e2) {
                        j.c(a, e2, "Failed to parse push message", new Object[0]);
                        return;
                    }
                }
                break;
            default:
                return;
        }
    }

    public void a(e eVar, g gVar) {
        if (gVar.h()) {
            try {
                JSONArray jSONArray = new JSONObject(gVar.a()).getJSONArray("nodes");
                if (jSONArray != null) {
                    a(jSONArray);
                }
                this.d.edit().putLong("next_sync_time_millis", System.currentTimeMillis() + DateUtils.MILLIS_PER_DAY).apply();
            } catch (Exception e2) {
                j.c(a, e2, "Failed to parse /sync route response", new Object[0]);
            }
        } else {
            j.e("SYNC", gVar.b(), new Object[0]);
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized void a(a aVar) {
        this.g = aVar;
        if (!(aVar == null || this.h == null)) {
            this.i = this.h;
            this.h = null;
            aVar.b(this.i.e);
        }
    }

    public void a(boolean z) {
        this.b.a((b) this);
        this.c.a(com.salesforce.marketingcloud.c.d.SYNC);
        this.g = null;
    }

    @NonNull
    public String b() {
        return "ControlChannel";
    }
}
