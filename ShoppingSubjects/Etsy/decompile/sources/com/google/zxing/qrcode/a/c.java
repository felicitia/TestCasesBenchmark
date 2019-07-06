package com.google.zxing.qrcode.a;

import com.etsy.android.lib.models.editable.EditableListing;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.a;
import com.google.zxing.qrcode.decoder.a.b;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: Encoder */
public final class c {
    private static final int[] a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private static int a(b bVar) {
        return d.a(bVar) + d.b(bVar) + d.c(bVar) + d.d(bVar);
    }

    public static f a(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException {
        a aVar;
        String str2 = "ISO-8859-1";
        if (map != null && map.containsKey(EncodeHintType.CHARACTER_SET)) {
            str2 = map.get(EncodeHintType.CHARACTER_SET).toString();
        }
        Mode a2 = a(str, str2);
        com.google.zxing.common.a aVar2 = new com.google.zxing.common.a();
        if (a2 == Mode.BYTE && !"ISO-8859-1".equals(str2)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(str2);
            if (characterSetECIByName != null) {
                a(characterSetECIByName, aVar2);
            }
        }
        a(a2, aVar2);
        com.google.zxing.common.a aVar3 = new com.google.zxing.common.a();
        a(str, a2, aVar3, str2);
        if (map == null || !map.containsKey(EncodeHintType.QR_VERSION)) {
            aVar = a(errorCorrectionLevel, a2, aVar2, aVar3);
        } else {
            aVar = a.a(Integer.parseInt(map.get(EncodeHintType.QR_VERSION).toString()));
            if (!a(a(a2, aVar2, aVar3, aVar), aVar, errorCorrectionLevel)) {
                throw new WriterException("Data too big for requested version");
            }
        }
        com.google.zxing.common.a aVar4 = new com.google.zxing.common.a();
        aVar4.a(aVar2);
        a(a2 == Mode.BYTE ? aVar3.b() : str.length(), aVar, a2, aVar4);
        aVar4.a(aVar3);
        b a3 = aVar.a(errorCorrectionLevel);
        int b = aVar.b() - a3.c();
        a(b, aVar4);
        com.google.zxing.common.a a4 = a(aVar4, aVar.b(), b, a3.b());
        f fVar = new f();
        fVar.a(errorCorrectionLevel);
        fVar.a(a2);
        fVar.a(aVar);
        int c = aVar.c();
        b bVar = new b(c, c);
        int a5 = a(a4, errorCorrectionLevel, aVar, bVar);
        fVar.a(a5);
        e.a(a4, errorCorrectionLevel, aVar, a5, bVar);
        fVar.a(bVar);
        return fVar;
    }

    private static a a(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, com.google.zxing.common.a aVar, com.google.zxing.common.a aVar2) throws WriterException {
        return a(a(mode, aVar, aVar2, a(a(mode, aVar, aVar2, a.a(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }

    private static int a(Mode mode, com.google.zxing.common.a aVar, com.google.zxing.common.a aVar2, a aVar3) {
        return aVar.a() + mode.getCharacterCountBits(aVar3) + aVar2.a();
    }

    static int a(int i) {
        if (i < a.length) {
            return a[i];
        }
        return -1;
    }

    private static Mode a(String str, String str2) {
        if ("Shift_JIS".equals(str2) && a(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (a((int) charAt) == -1) {
                return Mode.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z2) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean a(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                byte b = bytes[i] & 255;
                if ((b < 129 || b > 159) && (b < 224 || b > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    private static int a(com.google.zxing.common.a aVar, ErrorCorrectionLevel errorCorrectionLevel, a aVar2, b bVar) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            e.a(aVar, errorCorrectionLevel, aVar2, i3, bVar);
            int a2 = a(bVar);
            if (a2 < i) {
                i2 = i3;
                i = a2;
            }
        }
        return i2;
    }

    private static a a(int i, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            a a2 = a.a(i2);
            if (a(i, a2, errorCorrectionLevel)) {
                return a2;
            }
        }
        throw new WriterException("Data too big");
    }

    private static boolean a(int i, a aVar, ErrorCorrectionLevel errorCorrectionLevel) {
        return aVar.b() - aVar.a(errorCorrectionLevel).c() >= (i + 7) / 8;
    }

    static void a(int i, com.google.zxing.common.a aVar) throws WriterException {
        int i2 = i << 3;
        if (aVar.a() > i2) {
            StringBuilder sb = new StringBuilder("data bits cannot fit in the QR Code");
            sb.append(aVar.a());
            sb.append(EditableListing.CATEGORY_PATH_JOIN_STRING);
            sb.append(i2);
            throw new WriterException(sb.toString());
        }
        for (int i3 = 0; i3 < 4 && aVar.a() < i2; i3++) {
            aVar.a(false);
        }
        int a2 = aVar.a() & 7;
        if (a2 > 0) {
            while (a2 < 8) {
                aVar.a(false);
                a2++;
            }
        }
        int b = i - aVar.b();
        for (int i4 = 0; i4 < b; i4++) {
            aVar.a((i4 & 1) == 0 ? 236 : 17, 8);
        }
        if (aVar.a() != i2) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    static void a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 >= i3) {
            throw new WriterException("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i7 + 1;
        int i9 = i2 / i3;
        int i10 = i9 + 1;
        int i11 = i7 - i9;
        int i12 = i8 - i10;
        if (i11 != i12) {
            throw new WriterException("EC bytes mismatch");
        } else if (i3 != i6 + i5) {
            throw new WriterException("RS blocks mismatch");
        } else if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
            throw new WriterException("Total bytes mismatch");
        } else if (i4 < i6) {
            iArr[0] = i9;
            iArr2[0] = i11;
        } else {
            iArr[0] = i10;
            iArr2[0] = i12;
        }
    }

    static com.google.zxing.common.a a(com.google.zxing.common.a aVar, int i, int i2, int i3) throws WriterException {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (aVar.b() != i5) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        ArrayList<a> arrayList = new ArrayList<>(i6);
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < i6; i10++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            int[] iArr3 = iArr2;
            a(i4, i5, i6, i10, iArr, iArr2);
            int i11 = iArr[0];
            byte[] bArr = new byte[i11];
            aVar.a(i7 << 3, bArr, 0, i11);
            byte[] a2 = a(bArr, iArr3[0]);
            arrayList.add(new a(bArr, a2));
            i8 = Math.max(i8, i11);
            i9 = Math.max(i9, a2.length);
            i7 += iArr[0];
        }
        if (i5 != i7) {
            throw new WriterException("Data bytes does not match offset");
        }
        com.google.zxing.common.a aVar2 = new com.google.zxing.common.a();
        for (int i12 = 0; i12 < i8; i12++) {
            for (a a3 : arrayList) {
                byte[] a4 = a3.a();
                if (i12 < a4.length) {
                    aVar2.a(a4[i12], 8);
                }
            }
        }
        for (int i13 = 0; i13 < i9; i13++) {
            for (a b : arrayList) {
                byte[] b2 = b.b();
                if (i13 < b2.length) {
                    aVar2.a(b2[i13], 8);
                }
            }
        }
        if (i4 == aVar2.b()) {
            return aVar2;
        }
        StringBuilder sb = new StringBuilder("Interleaving error: ");
        sb.append(i4);
        sb.append(" and ");
        sb.append(aVar2.b());
        sb.append(" differ.");
        throw new WriterException(sb.toString());
    }

    static byte[] a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new com.google.zxing.common.reedsolomon.c(com.google.zxing.common.reedsolomon.a.e).a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    static void a(Mode mode, com.google.zxing.common.a aVar) {
        aVar.a(mode.getBits(), 4);
    }

    static void a(int i, a aVar, Mode mode, com.google.zxing.common.a aVar2) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(aVar);
        int i2 = 1 << characterCountBits;
        if (i >= i2) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(" is bigger than ");
            sb.append(i2 - 1);
            throw new WriterException(sb.toString());
        }
        aVar2.a(i, characterCountBits);
    }

    static void a(String str, Mode mode, com.google.zxing.common.a aVar, String str2) throws WriterException {
        switch (mode) {
            case NUMERIC:
                a((CharSequence) str, aVar);
                return;
            case ALPHANUMERIC:
                b(str, aVar);
                return;
            case BYTE:
                a(str, aVar, str2);
                return;
            case KANJI:
                a(str, aVar);
                return;
            default:
                StringBuilder sb = new StringBuilder("Invalid mode: ");
                sb.append(mode);
                throw new WriterException(sb.toString());
        }
    }

    static void a(CharSequence charSequence, com.google.zxing.common.a aVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                aVar.a((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    aVar.a((charAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    aVar.a(charAt, 4);
                }
            }
        }
    }

    static void b(CharSequence charSequence, com.google.zxing.common.a aVar) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a2 = a((int) charSequence.charAt(i));
            if (a2 == -1) {
                throw new WriterException();
            }
            int i2 = i + 1;
            if (i2 < length) {
                int a3 = a((int) charSequence.charAt(i2));
                if (a3 == -1) {
                    throw new WriterException();
                }
                aVar.a((a2 * 45) + a3, 11);
                i += 2;
            } else {
                aVar.a(a2, 6);
                i = i2;
            }
        }
    }

    static void a(String str, com.google.zxing.common.a aVar, String str2) throws WriterException {
        try {
            for (byte a2 : str.getBytes(str2)) {
                aVar.a(a2, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    static void a(String str, com.google.zxing.common.a aVar) throws WriterException {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i = 0; i < length; i += 2) {
                byte b = ((bytes[i] & 255) << 8) | (bytes[i + 1] & 255);
                int i2 = (b < 33088 || b > 40956) ? (b < 57408 || b > 60351) ? -1 : b - 49472 : b - 33088;
                if (i2 == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                aVar.a(((i2 >> 8) * 192) + (i2 & 255), 13);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    private static void a(CharacterSetECI characterSetECI, com.google.zxing.common.a aVar) {
        aVar.a(Mode.ECI.getBits(), 4);
        aVar.a(characterSetECI.getValue(), 8);
    }
}
