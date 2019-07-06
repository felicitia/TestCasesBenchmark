package com.google.zxing.qrcode.a;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.a;

/* compiled from: QRCode */
public final class f {
    private Mode a;
    private ErrorCorrectionLevel b;
    private a c;
    private int d = -1;
    private b e;

    public static boolean b(int i) {
        return i >= 0 && i < 8;
    }

    public b a() {
        return this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.a);
        sb.append("\n ecLevel: ");
        sb.append(this.b);
        sb.append("\n version: ");
        sb.append(this.c);
        sb.append("\n maskPattern: ");
        sb.append(this.d);
        if (this.e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.e);
        }
        sb.append(">>\n");
        return sb.toString();
    }

    public void a(Mode mode) {
        this.a = mode;
    }

    public void a(ErrorCorrectionLevel errorCorrectionLevel) {
        this.b = errorCorrectionLevel;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(b bVar) {
        this.e = bVar;
    }
}
