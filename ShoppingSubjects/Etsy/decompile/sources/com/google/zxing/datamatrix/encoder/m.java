package com.google.zxing.datamatrix.encoder;

/* compiled from: X12Encoder */
final class m extends c {
    public int a() {
        return 3;
    }

    m() {
    }

    public void a(h hVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!hVar.g()) {
                break;
            }
            char b = hVar.b();
            hVar.a++;
            a(b, sb);
            if (sb.length() % 3 == 0) {
                a(hVar, sb);
                int a = j.a(hVar.a(), hVar.a, a());
                if (a != a()) {
                    hVar.b(a);
                    break;
                }
            }
        }
        b(hVar, sb);
    }

    /* access modifiers changed from: 0000 */
    public int a(char c, StringBuilder sb) {
        if (c == 13) {
            sb.append(0);
        } else if (c == '*') {
            sb.append(1);
        } else if (c == '>') {
            sb.append(2);
        } else if (c == ' ') {
            sb.append(3);
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
        } else if (c < 'A' || c > 'Z') {
            j.c(c);
        } else {
            sb.append((char) ((c - 'A') + 14));
        }
        return 1;
    }

    /* access modifiers changed from: 0000 */
    public void b(h hVar, StringBuilder sb) {
        hVar.j();
        int f = hVar.i().f() - hVar.d();
        hVar.a -= sb.length();
        if (hVar.h() > 1 || f > 1 || hVar.h() != f) {
            hVar.a(254);
        }
        if (hVar.e() < 0) {
            hVar.b(0);
        }
    }
}
