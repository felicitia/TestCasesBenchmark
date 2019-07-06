package com.squareup.moshi;

import com.squareup.moshi.JsonReader.Token;
import com.squareup.moshi.JsonReader.a;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import okio.ByteString;
import okio.c;
import okio.e;
import org.apache.commons.lang3.CharUtils;

/* compiled from: JsonUtf8Reader */
final class h extends JsonReader {
    private static final ByteString g = ByteString.encodeUtf8("'\\");
    private static final ByteString h = ByteString.encodeUtf8("\"\\");
    private static final ByteString i = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    private static final ByteString j = ByteString.encodeUtf8("\n\r");
    private final e k;
    private final c l;
    private int m = 0;
    private long n;
    private int o;
    private String p;

    h(e eVar) {
        if (eVar == null) {
            throw new NullPointerException("source == null");
        }
        this.k = eVar;
        this.l = eVar.c();
        a(6);
    }

    public void c() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 3) {
            a(1);
            this.d[this.a - 1] = 0;
            this.m = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected BEGIN_ARRAY but was ");
        sb.append(h());
        sb.append(" at path ");
        sb.append(s());
        throw new JsonDataException(sb.toString());
    }

    public void d() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 4) {
            this.a--;
            int[] iArr = this.d;
            int i3 = this.a - 1;
            iArr[i3] = iArr[i3] + 1;
            this.m = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected END_ARRAY but was ");
        sb.append(h());
        sb.append(" at path ");
        sb.append(s());
        throw new JsonDataException(sb.toString());
    }

    public void e() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 1) {
            a(3);
            this.m = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected BEGIN_OBJECT but was ");
        sb.append(h());
        sb.append(" at path ");
        sb.append(s());
        throw new JsonDataException(sb.toString());
    }

    public void f() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 2) {
            this.a--;
            this.c[this.a] = null;
            int[] iArr = this.d;
            int i3 = this.a - 1;
            iArr[i3] = iArr[i3] + 1;
            this.m = 0;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected END_OBJECT but was ");
        sb.append(h());
        sb.append(" at path ");
        sb.append(s());
        throw new JsonDataException(sb.toString());
    }

    public boolean g() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        return (i2 == 2 || i2 == 4) ? false : true;
    }

    public Token h() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        switch (i2) {
            case 1:
                return Token.BEGIN_OBJECT;
            case 2:
                return Token.END_OBJECT;
            case 3:
                return Token.BEGIN_ARRAY;
            case 4:
                return Token.END_ARRAY;
            case 5:
            case 6:
                return Token.BOOLEAN;
            case 7:
                return Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return Token.NAME;
            case 16:
            case 17:
                return Token.NUMBER;
            case 18:
                return Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    private int u() throws IOException {
        int i2 = this.b[this.a - 1];
        if (i2 == 1) {
            this.b[this.a - 1] = 2;
        } else if (i2 == 2) {
            int c = c(true);
            this.l.i();
            if (c != 44) {
                if (c == 59) {
                    z();
                } else if (c != 93) {
                    throw a("Unterminated array");
                } else {
                    this.m = 4;
                    return 4;
                }
            }
        } else if (i2 == 3 || i2 == 5) {
            this.b[this.a - 1] = 4;
            if (i2 == 5) {
                int c2 = c(true);
                this.l.i();
                if (c2 != 44) {
                    if (c2 == 59) {
                        z();
                    } else if (c2 != 125) {
                        throw a("Unterminated object");
                    } else {
                        this.m = 2;
                        return 2;
                    }
                }
            }
            int c3 = c(true);
            if (c3 == 34) {
                this.l.i();
                this.m = 13;
                return 13;
            } else if (c3 == 39) {
                this.l.i();
                z();
                this.m = 12;
                return 12;
            } else if (c3 != 125) {
                z();
                if (b((int) (char) c3)) {
                    this.m = 14;
                    return 14;
                }
                throw a("Expected name");
            } else if (i2 != 5) {
                this.l.i();
                this.m = 2;
                return 2;
            } else {
                throw a("Expected name");
            }
        } else if (i2 == 4) {
            this.b[this.a - 1] = 5;
            int c4 = c(true);
            this.l.i();
            if (c4 != 58) {
                if (c4 != 61) {
                    throw a("Expected ':'");
                }
                z();
                if (this.k.b(1) && this.l.c(0) == 62) {
                    this.l.i();
                }
            }
        } else if (i2 == 6) {
            this.b[this.a - 1] = 7;
        } else if (i2 == 7) {
            if (c(false) == -1) {
                this.m = 18;
                return 18;
            }
            z();
        } else if (i2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int c5 = c(true);
        if (c5 == 34) {
            this.l.i();
            this.m = 9;
            return 9;
        } else if (c5 != 39) {
            if (!(c5 == 44 || c5 == 59)) {
                if (c5 == 91) {
                    this.l.i();
                    this.m = 3;
                    return 3;
                } else if (c5 != 93) {
                    if (c5 != 123) {
                        int v = v();
                        if (v != 0) {
                            return v;
                        }
                        int w = w();
                        if (w != 0) {
                            return w;
                        }
                        if (!b((int) this.l.c(0))) {
                            throw a("Expected value");
                        }
                        z();
                        this.m = 10;
                        return 10;
                    }
                    this.l.i();
                    this.m = 1;
                    return 1;
                } else if (i2 == 1) {
                    this.l.i();
                    this.m = 4;
                    return 4;
                }
            }
            if (i2 == 1 || i2 == 2) {
                z();
                this.m = 7;
                return 7;
            }
            throw a("Unexpected value");
        } else {
            z();
            this.l.i();
            this.m = 8;
            return 8;
        }
    }

    private int v() throws IOException {
        int i2;
        String str;
        String str2;
        byte c = this.l.c(0);
        if (c == 116 || c == 84) {
            str2 = "true";
            str = "TRUE";
            i2 = 5;
        } else if (c == 102 || c == 70) {
            str2 = "false";
            str = "FALSE";
            i2 = 6;
        } else if (c != 110 && c != 78) {
            return 0;
        } else {
            str2 = "null";
            str = "NULL";
            i2 = 7;
        }
        int length = str2.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.k.b((long) i4)) {
                return 0;
            }
            byte c2 = this.l.c((long) i3);
            if (c2 != str2.charAt(i3) && c2 != str.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.k.b((long) (length + 1)) && b((int) this.l.c((long) length))) {
            return 0;
        }
        this.l.i((long) length);
        this.m = i2;
        return i2;
    }

    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r6v11 */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: type inference failed for: r6v13 */
    /* JADX WARNING: type inference failed for: r6v14 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v2
      assigns: []
      uses: []
      mth insns count: 106
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int w() throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = 0
            r3 = 1
            r4 = 0
            r7 = r1
            r9 = r3
            r5 = r4
            r6 = r5
            r10 = r6
        L_0x000b:
            okio.e r11 = r0.k
            int r12 = r5 + 1
            long r13 = (long) r12
            boolean r11 = r11.b(r13)
            r15 = 2
            if (r11 != 0) goto L_0x0019
            goto L_0x0084
        L_0x0019:
            okio.c r11 = r0.l
            long r13 = (long) r5
            byte r11 = r11.c(r13)
            r13 = 43
            r14 = 5
            if (r11 == r13) goto L_0x00d3
            r13 = 69
            if (r11 == r13) goto L_0x00ca
            r13 = 101(0x65, float:1.42E-43)
            if (r11 == r13) goto L_0x00ca
            switch(r11) {
                case 45: goto L_0x00c0;
                case 46: goto L_0x00bb;
                default: goto L_0x0030;
            }
        L_0x0030:
            r13 = 48
            if (r11 < r13) goto L_0x007e
            r13 = 57
            if (r11 <= r13) goto L_0x0039
            goto L_0x007e
        L_0x0039:
            if (r6 == r3) goto L_0x0076
            if (r6 != 0) goto L_0x003e
            goto L_0x0076
        L_0x003e:
            if (r6 != r15) goto L_0x0066
            int r5 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r5 != 0) goto L_0x0045
            return r4
        L_0x0045:
            r13 = 10
            long r13 = r13 * r7
            int r11 = r11 + -48
            long r3 = (long) r11
            long r15 = r13 - r3
            r3 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0061
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x005f
            int r3 = (r15 > r7 ? 1 : (r15 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x005f
            goto L_0x0061
        L_0x005f:
            r3 = 0
            goto L_0x0062
        L_0x0061:
            r3 = 1
        L_0x0062:
            r3 = r3 & r9
            r9 = r3
            r7 = r15
            goto L_0x007c
        L_0x0066:
            r3 = 3
            if (r6 != r3) goto L_0x006d
            r4 = 0
            r6 = 4
            goto L_0x00d7
        L_0x006d:
            if (r6 == r14) goto L_0x0072
            r3 = 6
            if (r6 != r3) goto L_0x007c
        L_0x0072:
            r4 = 0
            r6 = 7
            goto L_0x00d7
        L_0x0076:
            int r11 = r11 + -48
            int r3 = -r11
            long r3 = (long) r3
            r7 = r3
            r6 = r15
        L_0x007c:
            r4 = 0
            goto L_0x00d7
        L_0x007e:
            boolean r3 = r0.b(r11)
            if (r3 != 0) goto L_0x00b9
        L_0x0084:
            if (r6 != r15) goto L_0x00a7
            if (r9 == 0) goto L_0x00a7
            r3 = -9223372036854775808
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 != 0) goto L_0x0090
            if (r10 == 0) goto L_0x00a7
        L_0x0090:
            int r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0096
            if (r10 != 0) goto L_0x00a7
        L_0x0096:
            if (r10 == 0) goto L_0x0099
            goto L_0x009a
        L_0x0099:
            long r7 = -r7
        L_0x009a:
            r0.n = r7
            okio.c r1 = r0.l
            long r2 = (long) r5
            r1.i(r2)
            r1 = 16
            r0.m = r1
            return r1
        L_0x00a7:
            if (r6 == r15) goto L_0x00b2
            r1 = 4
            if (r6 == r1) goto L_0x00b2
            r1 = 7
            if (r6 != r1) goto L_0x00b0
            goto L_0x00b2
        L_0x00b0:
            r4 = 0
            return r4
        L_0x00b2:
            r0.o = r5
            r1 = 17
            r0.m = r1
            return r1
        L_0x00b9:
            r4 = 0
            return r4
        L_0x00bb:
            r3 = 3
            if (r6 != r15) goto L_0x00bf
            goto L_0x00d6
        L_0x00bf:
            return r4
        L_0x00c0:
            r3 = 6
            if (r6 != 0) goto L_0x00c6
            r6 = 1
            r10 = 1
            goto L_0x00d7
        L_0x00c6:
            if (r6 != r14) goto L_0x00c9
            goto L_0x00d6
        L_0x00c9:
            return r4
        L_0x00ca:
            if (r6 == r15) goto L_0x00d1
            r3 = 4
            if (r6 != r3) goto L_0x00d0
            goto L_0x00d1
        L_0x00d0:
            return r4
        L_0x00d1:
            r6 = r14
            goto L_0x00d7
        L_0x00d3:
            r3 = 6
            if (r6 != r14) goto L_0x00db
        L_0x00d6:
            r6 = r3
        L_0x00d7:
            r5 = r12
            r3 = 1
            goto L_0x000b
        L_0x00db:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.h.w():int");
    }

    private boolean b(int i2) throws IOException {
        switch (i2) {
            case 9:
            case 10:
            case 12:
            case 13:
            case 32:
            case 44:
            case 58:
            case 91:
            case 93:
            case 123:
            case 125:
                break;
            case 35:
            case 47:
            case 59:
            case 61:
            case 92:
                z();
                break;
            default:
                return true;
        }
        return false;
    }

    public String i() throws IOException {
        String str;
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 14) {
            str = x();
        } else if (i2 == 13) {
            str = a(h);
        } else if (i2 == 12) {
            str = a(g);
        } else if (i2 == 15) {
            str = this.p;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a name but was ");
            sb.append(h());
            sb.append(" at path ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        this.m = 0;
        this.c[this.a - 1] = str;
        return str;
    }

    public int a(a aVar) throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 < 12 || i2 > 15) {
            return -1;
        }
        if (i2 == 15) {
            return a(this.p, aVar);
        }
        int a = this.k.a(aVar.b);
        if (a != -1) {
            this.m = 0;
            this.c[this.a - 1] = aVar.a[a];
            return a;
        }
        String str = this.c[this.a - 1];
        String i3 = i();
        int a2 = a(i3, aVar);
        if (a2 == -1) {
            this.m = 15;
            this.p = i3;
            this.c[this.a - 1] = str;
        }
        return a2;
    }

    public void j() throws IOException {
        if (this.f) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot skip unexpected ");
            sb.append(h());
            sb.append(" at ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 14) {
            y();
        } else if (i2 == 13) {
            b(h);
        } else if (i2 == 12) {
            b(g);
        } else if (i2 != 15) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected a name but was ");
            sb2.append(h());
            sb2.append(" at path ");
            sb2.append(s());
            throw new JsonDataException(sb2.toString());
        }
        this.m = 0;
        this.c[this.a - 1] = "null";
    }

    private int a(String str, a aVar) {
        int length = aVar.a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(aVar.a[i2])) {
                this.m = 0;
                this.c[this.a - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    public String k() throws IOException {
        String str;
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 10) {
            str = x();
        } else if (i2 == 9) {
            str = a(h);
        } else if (i2 == 8) {
            str = a(g);
        } else if (i2 == 11) {
            str = this.p;
            this.p = null;
        } else if (i2 == 16) {
            str = Long.toString(this.n);
        } else if (i2 == 17) {
            str = this.l.e((long) this.o);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a string but was ");
            sb.append(h());
            sb.append(" at path ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        this.m = 0;
        int[] iArr = this.d;
        int i3 = this.a - 1;
        iArr[i3] = iArr[i3] + 1;
        return str;
    }

    public int b(a aVar) throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 < 8 || i2 > 11) {
            return -1;
        }
        if (i2 == 11) {
            return b(this.p, aVar);
        }
        int a = this.k.a(aVar.b);
        if (a != -1) {
            this.m = 0;
            int[] iArr = this.d;
            int i3 = this.a - 1;
            iArr[i3] = iArr[i3] + 1;
            return a;
        }
        String k2 = k();
        int b = b(k2, aVar);
        if (b == -1) {
            this.m = 11;
            this.p = k2;
            int[] iArr2 = this.d;
            int i4 = this.a - 1;
            iArr2[i4] = iArr2[i4] - 1;
        }
        return b;
    }

    private int b(String str, a aVar) {
        int length = aVar.a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(aVar.a[i2])) {
                this.m = 0;
                int[] iArr = this.d;
                int i3 = this.a - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
        }
        return -1;
    }

    public boolean l() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 5) {
            this.m = 0;
            int[] iArr = this.d;
            int i3 = this.a - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.m = 0;
            int[] iArr2 = this.d;
            int i4 = this.a - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a boolean but was ");
            sb.append(h());
            sb.append(" at path ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
    }

    public <T> T m() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 7) {
            this.m = 0;
            int[] iArr = this.d;
            int i3 = this.a - 1;
            iArr[i3] = iArr[i3] + 1;
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected null but was ");
        sb.append(h());
        sb.append(" at path ");
        sb.append(s());
        throw new JsonDataException(sb.toString());
    }

    public double n() throws IOException {
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 16) {
            this.m = 0;
            int[] iArr = this.d;
            int i3 = this.a - 1;
            iArr[i3] = iArr[i3] + 1;
            return (double) this.n;
        }
        if (i2 == 17) {
            this.p = this.l.e((long) this.o);
        } else if (i2 == 9) {
            this.p = a(h);
        } else if (i2 == 8) {
            this.p = a(g);
        } else if (i2 == 10) {
            this.p = x();
        } else if (i2 != 11) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a double but was ");
            sb.append(h());
            sb.append(" at path ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        this.m = 11;
        try {
            double parseDouble = Double.parseDouble(this.p);
            if (this.e || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
                this.p = null;
                this.m = 0;
                int[] iArr2 = this.d;
                int i4 = this.a - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseDouble;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("JSON forbids NaN and infinities: ");
            sb2.append(parseDouble);
            sb2.append(" at path ");
            sb2.append(s());
            throw new JsonEncodingException(sb2.toString());
        } catch (NumberFormatException unused) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Expected a double but was ");
            sb3.append(this.p);
            sb3.append(" at path ");
            sb3.append(s());
            throw new JsonDataException(sb3.toString());
        }
    }

    public long o() throws IOException {
        String str;
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 16) {
            this.m = 0;
            int[] iArr = this.d;
            int i3 = this.a - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.n;
        }
        if (i2 == 17) {
            this.p = this.l.e((long) this.o);
        } else if (i2 == 9 || i2 == 8) {
            if (i2 == 9) {
                str = a(h);
            } else {
                str = a(g);
            }
            this.p = str;
            try {
                long parseLong = Long.parseLong(this.p);
                this.m = 0;
                int[] iArr2 = this.d;
                int i4 = this.a - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else if (i2 != 11) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected a long but was ");
            sb.append(h());
            sb.append(" at path ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        this.m = 11;
        try {
            long longValueExact = new BigDecimal(this.p).longValueExact();
            this.p = null;
            this.m = 0;
            int[] iArr3 = this.d;
            int i5 = this.a - 1;
            iArr3[i5] = iArr3[i5] + 1;
            return longValueExact;
        } catch (ArithmeticException | NumberFormatException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected a long but was ");
            sb2.append(this.p);
            sb2.append(" at path ");
            sb2.append(s());
            throw new JsonDataException(sb2.toString());
        }
    }

    private String a(ByteString byteString) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long b = this.k.b(byteString);
            if (b == -1) {
                throw a("Unterminated string");
            } else if (this.l.c(b) == 92) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.l.e(b));
                this.l.i();
                sb.append(B());
            } else if (sb == null) {
                String e = this.l.e(b);
                this.l.i();
                return e;
            } else {
                sb.append(this.l.e(b));
                this.l.i();
                return sb.toString();
            }
        }
    }

    private String x() throws IOException {
        long b = this.k.b(i);
        return b != -1 ? this.l.e(b) : this.l.p();
    }

    private void b(ByteString byteString) throws IOException {
        while (true) {
            long b = this.k.b(byteString);
            if (b == -1) {
                throw a("Unterminated string");
            } else if (this.l.c(b) == 92) {
                this.l.i(b + 1);
                B();
            } else {
                this.l.i(b + 1);
                return;
            }
        }
    }

    private void y() throws IOException {
        long b = this.k.b(i);
        c cVar = this.l;
        if (b == -1) {
            b = this.l.b();
        }
        cVar.i(b);
    }

    public int p() throws IOException {
        String str;
        int i2 = this.m;
        if (i2 == 0) {
            i2 = u();
        }
        if (i2 == 16) {
            int i3 = (int) this.n;
            if (this.n != ((long) i3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Expected an int but was ");
                sb.append(this.n);
                sb.append(" at path ");
                sb.append(s());
                throw new JsonDataException(sb.toString());
            }
            this.m = 0;
            int[] iArr = this.d;
            int i4 = this.a - 1;
            iArr[i4] = iArr[i4] + 1;
            return i3;
        }
        if (i2 == 17) {
            this.p = this.l.e((long) this.o);
        } else if (i2 == 9 || i2 == 8) {
            if (i2 == 9) {
                str = a(h);
            } else {
                str = a(g);
            }
            this.p = str;
            try {
                int parseInt = Integer.parseInt(this.p);
                this.m = 0;
                int[] iArr2 = this.d;
                int i5 = this.a - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (i2 != 11) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected an int but was ");
            sb2.append(h());
            sb2.append(" at path ");
            sb2.append(s());
            throw new JsonDataException(sb2.toString());
        }
        this.m = 11;
        try {
            double parseDouble = Double.parseDouble(this.p);
            int i6 = (int) parseDouble;
            if (((double) i6) != parseDouble) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Expected an int but was ");
                sb3.append(this.p);
                sb3.append(" at path ");
                sb3.append(s());
                throw new JsonDataException(sb3.toString());
            }
            this.p = null;
            this.m = 0;
            int[] iArr3 = this.d;
            int i7 = this.a - 1;
            iArr3[i7] = iArr3[i7] + 1;
            return i6;
        } catch (NumberFormatException unused2) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Expected an int but was ");
            sb4.append(this.p);
            sb4.append(" at path ");
            sb4.append(s());
            throw new JsonDataException(sb4.toString());
        }
    }

    public void close() throws IOException {
        this.m = 0;
        this.b[0] = 8;
        this.a = 1;
        this.l.t();
        this.k.close();
    }

    public void q() throws IOException {
        if (this.f) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot skip unexpected ");
            sb.append(h());
            sb.append(" at ");
            sb.append(s());
            throw new JsonDataException(sb.toString());
        }
        int i2 = 0;
        do {
            int i3 = this.m;
            if (i3 == 0) {
                i3 = u();
            }
            if (i3 == 3) {
                a(1);
                i2++;
            } else if (i3 == 1) {
                a(3);
                i2++;
            } else if (i3 == 4) {
                this.a--;
                i2--;
            } else if (i3 == 2) {
                this.a--;
                i2--;
            } else if (i3 == 14 || i3 == 10) {
                y();
            } else if (i3 == 9 || i3 == 13) {
                b(h);
            } else if (i3 == 8 || i3 == 12) {
                b(g);
            } else if (i3 == 17) {
                this.l.i((long) this.o);
            }
            this.m = 0;
        } while (i2 != 0);
        int[] iArr = this.d;
        int i4 = this.a - 1;
        iArr[i4] = iArr[i4] + 1;
        this.c[this.a - 1] = "null";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r6.l.i((long) (r3 - 1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r1 != 47) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        if (r6.k.b(2) != false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        z();
        r3 = r6.l.c(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        if (r3 == 42) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        if (r3 == 47) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        r6.l.i();
        r6.l.i();
        A();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        r6.l.i();
        r6.l.i();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        if (b("*/") != false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0074, code lost:
        throw a("Unterminated comment");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0075, code lost:
        r6.l.i();
        r6.l.i();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0082, code lost:
        if (r1 != 35) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0084, code lost:
        z();
        A();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008c, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int c(boolean r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
        L_0x0001:
            r1 = r0
        L_0x0002:
            okio.e r2 = r6.k
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.b(r4)
            if (r2 == 0) goto L_0x0090
            okio.c r2 = r6.l
            long r4 = (long) r1
            byte r1 = r2.c(r4)
            r2 = 10
            if (r1 == r2) goto L_0x008d
            r2 = 32
            if (r1 == r2) goto L_0x008d
            r2 = 13
            if (r1 == r2) goto L_0x008d
            r2 = 9
            if (r1 != r2) goto L_0x0025
            goto L_0x008d
        L_0x0025:
            okio.c r2 = r6.l
            int r3 = r3 + -1
            long r3 = (long) r3
            r2.i(r3)
            r2 = 47
            if (r1 != r2) goto L_0x0080
            okio.e r3 = r6.k
            r4 = 2
            boolean r3 = r3.b(r4)
            if (r3 != 0) goto L_0x003c
            return r1
        L_0x003c:
            r6.z()
            okio.c r3 = r6.l
            r4 = 1
            byte r3 = r3.c(r4)
            r4 = 42
            if (r3 == r4) goto L_0x005c
            if (r3 == r2) goto L_0x004e
            return r1
        L_0x004e:
            okio.c r1 = r6.l
            r1.i()
            okio.c r1 = r6.l
            r1.i()
            r6.A()
            goto L_0x0001
        L_0x005c:
            okio.c r1 = r6.l
            r1.i()
            okio.c r1 = r6.l
            r1.i()
            java.lang.String r1 = "*/"
            boolean r1 = r6.b(r1)
            if (r1 != 0) goto L_0x0075
            java.lang.String r7 = "Unterminated comment"
            com.squareup.moshi.JsonEncodingException r7 = r6.a(r7)
            throw r7
        L_0x0075:
            okio.c r1 = r6.l
            r1.i()
            okio.c r1 = r6.l
            r1.i()
            goto L_0x0001
        L_0x0080:
            r2 = 35
            if (r1 != r2) goto L_0x008c
            r6.z()
            r6.A()
            goto L_0x0001
        L_0x008c:
            return r1
        L_0x008d:
            r1 = r3
            goto L_0x0002
        L_0x0090:
            if (r7 == 0) goto L_0x009a
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r7.<init>(r0)
            throw r7
        L_0x009a:
            r7 = -1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.h.c(boolean):int");
    }

    private void z() throws IOException {
        if (!this.e) {
            throw a("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void A() throws IOException {
        long b = this.k.b(j);
        this.l.i(b != -1 ? b + 1 : this.l.b());
    }

    private boolean b(String str) throws IOException {
        while (true) {
            int i2 = 0;
            if (!this.k.b((long) str.length())) {
                return false;
            }
            while (i2 < str.length()) {
                if (this.l.c((long) i2) != str.charAt(i2)) {
                    this.l.i();
                } else {
                    i2++;
                }
            }
            return true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JsonReader(");
        sb.append(this.k);
        sb.append(")");
        return sb.toString();
    }

    private char B() throws IOException {
        int i2;
        if (!this.k.b(1)) {
            throw a("Unterminated escape sequence");
        }
        byte i3 = this.l.i();
        if (i3 == 10 || i3 == 34 || i3 == 39 || i3 == 47 || i3 == 92) {
            return (char) i3;
        }
        if (i3 == 98) {
            return 8;
        }
        if (i3 == 102) {
            return 12;
        }
        if (i3 == 110) {
            return 10;
        }
        if (i3 == 114) {
            return CharUtils.CR;
        }
        switch (i3) {
            case 116:
                return 9;
            case 117:
                if (!this.k.b(4)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unterminated escape sequence at path ");
                    sb.append(s());
                    throw new EOFException(sb.toString());
                }
                char c = 0;
                for (int i4 = 0; i4 < 4; i4++) {
                    byte c2 = this.l.c((long) i4);
                    char c3 = (char) (c << 4);
                    if (c2 >= 48 && c2 <= 57) {
                        i2 = c2 - 48;
                    } else if (c2 >= 97 && c2 <= 102) {
                        i2 = (c2 - 97) + 10;
                    } else if (c2 < 65 || c2 > 70) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("\\u");
                        sb2.append(this.l.e(4));
                        throw a(sb2.toString());
                    } else {
                        i2 = (c2 - 65) + 10;
                    }
                    c = (char) (c3 + i2);
                }
                this.l.i(4);
                return c;
            default:
                if (this.e) {
                    return (char) i3;
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Invalid escape sequence: \\");
                sb3.append((char) i3);
                throw a(sb3.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void t() throws IOException {
        if (g()) {
            this.p = i();
            this.m = 11;
        }
    }
}
