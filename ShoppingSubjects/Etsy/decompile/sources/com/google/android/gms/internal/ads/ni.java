package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

final class ni implements OnClickListener {
    private final /* synthetic */ JsResult a;

    ni(JsResult jsResult) {
        this.a = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.confirm();
    }
}
