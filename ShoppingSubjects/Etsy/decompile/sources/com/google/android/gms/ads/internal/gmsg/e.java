package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bh;
import com.google.android.gms.ads.internal.overlay.i;
import com.google.android.gms.ads.internal.overlay.k;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ack;
import com.google.android.gms.internal.ads.ait;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.c;
import com.google.android.gms.internal.ads.fu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.of;
import com.google.android.gms.internal.ads.og;
import com.google.android.gms.internal.ads.oh;
import com.google.android.gms.internal.ads.ok;
import com.google.android.gms.internal.ads.on;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzcj;
import java.net.URISyntaxException;
import java.util.Map;

@bu
public final class e<T extends of & og & oh & ok & on> implements ae<T> {
    private final Context a;
    private final ack b;
    private final zzang c;
    private final k d;
    private final ait e;
    private final i f;
    private final k g;
    private final m h;
    private final bh i;
    private final c j;
    private final nn k = null;

    public e(Context context, zzang zzang, ack ack, k kVar, ait ait, k kVar2, m mVar, i iVar, bh bhVar, c cVar) {
        this.a = context;
        this.c = zzang;
        this.b = ack;
        this.d = kVar;
        this.e = ait;
        this.g = kVar2;
        this.h = mVar;
        this.i = bhVar;
        this.j = cVar;
        this.f = iVar;
    }

    @VisibleForTesting
    static String a(Context context, ack ack, String str, View view, @Nullable Activity activity) {
        if (ack == null) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (ack.b(parse)) {
                parse = ack.a(parse, context, view, activity);
            }
            return parse.toString();
        } catch (zzcj unused) {
            return str;
        } catch (Exception e2) {
            ao.i().a((Throwable) e2, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return str;
        }
    }

    private final void a(boolean z) {
        if (this.j != null) {
            this.j.a(z);
        }
    }

    private static boolean a(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int b(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return ao.g().b();
            }
            if ("l".equalsIgnoreCase(str)) {
                return ao.g().a();
            }
            if ("c".equalsIgnoreCase(str)) {
                return ao.g().c();
            }
        }
        return -1;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        of ofVar = (of) obj;
        String a2 = fu.a((String) map.get("u"), ofVar.getContext());
        String str = (String) map.get("a");
        if (str == null) {
            gv.e("Action missing from an open GMSG.");
        } else if (this.i != null && !this.i.b()) {
            this.i.a(a2);
        } else if ("expand".equalsIgnoreCase(str)) {
            if (((og) ofVar).zzuj()) {
                gv.e("Cannot expand WebView that is already expanded.");
                return;
            }
            a(false);
            ((oh) ofVar).zza(a(map), b(map));
        } else if ("webapp".equalsIgnoreCase(str)) {
            a(false);
            if (a2 != null) {
                ((oh) ofVar).zza(a(map), b(map), a2);
            } else {
                ((oh) ofVar).zza(a(map), b(map), (String) map.get("html"), (String) map.get("baseurl"));
            }
        } else if (!"app".equalsIgnoreCase(str) || !"true".equalsIgnoreCase((String) map.get("system_browser"))) {
            a(true);
            String str2 = (String) map.get("intent_url");
            Intent intent = null;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    intent = Intent.parseUri(str2, 0);
                } catch (URISyntaxException e2) {
                    String str3 = "Error parsing the url: ";
                    String valueOf = String.valueOf(str2);
                    gv.b(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e2);
                }
            }
            if (!(intent == null || intent.getData() == null)) {
                Uri data = intent.getData();
                String uri = data.toString();
                if (!TextUtils.isEmpty(uri)) {
                    try {
                        uri = a(ofVar.getContext(), ((ok) ofVar).zzui(), uri, ((on) ofVar).getView(), ofVar.zzto());
                    } catch (Exception e3) {
                        gv.b("Error occurred while adding signals.", e3);
                        ao.i().a((Throwable) e3, "OpenGmsgHandler.onGmsg");
                    }
                    try {
                        data = Uri.parse(uri);
                    } catch (Exception e4) {
                        String str4 = "Error parsing the uri: ";
                        String valueOf2 = String.valueOf(uri);
                        gv.b(valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4), e4);
                        ao.i().a((Throwable) e4, "OpenGmsgHandler.onGmsg");
                    }
                }
                intent.setData(data);
            }
            if (intent != null) {
                ((oh) ofVar).zza(new zzc(intent));
                return;
            }
            if (!TextUtils.isEmpty(a2)) {
                a2 = a(ofVar.getContext(), ((ok) ofVar).zzui(), a2, ((on) ofVar).getView(), ofVar.zzto());
            }
            oh ohVar = (oh) ofVar;
            zzc zzc = new zzc((String) map.get("i"), a2, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e"));
            ohVar.zza(zzc);
        } else {
            a(true);
            ofVar.getContext();
            if (TextUtils.isEmpty(a2)) {
                gv.e("Destination url cannot be empty.");
                return;
            }
            try {
                ((oh) ofVar).zza(new zzc(new f(ofVar.getContext(), ((ok) ofVar).zzui(), ((on) ofVar).getView()).a(map)));
            } catch (ActivityNotFoundException e5) {
                gv.e(e5.getMessage());
            }
        }
    }
}
