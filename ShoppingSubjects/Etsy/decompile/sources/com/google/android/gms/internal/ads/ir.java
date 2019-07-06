package com.google.android.gms.internal.ads;

final class ir implements arn {
    private final /* synthetic */ String a;
    private final /* synthetic */ iv b;

    ir(in inVar, String str, iv ivVar) {
        this.a = str;
        this.b = ivVar;
    }

    public final void a(zzae zzae) {
        String str = this.a;
        String zzae2 = zzae.toString();
        StringBuilder sb = new StringBuilder(21 + String.valueOf(str).length() + String.valueOf(zzae2).length());
        sb.append("Failed to load URL: ");
        sb.append(str);
        sb.append("\n");
        sb.append(zzae2);
        gv.e(sb.toString());
        this.b.a(null);
    }
}
