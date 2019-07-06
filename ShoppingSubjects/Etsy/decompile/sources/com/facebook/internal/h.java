package com.facebook.internal;

import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookRequestError.Category;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FacebookRequestErrorClassification */
public final class h {
    private static h g;
    private final Map<Integer, Set<Integer>> a;
    private final Map<Integer, Set<Integer>> b;
    private final Map<Integer, Set<Integer>> c;
    private final String d;
    private final String e;
    private final String f;

    h(Map<Integer, Set<Integer>> map, Map<Integer, Set<Integer>> map2, Map<Integer, Set<Integer>> map3, String str, String str2, String str3) {
        this.a = map;
        this.b = map2;
        this.c = map3;
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    public String a(Category category) {
        switch (category) {
            case OTHER:
                return this.d;
            case LOGIN_RECOVERABLE:
                return this.f;
            case TRANSIENT:
                return this.e;
            default:
                return null;
        }
    }

    public Category a(int i, int i2, boolean z) {
        if (z) {
            return Category.TRANSIENT;
        }
        if (this.a != null && this.a.containsKey(Integer.valueOf(i))) {
            Set set = (Set) this.a.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return Category.OTHER;
            }
        }
        if (this.c != null && this.c.containsKey(Integer.valueOf(i))) {
            Set set2 = (Set) this.c.get(Integer.valueOf(i));
            if (set2 == null || set2.contains(Integer.valueOf(i2))) {
                return Category.LOGIN_RECOVERABLE;
            }
        }
        if (this.b != null && this.b.containsKey(Integer.valueOf(i))) {
            Set set3 = (Set) this.b.get(Integer.valueOf(i));
            if (set3 == null || set3.contains(Integer.valueOf(i2))) {
                return Category.TRANSIENT;
            }
        }
        return Category.OTHER;
    }

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (g == null) {
                g = b();
            }
            hVar = g;
        }
        return hVar;
    }

    private static h b() {
        h hVar = new h(null, new FacebookRequestErrorClassification$1(), new FacebookRequestErrorClassification$2(), null, null, null);
        return hVar;
    }

    private static Map<Integer, Set<Integer>> a(JSONObject jSONObject) {
        HashSet hashSet;
        JSONArray optJSONArray = jSONObject.optJSONArray(ResponseConstants.ITEMS);
        if (optJSONArray.length() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt(ResponseConstants.CODE);
                if (optInt != 0) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                    if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                        hashSet = null;
                    } else {
                        hashSet = new HashSet();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            int optInt2 = optJSONArray2.optInt(i2);
                            if (optInt2 != 0) {
                                hashSet.add(Integer.valueOf(optInt2));
                            }
                        }
                    }
                    hashMap.put(Integer.valueOf(optInt), hashSet);
                }
            }
        }
        return hashMap;
    }

    public static h a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        Map map = null;
        Map map2 = null;
        Map map3 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString(ResponseConstants.NAME);
                if (optString != null) {
                    if (optString.equalsIgnoreCase(ResponseConstants.OTHER)) {
                        str = optJSONObject.optString("recovery_message", null);
                        map = a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("transient")) {
                        str2 = optJSONObject.optString("recovery_message", null);
                        map2 = a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("login_recoverable")) {
                        str3 = optJSONObject.optString("recovery_message", null);
                        map3 = a(optJSONObject);
                    }
                }
            }
        }
        h hVar = new h(map, map2, map3, str, str2, str3);
        return hVar;
    }
}
