package com.google.firebase.iid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.a;
import com.google.android.gms.tasks.f;
import java.io.IOException;

final class ag implements a<Bundle, String> {
    private final /* synthetic */ ad a;

    ag(ad adVar) {
        this.a = adVar;
    }

    public final /* synthetic */ Object then(@NonNull f fVar) throws Exception {
        return ad.a((Bundle) fVar.a(IOException.class));
    }
}
