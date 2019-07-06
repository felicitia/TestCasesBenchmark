package org.apache.commons.math3.optim.linear;

import com.etsy.android.ui.dialog.EtsyDialogFragment;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class SimplexTableau implements Serializable {
    private static final double CUTOFF_THRESHOLD = 1.0E-12d;
    private static final int DEFAULT_ULPS = 10;
    private static final String NEGATIVE_VAR_COLUMN_LABEL = "x-";
    private static final long serialVersionUID = -1369660067587938365L;
    private final List<String> columnLabels;
    private final List<LinearConstraint> constraints;
    private final double epsilon;
    private final LinearObjectiveFunction f;
    private final int maxUlps;
    private int numArtificialVariables;
    private final int numDecisionVariables;
    private final int numSlackVariables;
    private final boolean restrictToNonNegative;
    private transient RealMatrix tableau;

    SimplexTableau(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z, double d) {
        this(linearObjectiveFunction, collection, goalType, z, d, 10);
    }

    SimplexTableau(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z, double d, int i) {
        this.columnLabels = new ArrayList();
        this.f = linearObjectiveFunction;
        this.constraints = normalizeConstraints(collection);
        this.restrictToNonNegative = z;
        this.epsilon = d;
        this.maxUlps = i;
        boolean z2 = true;
        this.numDecisionVariables = linearObjectiveFunction.getCoefficients().getDimension() + (z ^ true ? 1 : 0);
        this.numSlackVariables = getConstraintTypeCounts(Relationship.LEQ) + getConstraintTypeCounts(Relationship.GEQ);
        this.numArtificialVariables = getConstraintTypeCounts(Relationship.EQ) + getConstraintTypeCounts(Relationship.GEQ);
        if (goalType != GoalType.MAXIMIZE) {
            z2 = false;
        }
        this.tableau = createTableau(z2);
        initializeColumnLabels();
    }

    /* access modifiers changed from: protected */
    public void initializeColumnLabels() {
        if (getNumObjectiveFunctions() == 2) {
            this.columnLabels.add("W");
        }
        this.columnLabels.add("Z");
        for (int i = 0; i < getOriginalNumDecisionVariables(); i++) {
            List<String> list = this.columnLabels;
            StringBuilder sb = new StringBuilder();
            sb.append(EtsyDialogFragment.OPT_X_BUTTON);
            sb.append(i);
            list.add(sb.toString());
        }
        if (!this.restrictToNonNegative) {
            this.columnLabels.add(NEGATIVE_VAR_COLUMN_LABEL);
        }
        for (int i2 = 0; i2 < getNumSlackVariables(); i2++) {
            List<String> list2 = this.columnLabels;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("s");
            sb2.append(i2);
            list2.add(sb2.toString());
        }
        for (int i3 = 0; i3 < getNumArtificialVariables(); i3++) {
            List<String> list3 = this.columnLabels;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("a");
            sb3.append(i3);
            list3.add(sb3.toString());
        }
        this.columnLabels.add("RHS");
    }

    /* access modifiers changed from: protected */
    public RealMatrix createTableau(boolean z) {
        long j;
        int numObjectiveFunctions = this.numDecisionVariables + this.numSlackVariables + this.numArtificialVariables + getNumObjectiveFunctions() + 1;
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(this.constraints.size() + getNumObjectiveFunctions(), numObjectiveFunctions);
        if (getNumObjectiveFunctions() == 2) {
            array2DRowRealMatrix.setEntry(0, 0, -1.0d);
        }
        int i = getNumObjectiveFunctions() == 1 ? 0 : 1;
        array2DRowRealMatrix.setEntry(i, i, z ? 1.0d : -1.0d);
        RealVector mapMultiply = z ? this.f.getCoefficients().mapMultiply(-1.0d) : this.f.getCoefficients();
        copyArray(mapMultiply.toArray(), array2DRowRealMatrix.getDataRef()[i]);
        int i2 = numObjectiveFunctions - 1;
        array2DRowRealMatrix.setEntry(i, i2, z ? this.f.getConstantTerm() : this.f.getConstantTerm() * -1.0d);
        if (!this.restrictToNonNegative) {
            array2DRowRealMatrix.setEntry(i, getSlackVariableOffset() - 1, getInvertedCoefficientSum(mapMultiply));
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < this.constraints.size()) {
            LinearConstraint linearConstraint = (LinearConstraint) this.constraints.get(i3);
            int numObjectiveFunctions2 = getNumObjectiveFunctions() + i3;
            copyArray(linearConstraint.getCoefficients().toArray(), array2DRowRealMatrix.getDataRef()[numObjectiveFunctions2]);
            if (!this.restrictToNonNegative) {
                array2DRowRealMatrix.setEntry(numObjectiveFunctions2, getSlackVariableOffset() - 1, getInvertedCoefficientSum(linearConstraint.getCoefficients()));
            }
            array2DRowRealMatrix.setEntry(numObjectiveFunctions2, i2, linearConstraint.getValue());
            if (linearConstraint.getRelationship() == Relationship.LEQ) {
                int i6 = i4 + 1;
                array2DRowRealMatrix.setEntry(numObjectiveFunctions2, getSlackVariableOffset() + i4, 1.0d);
                i4 = i6;
            } else if (linearConstraint.getRelationship() == Relationship.GEQ) {
                int i7 = i4 + 1;
                j = -4616189618054758400L;
                array2DRowRealMatrix.setEntry(numObjectiveFunctions2, getSlackVariableOffset() + i4, -1.0d);
                i4 = i7;
                if (linearConstraint.getRelationship() != Relationship.EQ || linearConstraint.getRelationship() == Relationship.GEQ) {
                    array2DRowRealMatrix.setEntry(0, getArtificialVariableOffset() + i5, 1.0d);
                    int i8 = i5 + 1;
                    array2DRowRealMatrix.setEntry(numObjectiveFunctions2, getArtificialVariableOffset() + i5, 1.0d);
                    array2DRowRealMatrix.setRowVector(0, array2DRowRealMatrix.getRowVector(0).subtract(array2DRowRealMatrix.getRowVector(numObjectiveFunctions2)));
                    i5 = i8;
                }
                i3++;
                long j2 = j;
            }
            j = -4616189618054758400L;
            if (linearConstraint.getRelationship() != Relationship.EQ) {
            }
            array2DRowRealMatrix.setEntry(0, getArtificialVariableOffset() + i5, 1.0d);
            int i82 = i5 + 1;
            array2DRowRealMatrix.setEntry(numObjectiveFunctions2, getArtificialVariableOffset() + i5, 1.0d);
            array2DRowRealMatrix.setRowVector(0, array2DRowRealMatrix.getRowVector(0).subtract(array2DRowRealMatrix.getRowVector(numObjectiveFunctions2)));
            i5 = i82;
            i3++;
            long j22 = j;
        }
        return array2DRowRealMatrix;
    }

    public List<LinearConstraint> normalizeConstraints(Collection<LinearConstraint> collection) {
        ArrayList arrayList = new ArrayList();
        for (LinearConstraint normalize : collection) {
            arrayList.add(normalize(normalize));
        }
        return arrayList;
    }

    private LinearConstraint normalize(LinearConstraint linearConstraint) {
        if (linearConstraint.getValue() < 0.0d) {
            return new LinearConstraint(linearConstraint.getCoefficients().mapMultiply(-1.0d), linearConstraint.getRelationship().oppositeRelationship(), -1.0d * linearConstraint.getValue());
        }
        return new LinearConstraint(linearConstraint.getCoefficients(), linearConstraint.getRelationship(), linearConstraint.getValue());
    }

    /* access modifiers changed from: protected */
    public final int getNumObjectiveFunctions() {
        return this.numArtificialVariables > 0 ? 2 : 1;
    }

    private int getConstraintTypeCounts(Relationship relationship) {
        int i = 0;
        for (LinearConstraint relationship2 : this.constraints) {
            if (relationship2.getRelationship() == relationship) {
                i++;
            }
        }
        return i;
    }

    protected static double getInvertedCoefficientSum(RealVector realVector) {
        double d = 0.0d;
        for (double d2 : realVector.toArray()) {
            d -= d2;
        }
        return d;
    }

    /* access modifiers changed from: protected */
    public Integer getBasicRow(int i) {
        Integer num = null;
        for (int i2 = 0; i2 < getHeight(); i2++) {
            double entry = getEntry(i2, i);
            if (Precision.equals(entry, 1.0d, this.maxUlps) && num == null) {
                num = Integer.valueOf(i2);
            } else if (!Precision.equals(entry, 0.0d, this.maxUlps)) {
                return null;
            }
        }
        return num;
    }

    /* access modifiers changed from: protected */
    public void dropPhase1Objective() {
        if (getNumObjectiveFunctions() != 1) {
            TreeSet treeSet = new TreeSet();
            treeSet.add(Integer.valueOf(0));
            for (int numObjectiveFunctions = getNumObjectiveFunctions(); numObjectiveFunctions < getArtificialVariableOffset(); numObjectiveFunctions++) {
                if (Precision.compareTo(this.tableau.getEntry(0, numObjectiveFunctions), 0.0d, this.epsilon) > 0) {
                    treeSet.add(Integer.valueOf(numObjectiveFunctions));
                }
            }
            for (int i = 0; i < getNumArtificialVariables(); i++) {
                int artificialVariableOffset = getArtificialVariableOffset() + i;
                if (getBasicRow(artificialVariableOffset) == null) {
                    treeSet.add(Integer.valueOf(artificialVariableOffset));
                }
            }
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{getHeight() - 1, getWidth() - treeSet.size()});
            for (int i2 = 1; i2 < getHeight(); i2++) {
                int i3 = 0;
                for (int i4 = 0; i4 < getWidth(); i4++) {
                    if (!treeSet.contains(Integer.valueOf(i4))) {
                        int i5 = i3 + 1;
                        dArr[i2 - 1][i3] = this.tableau.getEntry(i2, i4);
                        i3 = i5;
                    }
                }
            }
            Integer[] numArr = (Integer[]) treeSet.toArray(new Integer[treeSet.size()]);
            for (int length = numArr.length - 1; length >= 0; length--) {
                this.columnLabels.remove(numArr[length].intValue());
            }
            this.tableau = new Array2DRowRealMatrix(dArr);
            this.numArtificialVariables = 0;
        }
    }

    private void copyArray(double[] dArr, double[] dArr2) {
        System.arraycopy(dArr, 0, dArr2, getNumObjectiveFunctions(), dArr.length);
    }

    /* access modifiers changed from: 0000 */
    public boolean isOptimal() {
        for (int numObjectiveFunctions = getNumObjectiveFunctions(); numObjectiveFunctions < getWidth() - 1; numObjectiveFunctions++) {
            if (Precision.compareTo(this.tableau.getEntry(0, numObjectiveFunctions), 0.0d, this.epsilon) < 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public PointValuePair getSolution() {
        double d;
        double d2;
        int indexOf = this.columnLabels.indexOf(NEGATIVE_VAR_COLUMN_LABEL);
        Integer basicRow = indexOf > 0 ? getBasicRow(indexOf) : null;
        if (basicRow == null) {
            d = 0.0d;
        } else {
            d = getEntry(basicRow.intValue(), getRhsOffset());
        }
        HashSet hashSet = new HashSet();
        double[] dArr = new double[getOriginalNumDecisionVariables()];
        for (int i = 0; i < dArr.length; i++) {
            List<String> list = this.columnLabels;
            StringBuilder sb = new StringBuilder();
            sb.append(EtsyDialogFragment.OPT_X_BUTTON);
            sb.append(i);
            int indexOf2 = list.indexOf(sb.toString());
            if (indexOf2 < 0) {
                dArr[i] = 0.0d;
            } else {
                Integer basicRow2 = getBasicRow(indexOf2);
                if (basicRow2 != null && basicRow2.intValue() == 0) {
                    dArr[i] = 0.0d;
                } else if (hashSet.contains(basicRow2)) {
                    dArr[i] = 0.0d - (this.restrictToNonNegative ? 0.0d : d);
                } else {
                    hashSet.add(basicRow2);
                    if (basicRow2 == null) {
                        d2 = 0.0d;
                    } else {
                        d2 = getEntry(basicRow2.intValue(), getRhsOffset());
                    }
                    dArr[i] = d2 - (this.restrictToNonNegative ? 0.0d : d);
                }
            }
        }
        return new PointValuePair(dArr, this.f.value(dArr));
    }

    /* access modifiers changed from: protected */
    public void divideRow(int i, double d) {
        for (int i2 = 0; i2 < getWidth(); i2++) {
            this.tableau.setEntry(i, i2, this.tableau.getEntry(i, i2) / d);
        }
    }

    /* access modifiers changed from: protected */
    public void subtractRow(int i, int i2, double d) {
        for (int i3 = 0; i3 < getWidth(); i3++) {
            double entry = this.tableau.getEntry(i, i3) - (this.tableau.getEntry(i2, i3) * d);
            if (FastMath.abs(entry) < 1.0E-12d) {
                entry = 0.0d;
            }
            this.tableau.setEntry(i, i3, entry);
        }
    }

    /* access modifiers changed from: protected */
    public final int getWidth() {
        return this.tableau.getColumnDimension();
    }

    /* access modifiers changed from: protected */
    public final int getHeight() {
        return this.tableau.getRowDimension();
    }

    /* access modifiers changed from: protected */
    public final double getEntry(int i, int i2) {
        return this.tableau.getEntry(i, i2);
    }

    /* access modifiers changed from: protected */
    public final void setEntry(int i, int i2, double d) {
        this.tableau.setEntry(i, i2, d);
    }

    /* access modifiers changed from: protected */
    public final int getSlackVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables;
    }

    /* access modifiers changed from: protected */
    public final int getArtificialVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables + this.numSlackVariables;
    }

    /* access modifiers changed from: protected */
    public final int getRhsOffset() {
        return getWidth() - 1;
    }

    /* access modifiers changed from: protected */
    public final int getNumDecisionVariables() {
        return this.numDecisionVariables;
    }

    /* access modifiers changed from: protected */
    public final int getOriginalNumDecisionVariables() {
        return this.f.getCoefficients().getDimension();
    }

    /* access modifiers changed from: protected */
    public final int getNumSlackVariables() {
        return this.numSlackVariables;
    }

    /* access modifiers changed from: protected */
    public final int getNumArtificialVariables() {
        return this.numArtificialVariables;
    }

    /* access modifiers changed from: protected */
    public final double[][] getData() {
        return this.tableau.getData();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimplexTableau)) {
            return false;
        }
        SimplexTableau simplexTableau = (SimplexTableau) obj;
        if (!(this.restrictToNonNegative == simplexTableau.restrictToNonNegative && this.numDecisionVariables == simplexTableau.numDecisionVariables && this.numSlackVariables == simplexTableau.numSlackVariables && this.numArtificialVariables == simplexTableau.numArtificialVariables && this.epsilon == simplexTableau.epsilon && this.maxUlps == simplexTableau.maxUlps && this.f.equals(simplexTableau.f) && this.constraints.equals(simplexTableau.constraints) && this.tableau.equals(simplexTableau.tableau))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((Boolean.valueOf(this.restrictToNonNegative).hashCode() ^ this.numDecisionVariables) ^ this.numSlackVariables) ^ this.numArtificialVariables) ^ Double.valueOf(this.epsilon).hashCode()) ^ this.maxUlps) ^ this.f.hashCode()) ^ this.constraints.hashCode()) ^ this.tableau.hashCode();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        MatrixUtils.serializeRealMatrix(this.tableau, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        MatrixUtils.deserializeRealMatrix(this, "tableau", objectInputStream);
    }
}
