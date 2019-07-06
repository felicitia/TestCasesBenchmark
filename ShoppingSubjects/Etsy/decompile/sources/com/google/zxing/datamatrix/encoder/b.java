package com.google.zxing.datamatrix.encoder;

import android.support.v4.view.InputDeviceCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

/* compiled from: Base256Encoder */
final class b implements g {
    public int a() {
        return 5;
    }

    b() {
    }

    public void a(h hVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(0);
        while (true) {
            if (!hVar.g()) {
                break;
            }
            sb.append(hVar.b());
            hVar.a++;
            int a = j.a(hVar.a(), hVar.a, a());
            if (a != a()) {
                hVar.b(a);
                break;
            }
        }
        int length = sb.length() - 1;
        int d = hVar.d() + length + 1;
        hVar.c(d);
        boolean z = hVar.i().f() - d > 0;
        if (hVar.g() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length <= 1555) {
                sb.setCharAt(0, (char) ((length / Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + 249));
                sb.insert(1, (char) (length % Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            } else {
                StringBuilder sb2 = new StringBuilder("Message length not in valid ranges: ");
                sb2.append(length);
                throw new IllegalStateException(sb2.toString());
            }
        }
        int length2 = sb.length();
        for (int i = 0; i < length2; i++) {
            hVar.a(a(sb.charAt(i), hVar.d() + 1));
        }
    }

    private static char a(char c, int i) {
        int i2 = c + ((i * 149) % 255) + 1;
        return i2 <= 255 ? (char) i2 : (char) (i2 + InputDeviceCompat.SOURCE_ANY);
    }
}
