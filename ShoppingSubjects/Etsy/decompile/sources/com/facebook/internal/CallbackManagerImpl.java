package com.facebook.internal;

import android.content.Intent;
import android.util.Log;
import com.facebook.d;
import com.facebook.f;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class CallbackManagerImpl implements d {
    private static final String a = "CallbackManagerImpl";
    private static Map<Integer, a> b = new HashMap();
    private Map<Integer, a> c = new HashMap();

    public enum RequestCodeOffset {
        Login(0),
        Share(1),
        Message(2),
        Like(3),
        GameRequest(4),
        AppGroupCreate(5),
        AppGroupJoin(6),
        AppInvite(7),
        DeviceShare(8),
        InAppPurchase(9);
        
        private final int offset;

        private RequestCodeOffset(int i) {
            this.offset = i;
        }

        public int toRequestCode() {
            return f.p() + this.offset;
        }
    }

    public interface a {
        boolean a(int i, Intent intent);
    }

    public static synchronized void a(int i, a aVar) {
        synchronized (CallbackManagerImpl.class) {
            aa.a((Object) aVar, "callback");
            if (!b.containsKey(Integer.valueOf(i))) {
                b.put(Integer.valueOf(i), aVar);
            }
        }
    }

    private static synchronized a a(Integer num) {
        a aVar;
        synchronized (CallbackManagerImpl.class) {
            aVar = (a) b.get(num);
        }
        return aVar;
    }

    private static boolean b(int i, int i2, Intent intent) {
        a a2 = a(Integer.valueOf(i));
        if (a2 != null) {
            return a2.a(i2, intent);
        }
        return false;
    }

    public void b(int i, a aVar) {
        aa.a((Object) aVar, "callback");
        this.c.put(Integer.valueOf(i), aVar);
    }

    public void a(int i) {
        this.c.remove(Integer.valueOf(i));
    }

    public boolean a(int i, int i2, Intent intent) {
        if (a(intent)) {
            i = RequestCodeOffset.InAppPurchase.toRequestCode();
        }
        a aVar = (a) this.c.get(Integer.valueOf(i));
        if (aVar != null) {
            return aVar.a(i2, intent);
        }
        return b(i, i2, intent);
    }

    private static boolean a(Intent intent) {
        boolean z = false;
        if (intent != null) {
            String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
            if (stringExtra != null) {
                try {
                    JSONObject jSONObject = new JSONObject(stringExtra);
                    if (jSONObject.has("orderId") && jSONObject.has("packageName") && jSONObject.has("productId") && jSONObject.has("purchaseTime") && jSONObject.has("purchaseState") && jSONObject.has("developerPayload") && jSONObject.has("purchaseToken")) {
                        z = true;
                    }
                    return z;
                } catch (JSONException e) {
                    Log.e(a, "Error parsing intent data.", e);
                    return false;
                }
            }
        }
        return false;
    }
}
