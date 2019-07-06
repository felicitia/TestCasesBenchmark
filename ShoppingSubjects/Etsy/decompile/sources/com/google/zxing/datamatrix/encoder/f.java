package com.google.zxing.datamatrix.encoder;

/* compiled from: EdifactEncoder */
final class f implements g {
    public int a() {
        return 4;
    }

    f() {
    }

    public void a(h hVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!hVar.g()) {
                break;
            }
            a(hVar.b(), sb);
            hVar.a++;
            if (sb.length() >= 4) {
                hVar.a(a((CharSequence) sb, 0));
                sb.delete(0, 4);
                if (j.a(hVar.a(), hVar.a, a()) != a()) {
                    hVar.b(0);
                    break;
                }
            }
        }
        sb.append(31);
        a(hVar, (CharSequence) sb);
    }

    private static void a(h hVar, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length != 0) {
                boolean z = true;
                if (length == 1) {
                    hVar.j();
                    int f = hVar.i().f() - hVar.d();
                    if (hVar.h() == 0 && f <= 2) {
                        hVar.b(0);
                        return;
                    }
                }
                if (length > 4) {
                    throw new IllegalStateException("Count must not exceed 4");
                }
                int i = length - 1;
                String a = a(charSequence, 0);
                if (!(!hVar.g()) || i > 2) {
                    z = false;
                }
                if (i <= 2) {
                    hVar.c(hVar.d() + i);
                    if (hVar.i().f() - hVar.d() >= 3) {
                        hVar.c(hVar.d() + a.length());
                        z = false;
                    }
                }
                if (z) {
                    hVar.k();
                    hVar.a -= i;
                } else {
                    hVar.a(a);
                }
                hVar.b(0);
            }
        } finally {
            hVar.b(0);
        }
    }

    private static void a(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c < '@' || c > '^') {
            j.c(c);
        } else {
            sb.append((char) (c - '@'));
        }
    }

    private static String a(CharSequence charSequence, int i) {
        int length = charSequence.length() - i;
        if (length == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        char charAt = charSequence.charAt(i);
        char c = 0;
        int charAt2 = length >= 2 ? charSequence.charAt(i + 1) : 0;
        int charAt3 = length >= 3 ? charSequence.charAt(i + 2) : 0;
        if (length >= 4) {
            c = charSequence.charAt(i + 3);
        }
        int i2 = (charAt << 18) + (charAt2 << 12) + (charAt3 << 6) + c;
        char c2 = (char) ((i2 >> 16) & 255);
        char c3 = (char) ((i2 >> 8) & 255);
        char c4 = (char) (i2 & 255);
        StringBuilder sb = new StringBuilder(3);
        sb.append(c2);
        if (length >= 2) {
            sb.append(c3);
        }
        if (length >= 3) {
            sb.append(c4);
        }
        return sb.toString();
    }
}
