package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.a.a.C0131a;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.Map;

@bu
public final class asd extends m {
    private final Map<String, String> a;
    /* access modifiers changed from: private */
    public final Context b;
    private String c = d("description");
    private long d = e("start_ticks");
    private long e = e("end_ticks");
    private String f = d("summary");
    private String g = d(ResponseConstants.LOCATION);

    public asd(nn nnVar, Map<String, String> map) {
        super(nnVar, "createCalendarEvent");
        this.a = map;
        this.b = nnVar.zzto();
    }

    private final String d(String str) {
        return TextUtils.isEmpty((CharSequence) this.a.get(str)) ? "" : (String) this.a.get(str);
    }

    private final long e(String str) {
        String str2 = (String) this.a.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final void a() {
        if (this.b == null) {
            a("Activity context is not available.");
            return;
        }
        ao.e();
        if (!hd.f(this.b).d()) {
            a("This feature is not available on the device.");
            return;
        }
        ao.e();
        Builder e2 = hd.e(this.b);
        Resources h = ao.i().h();
        e2.setTitle(h != null ? h.getString(C0131a.s5) : "Create calendar event");
        e2.setMessage(h != null ? h.getString(C0131a.s6) : "Allow Ad to create a calendar event?");
        e2.setPositiveButton(h != null ? h.getString(C0131a.s3) : "Accept", new ase(this));
        e2.setNegativeButton(h != null ? h.getString(C0131a.s4) : "Decline", new b(this));
        e2.create().show();
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(14)
    public final Intent b() {
        Intent data = new Intent("android.intent.action.EDIT").setData(Events.CONTENT_URI);
        data.putExtra("title", this.c);
        data.putExtra("eventLocation", this.g);
        data.putExtra("description", this.f);
        if (this.d > -1) {
            data.putExtra("beginTime", this.d);
        }
        if (this.e > -1) {
            data.putExtra("endTime", this.e);
        }
        data.setFlags(ErrorDialogData.BINDER_CRASH);
        return data;
    }
}
