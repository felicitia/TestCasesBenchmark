package com.google.zxing.multi.qrcode;

import com.google.zxing.ResultMetadataType;
import com.google.zxing.c;
import com.google.zxing.d;
import com.google.zxing.qrcode.a;
import java.io.Serializable;
import java.util.Comparator;

public final class QRCodeMultiReader extends a {
    private static final c[] a = new c[0];
    private static final d[] b = new d[0];

    private static final class SAComparator implements Serializable, Comparator<c> {
        private SAComparator() {
        }

        public int compare(c cVar, c cVar2) {
            int intValue = ((Integer) cVar.a().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            int intValue2 = ((Integer) cVar2.a().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            if (intValue < intValue2) {
                return -1;
            }
            return intValue > intValue2 ? 1 : 0;
        }
    }
}
