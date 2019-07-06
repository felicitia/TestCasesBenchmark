package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

final class nh implements OnClickListener {
    private final /* synthetic */ JsResult a;

    nh(JsResult jsResult) {
        this.a = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
