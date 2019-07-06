package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays.Function;

public class ResizableDoubleArray implements Serializable, DoubleArray {
    @Deprecated
    public static final int ADDITIVE_MODE = 1;
    private static final double DEFAULT_CONTRACTION_DELTA = 0.5d;
    private static final double DEFAULT_EXPANSION_FACTOR = 2.0d;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    @Deprecated
    public static final int MULTIPLICATIVE_MODE = 0;
    private static final long serialVersionUID = -3485529955529426875L;
    private double contractionCriterion;
    private double expansionFactor;
    private ExpansionMode expansionMode;
    private double[] internalArray;
    private int numElements;
    private int startIndex;

    public enum ExpansionMode {
        MULTIPLICATIVE,
        ADDITIVE
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void setInitialCapacity(int i) throws MathIllegalArgumentException {
    }

    public ResizableDoubleArray() {
        this(16);
    }

    public ResizableDoubleArray(int i) throws MathIllegalArgumentException {
        this(i, (double) DEFAULT_EXPANSION_FACTOR);
    }

    public ResizableDoubleArray(double[] dArr) {
        this(16, DEFAULT_EXPANSION_FACTOR, 2.5d, ExpansionMode.MULTIPLICATIVE, dArr);
    }

    @Deprecated
    public ResizableDoubleArray(int i, float f) throws MathIllegalArgumentException {
        this(i, (double) f);
    }

    public ResizableDoubleArray(int i, double d) throws MathIllegalArgumentException {
        this(i, d, DEFAULT_CONTRACTION_DELTA + d);
    }

    @Deprecated
    public ResizableDoubleArray(int i, float f, float f2) throws MathIllegalArgumentException {
        this(i, (double) f, (double) f2);
    }

    public ResizableDoubleArray(int i, double d, double d2) throws MathIllegalArgumentException {
        this(i, d, d2, ExpansionMode.MULTIPLICATIVE, null);
    }

    public ResizableDoubleArray(int i, float f, float f2, int i2) throws MathIllegalArgumentException {
        this(i, (double) f, (double) f2, i2 == 1 ? ExpansionMode.ADDITIVE : ExpansionMode.MULTIPLICATIVE, null);
        setExpansionMode(i2);
    }

    public ResizableDoubleArray(int i, double d, double d2, ExpansionMode expansionMode2, double... dArr) throws MathIllegalArgumentException {
        this.contractionCriterion = 2.5d;
        this.expansionFactor = DEFAULT_EXPANSION_FACTOR;
        this.expansionMode = ExpansionMode.MULTIPLICATIVE;
        this.numElements = 0;
        this.startIndex = 0;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.INITIAL_CAPACITY_NOT_POSITIVE, Integer.valueOf(i));
        }
        checkContractExpand(d2, d);
        this.expansionFactor = d;
        this.contractionCriterion = d2;
        this.expansionMode = expansionMode2;
        this.internalArray = new double[i];
        this.numElements = 0;
        this.startIndex = 0;
        if (dArr != null) {
            addElements(dArr);
        }
    }

    public ResizableDoubleArray(ResizableDoubleArray resizableDoubleArray) throws NullArgumentException {
        this.contractionCriterion = 2.5d;
        this.expansionFactor = DEFAULT_EXPANSION_FACTOR;
        this.expansionMode = ExpansionMode.MULTIPLICATIVE;
        this.numElements = 0;
        this.startIndex = 0;
        MathUtils.checkNotNull(resizableDoubleArray);
        copy(resizableDoubleArray, this);
    }

    public synchronized void addElement(double d) {
        if (this.internalArray.length <= this.startIndex + this.numElements) {
            expand();
        }
        double[] dArr = this.internalArray;
        int i = this.startIndex;
        int i2 = this.numElements;
        this.numElements = i2 + 1;
        dArr[i + i2] = d;
    }

    public synchronized void addElements(double[] dArr) {
        double[] dArr2 = new double[(this.numElements + dArr.length + 1)];
        System.arraycopy(this.internalArray, this.startIndex, dArr2, 0, this.numElements);
        System.arraycopy(dArr, 0, dArr2, this.numElements, dArr.length);
        this.internalArray = dArr2;
        this.startIndex = 0;
        this.numElements += dArr.length;
    }

    public synchronized double addElementRolling(double d) {
        double d2;
        d2 = this.internalArray[this.startIndex];
        if (this.startIndex + this.numElements + 1 > this.internalArray.length) {
            expand();
        }
        this.startIndex++;
        this.internalArray[this.startIndex + (this.numElements - 1)] = d;
        if (shouldContract()) {
            contract();
        }
        return d2;
    }

    public synchronized double substituteMostRecentElement(double d) throws MathIllegalStateException {
        double d2;
        if (this.numElements < 1) {
            throw new MathIllegalStateException(LocalizedFormats.CANNOT_SUBSTITUTE_ELEMENT_FROM_EMPTY_ARRAY, new Object[0]);
        }
        int i = this.startIndex + (this.numElements - 1);
        d2 = this.internalArray[i];
        this.internalArray[i] = d;
        return d2;
    }

    /* access modifiers changed from: protected */
    public void checkContractExpand(float f, float f2) throws MathIllegalArgumentException {
        checkContractExpand((double) f, (double) f2);
    }

    /* access modifiers changed from: protected */
    public void checkContractExpand(double d, double d2) throws NumberIsTooSmallException {
        if (d < d2) {
            NumberIsTooSmallException numberIsTooSmallException = new NumberIsTooSmallException(Double.valueOf(d), Integer.valueOf(1), true);
            numberIsTooSmallException.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_EXPANSION_FACTOR, Double.valueOf(d), Double.valueOf(d2));
            throw numberIsTooSmallException;
        } else if (d <= 1.0d) {
            NumberIsTooSmallException numberIsTooSmallException2 = new NumberIsTooSmallException(Double.valueOf(d), Integer.valueOf(1), false);
            numberIsTooSmallException2.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_ONE, Double.valueOf(d));
            throw numberIsTooSmallException2;
        } else if (d2 <= 1.0d) {
            NumberIsTooSmallException numberIsTooSmallException3 = new NumberIsTooSmallException(Double.valueOf(d), Integer.valueOf(1), false);
            numberIsTooSmallException3.getContext().addMessage(LocalizedFormats.EXPANSION_FACTOR_SMALLER_THAN_ONE, Double.valueOf(d2));
            throw numberIsTooSmallException3;
        }
    }

    public synchronized void clear() {
        this.numElements = 0;
        this.startIndex = 0;
    }

    public synchronized void contract() {
        double[] dArr = new double[(this.numElements + 1)];
        System.arraycopy(this.internalArray, this.startIndex, dArr, 0, this.numElements);
        this.internalArray = dArr;
        this.startIndex = 0;
    }

    public synchronized void discardFrontElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, true);
    }

    public synchronized void discardMostRecentElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, false);
    }

    private synchronized void discardExtremeElements(int i, boolean z) throws MathIllegalArgumentException {
        if (i > this.numElements) {
            throw new MathIllegalArgumentException(LocalizedFormats.TOO_MANY_ELEMENTS_TO_DISCARD_FROM_ARRAY, Integer.valueOf(i), Integer.valueOf(this.numElements));
        } else if (i < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_DISCARD_NEGATIVE_NUMBER_OF_ELEMENTS, Integer.valueOf(i));
        } else {
            this.numElements -= i;
            if (z) {
                this.startIndex += i;
            }
            if (shouldContract()) {
                contract();
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void expand() {
        int i;
        if (this.expansionMode == ExpansionMode.MULTIPLICATIVE) {
            i = (int) FastMath.ceil(((double) this.internalArray.length) * this.expansionFactor);
        } else {
            i = (int) (((long) this.internalArray.length) + FastMath.round(this.expansionFactor));
        }
        double[] dArr = new double[i];
        System.arraycopy(this.internalArray, 0, dArr, 0, this.internalArray.length);
        this.internalArray = dArr;
    }

    private synchronized void expandTo(int i) {
        double[] dArr = new double[i];
        System.arraycopy(this.internalArray, 0, dArr, 0, this.internalArray.length);
        this.internalArray = dArr;
    }

    @Deprecated
    public float getContractionCriteria() {
        return (float) getContractionCriterion();
    }

    public double getContractionCriterion() {
        return this.contractionCriterion;
    }

    public synchronized double getElement(int i) {
        if (i >= this.numElements) {
            throw new ArrayIndexOutOfBoundsException(i);
        } else if (i >= 0) {
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return this.internalArray[this.startIndex + i];
    }

    public synchronized double[] getElements() {
        double[] dArr;
        dArr = new double[this.numElements];
        System.arraycopy(this.internalArray, this.startIndex, dArr, 0, this.numElements);
        return dArr;
    }

    @Deprecated
    public float getExpansionFactor() {
        return (float) this.expansionFactor;
    }

    public int getExpansionMode() {
        switch (this.expansionMode) {
            case MULTIPLICATIVE:
                return 0;
            case ADDITIVE:
                return 1;
            default:
                throw new MathInternalError();
        }
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    public synchronized int getInternalLength() {
        return this.internalArray.length;
    }

    public int getCapacity() {
        return this.internalArray.length;
    }

    public synchronized int getNumElements() {
        return this.numElements;
    }

    @Deprecated
    public synchronized double[] getInternalValues() {
        return this.internalArray;
    }

    /* access modifiers changed from: protected */
    public double[] getArrayRef() {
        return this.internalArray;
    }

    /* access modifiers changed from: protected */
    public int getStartIndex() {
        return this.startIndex;
    }

    @Deprecated
    public void setContractionCriteria(float f) throws MathIllegalArgumentException {
        checkContractExpand(f, getExpansionFactor());
        synchronized (this) {
            this.contractionCriterion = (double) f;
        }
    }

    public double compute(Function function) {
        return function.evaluate(this.internalArray, this.startIndex, this.numElements);
    }

    public synchronized void setElement(int i, double d) {
        if (i < 0) {
            try {
                throw new ArrayIndexOutOfBoundsException(i);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            int i2 = i + 1;
            if (i2 > this.numElements) {
                this.numElements = i2;
            }
            if (this.startIndex + i >= this.internalArray.length) {
                expandTo(this.startIndex + i2);
            }
            this.internalArray[this.startIndex + i] = d;
        }
    }

    @Deprecated
    public void setExpansionFactor(float f) throws MathIllegalArgumentException {
        double d = (double) f;
        checkContractExpand(getContractionCriterion(), d);
        synchronized (this) {
            this.expansionFactor = d;
        }
    }

    @Deprecated
    public void setExpansionMode(int i) throws MathIllegalArgumentException {
        if (i == 0 || i == 1) {
            synchronized (this) {
                if (i == 0) {
                    try {
                        setExpansionMode(ExpansionMode.MULTIPLICATIVE);
                    } catch (Throwable th) {
                        throw th;
                    }
                } else if (i == 1) {
                    setExpansionMode(ExpansionMode.ADDITIVE);
                }
            }
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.UNSUPPORTED_EXPANSION_MODE, Integer.valueOf(i), Integer.valueOf(0), "MULTIPLICATIVE_MODE", Integer.valueOf(1), "ADDITIVE_MODE");
    }

    @Deprecated
    public void setExpansionMode(ExpansionMode expansionMode2) {
        this.expansionMode = expansionMode2;
    }

    public synchronized void setNumElements(int i) throws MathIllegalArgumentException {
        if (i < 0) {
            try {
                throw new MathIllegalArgumentException(LocalizedFormats.INDEX_NOT_POSITIVE, Integer.valueOf(i));
            } catch (Throwable th) {
                throw th;
            }
        } else {
            int i2 = this.startIndex + i;
            if (i2 > this.internalArray.length) {
                expandTo(i2);
            }
            this.numElements = i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean shouldContract() {
        /*
            r7 = this;
            monitor-enter(r7)
            org.apache.commons.math3.util.ResizableDoubleArray$ExpansionMode r0 = r7.expansionMode     // Catch:{ all -> 0x002b }
            org.apache.commons.math3.util.ResizableDoubleArray$ExpansionMode r1 = org.apache.commons.math3.util.ResizableDoubleArray.ExpansionMode.MULTIPLICATIVE     // Catch:{ all -> 0x002b }
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L_0x001b
            double[] r0 = r7.internalArray     // Catch:{ all -> 0x002b }
            int r0 = r0.length     // Catch:{ all -> 0x002b }
            float r0 = (float) r0     // Catch:{ all -> 0x002b }
            int r1 = r7.numElements     // Catch:{ all -> 0x002b }
            float r1 = (float) r1     // Catch:{ all -> 0x002b }
            float r0 = r0 / r1
            double r0 = (double) r0     // Catch:{ all -> 0x002b }
            double r4 = r7.contractionCriterion     // Catch:{ all -> 0x002b }
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0019
            r2 = r3
        L_0x0019:
            monitor-exit(r7)
            return r2
        L_0x001b:
            double[] r0 = r7.internalArray     // Catch:{ all -> 0x002b }
            int r0 = r0.length     // Catch:{ all -> 0x002b }
            int r1 = r7.numElements     // Catch:{ all -> 0x002b }
            int r0 = r0 - r1
            double r0 = (double) r0     // Catch:{ all -> 0x002b }
            double r4 = r7.contractionCriterion     // Catch:{ all -> 0x002b }
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0029
            r2 = r3
        L_0x0029:
            monitor-exit(r7)
            return r2
        L_0x002b:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.ResizableDoubleArray.shouldContract():boolean");
    }

    @Deprecated
    public synchronized int start() {
        return this.startIndex;
    }

    public static void copy(ResizableDoubleArray resizableDoubleArray, ResizableDoubleArray resizableDoubleArray2) throws NullArgumentException {
        MathUtils.checkNotNull(resizableDoubleArray);
        MathUtils.checkNotNull(resizableDoubleArray2);
        synchronized (resizableDoubleArray) {
            synchronized (resizableDoubleArray2) {
                resizableDoubleArray2.contractionCriterion = resizableDoubleArray.contractionCriterion;
                resizableDoubleArray2.expansionFactor = resizableDoubleArray.expansionFactor;
                resizableDoubleArray2.expansionMode = resizableDoubleArray.expansionMode;
                resizableDoubleArray2.internalArray = new double[resizableDoubleArray.internalArray.length];
                System.arraycopy(resizableDoubleArray.internalArray, 0, resizableDoubleArray2.internalArray, 0, resizableDoubleArray2.internalArray.length);
                resizableDoubleArray2.numElements = resizableDoubleArray.numElements;
                resizableDoubleArray2.startIndex = resizableDoubleArray.startIndex;
            }
        }
    }

    public synchronized ResizableDoubleArray copy() {
        ResizableDoubleArray resizableDoubleArray;
        resizableDoubleArray = new ResizableDoubleArray();
        copy(this, resizableDoubleArray);
        return resizableDoubleArray;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResizableDoubleArray)) {
            return false;
        }
        synchronized (this) {
            synchronized (obj) {
                ResizableDoubleArray resizableDoubleArray = (ResizableDoubleArray) obj;
                if (!(((((resizableDoubleArray.contractionCriterion > this.contractionCriterion ? 1 : (resizableDoubleArray.contractionCriterion == this.contractionCriterion ? 0 : -1)) == 0) && (resizableDoubleArray.expansionFactor > this.expansionFactor ? 1 : (resizableDoubleArray.expansionFactor == this.expansionFactor ? 0 : -1)) == 0) && resizableDoubleArray.expansionMode == this.expansionMode) && resizableDoubleArray.numElements == this.numElements) || resizableDoubleArray.startIndex != this.startIndex) {
                    z = false;
                }
                if (!z) {
                    return false;
                }
                boolean equals = Arrays.equals(this.internalArray, resizableDoubleArray.internalArray);
                return equals;
            }
        }
    }

    public synchronized int hashCode() {
        return Arrays.hashCode(new int[]{Double.valueOf(this.expansionFactor).hashCode(), Double.valueOf(this.contractionCriterion).hashCode(), this.expansionMode.hashCode(), Arrays.hashCode(this.internalArray), this.numElements, this.startIndex});
    }
}
