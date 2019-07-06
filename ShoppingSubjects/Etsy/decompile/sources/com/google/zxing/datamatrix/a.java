package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.datamatrix.encoder.e;
import com.google.zxing.datamatrix.encoder.i;
import com.google.zxing.datamatrix.encoder.j;
import com.google.zxing.datamatrix.encoder.k;
import com.google.zxing.f;
import java.util.Map;

/* compiled from: DataMatrixWriter */
public final class a implements f {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        com.google.zxing.a aVar;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.DATA_MATRIX) {
            StringBuilder sb = new StringBuilder("Can only encode DATA_MATRIX, but got ");
            sb.append(barcodeFormat);
            throw new IllegalArgumentException(sb.toString());
        } else if (i < 0 || i2 < 0) {
            StringBuilder sb2 = new StringBuilder("Requested dimensions are too small: ");
            sb2.append(i);
            sb2.append('x');
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        } else {
            SymbolShapeHint symbolShapeHint = SymbolShapeHint.FORCE_NONE;
            com.google.zxing.a aVar2 = null;
            if (map != null) {
                SymbolShapeHint symbolShapeHint2 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
                if (symbolShapeHint2 != null) {
                    symbolShapeHint = symbolShapeHint2;
                }
                aVar = (com.google.zxing.a) map.get(EncodeHintType.MIN_SIZE);
                if (aVar == null) {
                    aVar = null;
                }
                com.google.zxing.a aVar3 = (com.google.zxing.a) map.get(EncodeHintType.MAX_SIZE);
                if (aVar3 != null) {
                    aVar2 = aVar3;
                }
            } else {
                aVar = null;
            }
            String a = j.a(str, symbolShapeHint, aVar, aVar2);
            k a2 = k.a(a.length(), symbolShapeHint, aVar, aVar2, true);
            e eVar = new e(i.a(a, a2), a2.b(), a2.c());
            eVar.a();
            return a(eVar, a2);
        }
    }

    private static b a(e eVar, k kVar) {
        int b = kVar.b();
        int c = kVar.c();
        com.google.zxing.qrcode.a.b bVar = new com.google.zxing.qrcode.a.b(kVar.d(), kVar.e());
        int i = 0;
        for (int i2 = 0; i2 < c; i2++) {
            if (i2 % kVar.c == 0) {
                int i3 = 0;
                for (int i4 = 0; i4 < kVar.d(); i4++) {
                    bVar.a(i3, i, i4 % 2 == 0);
                    i3++;
                }
                i++;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < b; i6++) {
                if (i6 % kVar.b == 0) {
                    bVar.a(i5, i, true);
                    i5++;
                }
                bVar.a(i5, i, eVar.a(i6, i2));
                i5++;
                if (i6 % kVar.b == kVar.b - 1) {
                    bVar.a(i5, i, i2 % 2 == 0);
                    i5++;
                }
            }
            i++;
            if (i2 % kVar.c == kVar.c - 1) {
                int i7 = 0;
                for (int i8 = 0; i8 < kVar.d(); i8++) {
                    bVar.a(i7, i, true);
                    i7++;
                }
                i++;
            }
        }
        return a(bVar);
    }

    private static b a(com.google.zxing.qrcode.a.b bVar) {
        int b = bVar.b();
        int a = bVar.a();
        b bVar2 = new b(b, a);
        bVar2.a();
        for (int i = 0; i < b; i++) {
            for (int i2 = 0; i2 < a; i2++) {
                if (bVar.a(i, i2) == 1) {
                    bVar2.b(i, i2);
                }
            }
        }
        return bVar2;
    }
}
