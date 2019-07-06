package com.google.zxing.datamatrix.encoder;

/* compiled from: ASCIIEncoder */
final class a implements g {
    public int a() {
        return 0;
    }

    a() {
    }

    public void a(h hVar) {
        if (j.a((CharSequence) hVar.a(), hVar.a) >= 2) {
            hVar.a(a(hVar.a().charAt(hVar.a), hVar.a().charAt(hVar.a + 1)));
            hVar.a += 2;
            return;
        }
        char b = hVar.b();
        int a = j.a(hVar.a(), hVar.a, a());
        if (a != a()) {
            switch (a) {
                case 1:
                    hVar.a(230);
                    hVar.b(1);
                    return;
                case 2:
                    hVar.a(239);
                    hVar.b(2);
                    return;
                case 3:
                    hVar.a(238);
                    hVar.b(3);
                    return;
                case 4:
                    hVar.a(240);
                    hVar.b(4);
                    return;
                case 5:
                    hVar.a(231);
                    hVar.b(5);
                    return;
                default:
                    StringBuilder sb = new StringBuilder("Illegal mode: ");
                    sb.append(a);
                    throw new IllegalStateException(sb.toString());
            }
        } else if (j.b(b)) {
            hVar.a(235);
            hVar.a((char) ((b - 128) + 1));
            hVar.a++;
        } else {
            hVar.a((char) (b + 1));
            hVar.a++;
        }
    }

    private static char a(char c, char c2) {
        if (j.a(c) && j.a(c2)) {
            return (char) (((c - '0') * 10) + (c2 - '0') + 130);
        }
        StringBuilder sb = new StringBuilder("not digits: ");
        sb.append(c);
        sb.append(c2);
        throw new IllegalArgumentException(sb.toString());
    }
}
