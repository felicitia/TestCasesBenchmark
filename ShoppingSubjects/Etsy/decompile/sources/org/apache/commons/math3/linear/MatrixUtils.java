package org.apache.commons.math3.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class MatrixUtils {
    public static final RealMatrixFormat DEFAULT_FORMAT = RealMatrixFormat.getInstance();
    public static final RealMatrixFormat OCTAVE_FORMAT;

    private static class BigFractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<BigFraction> {
        private double[][] data;

        public BigFractionMatrixConverter() {
            super(BigFraction.ZERO);
        }

        public void start(int i, int i2, int i3, int i4, int i5, int i6) {
            this.data = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
        }

        public void visit(int i, int i2, BigFraction bigFraction) {
            this.data[i][i2] = bigFraction.doubleValue();
        }

        /* access modifiers changed from: 0000 */
        public Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(this.data, false);
        }
    }

    private static class FractionMatrixConverter extends DefaultFieldMatrixPreservingVisitor<Fraction> {
        private double[][] data;

        public FractionMatrixConverter() {
            super(Fraction.ZERO);
        }

        public void start(int i, int i2, int i3, int i4, int i5, int i6) {
            this.data = (double[][]) Array.newInstance(double.class, new int[]{i, i2});
        }

        public void visit(int i, int i2, Fraction fraction) {
            this.data[i][i2] = fraction.doubleValue();
        }

        /* access modifiers changed from: 0000 */
        public Array2DRowRealMatrix getConvertedMatrix() {
            return new Array2DRowRealMatrix(this.data, false);
        }
    }

    static {
        RealMatrixFormat realMatrixFormat = new RealMatrixFormat("[", "]", "", "", VectorFormat.DEFAULT_SEPARATOR, ", ");
        OCTAVE_FORMAT = realMatrixFormat;
    }

    private MatrixUtils() {
    }

    public static RealMatrix createRealMatrix(int i, int i2) {
        return i * i2 <= 4096 ? new Array2DRowRealMatrix(i, i2) : new BlockRealMatrix(i, i2);
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(Field<T> field, int i, int i2) {
        return i * i2 <= 4096 ? new Array2DRowFieldMatrix(field, i, i2) : new BlockFieldMatrix(field, i, i2);
    }

    public static RealMatrix createRealMatrix(double[][] dArr) throws NullArgumentException, DimensionMismatchException, NoDataException {
        if (dArr != null && dArr[0] != null) {
            return dArr.length * dArr[0].length <= 4096 ? new Array2DRowRealMatrix(dArr) : new BlockRealMatrix(dArr);
        }
        throw new NullArgumentException();
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldMatrix(T[][] tArr) throws DimensionMismatchException, NoDataException, NullArgumentException {
        if (tArr != null && tArr[0] != null) {
            return tArr.length * tArr[0].length <= 4096 ? new Array2DRowFieldMatrix(tArr) : new BlockFieldMatrix(tArr);
        }
        throw new NullArgumentException();
    }

    public static RealMatrix createRealIdentityMatrix(int i) {
        RealMatrix createRealMatrix = createRealMatrix(i, i);
        for (int i2 = 0; i2 < i; i2++) {
            createRealMatrix.setEntry(i2, i2, 1.0d);
        }
        return createRealMatrix;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldIdentityMatrix(Field<T> field, int i) {
        FieldElement fieldElement = (FieldElement) field.getZero();
        FieldElement fieldElement2 = (FieldElement) field.getOne();
        FieldElement[][] fieldElementArr = (FieldElement[][]) Array.newInstance(field.getRuntimeClass(), new int[]{i, i});
        for (int i2 = 0; i2 < i; i2++) {
            FieldElement[] fieldElementArr2 = fieldElementArr[i2];
            Arrays.fill(fieldElementArr2, fieldElement);
            fieldElementArr2[i2] = fieldElement2;
        }
        return new Array2DRowFieldMatrix(field, (T[][]) fieldElementArr, false);
    }

    public static RealMatrix createRealDiagonalMatrix(double[] dArr) {
        RealMatrix createRealMatrix = createRealMatrix(dArr.length, dArr.length);
        for (int i = 0; i < dArr.length; i++) {
            createRealMatrix.setEntry(i, i, dArr[i]);
        }
        return createRealMatrix;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createFieldDiagonalMatrix(T[] tArr) {
        FieldMatrix<T> createFieldMatrix = createFieldMatrix(tArr[0].getField(), tArr.length, tArr.length);
        for (int i = 0; i < tArr.length; i++) {
            createFieldMatrix.setEntry(i, i, tArr[i]);
        }
        return createFieldMatrix;
    }

    public static RealVector createRealVector(double[] dArr) throws NoDataException, NullArgumentException {
        if (dArr != null) {
            return new ArrayRealVector(dArr, true);
        }
        throw new NullArgumentException();
    }

    public static <T extends FieldElement<T>> FieldVector<T> createFieldVector(T[] tArr) throws NoDataException, NullArgumentException, ZeroException {
        if (tArr == null) {
            throw new NullArgumentException();
        } else if (tArr.length != 0) {
            return new ArrayFieldVector(tArr[0].getField(), tArr, true);
        } else {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
    }

    public static RealMatrix createRowRealMatrix(double[] dArr) throws NoDataException, NullArgumentException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int length = dArr.length;
        RealMatrix createRealMatrix = createRealMatrix(1, length);
        for (int i = 0; i < length; i++) {
            createRealMatrix.setEntry(0, i, dArr[i]);
        }
        return createRealMatrix;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createRowFieldMatrix(T[] tArr) throws NoDataException, NullArgumentException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        int length = tArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        FieldMatrix<T> createFieldMatrix = createFieldMatrix(tArr[0].getField(), 1, length);
        for (int i = 0; i < length; i++) {
            createFieldMatrix.setEntry(0, i, tArr[i]);
        }
        return createFieldMatrix;
    }

    public static RealMatrix createColumnRealMatrix(double[] dArr) throws NoDataException, NullArgumentException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int length = dArr.length;
        RealMatrix createRealMatrix = createRealMatrix(length, 1);
        for (int i = 0; i < length; i++) {
            createRealMatrix.setEntry(i, 0, dArr[i]);
        }
        return createRealMatrix;
    }

    public static <T extends FieldElement<T>> FieldMatrix<T> createColumnFieldMatrix(T[] tArr) throws NoDataException, NullArgumentException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        int length = tArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        FieldMatrix<T> createFieldMatrix = createFieldMatrix(tArr[0].getField(), length, 1);
        for (int i = 0; i < length; i++) {
            createFieldMatrix.setEntry(i, 0, tArr[i]);
        }
        return createFieldMatrix;
    }

    private static boolean isSymmetricInternal(RealMatrix realMatrix, double d, boolean z) {
        int rowDimension = realMatrix.getRowDimension();
        if (rowDimension == realMatrix.getColumnDimension()) {
            int i = 0;
            while (i < rowDimension) {
                int i2 = i + 1;
                int i3 = i2;
                while (i3 < rowDimension) {
                    double entry = realMatrix.getEntry(i, i3);
                    double entry2 = realMatrix.getEntry(i3, i);
                    if (FastMath.abs(entry - entry2) <= FastMath.max(FastMath.abs(entry), FastMath.abs(entry2)) * d) {
                        i3++;
                    } else if (!z) {
                        return false;
                    } else {
                        throw new NonSymmetricMatrixException(i, i3, d);
                    }
                }
                i = i2;
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            throw new NonSquareMatrixException(rowDimension, realMatrix.getColumnDimension());
        }
    }

    public static void checkSymmetric(RealMatrix realMatrix, double d) {
        isSymmetricInternal(realMatrix, d, true);
    }

    public static boolean isSymmetric(RealMatrix realMatrix, double d) {
        return isSymmetricInternal(realMatrix, d, false);
    }

    public static void checkMatrixIndex(AnyMatrix anyMatrix, int i, int i2) throws OutOfRangeException {
        checkRowIndex(anyMatrix, i);
        checkColumnIndex(anyMatrix, i2);
    }

    public static void checkRowIndex(AnyMatrix anyMatrix, int i) throws OutOfRangeException {
        if (i < 0 || i >= anyMatrix.getRowDimension()) {
            throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(anyMatrix.getRowDimension() - 1));
        }
    }

    public static void checkColumnIndex(AnyMatrix anyMatrix, int i) throws OutOfRangeException {
        if (i < 0 || i >= anyMatrix.getColumnDimension()) {
            throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(anyMatrix.getColumnDimension() - 1));
        }
    }

    public static void checkSubMatrixIndex(AnyMatrix anyMatrix, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkRowIndex(anyMatrix, i);
        checkRowIndex(anyMatrix, i2);
        if (i2 < i) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(i2), Integer.valueOf(i), false);
        }
        checkColumnIndex(anyMatrix, i3);
        checkColumnIndex(anyMatrix, i4);
        if (i4 < i3) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, Integer.valueOf(i4), Integer.valueOf(i3), false);
        }
    }

    public static void checkSubMatrixIndex(AnyMatrix anyMatrix, int[] iArr, int[] iArr2) throws NoDataException, NullArgumentException, OutOfRangeException {
        if (iArr == null) {
            throw new NullArgumentException();
        } else if (iArr2 == null) {
            throw new NullArgumentException();
        } else if (iArr.length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_ROW_INDEX_ARRAY);
        } else if (iArr2.length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_SELECTED_COLUMN_INDEX_ARRAY);
        } else {
            for (int checkRowIndex : iArr) {
                checkRowIndex(anyMatrix, checkRowIndex);
            }
            for (int checkColumnIndex : iArr2) {
                checkColumnIndex(anyMatrix, checkColumnIndex);
            }
        }
    }

    public static void checkAdditionCompatible(AnyMatrix anyMatrix, AnyMatrix anyMatrix2) throws MatrixDimensionMismatchException {
        if (anyMatrix.getRowDimension() != anyMatrix2.getRowDimension() || anyMatrix.getColumnDimension() != anyMatrix2.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(anyMatrix.getRowDimension(), anyMatrix.getColumnDimension(), anyMatrix2.getRowDimension(), anyMatrix2.getColumnDimension());
        }
    }

    public static void checkSubtractionCompatible(AnyMatrix anyMatrix, AnyMatrix anyMatrix2) throws MatrixDimensionMismatchException {
        if (anyMatrix.getRowDimension() != anyMatrix2.getRowDimension() || anyMatrix.getColumnDimension() != anyMatrix2.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(anyMatrix.getRowDimension(), anyMatrix.getColumnDimension(), anyMatrix2.getRowDimension(), anyMatrix2.getColumnDimension());
        }
    }

    public static void checkMultiplicationCompatible(AnyMatrix anyMatrix, AnyMatrix anyMatrix2) throws DimensionMismatchException {
        if (anyMatrix.getColumnDimension() != anyMatrix2.getRowDimension()) {
            throw new DimensionMismatchException(anyMatrix.getColumnDimension(), anyMatrix2.getRowDimension());
        }
    }

    public static Array2DRowRealMatrix fractionMatrixToRealMatrix(FieldMatrix<Fraction> fieldMatrix) {
        FractionMatrixConverter fractionMatrixConverter = new FractionMatrixConverter();
        fieldMatrix.walkInOptimizedOrder((FieldMatrixPreservingVisitor<T>) fractionMatrixConverter);
        return fractionMatrixConverter.getConvertedMatrix();
    }

    public static Array2DRowRealMatrix bigFractionMatrixToRealMatrix(FieldMatrix<BigFraction> fieldMatrix) {
        BigFractionMatrixConverter bigFractionMatrixConverter = new BigFractionMatrixConverter();
        fieldMatrix.walkInOptimizedOrder((FieldMatrixPreservingVisitor<T>) bigFractionMatrixConverter);
        return bigFractionMatrixConverter.getConvertedMatrix();
    }

    public static void serializeRealVector(RealVector realVector, ObjectOutputStream objectOutputStream) throws IOException {
        int dimension = realVector.getDimension();
        objectOutputStream.writeInt(dimension);
        for (int i = 0; i < dimension; i++) {
            objectOutputStream.writeDouble(realVector.getEntry(i));
        }
    }

    public static void deserializeRealVector(Object obj, String str, ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        try {
            int readInt = objectInputStream.readInt();
            double[] dArr = new double[readInt];
            for (int i = 0; i < readInt; i++) {
                dArr[i] = objectInputStream.readDouble();
            }
            ArrayRealVector arrayRealVector = new ArrayRealVector(dArr, false);
            java.lang.reflect.Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, arrayRealVector);
        } catch (NoSuchFieldException e) {
            IOException iOException = new IOException();
            iOException.initCause(e);
            throw iOException;
        } catch (IllegalAccessException e2) {
            IOException iOException2 = new IOException();
            iOException2.initCause(e2);
            throw iOException2;
        }
    }

    public static void serializeRealMatrix(RealMatrix realMatrix, ObjectOutputStream objectOutputStream) throws IOException {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        objectOutputStream.writeInt(rowDimension);
        objectOutputStream.writeInt(columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                objectOutputStream.writeDouble(realMatrix.getEntry(i, i2));
            }
        }
    }

    public static void deserializeRealMatrix(Object obj, String str, ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        try {
            int readInt = objectInputStream.readInt();
            int readInt2 = objectInputStream.readInt();
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{readInt, readInt2});
            for (int i = 0; i < readInt; i++) {
                double[] dArr2 = dArr[i];
                for (int i2 = 0; i2 < readInt2; i2++) {
                    dArr2[i2] = objectInputStream.readDouble();
                }
            }
            Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr, false);
            java.lang.reflect.Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, array2DRowRealMatrix);
        } catch (NoSuchFieldException e) {
            IOException iOException = new IOException();
            iOException.initCause(e);
            throw iOException;
        } catch (IllegalAccessException e2) {
            IOException iOException2 = new IOException();
            iOException2.initCause(e2);
            throw iOException2;
        }
    }

    public static void solveLowerTriangularSystem(RealMatrix realMatrix, RealVector realVector) throws DimensionMismatchException, MathArithmeticException, NonSquareMatrixException {
        int i = 0;
        if (realMatrix == null || realVector == null || realMatrix.getRowDimension() != realVector.getDimension()) {
            int rowDimension = realMatrix == null ? 0 : realMatrix.getRowDimension();
            if (realVector != null) {
                i = realVector.getDimension();
            }
            throw new DimensionMismatchException(rowDimension, i);
        } else if (realMatrix.getColumnDimension() != realMatrix.getRowDimension()) {
            throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        } else {
            int rowDimension2 = realMatrix.getRowDimension();
            int i2 = 0;
            while (i2 < rowDimension2) {
                double entry = realMatrix.getEntry(i2, i2);
                if (FastMath.abs(entry) < Precision.SAFE_MIN) {
                    throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
                }
                double entry2 = realVector.getEntry(i2) / entry;
                realVector.setEntry(i2, entry2);
                int i3 = i2 + 1;
                for (int i4 = i3; i4 < rowDimension2; i4++) {
                    realVector.setEntry(i4, realVector.getEntry(i4) - (realMatrix.getEntry(i4, i2) * entry2));
                }
                i2 = i3;
            }
        }
    }

    public static void solveUpperTriangularSystem(RealMatrix realMatrix, RealVector realVector) throws DimensionMismatchException, MathArithmeticException, NonSquareMatrixException {
        int i = 0;
        if (realMatrix == null || realVector == null || realMatrix.getRowDimension() != realVector.getDimension()) {
            int rowDimension = realMatrix == null ? 0 : realMatrix.getRowDimension();
            if (realVector != null) {
                i = realVector.getDimension();
            }
            throw new DimensionMismatchException(rowDimension, i);
        } else if (realMatrix.getColumnDimension() != realMatrix.getRowDimension()) {
            throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        } else {
            int rowDimension2 = realMatrix.getRowDimension();
            while (true) {
                rowDimension2--;
                if (rowDimension2 > -1) {
                    double entry = realMatrix.getEntry(rowDimension2, rowDimension2);
                    if (FastMath.abs(entry) < Precision.SAFE_MIN) {
                        throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
                    }
                    double entry2 = realVector.getEntry(rowDimension2) / entry;
                    realVector.setEntry(rowDimension2, entry2);
                    for (int i2 = rowDimension2 - 1; i2 > -1; i2--) {
                        realVector.setEntry(i2, realVector.getEntry(i2) - (realMatrix.getEntry(i2, rowDimension2) * entry2));
                    }
                } else {
                    return;
                }
            }
        }
    }

    public static RealMatrix blockInverse(RealMatrix realMatrix, int i) {
        int rowDimension = realMatrix.getRowDimension();
        if (realMatrix.getColumnDimension() != rowDimension) {
            throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        }
        int i2 = i + 1;
        RealMatrix subMatrix = realMatrix.getSubMatrix(0, i, 0, i);
        int i3 = rowDimension - 1;
        RealMatrix subMatrix2 = realMatrix.getSubMatrix(0, i, i2, i3);
        RealMatrix subMatrix3 = realMatrix.getSubMatrix(i2, i3, 0, i);
        RealMatrix subMatrix4 = realMatrix.getSubMatrix(i2, i3, i2, i3);
        RealMatrix inverse = new SingularValueDecomposition(subMatrix).getSolver().getInverse();
        RealMatrix inverse2 = new SingularValueDecomposition(subMatrix4).getSolver().getInverse();
        RealMatrix inverse3 = new SingularValueDecomposition(subMatrix.subtract(subMatrix2.multiply(inverse2).multiply(subMatrix3))).getSolver().getInverse();
        RealMatrix inverse4 = new SingularValueDecomposition(subMatrix4.subtract(subMatrix3.multiply(inverse).multiply(subMatrix2))).getSolver().getInverse();
        RealMatrix scalarMultiply = inverse.multiply(subMatrix2).multiply(inverse4).scalarMultiply(-1.0d);
        RealMatrix scalarMultiply2 = inverse2.multiply(subMatrix3).multiply(inverse3).scalarMultiply(-1.0d);
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(rowDimension, rowDimension);
        array2DRowRealMatrix.setSubMatrix(inverse3.getData(), 0, 0);
        array2DRowRealMatrix.setSubMatrix(scalarMultiply.getData(), 0, i2);
        array2DRowRealMatrix.setSubMatrix(scalarMultiply2.getData(), i2, 0);
        array2DRowRealMatrix.setSubMatrix(inverse4.getData(), i2, i2);
        return array2DRowRealMatrix;
    }
}
