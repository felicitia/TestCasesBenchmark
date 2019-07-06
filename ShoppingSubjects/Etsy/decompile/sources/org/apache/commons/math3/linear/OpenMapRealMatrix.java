package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap.Iterator;

@Deprecated
public class OpenMapRealMatrix extends AbstractRealMatrix implements Serializable, SparseRealMatrix {
    private static final long serialVersionUID = -5962461716457143437L;
    private final int columns;
    private final OpenIntToDoubleHashMap entries;
    private final int rows;

    public OpenMapRealMatrix(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        super(i, i2);
        long j = ((long) i) * ((long) i2);
        if (j >= 2147483647L) {
            throw new NumberIsTooLargeException(Long.valueOf(j), Integer.valueOf(Integer.MAX_VALUE), false);
        }
        this.rows = i;
        this.columns = i2;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
    }

    public OpenMapRealMatrix(OpenMapRealMatrix openMapRealMatrix) {
        this.rows = openMapRealMatrix.rows;
        this.columns = openMapRealMatrix.columns;
        this.entries = new OpenIntToDoubleHashMap(openMapRealMatrix.entries);
    }

    public OpenMapRealMatrix copy() {
        return new OpenMapRealMatrix(this);
    }

    public OpenMapRealMatrix createMatrix(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        return new OpenMapRealMatrix(i, i2);
    }

    public int getColumnDimension() {
        return this.columns;
    }

    public OpenMapRealMatrix add(OpenMapRealMatrix openMapRealMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, openMapRealMatrix);
        OpenMapRealMatrix openMapRealMatrix2 = new OpenMapRealMatrix(this);
        Iterator it = openMapRealMatrix.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key() / this.columns;
            int key2 = it.key() - (this.columns * key);
            openMapRealMatrix2.setEntry(key, key2, getEntry(key, key2) + it.value());
        }
        return openMapRealMatrix2;
    }

    public OpenMapRealMatrix subtract(RealMatrix realMatrix) throws MatrixDimensionMismatchException {
        try {
            return subtract((OpenMapRealMatrix) realMatrix);
        } catch (ClassCastException unused) {
            return (OpenMapRealMatrix) super.subtract(realMatrix);
        }
    }

    public OpenMapRealMatrix subtract(OpenMapRealMatrix openMapRealMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, openMapRealMatrix);
        OpenMapRealMatrix openMapRealMatrix2 = new OpenMapRealMatrix(this);
        Iterator it = openMapRealMatrix.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key() / this.columns;
            int key2 = it.key() - (this.columns * key);
            openMapRealMatrix2.setEntry(key, key2, getEntry(key, key2) - it.value());
        }
        return openMapRealMatrix2;
    }

    public RealMatrix multiply(RealMatrix realMatrix) throws DimensionMismatchException, NumberIsTooLargeException {
        try {
            return multiply((OpenMapRealMatrix) realMatrix);
        } catch (ClassCastException unused) {
            MatrixUtils.checkMultiplicationCompatible(this, realMatrix);
            int columnDimension = realMatrix.getColumnDimension();
            BlockRealMatrix blockRealMatrix = new BlockRealMatrix(this.rows, columnDimension);
            Iterator it = this.entries.iterator();
            while (it.hasNext()) {
                it.advance();
                double value = it.value();
                int key = it.key();
                int i = key / this.columns;
                int i2 = key % this.columns;
                for (int i3 = 0; i3 < columnDimension; i3++) {
                    blockRealMatrix.addToEntry(i, i3, realMatrix.getEntry(i2, i3) * value);
                }
            }
            return blockRealMatrix;
        }
    }

    public OpenMapRealMatrix multiply(OpenMapRealMatrix openMapRealMatrix) throws DimensionMismatchException, NumberIsTooLargeException {
        MatrixUtils.checkMultiplicationCompatible(this, openMapRealMatrix);
        int columnDimension = openMapRealMatrix.getColumnDimension();
        OpenMapRealMatrix openMapRealMatrix2 = new OpenMapRealMatrix(this.rows, columnDimension);
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            double value = it.value();
            int key = it.key();
            int i = key / this.columns;
            int i2 = key % this.columns;
            for (int i3 = 0; i3 < columnDimension; i3++) {
                int computeKey = openMapRealMatrix.computeKey(i2, i3);
                if (openMapRealMatrix.entries.containsKey(computeKey)) {
                    int computeKey2 = openMapRealMatrix2.computeKey(i, i3);
                    double d = openMapRealMatrix2.entries.get(computeKey2) + (openMapRealMatrix.entries.get(computeKey) * value);
                    if (d == 0.0d) {
                        openMapRealMatrix2.entries.remove(computeKey2);
                    } else {
                        openMapRealMatrix2.entries.put(computeKey2, d);
                    }
                }
            }
        }
        return openMapRealMatrix2;
    }

    public double getEntry(int i, int i2) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        MatrixUtils.checkColumnIndex(this, i2);
        return this.entries.get(computeKey(i, i2));
    }

    public int getRowDimension() {
        return this.rows;
    }

    public void setEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        MatrixUtils.checkColumnIndex(this, i2);
        if (d == 0.0d) {
            this.entries.remove(computeKey(i, i2));
        } else {
            this.entries.put(computeKey(i, i2), d);
        }
    }

    public void addToEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        MatrixUtils.checkColumnIndex(this, i2);
        int computeKey = computeKey(i, i2);
        double d2 = this.entries.get(computeKey) + d;
        if (d2 == 0.0d) {
            this.entries.remove(computeKey);
        } else {
            this.entries.put(computeKey, d2);
        }
    }

    public void multiplyEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        MatrixUtils.checkColumnIndex(this, i2);
        int computeKey = computeKey(i, i2);
        double d2 = this.entries.get(computeKey) * d;
        if (d2 == 0.0d) {
            this.entries.remove(computeKey);
        } else {
            this.entries.put(computeKey, d2);
        }
    }

    private int computeKey(int i, int i2) {
        return (i * this.columns) + i2;
    }
}
