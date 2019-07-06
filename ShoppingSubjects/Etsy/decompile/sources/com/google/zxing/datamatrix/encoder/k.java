package com.google.zxing.datamatrix.encoder;

import com.google.zxing.a;

/* compiled from: SymbolInfo */
public class k {
    static final k[] a;
    private static k[] d;
    public final int b;
    public final int c;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;

    static {
        k kVar = new k(false, 3, 5, 8, 8, 1);
        k kVar2 = new k(false, 5, 7, 10, 10, 1);
        k kVar3 = new k(true, 5, 7, 16, 6, 1);
        k kVar4 = new k(false, 8, 10, 12, 12, 1);
        k kVar5 = new k(true, 10, 11, 14, 6, 2);
        k kVar6 = new k(false, 12, 12, 14, 14, 1);
        k kVar7 = new k(true, 16, 14, 24, 10, 1);
        k kVar8 = new k(false, 18, 14, 16, 16, 1);
        k kVar9 = new k(false, 22, 18, 18, 18, 1);
        k kVar10 = new k(true, 22, 18, 16, 10, 2);
        k kVar11 = new k(false, 30, 20, 20, 20, 1);
        k kVar12 = new k(true, 32, 24, 16, 14, 2);
        k kVar13 = new k(false, 36, 24, 22, 22, 1);
        k kVar14 = new k(false, 44, 28, 24, 24, 1);
        k kVar15 = new k(true, 49, 28, 22, 14, 2);
        k kVar16 = new k(false, 62, 36, 14, 14, 4);
        k kVar17 = new k(false, 86, 42, 16, 16, 4);
        k kVar18 = new k(false, 114, 48, 18, 18, 4);
        k kVar19 = new k(false, 144, 56, 20, 20, 4);
        k kVar20 = new k(false, 174, 68, 22, 22, 4);
        k kVar21 = new k(false, 204, 84, 24, 24, 4, 102, 42);
        k kVar22 = new k(false, 280, 112, 14, 14, 16, 140, 56);
        k kVar23 = new k(false, 368, 144, 16, 16, 16, 92, 36);
        k kVar24 = new k(false, 456, 192, 18, 18, 16, 114, 48);
        k kVar25 = new k(false, 576, 224, 20, 20, 16, 144, 56);
        k kVar26 = new k(false, 696, 272, 22, 22, 16, 174, 68);
        k kVar27 = new k(false, 816, 336, 24, 24, 16, 136, 56);
        k kVar28 = new k(false, 1050, 408, 18, 18, 36, 175, 68);
        k kVar29 = new k(false, 1304, 496, 20, 20, 36, 163, 62);
        k[] kVarArr = {kVar, kVar2, kVar3, kVar4, kVar5, kVar6, kVar7, kVar8, kVar9, kVar10, kVar11, kVar12, kVar13, kVar14, kVar15, kVar16, kVar17, kVar18, kVar19, kVar20, kVar21, kVar22, kVar23, kVar24, kVar25, kVar26, kVar27, kVar28, kVar29, new d()};
        a = kVarArr;
        d = kVarArr;
    }

    public k(boolean z, int i2, int i3, int i4, int i5, int i6) {
        this(z, i2, i3, i4, i5, i6, i2, i3);
    }

    k(boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.e = z;
        this.f = i2;
        this.g = i3;
        this.b = i4;
        this.c = i5;
        this.h = i6;
        this.i = i7;
        this.j = i8;
    }

    public static k a(int i2, SymbolShapeHint symbolShapeHint, a aVar, a aVar2, boolean z) {
        k[] kVarArr;
        for (k kVar : d) {
            if ((symbolShapeHint != SymbolShapeHint.FORCE_SQUARE || !kVar.e) && ((symbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE || kVar.e) && ((aVar == null || (kVar.d() >= aVar.a() && kVar.e() >= aVar.b())) && ((aVar2 == null || (kVar.d() <= aVar2.a() && kVar.e() <= aVar2.b())) && i2 <= kVar.f)))) {
                return kVar;
            }
        }
        if (!z) {
            return null;
        }
        StringBuilder sb = new StringBuilder("Can't find a symbol arrangement that matches the message. Data codewords: ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    private int h() {
        int i2 = this.h;
        if (i2 != 4) {
            if (i2 == 16) {
                return 4;
            }
            if (i2 == 36) {
                return 6;
            }
            switch (i2) {
                case 1:
                    return 1;
                case 2:
                    break;
                default:
                    throw new IllegalStateException("Cannot handle this number of data regions");
            }
        }
        return 2;
    }

    private int i() {
        int i2 = this.h;
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 36) {
            return 6;
        }
        switch (i2) {
            case 1:
            case 2:
                return 1;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    public final int b() {
        return h() * this.b;
    }

    public final int c() {
        return i() * this.c;
    }

    public final int d() {
        return b() + (h() << 1);
    }

    public final int e() {
        return c() + (i() << 1);
    }

    public int a() {
        return this.f / this.i;
    }

    public final int f() {
        return this.f;
    }

    public final int g() {
        return this.g;
    }

    public int a(int i2) {
        return this.i;
    }

    public final int b(int i2) {
        return this.j;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.e ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.b);
        sb.append('x');
        sb.append(this.c);
        sb.append(", symbol size ");
        sb.append(d());
        sb.append('x');
        sb.append(e());
        sb.append(", symbol data size ");
        sb.append(b());
        sb.append('x');
        sb.append(c());
        sb.append(", codewords ");
        sb.append(this.f);
        sb.append('+');
        sb.append(this.g);
        return sb.toString();
    }
}
