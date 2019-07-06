package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.e;
import com.facebook.internal.a;

/* compiled from: ResultProcessor */
public abstract class i {
    private e a;

    public abstract void a(a aVar, Bundle bundle);

    public i(e eVar) {
        this.a = eVar;
    }

    public void a(a aVar) {
        if (this.a != null) {
            this.a.a();
        }
    }

    public void a(a aVar, FacebookException facebookException) {
        if (this.a != null) {
            this.a.a(facebookException);
        }
    }
}
