package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule.a;
import com.google.android.gms.dynamite.DynamiteModule.a.C0138a;
import com.google.android.gms.dynamite.DynamiteModule.a.b;

final class e implements a {
    e() {
    }

    public final b a(Context context, String str, C0138a aVar) throws LoadingException {
        b bVar = new b();
        bVar.a = aVar.a(context, str);
        bVar.b = bVar.a != 0 ? aVar.a(context, str, false) : aVar.a(context, str, true);
        if (bVar.a == 0 && bVar.b == 0) {
            bVar.c = 0;
            return bVar;
        } else if (bVar.a >= bVar.b) {
            bVar.c = -1;
            return bVar;
        } else {
            bVar.c = 1;
            return bVar;
        }
    }
}
