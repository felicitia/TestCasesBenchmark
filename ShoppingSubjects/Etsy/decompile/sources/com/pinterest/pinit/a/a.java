package com.pinterest.pinit.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Base64;
import com.pinterest.pinit.a.a.b;
import com.pinterest.pinit.a.a.c;

/* compiled from: Assets */
public class a {
    private static int[] a = {16842910};
    private static int[] b = {16842919};

    public static StateListDrawable a(Context context) {
        Resources resources = context.getResources();
        int i = resources.getDisplayMetrics().densityDpi;
        StateListDrawable stateListDrawable = new StateListDrawable();
        byte[] bArr = i != 240 ? (i == 320 || i == 480) ? a(c.b()) : a(b.b()) : a(com.pinterest.pinit.a.a.a.b());
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        byte[] ninePatchChunk = decodeByteArray.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        int[] iArr = b;
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(resources, decodeByteArray, ninePatchChunk, new Rect(), null);
        stateListDrawable.addState(iArr, ninePatchDrawable);
        byte[] bArr2 = i != 240 ? (i == 320 || i == 480) ? a(c.a()) : a(b.a()) : a(com.pinterest.pinit.a.a.a.a());
        Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length);
        byte[] ninePatchChunk2 = decodeByteArray2.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk2);
        int[] iArr2 = a;
        NinePatchDrawable ninePatchDrawable2 = new NinePatchDrawable(resources, decodeByteArray2, ninePatchChunk2, new Rect(), null);
        stateListDrawable.addState(iArr2, ninePatchDrawable2);
        return stateListDrawable;
    }

    private static byte[] a(String str) {
        return Base64.decode(str, 0);
    }
}
