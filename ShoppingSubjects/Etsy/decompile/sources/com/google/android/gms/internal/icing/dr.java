package com.google.android.gms.internal.icing;

import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public final class dr {
    @VisibleForTesting
    static final String[] a = {"text1", "text2", ResponseConstants.ICON, "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity", "thing_proto"};
    private static final Map<String, Integer> b = new HashMap(a.length);

    static {
        for (int i = 0; i < a.length; i++) {
            b.put(a[i], Integer.valueOf(i));
        }
    }

    public static int a(String str) {
        Integer num = (Integer) b.get(str);
        if (num != null) {
            return num.intValue();
        }
        StringBuilder sb = new StringBuilder(44 + String.valueOf(str).length());
        sb.append("[");
        sb.append(str);
        sb.append("] is not a valid global search section name");
        throw new IllegalArgumentException(sb.toString());
    }

    public static String a(int i) {
        if (i < 0 || i >= a.length) {
            return null;
        }
        return a[i];
    }
}
