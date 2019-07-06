package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule.a;
import com.google.android.gms.dynamite.DynamiteModule.a.C0138a;
import com.google.android.gms.dynamite.DynamiteModule.a.b;

final class c implements a {
    c() {
    }

    public final b a(Context context, String str, C0138a aVar) throws LoadingException {
        b bVar = new b();
        bVar.a = aVar.a(context, str);
        if (bVar.a != 0) {
            bVar.c = -1;
            return bVar;
        }
        bVar.b = aVar.a(context, str, true);
        if (bVar.b != 0) {
            bVar.c = 1;
        }
        return bVar;
    }
}
