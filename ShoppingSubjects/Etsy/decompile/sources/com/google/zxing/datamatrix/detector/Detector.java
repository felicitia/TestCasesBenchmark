package com.google.zxing.datamatrix.detector;

import com.google.zxing.d;
import java.io.Serializable;
import java.util.Comparator;

public final class Detector {

    private static final class ResultPointsAndTransitionsComparator implements Serializable, Comparator<a> {
        private ResultPointsAndTransitionsComparator() {
        }

        public int compare(a aVar, a aVar2) {
            return aVar.a() - aVar2.a();
        }
    }

    private static final class a {
        private final d a;
        private final d b;
        private final int c;

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.c;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("/");
            sb.append(this.b);
            sb.append('/');
            sb.append(this.c);
            return sb.toString();
        }
    }
}
