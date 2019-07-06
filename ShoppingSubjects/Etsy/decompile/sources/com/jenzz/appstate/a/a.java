package com.jenzz.appstate.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.jenzz.appstate.AppState;

@RestrictTo({Scope.LIBRARY})
/* compiled from: AppStateRecognizer */
public interface a {
    void a();

    void a(@NonNull com.jenzz.appstate.a aVar);

    @NonNull
    AppState b();
}
