package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class ColognePhonetic implements StringEncoder {
    private static final char[][] PREPROCESS_MAP = {new char[]{196, 'A'}, new char[]{220, 'U'}, new char[]{214, 'O'}, new char[]{223, 'S'}};

    private abstract class CologneBuffer {
        protected final char[] data;
        protected int length = 0;

        /* access modifiers changed from: protected */
        public abstract char[] copyData(int i, int i2);

        public CologneBuffer(char[] cArr) {
            this.data = cArr;
            this.length = cArr.length;
        }

        public CologneBuffer(int i) {
            this.data = new char[i];
            this.length = 0;
        }

        public int length() {
            return this.length;
        }

        public String toString() {
            return new String(copyData(0, this.length));
        }
    }

    private class CologneInputBuffer extends CologneBuffer {
        public CologneInputBuffer(char[] cArr) {
            super(cArr);
        }

        public void addLeft(char c) {
            this.length++;
            this.data[getNextPos()] = c;
        }

        /* access modifiers changed from: protected */
        public char[] copyData(int i, int i2) {
            char[] cArr = new char[i2];
            System.arraycopy(this.data, (this.data.length - this.length) + i, cArr, 0, i2);
            return cArr;
        }

        public char getNextChar() {
            return this.data[getNextPos()];
        }

        /* access modifiers changed from: protected */
        public int getNextPos() {
            return this.data.length - this.length;
        }

        public char removeNext() {
            char nextChar = getNextChar();
            this.length--;
            return nextChar;
        }
    }

    private class CologneOutputBuffer extends CologneBuffer {
        public CologneOutputBuffer(int i) {
            super(i);
        }

        public void addRight(char c) {
            this.data[this.length] = c;
            this.length++;
        }

        /* access modifiers changed from: protected */
        public char[] copyData(int i, int i2) {
            char[] cArr = new char[i2];
            System.arraycopy(this.data, i, cArr, 0, i2);
            return cArr;
        }
    }

    private static boolean arrayContains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:75:0x011c, code lost:
        if (r7 != '/') goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0125, code lost:
        if (r8 <= '8') goto L_0x012a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String colognePhonetic(java.lang.String r18) {
        /*
            r17 = this;
            r0 = r17
            if (r18 != 0) goto L_0x0006
            r1 = 0
            return r1
        L_0x0006:
            java.lang.String r1 = r17.preprocess(r18)
            org.apache.commons.codec.language.ColognePhonetic$CologneOutputBuffer r2 = new org.apache.commons.codec.language.ColognePhonetic$CologneOutputBuffer
            int r3 = r1.length()
            r4 = 2
            int r3 = r3 * r4
            r2.<init>(r3)
            org.apache.commons.codec.language.ColognePhonetic$CologneInputBuffer r3 = new org.apache.commons.codec.language.ColognePhonetic$CologneInputBuffer
            char[] r1 = r1.toCharArray()
            r3.<init>(r1)
            int r1 = r3.length()
            r5 = 47
            r6 = 45
            r7 = r5
            r8 = r6
        L_0x0028:
            if (r1 <= 0) goto L_0x012f
            char r1 = r3.removeNext()
            int r9 = r3.length()
            if (r9 <= 0) goto L_0x0039
            char r10 = r3.getNextChar()
            goto L_0x003a
        L_0x0039:
            r10 = r6
        L_0x003a:
            r11 = 7
            char[] r12 = new char[r11]
            r12 = {65, 69, 73, 74, 79, 85, 89} // fill-array
            boolean r12 = arrayContains(r12, r1)
            if (r12 == 0) goto L_0x004a
            r8 = 48
            goto L_0x0114
        L_0x004a:
            r12 = 72
            if (r1 == r12) goto L_0x0110
            r14 = 65
            if (r1 < r14) goto L_0x0110
            r14 = 90
            if (r1 <= r14) goto L_0x0058
            goto L_0x0110
        L_0x0058:
            r15 = 66
            if (r1 == r15) goto L_0x010d
            r15 = 80
            if (r1 != r15) goto L_0x0064
            if (r10 == r12) goto L_0x0064
            goto L_0x010d
        L_0x0064:
            r12 = 68
            r15 = 3
            if (r1 == r12) goto L_0x006d
            r12 = 84
            if (r1 != r12) goto L_0x007c
        L_0x006d:
            char[] r12 = new char[r15]
            r12 = {83, 67, 90} // fill-array
            boolean r12 = arrayContains(r12, r10)
            if (r12 != 0) goto L_0x007c
            r8 = 50
            goto L_0x0114
        L_0x007c:
            r12 = 4
            char[] r12 = new char[r12]
            r12 = {87, 70, 80, 86} // fill-array
            boolean r12 = arrayContains(r12, r1)
            if (r12 == 0) goto L_0x008c
            r8 = 51
            goto L_0x0114
        L_0x008c:
            char[] r12 = new char[r15]
            r12 = {71, 75, 81} // fill-array
            boolean r12 = arrayContains(r12, r1)
            if (r12 == 0) goto L_0x009b
        L_0x0097:
            r8 = 52
            goto L_0x0114
        L_0x009b:
            r12 = 88
            r13 = 83
            if (r1 != r12) goto L_0x00b2
            char[] r12 = new char[r15]
            r12 = {67, 75, 81} // fill-array
            boolean r12 = arrayContains(r12, r8)
            if (r12 != 0) goto L_0x00b2
            r3.addLeft(r13)
            int r9 = r9 + 1
            goto L_0x0097
        L_0x00b2:
            if (r1 == r13) goto L_0x010a
            if (r1 != r14) goto L_0x00b7
            goto L_0x010a
        L_0x00b7:
            r12 = 67
            if (r1 != r12) goto L_0x00e2
            if (r7 != r5) goto L_0x00cb
            r8 = 9
            char[] r8 = new char[r8]
            r8 = {65, 72, 75, 76, 79, 81, 82, 85, 88} // fill-array
            boolean r8 = arrayContains(r8, r10)
            if (r8 == 0) goto L_0x010a
            goto L_0x0097
        L_0x00cb:
            char[] r12 = new char[r4]
            r12 = {83, 90} // fill-array
            boolean r8 = arrayContains(r12, r8)
            if (r8 != 0) goto L_0x010a
            char[] r8 = new char[r11]
            r8 = {65, 72, 79, 85, 75, 81, 88} // fill-array
            boolean r8 = arrayContains(r8, r10)
            if (r8 != 0) goto L_0x0097
            goto L_0x010a
        L_0x00e2:
            char[] r8 = new char[r15]
            r8 = {84, 68, 88} // fill-array
            boolean r8 = arrayContains(r8, r1)
            if (r8 == 0) goto L_0x00ee
            goto L_0x010a
        L_0x00ee:
            r8 = 82
            if (r1 != r8) goto L_0x00f5
            r8 = 55
            goto L_0x0114
        L_0x00f5:
            r8 = 76
            if (r1 != r8) goto L_0x00fc
            r8 = 53
            goto L_0x0114
        L_0x00fc:
            r8 = 77
            if (r1 == r8) goto L_0x0107
            r8 = 78
            if (r1 != r8) goto L_0x0105
            goto L_0x0107
        L_0x0105:
            r8 = r1
            goto L_0x0114
        L_0x0107:
            r8 = 54
            goto L_0x0114
        L_0x010a:
            r8 = 56
            goto L_0x0114
        L_0x010d:
            r8 = 49
            goto L_0x0114
        L_0x0110:
            if (r7 != r5) goto L_0x0113
            goto L_0x012c
        L_0x0113:
            r8 = r6
        L_0x0114:
            if (r8 == r6) goto L_0x012a
            if (r7 == r8) goto L_0x011f
            r10 = 48
            if (r8 != r10) goto L_0x0127
            if (r7 == r5) goto L_0x0127
            goto L_0x0121
        L_0x011f:
            r10 = 48
        L_0x0121:
            if (r8 < r10) goto L_0x0127
            r7 = 56
            if (r8 <= r7) goto L_0x012a
        L_0x0127:
            r2.addRight(r8)
        L_0x012a:
            r7 = r8
            r8 = r1
        L_0x012c:
            r1 = r9
            goto L_0x0028
        L_0x012f:
            java.lang.String r1 = r2.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.ColognePhonetic.colognePhonetic(java.lang.String):java.lang.String");
    }

    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("This method's parameter was expected to be of the type ");
        sb.append(String.class.getName());
        sb.append(". But actually it was of the type ");
        sb.append(obj.getClass().getName());
        sb.append(".");
        throw new EncoderException(sb.toString());
    }

    public String encode(String str) {
        return colognePhonetic(str);
    }

    public boolean isEncodeEqual(String str, String str2) {
        return colognePhonetic(str).equals(colognePhonetic(str2));
    }

    private String preprocess(String str) {
        char[] charArray = str.toUpperCase(Locale.GERMAN).toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] > 'Z') {
                char[][] cArr = PREPROCESS_MAP;
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    char[] cArr2 = cArr[i2];
                    if (charArray[i] == cArr2[0]) {
                        charArray[i] = cArr2[1];
                        break;
                    }
                    i2++;
                }
            }
        }
        return new String(charArray);
    }
}
