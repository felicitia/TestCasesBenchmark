package org.apache.commons.math3.optimization.general;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {
    private double[] beta;
    private final double costRelativeTolerance;
    private double[] diagR;
    private final double initialStepBoundFactor;
    private double[] jacNorm;
    private double[] lmDir;
    private double lmPar;
    private final double orthoTolerance;
    private final double parRelativeTolerance;
    private int[] permutation;
    private final double qrRankingThreshold;
    private int rank;
    private int solvedCols;
    private double[][] weightedJacobian;
    private double[] weightedResidual;

    public LevenbergMarquardtOptimizer() {
        this(100.0d, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        this(100.0d, convergenceChecker, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, ConvergenceChecker<PointVectorValuePair> convergenceChecker, double d2, double d3, double d4, double d5) {
        super(convergenceChecker);
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3) {
        this(100.0d, d, d2, d3, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3, double d4, double d5) {
        super(null);
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    /* access modifiers changed from: protected */
    public PointVectorValuePair doOptimize() {
        double d;
        ConvergenceChecker convergenceChecker;
        int i;
        boolean z;
        double d2;
        double d3;
        double d4;
        PointVectorValuePair pointVectorValuePair;
        ConvergenceChecker convergenceChecker2;
        ConvergenceChecker convergenceChecker3;
        int i2;
        int length = getTarget().length;
        double[] startPoint = getStartPoint();
        int length2 = startPoint.length;
        this.solvedCols = FastMath.min(length, length2);
        this.diagR = new double[length2];
        this.jacNorm = new double[length2];
        this.beta = new double[length2];
        this.permutation = new int[length2];
        this.lmDir = new double[length2];
        double[] dArr = new double[length2];
        double[] dArr2 = new double[length2];
        double[] dArr3 = new double[length];
        double[] dArr4 = new double[length];
        double[] dArr5 = new double[length];
        double[] dArr6 = new double[length2];
        double[] dArr7 = new double[length2];
        double[] dArr8 = new double[length2];
        RealMatrix weightSquareRoot = getWeightSquareRoot();
        double[] computeObjectiveValue = computeObjectiveValue(startPoint);
        double[] computeResiduals = computeResiduals(computeObjectiveValue);
        PointVectorValuePair pointVectorValuePair2 = new PointVectorValuePair(startPoint, computeObjectiveValue);
        double computeCost = computeCost(computeResiduals);
        double[] dArr9 = dArr7;
        double[] dArr10 = dArr8;
        this.lmPar = 0.0d;
        ConvergenceChecker convergenceChecker4 = getConvergenceChecker();
        double[] dArr11 = dArr6;
        double d5 = computeCost;
        boolean z2 = true;
        double d6 = 0.0d;
        double d7 = 0.0d;
        double[] dArr12 = computeObjectiveValue;
        double[] dArr13 = dArr3;
        int i3 = 0;
        loop0:
        while (true) {
            int i4 = i3 + 1;
            qrDecomposition(computeWeightedJacobian(startPoint));
            this.weightedResidual = weightSquareRoot.operate(computeResiduals);
            int i5 = 0;
            while (i5 < length) {
                RealMatrix realMatrix = weightSquareRoot;
                dArr5[i5] = this.weightedResidual[i5];
                i5++;
                weightSquareRoot = realMatrix;
            }
            RealMatrix realMatrix2 = weightSquareRoot;
            qTy(dArr5);
            int i6 = 0;
            while (i6 < this.solvedCols) {
                int i7 = this.permutation[i6];
                double[] dArr14 = dArr13;
                double[] dArr15 = computeResiduals;
                this.weightedJacobian[i6][i7] = this.diagR[i7];
                i6++;
                dArr13 = dArr14;
                computeResiduals = dArr15;
            }
            double[] dArr16 = dArr13;
            double[] dArr17 = computeResiduals;
            if (z2) {
                double d8 = 0.0d;
                for (int i8 = 0; i8 < length2; i8++) {
                    double d9 = this.jacNorm[i8];
                    if (d9 == 0.0d) {
                        d9 = 1.0d;
                    }
                    double d10 = startPoint[i8] * d9;
                    d8 += d10 * d10;
                    dArr[i8] = d9;
                }
                d7 = FastMath.sqrt(d8);
                d = 0.0d;
                d6 = d7 == 0.0d ? this.initialStepBoundFactor : this.initialStepBoundFactor * d7;
            } else {
                d = 0.0d;
            }
            if (d5 != d) {
                double d11 = d;
                int i9 = 0;
                while (i9 < this.solvedCols) {
                    int i10 = this.permutation[i9];
                    boolean z3 = z2;
                    double d12 = this.jacNorm[i10];
                    if (d12 != d) {
                        int i11 = 0;
                        double d13 = 0.0d;
                        while (i11 <= i9) {
                            d13 += this.weightedJacobian[i11][i10] * dArr5[i11];
                            i11++;
                            length = length;
                        }
                        i2 = length;
                        convergenceChecker3 = convergenceChecker4;
                        d11 = FastMath.max(d11, FastMath.abs(d13) / (d12 * d5));
                    } else {
                        convergenceChecker3 = convergenceChecker4;
                        i2 = length;
                        double d14 = d11;
                    }
                    i9++;
                    z2 = z3;
                    length = i2;
                    convergenceChecker4 = convergenceChecker3;
                    d = 0.0d;
                }
                z = z2;
                convergenceChecker = convergenceChecker4;
                i = length;
                d2 = d11;
            } else {
                z = z2;
                convergenceChecker = convergenceChecker4;
                i = length;
                d2 = 0.0d;
            }
            if (d2 <= this.orthoTolerance) {
                setCost(d5);
                this.point = pointVectorValuePair2.getPoint();
                return pointVectorValuePair2;
            }
            int i12 = 0;
            while (i12 < length2) {
                double d15 = d5;
                dArr[i12] = FastMath.max(dArr[i12], this.jacNorm[i12]);
                i12++;
                d5 = d15;
            }
            double d16 = d5;
            PointVectorValuePair pointVectorValuePair3 = pointVectorValuePair2;
            double[] dArr18 = dArr12;
            double d17 = d6;
            dArr13 = dArr16;
            computeResiduals = dArr17;
            double d18 = 0.0d;
            while (d18 < 1.0E-4d) {
                for (int i13 = 0; i13 < this.solvedCols; i13++) {
                    int i14 = this.permutation[i13];
                    dArr2[i14] = startPoint[i14];
                }
                double[] dArr19 = this.weightedResidual;
                this.weightedResidual = dArr13;
                int i15 = i4;
                RealMatrix realMatrix3 = realMatrix2;
                double d19 = d2;
                double[] dArr20 = dArr18;
                double[] dArr21 = dArr5;
                double[] dArr22 = dArr2;
                double[] dArr23 = dArr;
                determineLMParameter(dArr5, d17, dArr, dArr11, dArr9, dArr10);
                double d20 = 0.0d;
                for (int i16 = 0; i16 < this.solvedCols; i16++) {
                    int i17 = this.permutation[i16];
                    this.lmDir[i17] = -this.lmDir[i17];
                    startPoint[i17] = dArr22[i17] + this.lmDir[i17];
                    double d21 = dArr23[i17] * this.lmDir[i17];
                    d20 += d21 * d21;
                }
                double sqrt = FastMath.sqrt(d20);
                if (z) {
                    d17 = FastMath.min(d17, sqrt);
                }
                double d22 = d17;
                double[] computeObjectiveValue2 = computeObjectiveValue(startPoint);
                double[] computeResiduals2 = computeResiduals(computeObjectiveValue2);
                PointVectorValuePair pointVectorValuePair4 = new PointVectorValuePair(startPoint, computeObjectiveValue2);
                double[] dArr24 = dArr20;
                double[] dArr25 = dArr21;
                double computeCost2 = computeCost(computeResiduals2);
                double d23 = -1.0d;
                double d24 = 0.1d * computeCost2;
                if (d24 < d16) {
                    double d25 = computeCost2 / d16;
                    d23 = 1.0d - (d25 * d25);
                }
                double d26 = computeCost2;
                double d27 = d23;
                double[] dArr26 = computeObjectiveValue2;
                int i18 = 0;
                while (i18 < this.solvedCols) {
                    int i19 = this.permutation[i18];
                    double[] dArr27 = computeResiduals2;
                    double d28 = this.lmDir[i19];
                    dArr11[i18] = 0.0d;
                    int i20 = 0;
                    while (i20 <= i18) {
                        double[] dArr28 = dArr19;
                        dArr11[i20] = dArr11[i20] + (this.weightedJacobian[i20][i19] * d28);
                        i20++;
                        dArr19 = dArr28;
                    }
                    double[] dArr29 = dArr19;
                    i18++;
                    computeResiduals2 = dArr27;
                }
                double[] dArr30 = computeResiduals2;
                double[] dArr31 = dArr19;
                double d29 = 0.0d;
                for (int i21 = 0; i21 < this.solvedCols; i21++) {
                    d29 += dArr11[i21] * dArr11[i21];
                }
                double d30 = d16 * d16;
                double d31 = d29 / d30;
                PointVectorValuePair pointVectorValuePair5 = pointVectorValuePair4;
                double d32 = ((this.lmPar * sqrt) * sqrt) / d30;
                double d33 = d31 + (2.0d * d32);
                double d34 = -(d31 + d32);
                double d35 = d33 == 0.0d ? 0.0d : d27 / d33;
                double d36 = 0.5d;
                if (d35 <= 0.25d) {
                    if (d27 < 0.0d) {
                        d36 = (0.5d * d34) / (d34 + (0.5d * d27));
                    }
                    if (d24 >= d16 || d36 < 0.1d) {
                        d36 = 0.1d;
                    }
                    double min = FastMath.min(d22, 10.0d * sqrt) * d36;
                    this.lmPar /= d36;
                    d17 = min;
                    d3 = 0.0d;
                } else {
                    d3 = 0.0d;
                    if (this.lmPar == 0.0d || d35 >= 0.75d) {
                        double d37 = sqrt * 2.0d;
                        this.lmPar *= 0.5d;
                        d17 = d37;
                    } else {
                        d17 = d22;
                    }
                }
                if (d35 >= 1.0E-4d) {
                    double d38 = d3;
                    for (int i22 = 0; i22 < length2; i22++) {
                        double d39 = dArr23[i22] * startPoint[i22];
                        d38 += d39 * d39;
                    }
                    d7 = FastMath.sqrt(d38);
                    if (convergenceChecker != null) {
                        convergenceChecker2 = convergenceChecker;
                        pointVectorValuePair = pointVectorValuePair5;
                        if (convergenceChecker2.converged(i15, pointVectorValuePair2, pointVectorValuePair)) {
                            setCost(d26);
                            this.point = pointVectorValuePair.getPoint();
                            return pointVectorValuePair;
                        }
                        d4 = d26;
                    } else {
                        convergenceChecker2 = convergenceChecker;
                        d4 = d26;
                        pointVectorValuePair = pointVectorValuePair5;
                    }
                    dArr18 = dArr26;
                    z = false;
                } else {
                    convergenceChecker2 = convergenceChecker;
                    for (int i23 = 0; i23 < this.solvedCols; i23++) {
                        int i24 = this.permutation[i23];
                        startPoint[i24] = dArr22[i24];
                    }
                    double[] dArr32 = this.weightedResidual;
                    this.weightedResidual = dArr31;
                    double[] dArr33 = dArr24;
                    dArr31 = dArr32;
                    pointVectorValuePair = new PointVectorValuePair(startPoint, dArr33);
                    dArr18 = dArr33;
                    d4 = d16;
                }
                int i25 = i15;
                if ((FastMath.abs(d27) > this.costRelativeTolerance || d33 > this.costRelativeTolerance || d35 > 2.0d) && d17 > this.parRelativeTolerance * d7) {
                    if (FastMath.abs(d27) <= 2.2204E-16d && d33 <= 2.2204E-16d && d35 <= 2.0d) {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, Double.valueOf(this.costRelativeTolerance));
                    } else if (d17 <= 2.2204E-16d * d7) {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, Double.valueOf(this.parRelativeTolerance));
                    } else if (d19 <= 2.2204E-16d) {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, Double.valueOf(this.orthoTolerance));
                    } else {
                        convergenceChecker = convergenceChecker2;
                        d16 = d4;
                        dArr2 = dArr22;
                        dArr = dArr23;
                        d2 = d19;
                        dArr5 = dArr25;
                        computeResiduals = dArr30;
                        dArr13 = dArr31;
                        i4 = i25;
                        pointVectorValuePair3 = pointVectorValuePair;
                        d18 = d35;
                        realMatrix2 = realMatrix3;
                    }
                }
            }
            i3 = i4;
            pointVectorValuePair2 = pointVectorValuePair3;
            d6 = d17;
            dArr12 = dArr18;
            weightSquareRoot = realMatrix2;
            z2 = z;
            length = i;
            convergenceChecker4 = convergenceChecker;
            d5 = d16;
            dArr5 = dArr5;
        }
        setCost(d4);
        this.point = pointVectorValuePair.getPoint();
        return pointVectorValuePair;
    }

    private void determineLMParameter(double[] dArr, double d, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5) {
        double d2;
        double d3;
        double[] dArr6 = dArr;
        double d4 = d;
        double[] dArr7 = dArr3;
        double[] dArr8 = dArr4;
        double[] dArr9 = dArr5;
        int i = 0;
        int length = this.weightedJacobian[0].length;
        for (int i2 = 0; i2 < this.rank; i2++) {
            this.lmDir[this.permutation[i2]] = dArr6[i2];
        }
        for (int i3 = this.rank; i3 < length; i3++) {
            this.lmDir[this.permutation[i3]] = 0.0d;
        }
        int i4 = this.rank - 1;
        while (i4 >= 0) {
            int i5 = this.permutation[i4];
            double d5 = this.lmDir[i5] / this.diagR[i5];
            for (int i6 = i; i6 < i4; i6++) {
                double[] dArr10 = this.lmDir;
                int i7 = this.permutation[i6];
                dArr10[i7] = dArr10[i7] - (this.weightedJacobian[i6][i5] * d5);
            }
            this.lmDir[i5] = d5;
            i4--;
            i = 0;
        }
        double d6 = 0.0d;
        for (int i8 = 0; i8 < this.solvedCols; i8++) {
            int i9 = this.permutation[i8];
            double d7 = dArr2[i9] * this.lmDir[i9];
            dArr7[i9] = d7;
            d6 += d7 * d7;
        }
        double sqrt = FastMath.sqrt(d6);
        double d8 = sqrt - d4;
        double d9 = 0.1d * d4;
        if (d8 <= d9) {
            this.lmPar = 0.0d;
            return;
        }
        if (this.rank == this.solvedCols) {
            for (int i10 = 0; i10 < this.solvedCols; i10++) {
                int i11 = this.permutation[i10];
                dArr7[i11] = dArr7[i11] * (dArr2[i11] / sqrt);
            }
            int i12 = 0;
            double d10 = 0.0d;
            while (i12 < this.solvedCols) {
                int i13 = this.permutation[i12];
                double d11 = d9;
                double d12 = 0.0d;
                for (int i14 = 0; i14 < i12; i14++) {
                    d12 += this.weightedJacobian[i14][i13] * dArr7[this.permutation[i14]];
                }
                double d13 = (dArr7[i13] - d12) / this.diagR[i13];
                dArr7[i13] = d13;
                d10 += d13 * d13;
                i12++;
                d9 = d11;
                double[] dArr11 = dArr4;
            }
            d2 = d9;
            d3 = d8 / (d4 * d10);
        } else {
            d2 = d9;
            d3 = 0.0d;
        }
        double d14 = d8;
        int i15 = 0;
        double d15 = 0.0d;
        while (i15 < this.solvedCols) {
            int i16 = this.permutation[i15];
            int i17 = 0;
            double d16 = 0.0d;
            while (i17 <= i15) {
                d16 += this.weightedJacobian[i17][i16] * dArr6[i17];
                i17++;
                double[] dArr12 = dArr5;
            }
            double d17 = d16 / dArr2[i16];
            d15 += d17 * d17;
            i15++;
            double[] dArr13 = dArr5;
        }
        double sqrt2 = FastMath.sqrt(d15);
        double d18 = sqrt2 / d4;
        if (d18 == 0.0d) {
            d18 = 2.2251E-308d / FastMath.min(d4, 0.1d);
        } else {
            double d19 = d18;
        }
        this.lmPar = FastMath.min(d18, FastMath.max(this.lmPar, d3));
        double d20 = 0.0d;
        if (this.lmPar == 0.0d) {
            this.lmPar = sqrt2 / sqrt;
        }
        int i18 = 10;
        while (i18 >= 0) {
            if (this.lmPar == d20) {
                this.lmPar = FastMath.max(2.2251E-308d, 0.001d * d18);
            }
            double sqrt3 = FastMath.sqrt(this.lmPar);
            for (int i19 = 0; i19 < this.solvedCols; i19++) {
                int i20 = this.permutation[i19];
                dArr7[i20] = dArr2[i20] * sqrt3;
            }
            double[] dArr14 = dArr4;
            double[] dArr15 = dArr5;
            determineLMDirection(dArr6, dArr7, dArr14, dArr15);
            double d21 = d18;
            int i21 = 0;
            double d22 = 0.0d;
            while (i21 < this.solvedCols) {
                int i22 = this.permutation[i21];
                double d23 = dArr2[i22] * this.lmDir[i22];
                dArr15[i22] = d23;
                d22 += d23 * d23;
                i21++;
                double[] dArr16 = dArr;
            }
            double sqrt4 = FastMath.sqrt(d22);
            double d24 = sqrt4 - d4;
            if (FastMath.abs(d24) > d2 && (d3 != 0.0d || d24 > d14 || d14 >= 0.0d)) {
                for (int i23 = 0; i23 < this.solvedCols; i23++) {
                    int i24 = this.permutation[i23];
                    dArr7[i24] = (dArr15[i24] * dArr2[i24]) / sqrt4;
                }
                int i25 = 0;
                while (i25 < this.solvedCols) {
                    int i26 = this.permutation[i25];
                    dArr7[i26] = dArr7[i26] / dArr14[i25];
                    double d25 = dArr7[i26];
                    int i27 = i25 + 1;
                    int i28 = i27;
                    while (i27 < this.solvedCols) {
                        int i29 = this.permutation[i27];
                        dArr7[i29] = dArr7[i29] - (this.weightedJacobian[i27][i26] * d25);
                        i27++;
                    }
                    i25 = i28;
                }
                double d26 = 0.0d;
                for (int i30 = 0; i30 < this.solvedCols; i30++) {
                    double d27 = dArr7[this.permutation[i30]];
                    d26 += d27 * d27;
                }
                double d28 = d24 / (d26 * d4);
                if (d24 > 0.0d) {
                    d3 = FastMath.max(d3, this.lmPar);
                } else if (d24 < 0.0d) {
                    d21 = FastMath.min(d21, this.lmPar);
                } else {
                    double d29 = d21;
                }
                this.lmPar = FastMath.max(d3, this.lmPar + d28);
                i18--;
                d14 = d24;
                d20 = 0.0d;
                d18 = d21;
                dArr6 = dArr;
                d4 = d;
            } else {
                return;
            }
        }
    }

    private void determineLMDirection(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) {
        int i;
        double d;
        double d2;
        double d3;
        double[] dArr5 = dArr3;
        int i2 = 0;
        while (i2 < this.solvedCols) {
            int i3 = this.permutation[i2];
            int i4 = i2 + 1;
            for (int i5 = i4; i5 < this.solvedCols; i5++) {
                this.weightedJacobian[i5][i3] = this.weightedJacobian[i2][this.permutation[i5]];
            }
            this.lmDir[i2] = this.diagR[i3];
            dArr4[i2] = dArr[i2];
            i2 = i4;
        }
        int i6 = 0;
        while (true) {
            double d4 = 0.0d;
            if (i6 >= this.solvedCols) {
                break;
            }
            double d5 = dArr2[this.permutation[i6]];
            if (d5 != 0.0d) {
                Arrays.fill(dArr5, i6 + 1, dArr5.length, 0.0d);
            }
            dArr5[i6] = d5;
            int i7 = i6;
            double d6 = 0.0d;
            while (i7 < this.solvedCols) {
                int i8 = this.permutation[i7];
                if (dArr5[i7] != d4) {
                    double d7 = this.weightedJacobian[i7][i8];
                    if (FastMath.abs(d7) < FastMath.abs(dArr5[i7])) {
                        double d8 = d7 / dArr5[i7];
                        i = i6;
                        d3 = 1.0d / FastMath.sqrt(1.0d + (d8 * d8));
                        d2 = d8 * d3;
                        d = d6;
                    } else {
                        i = i6;
                        double d9 = dArr5[i7] / d7;
                        d = d6;
                        d2 = 1.0d / FastMath.sqrt(1.0d + (d9 * d9));
                        d3 = d2 * d9;
                    }
                    this.weightedJacobian[i7][i8] = (d7 * d2) + (dArr5[i7] * d3);
                    double d10 = -d3;
                    double d11 = (dArr4[i7] * d10) + (d2 * d);
                    dArr4[i7] = (dArr4[i7] * d2) + (d3 * d);
                    for (int i9 = i7 + 1; i9 < this.solvedCols; i9++) {
                        double d12 = this.weightedJacobian[i9][i8];
                        double d13 = (d2 * d12) + (dArr5[i9] * d3);
                        dArr5[i9] = (d12 * d10) + (dArr5[i9] * d2);
                        this.weightedJacobian[i9][i8] = d13;
                    }
                    d6 = d11;
                } else {
                    i = i6;
                    double d14 = d6;
                }
                i7++;
                i6 = i;
                d4 = 0.0d;
            }
            int i10 = i6;
            dArr5[i10] = this.weightedJacobian[i10][this.permutation[i10]];
            this.weightedJacobian[i10][this.permutation[i10]] = this.lmDir[i10];
            i6 = i10 + 1;
        }
        int i11 = this.solvedCols;
        for (int i12 = 0; i12 < this.solvedCols; i12++) {
            if (dArr5[i12] == 0.0d && i11 == this.solvedCols) {
                i11 = i12;
            }
            if (i11 < this.solvedCols) {
                dArr4[i12] = 0.0d;
            }
        }
        if (i11 > 0) {
            for (int i13 = i11 - 1; i13 >= 0; i13--) {
                int i14 = this.permutation[i13];
                double d15 = 0.0d;
                for (int i15 = i13 + 1; i15 < i11; i15++) {
                    d15 += this.weightedJacobian[i15][i14] * dArr4[i15];
                }
                dArr4[i13] = (dArr4[i13] - d15) / dArr5[i13];
            }
        }
        for (int i16 = 0; i16 < this.lmDir.length; i16++) {
            this.lmDir[this.permutation[i16]] = dArr4[i16];
        }
    }

    private void qrDecomposition(RealMatrix realMatrix) throws ConvergenceException {
        double d;
        this.weightedJacobian = realMatrix.scalarMultiply(-1.0d).getData();
        char c = 0;
        int length = this.weightedJacobian[0].length;
        int i = 0;
        while (true) {
            d = 0.0d;
            if (i >= length) {
                break;
            }
            this.permutation[i] = i;
            double d2 = 0.0d;
            for (double[] dArr : this.weightedJacobian) {
                double d3 = dArr[i];
                d2 += d3 * d3;
            }
            this.jacNorm[i] = FastMath.sqrt(d2);
            i++;
        }
        int i2 = 0;
        while (i2 < length) {
            int i3 = -1;
            double d4 = Double.NEGATIVE_INFINITY;
            for (int i4 = i2; i4 < length; i4++) {
                double d5 = d;
                for (int i5 = i2; i5 < r1; i5++) {
                    double d6 = this.weightedJacobian[i5][this.permutation[i4]];
                    d5 += d6 * d6;
                }
                if (Double.isInfinite(d5) || Double.isNaN(d5)) {
                    LocalizedFormats localizedFormats = LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN;
                    Object[] objArr = new Object[2];
                    objArr[c] = Integer.valueOf(r1);
                    objArr[1] = Integer.valueOf(length);
                    throw new ConvergenceException(localizedFormats, objArr);
                }
                if (d5 > d4) {
                    i3 = i4;
                    d4 = d5;
                }
            }
            if (d4 <= this.qrRankingThreshold) {
                this.rank = i2;
                return;
            }
            int i6 = this.permutation[i3];
            this.permutation[i3] = this.permutation[i2];
            this.permutation[i2] = i6;
            double d7 = this.weightedJacobian[i2][i6];
            double sqrt = d7 > d ? -FastMath.sqrt(d4) : FastMath.sqrt(d4);
            double d8 = 1.0d / (d4 - (d7 * sqrt));
            this.beta[i6] = d8;
            this.diagR[i6] = sqrt;
            double[] dArr2 = this.weightedJacobian[i2];
            dArr2[i6] = dArr2[i6] - sqrt;
            int i7 = (length - 1) - i2;
            while (i7 > 0) {
                double d9 = d;
                for (int i8 = i2; i8 < r1; i8++) {
                    d9 += this.weightedJacobian[i8][i6] * this.weightedJacobian[i8][this.permutation[i2 + i7]];
                }
                double d10 = d9 * d8;
                for (int i9 = i2; i9 < r1; i9++) {
                    double[] dArr3 = this.weightedJacobian[i9];
                    int i10 = this.permutation[i2 + i7];
                    dArr3[i10] = dArr3[i10] - (this.weightedJacobian[i9][i6] * d10);
                }
                i7--;
                d = 0.0d;
            }
            i2++;
            c = 0;
            d = 0.0d;
        }
        this.rank = this.solvedCols;
    }

    private void qTy(double[] dArr) {
        int length = this.weightedJacobian.length;
        int length2 = this.weightedJacobian[0].length;
        for (int i = 0; i < length2; i++) {
            int i2 = this.permutation[i];
            double d = 0.0d;
            for (int i3 = i; i3 < length; i3++) {
                d += this.weightedJacobian[i3][i2] * dArr[i3];
            }
            double d2 = d * this.beta[i2];
            for (int i4 = i; i4 < length; i4++) {
                dArr[i4] = dArr[i4] - (this.weightedJacobian[i4][i2] * d2);
            }
        }
    }
}
