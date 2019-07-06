package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class arh {
    private static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return str.replaceAll(str2, str3);
    }

    public static List<String> a(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static void a(Context context, String str, ga gaVar, String str2, boolean z, List<String> list) {
        if (list != null && !list.isEmpty()) {
            String str3 = z ? "1" : "0";
            for (String a : list) {
                String a2 = a(a(a(a(a(a(a(a, "@gw_adlocid@", str2), "@gw_adnetrefresh@", str3), "@gw_qdata@", gaVar.r.i), "@gw_sdkver@", str), "@gw_sessid@", ajh.c()), "@gw_seqnum@", gaVar.j), "@gw_adnetstatus@", gaVar.t);
                if (gaVar.o != null) {
                    a2 = a(a(a2, "@gw_adnetid@", gaVar.o.b), "@gw_allocid@", gaVar.o.d);
                }
                String a3 = fu.a(a2, context);
                ao.e();
                hd.a(context, str, a3);
            }
        }
    }

    public static void a(Context context, String str, List<String> list, String str2, @Nullable zzaig zzaig) {
        if (list != null && !list.isEmpty()) {
            if (!TextUtils.isEmpty(str2) && jt.c()) {
                str2 = "fakeUserForAdDebugLog";
            }
            long currentTimeMillis = ao.l().currentTimeMillis();
            for (String a : list) {
                String a2 = a(a(a, "@gw_rwd_userid@", Uri.encode(str2)), "@gw_tmstmp@", Long.toString(currentTimeMillis));
                if (zzaig != null) {
                    a2 = a(a(a2, "@gw_rwd_itm@", Uri.encode(zzaig.type)), "@gw_rwd_amt@", Integer.toString(zzaig.zzcmk));
                }
                ao.e();
                hd.a(context, str, a2);
            }
        }
    }

    public static boolean a(String str, int[] iArr) {
        if (TextUtils.isEmpty(str) || iArr.length != 2) {
            return false;
        }
        String[] split = str.split(EtsyDialogFragment.OPT_X_BUTTON);
        if (split.length != 2) {
            return false;
        }
        try {
            iArr[0] = Integer.parseInt(split[0]);
            iArr[1] = Integer.parseInt(split[1]);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
