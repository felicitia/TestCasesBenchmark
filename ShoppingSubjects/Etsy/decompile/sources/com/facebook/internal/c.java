package com.facebook.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BundleJSONConverter */
public class c {
    private static final Map<Class<?>, a> a = new HashMap();

    /* compiled from: BundleJSONConverter */
    public interface a {
        void a(Bundle bundle, String str, Object obj) throws JSONException;

        void a(JSONObject jSONObject, String str, Object obj) throws JSONException;
    }

    static {
        a.put(Boolean.class, new a() {
            public void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            }

            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(Integer.class, new a() {
            public void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putInt(str, ((Integer) obj).intValue());
            }

            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(Long.class, new a() {
            public void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putLong(str, ((Long) obj).longValue());
            }

            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(Double.class, new a() {
            public void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            }

            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(String.class, new a() {
            public void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putString(str, (String) obj);
            }

            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(String[].class, new a() {
            public void a(Bundle bundle, String str, Object obj) throws JSONException {
                throw new IllegalArgumentException("Unexpected type from JSON");
            }

            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                JSONArray jSONArray = new JSONArray();
                for (String put : (String[]) obj) {
                    jSONArray.put(put);
                }
                jSONObject.put(str, jSONArray);
            }
        });
        a.put(JSONArray.class, new a() {
            public void a(Bundle bundle, String str, Object obj) throws JSONException {
                JSONArray jSONArray = (JSONArray) obj;
                ArrayList arrayList = new ArrayList();
                if (jSONArray.length() == 0) {
                    bundle.putStringArrayList(str, arrayList);
                    return;
                }
                int i = 0;
                while (i < jSONArray.length()) {
                    Object obj2 = jSONArray.get(i);
                    if (obj2 instanceof String) {
                        arrayList.add((String) obj2);
                        i++;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unexpected type in an array: ");
                        sb.append(obj2.getClass());
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                bundle.putStringArrayList(str, arrayList);
            }

            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
            }
        });
    }

    public static JSONObject a(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                if (obj instanceof List) {
                    JSONArray jSONArray = new JSONArray();
                    for (String put : (List) obj) {
                        jSONArray.put(put);
                    }
                    jSONObject.put(str, jSONArray);
                } else if (obj instanceof Bundle) {
                    jSONObject.put(str, a((Bundle) obj));
                } else {
                    a aVar = (a) a.get(obj.getClass());
                    if (aVar == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unsupported type: ");
                        sb.append(obj.getClass());
                        throw new IllegalArgumentException(sb.toString());
                    }
                    aVar.a(jSONObject, str, obj);
                }
            }
        }
        return jSONObject;
    }

    public static Bundle a(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (!(obj == null || obj == JSONObject.NULL)) {
                if (obj instanceof JSONObject) {
                    bundle.putBundle(str, a((JSONObject) obj));
                } else {
                    a aVar = (a) a.get(obj.getClass());
                    if (aVar == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unsupported type: ");
                        sb.append(obj.getClass());
                        throw new IllegalArgumentException(sb.toString());
                    }
                    aVar.a(bundle, str, obj);
                }
            }
        }
        return bundle;
    }
}
