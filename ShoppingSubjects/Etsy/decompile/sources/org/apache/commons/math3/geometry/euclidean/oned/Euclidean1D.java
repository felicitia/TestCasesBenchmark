package org.apache.commons.math3.geometry.euclidean.oned;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Space;

public class Euclidean1D implements Serializable, Space {
    private static final long serialVersionUID = -1178039568877797126L;

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final Euclidean1D INSTANCE = new Euclidean1D();

        private LazyHolder() {
        }
    }

    public int getDimension() {
        return 1;
    }

    private Euclidean1D() {
    }

    public static Euclidean1D getInstance() {
        return LazyHolder.INSTANCE;
    }

    public Space getSubSpace() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException(LocalizedFormats.NOT_SUPPORTED_IN_DIMENSION_N, Integer.valueOf(1));
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }
}
