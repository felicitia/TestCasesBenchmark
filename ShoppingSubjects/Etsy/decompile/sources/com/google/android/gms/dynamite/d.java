package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule.a;
import com.google.android.gms.dynamite.DynamiteModule.a.C0138a;
import com.google.android.gms.dynamite.DynamiteModule.a.b;

final class d implements a {
    d() {
    }

    public final b a(Context context, String str, C0138a aVar) throws LoadingException {
        int i;
        b bVar = new b();
        bVar.a = aVar.a(context, str);
        bVar.b = aVar.a(context, str, true);
        if (bVar.a == 0 && bVar.b == 0) {
            i = 0;
        } else if (bVar.a >= bVar.b) {
            i = -1;
        } else {
            bVar.c = 1;
            return bVar;
        }
        bVar.c = i;
        return bVar;
    }
}
