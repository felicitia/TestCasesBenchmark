package com.google.zxing.datamatrix.encoder;

/* compiled from: TextEncoder */
final class l extends c {
    public int a() {
        return 2;
    }

    l() {
    }

    /* access modifiers changed from: 0000 */
    public int a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append(3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'a' && c <= 'z') {
            sb.append((char) ((c - 'a') + 14));
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
        } else if (c == '`') {
            sb.append(2);
            sb.append((char) (c - '`'));
            return 2;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append(2);
            sb.append((char) ((c - 'A') + 1));
            return 2;
        } else if (c >= '{' && c <= 127) {
            sb.append(2);
            sb.append((char) ((c - '{') + 27));
            return 2;
        } else if (c >= 128) {
            sb.append("\u0001\u001e");
            return 2 + a((char) (c - 128), sb);
        } else {
            j.c(c);
            return -1;
        }
    }
}
