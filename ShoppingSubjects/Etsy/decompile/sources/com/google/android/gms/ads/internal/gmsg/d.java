package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.bh;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.asd;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.c;
import com.google.android.gms.internal.ads.e;
import com.google.android.gms.internal.ads.f;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.n;
import com.google.android.gms.internal.ads.nn;
import java.util.Map;

@bu
public final class d implements ae<nn> {
    private static final Map<String, Integer> d = CollectionUtils.mapOfKeyValueArrays(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7)});
    private final bh a;
    private final c b;
    private final n c;

    public d(bh bhVar, c cVar, n nVar) {
        this.a = bhVar;
        this.b = cVar;
        this.c = nVar;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        int intValue = ((Integer) d.get((String) map.get("a"))).intValue();
        if (intValue != 5 && intValue != 7 && this.a != null && !this.a.b()) {
            this.a.a(null);
        } else if (intValue != 1) {
            switch (intValue) {
                case 3:
                    new f(nnVar, map).a();
                    return;
                case 4:
                    new asd(nnVar, map).a();
                    return;
                case 5:
                    new e(nnVar, map).a();
                    return;
                case 6:
                    this.b.a(true);
                    return;
                case 7:
                    if (((Boolean) ajh.f().a(akl.M)).booleanValue()) {
                        this.c.zzcz();
                    }
                    return;
                default:
                    gv.d("Unknown MRAID command called.");
                    return;
            }
        } else {
            this.b.a(map);
        }
    }
}
