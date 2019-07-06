package com.salesforce.marketingcloud.analytics;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.List;

@RestrictTo({Scope.LIBRARY})
public final class i {
    public static String a(List<e> list) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (e eVar : list) {
            if (eVar != null) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(eVar.a());
            }
        }
        return sb.toString();
    }

    public static String[] a(String str) {
        return str.split("\\s*,\\s*");
    }
}
