package com.google.zxing;

import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.e;
import com.google.zxing.oned.g;
import com.google.zxing.oned.i;
import com.google.zxing.oned.j;
import com.google.zxing.oned.l;
import com.google.zxing.oned.o;
import com.google.zxing.oned.s;
import com.google.zxing.pdf417.a;
import java.util.Map;

/* compiled from: MultiFormatWriter */
public final class b implements f {
    public com.google.zxing.common.b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        f fVar;
        switch (barcodeFormat) {
            case EAN_8:
                fVar = new j();
                break;
            case UPC_E:
                fVar = new s();
                break;
            case EAN_13:
                fVar = new i();
                break;
            case UPC_A:
                fVar = new o();
                break;
            case QR_CODE:
                fVar = new com.google.zxing.qrcode.b();
                break;
            case CODE_39:
                fVar = new e();
                break;
            case CODE_93:
                fVar = new g();
                break;
            case CODE_128:
                fVar = new Code128Writer();
                break;
            case ITF:
                fVar = new l();
                break;
            case PDF_417:
                fVar = new a();
                break;
            case CODABAR:
                fVar = new com.google.zxing.oned.b();
                break;
            case DATA_MATRIX:
                fVar = new com.google.zxing.datamatrix.a();
                break;
            case AZTEC:
                fVar = new com.google.zxing.aztec.a();
                break;
            default:
                StringBuilder sb = new StringBuilder("No encoder available for format ");
                sb.append(barcodeFormat);
                throw new IllegalArgumentException(sb.toString());
        }
        return fVar.a(str, barcodeFormat, i, i2, map);
    }
}
