package com.etsy.android.util;

import android.net.Uri;
import android.net.Uri.Builder;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

/* compiled from: DeepLinkUtil */
public class c {
    public static Uri a(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.get("uri") == null) {
            return null;
        }
        Builder buildUpon = Uri.parse(hashMap.get("uri").toString()).buildUpon();
        if (hashMap.get(ResponseConstants.OBJECT_ID) != null) {
            buildUpon.appendPath(hashMap.get(ResponseConstants.OBJECT_ID).toString()).build();
        }
        for (String str : hashMap.keySet()) {
            if (!"uri".equals(str) && !ResponseConstants.OBJECT_ID.equals(str) && hashMap.get(str) != null) {
                buildUpon.appendQueryParameter(str, hashMap.get(str).toString());
            }
        }
        return buildUpon.build();
    }
}
