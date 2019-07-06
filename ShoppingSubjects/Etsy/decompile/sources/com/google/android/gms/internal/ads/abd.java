package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class abd extends aam<abd> {
    private static volatile abd[] f;
    public Integer a;
    public String b;
    public aay c;
    public Integer d;
    public String[] e;
    private aba g;
    private Integer h;
    private int[] i;
    private String j;

    public abd() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.g = null;
        this.h = null;
        this.i = aau.a;
        this.j = null;
        this.d = null;
        this.e = aau.c;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final abd a(aaj aaj) throws IOException {
        aar aar;
        int c2;
        while (true) {
            int a2 = aaj.a();
            switch (a2) {
                case 0:
                    return this;
                case 8:
                    this.a = Integer.valueOf(aaj.c());
                    continue;
                case 18:
                    this.b = aaj.e();
                    continue;
                case 26:
                    if (this.c == null) {
                        this.c = new aay();
                    }
                    aar = this.c;
                    break;
                case 34:
                    if (this.g == null) {
                        this.g = new aba();
                    }
                    aar = this.g;
                    break;
                case 40:
                    this.h = Integer.valueOf(aaj.c());
                    continue;
                case 48:
                    int a3 = aau.a(aaj, 48);
                    int length = this.i == null ? 0 : this.i.length;
                    int[] iArr = new int[(a3 + length)];
                    if (length != 0) {
                        System.arraycopy(this.i, 0, iArr, 0, length);
                    }
                    while (length < iArr.length - 1) {
                        iArr[length] = aaj.c();
                        aaj.a();
                        length++;
                    }
                    iArr[length] = aaj.c();
                    this.i = iArr;
                    continue;
                case 50:
                    int c3 = aaj.c(aaj.g());
                    int j2 = aaj.j();
                    int i2 = 0;
                    while (aaj.i() > 0) {
                        aaj.c();
                        i2++;
                    }
                    aaj.e(j2);
                    int length2 = this.i == null ? 0 : this.i.length;
                    int[] iArr2 = new int[(i2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.i, 0, iArr2, 0, length2);
                    }
                    while (length2 < iArr2.length) {
                        iArr2[length2] = aaj.c();
                        length2++;
                    }
                    this.i = iArr2;
                    aaj.d(c3);
                    continue;
                case 58:
                    this.j = aaj.e();
                    continue;
                case 64:
                    try {
                        c2 = aaj.c();
                        if (c2 < 0 || c2 > 3) {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append(c2);
                            sb.append(" is not a valid enum AdResourceType");
                            break;
                        } else {
                            this.d = Integer.valueOf(c2);
                            continue;
                        }
                    } catch (IllegalArgumentException unused) {
                        aaj.e(aaj.j());
                        a(aaj, a2);
                        break;
                    }
                case 74:
                    int a4 = aau.a(aaj, 74);
                    int length3 = this.e == null ? 0 : this.e.length;
                    String[] strArr = new String[(a4 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.e, 0, strArr, 0, length3);
                    }
                    while (length3 < strArr.length - 1) {
                        strArr[length3] = aaj.e();
                        aaj.a();
                        length3++;
                    }
                    strArr[length3] = aaj.e();
                    this.e = strArr;
                    continue;
                default:
                    if (!super.a(aaj, a2)) {
                        return this;
                    }
                    continue;
            }
            aaj.a(aar);
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(c2);
        sb2.append(" is not a valid enum AdResourceType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public static abd[] b() {
        if (f == null) {
            synchronized (aaq.b) {
                if (f == null) {
                    f = new abd[0];
                }
            }
        }
        return f;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a() + aal.b(1, this.a.intValue());
        if (this.b != null) {
            a2 += aal.b(2, this.b);
        }
        if (this.c != null) {
            a2 += aal.b(3, (aar) this.c);
        }
        if (this.g != null) {
            a2 += aal.b(4, (aar) this.g);
        }
        if (this.h != null) {
            a2 += aal.b(5, this.h.intValue());
        }
        if (this.i != null && this.i.length > 0) {
            int i2 = 0;
            for (int a3 : this.i) {
                i2 += aal.a(a3);
            }
            a2 = a2 + i2 + (this.i.length * 1);
        }
        if (this.j != null) {
            a2 += aal.b(7, this.j);
        }
        if (this.d != null) {
            a2 += aal.b(8, this.d.intValue());
        }
        if (this.e == null || this.e.length <= 0) {
            return a2;
        }
        int i3 = 0;
        int i4 = 0;
        for (String str : this.e) {
            if (str != null) {
                i4++;
                i3 += aal.a(str);
            }
        }
        return a2 + i3 + (1 * i4);
    }

    public final void a(aal aal) throws IOException {
        aal.a(1, this.a.intValue());
        if (this.b != null) {
            aal.a(2, this.b);
        }
        if (this.c != null) {
            aal.a(3, (aar) this.c);
        }
        if (this.g != null) {
            aal.a(4, (aar) this.g);
        }
        if (this.h != null) {
            aal.a(5, this.h.intValue());
        }
        if (this.i != null && this.i.length > 0) {
            for (int a2 : this.i) {
                aal.a(6, a2);
            }
        }
        if (this.j != null) {
            aal.a(7, this.j);
        }
        if (this.d != null) {
            aal.a(8, this.d.intValue());
        }
        if (this.e != null && this.e.length > 0) {
            for (String str : this.e) {
                if (str != null) {
                    aal.a(9, str);
                }
            }
        }
        super.a(aal);
    }
}
