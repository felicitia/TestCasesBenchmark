package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final class e implements Comparator<Scope> {
    e() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
    }
}
