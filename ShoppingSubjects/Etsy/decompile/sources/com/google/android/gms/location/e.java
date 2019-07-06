package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;

public class e {
    private final int a;
    private final int b;
    private final List<b> c;
    private final Location d;

    private e(int i, int i2, List<b> list, Location location) {
        this.a = i;
        this.b = i2;
        this.c = list;
        this.d = location;
    }

    public static e a(Intent intent) {
        ArrayList arrayList = null;
        if (intent == null) {
            return null;
        }
        int i = -1;
        int intExtra = intent.getIntExtra(Constants.KEY_GMS_ERROR_CODE, -1);
        int intExtra2 = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        if (intExtra2 != -1 && (intExtra2 == 1 || intExtra2 == 2 || intExtra2 == 4)) {
            i = intExtra2;
        }
        ArrayList arrayList2 = (ArrayList) intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if (arrayList2 != null) {
            arrayList = new ArrayList(arrayList2.size());
            ArrayList arrayList3 = arrayList2;
            int size = arrayList3.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList3.get(i2);
                i2++;
                arrayList.add(zzbh.zza((byte[]) obj));
            }
        }
        return new e(intExtra, i, arrayList, (Location) intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
    }

    public boolean a() {
        return this.a != -1;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public List<b> d() {
        return this.c;
    }
}
