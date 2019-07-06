package com.google.android.gms.internal.icing;

import java.util.HashMap;
import java.util.Map;

public final class zzr {
    static final String[] zzy = {"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity", "thing_proto"};
    private static final Map<String, Integer> zzz = new HashMap(zzy.length);

    static {
        for (int i = 0; i < zzy.length; i++) {
            zzz.put(zzy[i], Integer.valueOf(i));
        }
    }

    public static String zza(int i) {
        if (i < 0 || i >= zzy.length) {
            return null;
        }
        return zzy[i];
    }
}
