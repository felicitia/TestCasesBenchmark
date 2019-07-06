package com.google.android.gms.internal.firebase-perf;

import java.util.AbstractMap.SimpleEntry;
import java.util.Locale;

public abstract class zzo {
    public abstract boolean zzn();

    public static String zza(String str, int i) {
        if (str == null) {
            switch (zzp.zzbt[i - 1]) {
                case 1:
                    return "Counter name must not be null";
                case 2:
                    return "Metric name must not be null";
            }
        } else {
            if (str.length() > 100) {
                switch (zzp.zzbt[i - 1]) {
                    case 1:
                        return String.format(Locale.US, "Counter name must not exceed %d characters", new Object[]{Integer.valueOf(100)});
                    case 2:
                        return String.format(Locale.US, "Metric name must not exceed %d characters", new Object[]{Integer.valueOf(100)});
                }
            } else if (str.startsWith("_")) {
                for (zzy zzy : zzy.values()) {
                    if (zzy.toString().equals(str)) {
                        return null;
                    }
                }
                switch (zzp.zzbt[i - 1]) {
                    case 1:
                        return "Counter name must not start with '_'";
                    case 2:
                        return "Metric name must not start with '_'";
                }
            }
        }
        return null;
    }

    public static String zza(SimpleEntry<String, String> simpleEntry) {
        String str = (String) simpleEntry.getKey();
        String str2 = (String) simpleEntry.getValue();
        if (str == null) {
            return "Attribute key must not be null";
        }
        if (str2 == null) {
            return "Attribute value must not be null";
        }
        if (str.length() > 40) {
            return String.format(Locale.US, "Attribute key length must not exceed %d characters", new Object[]{Integer.valueOf(40)});
        } else if (str2.length() > 100) {
            return String.format(Locale.US, "Attribute value length must not exceed %d characters", new Object[]{Integer.valueOf(100)});
        } else if (!str.matches("^(?!(firebase_|google_|ga_))[A-Za-z][A-Za-z_0-9]*")) {
            return "Attribute key must start with letter, must only contain alphanumeric characters and underscore and must not start with \"firebase_\", \"google_\" and \"ga_";
        } else {
            return null;
        }
    }
}
