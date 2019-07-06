package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@bu
public final class mu implements ae<mo> {
    private boolean a;

    private static int a(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                ajh.a();
                return jp.a(context, Integer.parseInt(str2));
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder(34 + String.valueOf(str).length() + String.valueOf(str2).length());
                sb.append("Could not parse ");
                sb.append(str);
                sb.append(" in a video GMSG: ");
                sb.append(str2);
                gv.e(sb.toString());
            }
        }
        return i;
    }

    private static void a(zzapi zzapi, Map<String, String> map) {
        String str = (String) map.get("minBufferMs");
        String str2 = (String) map.get("maxBufferMs");
        String str3 = (String) map.get("bufferForPlaybackMs");
        String str4 = (String) map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                gv.e(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
            }
        }
        if (str2 != null) {
            Integer.parseInt(str2);
        }
        if (str3 != null) {
            Integer.parseInt(str3);
        }
        if (str4 != null) {
            Integer.parseInt(str4);
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i;
        int i2;
        String[] split;
        mo moVar = (mo) obj;
        String str = (String) map.get(ResponseConstants.ACTION);
        if (str == null) {
            gv.e("Action missing from video GMSG.");
            return;
        }
        if (gv.a(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String jSONObject2 = jSONObject.toString();
            StringBuilder sb = new StringBuilder(13 + String.valueOf(str).length() + String.valueOf(jSONObject2).length());
            sb.append("Video GMSG: ");
            sb.append(str);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(jSONObject2);
            gv.b(sb.toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get(ResponseConstants.COLOR);
            if (TextUtils.isEmpty(str2)) {
                gv.e("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                moVar.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException unused) {
                gv.e("Invalid color parameter in video GMSG.");
            }
        } else {
            if ("decoderProps".equals(str)) {
                String str3 = (String) map.get("mimeTypes");
                if (str3 == null) {
                    gv.e("No MIME types specified for decoder properties inspection.");
                    zzapi.zza(moVar, "missingMimeTypes");
                } else if (VERSION.SDK_INT < 16) {
                    gv.e("Video decoder properties available on API versions >= 16.");
                    zzapi.zza(moVar, "deficientApiVersion");
                } else {
                    HashMap hashMap = new HashMap();
                    for (String str4 : str3.split(",")) {
                        hashMap.put(str4, jn.a(str4.trim()));
                    }
                    zzapi.zza(moVar, (Map<String, List<Map<String, Object>>>) hashMap);
                }
            } else {
                mg zztl = moVar.zztl();
                if (zztl == null) {
                    gv.e("Could not get underlay container for a video GMSG.");
                    return;
                }
                boolean equals = "new".equals(str);
                boolean equals2 = "position".equals(str);
                if (equals || equals2) {
                    Context context = moVar.getContext();
                    int a2 = a(context, map, EtsyDialogFragment.OPT_X_BUTTON, 0);
                    int a3 = a(context, map, "y", 0);
                    int a4 = a(context, map, "w", -1);
                    int a5 = a(context, map, "h", -1);
                    if (((Boolean) ajh.f().a(akl.cf)).booleanValue()) {
                        a4 = Math.min(a4, moVar.zzts() - a2);
                        i = Math.min(a5, moVar.zztr() - a3);
                    } else {
                        i = a5;
                    }
                    try {
                        i2 = Integer.parseInt((String) map.get("player"));
                    } catch (NumberFormatException unused2) {
                        i2 = 0;
                    }
                    boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                    if (!equals || zztl.a() != null) {
                        zztl.a(a2, a3, a4, i);
                        return;
                    }
                    zztl.a(a2, a3, a4, i, i2, parseBoolean, new mn((String) map.get(ResponseConstants.FLAGS)));
                    zzapi a6 = zztl.a();
                    if (a6 != null) {
                        a(a6, map);
                    }
                    return;
                }
                zzapi a7 = zztl.a();
                if (a7 == null) {
                    zzapi.zza(moVar);
                } else if ("click".equals(str)) {
                    Context context2 = moVar.getContext();
                    int a8 = a(context2, map, EtsyDialogFragment.OPT_X_BUTTON, 0);
                    int a9 = a(context2, map, "y", 0);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) a8, (float) a9, 0);
                    a7.zzf(obtain);
                    obtain.recycle();
                } else if ("currentTime".equals(str)) {
                    String str5 = (String) map.get("time");
                    if (str5 == null) {
                        gv.e("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        a7.seekTo((int) (Float.parseFloat(str5) * 1000.0f));
                    } catch (NumberFormatException unused3) {
                        String str6 = "Could not parse time parameter from currentTime video GMSG: ";
                        String valueOf = String.valueOf(str5);
                        gv.e(valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
                    }
                } else if ("hide".equals(str)) {
                    a7.setVisibility(4);
                } else if ("load".equals(str)) {
                    a7.zzta();
                } else if ("loadControl".equals(str)) {
                    a(a7, map);
                } else if ("muted".equals(str)) {
                    if (Boolean.parseBoolean((String) map.get("muted"))) {
                        a7.zztb();
                    } else {
                        a7.zztc();
                    }
                } else if ("pause".equals(str)) {
                    a7.pause();
                } else if ("play".equals(str)) {
                    a7.play();
                } else if ("show".equals(str)) {
                    a7.setVisibility(0);
                } else if ("src".equals(str)) {
                    a7.zzdn((String) map.get("src"));
                } else if ("touchMove".equals(str)) {
                    Context context3 = moVar.getContext();
                    a7.zza((float) a(context3, map, "dx", 0), (float) a(context3, map, "dy", 0));
                    if (!this.a) {
                        moVar.zznp();
                        this.a = true;
                    }
                } else if ("volume".equals(str)) {
                    String str7 = (String) map.get("volume");
                    if (str7 == null) {
                        gv.e("Level parameter missing from volume video GMSG.");
                        return;
                    }
                    try {
                        a7.setVolume(Float.parseFloat(str7));
                    } catch (NumberFormatException unused4) {
                        String str8 = "Could not parse volume parameter from volume video GMSG: ";
                        String valueOf2 = String.valueOf(str7);
                        gv.e(valueOf2.length() != 0 ? str8.concat(valueOf2) : new String(str8));
                    }
                } else if ("watermark".equals(str)) {
                    a7.zztd();
                } else {
                    String str9 = "Unknown video action: ";
                    String valueOf3 = String.valueOf(str);
                    gv.e(valueOf3.length() != 0 ? str9.concat(valueOf3) : new String(str9));
                }
            }
        }
    }
}
