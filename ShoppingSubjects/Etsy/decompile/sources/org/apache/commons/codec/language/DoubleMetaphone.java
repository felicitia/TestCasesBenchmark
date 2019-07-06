package org.apache.commons.codec.language;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class DoubleMetaphone implements StringEncoder {
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER = {"ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER"};
    private static final String[] L_R_N_M_B_H_F_V_W_SPACE = {"L", "R", "N", "M", "B", "H", "F", "V", "W", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR};
    private static final String[] L_T_K_S_N_M_B_Z = {"L", "T", "K", "S", "N", "M", "B", "Z"};
    private static final String[] SILENT_START = {"GN", "KN", "PN", "WR", "PS"};
    private static final String VOWELS = "AEIOUY";
    private int maxCodeLen = 4;

    public class DoubleMetaphoneResult {
        private final StringBuffer alternate = new StringBuffer(DoubleMetaphone.this.getMaxCodeLen());
        private final int maxLength;
        private final StringBuffer primary = new StringBuffer(DoubleMetaphone.this.getMaxCodeLen());

        public DoubleMetaphoneResult(int i) {
            this.maxLength = i;
        }

        public void append(char c) {
            appendPrimary(c);
            appendAlternate(c);
        }

        public void append(char c, char c2) {
            appendPrimary(c);
            appendAlternate(c2);
        }

        public void appendPrimary(char c) {
            if (this.primary.length() < this.maxLength) {
                this.primary.append(c);
            }
        }

        public void appendAlternate(char c) {
            if (this.alternate.length() < this.maxLength) {
                this.alternate.append(c);
            }
        }

        public void append(String str) {
            appendPrimary(str);
            appendAlternate(str);
        }

        public void append(String str, String str2) {
            appendPrimary(str);
            appendAlternate(str2);
        }

        public void appendPrimary(String str) {
            int length = this.maxLength - this.primary.length();
            if (str.length() <= length) {
                this.primary.append(str);
            } else {
                this.primary.append(str.substring(0, length));
            }
        }

        public void appendAlternate(String str) {
            int length = this.maxLength - this.alternate.length();
            if (str.length() <= length) {
                this.alternate.append(str);
            } else {
                this.alternate.append(str.substring(0, length));
            }
        }

        public String getPrimary() {
            return this.primary.toString();
        }

        public String getAlternate() {
            return this.alternate.toString();
        }

        public boolean isComplete() {
            return this.primary.length() >= this.maxLength && this.alternate.length() >= this.maxLength;
        }
    }

    public String doubleMetaphone(String str) {
        return doubleMetaphone(str, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
        r1 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String doubleMetaphone(java.lang.String r8, boolean r9) {
        /*
            r7 = this;
            java.lang.String r8 = r7.cleanInput(r8)
            if (r8 != 0) goto L_0x0008
            r8 = 0
            return r8
        L_0x0008:
            boolean r0 = r7.isSlavoGermanic(r8)
            boolean r1 = r7.isSilentStart(r8)
            org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult r2 = new org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult
            int r3 = r7.getMaxCodeLen()
            r2.<init>(r3)
        L_0x0019:
            boolean r3 = r2.isComplete()
            if (r3 != 0) goto L_0x0110
            int r3 = r8.length()
            int r3 = r3 + -1
            if (r1 > r3) goto L_0x0110
            char r3 = r8.charAt(r1)
            r4 = 199(0xc7, float:2.79E-43)
            if (r3 == r4) goto L_0x0107
            r4 = 209(0xd1, float:2.93E-43)
            r5 = 78
            if (r3 == r4) goto L_0x0100
            r4 = 75
            r6 = 70
            switch(r3) {
                case 65: goto L_0x00fa;
                case 66: goto L_0x00e7;
                case 67: goto L_0x00e1;
                case 68: goto L_0x00db;
                case 69: goto L_0x00fa;
                case 70: goto L_0x00cc;
                case 71: goto L_0x00c6;
                case 72: goto L_0x00c0;
                case 73: goto L_0x00fa;
                case 74: goto L_0x00ba;
                case 75: goto L_0x00ab;
                case 76: goto L_0x00a5;
                case 77: goto L_0x0092;
                case 78: goto L_0x0084;
                case 79: goto L_0x00fa;
                case 80: goto L_0x007f;
                case 81: goto L_0x006f;
                case 82: goto L_0x006a;
                case 83: goto L_0x0065;
                case 84: goto L_0x0060;
                case 85: goto L_0x00fa;
                case 86: goto L_0x004e;
                case 87: goto L_0x0049;
                case 88: goto L_0x0044;
                case 89: goto L_0x00fa;
                case 90: goto L_0x003f;
                default: goto L_0x003c;
            }
        L_0x003c:
            int r1 = r1 + 1
            goto L_0x0019
        L_0x003f:
            int r1 = r7.handleZ(r8, r2, r1, r0)
            goto L_0x0019
        L_0x0044:
            int r1 = r7.handleX(r8, r2, r1)
            goto L_0x0019
        L_0x0049:
            int r1 = r7.handleW(r8, r2, r1)
            goto L_0x0019
        L_0x004e:
            r2.append(r6)
            int r3 = r1 + 1
            char r4 = r7.charAt(r8, r3)
            r5 = 86
            if (r4 != r5) goto L_0x005e
            int r1 = r1 + 2
            goto L_0x0019
        L_0x005e:
            r1 = r3
            goto L_0x0019
        L_0x0060:
            int r1 = r7.handleT(r8, r2, r1)
            goto L_0x0019
        L_0x0065:
            int r1 = r7.handleS(r8, r2, r1, r0)
            goto L_0x0019
        L_0x006a:
            int r1 = r7.handleR(r8, r2, r1, r0)
            goto L_0x0019
        L_0x006f:
            r2.append(r4)
            int r3 = r1 + 1
            char r4 = r7.charAt(r8, r3)
            r5 = 81
            if (r4 != r5) goto L_0x005e
            int r1 = r1 + 2
            goto L_0x0019
        L_0x007f:
            int r1 = r7.handleP(r8, r2, r1)
            goto L_0x0019
        L_0x0084:
            r2.append(r5)
            int r3 = r1 + 1
            char r4 = r7.charAt(r8, r3)
            if (r4 != r5) goto L_0x005e
            int r1 = r1 + 2
            goto L_0x0019
        L_0x0092:
            r3 = 77
            r2.append(r3)
            boolean r3 = r7.conditionM0(r8, r1)
            if (r3 == 0) goto L_0x00a1
            int r1 = r1 + 2
            goto L_0x0019
        L_0x00a1:
            int r1 = r1 + 1
            goto L_0x0019
        L_0x00a5:
            int r1 = r7.handleL(r8, r2, r1)
            goto L_0x0019
        L_0x00ab:
            r2.append(r4)
            int r3 = r1 + 1
            char r5 = r7.charAt(r8, r3)
            if (r5 != r4) goto L_0x005e
            int r1 = r1 + 2
            goto L_0x0019
        L_0x00ba:
            int r1 = r7.handleJ(r8, r2, r1, r0)
            goto L_0x0019
        L_0x00c0:
            int r1 = r7.handleH(r8, r2, r1)
            goto L_0x0019
        L_0x00c6:
            int r1 = r7.handleG(r8, r2, r1, r0)
            goto L_0x0019
        L_0x00cc:
            r2.append(r6)
            int r3 = r1 + 1
            char r4 = r7.charAt(r8, r3)
            if (r4 != r6) goto L_0x005e
            int r1 = r1 + 2
            goto L_0x0019
        L_0x00db:
            int r1 = r7.handleD(r8, r2, r1)
            goto L_0x0019
        L_0x00e1:
            int r1 = r7.handleC(r8, r2, r1)
            goto L_0x0019
        L_0x00e7:
            r3 = 80
            r2.append(r3)
            int r3 = r1 + 1
            char r4 = r7.charAt(r8, r3)
            r5 = 66
            if (r4 != r5) goto L_0x005e
            int r1 = r1 + 2
            goto L_0x0019
        L_0x00fa:
            int r1 = r7.handleAEIOUY(r2, r1)
            goto L_0x0019
        L_0x0100:
            r2.append(r5)
            int r1 = r1 + 1
            goto L_0x0019
        L_0x0107:
            r3 = 83
            r2.append(r3)
            int r1 = r1 + 1
            goto L_0x0019
        L_0x0110:
            if (r9 == 0) goto L_0x0117
            java.lang.String r8 = r2.getAlternate()
            goto L_0x011b
        L_0x0117:
            java.lang.String r8 = r2.getPrimary()
        L_0x011b:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.DoubleMetaphone.doubleMetaphone(java.lang.String, boolean):java.lang.String");
    }

    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return doubleMetaphone((String) obj);
        }
        throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
    }

    public String encode(String str) {
        return doubleMetaphone(str);
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2) {
        return isDoubleMetaphoneEqual(str, str2, false);
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2, boolean z) {
        return doubleMetaphone(str, z).equals(doubleMetaphone(str2, z));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int i) {
        this.maxCodeLen = i;
    }

    private int handleAEIOUY(DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i == 0) {
            doubleMetaphoneResult.append('A');
        }
        return i + 1;
    }

    private int handleC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2;
        String str2 = str;
        DoubleMetaphoneResult doubleMetaphoneResult2 = doubleMetaphoneResult;
        int i3 = i;
        if (conditionC0(str2, i3)) {
            doubleMetaphoneResult2.append('K');
            i2 = i3 + 2;
        } else if (i3 == 0 && contains(str2, i3, 6, "CAESAR")) {
            doubleMetaphoneResult2.append('S');
            i2 = i3 + 2;
        } else if (contains(str2, i3, 2, "CH")) {
            i2 = handleCH(str, doubleMetaphoneResult, i);
        } else if (!contains(str2, i3, 2, "CZ") || contains(str2, i3 - 2, 4, "WICZ")) {
            int i4 = i3 + 1;
            if (contains(str2, i4, 3, "CIA")) {
                doubleMetaphoneResult2.append('X');
                i2 = i3 + 3;
            } else if (contains(str2, i3, 2, "CC") && (i3 != 1 || charAt(str2, 0) != 'M')) {
                return handleCC(str, doubleMetaphoneResult, i);
            } else {
                if (contains(str2, i3, 2, "CK", "CG", "CQ")) {
                    doubleMetaphoneResult2.append('K');
                    i2 = i3 + 2;
                } else {
                    if (contains(str2, i3, 2, "CI", "CE", "CY")) {
                        if (contains(str2, i3, 3, "CIO", "CIE", "CIA")) {
                            doubleMetaphoneResult2.append('S', 'X');
                        } else {
                            doubleMetaphoneResult2.append('S');
                        }
                        i2 = i3 + 2;
                    } else {
                        doubleMetaphoneResult2.append('K');
                        if (contains(str2, i4, 2, " C", " Q", " G")) {
                            i2 = i3 + 3;
                        } else {
                            i2 = (!contains(str2, i4, 1, "C", "K", "Q") || contains(str2, i4, 2, "CE", "CI")) ? i4 : i3 + 2;
                        }
                    }
                }
            }
        } else {
            doubleMetaphoneResult2.append('S', 'X');
            i2 = i3 + 2;
        }
        return i2;
    }

    private int handleCC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = i + 2;
        if (!contains(str, i2, 1, "I", "E", "H") || contains(str, i2, 2, "HU")) {
            doubleMetaphoneResult.append('K');
            return i2;
        }
        if (!(i == 1 && charAt(str, i - 1) == 'A') && !contains(str, i - 1, 5, "UCCEE", "UCCES")) {
            doubleMetaphoneResult.append('X');
        } else {
            doubleMetaphoneResult.append("KS");
        }
        return i + 3;
    }

    private int handleCH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i > 0 && contains(str, i, 4, "CHAE")) {
            doubleMetaphoneResult.append('K', 'X');
            return i + 2;
        } else if (conditionCH0(str, i)) {
            doubleMetaphoneResult.append('K');
            return i + 2;
        } else if (conditionCH1(str, i)) {
            doubleMetaphoneResult.append('K');
            return i + 2;
        } else {
            if (i <= 0) {
                doubleMetaphoneResult.append('X');
            } else if (contains(str, 0, 2, "MC")) {
                doubleMetaphoneResult.append('K');
            } else {
                doubleMetaphoneResult.append('X', 'K');
            }
            return i + 2;
        }
    }

    private int handleD(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 2, "DG")) {
            int i2 = i + 2;
            if (contains(str, i2, 1, "I", "E", "Y")) {
                doubleMetaphoneResult.append('J');
                return i + 3;
            }
            doubleMetaphoneResult.append("TK");
            return i2;
        } else if (contains(str, i, 2, "DT", "DD")) {
            doubleMetaphoneResult.append('T');
            return i + 2;
        } else {
            doubleMetaphoneResult.append('T');
            return i + 1;
        }
    }

    private int handleG(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        int i2;
        String str2 = str;
        DoubleMetaphoneResult doubleMetaphoneResult2 = doubleMetaphoneResult;
        int i3 = i;
        int i4 = i3 + 1;
        if (charAt(str2, i4) == 'H') {
            return handleGH(str, doubleMetaphoneResult, i);
        }
        if (charAt(str2, i4) == 'N') {
            if (i3 == 1 && isVowel(charAt(str2, 0)) && !z) {
                doubleMetaphoneResult2.append("KN", "N");
            } else if (contains(str2, i3 + 2, 2, "EY") || charAt(str2, i4) == 'Y' || z) {
                doubleMetaphoneResult2.append("KN");
            } else {
                doubleMetaphoneResult2.append("N", "KN");
            }
            return i3 + 2;
        } else if (contains(str2, i4, 2, "LI") && !z) {
            doubleMetaphoneResult2.append("KL", "L");
            return i3 + 2;
        } else if (i3 != 0 || (charAt(str2, i4) != 'Y' && !contains(str2, i4, 2, ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER))) {
            if (contains(str2, i4, 2, "ER") || charAt(str2, i4) == 'Y') {
                i2 = 3;
                if (!contains(str2, 0, 6, "DANGER", "RANGER", "MANGER")) {
                    int i5 = i3 - 1;
                    if (!contains(str2, i5, 1, "E", "I") && !contains(str2, i5, 3, "RGY", "OGY")) {
                        doubleMetaphoneResult2.append('K', 'J');
                        return i3 + 2;
                    }
                }
            } else {
                i2 = 3;
            }
            if (contains(str2, i4, 1, "E", "I", "Y") || contains(str2, i3 - 1, 4, "AGGI", "OGGI")) {
                if (contains(str2, 0, 4, "VAN ", "VON ") || contains(str2, 0, i2, "SCH") || contains(str2, i4, 2, "ET")) {
                    doubleMetaphoneResult2.append('K');
                } else if (contains(str2, i4, i2, "IER")) {
                    doubleMetaphoneResult2.append('J');
                } else {
                    doubleMetaphoneResult2.append('J', 'K');
                }
                return i3 + 2;
            } else if (charAt(str2, i4) == 'G') {
                int i6 = i3 + 2;
                doubleMetaphoneResult2.append('K');
                return i6;
            } else {
                doubleMetaphoneResult2.append('K');
                return i4;
            }
        } else {
            doubleMetaphoneResult2.append('K', 'J');
            return i3 + 2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        if (contains(r9, r11 - 2, 1, "B", "H", "D") == false) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
        if (contains(r9, r11 - 3, 1, "B", "H", "D") == false) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int handleGH(java.lang.String r16, org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult r17, int r18) {
        /*
            r15 = this;
            r0 = r15
            r9 = r16
            r10 = r17
            r11 = r18
            r12 = 75
            r13 = 2
            if (r11 <= 0) goto L_0x001f
            int r1 = r11 + -1
            char r1 = r0.charAt(r9, r1)
            boolean r1 = r0.isVowel(r1)
            if (r1 != 0) goto L_0x001f
            r10.append(r12)
            int r1 = r11 + 2
            goto L_0x00a3
        L_0x001f:
            r14 = 73
            if (r11 != 0) goto L_0x0037
            int r1 = r11 + 2
            char r2 = r0.charAt(r9, r1)
            if (r2 != r14) goto L_0x0032
            r2 = 74
            r10.append(r2)
            goto L_0x00a3
        L_0x0032:
            r10.append(r12)
            goto L_0x00a3
        L_0x0037:
            r7 = 1
            if (r11 <= r7) goto L_0x004a
            int r2 = r11 + -2
            r3 = 1
            java.lang.String r4 = "B"
            java.lang.String r5 = "H"
            java.lang.String r6 = "D"
            r1 = r9
            boolean r1 = contains(r1, r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x006b
        L_0x004a:
            if (r11 <= r13) goto L_0x005c
            int r2 = r11 + -3
            r3 = 1
            java.lang.String r4 = "B"
            java.lang.String r5 = "H"
            java.lang.String r6 = "D"
            r1 = r9
            boolean r1 = contains(r1, r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x006b
        L_0x005c:
            r1 = 3
            if (r11 <= r1) goto L_0x006e
            int r1 = r11 + -4
            java.lang.String r2 = "B"
            java.lang.String r3 = "H"
            boolean r1 = contains(r9, r1, r7, r2, r3)
            if (r1 == 0) goto L_0x006e
        L_0x006b:
            int r1 = r11 + 2
            goto L_0x00a3
        L_0x006e:
            if (r11 <= r13) goto L_0x0094
            int r1 = r11 + -1
            char r1 = r0.charAt(r9, r1)
            r2 = 85
            if (r1 != r2) goto L_0x0094
            int r2 = r11 + -3
            r3 = 1
            java.lang.String r4 = "C"
            java.lang.String r5 = "G"
            java.lang.String r6 = "L"
            java.lang.String r7 = "R"
            java.lang.String r8 = "T"
            r1 = r9
            boolean r1 = contains(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r1 == 0) goto L_0x0094
            r1 = 70
            r10.append(r1)
            goto L_0x00a1
        L_0x0094:
            if (r11 <= 0) goto L_0x00a1
            int r1 = r11 + -1
            char r1 = r0.charAt(r9, r1)
            if (r1 == r14) goto L_0x00a1
            r10.append(r12)
        L_0x00a1:
            int r1 = r11 + 2
        L_0x00a3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.DoubleMetaphone.handleGH(java.lang.String, org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult, int):int");
    }

    private int handleH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if ((i != 0 && !isVowel(charAt(str, i - 1))) || !isVowel(charAt(str, i + 1))) {
            return i + 1;
        }
        doubleMetaphoneResult.append('H');
        return i + 2;
    }

    private int handleJ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        String str2 = str;
        DoubleMetaphoneResult doubleMetaphoneResult2 = doubleMetaphoneResult;
        int i2 = i;
        if (contains(str2, i2, 4, "JOSE") || contains(str2, 0, 4, "SAN ")) {
            if ((i2 == 0 && charAt(str2, i2 + 4) == ' ') || str2.length() == 4 || contains(str2, 0, 4, "SAN ")) {
                doubleMetaphoneResult2.append('H');
            } else {
                doubleMetaphoneResult2.append('J', 'H');
            }
            return i2 + 1;
        }
        if (i2 != 0 || contains(str2, i2, 4, "JOSE")) {
            int i3 = i2 - 1;
            if (isVowel(charAt(str2, i3)) && !z) {
                int i4 = i2 + 1;
                if (charAt(str2, i4) == 'A' || charAt(str2, i4) == 'O') {
                    doubleMetaphoneResult2.append('J', 'H');
                }
            }
            if (i2 == str2.length() - 1) {
                doubleMetaphoneResult2.append('J', ' ');
            } else if (!contains(str2, i2 + 1, 1, L_T_K_S_N_M_B_Z)) {
                if (!contains(str2, i3, 1, "S", "K", "L")) {
                    doubleMetaphoneResult2.append('J');
                }
            }
        } else {
            doubleMetaphoneResult2.append('J', 'A');
        }
        int i5 = i2 + 1;
        if (charAt(str2, i5) == 'J') {
            return i2 + 2;
        }
        return i5;
    }

    private int handleL(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'L') {
            if (conditionL0(str, i)) {
                doubleMetaphoneResult.appendPrimary('L');
            } else {
                doubleMetaphoneResult.append('L');
            }
            return i + 2;
        }
        doubleMetaphoneResult.append('L');
        return i2;
    }

    private int handleP(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'H') {
            doubleMetaphoneResult.append('F');
            return i + 2;
        }
        doubleMetaphoneResult.append('P');
        if (contains(str, i2, 1, "P", "B")) {
            i2 = i + 2;
        }
        return i2;
    }

    private int handleR(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (i != str.length() - 1 || z || !contains(str, i - 2, 2, "IE") || contains(str, i - 4, 2, "ME", "MA")) {
            doubleMetaphoneResult.append('R');
        } else {
            doubleMetaphoneResult.appendAlternate('R');
        }
        int i2 = i + 1;
        return charAt(str, i2) == 'R' ? i + 2 : i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0079, code lost:
        if (contains(r7, r9 + 1, 1, "M", "N", "L", "W") == false) goto L_0x007b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int handleS(java.lang.String r15, org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult r16, int r17, boolean r18) {
        /*
            r14 = this;
            r7 = r15
            r8 = r16
            r9 = r17
            int r0 = r9 + -1
            java.lang.String r1 = "ISL"
            java.lang.String r2 = "YSL"
            r3 = 3
            boolean r0 = contains(r7, r0, r3, r1, r2)
            r10 = 1
            if (r0 == 0) goto L_0x0017
            int r0 = r9 + 1
            goto L_0x00d4
        L_0x0017:
            r11 = 88
            r12 = 83
            if (r9 != 0) goto L_0x002d
            r0 = 5
            java.lang.String r1 = "SUGAR"
            boolean r0 = contains(r7, r9, r0, r1)
            if (r0 == 0) goto L_0x002d
            r8.append(r11, r12)
            int r0 = r9 + 1
            goto L_0x00d4
        L_0x002d:
            java.lang.String r0 = "SH"
            r13 = 2
            boolean r0 = contains(r7, r9, r13, r0)
            if (r0 == 0) goto L_0x0053
            int r1 = r9 + 1
            r2 = 4
            java.lang.String r3 = "HEIM"
            java.lang.String r4 = "HOEK"
            java.lang.String r5 = "HOLM"
            java.lang.String r6 = "HOLZ"
            r0 = r7
            boolean r0 = contains(r0, r1, r2, r3, r4, r5, r6)
            if (r0 == 0) goto L_0x004c
            r8.append(r12)
            goto L_0x004f
        L_0x004c:
            r8.append(r11)
        L_0x004f:
            int r0 = r9 + 2
            goto L_0x00d4
        L_0x0053:
            java.lang.String r0 = "SIO"
            java.lang.String r1 = "SIA"
            boolean r0 = contains(r7, r9, r3, r0, r1)
            if (r0 != 0) goto L_0x00c9
            r0 = 4
            java.lang.String r1 = "SIAN"
            boolean r0 = contains(r7, r9, r0, r1)
            if (r0 == 0) goto L_0x0067
            goto L_0x00c9
        L_0x0067:
            if (r9 != 0) goto L_0x007b
            int r1 = r9 + 1
            r2 = 1
            java.lang.String r3 = "M"
            java.lang.String r4 = "N"
            java.lang.String r5 = "L"
            java.lang.String r6 = "W"
            r0 = r7
            boolean r0 = contains(r0, r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x0085
        L_0x007b:
            int r0 = r9 + 1
            java.lang.String r1 = "Z"
            boolean r1 = contains(r7, r0, r10, r1)
            if (r1 == 0) goto L_0x0095
        L_0x0085:
            r8.append(r12, r11)
            int r0 = r9 + 1
            java.lang.String r1 = "Z"
            boolean r1 = contains(r7, r0, r10, r1)
            if (r1 == 0) goto L_0x00d4
            int r0 = r9 + 2
            goto L_0x00d4
        L_0x0095:
            java.lang.String r1 = "SC"
            boolean r1 = contains(r7, r9, r13, r1)
            if (r1 == 0) goto L_0x00a2
            int r0 = r14.handleSC(r15, r16, r17)
            goto L_0x00d4
        L_0x00a2:
            int r1 = r7.length()
            int r1 = r1 - r10
            if (r9 != r1) goto L_0x00b9
            int r1 = r9 + -2
            java.lang.String r2 = "AI"
            java.lang.String r3 = "OI"
            boolean r1 = contains(r7, r1, r13, r2, r3)
            if (r1 == 0) goto L_0x00b9
            r8.appendAlternate(r12)
            goto L_0x00bc
        L_0x00b9:
            r8.append(r12)
        L_0x00bc:
            java.lang.String r1 = "S"
            java.lang.String r2 = "Z"
            boolean r1 = contains(r7, r0, r10, r1, r2)
            if (r1 == 0) goto L_0x00d4
            int r0 = r9 + 2
            goto L_0x00d4
        L_0x00c9:
            if (r18 == 0) goto L_0x00cf
            r8.append(r12)
            goto L_0x00d2
        L_0x00cf:
            r8.append(r12, r11)
        L_0x00d2:
            int r0 = r9 + 3
        L_0x00d4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.DoubleMetaphone.handleS(java.lang.String, org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult, int, boolean):int");
    }

    private int handleSC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        String str2 = str;
        DoubleMetaphoneResult doubleMetaphoneResult2 = doubleMetaphoneResult;
        int i2 = i + 2;
        if (charAt(str2, i2) == 'H') {
            int i3 = i + 3;
            if (contains(str2, i3, 2, "OO", "ER", "EN", "UY", "ED", "EM")) {
                if (contains(str2, i3, 2, "ER", "EN")) {
                    doubleMetaphoneResult2.append("X", "SK");
                } else {
                    doubleMetaphoneResult2.append("SK");
                }
            } else if (i != 0 || isVowel(charAt(str2, 3)) || charAt(str2, 3) == 'W') {
                doubleMetaphoneResult2.append('X');
            } else {
                doubleMetaphoneResult2.append('X', 'S');
            }
        } else {
            if (contains(str2, i2, 1, "I", "E", "Y")) {
                doubleMetaphoneResult2.append('S');
            } else {
                doubleMetaphoneResult2.append("SK");
            }
        }
        return i + 3;
    }

    private int handleT(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 4, "TION")) {
            doubleMetaphoneResult.append('X');
            return i + 3;
        } else if (contains(str, i, 3, "TIA", "TCH")) {
            doubleMetaphoneResult.append('X');
            return i + 3;
        } else if (contains(str, i, 2, "TH") || contains(str, i, 3, "TTH")) {
            int i2 = i + 2;
            if (contains(str, i2, 2, "OM", "AM") || contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.append('T');
                return i2;
            }
            doubleMetaphoneResult.append('0', 'T');
            return i2;
        } else {
            doubleMetaphoneResult.append('T');
            int i3 = i + 1;
            return contains(str, i3, 1, "T", "D") ? i + 2 : i3;
        }
    }

    private int handleW(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 2, "WR")) {
            doubleMetaphoneResult.append('R');
            return i + 2;
        }
        if (i == 0) {
            int i2 = i + 1;
            if (isVowel(charAt(str, i2)) || contains(str, i, 2, "WH")) {
                if (isVowel(charAt(str, i2))) {
                    doubleMetaphoneResult.append('A', 'F');
                } else {
                    doubleMetaphoneResult.append('A');
                }
                return i2;
            }
        }
        if (i != str.length() - 1 || !isVowel(charAt(str, i - 1))) {
            if (!contains(str, i - 1, 5, "EWSKI", "EWSKY", "OWSKI", "OWSKY") && !contains(str, 0, 3, "SCH")) {
                if (!contains(str, i, 4, "WICZ", "WITZ")) {
                    return i + 1;
                }
                doubleMetaphoneResult.append("TS", "FX");
                return i + 4;
            }
        }
        doubleMetaphoneResult.appendAlternate('F');
        return i + 1;
    }

    private int handleX(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i == 0) {
            doubleMetaphoneResult.append('S');
            return i + 1;
        }
        if (i != str.length() - 1 || (!contains(str, i - 3, 3, "IAU", "EAU") && !contains(str, i - 2, 2, "AU", "OU"))) {
            doubleMetaphoneResult.append("KS");
        }
        int i2 = i + 1;
        return contains(str, i2, 1, "C", "X") ? i + 2 : i2;
    }

    private int handleZ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'H') {
            doubleMetaphoneResult.append('J');
            return i + 2;
        }
        if (contains(str, i2, 2, "ZO", "ZI", "ZA") || (z && i > 0 && charAt(str, i - 1) != 'T')) {
            doubleMetaphoneResult.append("S", "TS");
        } else {
            doubleMetaphoneResult.append('S');
        }
        if (charAt(str, i2) == 'Z') {
            i2 = i + 2;
        }
        return i2;
    }

    private boolean conditionC0(String str, int i) {
        if (contains(str, i, 4, "CHIA")) {
            return true;
        }
        boolean z = false;
        if (i <= 1) {
            return false;
        }
        int i2 = i - 2;
        if (isVowel(charAt(str, i2)) || !contains(str, i - 1, 3, "ACH")) {
            return false;
        }
        char charAt = charAt(str, i + 2);
        if (!(charAt == 'I' || charAt == 'E') || contains(str, i2, 6, "BACHER", "MACHER")) {
            z = true;
        }
        return z;
    }

    private boolean conditionCH0(String str, int i) {
        if (i != 0) {
            return false;
        }
        int i2 = i + 1;
        if (!contains(str, i2, 5, "HARAC", "HARIS")) {
            if (!contains(str, i2, 3, "HOR", "HYM", "HIA", "HEM")) {
                return false;
            }
        }
        return !contains(str, 0, 5, "CHORE");
    }

    private boolean conditionCH1(String str, int i) {
        if (contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH")) {
            return true;
        }
        if (contains(str, i - 2, 6, "ORCHES", "ARCHIT", "ORCHID")) {
            return true;
        }
        int i2 = i + 2;
        if (contains(str, i2, 1, "T", "S")) {
            return true;
        }
        return (contains(str, i + -1, 1, "A", "O", "U", "E") || i == 0) && (contains(str, i2, 1, L_R_N_M_B_H_F_V_W_SPACE) || i + 1 == str.length() - 1);
    }

    private boolean conditionL0(String str, int i) {
        if (i == str.length() - 3) {
            if (contains(str, i - 1, 4, "ILLO", "ILLA", "ALLE")) {
                return true;
            }
        }
        if ((contains(str, str.length() - 2, 2, "AS", "OS") || contains(str, str.length() - 1, 1, "A", "O")) && contains(str, i - 1, 4, "ALLE")) {
            return true;
        }
        return false;
    }

    private boolean conditionM0(String str, int i) {
        int i2 = i + 1;
        boolean z = true;
        if (charAt(str, i2) == 'M') {
            return true;
        }
        if (!contains(str, i - 1, 3, "UMB") || (i2 != str.length() - 1 && !contains(str, i + 2, 2, "ER"))) {
            z = false;
        }
        return z;
    }

    private boolean isSlavoGermanic(String str) {
        return str.indexOf(87) > -1 || str.indexOf(75) > -1 || str.indexOf("CZ") > -1 || str.indexOf("WITZ") > -1;
    }

    private boolean isVowel(char c) {
        return VOWELS.indexOf(c) != -1;
    }

    private boolean isSilentStart(String str) {
        for (String startsWith : SILENT_START) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    private String cleanInput(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim.toUpperCase(Locale.ENGLISH);
    }

    /* access modifiers changed from: protected */
    public char charAt(String str, int i) {
        if (i < 0 || i >= str.length()) {
            return 0;
        }
        return str.charAt(i);
    }

    private static boolean contains(String str, int i, int i2, String str2) {
        return contains(str, i, i2, new String[]{str2});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3) {
        return contains(str, i, i2, new String[]{str2, str3});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4) {
        return contains(str, i, i2, new String[]{str2, str3, str4});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4, String str5) {
        return contains(str, i, i2, new String[]{str2, str3, str4, str5});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4, String str5, String str6) {
        return contains(str, i, i2, new String[]{str2, str3, str4, str5, str6});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4, String str5, String str6, String str7) {
        return contains(str, i, i2, new String[]{str2, str3, str4, str5, str6, str7});
    }

    protected static boolean contains(String str, int i, int i2, String[] strArr) {
        if (i < 0) {
            return false;
        }
        int i3 = i2 + i;
        if (i3 > str.length()) {
            return false;
        }
        String substring = str.substring(i, i3);
        for (String equals : strArr) {
            if (substring.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
