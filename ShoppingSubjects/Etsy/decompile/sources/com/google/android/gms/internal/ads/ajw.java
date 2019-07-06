package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;

@bu
public final class ajw {
    private final Context a;

    public ajw(Context context) {
        Preconditions.checkNotNull(context, "Context can not be null");
        this.a = context;
    }

    private final boolean a(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent can not be null");
        return !this.a.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public final boolean a() {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return a(intent);
    }

    public final boolean b() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return a(intent);
    }

    public final boolean c() {
        return ((Boolean) jg.a(this.a, new ajx())).booleanValue() && Wrappers.packageManager(this.a).checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    @TargetApi(14)
    public final boolean d() {
        return a(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}
