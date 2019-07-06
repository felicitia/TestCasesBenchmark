package com.onfido.android.sdk.capture.ui.country_selection;

import android.telephony.TelephonyManager;
import io.reactivex.Observable;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public class GetCurrentCountryCodeUseCase {
    private final TelephonyManager a;

    public GetCurrentCountryCodeUseCase(TelephonyManager telephonyManager) {
        Intrinsics.checkParameterIsNotNull(telephonyManager, "telephonyManager");
        this.a = telephonyManager;
    }

    public Observable<String> build() {
        String str;
        String simCountryIso = this.a.getSimCountryIso();
        if (simCountryIso == null) {
            str = null;
        } else if (simCountryIso == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        } else {
            str = simCountryIso.toUpperCase();
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toUpperCase()");
        }
        Observable<String> just = Observable.just(str);
        Intrinsics.checkExpressionValueIsNotNull(just, "Observable.just(telephon…ountryIso?.toUpperCase())");
        return just;
    }
}
