package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

final class h implements OnClickListener {
    private final /* synthetic */ Context a;
    private final /* synthetic */ Intent b;

    h(Context context, Intent intent) {
        this.a = context;
        this.b = intent;
    }

    public final void onClick(View view) {
        try {
            this.a.startActivity(this.b);
        } catch (ActivityNotFoundException e) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
        }
    }
}
