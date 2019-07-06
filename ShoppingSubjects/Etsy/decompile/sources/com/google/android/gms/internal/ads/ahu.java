package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahu extends aam<ahu> {
    private Integer a;
    private aih b;
    private aih c;
    private aih d;
    private aih[] e;
    private Integer f;

    public ahu() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = aih.b();
        this.f = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a.intValue());
        }
        if (this.b != null) {
            a2 += aal.b(2, (aar) this.b);
        }
        if (this.c != null) {
            a2 += aal.b(3, (aar) this.c);
        }
        if (this.d != null) {
            a2 += aal.b(4, (aar) this.d);
        }
        if (this.e != null && this.e.length > 0) {
            for (aih aih : this.e) {
                if (aih != null) {
                    a2 += aal.b(5, (aar) aih);
                }
            }
        }
        return this.f != null ? a2 + aal.b(6, this.f.intValue()) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        aih aih;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 != 8) {
                if (a2 == 18) {
                    if (this.b == null) {
                        this.b = new aih();
                    }
                    aih = this.b;
                } else if (a2 == 26) {
                    if (this.c == null) {
                        this.c = new aih();
                    }
                    aih = this.c;
                } else if (a2 == 34) {
                    if (this.d == null) {
                        this.d = new aih();
                    }
                    aih = this.d;
                } else if (a2 == 42) {
                    int a3 = aau.a(aaj, 42);
                    int length = this.e == null ? 0 : this.e.length;
                    aih[] aihArr = new aih[(a3 + length)];
                    if (length != 0) {
                        System.arraycopy(this.e, 0, aihArr, 0, length);
                    }
                    while (length < aihArr.length - 1) {
                        aihArr[length] = new aih();
                        aaj.a((aar) aihArr[length]);
                        aaj.a();
                        length++;
                    }
                    aihArr[length] = new aih();
                    aaj.a((aar) aihArr[length]);
                    this.e = aihArr;
                } else if (a2 == 48) {
                    this.f = Integer.valueOf(aaj.g());
                } else if (!super.a(aaj, a2)) {
                    return this;
                }
                aaj.a((aar) aih);
            } else {
                this.a = Integer.valueOf(aaj.g());
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(2, (aar) this.b);
        }
        if (this.c != null) {
            aal.a(3, (aar) this.c);
        }
        if (this.d != null) {
            aal.a(4, (aar) this.d);
        }
        if (this.e != null && this.e.length > 0) {
            for (aih aih : this.e) {
                if (aih != null) {
                    aal.a(5, (aar) aih);
                }
            }
        }
        if (this.f != null) {
            aal.a(6, this.f.intValue());
        }
        super.a(aal);
    }
}
