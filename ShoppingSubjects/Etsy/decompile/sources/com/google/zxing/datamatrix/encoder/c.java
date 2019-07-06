package com.google.zxing.datamatrix.encoder;

/* compiled from: C40Encoder */
class c implements g {
    public int a() {
        return 1;
    }

    c() {
    }

    public void a(h hVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!hVar.g()) {
                break;
            }
            char b = hVar.b();
            hVar.a++;
            int a = a(b, sb);
            int d = hVar.d() + ((sb.length() / 3) << 1);
            hVar.c(d);
            int f = hVar.i().f() - d;
            if (!hVar.g()) {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && (f < 2 || f > 2)) {
                    a = a(hVar, sb, sb2, a);
                }
                while (sb.length() % 3 == 1 && ((a <= 3 && f != 1) || a > 3)) {
                    a = a(hVar, sb, sb2, a);
                }
            } else if (sb.length() % 3 == 0) {
                int a2 = j.a(hVar.a(), hVar.a, a());
                if (a2 != a()) {
                    hVar.b(a2);
                    break;
                }
            }
        }
        b(hVar, sb);
    }

    private int a(h hVar, StringBuilder sb, StringBuilder sb2, int i) {
        int length = sb.length();
        sb.delete(length - i, length);
        hVar.a--;
        int a = a(hVar.b(), sb2);
        hVar.k();
        return a;
    }

    static void a(h hVar, StringBuilder sb) {
        hVar.a(a((CharSequence) sb, 0));
        sb.delete(0, 3);
    }

    /* access modifiers changed from: 0000 */
    public void b(h hVar, StringBuilder sb) {
        int length = sb.length() % 3;
        int d = hVar.d() + ((sb.length() / 3) << 1);
        hVar.c(d);
        int f = hVar.i().f() - d;
        if (length == 2) {
            sb.append(0);
            while (sb.length() >= 3) {
                a(hVar, sb);
            }
            if (hVar.g()) {
                hVar.a(254);
            }
        } else if (f == 1 && length == 1) {
            while (sb.length() >= 3) {
                a(hVar, sb);
            }
            if (hVar.g()) {
                hVar.a(254);
            }
            hVar.a--;
        } else if (length == 0) {
            while (sb.length() >= 3) {
                a(hVar, sb);
            }
            if (f > 0 || hVar.g()) {
                hVar.a(254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        hVar.b(0);
    }

    /* access modifiers changed from: 0000 */
    public int a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append(3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
            return 1;
        } else if (c >= 0 && c <= 31) {
            sb.append(0);
            sb.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            sb.append(1);
            sb.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            sb.append(1);
            sb.append((char) ((c - ':') + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            sb.append(1);
            sb.append((char) ((c - '[') + 22));
            return 2;
        } else if (c >= '`' && c <= 127) {
            sb.append(2);
            sb.append((char) (c - '`'));
            return 2;
        } else if (c >= 128) {
            sb.append("\u0001\u001e");
            return 2 + a((char) (c - 128), sb);
        } else {
            StringBuilder sb2 = new StringBuilder("Illegal character: ");
            sb2.append(c);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    private static String a(CharSequence charSequence, int i) {
        int charAt = (charSequence.charAt(i) * 1600) + (charSequence.charAt(i + 1) * '(') + charSequence.charAt(i + 2) + 1;
        return new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)});
    }
}
