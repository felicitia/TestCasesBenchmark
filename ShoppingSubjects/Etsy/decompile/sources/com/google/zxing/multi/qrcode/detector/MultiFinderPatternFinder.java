package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.a;
import com.google.zxing.qrcode.detector.b;
import java.io.Serializable;
import java.util.Comparator;

final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final b[] a = new b[0];

    private static final class ModuleSizeComparator implements Serializable, Comparator<a> {
        private ModuleSizeComparator() {
        }

        public int compare(a aVar, a aVar2) {
            double a = (double) (aVar2.a() - aVar.a());
            if (a < 0.0d) {
                return -1;
            }
            return a > 0.0d ? 1 : 0;
        }
    }
}
