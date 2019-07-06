package com.google.android.gms.ads.internal.gmsg;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.nn;
import java.util.HashMap;
import java.util.Map;

final class v implements ae<nn> {
    v() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        WindowManager windowManager = (WindowManager) nnVar.getContext().getSystemService("window");
        ao.e();
        View view = (View) nnVar;
        DisplayMetrics a = hd.a(windowManager);
        int i = a.widthPixels;
        int i2 = a.heightPixels;
        int[] iArr = new int[2];
        HashMap hashMap = new HashMap();
        view.getLocationInWindow(iArr);
        hashMap.put("xInPixels", Integer.valueOf(iArr[0]));
        hashMap.put("yInPixels", Integer.valueOf(iArr[1]));
        hashMap.put("windowWidthInPixels", Integer.valueOf(i));
        hashMap.put("windowHeightInPixels", Integer.valueOf(i2));
        nnVar.zza("locationReady", (Map<String, ?>) hashMap);
        gv.e("GET LOCATION COMPILED");
    }
}
