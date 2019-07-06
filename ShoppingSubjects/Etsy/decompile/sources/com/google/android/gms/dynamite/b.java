package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule.a;
import com.google.android.gms.dynamite.DynamiteModule.a.C0138a;

final class b implements a {
    b() {
    }

    public final com.google.android.gms.dynamite.DynamiteModule.a.b a(Context context, String str, C0138a aVar) throws LoadingException {
        com.google.android.gms.dynamite.DynamiteModule.a.b bVar = new com.google.android.gms.dynamite.DynamiteModule.a.b();
        bVar.b = aVar.a(context, str, true);
        if (bVar.b != 0) {
            bVar.c = 1;
            return bVar;
        }
        bVar.a = aVar.a(context, str);
        if (bVar.a != 0) {
            bVar.c = -1;
        }
        return bVar;
    }
}
