package com.facebook.share.internal;

import com.facebook.share.model.CameraEffectArguments;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CameraEffectJSONUtility */
public class a {
    private static final Map<Class<?>, C0124a> a = new HashMap();

    /* renamed from: com.facebook.share.internal.a$a reason: collision with other inner class name */
    /* compiled from: CameraEffectJSONUtility */
    public interface C0124a {
        void a(JSONObject jSONObject, String str, Object obj) throws JSONException;
    }

    static {
        a.put(String.class, new C0124a() {
            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(String[].class, new C0124a() {
            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                JSONArray jSONArray = new JSONArray();
                for (String put : (String[]) obj) {
                    jSONArray.put(put);
                }
                jSONObject.put(str, jSONArray);
            }
        });
        a.put(JSONArray.class, new C0124a() {
            public void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
            }
        });
    }

    public static JSONObject a(CameraEffectArguments cameraEffectArguments) throws JSONException {
        if (cameraEffectArguments == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : cameraEffectArguments.keySet()) {
            Object obj = cameraEffectArguments.get(str);
            if (obj != null) {
                C0124a aVar = (C0124a) a.get(obj.getClass());
                if (aVar == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unsupported type: ");
                    sb.append(obj.getClass());
                    throw new IllegalArgumentException(sb.toString());
                }
                aVar.a(jSONObject, str, obj);
            }
        }
        return jSONObject;
    }
}
