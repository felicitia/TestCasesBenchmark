package com.google.zxing.qrcode.detector;

import java.io.Serializable;
import java.util.Comparator;

public class FinderPatternFinder {

    private static final class CenterComparator implements Serializable, Comparator<a> {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        public int compare(a aVar, a aVar2) {
            if (aVar2.b() != aVar.b()) {
                return aVar2.b() - aVar.b();
            }
            float abs = Math.abs(aVar2.a() - this.average);
            float abs2 = Math.abs(aVar.a() - this.average);
            if (abs < abs2) {
                return 1;
            }
            return abs == abs2 ? 0 : -1;
        }
    }

    private static final class FurthestFromAverageComparator implements Serializable, Comparator<a> {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        public int compare(a aVar, a aVar2) {
            float abs = Math.abs(aVar2.a() - this.average);
            float abs2 = Math.abs(aVar.a() - this.average);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }
    }
}
