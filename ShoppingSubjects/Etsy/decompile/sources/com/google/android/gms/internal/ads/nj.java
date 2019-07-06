package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsPromptResult;

final class nj implements OnCancelListener {
    private final /* synthetic */ JsPromptResult a;

    nj(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
    }
}
