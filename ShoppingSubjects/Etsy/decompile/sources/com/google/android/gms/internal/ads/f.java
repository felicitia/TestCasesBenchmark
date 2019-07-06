package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.a.a.C0131a;
import com.google.android.gms.ads.internal.ao;
import java.util.Map;

@bu
public final class f extends m {
    private final Map<String, String> a;
    /* access modifiers changed from: private */
    public final Context b;

    public f(nn nnVar, Map<String, String> map) {
        super(nnVar, "storePicture");
        this.a = map;
        this.b = nnVar.zzto();
    }

    public final void a() {
        if (this.b == null) {
            a("Activity context is not available");
            return;
        }
        ao.e();
        if (!hd.f(this.b).c()) {
            a("Feature is not supported by the device.");
            return;
        }
        String str = (String) this.a.get("iurl");
        if (TextUtils.isEmpty(str)) {
            a("Image url cannot be empty.");
        } else if (!URLUtil.isValidUrl(str)) {
            String str2 = "Invalid image url: ";
            String valueOf = String.valueOf(str);
            a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            ao.e();
            if (!hd.c(lastPathSegment)) {
                String str3 = "Image type not recognized: ";
                String valueOf2 = String.valueOf(lastPathSegment);
                a(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                return;
            }
            Resources h = ao.i().h();
            ao.e();
            Builder e = hd.e(this.b);
            e.setTitle(h != null ? h.getString(C0131a.s1) : "Save image");
            e.setMessage(h != null ? h.getString(C0131a.s2) : "Allow Ad to store image in Picture gallery?");
            e.setPositiveButton(h != null ? h.getString(C0131a.s3) : "Accept", new g(this, str, lastPathSegment));
            e.setNegativeButton(h != null ? h.getString(C0131a.s4) : "Decline", new h(this));
            e.create().show();
        }
    }
}
