package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final /* synthetic */ class d implements Comparator {
    static final Comparator a = new d();

    private d() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
    }
}
