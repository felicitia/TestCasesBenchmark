package com.etsy.android.localization.addresses;

import android.support.v4.os.EnvironmentCompat;
import java.util.HashMap;

/* compiled from: Address */
public class a {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;

    public a() {
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
    }

    public a(HashMap<AddressFieldType, String> hashMap) {
        this.a = a(hashMap, AddressFieldType.NAME);
        this.b = a(hashMap, AddressFieldType.FIRST_LINE);
        this.c = a(hashMap, AddressFieldType.SECOND_LINE);
        this.d = a(hashMap, AddressFieldType.CITY);
        this.e = a(hashMap, AddressFieldType.STATE);
        this.f = a(hashMap, AddressFieldType.ZIP);
        this.g = a(hashMap, AddressFieldType.COUNTRY_NAME);
        this.h = a(hashMap, AddressFieldType.PHONE);
    }

    private String a(HashMap<AddressFieldType, String> hashMap, AddressFieldType addressFieldType) {
        return hashMap.containsKey(addressFieldType) ? (String) hashMap.get(addressFieldType) : "";
    }

    public HashMap<AddressFieldType, String> a() {
        HashMap<AddressFieldType, String> hashMap = new HashMap<>();
        hashMap.put(AddressFieldType.NAME, this.a);
        hashMap.put(AddressFieldType.FIRST_LINE, this.b);
        hashMap.put(AddressFieldType.SECOND_LINE, this.c);
        hashMap.put(AddressFieldType.CITY, this.d);
        hashMap.put(AddressFieldType.STATE, this.e);
        hashMap.put(AddressFieldType.ZIP, this.f);
        hashMap.put(AddressFieldType.COUNTRY_NAME, this.g);
        hashMap.put(AddressFieldType.PHONE, this.h);
        return hashMap;
    }

    public String toString() {
        return b.a(a());
    }

    public String a(AddressFieldType addressFieldType) {
        switch (addressFieldType) {
            case NAME:
                return this.a;
            case FIRST_LINE:
                return this.b;
            case SECOND_LINE:
                return this.c;
            case CITY:
                return this.d;
            case STATE:
                return this.e;
            case ZIP:
                return this.f;
            case COUNTRY_NAME:
                return this.g;
            case PHONE:
                return this.h;
            case UNKNOWN:
                return "";
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }
}
