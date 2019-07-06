package com.google.android.gms.gcm;

import android.os.Bundle;

public final class i {
    public static final i a = new i(0, 30, 3600);
    private static final i b = new i(1, 30, 3600);
    private final int c;
    private final int d = 30;
    private final int e = 3600;

    private i(int i, int i2, int i3) {
        this.c = i;
    }

    public final Bundle a(Bundle bundle) {
        bundle.putInt("retry_policy", this.c);
        bundle.putInt("initial_backoff_seconds", this.d);
        bundle.putInt("maximum_backoff_seconds", this.e);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return iVar.c == this.c && iVar.d == this.d && iVar.e == this.e;
    }

    public final int hashCode() {
        return (((((this.c + 1) ^ 1000003) * 1000003) ^ this.d) * 1000003) ^ this.e;
    }

    public final String toString() {
        int i = this.c;
        int i2 = this.d;
        int i3 = this.e;
        StringBuilder sb = new StringBuilder(74);
        sb.append("policy=");
        sb.append(i);
        sb.append(" initial_backoff=");
        sb.append(i2);
        sb.append(" maximum_backoff=");
        sb.append(i3);
        return sb.toString();
    }
}
