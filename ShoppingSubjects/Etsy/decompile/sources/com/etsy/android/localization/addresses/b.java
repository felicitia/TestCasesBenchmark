package com.etsy.android.localization.addresses;

import android.content.Context;
import java.util.HashMap;
import java.util.List;

/* compiled from: AddressFormatter */
public class b {
    private static c a;
    private static Context b;

    public static void a(Context context) {
        b = context;
    }

    public static String a(HashMap<AddressFieldType, String> hashMap) {
        if (a == null) {
            if (b == null) {
                a = new c();
            } else {
                a = c.a(b);
            }
        }
        d a2 = a.a((String) hashMap.get(AddressFieldType.COUNTRY_NAME));
        return a(hashMap, a2.b, a2.d);
    }

    private static String a(HashMap<AddressFieldType, String> hashMap, String str, List<AddressFieldType> list) {
        AddressFieldType[] values;
        for (AddressFieldType addressFieldType : list) {
            String str2 = (String) hashMap.get(addressFieldType);
            if (str2 != null) {
                hashMap.put(addressFieldType, str2.toUpperCase());
            }
        }
        for (AddressFieldType addressFieldType2 : AddressFieldType.values()) {
            String str3 = (String) hashMap.get(addressFieldType2);
            if (a(str3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("%");
                sb.append(addressFieldType2.toString());
                str = str.replaceAll(sb.toString(), str3);
            } else {
                str = c.a(str, addressFieldType2);
            }
        }
        return str;
    }

    public static boolean a(String str) {
        return str != null && !str.isEmpty() && !"".equals(str.trim()) && !"null".equals(str.trim());
    }
}
