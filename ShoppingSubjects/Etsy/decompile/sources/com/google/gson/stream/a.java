package com.google.gson.stream;

import com.google.gson.internal.a.e;
import com.google.gson.internal.d;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: JsonReader */
public class a implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    static {
        d.a = new d() {
            public void a(a aVar) throws IOException {
                if (aVar instanceof e) {
                    ((e) aVar).o();
                    return;
                }
                int i = aVar.a;
                if (i == 0) {
                    i = aVar.r();
                }
                if (i == 13) {
                    aVar.a = 9;
                } else if (i == 12) {
                    aVar.a = 8;
                } else if (i == 14) {
                    aVar.a = 10;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Expected a name but was ");
                    sb.append(aVar.f());
                    sb.append(aVar.s());
                    throw new IllegalStateException(sb.toString());
                }
            }
        };
    }

    public a(Reader reader) {
        int[] iArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr[i2] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected BEGIN_ARRAY but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public void b() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 4) {
            this.n--;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected END_ARRAY but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public void c() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 1) {
            a(3);
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected BEGIN_OBJECT but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public void d() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 2) {
            this.n--;
            this.o[this.n] = null;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            this.a = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected END_OBJECT but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public boolean e() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        return (i2 == 2 || i2 == 4) ? false : true;
    }

    public JsonToken f() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        switch (i2) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* access modifiers changed from: 0000 */
    public int r() throws IOException {
        int i2 = this.m[this.n - 1];
        if (i2 == 1) {
            this.m[this.n - 1] = 2;
        } else if (i2 == 2) {
            int b2 = b(true);
            if (b2 != 44) {
                if (b2 == 59) {
                    w();
                } else if (b2 != 93) {
                    throw b("Unterminated array");
                } else {
                    this.a = 4;
                    return 4;
                }
            }
        } else if (i2 == 3 || i2 == 5) {
            this.m[this.n - 1] = 4;
            if (i2 == 5) {
                int b3 = b(true);
                if (b3 != 44) {
                    if (b3 == 59) {
                        w();
                    } else if (b3 != 125) {
                        throw b("Unterminated object");
                    } else {
                        this.a = 2;
                        return 2;
                    }
                }
            }
            int b4 = b(true);
            if (b4 == 34) {
                this.a = 13;
                return 13;
            } else if (b4 == 39) {
                w();
                this.a = 12;
                return 12;
            } else if (b4 != 125) {
                w();
                this.f--;
                if (a((char) b4)) {
                    this.a = 14;
                    return 14;
                }
                throw b("Expected name");
            } else if (i2 != 5) {
                this.a = 2;
                return 2;
            } else {
                throw b("Expected name");
            }
        } else if (i2 == 4) {
            this.m[this.n - 1] = 5;
            int b5 = b(true);
            if (b5 != 58) {
                if (b5 != 61) {
                    throw b("Expected ':'");
                }
                w();
                if ((this.f < this.g || b(1)) && this.e[this.f] == '>') {
                    this.f++;
                }
            }
        } else if (i2 == 6) {
            if (this.d) {
                z();
            }
            this.m[this.n - 1] = 7;
        } else if (i2 == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            w();
            this.f--;
        } else if (i2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int b6 = b(true);
        if (b6 == 34) {
            this.a = 9;
            return 9;
        } else if (b6 != 39) {
            if (!(b6 == 44 || b6 == 59)) {
                if (b6 == 91) {
                    this.a = 3;
                    return 3;
                } else if (b6 != 93) {
                    if (b6 != 123) {
                        this.f--;
                        int o2 = o();
                        if (o2 != 0) {
                            return o2;
                        }
                        int t = t();
                        if (t != 0) {
                            return t;
                        }
                        if (!a(this.e[this.f])) {
                            throw b("Expected value");
                        }
                        w();
                        this.a = 10;
                        return 10;
                    }
                    this.a = 1;
                    return 1;
                } else if (i2 == 1) {
                    this.a = 4;
                    return 4;
                }
            }
            if (i2 == 1 || i2 == 2) {
                w();
                this.f--;
                this.a = 7;
                return 7;
            }
            throw b("Unexpected value");
        } else {
            w();
            this.a = 8;
            return 8;
        }
    }

    private int o() throws IOException {
        int i2;
        String str;
        String str2;
        char c2 = this.e[this.f];
        if (c2 == 't' || c2 == 'T') {
            str2 = "true";
            str = "TRUE";
            i2 = 5;
        } else if (c2 == 'f' || c2 == 'F') {
            str2 = "false";
            str = "FALSE";
            i2 = 6;
        } else if (c2 != 'n' && c2 != 'N') {
            return 0;
        } else {
            str2 = "null";
            str = "NULL";
            i2 = 7;
        }
        int length = str2.length();
        for (int i3 = 1; i3 < length; i3++) {
            if (this.f + i3 >= this.g && !b(i3 + 1)) {
                return 0;
            }
            char c3 = this.e[this.f + i3];
            if (c3 != str2.charAt(i3) && c3 != str.charAt(i3)) {
                return 0;
            }
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = i2;
        return i2;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int t() throws java.io.IOException {
        /*
            r21 = this;
            r0 = r21
            char[] r1 = r0.e
            int r2 = r0.f
            int r3 = r0.g
            r6 = 1
            r7 = 0
            r8 = r3
            r10 = r6
            r3 = r7
            r9 = r3
            r13 = r9
            r11 = 0
        L_0x0011:
            int r14 = r2 + r3
            r15 = 2
            if (r14 != r8) goto L_0x0028
            int r2 = r1.length
            if (r3 != r2) goto L_0x001a
            return r7
        L_0x001a:
            int r2 = r3 + 1
            boolean r2 = r0.b(r2)
            if (r2 != 0) goto L_0x0024
            goto L_0x0095
        L_0x0024:
            int r2 = r0.f
            int r8 = r0.g
        L_0x0028:
            int r14 = r2 + r3
            char r14 = r1[r14]
            r7 = 43
            r4 = 3
            r5 = 5
            if (r14 == r7) goto L_0x00ed
            r7 = 69
            if (r14 == r7) goto L_0x00e1
            r7 = 101(0x65, float:1.42E-43)
            if (r14 == r7) goto L_0x00e1
            switch(r14) {
                case 45: goto L_0x00d4;
                case 46: goto L_0x00cd;
                default: goto L_0x003d;
            }
        L_0x003d:
            r7 = 48
            if (r14 < r7) goto L_0x008f
            r7 = 57
            if (r14 <= r7) goto L_0x0046
            goto L_0x008f
        L_0x0046:
            if (r9 == r6) goto L_0x0084
            if (r9 != 0) goto L_0x004b
            goto L_0x0084
        L_0x004b:
            if (r9 != r15) goto L_0x0077
            r18 = 0
            int r4 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r4 != 0) goto L_0x0055
            r4 = 0
            return r4
        L_0x0055:
            r4 = 10
            long r4 = r4 * r11
            int r14 = r14 + -48
            long r14 = (long) r14
            long r16 = r4 - r14
            r4 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r7 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x0071
            int r7 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x006f
            int r4 = (r16 > r11 ? 1 : (r16 == r11 ? 0 : -1))
            if (r4 >= 0) goto L_0x006f
            goto L_0x0071
        L_0x006f:
            r4 = 0
            goto L_0x0072
        L_0x0071:
            r4 = r6
        L_0x0072:
            r4 = r4 & r10
            r10 = r4
            r11 = r16
            goto L_0x008a
        L_0x0077:
            if (r9 != r4) goto L_0x007c
            r7 = 0
            r9 = 4
            goto L_0x008b
        L_0x007c:
            if (r9 == r5) goto L_0x0081
            r4 = 6
            if (r9 != r4) goto L_0x008a
        L_0x0081:
            r7 = 0
            r9 = 7
            goto L_0x008b
        L_0x0084:
            int r14 = r14 + -48
            int r4 = -r14
            long r4 = (long) r4
            r11 = r4
            r9 = r15
        L_0x008a:
            r7 = 0
        L_0x008b:
            r18 = 0
            goto L_0x00f4
        L_0x008f:
            boolean r1 = r0.a(r14)
            if (r1 != 0) goto L_0x00cb
        L_0x0095:
            if (r9 != r15) goto L_0x00b9
            if (r10 == 0) goto L_0x00b9
            r1 = -9223372036854775808
            int r4 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x00a1
            if (r13 == 0) goto L_0x00b9
        L_0x00a1:
            r18 = 0
            int r1 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r1 != 0) goto L_0x00a9
            if (r13 != 0) goto L_0x00b9
        L_0x00a9:
            if (r13 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            long r11 = -r11
        L_0x00ad:
            r0.j = r11
            int r1 = r0.f
            int r1 = r1 + r3
            r0.f = r1
            r1 = 15
            r0.a = r1
            return r1
        L_0x00b9:
            if (r9 == r15) goto L_0x00c4
            r1 = 4
            if (r9 == r1) goto L_0x00c4
            r1 = 7
            if (r9 != r1) goto L_0x00c2
            goto L_0x00c4
        L_0x00c2:
            r7 = 0
            return r7
        L_0x00c4:
            r0.k = r3
            r1 = 16
            r0.a = r1
            return r1
        L_0x00cb:
            r7 = 0
            return r7
        L_0x00cd:
            r7 = 0
            r18 = 0
            if (r9 != r15) goto L_0x00d3
            goto L_0x00f3
        L_0x00d3:
            return r7
        L_0x00d4:
            r4 = 6
            r7 = 0
            r18 = 0
            if (r9 != 0) goto L_0x00dd
            r9 = r6
            r13 = r9
            goto L_0x00f4
        L_0x00dd:
            if (r9 != r5) goto L_0x00e0
            goto L_0x00f3
        L_0x00e0:
            return r7
        L_0x00e1:
            r7 = 0
            r18 = 0
            if (r9 == r15) goto L_0x00eb
            r4 = 4
            if (r9 != r4) goto L_0x00ea
            goto L_0x00eb
        L_0x00ea:
            return r7
        L_0x00eb:
            r9 = r5
            goto L_0x00f4
        L_0x00ed:
            r4 = 6
            r7 = 0
            r18 = 0
            if (r9 != r5) goto L_0x00f8
        L_0x00f3:
            r9 = r4
        L_0x00f4:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x00f8:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.a.t():int");
    }

    private boolean a(char c2) throws IOException {
        switch (c2) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                w();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() throws IOException {
        String str;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 14) {
            str = u();
        } else if (i2 == 12) {
            str = b('\'');
        } else if (i2 == 13) {
            str = b('\"');
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a name but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 0;
        this.o[this.n - 1] = str;
        return str;
    }

    public String h() throws IOException {
        String str;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 10) {
            str = u();
        } else if (i2 == 8) {
            str = b('\'');
        } else if (i2 == 9) {
            str = b('\"');
        } else if (i2 == 11) {
            str = this.l;
            this.l = null;
        } else if (i2 == 15) {
            str = Long.toString(this.j);
        } else if (i2 == 16) {
            str = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a string but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i3 = this.n - 1;
        iArr[i3] = iArr[i3] + 1;
        return str;
    }

    public boolean i() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 5) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a boolean but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
    }

    public void j() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected null but was ");
        sb.append(f());
        sb.append(s());
        throw new IllegalStateException(sb.toString());
    }

    public double k() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return (double) this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9) {
            this.l = b(i2 == 8 ? '\'' : '\"');
        } else if (i2 == 10) {
            this.l = u();
        } else if (i2 != 11) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a double but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return parseDouble;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("JSON forbids NaN and infinities: ");
        sb2.append(parseDouble);
        sb2.append(s());
        throw new MalformedJsonException(sb2.toString());
    }

    public long l() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = u();
            } else {
                this.l = b(i2 == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i4 = this.n - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a long but was ");
            sb.append(f());
            sb.append(s());
            throw new IllegalStateException(sb.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        long j2 = (long) parseDouble;
        if (((double) j2) != parseDouble) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected a long but was ");
            sb2.append(this.l);
            sb2.append(s());
            throw new NumberFormatException(sb2.toString());
        }
        this.l = null;
        this.a = 0;
        int[] iArr3 = this.p;
        int i5 = this.n - 1;
        iArr3[i5] = iArr3[i5] + 1;
        return j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        if (r1 != null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005d, code lost:
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r4) * 2, 16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006b, code lost:
        r1.append(r0, r4, r2 - r4);
        r9.f = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(char r10) throws java.io.IOException {
        /*
            r9 = this;
            char[] r0 = r9.e
            r1 = 0
        L_0x0003:
            int r2 = r9.f
            int r3 = r9.g
        L_0x0007:
            r4 = r2
        L_0x0008:
            r5 = 16
            r6 = 1
            if (r2 >= r3) goto L_0x005b
            int r7 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r10) goto L_0x0027
            r9.f = r7
            int r7 = r7 - r4
            int r7 = r7 - r6
            if (r1 != 0) goto L_0x001f
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0, r4, r7)
            return r10
        L_0x001f:
            r1.append(r0, r4, r7)
            java.lang.String r10 = r1.toString()
            return r10
        L_0x0027:
            r8 = 92
            if (r2 != r8) goto L_0x004e
            r9.f = r7
            int r7 = r7 - r4
            int r7 = r7 - r6
            if (r1 != 0) goto L_0x003f
            int r1 = r7 + 1
            int r1 = r1 * 2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r2.<init>(r1)
            r1 = r2
        L_0x003f:
            r1.append(r0, r4, r7)
            char r2 = r9.y()
            r1.append(r2)
            int r2 = r9.f
            int r3 = r9.g
            goto L_0x0007
        L_0x004e:
            r5 = 10
            if (r2 != r5) goto L_0x0059
            int r2 = r9.h
            int r2 = r2 + r6
            r9.h = r2
            r9.i = r7
        L_0x0059:
            r2 = r7
            goto L_0x0008
        L_0x005b:
            if (r1 != 0) goto L_0x006b
            int r1 = r2 - r4
            int r1 = r1 * 2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r3.<init>(r1)
            r1 = r3
        L_0x006b:
            int r3 = r2 - r4
            r1.append(r0, r4, r3)
            r9.f = r2
            boolean r2 = r9.b(r6)
            if (r2 != 0) goto L_0x0003
            java.lang.String r10 = "Unterminated string"
            java.io.IOException r10 = r9.b(r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.a.b(char):java.lang.String");
    }

    private String u() throws IOException {
        int i2;
        String str;
        int i3 = 0;
        StringBuilder sb = null;
        while (true) {
            i2 = 0;
            while (true) {
                if (this.f + i2 < this.g) {
                    switch (this.e[this.f + i2]) {
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case '{':
                        case '}':
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            w();
                            break;
                        default:
                            i2++;
                            break;
                    }
                } else if (i2 >= this.e.length) {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i2, 16));
                    }
                    sb.append(this.e, this.f, i2);
                    this.f += i2;
                    if (!b(1)) {
                    }
                } else if (b(i2 + 1)) {
                }
            }
        }
        i3 = i2;
        if (sb == null) {
            str = new String(this.e, this.f, i3);
        } else {
            sb.append(this.e, this.f, i3);
            str = sb.toString();
        }
        this.f += i3;
        return str;
    }

    private void c(char c2) throws IOException {
        char[] cArr = this.e;
        do {
            int i2 = this.f;
            int i3 = this.g;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f = i4;
                    return;
                } else if (c3 == '\\') {
                    this.f = i4;
                    y();
                    i2 = this.f;
                    i3 = this.g;
                } else {
                    if (c3 == 10) {
                        this.h++;
                        this.i = i4;
                    }
                    i2 = i4;
                }
            }
            this.f = i2;
        } while (b(1));
        throw b("Unterminated string");
    }

    private void v() throws IOException {
        do {
            int i2 = 0;
            while (this.f + i2 < this.g) {
                switch (this.e[this.f + i2]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        w();
                        break;
                    default:
                        i2++;
                }
                this.f += i2;
                return;
            }
            this.f += i2;
        } while (b(1));
    }

    public int m() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            int i3 = (int) this.j;
            if (this.j != ((long) i3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Expected an int but was ");
                sb.append(this.j);
                sb.append(s());
                throw new NumberFormatException(sb.toString());
            }
            this.a = 0;
            int[] iArr = this.p;
            int i4 = this.n - 1;
            iArr[i4] = iArr[i4] + 1;
            return i3;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = u();
            } else {
                this.l = b(i2 == 8 ? '\'' : '\"');
            }
            try {
                int parseInt = Integer.parseInt(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i5 = this.n - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected an int but was ");
            sb2.append(f());
            sb2.append(s());
            throw new IllegalStateException(sb2.toString());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        int i6 = (int) parseDouble;
        if (((double) i6) != parseDouble) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Expected an int but was ");
            sb3.append(this.l);
            sb3.append(s());
            throw new NumberFormatException(sb3.toString());
        }
        this.l = null;
        this.a = 0;
        int[] iArr3 = this.p;
        int i7 = this.n - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    public void close() throws IOException {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() throws IOException {
        int i2 = 0;
        do {
            int i3 = this.a;
            if (i3 == 0) {
                i3 = r();
            }
            if (i3 == 3) {
                a(1);
                i2++;
            } else if (i3 == 1) {
                a(3);
                i2++;
            } else if (i3 == 4) {
                this.n--;
                i2--;
            } else if (i3 == 2) {
                this.n--;
                i2--;
            } else if (i3 == 14 || i3 == 10) {
                v();
            } else if (i3 == 8 || i3 == 12) {
                c('\'');
            } else if (i3 == 9 || i3 == 13) {
                c('\"');
            } else if (i3 == 16) {
                this.f += this.k;
            }
            this.a = 0;
        } while (i2 != 0);
        int[] iArr = this.p;
        int i4 = this.n - 1;
        iArr[i4] = iArr[i4] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int i2) {
        if (this.n == this.m.length) {
            int[] iArr = new int[(this.n * 2)];
            int[] iArr2 = new int[(this.n * 2)];
            String[] strArr = new String[(this.n * 2)];
            System.arraycopy(this.m, 0, iArr, 0, this.n);
            System.arraycopy(this.p, 0, iArr2, 0, this.n);
            System.arraycopy(this.o, 0, strArr, 0, this.n);
            this.m = iArr;
            this.p = iArr2;
            this.o = strArr;
        }
        int[] iArr3 = this.m;
        int i3 = this.n;
        this.n = i3 + 1;
        iArr3[i3] = i2;
    }

    private boolean b(int i2) throws IOException {
        char[] cArr = this.e;
        this.i -= this.f;
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(cArr, this.f, cArr, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int read = this.c.read(cArr, this.g, cArr.length - this.g);
            if (read == -1) {
                return false;
            }
            this.g += read;
            if (this.h == 0 && this.i == 0 && this.g > 0 && cArr[0] == 65279) {
                this.f++;
                this.i++;
                i2++;
            }
        } while (this.g < i2);
        return true;
    }

    private int b(boolean z) throws IOException {
        char[] cArr = this.e;
        int i2 = this.f;
        int i3 = this.g;
        while (true) {
            if (i2 == i3) {
                this.f = i2;
                if (b(1)) {
                    i2 = this.f;
                    i3 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("End of input");
                    sb.append(s());
                    throw new EOFException(sb.toString());
                }
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == 10) {
                this.h++;
                this.i = i4;
            } else if (!(c2 == ' ' || c2 == 13 || c2 == 9)) {
                if (c2 == '/') {
                    this.f = i4;
                    if (i4 == i3) {
                        this.f--;
                        boolean b2 = b(2);
                        this.f++;
                        if (!b2) {
                            return c2;
                        }
                    }
                    w();
                    char c3 = cArr[this.f];
                    if (c3 == '*') {
                        this.f++;
                        if (!a("*/")) {
                            throw b("Unterminated comment");
                        }
                        i2 = this.f + 2;
                        i3 = this.g;
                    } else if (c3 != '/') {
                        return c2;
                    } else {
                        this.f++;
                        x();
                        i2 = this.f;
                        i3 = this.g;
                    }
                } else if (c2 == '#') {
                    this.f = i4;
                    w();
                    x();
                    i2 = this.f;
                    i3 = this.g;
                } else {
                    this.f = i4;
                    return c2;
                }
            }
            i2 = i4;
        }
    }

    private void w() throws IOException {
        if (!this.d) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void x() throws IOException {
        char c2;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i2 = this.f;
                this.f = i2 + 1;
                c2 = cArr[i2];
                if (c2 == 10) {
                    this.h++;
                    this.i = this.f;
                    return;
                }
            } else {
                return;
            }
        } while (c2 != 13);
    }

    private boolean a(String str) throws IOException {
        int length = str.length();
        while (true) {
            int i2 = 0;
            if (this.f + length > this.g && !b(length)) {
                return false;
            }
            if (this.e[this.f] == 10) {
                this.h++;
                this.i = this.f + 1;
            } else {
                while (i2 < length) {
                    if (this.e[this.f + i2] == str.charAt(i2)) {
                        i2++;
                    }
                }
                return true;
            }
            this.f++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(s());
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public String s() {
        int i2 = this.h + 1;
        int i3 = (this.f - this.i) + 1;
        StringBuilder sb = new StringBuilder();
        sb.append(" at line ");
        sb.append(i2);
        sb.append(" column ");
        sb.append(i3);
        sb.append(" path ");
        sb.append(p());
        return sb.toString();
    }

    public String p() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = this.n;
        for (int i3 = 0; i3 < i2; i3++) {
            switch (this.m[i3]) {
                case 1:
                case 2:
                    sb.append('[');
                    sb.append(this.p[i3]);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    if (this.o[i3] == null) {
                        break;
                    } else {
                        sb.append(this.o[i3]);
                        break;
                    }
            }
        }
        return sb.toString();
    }

    private char y() throws IOException {
        int i2;
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i3 = this.f;
            this.f = i3 + 1;
            char c2 = cArr[i3];
            if (c2 == 10) {
                this.h++;
                this.i = this.f;
            } else if (!(c2 == '\"' || c2 == '\'' || c2 == '/' || c2 == '\\')) {
                if (c2 == 'b') {
                    return 8;
                }
                if (c2 == 'f') {
                    return 12;
                }
                if (c2 == 'n') {
                    return 10;
                }
                if (c2 == 'r') {
                    return CharUtils.CR;
                }
                switch (c2) {
                    case 't':
                        return 9;
                    case 'u':
                        if (this.f + 4 <= this.g || b(4)) {
                            char c3 = 0;
                            int i4 = this.f;
                            int i5 = i4 + 4;
                            while (i4 < i5) {
                                char c4 = this.e[i4];
                                char c5 = (char) (c3 << 4);
                                if (c4 >= '0' && c4 <= '9') {
                                    i2 = c4 - '0';
                                } else if (c4 >= 'a' && c4 <= 'f') {
                                    i2 = (c4 - 'a') + 10;
                                } else if (c4 < 'A' || c4 > 'F') {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("\\u");
                                    sb.append(new String(this.e, this.f, 4));
                                    throw new NumberFormatException(sb.toString());
                                } else {
                                    i2 = (c4 - 'A') + 10;
                                }
                                c3 = (char) (c5 + i2);
                                i4++;
                            }
                            this.f += 4;
                            return c3;
                        }
                        throw b("Unterminated escape sequence");
                    default:
                        throw b("Invalid escape sequence");
                }
            }
            return c2;
        }
        throw b("Unterminated escape sequence");
    }

    private IOException b(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(s());
        throw new MalformedJsonException(sb.toString());
    }

    private void z() throws IOException {
        b(true);
        this.f--;
        if (this.f + b.length <= this.g || b(b.length)) {
            int i2 = 0;
            while (i2 < b.length) {
                if (this.e[this.f + i2] == b[i2]) {
                    i2++;
                } else {
                    return;
                }
            }
            this.f += b.length;
        }
    }
}
