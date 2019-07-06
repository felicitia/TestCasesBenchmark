package com.etsy.android.localization.addresses;

import android.content.Context;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.localization.addresses.e.a;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AddressFormatterData */
public class c {
    private static c a;
    private static ArrayList<Integer> b;
    private HashMap<Integer, d> c;
    private d d = new d();

    public c() {
        this.d.c.add(AddressFieldType.NAME);
        this.d.c.add(AddressFieldType.FIRST_LINE);
        this.d.c.add(AddressFieldType.CITY);
        this.d.c.add(AddressFieldType.STATE);
        this.d.c.add(AddressFieldType.ZIP);
        this.d.d.add(AddressFieldType.CITY);
        this.d.d.add(AddressFieldType.STATE);
        this.d.e = "United States";
        this.d.g = 209;
        this.d.h = ResponseConstants.STATE;
        this.d.i = ResponseConstants.CITY;
        this.d.k = ResponseConstants.ZIP;
        this.d.l = "(\\d{5})(?:[ \\-](\\d{4}))?";
        this.d.p = "%name\n%first_line\n%second_line\n%city\n%state %zip";
        this.d.q = "US";
        this.c = new HashMap<>();
        b = new ArrayList<>();
    }

    public void a(int i, d dVar) {
        this.c.put(Integer.valueOf(i), dVar);
    }

    public d a(int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            return (d) this.c.get(Integer.valueOf(i));
        }
        return b();
    }

    public d a(String str) {
        String lowerCase = str.toLowerCase();
        for (d dVar : this.c.values()) {
            if (dVar.e != null && dVar.e.toLowerCase().equals(lowerCase)) {
                return dVar;
            }
        }
        return this.d;
    }

    public static c a(Context context) {
        try {
            InputStream open = context.getAssets().open("address_formatter_data.json");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return a(b(new String(bArr, "UTF-8")), context.getResources().getStringArray(a.countries_array), context.getResources().getStringArray(a.country_ids_array));
        } catch (IOException e) {
            com.google.a.a.a.a.a.a.a(e);
            return new c();
        }
    }

    public static c b(String str) {
        c cVar = new c();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                int parseInt = Integer.parseInt(str2);
                JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                d dVar = new d();
                dVar.a = jSONObject2.getString("locale");
                dVar.b = jSONObject2.getString(ResponseConstants.FORMAT);
                JSONArray jSONArray = jSONObject2.getJSONArray("required_fields");
                for (int i = 0; i < jSONArray.length(); i++) {
                    dVar.c.add(AddressFieldType.fromString(jSONArray.getString(i)));
                }
                JSONArray jSONArray2 = jSONObject2.getJSONArray("uppercase_fields");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    dVar.d.add(AddressFieldType.fromString(jSONArray2.getString(i2)));
                }
                dVar.e = c(jSONObject2.getString(ResponseConstants.NAME));
                dVar.g = parseInt;
                dVar.h = a(jSONObject2, "administrative_area_type");
                dVar.i = a(jSONObject2, "locality_type");
                dVar.j = a(jSONObject2, "second_line_type");
                dVar.k = a(jSONObject2, "postal_code_type");
                dVar.l = a(jSONObject2, "postal_code_pattern");
                dVar.m = a(jSONObject2, "postal_code_prefix");
                JSONArray optJSONArray = jSONObject2.optJSONArray("administrative_areas");
                if (optJSONArray != null) {
                    for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                        dVar.n.add(optJSONArray.getString(i3));
                    }
                }
                dVar.o = a(jSONObject2, "local_format");
                dVar.p = a(jSONObject2, "input_format");
                dVar.q = a(jSONObject2, "iso_code");
                cVar.a(parseInt, dVar);
            }
        } catch (JSONException e) {
            com.google.a.a.a.a.a.a.a(e);
        }
        return cVar;
    }

    public static c a(c cVar, String[] strArr, String[] strArr2) {
        for (int i = 0; i < strArr2.length; i++) {
            int parseInt = Integer.parseInt(strArr2[i]);
            String str = strArr[i];
            d a2 = cVar.a(parseInt);
            if (a2.b().booleanValue()) {
                a2.g = parseInt;
                a2.e = str;
                a2.f = str;
                cVar.a(parseInt, a2);
            } else {
                a2.f = str;
            }
            b.add(Integer.valueOf(parseInt));
        }
        a = cVar;
        return cVar;
    }

    public ArrayList<d> a() {
        ArrayList<d> arrayList = new ArrayList<>();
        Iterator it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(a(((Integer) it.next()).intValue()));
        }
        return arrayList;
    }

    private d b() {
        d dVar = new d();
        dVar.e = this.d.e;
        dVar.g = this.d.g;
        dVar.a = this.d.a;
        dVar.b = this.d.b;
        dVar.p = this.d.p;
        dVar.h = this.d.h;
        dVar.i = this.d.i;
        dVar.k = this.d.k;
        dVar.l = this.d.l;
        dVar.a(this.d.c);
        dVar.b(this.d.d);
        dVar.a(Boolean.valueOf(true));
        return dVar;
    }

    private static String a(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.isNull(str)) {
                return null;
            }
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    private static String c(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, 1).toUpperCase());
        sb.append(str.substring(1).toLowerCase());
        return sb.toString();
    }

    public static String a(String str, AddressFieldType addressFieldType) {
        String str2;
        String addressFieldType2 = addressFieldType.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(addressFieldType2);
        int indexOf = str.indexOf(sb.toString());
        if (indexOf == -1) {
            return str;
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(37, i);
        int indexOf3 = str.indexOf("\\n", i);
        if (indexOf2 == -1) {
            indexOf2 = indexOf3 + 1;
        } else if (indexOf3 != -1) {
            indexOf2 = Math.min(indexOf2, indexOf3 + 1);
        }
        if (indexOf2 == -1) {
            if (addressFieldType2 == ResponseConstants.COUNTRY_NAME) {
                indexOf--;
            }
            str2 = str.substring(indexOf);
        } else {
            str2 = str.substring(indexOf, indexOf2);
        }
        return str.replace(str2, "");
    }
}
