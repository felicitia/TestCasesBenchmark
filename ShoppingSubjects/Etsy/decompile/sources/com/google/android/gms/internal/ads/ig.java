package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import com.google.android.gms.ads.internal.ao;

final class ig implements OnClickListener {
    private final /* synthetic */ Cif a;

    ig(Cif ifVar) {
        this.a = ifVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        ao.e();
        hd.a(this.a.a, Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push"));
    }
}
