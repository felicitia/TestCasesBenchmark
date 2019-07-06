package com.facebook.share.internal;

import android.support.annotation.Nullable;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OpenGraphJSONUtility */
public final class h {

    /* compiled from: OpenGraphJSONUtility */
    public interface a {
        JSONObject a(SharePhoto sharePhoto);
    }

    public static JSONObject a(ShareOpenGraphAction shareOpenGraphAction, a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphAction.keySet()) {
            jSONObject.put(str, a(shareOpenGraphAction.get(str), aVar));
        }
        return jSONObject;
    }

    private static JSONObject a(ShareOpenGraphObject shareOpenGraphObject, a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphObject.keySet()) {
            jSONObject.put(str, a(shareOpenGraphObject.get(str), aVar));
        }
        return jSONObject;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.Object>, for r2v0, types: [java.util.List, java.util.List<java.lang.Object>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONArray a(java.util.List<java.lang.Object> r2, com.facebook.share.internal.h.a r3) throws org.json.JSONException {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x0009:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L_0x001b
            java.lang.Object r1 = r2.next()
            java.lang.Object r1 = a(r1, r3)
            r0.put(r1)
            goto L_0x0009
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.h.a(java.util.List, com.facebook.share.internal.h$a):org.json.JSONArray");
    }

    public static Object a(@Nullable Object obj, a aVar) throws JSONException {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long)) {
            return obj;
        }
        if (obj instanceof SharePhoto) {
            if (aVar != null) {
                return aVar.a((SharePhoto) obj);
            }
            return null;
        } else if (obj instanceof ShareOpenGraphObject) {
            return a((ShareOpenGraphObject) obj, aVar);
        } else {
            if (obj instanceof List) {
                return a((List) obj, aVar);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid object found for JSON serialization: ");
            sb.append(obj.toString());
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
