package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.components.e;
import com.google.firebase.components.f;
import java.util.Arrays;
import java.util.List;

@Keep
@KeepForSdk
public final class Registrar implements e {

    private static class a implements com.google.firebase.iid.a.a {
        private final FirebaseInstanceId a;

        public a(FirebaseInstanceId firebaseInstanceId) {
            this.a = firebaseInstanceId;
        }
    }

    @Keep
    public final List<com.google.firebase.components.a<?>> getComponents() {
        return Arrays.asList(new com.google.firebase.components.a[]{com.google.firebase.components.a.a(FirebaseInstanceId.class).a(f.a(com.google.firebase.a.class)).a(h.a).a().c(), com.google.firebase.components.a.a(com.google.firebase.iid.a.a.class).a(f.a(FirebaseInstanceId.class)).a(i.a).c()});
    }
}
