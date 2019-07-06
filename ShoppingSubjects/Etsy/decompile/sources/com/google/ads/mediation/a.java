package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Date;
import java.util.Set;

@Deprecated
public class a {
    private final Date a;
    private final Gender b;
    private final Set<String> c;
    private final boolean d;
    private final Location e;

    public a(Date date, Gender gender, Set<String> set, boolean z, Location location) {
        this.a = date;
        this.b = gender;
        this.c = set;
        this.d = z;
        this.e = location;
    }
}
