package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public abstract class AbstractRealMatrix extends RealLinearOperator implements RealMatrix {
    private static final RealMatrixFormat DEFAULT_FORMAT = RealMatrixFormat.getInstance(Locale.US);

    public abstract RealMatrix copy();

    public abstract RealMatrix createMatrix(int i, int i2) throws NotStrictlyPositiveException;

    public abstract int getColumnDimension();

    public abstract double getEntry(int i, int i2) throws OutOfRangeException;

    public abstract int getRowDimension();

    public abstract void setEntry(int i, int i2, double d) throws OutOfRangeException;

    static {
        DEFAULT_FORMAT.getFormat().setMinimumFractionDigits(1);
    }

    protected AbstractRealMatrix() {
    }

    protected AbstractRealMatrix(int i, int i2) throws NotStrictlyPositiveException {
        if (i < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else if (i2 < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i2));
        }
    }

    public RealMatrix add(RealMatrix realMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, realMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        RealMatrix createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, getEntry(i, i2) + realMatrix.getEntry(i, i2));
            }
        }
        return createMatrix;
    }

    public RealMatrix subtract(RealMatrix realMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, realMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        RealMatrix createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, getEntry(i, i2) - realMatrix.getEntry(i, i2));
            }
        }
        return createMatrix;
    }

    public RealMatrix scalarAdd(double d) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        RealMatrix createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, getEntry(i, i2) + d);
            }
        }
        return createMatrix;
    }

    public RealMatrix scalarMultiply(double d) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        RealMatrix createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, getEntry(i, i2) * d);
            }
        }
        return createMatrix;
    }

    public RealMatrix multiply(RealMatrix realMatrix) throws DimensionMismatchException {
        MatrixUtils.checkMultiplicationCompatible(this, realMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        int columnDimension2 = getColumnDimension();
        RealMatrix createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                double d = 0.0d;
                for (int i3 = 0; i3 < columnDimension2; i3++) {
                    d += getEntry(i, i3) * realMatrix.getEntry(i3, i2);
                }
                createMatrix.setEntry(i, i2, d);
            }
        }
        return createMatrix;
    }

    public RealMatrix preMultiply(RealMatrix realMatrix) throws DimensionMismatchException {
        return realMatrix.multiply(this);
    }

    public RealMatrix power(int i) throws NotPositiveException, NonSquareMatrixException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.NOT_POSITIVE_EXPONENT, Integer.valueOf(i));
        } else if (!isSquare()) {
            throw new NonSquareMatrixException(getRowDimension(), getColumnDimension());
        } else if (i == 0) {
            return MatrixUtils.createRealIdentityMatrix(getRowDimension());
        } else {
            if (i == 1) {
                return copy();
            }
            char[] charArray = Integer.toBinaryString(i - 1).toCharArray();
            ArrayList arrayList = new ArrayList();
            int i2 = -1;
            for (int i3 = 0; i3 < charArray.length; i3++) {
                if (charArray[i3] == '1') {
                    int length = (charArray.length - i3) - 1;
                    arrayList.add(Integer.valueOf(length));
                    if (i2 == -1) {
                        i2 = length;
                    }
                }
            }
            RealMatrix[] realMatrixArr = new RealMatrix[(i2 + 1)];
            realMatrixArr[0] = copy();
            for (int i4 = 1; i4 <= i2; i4++) {
                int i5 = i4 - 1;
                realMatrixArr[i4] = realMatrixArr[i5].multiply(realMatrixArr[i5]);
            }
            RealMatrix copy = copy();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                copy = copy.multiply(realMatrixArr[((Integer) it.next()).intValue()]);
            }
            return copy;
        }
    }

    public double[][] getData() {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{getRowDimension(), getColumnDimension()});
        for (int i = 0; i < dArr.length; i++) {
            double[] dArr2 = dArr[i];
            for (int i2 = 0; i2 < dArr2.length; i2++) {
                dArr2[i2] = getEntry(i, i2);
            }
        }
        return dArr;
    }

    public double getNorm() {
        return walkInColumnOrder((RealMatrixPreservingVisitor) new RealMatrixPreservingVisitor() {
            private double columnSum;
            private double endRow;
            private double maxColSum;

            public void start(int i, int i2, int i3, int i4, int i5, int i6) {
                this.endRow = (double) i4;
                this.columnSum = 0.0d;
                this.maxColSum = 0.0d;
            }

            public void visit(int i, int i2, double d) {
                this.columnSum += FastMath.abs(d);
                if (((double) i) == this.endRow) {
                    this.maxColSum = FastMath.max(this.maxColSum, this.columnSum);
                    this.columnSum = 0.0d;
                }
            }

            public double end() {
                return this.maxColSum;
            }
        });
    }

    public double getFrobeniusNorm() {
        return walkInOptimizedOrder((RealMatrixPreservingVisitor) new RealMatrixPreservingVisitor() {
            private double sum;

            public void start(int i, int i2, int i3, int i4, int i5, int i6) {
                this.sum = 0.0d;
            }

            public void visit(int i, int i2, double d) {
                this.sum += d * d;
            }

            public double end() {
                return FastMath.sqrt(this.sum);
            }
        });
    }

    public RealMatrix getSubMatrix(int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        RealMatrix createMatrix = createMatrix((i2 - i) + 1, (i4 - i3) + 1);
        for (int i5 = i; i5 <= i2; i5++) {
            for (int i6 = i3; i6 <= i4; i6++) {
                createMatrix.setEntry(i5 - i, i6 - i3, getEntry(i5, i6));
            }
        }
        return createMatrix;
    }

    public RealMatrix getSubMatrix(final int[] iArr, final int[] iArr2) throws NullArgumentException, NoDataException, OutOfRangeException {
        MatrixUtils.checkSubMatrixIndex(this, iArr, iArr2);
        RealMatrix createMatrix = createMatrix(iArr.length, iArr2.length);
        createMatrix.walkInOptimizedOrder((RealMatrixChangingVisitor) new DefaultRealMatrixChangingVisitor() {
            public double visit(int i, int i2, double d) {
                return AbstractRealMatrix.this.getEntry(iArr[i], iArr2[i2]);
            }
        });
        return createMatrix;
    }

    public void copySubMatrix(int i, int i2, int i3, int i4, final double[][] dArr) throws OutOfRangeException, NumberIsTooSmallException, MatrixDimensionMismatchException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        int i5 = (i2 + 1) - i;
        int i6 = (i4 + 1) - i3;
        if (dArr.length < i5 || dArr[0].length < i6) {
            throw new MatrixDimensionMismatchException(dArr.length, dArr[0].length, i5, i6);
        }
        walkInOptimizedOrder((RealMatrixPreservingVisitor) new DefaultRealMatrixPreservingVisitor() {
            private int startColumn;
            private int startRow;

            public void start(int i, int i2, int i3, int i4, int i5, int i6) {
                this.startRow = i3;
                this.startColumn = i5;
            }

            public void visit(int i, int i2, double d) {
                dArr[i - this.startRow][i2 - this.startColumn] = d;
            }
        }, i, i2, i3, i4);
    }

    public void copySubMatrix(int[] iArr, int[] iArr2, double[][] dArr) throws OutOfRangeException, NullArgumentException, NoDataException, MatrixDimensionMismatchException {
        MatrixUtils.checkSubMatrixIndex(this, iArr, iArr2);
        if (dArr.length < iArr.length || dArr[0].length < iArr2.length) {
            throw new MatrixDimensionMismatchException(dArr.length, dArr[0].length, iArr.length, iArr2.length);
        }
        for (int i = 0; i < iArr.length; i++) {
            double[] dArr2 = dArr[i];
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                dArr2[i2] = getEntry(iArr[i], iArr2[i2]);
            }
        }
    }

    public void setSubMatrix(double[][] dArr, int i, int i2) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException {
        MathUtils.checkNotNull(dArr);
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        int length2 = dArr[0].length;
        if (length2 == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int i3 = 1; i3 < length; i3++) {
            if (dArr[i3].length != length2) {
                throw new DimensionMismatchException(length2, dArr[i3].length);
            }
        }
        MatrixUtils.checkRowIndex(this, i);
        MatrixUtils.checkColumnIndex(this, i2);
        MatrixUtils.checkRowIndex(this, (length + i) - 1);
        MatrixUtils.checkColumnIndex(this, (length2 + i2) - 1);
        for (int i4 = 0; i4 < length; i4++) {
            for (int i5 = 0; i5 < length2; i5++) {
                setEntry(i + i4, i2 + i5, dArr[i4][i5]);
            }
        }
    }

    public RealMatrix getRowMatrix(int i) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        int columnDimension = getColumnDimension();
        RealMatrix createMatrix = createMatrix(1, columnDimension);
        for (int i2 = 0; i2 < columnDimension; i2++) {
            createMatrix.setEntry(0, i2, getEntry(i, i2));
        }
        return createMatrix;
    }

    public void setRowMatrix(int i, RealMatrix realMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, i);
        int columnDimension = getColumnDimension();
        if (realMatrix.getRowDimension() == 1 && realMatrix.getColumnDimension() == columnDimension) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                setEntry(i, i2, realMatrix.getEntry(0, i2));
            }
            return;
        }
        throw new MatrixDimensionMismatchException(realMatrix.getRowDimension(), realMatrix.getColumnDimension(), 1, columnDimension);
    }

    public RealMatrix getColumnMatrix(int i) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, i);
        int rowDimension = getRowDimension();
        RealMatrix createMatrix = createMatrix(rowDimension, 1);
        for (int i2 = 0; i2 < rowDimension; i2++) {
            createMatrix.setEntry(i2, 0, getEntry(i2, i));
        }
        return createMatrix;
    }

    public void setColumnMatrix(int i, RealMatrix realMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, i);
        int rowDimension = getRowDimension();
        if (realMatrix.getRowDimension() == rowDimension && realMatrix.getColumnDimension() == 1) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                setEntry(i2, i, realMatrix.getEntry(i2, 0));
            }
            return;
        }
        throw new MatrixDimensionMismatchException(realMatrix.getRowDimension(), realMatrix.getColumnDimension(), rowDimension, 1);
    }

    public RealVector getRowVector(int i) throws OutOfRangeException {
        return new ArrayRealVector(getRow(i), false);
    }

    public void setRowVector(int i, RealVector realVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, i);
        int columnDimension = getColumnDimension();
        if (realVector.getDimension() != columnDimension) {
            throw new MatrixDimensionMismatchException(1, realVector.getDimension(), 1, columnDimension);
        }
        for (int i2 = 0; i2 < columnDimension; i2++) {
            setEntry(i, i2, realVector.getEntry(i2));
        }
    }

    public RealVector getColumnVector(int i) throws OutOfRangeException {
        return new ArrayRealVector(getColumn(i), false);
    }

    public void setColumnVector(int i, RealVector realVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, i);
        int rowDimension = getRowDimension();
        if (realVector.getDimension() != rowDimension) {
            throw new MatrixDimensionMismatchException(realVector.getDimension(), 1, rowDimension, 1);
        }
        for (int i2 = 0; i2 < rowDimension; i2++) {
            setEntry(i2, i, realVector.getEntry(i2));
        }
    }

    public double[] getRow(int i) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        int columnDimension = getColumnDimension();
        double[] dArr = new double[columnDimension];
        for (int i2 = 0; i2 < columnDimension; i2++) {
            dArr[i2] = getEntry(i, i2);
        }
        return dArr;
    }

    public void setRow(int i, double[] dArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, i);
        int columnDimension = getColumnDimension();
        if (dArr.length != columnDimension) {
            throw new MatrixDimensionMismatchException(1, dArr.length, 1, columnDimension);
        }
        for (int i2 = 0; i2 < columnDimension; i2++) {
            setEntry(i, i2, dArr[i2]);
        }
    }

    public double[] getColumn(int i) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, i);
        int rowDimension = getRowDimension();
        double[] dArr = new double[rowDimension];
        for (int i2 = 0; i2 < rowDimension; i2++) {
            dArr[i2] = getEntry(i2, i);
        }
        return dArr;
    }

    public void setColumn(int i, double[] dArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, i);
        int rowDimension = getRowDimension();
        if (dArr.length != rowDimension) {
            throw new MatrixDimensionMismatchException(dArr.length, 1, rowDimension, 1);
        }
        for (int i2 = 0; i2 < rowDimension; i2++) {
            setEntry(i2, i, dArr[i2]);
        }
    }

    public void addToEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        setEntry(i, i2, getEntry(i, i2) + d);
    }

    public void multiplyEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        setEntry(i, i2, getEntry(i, i2) * d);
    }

    public RealMatrix transpose() {
        final RealMatrix createMatrix = createMatrix(getColumnDimension(), getRowDimension());
        walkInOptimizedOrder((RealMatrixPreservingVisitor) new DefaultRealMatrixPreservingVisitor() {
            public void visit(int i, int i2, double d) {
                createMatrix.setEntry(i2, i, d);
            }
        });
        return createMatrix;
    }

    public boolean isSquare() {
        return getColumnDimension() == getRowDimension();
    }

    public double getTrace() throws NonSquareMatrixException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (rowDimension != columnDimension) {
            throw new NonSquareMatrixException(rowDimension, columnDimension);
        }
        double d = 0.0d;
        for (int i = 0; i < rowDimension; i++) {
            d += getEntry(i, i);
        }
        return d;
    }

    public double[] operate(double[] dArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (dArr.length != columnDimension) {
            throw new DimensionMismatchException(dArr.length, columnDimension);
        }
        double[] dArr2 = new double[rowDimension];
        for (int i = 0; i < rowDimension; i++) {
            double d = 0.0d;
            for (int i2 = 0; i2 < columnDimension; i2++) {
                d += getEntry(i, i2) * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }

    public RealVector operate(RealVector realVector) throws DimensionMismatchException {
        try {
            return new ArrayRealVector(operate(((ArrayRealVector) realVector).getDataRef()), false);
        } catch (ClassCastException unused) {
            int rowDimension = getRowDimension();
            int columnDimension = getColumnDimension();
            if (realVector.getDimension() != columnDimension) {
                throw new DimensionMismatchException(realVector.getDimension(), columnDimension);
            }
            double[] dArr = new double[rowDimension];
            for (int i = 0; i < rowDimension; i++) {
                double d = 0.0d;
                for (int i2 = 0; i2 < columnDimension; i2++) {
                    d += getEntry(i, i2) * realVector.getEntry(i2);
                }
                dArr[i] = d;
            }
            return new ArrayRealVector(dArr, false);
        }
    }

    public double[] preMultiply(double[] dArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (dArr.length != rowDimension) {
            throw new DimensionMismatchException(dArr.length, rowDimension);
        }
        double[] dArr2 = new double[columnDimension];
        for (int i = 0; i < columnDimension; i++) {
            double d = 0.0d;
            for (int i2 = 0; i2 < rowDimension; i2++) {
                d += getEntry(i2, i) * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }

    public RealVector preMultiply(RealVector realVector) throws DimensionMismatchException {
        try {
            return new ArrayRealVector(preMultiply(((ArrayRealVector) realVector).getDataRef()), false);
        } catch (ClassCastException unused) {
            int rowDimension = getRowDimension();
            int columnDimension = getColumnDimension();
            if (realVector.getDimension() != rowDimension) {
                throw new DimensionMismatchException(realVector.getDimension(), rowDimension);
            }
            double[] dArr = new double[columnDimension];
            for (int i = 0; i < columnDimension; i++) {
                double d = 0.0d;
                for (int i2 = 0; i2 < rowDimension; i2++) {
                    d += getEntry(i2, i) * realVector.getEntry(i2);
                }
                dArr[i] = d;
            }
            return new ArrayRealVector(dArr, false);
        }
    }

    public double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                setEntry(i, i2, realMatrixChangingVisitor.visit(i, i2, getEntry(i, i2)));
            }
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                realMatrixPreservingVisitor.visit(i, i2, getEntry(i, i2));
            }
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            for (int i5 = i3; i5 <= i4; i5++) {
                setEntry(i, i5, realMatrixChangingVisitor.visit(i, i5, getEntry(i, i5)));
            }
            i++;
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            for (int i5 = i3; i5 <= i4; i5++) {
                realMatrixPreservingVisitor.visit(i, i5, getEntry(i, i5));
            }
            i++;
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                setEntry(i2, i, realMatrixChangingVisitor.visit(i2, i, getEntry(i2, i)));
            }
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                realMatrixPreservingVisitor.visit(i2, i, getEntry(i2, i));
            }
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                setEntry(i5, i3, realMatrixChangingVisitor.visit(i5, i3, getEntry(i5, i3)));
            }
            i3++;
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                realMatrixPreservingVisitor.visit(i5, i3, getEntry(i5, i3));
            }
            i3++;
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) {
        return walkInRowOrder(realMatrixChangingVisitor);
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) {
        return walkInRowOrder(realMatrixPreservingVisitor);
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        return walkInRowOrder(realMatrixChangingVisitor, i, i2, i3, i4);
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        return walkInRowOrder(realMatrixPreservingVisitor, i, i2, i3, i4);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String name = getClass().getName();
        sb.append(name.substring(name.lastIndexOf(46) + 1));
        sb.append(DEFAULT_FORMAT.format(this));
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RealMatrix)) {
            return false;
        }
        RealMatrix realMatrix = (RealMatrix) obj;
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (realMatrix.getColumnDimension() != columnDimension || realMatrix.getRowDimension() != rowDimension) {
            return false;
        }
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                if (getEntry(i, i2) != realMatrix.getEntry(i, i2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        int i = ((217 + rowDimension) * 31) + columnDimension;
        int i2 = 0;
        while (i2 < rowDimension) {
            int i3 = i;
            int i4 = 0;
            while (i4 < columnDimension) {
                int i5 = i4 + 1;
                i3 = (i3 * 31) + (((11 * (i2 + 1)) + (17 * i5)) * MathUtils.hash(getEntry(i2, i4)));
                i4 = i5;
            }
            i2++;
            i = i3;
        }
        return i;
    }
}
