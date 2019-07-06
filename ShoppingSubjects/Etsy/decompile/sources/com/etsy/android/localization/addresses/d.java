package com.etsy.android.localization.addresses;

import java.util.ArrayList;
import java.util.List;

/* compiled from: CountryData */
class d {
    public String a = "en";
    public String b = "%name\n%first_line\n%second_line\n%city, %state %zip\n%country_name";
    public List<AddressFieldType> c = new ArrayList();
    public List<AddressFieldType> d = new ArrayList();
    public String e;
    public String f;
    public int g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public List<String> n = new ArrayList();
    public String o;
    public String p;
    public String q;
    private Boolean r = Boolean.valueOf(false);

    public String a(AddressFieldType addressFieldType) {
        switch (addressFieldType) {
            case CITY:
                return this.i;
            case STATE:
                return this.h;
            case ZIP:
                return this.k;
            case SECOND_LINE:
                return this.j;
            default:
                return "";
        }
    }

    public String toString() {
        return a();
    }

    public String a() {
        return this.f != null ? this.f : this.e;
    }

    public void a(List<AddressFieldType> list) {
        for (AddressFieldType add : list) {
            this.c.add(add);
        }
    }

    public void b(List<AddressFieldType> list) {
        for (AddressFieldType add : list) {
            this.d.add(add);
        }
    }

    public Boolean b() {
        return this.r;
    }

    public void a(Boolean bool) {
        this.r = bool;
    }
}
