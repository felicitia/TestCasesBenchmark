package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.Arrays;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;

public class BOBYQAOptimizer extends MultivariateOptimizer {
    public static final double DEFAULT_INITIAL_RADIUS = 10.0d;
    public static final double DEFAULT_STOPPING_RADIUS = 1.0E-8d;
    private static final double HALF = 0.5d;
    public static final int MINIMUM_PROBLEM_DIMENSION = 2;
    private static final double MINUS_ONE = -1.0d;
    private static final double ONE = 1.0d;
    private static final double ONE_OVER_A_THOUSAND = 0.001d;
    private static final double ONE_OVER_EIGHT = 0.125d;
    private static final double ONE_OVER_FOUR = 0.25d;
    private static final double ONE_OVER_TEN = 0.1d;
    private static final double SIXTEEN = 16.0d;
    private static final double TEN = 10.0d;
    private static final double TWO = 2.0d;
    private static final double TWO_HUNDRED_FIFTY = 250.0d;
    private static final double ZERO = 0.0d;
    private ArrayRealVector alternativeNewPoint;
    private Array2DRowRealMatrix bMatrix;
    private double[] boundDifference;
    private ArrayRealVector currentBest;
    private ArrayRealVector fAtInterpolationPoints;
    private ArrayRealVector gradientAtTrustRegionCenter;
    private double initialTrustRegionRadius;
    private Array2DRowRealMatrix interpolationPoints;
    private boolean isMinimize;
    private ArrayRealVector lagrangeValuesAtNewPoint;
    private ArrayRealVector lowerDifference;
    private ArrayRealVector modelSecondDerivativesParameters;
    private ArrayRealVector modelSecondDerivativesValues;
    private ArrayRealVector newPoint;
    private final int numberOfInterpolationPoints;
    private ArrayRealVector originShift;
    private final double stoppingTrustRegionRadius;
    private ArrayRealVector trialStepPoint;
    private int trustRegionCenterInterpolationPointIndex;
    private ArrayRealVector trustRegionCenterOffset;
    private ArrayRealVector upperDifference;
    private Array2DRowRealMatrix zMatrix;

    private static class PathIsExploredException extends RuntimeException {
        private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";
        private static final long serialVersionUID = 745350979634801853L;

        PathIsExploredException() {
            StringBuilder sb = new StringBuilder();
            sb.append("If this exception is thrown, just remove it from the code ");
            sb.append(BOBYQAOptimizer.caller(3));
            super(sb.toString());
        }
    }

    private static void printMethod() {
    }

    private static void printState(int i) {
    }

    public BOBYQAOptimizer(int i) {
        this(i, 10.0d, 1.0E-8d);
    }

    public BOBYQAOptimizer(int i, double d, double d2) {
        super(null);
        this.numberOfInterpolationPoints = i;
        this.initialTrustRegionRadius = d;
        this.stoppingTrustRegionRadius = d2;
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double[] lowerBound = getLowerBound();
        double[] upperBound = getUpperBound();
        setup(lowerBound, upperBound);
        this.isMinimize = getGoalType() == GoalType.MINIMIZE;
        this.currentBest = new ArrayRealVector(getStartPoint());
        double bobyqa = bobyqa(lowerBound, upperBound);
        double[] dataRef = this.currentBest.getDataRef();
        if (!this.isMinimize) {
            bobyqa = -bobyqa;
        }
        return new PointValuePair(dataRef, bobyqa);
    }

    private double bobyqa(double[] dArr, double[] dArr2) {
        printMethod();
        int dimension = this.currentBest.getDimension();
        for (int i = 0; i < dimension; i++) {
            double d = this.boundDifference[i];
            this.lowerDifference.setEntry(i, dArr[i] - this.currentBest.getEntry(i));
            this.upperDifference.setEntry(i, dArr2[i] - this.currentBest.getEntry(i));
            if (this.lowerDifference.getEntry(i) >= (-this.initialTrustRegionRadius)) {
                if (this.lowerDifference.getEntry(i) >= 0.0d) {
                    this.currentBest.setEntry(i, dArr[i]);
                    this.lowerDifference.setEntry(i, 0.0d);
                    this.upperDifference.setEntry(i, d);
                } else {
                    this.currentBest.setEntry(i, dArr[i] + this.initialTrustRegionRadius);
                    this.lowerDifference.setEntry(i, -this.initialTrustRegionRadius);
                    this.upperDifference.setEntry(i, Math.max(dArr2[i] - this.currentBest.getEntry(i), this.initialTrustRegionRadius));
                }
            } else if (this.upperDifference.getEntry(i) <= this.initialTrustRegionRadius) {
                if (this.upperDifference.getEntry(i) <= 0.0d) {
                    this.currentBest.setEntry(i, dArr2[i]);
                    this.lowerDifference.setEntry(i, -d);
                    this.upperDifference.setEntry(i, 0.0d);
                } else {
                    this.currentBest.setEntry(i, dArr2[i] - this.initialTrustRegionRadius);
                    this.lowerDifference.setEntry(i, Math.min(dArr[i] - this.currentBest.getEntry(i), -this.initialTrustRegionRadius));
                    this.upperDifference.setEntry(i, this.initialTrustRegionRadius);
                }
            }
        }
        return bobyqb(dArr, dArr2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0402  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x041a  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x05f2  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0630  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0660 A[LOOP:26: B:167:0x065e->B:168:0x0660, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x067f  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x06d0 A[LOOP:31: B:182:0x06ce->B:183:0x06d0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x06e9  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0794  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x092b  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x098b  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x09b1  */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x09f8  */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x0a57  */
    /* JADX WARNING: Removed duplicated region for block: B:296:0x0aa4  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0da0  */
    /* JADX WARNING: Removed duplicated region for block: B:420:0x0fec  */
    /* JADX WARNING: Removed duplicated region for block: B:422:0x0ff8  */
    /* JADX WARNING: Removed duplicated region for block: B:424:0x1018  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x03a0  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x03fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double bobyqb(double[] r133, double[] r134) {
        /*
            r132 = this;
            r8 = r132
            printMethod()
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.currentBest
            int r11 = r0.getDimension()
            int r12 = r8.numberOfInterpolationPoints
            int r0 = r11 + 1
            int r13 = r12 - r0
            int r0 = r0 * r11
            int r14 = r0 / 2
            org.apache.commons.math3.linear.ArrayRealVector r15 = new org.apache.commons.math3.linear.ArrayRealVector
            r15.<init>(r11)
            org.apache.commons.math3.linear.ArrayRealVector r7 = new org.apache.commons.math3.linear.ArrayRealVector
            r7.<init>(r12)
            org.apache.commons.math3.linear.ArrayRealVector r6 = new org.apache.commons.math3.linear.ArrayRealVector
            r6.<init>(r12)
            r5 = 0
            r8.trustRegionCenterInterpolationPointIndex = r5
            r132.prelim(r133, r134)
            r0 = r5
            r1 = 0
        L_0x002c:
            if (r0 >= r11) goto L_0x0047
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trustRegionCenterOffset
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.interpolationPoints
            int r5 = r8.trustRegionCenterInterpolationPointIndex
            double r4 = r4.getEntry(r5, r0)
            r3.setEntry(r0, r4)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trustRegionCenterOffset
            double r3 = r3.getEntry(r0)
            double r3 = r3 * r3
            double r1 = r1 + r3
            int r0 = r0 + 1
            r5 = 0
            goto L_0x002c
        L_0x0047:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.fAtInterpolationPoints
            r3 = 0
            double r4 = r0.getEntry(r3)
            int r0 = r132.getEvaluations()
            r21 = r0
            r19 = r1
            double r0 = r8.initialTrustRegionRadius
            r3 = 20
            r23 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r2 = 60
            r38 = r0
            r40 = r38
            r0 = r3
            r32 = r4
            r48 = r23
            r50 = r48
            r4 = 0
            r5 = 0
            r30 = 0
            r34 = 0
            r36 = 0
            r42 = 0
            r44 = 0
            r46 = 0
            r52 = 0
            r54 = 0
            r56 = 0
            r58 = 0
            r60 = 0
        L_0x0081:
            r61 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r1 = 1
            if (r0 == r3) goto L_0x0b73
            if (r0 == r2) goto L_0x0b58
            r2 = 90
            if (r0 == r2) goto L_0x0b2d
            r63 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            r2 = 210(0xd2, float:2.94E-43)
            if (r0 == r2) goto L_0x0116
            r2 = 230(0xe6, float:3.22E-43)
            if (r0 == r2) goto L_0x010d
            r2 = 360(0x168, float:5.04E-43)
            if (r0 == r2) goto L_0x00f9
            r2 = 650(0x28a, float:9.11E-43)
            if (r0 == r2) goto L_0x00da
            r2 = 680(0x2a8, float:9.53E-43)
            if (r0 == r2) goto L_0x00bf
            r2 = 720(0x2d0, float:1.009E-42)
            if (r0 == r2) goto L_0x00b9
            org.apache.commons.math3.exception.MathIllegalStateException r0 = new org.apache.commons.math3.exception.MathIllegalStateException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.SIMPLE_MESSAGE
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "bobyqb"
            r18 = 0
            r1[r18] = r3
            r0.<init>(r2, r1)
            throw r0
        L_0x00b9:
            r18 = 0
            r17 = r18
            goto L_0x0ac0
        L_0x00bf:
            r18 = 0
            r106 = r6
            r10 = r12
            r105 = r13
            r9 = r14
            r108 = r15
            r17 = r18
            r65 = r30
            r12 = 650(0x28a, float:9.11E-43)
            r16 = 210(0xd2, float:2.94E-43)
            r22 = 230(0xe6, float:3.22E-43)
            r14 = r2
            r13 = r4
            r15 = r7
            r6 = r46
            goto L_0x0a4e
        L_0x00da:
            r18 = 0
            r99 = r4
            r106 = r6
            r10 = r12
            r105 = r13
            r9 = r14
            r108 = r15
            r17 = r18
            r65 = r30
            r93 = r38
            r0 = r40
            r88 = r46
            r12 = 650(0x28a, float:9.11E-43)
            r16 = 210(0xd2, float:2.94E-43)
            r22 = 230(0xe6, float:3.22E-43)
            r15 = r7
            goto L_0x0981
        L_0x00f9:
            r18 = 0
            r71 = r6
            r74 = r7
            r78 = r12
            r79 = r13
            r67 = r14
            r68 = r15
            r65 = r30
            r76 = r40
            goto L_0x0398
        L_0x010d:
            r18 = 0
            r67 = r14
            r65 = r30
        L_0x0113:
            r1 = 230(0xe6, float:3.22E-43)
            goto L_0x0154
        L_0x0116:
            r0 = r2
            r2 = 680(0x2a8, float:9.53E-43)
            r18 = 0
            printState(r0)
            r1 = r30
            double[] r22 = r8.altmov(r5, r1)
            r23 = r22[r18]
            r25 = 1
            r48 = r22[r25]
            r0 = 0
        L_0x012b:
            if (r0 >= r11) goto L_0x014d
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trialStepPoint
            r65 = r1
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.newPoint
            double r1 = r1.getEntry(r0)
            r67 = r14
            org.apache.commons.math3.linear.ArrayRealVector r14 = r8.trustRegionCenterOffset
            double r25 = r14.getEntry(r0)
            double r1 = r1 - r25
            r3.setEntry(r0, r1)
            int r0 = r0 + 1
            r1 = r65
            r14 = r67
            r3 = 20
            goto L_0x012b
        L_0x014d:
            r65 = r1
            r67 = r14
            r50 = r23
            goto L_0x0113
        L_0x0154:
            printState(r1)
            r0 = 0
        L_0x0158:
            if (r0 >= r12) goto L_0x01ad
            r68 = r15
            r2 = 0
            r9 = 0
            r14 = 0
            r22 = 0
        L_0x0163:
            if (r2 >= r11) goto L_0x0198
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r8.interpolationPoints
            double r24 = r3.getEntry(r0, r2)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trialStepPoint
            double r26 = r3.getEntry(r2)
            double r24 = r24 * r26
            double r14 = r14 + r24
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r8.interpolationPoints
            double r24 = r3.getEntry(r0, r2)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trustRegionCenterOffset
            double r26 = r3.getEntry(r2)
            double r24 = r24 * r26
            double r22 = r22 + r24
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r8.bMatrix
            double r24 = r3.getEntry(r0, r2)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trialStepPoint
            double r26 = r3.getEntry(r2)
            double r24 = r24 * r26
            double r9 = r9 + r24
            int r2 = r2 + 1
            goto L_0x0163
        L_0x0198:
            double r2 = r61 * r14
            double r2 = r2 + r22
            double r2 = r2 * r14
            r6.setEntry(r0, r2)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.lagrangeValuesAtNewPoint
            r2.setEntry(r0, r9)
            r7.setEntry(r0, r14)
            int r0 = r0 + 1
            r15 = r68
            goto L_0x0158
        L_0x01ad:
            r68 = r15
            r0 = 0
            r2 = 0
        L_0x01b2:
            if (r0 >= r13) goto L_0x01f5
            r9 = 0
            r14 = 0
        L_0x01b7:
            if (r9 >= r12) goto L_0x01ca
            org.apache.commons.math3.linear.Array2DRowRealMatrix r10 = r8.zMatrix
            double r22 = r10.getEntry(r9, r0)
            double r24 = r6.getEntry(r9)
            double r22 = r22 * r24
            double r14 = r14 + r22
            int r9 = r9 + 1
            goto L_0x01b7
        L_0x01ca:
            double r9 = r14 * r14
            double r2 = r2 - r9
            r9 = 0
        L_0x01ce:
            if (r9 >= r12) goto L_0x01ee
            org.apache.commons.math3.linear.ArrayRealVector r10 = r8.lagrangeValuesAtNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.lagrangeValuesAtNewPoint
            double r22 = r1.getEntry(r9)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r8.zMatrix
            double r24 = r1.getEntry(r9, r0)
            double r24 = r24 * r14
            r69 = r2
            double r1 = r22 + r24
            r10.setEntry(r9, r1)
            int r9 = r9 + 1
            r2 = r69
            r1 = 230(0xe6, float:3.22E-43)
            goto L_0x01ce
        L_0x01ee:
            r69 = r2
            int r0 = r0 + 1
            r1 = 230(0xe6, float:3.22E-43)
            goto L_0x01b2
        L_0x01f5:
            r0 = 0
            r9 = 0
            r14 = 0
            r23 = 0
        L_0x01fc:
            if (r0 >= r11) goto L_0x0278
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trialStepPoint
            double r25 = r1.getEntry(r0)
            double r25 = r25 * r25
            double r23 = r23 + r25
            r1 = 0
            r25 = 0
        L_0x020b:
            if (r1 >= r12) goto L_0x0222
            double r27 = r6.getEntry(r1)
            r71 = r6
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r8.bMatrix
            double r30 = r6.getEntry(r1, r0)
            double r27 = r27 * r30
            double r25 = r25 + r27
            int r1 = r1 + 1
            r6 = r71
            goto L_0x020b
        L_0x0222:
            r71 = r6
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trialStepPoint
            double r27 = r1.getEntry(r0)
            double r27 = r27 * r25
            double r14 = r14 + r27
            int r1 = r12 + r0
            r72 = r12
            r73 = r13
            r12 = r25
            r6 = 0
        L_0x0237:
            if (r6 >= r11) goto L_0x0250
            r74 = r7
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r8.bMatrix
            double r25 = r7.getEntry(r1, r6)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.trialStepPoint
            double r27 = r7.getEntry(r6)
            double r25 = r25 * r27
            double r12 = r12 + r25
            int r6 = r6 + 1
            r7 = r74
            goto L_0x0237
        L_0x0250:
            r74 = r7
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.lagrangeValuesAtNewPoint
            r6.setEntry(r1, r12)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trialStepPoint
            double r6 = r1.getEntry(r0)
            double r12 = r12 * r6
            double r14 = r14 + r12
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trialStepPoint
            double r6 = r1.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trustRegionCenterOffset
            double r12 = r1.getEntry(r0)
            double r6 = r6 * r12
            double r9 = r9 + r6
            int r0 = r0 + 1
            r6 = r71
            r12 = r72
            r13 = r73
            r7 = r74
            goto L_0x01fc
        L_0x0278:
            r71 = r6
            r74 = r7
            r72 = r12
            r73 = r13
            double r0 = r9 * r9
            double r6 = r19 + r9
            double r6 = r6 + r9
            double r9 = r61 * r23
            double r6 = r6 + r9
            double r6 = r6 * r23
            double r0 = r0 + r6
            double r0 = r0 + r2
            double r54 = r0 - r14
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.lagrangeValuesAtNewPoint
            int r1 = r8.trustRegionCenterInterpolationPointIndex
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.lagrangeValuesAtNewPoint
            int r3 = r8.trustRegionCenterInterpolationPointIndex
            double r2 = r2.getEntry(r3)
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r2 = r2 + r6
            r0.setEntry(r1, r2)
            if (r4 != 0) goto L_0x02fa
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.lagrangeValuesAtNewPoint
            double r0 = r0.getEntry(r5)
            double r0 = r0 * r0
            double r2 = r50 * r54
            double r52 = r0 + r2
            int r0 = (r52 > r48 ? 1 : (r52 == r48 ? 0 : -1))
            if (r0 >= 0) goto L_0x02f2
            r0 = 0
            int r2 = (r48 > r0 ? 1 : (r48 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x02f2
            r0 = 0
        L_0x02b8:
            if (r0 >= r11) goto L_0x02da
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.alternativeNewPoint
            double r2 = r2.getEntry(r0)
            r1.setEntry(r0, r2)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trialStepPoint
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.newPoint
            double r2 = r2.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.trustRegionCenterOffset
            double r6 = r6.getEntry(r0)
            double r2 = r2 - r6
            r1.setEntry(r0, r2)
            int r0 = r0 + 1
            goto L_0x02b8
        L_0x02da:
            r30 = r65
            r14 = r67
            r15 = r68
            r6 = r71
            r12 = r72
            r13 = r73
            r7 = r74
            r0 = 230(0xe6, float:3.22E-43)
            r2 = 60
            r3 = 20
            r48 = 0
            goto L_0x0081
        L_0x02f2:
            r76 = r40
            r78 = r72
            r79 = r73
            goto L_0x0398
        L_0x02fa:
            r9 = r40
            double r40 = r9 * r9
            r12 = r72
            r0 = 0
            r1 = 0
            r3 = 0
            r5 = 0
        L_0x0306:
            if (r0 >= r12) goto L_0x038e
            int r7 = r8.trustRegionCenterInterpolationPointIndex
            if (r0 != r7) goto L_0x0314
            r76 = r9
            r78 = r12
            r79 = r73
            goto L_0x0384
        L_0x0314:
            r13 = r73
            r7 = 0
            r14 = 0
        L_0x0319:
            if (r7 >= r13) goto L_0x032c
            r75 = r3
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r8.zMatrix
            double r25 = r3.getEntry(r0, r7)
            double r25 = r25 * r25
            double r14 = r14 + r25
            int r7 = r7 + 1
            r3 = r75
            goto L_0x0319
        L_0x032c:
            r75 = r3
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.lagrangeValuesAtNewPoint
            double r25 = r3.getEntry(r0)
            double r14 = r14 * r54
            double r25 = r25 * r25
            double r14 = r14 + r25
            r3 = 0
            r25 = 0
        L_0x033d:
            if (r3 >= r11) goto L_0x0354
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r8.interpolationPoints
            double r27 = r7.getEntry(r0, r3)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.trustRegionCenterOffset
            double r30 = r7.getEntry(r3)
            double r27 = r27 - r30
            double r27 = r27 * r27
            double r25 = r25 + r27
            int r3 = r3 + 1
            goto L_0x033d
        L_0x0354:
            double r27 = r25 / r40
            r76 = r9
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r78 = r12
            r79 = r13
            double r12 = r27 * r27
            double r9 = java.lang.Math.max(r9, r12)
            double r12 = r9 * r14
            int r3 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x036d
            r75 = r0
            goto L_0x0370
        L_0x036d:
            r12 = r1
            r14 = r52
        L_0x0370:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.lagrangeValuesAtNewPoint
            double r1 = r1.getEntry(r0)
            double r1 = r1 * r1
            double r9 = r9 * r1
            double r1 = java.lang.Math.max(r5, r9)
            r5 = r1
            r1 = r12
            r52 = r14
            r58 = r25
            r3 = r75
        L_0x0384:
            int r0 = r0 + 1
            r9 = r76
            r12 = r78
            r73 = r79
            goto L_0x0306
        L_0x038e:
            r75 = r3
            r76 = r9
            r78 = r12
            r79 = r73
            r5 = r75
        L_0x0398:
            r0 = 360(0x168, float:5.04E-43)
            printState(r0)
            r0 = 0
        L_0x039e:
            if (r0 >= r11) goto L_0x03ef
            r1 = r133[r0]
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.originShift
            double r6 = r3.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.newPoint
            double r12 = r3.getEntry(r0)
            double r6 = r6 + r12
            double r1 = java.lang.Math.max(r1, r6)
            r6 = r134[r0]
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.currentBest
            double r1 = java.lang.Math.min(r1, r6)
            r3.setEntry(r0, r1)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.newPoint
            double r1 = r1.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.lowerDifference
            double r6 = r3.getEntry(r0)
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x03d5
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.currentBest
            r2 = r133[r0]
            r1.setEntry(r0, r2)
        L_0x03d5:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.newPoint
            double r1 = r1.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.upperDifference
            double r6 = r3.getEntry(r0)
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x03ec
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.currentBest
            r2 = r134[r0]
            r1.setEntry(r0, r2)
        L_0x03ec:
            int r0 = r0 + 1
            goto L_0x039e
        L_0x03ef:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.currentBest
            double[] r0 = r0.toArray()
            double r0 = r8.computeObjectiveValue(r0)
            boolean r2 = r8.isMinimize
            if (r2 != 0) goto L_0x03fe
            double r0 = -r0
        L_0x03fe:
            r6 = r0
            r1 = -1
            if (r4 != r1) goto L_0x041a
            r0 = 720(0x2d0, float:1.009E-42)
            r32 = r6
            r34 = r32
            r30 = r65
            r14 = r67
            r15 = r68
            r6 = r71
            r7 = r74
            r40 = r76
            r12 = r78
            r13 = r79
            goto L_0x0aba
        L_0x041a:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.fAtInterpolationPoints
            int r2 = r8.trustRegionCenterInterpolationPointIndex
            double r12 = r0.getEntry(r2)
            r0 = 0
            r2 = 0
            r14 = 0
        L_0x0426:
            if (r0 >= r11) goto L_0x046b
            org.apache.commons.math3.linear.ArrayRealVector r15 = r8.trialStepPoint
            double r25 = r15.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r8.gradientAtTrustRegionCenter
            double r27 = r15.getEntry(r0)
            double r25 = r25 * r27
            double r2 = r2 + r25
            r130 = r2
            r3 = r14
            r14 = r130
            r2 = 0
        L_0x043e:
            if (r2 > r0) goto L_0x0462
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trialStepPoint
            double r25 = r1.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trialStepPoint
            double r27 = r1.getEntry(r0)
            double r25 = r25 * r27
            if (r2 != r0) goto L_0x0452
            double r25 = r25 * r61
        L_0x0452:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.modelSecondDerivativesValues
            double r27 = r1.getEntry(r3)
            double r27 = r27 * r25
            double r14 = r14 + r27
            int r3 = r3 + 1
            int r2 = r2 + 1
            r1 = -1
            goto L_0x043e
        L_0x0462:
            int r0 = r0 + 1
            r1 = -1
            r130 = r14
            r14 = r3
            r2 = r130
            goto L_0x0426
        L_0x046b:
            r1 = r2
            r14 = r78
            r0 = 0
        L_0x046f:
            if (r0 >= r14) goto L_0x0488
            r15 = r74
            double r25 = r15.getEntry(r0)
            double r25 = r25 * r25
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.modelSecondDerivativesParameters
            double r27 = r3.getEntry(r0)
            double r27 = r27 * r61
            double r27 = r27 * r25
            double r1 = r1 + r27
            int r0 = r0 + 1
            goto L_0x046f
        L_0x0488:
            r15 = r74
            double r25 = r6 - r12
            double r9 = r25 - r1
            double r27 = java.lang.Math.abs(r9)
            r80 = r9
            r82 = r14
            r83 = r15
            r9 = r38
            r14 = r46
            int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r0 <= 0) goto L_0x04a6
            int r0 = r132.getEvaluations()
            r21 = r0
        L_0x04a6:
            if (r4 <= 0) goto L_0x05aa
            r16 = 0
            int r0 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r0 < 0) goto L_0x04c1
            org.apache.commons.math3.exception.MathIllegalStateException r0 = new org.apache.commons.math3.exception.MathIllegalStateException
            org.apache.commons.math3.exception.util.LocalizedFormats r3 = org.apache.commons.math3.exception.util.LocalizedFormats.TRUST_REGION_STEP_FAILED
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r18 = 0
            r4[r18] = r1
            r0.<init>(r3, r4)
            throw r0
        L_0x04c1:
            r18 = 0
            double r25 = r25 / r1
            r84 = r1
            double r0 = r61 * r76
            int r2 = (r25 > r63 ? 1 : (r25 == r63 ? 0 : -1))
            if (r2 > 0) goto L_0x04d4
            double r0 = java.lang.Math.min(r0, r14)
        L_0x04d1:
            r38 = r0
            goto L_0x04ea
        L_0x04d4:
            r2 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            int r22 = (r25 > r2 ? 1 : (r25 == r2 ? 0 : -1))
            if (r22 > 0) goto L_0x04e2
            double r0 = java.lang.Math.max(r0, r14)
            goto L_0x04d1
        L_0x04e2:
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r2 = r2 * r14
            double r0 = java.lang.Math.max(r0, r2)
            goto L_0x04d1
        L_0x04ea:
            r0 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            double r0 = r0 * r9
            int r2 = (r38 > r0 ? 1 : (r38 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x04f4
            r40 = r9
            goto L_0x04f6
        L_0x04f4:
            r40 = r38
        L_0x04f6:
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 >= 0) goto L_0x059b
            double r0 = r40 * r40
            r86 = r4
            r87 = r5
            r4 = r16
            r29 = r4
            r2 = r18
            r22 = r2
            r34 = r52
            r3 = r82
        L_0x050c:
            if (r2 >= r3) goto L_0x0585
            r88 = r14
            r36 = r16
            r15 = r18
            r14 = r79
        L_0x0516:
            if (r15 >= r14) goto L_0x0529
            r90 = r3
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r8.zMatrix
            double r38 = r3.getEntry(r2, r15)
            double r38 = r38 * r38
            double r36 = r36 + r38
            int r15 = r15 + 1
            r3 = r90
            goto L_0x0516
        L_0x0529:
            r90 = r3
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.lagrangeValuesAtNewPoint
            double r38 = r3.getEntry(r2)
            double r36 = r36 * r54
            double r38 = r38 * r38
            double r36 = r36 + r38
            r58 = r16
            r3 = r18
        L_0x053b:
            if (r3 >= r11) goto L_0x0552
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r8.interpolationPoints
            double r38 = r15.getEntry(r2, r3)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r8.newPoint
            double r46 = r15.getEntry(r3)
            double r38 = r38 - r46
            double r38 = r38 * r38
            double r58 = r58 + r38
            int r3 = r3 + 1
            goto L_0x053b
        L_0x0552:
            double r38 = r58 / r0
            r91 = r0
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r93 = r9
            double r9 = r38 * r38
            double r0 = java.lang.Math.max(r0, r9)
            double r9 = r0 * r36
            int r3 = (r9 > r29 ? 1 : (r9 == r29 ? 0 : -1))
            if (r3 <= 0) goto L_0x056c
            r22 = r2
            r29 = r9
            r34 = r36
        L_0x056c:
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.lagrangeValuesAtNewPoint
            double r9 = r3.getEntry(r2)
            double r9 = r9 * r9
            double r0 = r0 * r9
            double r4 = java.lang.Math.max(r4, r0)
            int r2 = r2 + 1
            r79 = r14
            r14 = r88
            r3 = r90
            r0 = r91
            r9 = r93
            goto L_0x050c
        L_0x0585:
            r90 = r3
            r93 = r9
            r88 = r14
            r14 = r79
            double r4 = r4 * r61
            int r0 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0594
            goto L_0x05a7
        L_0x0594:
            r9 = r22
            r56 = r25
            r52 = r34
            goto L_0x05c0
        L_0x059b:
            r86 = r4
            r87 = r5
            r93 = r9
            r88 = r14
            r14 = r79
            r90 = r82
        L_0x05a7:
            r56 = r25
            goto L_0x05be
        L_0x05aa:
            r84 = r1
            r86 = r4
            r87 = r5
            r93 = r9
            r88 = r14
            r14 = r79
            r90 = r82
            r16 = 0
            r18 = 0
            r40 = r76
        L_0x05be:
            r9 = r87
        L_0x05c0:
            r3 = 210(0xd2, float:2.94E-43)
            r0 = r8
            r25 = r84
            r4 = 680(0x2a8, float:9.53E-43)
            r5 = 650(0x28a, float:9.11E-43)
            r10 = 60
            r15 = -1
            r22 = 230(0xe6, float:3.22E-43)
            r1 = r54
            r95 = r12
            r12 = r16
            r15 = r86
            r10 = r90
            r16 = r3
            r3 = r52
            r17 = r18
            r5 = r9
            r0.update(r1, r3, r5)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.modelSecondDerivativesParameters
            double r0 = r0.getEntry(r9)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.modelSecondDerivativesParameters
            r2.setEntry(r9, r12)
            r2 = r17
            r3 = r2
        L_0x05f0:
            if (r2 >= r11) goto L_0x062a
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.interpolationPoints
            double r4 = r4.getEntry(r9, r2)
            double r4 = r4 * r0
            r12 = r3
            r3 = r17
        L_0x05fc:
            if (r3 > r2) goto L_0x0620
            org.apache.commons.math3.linear.ArrayRealVector r13 = r8.modelSecondDerivativesValues
            r97 = r0
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.modelSecondDerivativesValues
            double r0 = r0.getEntry(r12)
            r99 = r15
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r8.interpolationPoints
            double r29 = r15.getEntry(r9, r3)
            double r29 = r29 * r4
            double r0 = r0 + r29
            r13.setEntry(r12, r0)
            int r12 = r12 + 1
            int r3 = r3 + 1
            r0 = r97
            r15 = r99
            goto L_0x05fc
        L_0x0620:
            r97 = r0
            r99 = r15
            int r2 = r2 + 1
            r3 = r12
            r12 = 0
            goto L_0x05f0
        L_0x062a:
            r99 = r15
            r0 = r17
        L_0x062e:
            if (r0 >= r14) goto L_0x0657
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r8.zMatrix
            double r1 = r1.getEntry(r9, r0)
            double r1 = r1 * r80
            r3 = r17
        L_0x063a:
            if (r3 >= r10) goto L_0x0654
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.modelSecondDerivativesParameters
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.modelSecondDerivativesParameters
            double r12 = r5.getEntry(r3)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r8.zMatrix
            double r29 = r5.getEntry(r3, r0)
            double r29 = r29 * r1
            double r12 = r12 + r29
            r4.setEntry(r3, r12)
            int r3 = r3 + 1
            goto L_0x063a
        L_0x0654:
            int r0 = r0 + 1
            goto L_0x062e
        L_0x0657:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.fAtInterpolationPoints
            r0.setEntry(r9, r6)
            r0 = r17
        L_0x065e:
            if (r0 >= r11) goto L_0x0679
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r8.interpolationPoints
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.newPoint
            double r2 = r2.getEntry(r0)
            r1.setEntry(r9, r0, r2)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r8.bMatrix
            double r1 = r1.getEntry(r9, r0)
            r12 = r68
            r12.setEntry(r0, r1)
            int r0 = r0 + 1
            goto L_0x065e
        L_0x0679:
            r12 = r68
            r0 = r17
        L_0x067d:
            if (r0 >= r10) goto L_0x06cc
            r1 = r17
            r2 = 0
        L_0x0683:
            if (r1 >= r14) goto L_0x0697
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.zMatrix
            double r4 = r4.getEntry(r9, r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r8.zMatrix
            double r29 = r13.getEntry(r0, r1)
            double r4 = r4 * r29
            double r2 = r2 + r4
            int r1 = r1 + 1
            goto L_0x0683
        L_0x0697:
            r1 = r17
            r4 = 0
        L_0x069b:
            if (r1 >= r11) goto L_0x06b0
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r8.interpolationPoints
            double r29 = r13.getEntry(r0, r1)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r8.trustRegionCenterOffset
            double r34 = r13.getEntry(r1)
            double r29 = r29 * r34
            double r4 = r4 + r29
            int r1 = r1 + 1
            goto L_0x069b
        L_0x06b0:
            double r2 = r2 * r4
            r1 = r17
        L_0x06b3:
            if (r1 >= r11) goto L_0x06c9
            double r4 = r12.getEntry(r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r8.interpolationPoints
            double r29 = r13.getEntry(r0, r1)
            double r29 = r29 * r2
            double r4 = r4 + r29
            r12.setEntry(r1, r4)
            int r1 = r1 + 1
            goto L_0x06b3
        L_0x06c9:
            int r0 = r0 + 1
            goto L_0x067d
        L_0x06cc:
            r0 = r17
        L_0x06ce:
            if (r0 >= r11) goto L_0x06e5
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.gradientAtTrustRegionCenter
            double r2 = r2.getEntry(r0)
            double r4 = r12.getEntry(r0)
            double r4 = r4 * r80
            double r2 = r2 + r4
            r1.setEntry(r0, r2)
            int r0 = r0 + 1
            goto L_0x06ce
        L_0x06e5:
            int r0 = (r6 > r95 ? 1 : (r6 == r95 ? 0 : -1))
            if (r0 >= 0) goto L_0x0792
            r8.trustRegionCenterInterpolationPointIndex = r9
            r0 = r17
            r1 = r0
            r19 = 0
        L_0x06f0:
            if (r0 >= r11) goto L_0x074c
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.trustRegionCenterOffset
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.newPoint
            double r3 = r3.getEntry(r0)
            r2.setEntry(r0, r3)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.trustRegionCenterOffset
            double r2 = r2.getEntry(r0)
            double r2 = r2 * r2
            double r19 = r19 + r2
            r2 = r1
            r1 = r17
        L_0x0709:
            if (r1 > r0) goto L_0x0748
            if (r1 >= r0) goto L_0x0728
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.gradientAtTrustRegionCenter
            double r4 = r4.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r8.modelSecondDerivativesValues
            double r29 = r13.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r8.trialStepPoint
            double r34 = r13.getEntry(r1)
            double r29 = r29 * r34
            double r4 = r4 + r29
            r3.setEntry(r0, r4)
        L_0x0728:
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.gradientAtTrustRegionCenter
            double r4 = r4.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r8.modelSecondDerivativesValues
            double r29 = r13.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r8.trialStepPoint
            double r34 = r13.getEntry(r0)
            double r29 = r29 * r34
            double r4 = r4 + r29
            r3.setEntry(r1, r4)
            int r2 = r2 + 1
            int r1 = r1 + 1
            goto L_0x0709
        L_0x0748:
            int r0 = r0 + 1
            r1 = r2
            goto L_0x06f0
        L_0x074c:
            r0 = r17
        L_0x074e:
            if (r0 >= r10) goto L_0x0792
            r1 = r17
            r2 = 0
        L_0x0754:
            if (r1 >= r11) goto L_0x0768
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.interpolationPoints
            double r4 = r4.getEntry(r0, r1)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r8.trialStepPoint
            double r29 = r13.getEntry(r1)
            double r4 = r4 * r29
            double r2 = r2 + r4
            int r1 = r1 + 1
            goto L_0x0754
        L_0x0768:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.modelSecondDerivativesParameters
            double r4 = r1.getEntry(r0)
            double r2 = r2 * r4
            r1 = r17
        L_0x0771:
            if (r1 >= r11) goto L_0x078f
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.gradientAtTrustRegionCenter
            double r29 = r5.getEntry(r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r8.interpolationPoints
            double r34 = r5.getEntry(r0, r1)
            double r34 = r34 * r2
            r100 = r2
            double r2 = r29 + r34
            r4.setEntry(r1, r2)
            int r1 = r1 + 1
            r2 = r100
            goto L_0x0771
        L_0x078f:
            int r0 = r0 + 1
            goto L_0x074e
        L_0x0792:
            if (r99 <= 0) goto L_0x092b
            r0 = r17
        L_0x0796:
            if (r0 >= r10) goto L_0x07b6
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.lagrangeValuesAtNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.fAtInterpolationPoints
            double r2 = r2.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.fAtInterpolationPoints
            int r5 = r8.trustRegionCenterInterpolationPointIndex
            double r4 = r4.getEntry(r5)
            double r2 = r2 - r4
            r1.setEntry(r0, r2)
            r13 = r71
            r1 = 0
            r13.setEntry(r0, r1)
            int r0 = r0 + 1
            goto L_0x0796
        L_0x07b6:
            r13 = r71
            r0 = r17
        L_0x07ba:
            if (r0 >= r14) goto L_0x07ef
            r1 = r17
            r2 = 0
        L_0x07c0:
            if (r1 >= r10) goto L_0x07d4
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.zMatrix
            double r4 = r4.getEntry(r1, r0)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r8.lagrangeValuesAtNewPoint
            double r29 = r15.getEntry(r1)
            double r4 = r4 * r29
            double r2 = r2 + r4
            int r1 = r1 + 1
            goto L_0x07c0
        L_0x07d4:
            r1 = r17
        L_0x07d6:
            if (r1 >= r10) goto L_0x07ec
            double r4 = r13.getEntry(r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r8.zMatrix
            double r29 = r15.getEntry(r1, r0)
            double r29 = r29 * r2
            double r4 = r4 + r29
            r13.setEntry(r1, r4)
            int r1 = r1 + 1
            goto L_0x07d6
        L_0x07ec:
            int r0 = r0 + 1
            goto L_0x07ba
        L_0x07ef:
            r0 = r17
        L_0x07f1:
            if (r0 >= r10) goto L_0x081f
            r1 = r17
            r2 = 0
        L_0x07f7:
            if (r1 >= r11) goto L_0x080b
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.interpolationPoints
            double r4 = r4.getEntry(r0, r1)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r8.trustRegionCenterOffset
            double r29 = r15.getEntry(r1)
            double r4 = r4 * r29
            double r2 = r2 + r4
            int r1 = r1 + 1
            goto L_0x07f7
        L_0x080b:
            double r4 = r13.getEntry(r0)
            r15 = r83
            r15.setEntry(r0, r4)
            double r4 = r13.getEntry(r0)
            double r2 = r2 * r4
            r13.setEntry(r0, r2)
            int r0 = r0 + 1
            goto L_0x07f1
        L_0x081f:
            r15 = r83
            r0 = r17
            r1 = 0
            r3 = 0
        L_0x0827:
            if (r0 >= r11) goto L_0x08d4
            r102 = r6
            r5 = r17
            r6 = 0
        L_0x082f:
            if (r5 >= r10) goto L_0x0856
            r104 = r9
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.bMatrix
            double r29 = r9.getEntry(r5, r0)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r8.lagrangeValuesAtNewPoint
            double r34 = r9.getEntry(r5)
            double r29 = r29 * r34
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.interpolationPoints
            double r34 = r9.getEntry(r5, r0)
            double r36 = r13.getEntry(r5)
            double r34 = r34 * r36
            double r29 = r29 + r34
            double r6 = r6 + r29
            int r5 = r5 + 1
            r9 = r104
            goto L_0x082f
        L_0x0856:
            r104 = r9
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.trustRegionCenterOffset
            double r29 = r5.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.lowerDifference
            double r34 = r5.getEntry(r0)
            int r5 = (r29 > r34 ? 1 : (r29 == r34 ? 0 : -1))
            if (r5 != 0) goto L_0x0885
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.gradientAtTrustRegionCenter
            r106 = r13
            r105 = r14
            double r13 = r5.getEntry(r0)
            r107 = r11
            r108 = r12
            r11 = 0
            double r13 = java.lang.Math.min(r11, r13)
            double r13 = r13 * r13
            double r1 = r1 + r13
            double r13 = java.lang.Math.min(r11, r6)
            double r13 = r13 * r13
            double r3 = r3 + r13
            goto L_0x08bd
        L_0x0885:
            r107 = r11
            r108 = r12
            r106 = r13
            r105 = r14
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.trustRegionCenterOffset
            double r11 = r5.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.upperDifference
            double r13 = r5.getEntry(r0)
            int r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r5 != 0) goto L_0x08b2
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.gradientAtTrustRegionCenter
            double r11 = r5.getEntry(r0)
            r13 = 0
            double r11 = java.lang.Math.max(r13, r11)
            double r11 = r11 * r11
            double r1 = r1 + r11
            double r11 = java.lang.Math.max(r13, r6)
            double r11 = r11 * r11
            double r3 = r3 + r11
            goto L_0x08bd
        L_0x08b2:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.gradientAtTrustRegionCenter
            double r11 = r5.getEntry(r0)
            double r11 = r11 * r11
            double r1 = r1 + r11
            double r11 = r6 * r6
            double r3 = r3 + r11
        L_0x08bd:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.lagrangeValuesAtNewPoint
            int r12 = r10 + r0
            r5.setEntry(r12, r6)
            int r0 = r0 + 1
            r6 = r102
            r9 = r104
            r14 = r105
            r13 = r106
            r11 = r107
            r12 = r108
            goto L_0x0827
        L_0x08d4:
            r102 = r6
            r104 = r9
            r107 = r11
            r108 = r12
            r106 = r13
            r105 = r14
            int r5 = r60 + 1
            r6 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r6 = r6 * r3
            int r0 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x08eb
            r5 = r17
        L_0x08eb:
            r0 = 3
            if (r5 < r0) goto L_0x0924
            r9 = r67
            int r0 = java.lang.Math.max(r10, r9)
            r1 = r17
        L_0x08f6:
            if (r1 >= r0) goto L_0x0926
            r11 = r107
            if (r1 >= r11) goto L_0x0909
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.lagrangeValuesAtNewPoint
            int r12 = r10 + r1
            double r3 = r3.getEntry(r12)
            r2.setEntry(r1, r3)
        L_0x0909:
            if (r1 >= r10) goto L_0x0914
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.modelSecondDerivativesParameters
            double r3 = r15.getEntry(r1)
            r2.setEntry(r1, r3)
        L_0x0914:
            if (r1 >= r9) goto L_0x091d
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.modelSecondDerivativesValues
            r3 = 0
            r2.setEntry(r1, r3)
        L_0x091d:
            int r1 = r1 + 1
            r107 = r11
            r5 = r17
            goto L_0x08f6
        L_0x0924:
            r9 = r67
        L_0x0926:
            r11 = r107
            r60 = r5
            goto L_0x0939
        L_0x092b:
            r102 = r6
            r104 = r9
            r108 = r12
            r105 = r14
            r9 = r67
            r106 = r71
            r15 = r83
        L_0x0939:
            if (r99 != 0) goto L_0x095e
        L_0x093b:
            r14 = r9
            r12 = r10
            r7 = r15
            r36 = r44
            r30 = r65
            r46 = r88
            r38 = r93
            r4 = r99
            r34 = r102
            r5 = r104
            r13 = r105
            r6 = r106
            r15 = r108
            r0 = 60
            r2 = 60
            r3 = 20
            r44 = r42
            r42 = r27
            goto L_0x0081
        L_0x095e:
            double r1 = r63 * r25
            double r12 = r95 + r1
            int r0 = (r102 > r12 ? 1 : (r102 == r12 ? 0 : -1))
            if (r0 > 0) goto L_0x0967
            goto L_0x093b
        L_0x0967:
            r0 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = r0 * r40
            r2 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r2 = r2 * r93
            double r0 = r0 * r0
            double r2 = r2 * r2
            double r58 = java.lang.Math.max(r0, r2)
            r0 = r40
            r36 = r44
            r34 = r102
            r12 = 650(0x28a, float:9.11E-43)
            r44 = r42
            r42 = r27
        L_0x0981:
            printState(r12)
            r2 = r17
            r3 = r58
            r5 = -1
        L_0x0989:
            if (r2 >= r10) goto L_0x09af
            r6 = r17
            r13 = 0
        L_0x098f:
            if (r6 >= r11) goto L_0x09a6
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r8.interpolationPoints
            double r25 = r7.getEntry(r2, r6)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.trustRegionCenterOffset
            double r27 = r7.getEntry(r6)
            double r25 = r25 - r27
            double r25 = r25 * r25
            double r13 = r13 + r25
            int r6 = r6 + 1
            goto L_0x098f
        L_0x09a6:
            int r6 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x09ac
            r5 = r2
            r3 = r13
        L_0x09ac:
            int r2 = r2 + 1
            goto L_0x0989
        L_0x09af:
            if (r5 < 0) goto L_0x09f8
            double r6 = java.lang.Math.sqrt(r3)
            r13 = r99
            r2 = -1
            if (r13 != r2) goto L_0x09cf
            double r0 = r0 * r63
            double r13 = r61 * r6
            double r38 = java.lang.Math.min(r0, r13)
            r0 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            double r0 = r0 * r93
            int r2 = (r38 > r0 ? 1 : (r38 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x09cd
            r0 = r93
            goto L_0x09cf
        L_0x09cd:
            r0 = r38
        L_0x09cf:
            double r6 = r6 * r63
            double r6 = java.lang.Math.min(r6, r0)
            r13 = r93
            double r30 = java.lang.Math.max(r6, r13)
            double r23 = r30 * r30
            r40 = r0
            r58 = r3
            r12 = r10
            r38 = r13
            r7 = r15
            r4 = r17
            r46 = r88
            r13 = r105
            r6 = r106
            r15 = r108
            r0 = 90
            r2 = 60
            r3 = 20
            r14 = r9
            goto L_0x0081
        L_0x09f8:
            r38 = r93
            r13 = r99
            r2 = -1
            if (r13 != r2) goto L_0x0a15
            r40 = r0
            r58 = r3
            r14 = r9
            r12 = r10
            r4 = r13
            r7 = r15
            r30 = r65
            r46 = r88
            r13 = r105
            r6 = r106
            r15 = r108
            r0 = 680(0x2a8, float:9.53E-43)
            goto L_0x0aba
        L_0x0a15:
            r6 = 0
            int r2 = (r56 > r6 ? 1 : (r56 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0a31
            r40 = r0
            r58 = r3
            r14 = r9
            r12 = r10
            r4 = r13
            r7 = r15
            r30 = r65
            r46 = r88
        L_0x0a27:
            r13 = r105
            r6 = r106
            r15 = r108
            r0 = 60
            goto L_0x0aba
        L_0x0a31:
            r6 = r88
            double r25 = java.lang.Math.max(r0, r6)
            int r2 = (r25 > r38 ? 1 : (r25 == r38 ? 0 : -1))
            if (r2 <= 0) goto L_0x0a48
            r40 = r0
            r58 = r3
            r46 = r6
            r14 = r9
            r12 = r10
            r4 = r13
            r7 = r15
            r30 = r65
            goto L_0x0a27
        L_0x0a48:
            r40 = r0
            r58 = r3
            r14 = 680(0x2a8, float:9.53E-43)
        L_0x0a4e:
            printState(r14)
            double r0 = r8.stoppingTrustRegionRadius
            int r2 = (r38 > r0 ? 1 : (r38 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0aa4
            double r0 = r61 * r38
            double r2 = r8.stoppingTrustRegionRadius
            double r2 = r38 / r2
            r25 = 4625196817309499392(0x4030000000000000, double:16.0)
            int r4 = (r2 > r25 ? 1 : (r2 == r25 ? 0 : -1))
            if (r4 > 0) goto L_0x0a68
            double r12 = r8.stoppingTrustRegionRadius
            r109 = r15
            goto L_0x0a81
        L_0x0a68:
            r12 = 4643000109586448384(0x406f400000000000, double:250.0)
            int r4 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r4 > 0) goto L_0x0a7b
            double r12 = java.lang.Math.sqrt(r2)
            r109 = r15
            double r14 = r8.stoppingTrustRegionRadius
            double r12 = r12 * r14
            goto L_0x0a81
        L_0x0a7b:
            r109 = r15
            double r38 = r38 * r63
            r12 = r38
        L_0x0a81:
            double r40 = java.lang.Math.max(r0, r12)
            int r21 = r132.getEvaluations()
            r56 = r2
            r46 = r6
            r14 = r9
            r38 = r12
            r4 = r17
            r30 = r65
            r13 = r105
            r6 = r106
            r15 = r108
            r7 = r109
            r0 = 60
            r2 = 60
            r3 = 20
            goto L_0x0ca8
        L_0x0aa4:
            r109 = r15
            r12 = -1
            if (r13 != r12) goto L_0x0ac0
            r0 = 360(0x168, float:5.04E-43)
            r46 = r6
            r14 = r9
            r12 = r10
            r4 = r13
            r30 = r65
            r13 = r105
            r6 = r106
            r15 = r108
            r7 = r109
        L_0x0aba:
            r2 = 60
            r3 = 20
            goto L_0x0081
        L_0x0ac0:
            r0 = 720(0x2d0, float:1.009E-42)
            printState(r0)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.fAtInterpolationPoints
            int r1 = r8.trustRegionCenterInterpolationPointIndex
            double r0 = r0.getEntry(r1)
            int r2 = (r0 > r32 ? 1 : (r0 == r32 ? 0 : -1))
            if (r2 > 0) goto L_0x0b2c
            r0 = r17
        L_0x0ad3:
            if (r0 >= r11) goto L_0x0b24
            r1 = r133[r0]
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.originShift
            double r3 = r3.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.trustRegionCenterOffset
            double r5 = r5.getEntry(r0)
            double r3 = r3 + r5
            double r1 = java.lang.Math.max(r1, r3)
            r3 = r134[r0]
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.currentBest
            double r1 = java.lang.Math.min(r1, r3)
            r5.setEntry(r0, r1)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trustRegionCenterOffset
            double r1 = r1.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.lowerDifference
            double r3 = r3.getEntry(r0)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0b0a
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.currentBest
            r2 = r133[r0]
            r1.setEntry(r0, r2)
        L_0x0b0a:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trustRegionCenterOffset
            double r1 = r1.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.upperDifference
            double r3 = r3.getEntry(r0)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0b21
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.currentBest
            r2 = r134[r0]
            r1.setEntry(r0, r2)
        L_0x0b21:
            int r0 = r0 + 1
            goto L_0x0ad3
        L_0x0b24:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.fAtInterpolationPoints
            int r1 = r8.trustRegionCenterInterpolationPointIndex
            double r34 = r0.getEntry(r1)
        L_0x0b2c:
            return r34
        L_0x0b2d:
            r106 = r6
            r109 = r7
            r10 = r12
            r105 = r13
            r9 = r14
            r65 = r30
            r6 = r46
            r16 = 210(0xd2, float:2.94E-43)
            r17 = 0
            r22 = 230(0xe6, float:3.22E-43)
            r13 = r4
            r0 = r2
            r26 = r3
            r104 = r5
            r3 = r15
            r111 = r23
            r117 = r36
            r113 = r40
            r5 = r42
            r115 = r44
            r24 = r106
            r12 = r109
            r25 = 60
            goto L_0x0d92
        L_0x0b58:
            r106 = r6
            r109 = r7
            r10 = r12
            r105 = r13
            r9 = r14
            r108 = r15
            r65 = r30
            r76 = r40
            r12 = -1
            r16 = 210(0xd2, float:2.94E-43)
            r17 = 0
            r22 = 230(0xe6, float:3.22E-43)
            r13 = r4
            r7 = r2
            r110 = r5
            goto L_0x0c3a
        L_0x0b73:
            r106 = r6
            r109 = r7
            r10 = r12
            r105 = r13
            r9 = r14
            r108 = r15
            r65 = r30
            r76 = r40
            r12 = -1
            r16 = 210(0xd2, float:2.94E-43)
            r17 = 0
            r22 = 230(0xe6, float:3.22E-43)
            r7 = r3
            r13 = r4
            r4 = r1
            printState(r7)
            int r0 = r8.trustRegionCenterInterpolationPointIndex
            if (r0 == 0) goto L_0x0c36
            r0 = r17
            r1 = r0
        L_0x0b95:
            if (r0 >= r11) goto L_0x0be8
            r2 = r1
            r1 = r17
        L_0x0b9a:
            if (r1 > r0) goto L_0x0be1
            if (r1 >= r0) goto L_0x0bbc
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.gradientAtTrustRegionCenter
            double r23 = r6.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.modelSecondDerivativesValues
            double r25 = r6.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.trustRegionCenterOffset
            double r27 = r6.getEntry(r1)
            double r25 = r25 * r27
            r110 = r5
            double r4 = r23 + r25
            r3.setEntry(r0, r4)
            goto L_0x0bbe
        L_0x0bbc:
            r110 = r5
        L_0x0bbe:
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.gradientAtTrustRegionCenter
            double r4 = r4.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.modelSecondDerivativesValues
            double r23 = r6.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.trustRegionCenterOffset
            double r25 = r6.getEntry(r0)
            double r23 = r23 * r25
            double r4 = r4 + r23
            r3.setEntry(r1, r4)
            int r2 = r2 + 1
            int r1 = r1 + 1
            r5 = r110
            r4 = 1
            goto L_0x0b9a
        L_0x0be1:
            r110 = r5
            int r0 = r0 + 1
            r1 = r2
            r4 = 1
            goto L_0x0b95
        L_0x0be8:
            r110 = r5
            int r0 = r132.getEvaluations()
            if (r0 <= r10) goto L_0x0c38
            r0 = r17
        L_0x0bf2:
            if (r0 >= r10) goto L_0x0c38
            r1 = r17
            r2 = 0
        L_0x0bf8:
            if (r1 >= r11) goto L_0x0c0c
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.interpolationPoints
            double r4 = r4.getEntry(r0, r1)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.trustRegionCenterOffset
            double r23 = r6.getEntry(r1)
            double r4 = r4 * r23
            double r2 = r2 + r4
            int r1 = r1 + 1
            goto L_0x0bf8
        L_0x0c0c:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.modelSecondDerivativesParameters
            double r4 = r1.getEntry(r0)
            double r2 = r2 * r4
            r1 = r17
        L_0x0c15:
            if (r1 >= r11) goto L_0x0c31
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.gradientAtTrustRegionCenter
            double r5 = r5.getEntry(r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r8.interpolationPoints
            double r23 = r7.getEntry(r0, r1)
            double r23 = r23 * r2
            double r5 = r5 + r23
            r4.setEntry(r1, r5)
            int r1 = r1 + 1
            r7 = 20
            goto L_0x0c15
        L_0x0c31:
            int r0 = r0 + 1
            r7 = 20
            goto L_0x0bf2
        L_0x0c36:
            r110 = r5
        L_0x0c38:
            r7 = 60
        L_0x0c3a:
            printState(r7)
            org.apache.commons.math3.linear.ArrayRealVector r3 = new org.apache.commons.math3.linear.ArrayRealVector
            r3.<init>(r11)
            org.apache.commons.math3.linear.ArrayRealVector r4 = new org.apache.commons.math3.linear.ArrayRealVector
            r4.<init>(r11)
            org.apache.commons.math3.linear.ArrayRealVector r5 = new org.apache.commons.math3.linear.ArrayRealVector
            r5.<init>(r11)
            org.apache.commons.math3.linear.ArrayRealVector r6 = new org.apache.commons.math3.linear.ArrayRealVector
            r6.<init>(r11)
            org.apache.commons.math3.linear.ArrayRealVector r1 = new org.apache.commons.math3.linear.ArrayRealVector
            r1.<init>(r11)
            r0 = r8
            r18 = r1
            r23 = 1
            r1 = r76
            r104 = r110
            r24 = r106
            r25 = r7
            r12 = r109
            r26 = 20
            r7 = r18
            double[] r0 = r0.trsbox(r1, r3, r4, r5, r6, r7)
            r1 = r0[r17]
            r3 = r0[r23]
            double r5 = java.lang.Math.sqrt(r1)
            r111 = r1
            r0 = r76
            double r46 = java.lang.Math.min(r0, r5)
            double r5 = r61 * r38
            int r2 = (r46 > r5 ? 1 : (r46 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x0d84
            r5 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r5 = r5 * r38
            double r58 = r5 * r5
            int r2 = r132.getEvaluations()
            int r5 = r21 + 2
            if (r2 > r5) goto L_0x0cab
            r40 = r0
            r14 = r9
            r7 = r12
            r6 = r24
            r2 = r25
            r3 = r26
            r30 = r65
            r5 = r104
            r13 = r105
            r15 = r108
            r23 = r111
        L_0x0ca5:
            r0 = 650(0x28a, float:9.11E-43)
        L_0x0ca7:
            r4 = -1
        L_0x0ca8:
            r12 = r10
            goto L_0x0081
        L_0x0cab:
            r113 = r0
            r5 = r42
            r0 = r44
            double r13 = java.lang.Math.max(r5, r0)
            r115 = r0
            r0 = r36
            double r13 = java.lang.Math.max(r13, r0)
            r27 = 4593671619917905920(0x3fc0000000000000, double:0.125)
            double r27 = r27 * r38
            double r27 = r27 * r38
            r29 = 0
            int r2 = (r3 > r29 ? 1 : (r3 == r29 ? 0 : -1))
            if (r2 <= 0) goto L_0x0cea
            double r27 = r27 * r3
            int r2 = (r13 > r27 ? 1 : (r13 == r27 ? 0 : -1))
            if (r2 <= 0) goto L_0x0cea
            r36 = r0
            r42 = r5
            r14 = r9
            r7 = r12
            r6 = r24
            r2 = r25
            r3 = r26
            r30 = r65
            r5 = r104
            r13 = r105
            r15 = r108
            r23 = r111
            r40 = r113
            r44 = r115
            goto L_0x0ca5
        L_0x0cea:
            double r13 = r13 / r38
            r2 = r17
        L_0x0cee:
            if (r2 >= r11) goto L_0x0d63
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.newPoint
            double r3 = r3.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.lowerDifference
            double r27 = r7.getEntry(r2)
            int r7 = (r3 > r27 ? 1 : (r3 == r27 ? 0 : -1))
            if (r7 != 0) goto L_0x0d07
            r3 = r108
            double r27 = r3.getEntry(r2)
            goto L_0x0d0b
        L_0x0d07:
            r3 = r108
            r27 = r13
        L_0x0d0b:
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.newPoint
            double r29 = r4.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r8.upperDifference
            double r36 = r4.getEntry(r2)
            int r4 = (r29 > r36 ? 1 : (r29 == r36 ? 0 : -1))
            if (r4 != 0) goto L_0x0d25
            r117 = r0
            double r0 = r3.getEntry(r2)
            double r0 = -r0
            r27 = r0
            goto L_0x0d27
        L_0x0d25:
            r117 = r0
        L_0x0d27:
            int r0 = (r27 > r13 ? 1 : (r27 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x0d5c
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.modelSecondDerivativesValues
            int r1 = r2 * r2
            int r1 = r1 + r2
            int r1 = r1 / 2
            double r0 = r0.getEntry(r1)
            r29 = r0
            r0 = r17
        L_0x0d3a:
            if (r0 >= r10) goto L_0x0d51
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r8.interpolationPoints
            double r36 = r1.getEntry(r0, r2)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.modelSecondDerivativesParameters
            double r40 = r1.getEntry(r0)
            double r36 = r36 * r36
            double r40 = r40 * r36
            double r29 = r29 + r40
            int r0 = r0 + 1
            goto L_0x0d3a
        L_0x0d51:
            double r29 = r29 * r61
            double r29 = r29 * r38
            double r27 = r27 + r29
            int r0 = (r27 > r13 ? 1 : (r27 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x0d5c
            goto L_0x0d67
        L_0x0d5c:
            int r2 = r2 + 1
            r108 = r3
            r0 = r117
            goto L_0x0cee
        L_0x0d63:
            r117 = r0
            r3 = r108
        L_0x0d67:
            r15 = r3
            r42 = r5
            r14 = r9
            r7 = r12
            r6 = r24
            r2 = r25
            r3 = r26
            r30 = r65
            r5 = r104
            r13 = r105
            r23 = r111
            r40 = r113
            r44 = r115
            r36 = r117
            r0 = 680(0x2a8, float:9.53E-43)
            goto L_0x0ca7
        L_0x0d84:
            r113 = r0
            r117 = r36
            r5 = r42
            r115 = r44
            r3 = r108
            int r4 = r13 + 1
            r0 = 90
        L_0x0d92:
            printState(r0)
            r1 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            double r1 = r1 * r19
            int r7 = (r111 > r1 ? 1 : (r111 == r1 ? 0 : -1))
            if (r7 > 0) goto L_0x0fec
            r1 = 4598175219545276416(0x3fd0000000000000, double:0.25)
            double r1 = r1 * r19
            r7 = r17
            r13 = 0
        L_0x0da8:
            if (r7 >= r10) goto L_0x0e47
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.modelSecondDerivativesParameters
            double r27 = r0.getEntry(r7)
            double r13 = r13 + r27
            r27 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            double r27 = r27 * r19
            r119 = r5
            r0 = r17
            r5 = r27
        L_0x0dbc:
            if (r0 >= r11) goto L_0x0dd5
            r121 = r9
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.interpolationPoints
            double r27 = r9.getEntry(r7, r0)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r8.trustRegionCenterOffset
            double r29 = r9.getEntry(r0)
            double r27 = r27 * r29
            double r5 = r5 + r27
            int r0 = r0 + 1
            r9 = r121
            goto L_0x0dbc
        L_0x0dd5:
            r121 = r9
            r12.setEntry(r7, r5)
            double r27 = r61 * r5
            double r27 = r1 - r27
            r0 = r17
        L_0x0de0:
            if (r0 >= r11) goto L_0x0e3b
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.bMatrix
            r122 = r13
            double r13 = r9.getEntry(r7, r0)
            r3.setEntry(r0, r13)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r8.lagrangeValuesAtNewPoint
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r8.interpolationPoints
            double r13 = r13.getEntry(r7, r0)
            double r13 = r13 * r5
            r124 = r5
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.trustRegionCenterOffset
            double r5 = r5.getEntry(r0)
            double r5 = r5 * r27
            double r13 = r13 + r5
            r9.setEntry(r0, r13)
            int r5 = r10 + r0
            r6 = r17
        L_0x0e08:
            if (r6 > r0) goto L_0x0e34
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.bMatrix
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r8.bMatrix
            double r13 = r13.getEntry(r5, r6)
            double r29 = r3.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r8.lagrangeValuesAtNewPoint
            double r36 = r15.getEntry(r6)
            double r29 = r29 * r36
            double r13 = r13 + r29
            org.apache.commons.math3.linear.ArrayRealVector r15 = r8.lagrangeValuesAtNewPoint
            double r29 = r15.getEntry(r0)
            double r36 = r3.getEntry(r6)
            double r29 = r29 * r36
            double r13 = r13 + r29
            r9.setEntry(r5, r6, r13)
            int r6 = r6 + 1
            goto L_0x0e08
        L_0x0e34:
            int r0 = r0 + 1
            r13 = r122
            r5 = r124
            goto L_0x0de0
        L_0x0e3b:
            r122 = r13
            int r7 = r7 + 1
            r5 = r119
            r9 = r121
            r0 = 90
            goto L_0x0da8
        L_0x0e47:
            r119 = r5
            r121 = r9
            r5 = r17
            r0 = r105
        L_0x0e4f:
            if (r5 >= r0) goto L_0x0f0a
            r6 = r17
            r18 = 0
            r27 = 0
        L_0x0e57:
            if (r6 >= r10) goto L_0x0e85
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r8.zMatrix
            double r29 = r7.getEntry(r6, r5)
            double r18 = r18 + r29
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.lagrangeValuesAtNewPoint
            double r29 = r12.getEntry(r6)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.zMatrix
            double r36 = r9.getEntry(r6, r5)
            r126 = r12
            r127 = r13
            double r12 = r29 * r36
            r7.setEntry(r6, r12)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.lagrangeValuesAtNewPoint
            double r12 = r7.getEntry(r6)
            double r27 = r27 + r12
            int r6 = r6 + 1
            r12 = r126
            r13 = r127
            goto L_0x0e57
        L_0x0e85:
            r126 = r12
            r127 = r13
            r6 = r17
        L_0x0e8b:
            if (r6 >= r11) goto L_0x0ed7
            double r12 = r1 * r18
            double r14 = r61 * r27
            double r12 = r12 - r14
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.trustRegionCenterOffset
            double r14 = r7.getEntry(r6)
            double r12 = r12 * r14
            r7 = r17
        L_0x0e9b:
            if (r7 >= r10) goto L_0x0eaf
            org.apache.commons.math3.linear.ArrayRealVector r9 = r8.lagrangeValuesAtNewPoint
            double r14 = r9.getEntry(r7)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.interpolationPoints
            double r29 = r9.getEntry(r7, r6)
            double r14 = r14 * r29
            double r12 = r12 + r14
            int r7 = r7 + 1
            goto L_0x0e9b
        L_0x0eaf:
            r3.setEntry(r6, r12)
            r7 = r17
        L_0x0eb4:
            if (r7 >= r10) goto L_0x0ed2
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r8.bMatrix
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r8.bMatrix
            double r14 = r14.getEntry(r7, r6)
            r129 = r0
            org.apache.commons.math3.linear.Array2DRowRealMatrix r0 = r8.zMatrix
            double r29 = r0.getEntry(r7, r5)
            double r29 = r29 * r12
            double r14 = r14 + r29
            r9.setEntry(r7, r6, r14)
            int r7 = r7 + 1
            r0 = r129
            goto L_0x0eb4
        L_0x0ed2:
            r129 = r0
            int r6 = r6 + 1
            goto L_0x0e8b
        L_0x0ed7:
            r129 = r0
            r0 = r17
        L_0x0edb:
            if (r0 >= r11) goto L_0x0f00
            int r12 = r0 + r10
            double r6 = r3.getEntry(r0)
            r9 = r17
        L_0x0ee5:
            if (r9 > r0) goto L_0x0efd
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r8.bMatrix
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r8.bMatrix
            double r14 = r14.getEntry(r12, r9)
            double r18 = r3.getEntry(r9)
            double r18 = r18 * r6
            double r14 = r14 + r18
            r13.setEntry(r12, r9, r14)
            int r9 = r9 + 1
            goto L_0x0ee5
        L_0x0efd:
            int r0 = r0 + 1
            goto L_0x0edb
        L_0x0f00:
            int r5 = r5 + 1
            r12 = r126
            r13 = r127
            r0 = r129
            goto L_0x0e4f
        L_0x0f0a:
            r129 = r0
            r126 = r12
            r127 = r13
            r0 = r17
            r1 = r0
        L_0x0f13:
            if (r0 >= r11) goto L_0x0f91
            r5 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            double r5 = r5 * r127
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.trustRegionCenterOffset
            double r12 = r2.getEntry(r0)
            double r5 = r5 * r12
            r3.setEntry(r0, r5)
            r2 = r17
        L_0x0f25:
            if (r2 >= r10) goto L_0x0f51
            double r5 = r3.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r8.modelSecondDerivativesParameters
            double r12 = r7.getEntry(r2)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r8.interpolationPoints
            double r14 = r7.getEntry(r2, r0)
            double r12 = r12 * r14
            double r5 = r5 + r12
            r3.setEntry(r0, r5)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r8.interpolationPoints
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r8.interpolationPoints
            double r6 = r6.getEntry(r2, r0)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r8.trustRegionCenterOffset
            double r12 = r9.getEntry(r0)
            double r6 = r6 - r12
            r5.setEntry(r2, r0, r6)
            int r2 = r2 + 1
            goto L_0x0f25
        L_0x0f51:
            r2 = r1
            r1 = r17
        L_0x0f54:
            if (r1 > r0) goto L_0x0f8d
            org.apache.commons.math3.linear.ArrayRealVector r5 = r8.modelSecondDerivativesValues
            org.apache.commons.math3.linear.ArrayRealVector r6 = r8.modelSecondDerivativesValues
            double r6 = r6.getEntry(r2)
            double r12 = r3.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r8.trustRegionCenterOffset
            double r14 = r9.getEntry(r0)
            double r12 = r12 * r14
            double r6 = r6 + r12
            org.apache.commons.math3.linear.ArrayRealVector r9 = r8.trustRegionCenterOffset
            double r12 = r9.getEntry(r1)
            double r14 = r3.getEntry(r0)
            double r12 = r12 * r14
            double r6 = r6 + r12
            r5.setEntry(r2, r6)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r8.bMatrix
            int r12 = r10 + r1
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r8.bMatrix
            int r7 = r10 + r0
            double r6 = r6.getEntry(r7, r1)
            r5.setEntry(r12, r0, r6)
            int r2 = r2 + 1
            int r1 = r1 + 1
            goto L_0x0f54
        L_0x0f8d:
            int r0 = r0 + 1
            r1 = r2
            goto L_0x0f13
        L_0x0f91:
            r0 = r17
        L_0x0f93:
            if (r0 >= r11) goto L_0x0fe7
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.originShift
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.originShift
            double r5 = r2.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.trustRegionCenterOffset
            double r12 = r2.getEntry(r0)
            double r5 = r5 + r12
            r1.setEntry(r0, r5)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.newPoint
            double r5 = r2.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.trustRegionCenterOffset
            double r12 = r2.getEntry(r0)
            double r5 = r5 - r12
            r1.setEntry(r0, r5)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.lowerDifference
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.lowerDifference
            double r5 = r2.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.trustRegionCenterOffset
            double r12 = r2.getEntry(r0)
            double r5 = r5 - r12
            r1.setEntry(r0, r5)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.upperDifference
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.upperDifference
            double r5 = r2.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r8.trustRegionCenterOffset
            double r12 = r2.getEntry(r0)
            double r5 = r5 - r12
            r1.setEntry(r0, r5)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r8.trustRegionCenterOffset
            r5 = 0
            r1.setEntry(r0, r5)
            int r0 = r0 + 1
            goto L_0x0f93
        L_0x0fe7:
            r5 = 0
            r19 = r5
            goto L_0x0ff6
        L_0x0fec:
            r119 = r5
            r121 = r9
            r126 = r12
            r129 = r105
            r5 = 0
        L_0x0ff6:
            if (r4 != 0) goto L_0x1018
            r15 = r3
            r12 = r10
            r0 = r16
        L_0x0ffc:
            r6 = r24
            r2 = r25
            r3 = r26
            r30 = r65
            r5 = r104
            r23 = r111
            r40 = r113
            r44 = r115
            r36 = r117
            r42 = r119
            r14 = r121
            r7 = r126
            r13 = r129
            goto L_0x0081
        L_0x1018:
            r15 = r3
            r12 = r10
            r0 = r22
            goto L_0x0ffc
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb(double[], double[]):double");
    }

    /* JADX WARNING: Removed duplicated region for block: B:149:0x0505  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0531 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double[] altmov(int r59, double r60) {
        /*
            r58 = this;
            r0 = r58
            r1 = r59
            printMethod()
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.currentBest
            int r4 = r4.getDimension()
            int r5 = r0.numberOfInterpolationPoints
            org.apache.commons.math3.linear.ArrayRealVector r6 = new org.apache.commons.math3.linear.ArrayRealVector
            r6.<init>(r4)
            org.apache.commons.math3.linear.ArrayRealVector r7 = new org.apache.commons.math3.linear.ArrayRealVector
            r7.<init>(r5)
            org.apache.commons.math3.linear.ArrayRealVector r8 = new org.apache.commons.math3.linear.ArrayRealVector
            r8.<init>(r4)
            org.apache.commons.math3.linear.ArrayRealVector r9 = new org.apache.commons.math3.linear.ArrayRealVector
            r9.<init>(r4)
            r11 = 0
        L_0x0024:
            r12 = 0
            if (r11 >= r5) goto L_0x002e
            r7.setEntry(r11, r12)
            int r11 = r11 + 1
            goto L_0x0024
        L_0x002e:
            int r11 = r5 - r4
            r14 = 1
            int r11 = r11 - r14
            r15 = 0
        L_0x0033:
            if (r15 >= r11) goto L_0x005b
            org.apache.commons.math3.linear.Array2DRowRealMatrix r10 = r0.zMatrix
            double r16 = r10.getEntry(r1, r15)
            r10 = 0
        L_0x003c:
            if (r10 >= r5) goto L_0x0055
            double r18 = r7.getEntry(r10)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r0.zMatrix
            double r21 = r14.getEntry(r10, r15)
            double r21 = r21 * r16
            double r12 = r18 + r21
            r7.setEntry(r10, r12)
            int r10 = r10 + 1
            r12 = 0
            r14 = 1
            goto L_0x003c
        L_0x0055:
            int r15 = r15 + 1
            r12 = 0
            r14 = 1
            goto L_0x0033
        L_0x005b:
            double r10 = r7.getEntry(r1)
            r12 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r14 = r12 * r10
            r12 = 0
        L_0x0064:
            if (r12 >= r4) goto L_0x007a
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r0.bMatrix
            r24 = r9
            r25 = r10
            double r9 = r13.getEntry(r1, r12)
            r6.setEntry(r12, r9)
            int r12 = r12 + 1
            r9 = r24
            r10 = r25
            goto L_0x0064
        L_0x007a:
            r24 = r9
            r25 = r10
            r9 = 0
        L_0x007f:
            if (r9 >= r5) goto L_0x00bd
            r10 = 0
            r11 = 0
        L_0x0084:
            if (r10 >= r4) goto L_0x0099
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r0.interpolationPoints
            double r16 = r13.getEntry(r9, r10)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.trustRegionCenterOffset
            double r18 = r13.getEntry(r10)
            double r16 = r16 * r18
            double r11 = r11 + r16
            int r10 = r10 + 1
            goto L_0x0084
        L_0x0099:
            double r16 = r7.getEntry(r9)
            double r11 = r11 * r16
            r10 = 0
        L_0x00a0:
            if (r10 >= r4) goto L_0x00ba
            double r16 = r6.getEntry(r10)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r0.interpolationPoints
            double r18 = r13.getEntry(r9, r10)
            double r18 = r18 * r11
            r27 = r11
            double r11 = r16 + r18
            r6.setEntry(r10, r11)
            int r10 = r10 + 1
            r11 = r27
            goto L_0x00a0
        L_0x00ba:
            int r9 = r9 + 1
            goto L_0x007f
        L_0x00bd:
            r9 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r29 = r7
            r30 = r8
            r18 = r9
            r9 = 0
            r10 = 0
            r11 = 0
            r13 = 0
            r16 = 0
        L_0x00cc:
            if (r9 >= r5) goto L_0x02a0
            int r7 = r0.trustRegionCenterInterpolationPointIndex
            if (r9 != r7) goto L_0x00da
            r33 = r5
            r34 = r6
            r44 = r9
            goto L_0x0294
        L_0x00da:
            r31 = r11
            r7 = 0
            r11 = 0
            r18 = 0
        L_0x00e1:
            if (r7 >= r4) goto L_0x0100
            org.apache.commons.math3.linear.Array2DRowRealMatrix r8 = r0.interpolationPoints
            double r21 = r8.getEntry(r9, r7)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trustRegionCenterOffset
            double r27 = r8.getEntry(r7)
            double r21 = r21 - r27
            double r27 = r6.getEntry(r7)
            double r27 = r27 * r21
            double r18 = r18 + r27
            double r21 = r21 * r21
            double r11 = r11 + r21
            int r7 = r7 + 1
            goto L_0x00e1
        L_0x0100:
            double r7 = java.lang.Math.sqrt(r11)
            double r7 = r60 / r7
            r33 = r5
            r34 = r6
            double r5 = -r7
            r35 = r5
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r2 = java.lang.Math.min(r5, r7)
            r5 = 0
            r6 = 0
            r21 = 0
        L_0x0117:
            if (r5 >= r4) goto L_0x01ea
            r37 = r6
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r0.interpolationPoints
            double r27 = r6.getEntry(r9, r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r38 = r6.getEntry(r5)
            double r27 = r27 - r38
            r22 = 0
            int r6 = (r27 > r22 ? 1 : (r27 == r22 ? 0 : -1))
            if (r6 <= 0) goto L_0x0188
            double r38 = r35 * r27
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.lowerDifference
            double r40 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r42 = r6.getEntry(r5)
            double r40 = r40 - r42
            int r6 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
            if (r6 >= 0) goto L_0x015a
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.lowerDifference
            double r35 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r37 = r6.getEntry(r5)
            double r35 = r35 - r37
            double r35 = r35 / r27
            int r6 = -r5
            r20 = 1
            int r6 = r6 + -1
            r37 = r6
        L_0x015a:
            double r38 = r7 * r27
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.upperDifference
            double r40 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r42 = r6.getEntry(r5)
            double r40 = r40 - r42
            int r6 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
            if (r6 <= 0) goto L_0x01e4
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.upperDifference
            double r6 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trustRegionCenterOffset
            double r21 = r8.getEntry(r5)
            double r6 = r6 - r21
            double r6 = r6 / r27
            double r6 = java.lang.Math.max(r2, r6)
            int r8 = r5 + 1
        L_0x0184:
            r21 = r8
            r7 = r6
            goto L_0x01e4
        L_0x0188:
            r22 = 0
            int r6 = (r27 > r22 ? 1 : (r27 == r22 ? 0 : -1))
            if (r6 >= 0) goto L_0x01e4
            double r38 = r35 * r27
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.upperDifference
            double r40 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r42 = r6.getEntry(r5)
            double r40 = r40 - r42
            int r6 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
            if (r6 <= 0) goto L_0x01b6
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.upperDifference
            double r35 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r37 = r6.getEntry(r5)
            double r35 = r35 - r37
            double r35 = r35 / r27
            int r6 = r5 + 1
            r37 = r6
        L_0x01b6:
            double r38 = r7 * r27
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.lowerDifference
            double r40 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r42 = r6.getEntry(r5)
            double r40 = r40 - r42
            int r6 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
            if (r6 >= 0) goto L_0x01e4
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.lowerDifference
            double r6 = r6.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trustRegionCenterOffset
            double r21 = r8.getEntry(r5)
            double r6 = r6 - r21
            double r6 = r6 / r27
            double r6 = java.lang.Math.max(r2, r6)
            int r8 = -r5
            r20 = 1
            int r8 = r8 + -1
            goto L_0x0184
        L_0x01e4:
            r6 = r37
            int r5 = r5 + 1
            goto L_0x0117
        L_0x01ea:
            r37 = r6
            if (r9 != r1) goto L_0x023c
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = r18 - r2
            double r2 = r35 * r5
            double r27 = r18 - r2
            double r0 = r35 * r27
            double r27 = r7 * r5
            double r38 = r18 - r27
            r44 = r9
            r45 = r10
            double r9 = r7 * r38
            double r38 = java.lang.Math.abs(r9)
            double r40 = java.lang.Math.abs(r0)
            int r22 = (r38 > r40 ? 1 : (r38 == r40 ? 0 : -1))
            if (r22 <= 0) goto L_0x0212
            r0 = r9
            r10 = r21
            goto L_0x0216
        L_0x0212:
            r7 = r35
            r10 = r37
        L_0x0216:
            r21 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r18 = r18 * r21
            double r2 = r18 - r2
            double r21 = r18 - r27
            double r2 = r2 * r21
            r21 = 0
            int r9 = (r2 > r21 ? 1 : (r2 == r21 ? 0 : -1))
            if (r9 >= 0) goto L_0x0239
            double r2 = r18 * r18
            double r2 = r2 / r5
            double r21 = java.lang.Math.abs(r2)
            double r27 = java.lang.Math.abs(r0)
            int r9 = (r21 > r27 ? 1 : (r21 == r27 ? 0 : -1))
            if (r9 <= 0) goto L_0x0239
            double r7 = r18 / r5
            r0 = r2
            r10 = 0
        L_0x0239:
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0278
        L_0x023c:
            r44 = r9
            r45 = r10
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r2 = r0 - r35
            double r2 = r2 * r35
            double r5 = r0 - r7
            double r0 = r7 * r5
            double r5 = java.lang.Math.abs(r0)
            double r9 = java.lang.Math.abs(r2)
            int r22 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r22 <= 0) goto L_0x025b
            r35 = r7
            r10 = r21
            goto L_0x025e
        L_0x025b:
            r0 = r2
            r10 = r37
        L_0x025e:
            r2 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r5 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            r2 = 4598175219545276416(0x3fd0000000000000, double:0.25)
            if (r5 <= 0) goto L_0x0273
            double r5 = java.lang.Math.abs(r0)
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x0273
            r0 = r2
            r7 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r10 = 0
            goto L_0x0275
        L_0x0273:
            r7 = r35
        L_0x0275:
            double r0 = r0 * r18
            goto L_0x0239
        L_0x0278:
            double r2 = r2 - r7
            double r2 = r2 * r7
            double r2 = r2 * r11
            double r0 = r0 * r0
            double r5 = r14 * r2
            double r5 = r5 * r2
            double r5 = r5 + r0
            double r0 = r0 * r5
            int r2 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r2 <= 0) goto L_0x028e
            r16 = r0
            r11 = r7
            r18 = r11
            r13 = r10
            r10 = r44
            goto L_0x0294
        L_0x028e:
            r18 = r7
            r11 = r31
            r10 = r45
        L_0x0294:
            int r9 = r44 + 1
            r5 = r33
            r6 = r34
            r0 = r58
            r1 = r59
            goto L_0x00cc
        L_0x02a0:
            r33 = r5
            r34 = r6
            r45 = r10
            r31 = r11
            r0 = 0
        L_0x02a9:
            if (r0 >= r4) goto L_0x02e1
            r1 = r58
            org.apache.commons.math3.linear.ArrayRealVector r2 = r1.trustRegionCenterOffset
            double r2 = r2.getEntry(r0)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r1.interpolationPoints
            r10 = r45
            double r5 = r5.getEntry(r10, r0)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r1.trustRegionCenterOffset
            double r7 = r7.getEntry(r0)
            double r5 = r5 - r7
            double r11 = r31 * r5
            double r2 = r2 + r11
            org.apache.commons.math3.linear.ArrayRealVector r5 = r1.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r6 = r1.lowerDifference
            double r6 = r6.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r1.upperDifference
            double r8 = r8.getEntry(r0)
            double r2 = java.lang.Math.min(r8, r2)
            double r2 = java.lang.Math.max(r6, r2)
            r5.setEntry(r0, r2)
            int r0 = r0 + 1
            goto L_0x02a9
        L_0x02e1:
            r1 = r58
            if (r13 >= 0) goto L_0x02f4
            org.apache.commons.math3.linear.ArrayRealVector r0 = r1.newPoint
            int r2 = -r13
            r3 = 1
            int r2 = r2 - r3
            org.apache.commons.math3.linear.ArrayRealVector r5 = r1.lowerDifference
            double r5 = r5.getEntry(r2)
            r0.setEntry(r2, r5)
            goto L_0x02f5
        L_0x02f4:
            r3 = 1
        L_0x02f5:
            if (r13 <= 0) goto L_0x0303
            org.apache.commons.math3.linear.ArrayRealVector r0 = r1.newPoint
            int r13 = r13 - r3
            org.apache.commons.math3.linear.ArrayRealVector r2 = r1.upperDifference
            double r2 = r2.getEntry(r13)
            r0.setEntry(r13, r2)
        L_0x0303:
            double r5 = r60 + r60
            r0 = 0
            r7 = 0
        L_0x0308:
            r9 = 0
            r10 = 0
        L_0x030b:
            if (r9 >= r4) goto L_0x035c
            r12 = r34
            double r13 = r12.getEntry(r9)
            r46 = r7
            r15 = r30
            r7 = 0
            r15.setEntry(r9, r7)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r1.trustRegionCenterOffset
            double r7 = r7.getEntry(r9)
            r48 = r0
            org.apache.commons.math3.linear.ArrayRealVector r0 = r1.lowerDifference
            double r16 = r0.getEntry(r9)
            double r7 = r7 - r16
            double r7 = java.lang.Math.min(r7, r13)
            r16 = 0
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 > 0) goto L_0x034c
            org.apache.commons.math3.linear.ArrayRealVector r0 = r1.trustRegionCenterOffset
            double r7 = r0.getEntry(r9)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r1.upperDifference
            double r21 = r0.getEntry(r9)
            double r7 = r7 - r21
            double r7 = java.lang.Math.max(r7, r13)
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 >= 0) goto L_0x0351
        L_0x034c:
            r15.setEntry(r9, r5)
            double r13 = r13 * r13
            double r10 = r10 + r13
        L_0x0351:
            int r9 = r9 + 1
            r34 = r12
            r30 = r15
            r7 = r46
            r0 = r48
            goto L_0x030b
        L_0x035c:
            r48 = r0
            r46 = r7
            r15 = r30
            r12 = r34
            r7 = 0
            int r0 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            r9 = 2
            if (r0 != 0) goto L_0x0374
            double[] r0 = new double[r9]
            r2 = 0
            r0[r2] = r25
            r2 = 1
            r0[r2] = r7
            return r0
        L_0x0374:
            double r13 = r60 * r60
            double r13 = r13 - r7
            int r0 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x03d9
            double r13 = r13 / r10
            double r7 = java.lang.Math.sqrt(r13)
            r0 = 0
        L_0x0381:
            if (r0 >= r4) goto L_0x03db
            double r10 = r15.getEntry(r0)
            int r13 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r13 != 0) goto L_0x03d6
            org.apache.commons.math3.linear.ArrayRealVector r10 = r1.trustRegionCenterOffset
            double r10 = r10.getEntry(r0)
            double r13 = r12.getEntry(r0)
            double r13 = r13 * r7
            double r10 = r10 - r13
            org.apache.commons.math3.linear.ArrayRealVector r13 = r1.lowerDifference
            double r13 = r13.getEntry(r0)
            int r16 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r16 > 0) goto L_0x03b5
            org.apache.commons.math3.linear.ArrayRealVector r10 = r1.lowerDifference
            double r10 = r10.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r1.trustRegionCenterOffset
            double r13 = r13.getEntry(r0)
            double r10 = r10 - r13
            r15.setEntry(r0, r10)
            r15.getEntry(r0)
            goto L_0x03d6
        L_0x03b5:
            org.apache.commons.math3.linear.ArrayRealVector r13 = r1.upperDifference
            double r13 = r13.getEntry(r0)
            int r16 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r16 < 0) goto L_0x03d3
            org.apache.commons.math3.linear.ArrayRealVector r10 = r1.upperDifference
            double r10 = r10.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r1.trustRegionCenterOffset
            double r13 = r13.getEntry(r0)
            double r10 = r10 - r13
            r15.setEntry(r0, r10)
            r15.getEntry(r0)
            goto L_0x03d6
        L_0x03d3:
            r12.getEntry(r0)
        L_0x03d6:
            int r0 = r0 + 1
            goto L_0x0381
        L_0x03d9:
            r7 = r18
        L_0x03db:
            r0 = 0
            r10 = 0
        L_0x03de:
            if (r0 >= r4) goto L_0x0459
            double r13 = r12.getEntry(r0)
            double r16 = r15.getEntry(r0)
            int r18 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
            if (r18 != 0) goto L_0x041b
            r49 = r10
            double r9 = -r7
            double r9 = r9 * r13
            r15.setEntry(r0, r9)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r1.upperDifference
            double r9 = r9.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r1.trustRegionCenterOffset
            double r16 = r11.getEntry(r0)
            double r18 = r15.getEntry(r0)
            double r2 = r16 + r18
            double r2 = java.lang.Math.min(r9, r2)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r1.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r10 = r1.lowerDifference
            double r10 = r10.getEntry(r0)
            double r2 = java.lang.Math.max(r10, r2)
            r9.setEntry(r0, r2)
            r22 = 0
            goto L_0x044e
        L_0x041b:
            r49 = r10
            double r2 = r15.getEntry(r0)
            r22 = 0
            int r9 = (r2 > r22 ? 1 : (r2 == r22 ? 0 : -1))
            if (r9 != 0) goto L_0x0433
            org.apache.commons.math3.linear.ArrayRealVector r2 = r1.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r3 = r1.trustRegionCenterOffset
            double r9 = r3.getEntry(r0)
            r2.setEntry(r0, r9)
            goto L_0x044e
        L_0x0433:
            int r2 = (r13 > r22 ? 1 : (r13 == r22 ? 0 : -1))
            if (r2 <= 0) goto L_0x0443
            org.apache.commons.math3.linear.ArrayRealVector r2 = r1.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r3 = r1.lowerDifference
            double r9 = r3.getEntry(r0)
            r2.setEntry(r0, r9)
            goto L_0x044e
        L_0x0443:
            org.apache.commons.math3.linear.ArrayRealVector r2 = r1.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r3 = r1.upperDifference
            double r9 = r3.getEntry(r0)
            r2.setEntry(r0, r9)
        L_0x044e:
            double r2 = r15.getEntry(r0)
            double r13 = r13 * r2
            double r10 = r49 + r13
            int r0 = r0 + 1
            r9 = 2
            goto L_0x03de
        L_0x0459:
            r49 = r10
            r22 = 0
            r9 = r22
            r0 = r33
            r2 = 0
        L_0x0462:
            if (r2 >= r0) goto L_0x0489
            r13 = r22
            r3 = 0
        L_0x0467:
            if (r3 >= r4) goto L_0x047a
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r1.interpolationPoints
            double r16 = r11.getEntry(r2, r3)
            double r18 = r15.getEntry(r3)
            double r16 = r16 * r18
            double r13 = r13 + r16
            int r3 = r3 + 1
            goto L_0x0467
        L_0x047a:
            r3 = r29
            double r16 = r3.getEntry(r2)
            double r16 = r16 * r13
            double r16 = r16 * r13
            double r9 = r9 + r16
            int r2 = r2 + 1
            goto L_0x0462
        L_0x0489:
            r3 = r29
            r2 = r48
            r11 = 1
            if (r2 != r11) goto L_0x0491
            double r9 = -r9
        L_0x0491:
            r51 = r5
            r13 = r9
            r10 = r49
            double r5 = -r10
            int r9 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r9 <= 0) goto L_0x04f4
            r53 = r7
            r7 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r7 = java.lang.Math.sqrt(r7)
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = r16 + r7
            double r7 = r7 * r5
            int r9 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x04ed
            double r5 = r5 / r13
            r7 = 0
        L_0x04ae:
            if (r7 >= r4) goto L_0x04e2
            org.apache.commons.math3.linear.ArrayRealVector r8 = r1.trustRegionCenterOffset
            double r8 = r8.getEntry(r7)
            double r13 = r15.getEntry(r7)
            double r13 = r13 * r5
            double r8 = r8 + r13
            org.apache.commons.math3.linear.ArrayRealVector r13 = r1.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r14 = r1.lowerDifference
            r55 = r15
            double r14 = r14.getEntry(r7)
            r56 = r0
            org.apache.commons.math3.linear.ArrayRealVector r0 = r1.upperDifference
            double r0 = r0.getEntry(r7)
            double r0 = java.lang.Math.min(r0, r8)
            double r0 = java.lang.Math.max(r14, r0)
            r13.setEntry(r7, r0)
            int r7 = r7 + 1
            r15 = r55
            r0 = r56
            r1 = r58
            goto L_0x04ae
        L_0x04e2:
            r56 = r0
            r55 = r15
            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r7 = r0 * r10
            double r7 = r7 * r5
            double r7 = r7 * r7
            goto L_0x0503
        L_0x04ed:
            r56 = r0
            r55 = r15
            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            goto L_0x04fe
        L_0x04f4:
            r56 = r0
            r53 = r7
            r55 = r15
            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x04fe:
            double r5 = r0 * r13
            double r10 = r10 + r5
            double r10 = r10 * r10
            r7 = r10
        L_0x0503:
            if (r2 != 0) goto L_0x0531
            r2 = 0
        L_0x0506:
            if (r2 >= r4) goto L_0x0520
            double r5 = r12.getEntry(r2)
            double r5 = -r5
            r12.setEntry(r2, r5)
            r5 = r58
            org.apache.commons.math3.linear.ArrayRealVector r6 = r5.alternativeNewPoint
            double r9 = r6.getEntry(r2)
            r6 = r24
            r6.setEntry(r2, r9)
            int r2 = r2 + 1
            goto L_0x0506
        L_0x0520:
            r29 = r3
            r34 = r12
            r5 = r51
            r18 = r53
            r30 = r55
            r33 = r56
            r0 = 1
            r1 = r58
            goto L_0x0308
        L_0x0531:
            r6 = r24
            r5 = r58
            int r0 = (r46 > r7 ? 1 : (r46 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x054a
            r0 = 0
        L_0x053a:
            if (r0 >= r4) goto L_0x0548
            org.apache.commons.math3.linear.ArrayRealVector r1 = r5.alternativeNewPoint
            double r2 = r6.getEntry(r0)
            r1.setEntry(r0, r2)
            int r0 = r0 + 1
            goto L_0x053a
        L_0x0548:
            r7 = r46
        L_0x054a:
            r0 = 2
            double[] r0 = new double[r0]
            r1 = 0
            r0[r1] = r25
            r1 = 1
            r0[r1] = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov(int, double):double[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x038b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01ec  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x038c A[LOOP:6: B:19:0x0089->B:94:0x038c, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void prelim(double[] r49, double[] r50) {
        /*
            r48 = this;
            r0 = r48
            printMethod()
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.currentBest
            int r3 = r3.getDimension()
            int r4 = r0.numberOfInterpolationPoints
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.bMatrix
            int r5 = r5.getRowDimension()
            double r6 = r0.initialTrustRegionRadius
            double r8 = r0.initialTrustRegionRadius
            double r6 = r6 * r8
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r10 = r8 / r6
            int r12 = r3 + 1
            r14 = 0
        L_0x001f:
            if (r14 >= r3) goto L_0x0053
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.originShift
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.currentBest
            double r8 = r13.getEntry(r14)
            r15.setEntry(r14, r8)
            r8 = 0
        L_0x002d:
            if (r8 >= r4) goto L_0x003d
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r0.interpolationPoints
            r22 = r10
            r10 = 0
            r9.setEntry(r8, r14, r10)
            int r8 = r8 + 1
            r10 = r22
            goto L_0x002d
        L_0x003d:
            r22 = r10
            r10 = 0
            r8 = 0
        L_0x0042:
            if (r8 >= r5) goto L_0x004e
            org.apache.commons.math3.linear.Array2DRowRealMatrix r9 = r0.bMatrix
            r9.setEntry(r8, r14, r10)
            int r8 = r8 + 1
            r10 = 0
            goto L_0x0042
        L_0x004e:
            int r14 = r14 + 1
            r10 = r22
            goto L_0x001f
        L_0x0053:
            r22 = r10
            int r5 = r3 * r12
            r8 = 2
            int r5 = r5 / r8
            r9 = 0
        L_0x005a:
            if (r9 >= r5) goto L_0x0066
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.modelSecondDerivativesValues
            r13 = 0
            r10.setEntry(r9, r13)
            int r9 = r9 + 1
            goto L_0x005a
        L_0x0066:
            r13 = 0
            r5 = 0
        L_0x0069:
            if (r5 >= r4) goto L_0x0084
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.modelSecondDerivativesParameters
            r9.setEntry(r5, r13)
            int r9 = r4 - r12
            r10 = 0
        L_0x0073:
            if (r10 >= r9) goto L_0x007f
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.zMatrix
            r11.setEntry(r5, r10, r13)
            int r10 = r10 + 1
            r13 = 0
            goto L_0x0073
        L_0x007f:
            int r5 = r5 + 1
            r13 = 0
            goto L_0x0069
        L_0x0084:
            r9 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r10 = r9
            r5 = 0
            r9 = 0
        L_0x0089:
            int r13 = r48.getEvaluations()
            int r14 = r13 - r3
            int r15 = r13 + -1
            r24 = r5
            int r5 = r14 + -1
            r25 = r9
            int r9 = r8 * r3
            r26 = r10
            r8 = 1
            if (r13 > r9) goto L_0x012b
            if (r13 < r8) goto L_0x00c4
            if (r13 > r3) goto L_0x00c4
            r28 = r9
            double r8 = r0.initialTrustRegionRadius
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.upperDifference
            double r10 = r10.getEntry(r15)
            r20 = 0
            int r16 = (r10 > r20 ? 1 : (r10 == r20 ? 0 : -1))
            if (r16 != 0) goto L_0x00b3
            double r8 = -r8
        L_0x00b3:
            org.apache.commons.math3.linear.Array2DRowRealMatrix r10 = r0.interpolationPoints
            r10.setEntry(r13, r15, r8)
            r34 = r5
            r36 = r6
            r6 = r8
            r35 = r14
            r5 = r24
            r9 = r25
            goto L_0x0128
        L_0x00c4:
            r28 = r9
            if (r13 <= r3) goto L_0x011c
            org.apache.commons.math3.linear.Array2DRowRealMatrix r8 = r0.interpolationPoints
            double r8 = r8.getEntry(r14, r5)
            double r10 = r0.initialTrustRegionRadius
            double r10 = -r10
            r32 = r8
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.lowerDifference
            double r8 = r8.getEntry(r5)
            r20 = 0
            int r16 = (r8 > r20 ? 1 : (r8 == r20 ? 0 : -1))
            if (r16 != 0) goto L_0x00ee
            double r8 = r0.initialTrustRegionRadius
            r10 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r8 = r8 * r10
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.upperDifference
            double r10 = r10.getEntry(r5)
            double r10 = java.lang.Math.min(r8, r10)
        L_0x00ee:
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.upperDifference
            double r8 = r8.getEntry(r5)
            int r16 = (r8 > r20 ? 1 : (r8 == r20 ? 0 : -1))
            if (r16 != 0) goto L_0x0108
            r8 = -4611686018427387904(0xc000000000000000, double:-2.0)
            double r10 = r0.initialTrustRegionRadius
            double r8 = r8 * r10
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.lowerDifference
            double r10 = r10.getEntry(r5)
            double r8 = java.lang.Math.max(r8, r10)
            goto L_0x0109
        L_0x0108:
            r8 = r10
        L_0x0109:
            org.apache.commons.math3.linear.Array2DRowRealMatrix r10 = r0.interpolationPoints
            r10.setEntry(r13, r5, r8)
            r34 = r5
            r36 = r6
            r10 = r8
            r35 = r14
            r5 = r24
            r9 = r25
            r6 = r32
            goto L_0x0160
        L_0x011c:
            r34 = r5
            r36 = r6
            r35 = r14
            r5 = r24
            r9 = r25
        L_0x0126:
            r6 = 0
        L_0x0128:
            r10 = 0
            goto L_0x0160
        L_0x012b:
            r28 = r9
            int r8 = r13 - r12
            int r8 = r8 / r3
            int r9 = r8 * r3
            int r9 = r13 - r9
            int r9 = r9 - r3
            int r8 = r8 + r9
            if (r8 <= r3) goto L_0x013e
            int r8 = r8 - r3
            r47 = r9
            r9 = r8
            r8 = r47
        L_0x013e:
            int r10 = r8 + -1
            int r11 = r9 + -1
            r34 = r5
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.interpolationPoints
            r35 = r14
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r0.interpolationPoints
            r36 = r6
            double r6 = r14.getEntry(r8, r10)
            r5.setEntry(r13, r10, r6)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.interpolationPoints
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r0.interpolationPoints
            double r6 = r6.getEntry(r9, r11)
            r5.setEntry(r13, r11, r6)
            r5 = r8
            goto L_0x0126
        L_0x0160:
            r8 = 0
        L_0x0161:
            if (r8 >= r3) goto L_0x01c3
            org.apache.commons.math3.linear.ArrayRealVector r14 = r0.currentBest
            r38 = r9
            r39 = r10
            r9 = r49[r8]
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.originShift
            double r24 = r11.getEntry(r8)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.interpolationPoints
            double r32 = r11.getEntry(r13, r8)
            r41 = r4
            r42 = r5
            double r4 = r24 + r32
            double r4 = java.lang.Math.max(r9, r4)
            r9 = r50[r8]
            double r4 = java.lang.Math.min(r4, r9)
            r14.setEntry(r8, r4)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.interpolationPoints
            double r4 = r4.getEntry(r13, r8)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.lowerDifference
            double r9 = r9.getEntry(r8)
            int r11 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x01a1
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.currentBest
            r9 = r49[r8]
            r4.setEntry(r8, r9)
        L_0x01a1:
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.interpolationPoints
            double r4 = r4.getEntry(r13, r8)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.upperDifference
            double r9 = r9.getEntry(r8)
            int r11 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x01b8
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.currentBest
            r9 = r50[r8]
            r4.setEntry(r8, r9)
        L_0x01b8:
            int r8 = r8 + 1
            r9 = r38
            r10 = r39
            r4 = r41
            r5 = r42
            goto L_0x0161
        L_0x01c3:
            r41 = r4
            r42 = r5
            r38 = r9
            r39 = r10
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.currentBest
            double[] r4 = r4.toArray()
            double r4 = r0.computeObjectiveValue(r4)
            boolean r8 = r0.isMinimize
            if (r8 == 0) goto L_0x01da
            goto L_0x01db
        L_0x01da:
            double r4 = -r4
        L_0x01db:
            int r8 = r48.getEvaluations()
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.fAtInterpolationPoints
            r9.setEntry(r13, r4)
            r9 = 1
            if (r8 != r9) goto L_0x01ec
            r9 = 0
            r0.trustRegionCenterInterpolationPointIndex = r9
            r10 = r4
            goto L_0x01fc
        L_0x01ec:
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.fAtInterpolationPoints
            int r10 = r0.trustRegionCenterInterpolationPointIndex
            double r9 = r9.getEntry(r10)
            int r11 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x01fa
            r0.trustRegionCenterInterpolationPointIndex = r13
        L_0x01fa:
            r10 = r26
        L_0x01fc:
            int r9 = r28 + 1
            if (r8 > r9) goto L_0x032b
            r24 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            r9 = 2
            if (r8 < r9) goto L_0x023e
            if (r8 > r12) goto L_0x023e
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.gradientAtTrustRegionCenter
            double r4 = r4 - r10
            double r4 = r4 / r6
            r9.setEntry(r15, r4)
            int r8 = r8 + r3
            r9 = r41
            if (r9 >= r8) goto L_0x022d
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r4 = r16 / r6
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r0.bMatrix
            double r7 = -r4
            r14 = 0
            r6.setEntry(r14, r15, r7)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r0.bMatrix
            r6.setEntry(r13, r15, r4)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.bMatrix
            int r5 = r9 + r15
            double r6 = r24 * r36
            r4.setEntry(r5, r15, r6)
            goto L_0x022f
        L_0x022d:
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x022f:
            r43 = r3
            r46 = r9
            r44 = r10
            r8 = r22
            r6 = r38
            r3 = r42
            r7 = 0
            goto L_0x0329
        L_0x023e:
            r9 = r41
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r14 = r3 + 2
            if (r8 < r14) goto L_0x031c
            int r14 = r35 + 1
            int r14 = r14 * r35
            r8 = 2
            int r14 = r14 / r8
            r8 = 1
            int r14 = r14 - r8
            double r26 = r4 - r10
            double r26 = r26 / r39
            double r28 = r39 - r6
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.modelSecondDerivativesValues
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.gradientAtTrustRegionCenter
            r1 = r34
            double r32 = r15.getEntry(r1)
            double r32 = r26 - r32
            r30 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r32 = r32 * r30
            r43 = r3
            double r2 = r32 / r28
            r8.setEntry(r14, r2)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r0.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.gradientAtTrustRegionCenter
            double r14 = r3.getEntry(r1)
            double r14 = r14 * r39
            double r26 = r26 * r6
            double r14 = r14 - r26
            double r14 = r14 / r28
            r2.setEntry(r1, r14)
            double r2 = r6 * r39
            r14 = 0
            int r8 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r8 >= 0) goto L_0x02be
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.fAtInterpolationPoints
            r14 = r35
            double r20 = r8.getEntry(r14)
            int r8 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r8 >= 0) goto L_0x02b9
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.fAtInterpolationPoints
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.fAtInterpolationPoints
            r46 = r9
            r44 = r10
            double r9 = r15.getEntry(r14)
            r8.setEntry(r13, r9)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.fAtInterpolationPoints
            r8.setEntry(r14, r4)
            int r4 = r0.trustRegionCenterInterpolationPointIndex
            if (r4 != r13) goto L_0x02ac
            r0.trustRegionCenterInterpolationPointIndex = r14
        L_0x02ac:
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.interpolationPoints
            r8 = r39
            r4.setEntry(r14, r1, r8)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.interpolationPoints
            r4.setEntry(r13, r1, r6)
            goto L_0x02c6
        L_0x02b9:
            r46 = r9
            r44 = r10
            goto L_0x02c4
        L_0x02be:
            r46 = r9
            r44 = r10
            r14 = r35
        L_0x02c4:
            r8 = r39
        L_0x02c6:
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.bMatrix
            double r6 = r6 + r8
            double r5 = -r6
            double r5 = r5 / r2
            r7 = 0
            r4.setEntry(r7, r1, r5)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.bMatrix
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.interpolationPoints
            double r5 = r5.getEntry(r14, r1)
            double r5 = r24 / r5
            r4.setEntry(r13, r1, r5)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.bMatrix
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.bMatrix
            double r5 = r5.getEntry(r7, r1)
            double r5 = -r5
            org.apache.commons.math3.linear.Array2DRowRealMatrix r8 = r0.bMatrix
            double r8 = r8.getEntry(r13, r1)
            double r5 = r5 - r8
            r4.setEntry(r14, r1, r5)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.zMatrix
            r5 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r5 = java.lang.Math.sqrt(r5)
            double r5 = r5 / r2
            r4.setEntry(r7, r1, r5)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r2 = r0.zMatrix
            r3 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r3 = java.lang.Math.sqrt(r3)
            double r3 = r3 / r36
            r2.setEntry(r13, r1, r3)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r2 = r0.zMatrix
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r0.zMatrix
            double r3 = r3.getEntry(r7, r1)
            double r3 = -r3
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.zMatrix
            double r5 = r5.getEntry(r13, r1)
            double r3 = r3 - r5
            r2.setEntry(r14, r1, r3)
            goto L_0x0323
        L_0x031c:
            r43 = r3
            r46 = r9
            r44 = r10
            r7 = 0
        L_0x0323:
            r8 = r22
            r6 = r38
            r3 = r42
        L_0x0329:
            r10 = 2
            goto L_0x0383
        L_0x032b:
            r43 = r3
            r44 = r10
            r1 = r34
            r46 = r41
            r7 = 0
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r2 = r0.zMatrix
            r8 = r22
            r2.setEntry(r7, r1, r8)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r2 = r0.zMatrix
            r2.setEntry(r13, r1, r8)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r2 = r0.zMatrix
            double r10 = -r8
            r3 = r42
            r2.setEntry(r3, r1, r10)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r2 = r0.zMatrix
            r6 = r38
            r2.setEntry(r6, r1, r10)
            int r1 = r3 + -1
            int r2 = r3 * r1
            r10 = 2
            int r2 = r2 / r10
            int r2 = r2 + r6
            r11 = 1
            int r2 = r2 - r11
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.interpolationPoints
            double r14 = r11.getEntry(r13, r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r0.interpolationPoints
            int r11 = r6 + -1
            double r18 = r1.getEntry(r13, r11)
            double r14 = r14 * r18
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.modelSecondDerivativesValues
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.fAtInterpolationPoints
            double r18 = r11.getEntry(r3)
            double r18 = r44 - r18
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.fAtInterpolationPoints
            double r20 = r11.getEntry(r6)
            double r18 = r18 - r20
            double r18 = r18 + r4
            double r4 = r18 / r14
            r1.setEntry(r2, r4)
        L_0x0383:
            int r1 = r48.getEvaluations()
            r2 = r46
            if (r1 < r2) goto L_0x038c
            return
        L_0x038c:
            r4 = r2
            r5 = r3
            r22 = r8
            r8 = r10
            r3 = r43
            r10 = r44
            r9 = r6
            r6 = r36
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim(double[], double[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:224:0x07d0  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x07ef  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double[] trsbox(double r98, org.apache.commons.math3.linear.ArrayRealVector r100, org.apache.commons.math3.linear.ArrayRealVector r101, org.apache.commons.math3.linear.ArrayRealVector r102, org.apache.commons.math3.linear.ArrayRealVector r103, org.apache.commons.math3.linear.ArrayRealVector r104) {
        /*
            r97 = this;
            r0 = r97
            r1 = r100
            r2 = r101
            r3 = r102
            r4 = r103
            r5 = r104
            printMethod()
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.currentBest
            int r6 = r6.getDimension()
            int r7 = r0.numberOfInterpolationPoints
            r9 = 0
            r10 = 0
        L_0x0019:
            r11 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r13 = 0
            if (r9 >= r6) goto L_0x007a
            r2.setEntry(r9, r13)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r15 = r15.getEntry(r9)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.lowerDifference
            double r20 = r8.getEntry(r9)
            int r8 = (r15 > r20 ? 1 : (r15 == r20 ? 0 : -1))
            if (r8 > 0) goto L_0x0040
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.gradientAtTrustRegionCenter
            double r15 = r8.getEntry(r9)
            int r8 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r8 < 0) goto L_0x005f
            r2.setEntry(r9, r11)
            goto L_0x005f
        L_0x0040:
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trustRegionCenterOffset
            double r11 = r8.getEntry(r9)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.upperDifference
            double r15 = r8.getEntry(r9)
            int r8 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r8 < 0) goto L_0x005f
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.gradientAtTrustRegionCenter
            double r11 = r8.getEntry(r9)
            int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r8 > 0) goto L_0x005f
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r2.setEntry(r9, r11)
        L_0x005f:
            double r11 = r2.getEntry(r9)
            int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x0069
            int r10 = r10 + 1
        L_0x0069:
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trialStepPoint
            r8.setEntry(r9, r13)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.gradientAtTrustRegionCenter
            double r11 = r8.getEntry(r9)
            r1.setEntry(r9, r11)
            int r9 = r9 + 1
            goto L_0x0019
        L_0x007a:
            double r8 = r98 * r98
            r11 = 90
            r12 = 50
            r13 = 30
            r14 = 20
            r15 = 190(0xbe, float:2.66E-43)
            r31 = r10
            r16 = 0
            r20 = 0
            r32 = 0
            r33 = 0
            r35 = 0
            r37 = 0
            r39 = 0
            r41 = 0
            r43 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r45 = 0
            r46 = 0
            r48 = 0
            r50 = 0
            r52 = 0
            r54 = -1
            r55 = 0
            r57 = 0
            r58 = 0
            r9 = r8
            r8 = r14
        L_0x00ae:
            if (r8 == r14) goto L_0x0862
            if (r8 == r13) goto L_0x0845
            r60 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r8 == r12) goto L_0x05b3
            if (r8 == r11) goto L_0x059b
            r13 = 100
            if (r8 == r13) goto L_0x057e
            r13 = 120(0x78, float:1.68E-43)
            if (r8 == r13) goto L_0x041f
            r13 = 150(0x96, float:2.1E-43)
            r14 = 1
            if (r8 == r13) goto L_0x01af
            if (r8 == r15) goto L_0x01aa
            r13 = 210(0xd2, float:2.94E-43)
            if (r8 == r13) goto L_0x00da
            org.apache.commons.math3.exception.MathIllegalStateException r1 = new org.apache.commons.math3.exception.MathIllegalStateException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.SIMPLE_MESSAGE
            java.lang.Object[] r3 = new java.lang.Object[r14]
            java.lang.String r4 = "trsbox"
            r5 = 0
            r3[r5] = r4
            r1.<init>(r2, r3)
            throw r1
        L_0x00da:
            printState(r13)
            r8 = 0
            r14 = 0
        L_0x00df:
            if (r8 >= r6) goto L_0x0135
            r62 = r14
            r13 = 0
            r4.setEntry(r8, r13)
            r14 = r62
            r13 = 0
        L_0x00eb:
            if (r13 > r8) goto L_0x012a
            if (r13 >= r8) goto L_0x0109
            double r60 = r4.getEntry(r8)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.modelSecondDerivativesValues
            double r63 = r11.getEntry(r14)
            double r65 = r3.getEntry(r13)
            double r63 = r63 * r65
            r67 = r13
            double r12 = r60 + r63
            r4.setEntry(r8, r12)
            r11 = r67
            goto L_0x010a
        L_0x0109:
            r11 = r13
        L_0x010a:
            double r12 = r4.getEntry(r11)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.modelSecondDerivativesValues
            double r60 = r15.getEntry(r14)
            double r63 = r3.getEntry(r8)
            double r60 = r60 * r63
            double r12 = r12 + r60
            r4.setEntry(r11, r12)
            int r14 = r14 + 1
            int r13 = r11 + 1
            r11 = 90
            r12 = 50
            r15 = 190(0xbe, float:2.66E-43)
            goto L_0x00eb
        L_0x012a:
            int r8 = r8 + 1
            r11 = 90
            r12 = 50
            r13 = 210(0xd2, float:2.94E-43)
            r15 = 190(0xbe, float:2.66E-43)
            goto L_0x00df
        L_0x0135:
            org.apache.commons.math3.linear.Array2DRowRealMatrix r8 = r0.interpolationPoints
            org.apache.commons.math3.linear.RealVector r8 = r8.operate(r3)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.modelSecondDerivativesParameters
            org.apache.commons.math3.linear.RealVector r8 = r8.ebeMultiply(r11)
            r11 = 0
        L_0x0142:
            if (r11 >= r7) goto L_0x016e
            org.apache.commons.math3.linear.ArrayRealVector r12 = r0.modelSecondDerivativesParameters
            double r12 = r12.getEntry(r11)
            r14 = 0
            int r18 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r18 == 0) goto L_0x016b
            r12 = 0
        L_0x0151:
            if (r12 >= r6) goto L_0x016b
            double r13 = r4.getEntry(r12)
            double r60 = r8.getEntry(r11)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r0.interpolationPoints
            double r63 = r15.getEntry(r11, r12)
            double r60 = r60 * r63
            double r13 = r13 + r60
            r4.setEntry(r12, r13)
            int r12 = r12 + 1
            goto L_0x0151
        L_0x016b:
            int r11 = r11 + 1
            goto L_0x0142
        L_0x016e:
            r11 = r43
            r13 = 0
            int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x017c
            r43 = r11
            r8 = 50
            goto L_0x057a
        L_0x017c:
            r8 = r32
            r13 = r45
            if (r8 <= r13) goto L_0x018d
            r14 = 150(0x96, float:2.1E-43)
            r32 = r8
            r43 = r11
            r45 = r13
            r8 = r14
            goto L_0x057a
        L_0x018d:
            r14 = 0
        L_0x018e:
            if (r14 >= r6) goto L_0x019e
            r68 = r9
            double r9 = r4.getEntry(r14)
            r5.setEntry(r14, r9)
            int r14 = r14 + 1
            r9 = r68
            goto L_0x018e
        L_0x019e:
            r68 = r9
            r32 = r8
            r43 = r11
            r45 = r13
        L_0x01a6:
            r8 = 120(0x78, float:1.68E-43)
            goto L_0x057a
        L_0x01aa:
            r14 = r15
            r84 = r43
            goto L_0x039f
        L_0x01af:
            r68 = r9
            r8 = r32
            r11 = r43
            r13 = r45
            r9 = 150(0x96, float:2.1E-43)
            printState(r9)
            r9 = 0
            r43 = 0
            r63 = 0
            r65 = 0
        L_0x01c3:
            if (r9 >= r6) goto L_0x01fa
            double r70 = r2.getEntry(r9)
            r26 = 0
            int r10 = (r70 > r26 ? 1 : (r70 == r26 ? 0 : -1))
            if (r10 != 0) goto L_0x01f7
            double r70 = r3.getEntry(r9)
            double r72 = r4.getEntry(r9)
            double r70 = r70 * r72
            double r43 = r43 + r70
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trialStepPoint
            double r70 = r10.getEntry(r9)
            double r72 = r4.getEntry(r9)
            double r70 = r70 * r72
            double r63 = r63 + r70
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trialStepPoint
            double r70 = r10.getEntry(r9)
            double r72 = r5.getEntry(r9)
            double r70 = r70 * r72
            double r65 = r65 + r70
        L_0x01f7:
            int r9 = r9 + 1
            goto L_0x01c3
        L_0x01fa:
            r9 = 4625478292286210048(0x4031000000000000, double:17.0)
            double r9 = r9 * r39
            r70 = 4614162998222441677(0x4008cccccccccccd, double:3.1)
            double r9 = r9 + r70
            int r9 = (int) r9
            r70 = r50
            r72 = r52
            r10 = 0
            r15 = -1
            r50 = 0
            r52 = r48
            r48 = 0
        L_0x0212:
            if (r10 >= r9) goto L_0x025b
            r74 = r15
            double r14 = (double) r10
            double r14 = r14 * r39
            r75 = r7
            r76 = r8
            double r7 = (double) r9
            double r72 = r14 / r7
            double r7 = r72 + r72
            double r14 = r72 * r72
            r22 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r14 = r22 + r14
            double r7 = r7 / r14
            double r14 = r72 * r65
            double r14 = r14 - r63
            double r14 = r14 - r63
            double r14 = r14 * r72
            double r14 = r43 + r14
            double r77 = r72 * r33
            double r77 = r77 - r46
            double r79 = r60 * r7
            double r79 = r79 * r14
            double r77 = r77 - r79
            double r7 = r7 * r77
            int r14 = (r7 > r48 ? 1 : (r7 == r48 ? 0 : -1))
            if (r14 <= 0) goto L_0x0249
            r48 = r7
            r15 = r10
            r70 = r50
            goto L_0x0251
        L_0x0249:
            int r15 = r74 + 1
            if (r10 != r15) goto L_0x024f
            r52 = r7
        L_0x024f:
            r15 = r74
        L_0x0251:
            int r10 = r10 + 1
            r50 = r7
            r7 = r75
            r8 = r76
            r14 = 1
            goto L_0x0212
        L_0x025b:
            r75 = r7
            r76 = r8
            r74 = r15
            if (r74 >= 0) goto L_0x0277
            r43 = r11
            r45 = r13
            r48 = r52
            r9 = r68
            r50 = r70
            r52 = r72
            r7 = r75
            r32 = r76
        L_0x0273:
            r8 = 190(0xbe, float:2.66E-43)
            goto L_0x057a
        L_0x0277:
            r15 = r74
            if (r15 >= r9) goto L_0x0290
            double r7 = r52 - r70
            double r48 = r48 + r48
            double r48 = r48 - r70
            double r48 = r48 - r52
            double r7 = r7 / r48
            r81 = r13
            double r13 = (double) r15
            double r7 = r7 * r60
            double r13 = r13 + r7
            double r13 = r13 * r39
            double r7 = (double) r9
            double r13 = r13 / r7
            goto L_0x0294
        L_0x0290:
            r81 = r13
            r13 = r72
        L_0x0294:
            double r7 = r13 * r13
            r22 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r48 = r22 - r7
            double r7 = r22 + r7
            double r48 = r48 / r7
            double r50 = r13 + r13
            double r50 = r50 / r7
            double r65 = r65 * r13
            double r65 = r65 - r63
            double r65 = r65 - r63
            double r65 = r65 * r13
            double r43 = r43 + r65
            double r7 = r13 * r33
            double r7 = r7 - r46
            double r60 = r60 * r50
            double r60 = r60 * r43
            double r7 = r7 - r60
            double r7 = r7 * r50
            r26 = 0
            int r10 = (r7 > r26 ? 1 : (r7 == r26 ? 0 : -1))
            if (r10 > 0) goto L_0x02d8
            r43 = r11
            r48 = r52
            r9 = r68
            r50 = r70
            r7 = r75
            r32 = r76
            r45 = r81
            r8 = 190(0xbe, float:2.66E-43)
            r11 = 90
            r12 = 50
            r15 = 190(0xbe, float:2.66E-43)
            r52 = r13
            goto L_0x045e
        L_0x02d8:
            r10 = 0
            r16 = 0
            r33 = 0
        L_0x02dd:
            if (r10 >= r6) goto L_0x034b
            double r43 = r1.getEntry(r10)
            r22 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r60 = r48 - r22
            double r63 = r5.getEntry(r10)
            double r60 = r60 * r63
            double r43 = r43 + r60
            double r60 = r4.getEntry(r10)
            double r60 = r60 * r50
            r82 = r13
            double r13 = r43 + r60
            r1.setEntry(r10, r13)
            double r13 = r2.getEntry(r10)
            r26 = 0
            int r18 = (r13 > r26 ? 1 : (r13 == r26 ? 0 : -1))
            if (r18 != 0) goto L_0x0332
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.trialStepPoint
            org.apache.commons.math3.linear.ArrayRealVector r14 = r0.trialStepPoint
            double r43 = r14.getEntry(r10)
            double r43 = r43 * r48
            double r60 = r3.getEntry(r10)
            double r60 = r60 * r50
            r84 = r11
            double r11 = r43 + r60
            r13.setEntry(r10, r11)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trialStepPoint
            double r11 = r11.getEntry(r10)
            double r13 = r1.getEntry(r10)
            double r11 = r11 * r13
            double r33 = r33 + r11
            double r11 = r1.getEntry(r10)
            double r11 = r11 * r11
            double r16 = r16 + r11
            goto L_0x0334
        L_0x0332:
            r84 = r11
        L_0x0334:
            double r11 = r5.getEntry(r10)
            double r11 = r11 * r48
            double r13 = r4.getEntry(r10)
            double r13 = r13 * r50
            double r11 = r11 + r13
            r5.setEntry(r10, r11)
            int r10 = r10 + 1
            r13 = r82
            r11 = r84
            goto L_0x02dd
        L_0x034b:
            r84 = r11
            r82 = r13
            double r35 = r35 + r7
            r10 = r54
            if (r10 < 0) goto L_0x0376
            if (r15 != r9) goto L_0x0376
            r9 = r31
            int r31 = r9 + 1
            r11 = r55
            r2.setEntry(r10, r11)
            r54 = r10
            r48 = r52
            r9 = r68
            r50 = r70
            r7 = r75
            r32 = r76
            r45 = r81
            r52 = r82
            r43 = r84
            r8 = 100
            goto L_0x057a
        L_0x0376:
            r9 = r31
            r11 = r55
            r13 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r13 = r13 * r35
            int r15 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r15 <= 0) goto L_0x039d
            r31 = r9
            r54 = r10
            r55 = r11
            r48 = r52
            r9 = r68
            r50 = r70
            r7 = r75
            r32 = r76
            r45 = r81
            r52 = r82
            r43 = r84
            goto L_0x01a6
        L_0x039d:
            r14 = 190(0xbe, float:2.66E-43)
        L_0x039f:
            printState(r14)
            r1 = 0
            r26 = 0
        L_0x03a5:
            if (r1 >= r6) goto L_0x0415
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trustRegionCenterOffset
            double r3 = r3.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trialStepPoint
            double r7 = r5.getEntry(r1)
            double r3 = r3 + r7
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.upperDifference
            double r7 = r5.getEntry(r1)
            double r3 = java.lang.Math.min(r3, r7)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.lowerDifference
            double r7 = r7.getEntry(r1)
            double r3 = java.lang.Math.max(r3, r7)
            r5.setEntry(r1, r3)
            double r3 = r2.getEntry(r1)
            r7 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x03e2
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lowerDifference
            double r4 = r4.getEntry(r1)
            r3.setEntry(r1, r4)
        L_0x03e2:
            double r3 = r2.getEntry(r1)
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x03f7
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.upperDifference
            double r4 = r4.getEntry(r1)
            r3.setEntry(r1, r4)
        L_0x03f7:
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trialStepPoint
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.newPoint
            double r4 = r4.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.trustRegionCenterOffset
            double r9 = r9.getEntry(r1)
            double r4 = r4 - r9
            r3.setEntry(r1, r4)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trialStepPoint
            double r3 = r3.getEntry(r1)
            double r3 = r3 * r3
            double r26 = r26 + r3
            int r1 = r1 + 1
            goto L_0x03a5
        L_0x0415:
            r1 = 2
            double[] r1 = new double[r1]
            r13 = 0
            r1[r13] = r26
            r2 = 1
            r1[r2] = r84
            return r1
        L_0x041f:
            r75 = r7
            r68 = r9
            r14 = r15
            r9 = r31
            r76 = r32
            r84 = r43
            r81 = r45
            r10 = r54
            r11 = r55
            r15 = r13
            r13 = 0
            printState(r15)
            int r32 = r76 + 1
            double r18 = r16 * r20
            double r22 = r33 * r33
            double r7 = r18 - r22
            r18 = 4547007122018943789(0x3f1a36e2eb1c432d, double:1.0E-4)
            double r18 = r18 * r35
            double r18 = r18 * r35
            int r22 = (r7 > r18 ? 1 : (r7 == r18 ? 0 : -1))
            if (r22 > 0) goto L_0x0464
            r31 = r9
            r54 = r10
            r55 = r11
            r8 = r14
            r15 = r8
            r9 = r68
            r7 = r75
            r45 = r81
            r43 = r84
            r11 = 90
            r12 = 50
        L_0x045e:
            r13 = 30
            r14 = 20
            goto L_0x00ae
        L_0x0464:
            double r7 = java.lang.Math.sqrt(r7)
            r10 = r13
        L_0x0469:
            if (r10 >= r6) goto L_0x0498
            double r18 = r2.getEntry(r10)
            r13 = 0
            int r22 = (r18 > r13 ? 1 : (r18 == r13 ? 0 : -1))
            if (r22 != 0) goto L_0x048d
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r18 = r15.getEntry(r10)
            double r18 = r18 * r33
            double r22 = r1.getEntry(r10)
            double r22 = r22 * r20
            double r18 = r18 - r22
            double r13 = r18 / r7
            r3.setEntry(r10, r13)
            r13 = 0
            goto L_0x0490
        L_0x048d:
            r3.setEntry(r10, r13)
        L_0x0490:
            int r10 = r10 + 1
            r13 = 0
            r14 = 190(0xbe, float:2.66E-43)
            r15 = 120(0x78, float:1.68E-43)
            goto L_0x0469
        L_0x0498:
            r13 = 0
            double r7 = -r7
            r55 = r11
            r10 = 0
            r39 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r54 = -1
        L_0x04a2:
            if (r10 >= r6) goto L_0x056a
            double r11 = r2.getEntry(r10)
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 != 0) goto L_0x0560
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trustRegionCenterOffset
            double r11 = r11.getEntry(r10)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.trialStepPoint
            double r13 = r13.getEntry(r10)
            double r11 = r11 + r13
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.lowerDifference
            double r13 = r13.getEntry(r10)
            double r11 = r11 - r13
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.upperDifference
            double r13 = r13.getEntry(r10)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r18 = r15.getEntry(r10)
            double r13 = r13 - r18
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r18 = r15.getEntry(r10)
            double r13 = r13 - r18
            r18 = 0
            int r15 = (r11 > r18 ? 1 : (r11 == r18 ? 0 : -1))
            if (r15 > 0) goto L_0x04e7
            int r31 = r9 + 1
            r11 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r2.setEntry(r10, r11)
        L_0x04e3:
            r86 = r7
            goto L_0x056e
        L_0x04e7:
            int r15 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            if (r15 > 0) goto L_0x04f3
            int r31 = r9 + 1
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r2.setEntry(r10, r11)
            goto L_0x04e3
        L_0x04f3:
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r18 = r15.getEntry(r10)
            double r43 = r3.getEntry(r10)
            double r18 = r18 * r18
            double r43 = r43 * r43
            double r18 = r18 + r43
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r43 = r15.getEntry(r10)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.lowerDifference
            double r45 = r15.getEntry(r10)
            double r43 = r43 - r45
            double r43 = r43 * r43
            r86 = r7
            double r7 = r18 - r43
            r26 = 0
            int r15 = (r7 > r26 ? 1 : (r7 == r26 ? 0 : -1))
            if (r15 <= 0) goto L_0x0533
            double r7 = java.lang.Math.sqrt(r7)
            double r43 = r3.getEntry(r10)
            double r7 = r7 - r43
            double r43 = r39 * r7
            int r15 = (r43 > r11 ? 1 : (r43 == r11 ? 0 : -1))
            if (r15 <= 0) goto L_0x0533
            double r39 = r11 / r7
            r54 = r10
            r55 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0533:
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.upperDifference
            double r7 = r7.getEntry(r10)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trustRegionCenterOffset
            double r11 = r11.getEntry(r10)
            double r7 = r7 - r11
            double r7 = r7 * r7
            double r7 = r18 - r7
            r11 = 0
            int r15 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r15 <= 0) goto L_0x0562
            double r7 = java.lang.Math.sqrt(r7)
            double r11 = r3.getEntry(r10)
            double r7 = r7 + r11
            double r11 = r39 * r7
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 <= 0) goto L_0x0562
            double r13 = r13 / r7
            r54 = r10
            r39 = r13
            r55 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0562
        L_0x0560:
            r86 = r7
        L_0x0562:
            int r10 = r10 + 1
            r7 = r86
            r13 = 0
            goto L_0x04a2
        L_0x056a:
            r86 = r7
            r31 = r9
        L_0x056e:
            r9 = r68
            r7 = r75
            r45 = r81
            r43 = r84
            r46 = r86
            r8 = 210(0xd2, float:2.94E-43)
        L_0x057a:
            r11 = 90
            goto L_0x07e5
        L_0x057e:
            r75 = r7
            r68 = r9
            r9 = r31
            r76 = r32
            r84 = r43
            r81 = r45
            r10 = r54
            r7 = r13
            r88 = r55
            r5 = r57
            r45 = r76
            r4 = 90
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r22 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            goto L_0x07c9
        L_0x059b:
            r75 = r7
            r68 = r9
            r9 = r31
            r76 = r32
            r81 = r45
            r10 = r54
            r88 = r55
            r5 = r57
            r4 = 90
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r22 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            goto L_0x07be
        L_0x05b3:
            r75 = r7
            r68 = r9
            r7 = r12
            r9 = r31
            r76 = r32
            r84 = r43
            r81 = r45
            r10 = r54
            r11 = r55
            printState(r7)
            r13 = r68
            r8 = 0
            r18 = 0
            r31 = 0
        L_0x05ce:
            if (r8 >= r6) goto L_0x0601
            double r43 = r2.getEntry(r8)
            r26 = 0
            int r15 = (r43 > r26 ? 1 : (r43 == r26 ? 0 : -1))
            if (r15 != 0) goto L_0x05fe
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r43 = r15.getEntry(r8)
            double r43 = r43 * r43
            double r13 = r13 - r43
            double r43 = r3.getEntry(r8)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r54 = r15.getEntry(r8)
            double r43 = r43 * r54
            double r18 = r18 + r43
            double r43 = r3.getEntry(r8)
            double r54 = r4.getEntry(r8)
            double r43 = r43 * r54
            double r31 = r31 + r43
        L_0x05fe:
            int r8 = r8 + 1
            goto L_0x05ce
        L_0x0601:
            r26 = 0
            int r8 = (r13 > r26 ? 1 : (r13 == r26 ? 0 : -1))
            if (r8 > 0) goto L_0x0624
            r31 = r9
            r54 = r10
            r55 = r11
            r9 = r68
            r32 = r76
            r45 = r81
            r43 = r84
            r8 = 90
            r11 = 90
            r13 = 30
            r14 = 20
            r15 = 190(0xbe, float:2.66E-43)
            r12 = r7
            r7 = r75
            goto L_0x00ae
        L_0x0624:
            double r43 = r37 * r13
            double r54 = r18 * r18
            double r7 = r43 + r54
            double r7 = java.lang.Math.sqrt(r7)
            int r10 = (r18 > r26 ? 1 : (r18 == r26 ? 0 : -1))
            if (r10 >= 0) goto L_0x0637
            double r7 = r7 - r18
            double r7 = r7 / r37
            goto L_0x063b
        L_0x0637:
            double r7 = r7 + r18
            double r7 = r13 / r7
        L_0x063b:
            int r10 = (r31 > r26 ? 1 : (r31 == r26 ? 0 : -1))
            if (r10 <= 0) goto L_0x0646
            double r13 = r16 / r31
            double r13 = java.lang.Math.min(r7, r13)
            goto L_0x0647
        L_0x0646:
            r13 = r7
        L_0x0647:
            r10 = 0
            r15 = -1
        L_0x0649:
            if (r10 >= r6) goto L_0x0694
            double r18 = r3.getEntry(r10)
            int r28 = (r18 > r26 ? 1 : (r18 == r26 ? 0 : -1))
            if (r28 == 0) goto L_0x068d
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trustRegionCenterOffset
            double r18 = r5.getEntry(r10)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trialStepPoint
            double r43 = r5.getEntry(r10)
            double r18 = r18 + r43
            double r43 = r3.getEntry(r10)
            int r5 = (r43 > r26 ? 1 : (r43 == r26 ? 0 : -1))
            if (r5 <= 0) goto L_0x0678
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.upperDifference
            double r43 = r5.getEntry(r10)
            double r43 = r43 - r18
            double r18 = r3.getEntry(r10)
            double r43 = r43 / r18
            goto L_0x0686
        L_0x0678:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.lowerDifference
            double r43 = r5.getEntry(r10)
            double r43 = r43 - r18
            double r18 = r3.getEntry(r10)
            double r43 = r43 / r18
        L_0x0686:
            int r5 = (r43 > r13 ? 1 : (r43 == r13 ? 0 : -1))
            if (r5 >= 0) goto L_0x068d
            r15 = r10
            r13 = r43
        L_0x068d:
            int r10 = r10 + 1
            r5 = r104
            r26 = 0
            goto L_0x0649
        L_0x0694:
            r18 = r26
            int r5 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            if (r5 <= 0) goto L_0x0720
            int r5 = r76 + 1
            r88 = r11
            double r10 = r31 / r37
            r12 = -1
            if (r15 != r12) goto L_0x06b9
            int r28 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r28 <= 0) goto L_0x06b9
            r90 = r13
            r12 = r84
            double r43 = java.lang.Math.min(r12, r10)
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r14 = (r43 > r12 ? 1 : (r43 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x06b6
            goto L_0x06be
        L_0x06b6:
            r10 = r43
            goto L_0x06be
        L_0x06b9:
            r90 = r13
            r12 = r84
            r10 = r12
        L_0x06be:
            r12 = 0
            r13 = 0
        L_0x06c1:
            if (r12 >= r6) goto L_0x0702
            double r18 = r1.getEntry(r12)
            double r29 = r4.getEntry(r12)
            double r29 = r29 * r90
            r92 = r5
            double r4 = r18 + r29
            r1.setEntry(r12, r4)
            double r4 = r2.getEntry(r12)
            r18 = 0
            int r28 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r28 != 0) goto L_0x06e4
            double r4 = r1.getEntry(r12)
            double r4 = r4 * r4
            double r13 = r13 + r4
        L_0x06e4:
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trialStepPoint
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trialStepPoint
            double r18 = r5.getEntry(r12)
            double r29 = r3.getEntry(r12)
            double r29 = r29 * r90
            r93 = r10
            double r10 = r18 + r29
            r4.setEntry(r12, r10)
            int r12 = r12 + 1
            r5 = r92
            r10 = r93
            r4 = r103
            goto L_0x06c1
        L_0x0702:
            r92 = r5
            r93 = r10
            double r60 = r60 * r90
            double r60 = r60 * r31
            double r4 = r16 - r60
            double r4 = r4 * r90
            r10 = 0
            double r4 = java.lang.Math.max(r4, r10)
            double r35 = r35 + r4
            r58 = r16
            r43 = r93
            r16 = r13
            r12 = r4
            r4 = r92
            goto L_0x072d
        L_0x0720:
            r88 = r11
            r90 = r13
            r10 = r18
            r12 = r84
            r43 = r12
            r4 = r76
            r12 = r10
        L_0x072d:
            if (r15 < 0) goto L_0x077a
            int r31 = r9 + 1
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r2.setEntry(r15, r7)
            double r12 = r3.getEntry(r15)
            int r5 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0744
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r2.setEntry(r15, r12)
            goto L_0x0746
        L_0x0744:
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0746:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trialStepPoint
            double r18 = r5.getEntry(r15)
            double r18 = r18 * r18
            double r18 = r68 - r18
            int r5 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1))
            if (r5 > 0) goto L_0x0766
            r32 = r4
            r54 = r15
            r9 = r18
        L_0x075a:
            r7 = r75
            r45 = r81
            r55 = r88
            r4 = r103
            r5 = r104
            goto L_0x0273
        L_0x0766:
            r32 = r4
            r54 = r15
            r9 = r18
            r7 = r75
            r45 = r81
            r55 = r88
            r4 = r103
            r5 = r104
            r8 = 20
            goto L_0x057a
        L_0x077a:
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r22 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r5 = (r90 > r7 ? 1 : (r90 == r7 ? 0 : -1))
            if (r5 >= 0) goto L_0x07b7
            r5 = r57
            if (r4 != r5) goto L_0x0791
        L_0x0786:
            r32 = r4
            r57 = r5
            r31 = r9
            r54 = r15
            r9 = r68
            goto L_0x075a
        L_0x0791:
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r7 = r7 * r35
            int r10 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r10 > 0) goto L_0x079d
            goto L_0x0786
        L_0x079d:
            double r41 = r16 / r58
            r32 = r4
            r57 = r5
            r31 = r9
            r54 = r15
            r9 = r68
            r7 = r75
            r45 = r81
            r55 = r88
            r4 = r103
            r5 = r104
            r8 = 30
            goto L_0x057a
        L_0x07b7:
            r5 = r57
            r76 = r4
            r10 = r15
            r4 = 90
        L_0x07be:
            printState(r4)
            r54 = r10
            r45 = r76
            r7 = 100
            r43 = 0
        L_0x07c9:
            printState(r7)
            int r8 = r6 + -1
            if (r9 < r8) goto L_0x07ef
            r11 = r4
            r57 = r5
            r31 = r9
            r32 = r45
            r9 = r68
            r7 = r75
            r45 = r81
            r55 = r88
            r4 = r103
            r5 = r104
            r8 = 190(0xbe, float:2.66E-43)
        L_0x07e5:
            r12 = 50
            r13 = 30
            r14 = 20
            r15 = 190(0xbe, float:2.66E-43)
            goto L_0x00ae
        L_0x07ef:
            r8 = 0
            r16 = 0
            r20 = 0
            r33 = 0
        L_0x07f6:
            if (r8 >= r6) goto L_0x0831
            double r10 = r2.getEntry(r8)
            r12 = 0
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x0829
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trialStepPoint
            double r10 = r10.getEntry(r8)
            double r10 = r10 * r10
            double r20 = r20 + r10
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trialStepPoint
            double r10 = r10.getEntry(r8)
            double r12 = r1.getEntry(r8)
            double r10 = r10 * r12
            double r33 = r33 + r10
            double r10 = r1.getEntry(r8)
            double r10 = r10 * r10
            double r16 = r16 + r10
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trialStepPoint
            double r10 = r10.getEntry(r8)
            r3.setEntry(r8, r10)
            goto L_0x082e
        L_0x0829:
            r10 = 0
            r3.setEntry(r8, r10)
        L_0x082e:
            int r8 = r8 + 1
            goto L_0x07f6
        L_0x0831:
            r11 = r4
            r57 = r5
            r31 = r9
            r32 = r45
            r9 = r68
            r7 = r75
            r55 = r88
            r4 = r103
            r5 = r104
            r8 = 210(0xd2, float:2.94E-43)
            goto L_0x07e5
        L_0x0845:
            r75 = r7
            r68 = r9
            r4 = r11
            r9 = r31
            r76 = r32
            r12 = r43
            r81 = r45
            r10 = r54
            r88 = r55
            r5 = r57
            r7 = 100
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r22 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r8 = r14
            r11 = 30
            goto L_0x0883
        L_0x0862:
            r75 = r7
            r68 = r9
            r4 = r11
            r8 = r14
            r9 = r31
            r76 = r32
            r12 = r43
            r81 = r45
            r10 = r54
            r88 = r55
            r5 = r57
            r7 = 100
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r22 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            printState(r8)
            r11 = 30
            r41 = 0
        L_0x0883:
            printState(r11)
            r14 = 0
            r37 = 0
        L_0x0889:
            if (r14 >= r6) goto L_0x08c5
            double r24 = r2.getEntry(r14)
            r95 = r5
            r4 = 0
            int r15 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x089b
            r3.setEntry(r14, r4)
            goto L_0x08b7
        L_0x089b:
            int r15 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r15 != 0) goto L_0x08a8
            double r4 = r1.getEntry(r14)
            double r4 = -r4
            r3.setEntry(r14, r4)
            goto L_0x08b7
        L_0x08a8:
            double r4 = r3.getEntry(r14)
            double r4 = r4 * r41
            double r24 = r1.getEntry(r14)
            double r4 = r4 - r24
            r3.setEntry(r14, r4)
        L_0x08b7:
            double r4 = r3.getEntry(r14)
            double r4 = r4 * r4
            double r37 = r37 + r4
            int r14 = r14 + 1
            r5 = r95
            r4 = 90
            goto L_0x0889
        L_0x08c5:
            r95 = r5
            r4 = 0
            int r14 = (r37 > r4 ? 1 : (r37 == r4 ? 0 : -1))
            if (r14 != 0) goto L_0x08ef
            r14 = r8
            r31 = r9
            r54 = r10
            r43 = r12
            r9 = r68
            r7 = r75
            r32 = r76
            r45 = r81
            r55 = r88
            r57 = r95
        L_0x08e0:
            r4 = r103
            r5 = r104
            r8 = 190(0xbe, float:2.66E-43)
        L_0x08e6:
            r12 = 50
            r15 = 190(0xbe, float:2.66E-43)
            r13 = r11
            r11 = 90
            goto L_0x00ae
        L_0x08ef:
            int r14 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r14 != 0) goto L_0x08fc
            int r32 = r76 + r6
            int r32 = r32 - r9
            r57 = r32
            r16 = r37
            goto L_0x08fe
        L_0x08fc:
            r57 = r95
        L_0x08fe:
            double r14 = r16 * r68
            r24 = 4547007122018943789(0x3f1a36e2eb1c432d, double:1.0E-4)
            double r24 = r24 * r35
            double r24 = r24 * r35
            int r26 = (r14 > r24 ? 1 : (r14 == r24 ? 0 : -1))
            if (r26 > 0) goto L_0x091f
            r14 = r8
            r31 = r9
            r54 = r10
            r43 = r12
            r9 = r68
            r7 = r75
            r32 = r76
            r45 = r81
            r55 = r88
            goto L_0x08e0
        L_0x091f:
            r14 = r8
            r31 = r9
            r54 = r10
            r43 = r12
            r9 = r68
            r7 = r75
            r32 = r76
            r45 = r81
            r55 = r88
            r4 = r103
            r5 = r104
            r8 = 210(0xd2, float:2.94E-43)
            goto L_0x08e6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox(double, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector):double[]");
    }

    private void update(double d, double d2, int i) {
        int i2;
        int i3 = i;
        printMethod();
        int dimension = this.currentBest.getDimension();
        int i4 = this.numberOfInterpolationPoints;
        int i5 = (i4 - dimension) - 1;
        ArrayRealVector arrayRealVector = new ArrayRealVector(i4 + dimension);
        int i6 = 0;
        int i7 = 0;
        double d3 = 0.0d;
        while (i7 < i4) {
            double d4 = d3;
            for (int i8 = 0; i8 < i5; i8++) {
                d4 = Math.max(d4, Math.abs(this.zMatrix.getEntry(i7, i8)));
            }
            i7++;
            d3 = d4;
        }
        double d5 = d3 * 1.0E-20d;
        int i9 = 1;
        while (i9 < i5) {
            if (Math.abs(this.zMatrix.getEntry(i3, i9)) > d5) {
                double entry = this.zMatrix.getEntry(i3, i6);
                double entry2 = this.zMatrix.getEntry(i3, i9);
                double sqrt = Math.sqrt((entry * entry) + (entry2 * entry2));
                double entry3 = this.zMatrix.getEntry(i3, i6) / sqrt;
                double entry4 = this.zMatrix.getEntry(i3, i9) / sqrt;
                int i10 = i6;
                while (i10 < i4) {
                    double entry5 = (this.zMatrix.getEntry(i10, i6) * entry3) + (this.zMatrix.getEntry(i10, i9) * entry4);
                    int i11 = i5;
                    double d6 = d5;
                    this.zMatrix.setEntry(i10, i9, (this.zMatrix.getEntry(i10, i9) * entry3) - (this.zMatrix.getEntry(i10, 0) * entry4));
                    this.zMatrix.setEntry(i10, 0, entry5);
                    i10++;
                    i5 = i11;
                    d5 = d6;
                    i6 = 0;
                }
            }
            int i12 = i5;
            double d7 = d5;
            this.zMatrix.setEntry(i3, i9, 0.0d);
            i9++;
            i5 = i12;
            d5 = d7;
            i6 = 0;
        }
        for (int i13 = 0; i13 < i4; i13++) {
            arrayRealVector.setEntry(i13, this.zMatrix.getEntry(i3, 0) * this.zMatrix.getEntry(i13, 0));
        }
        double entry6 = arrayRealVector.getEntry(i3);
        double entry7 = this.lagrangeValuesAtNewPoint.getEntry(i3);
        this.lagrangeValuesAtNewPoint.setEntry(i3, this.lagrangeValuesAtNewPoint.getEntry(i3) - ONE);
        double sqrt2 = Math.sqrt(d2);
        double d8 = entry7 / sqrt2;
        double entry8 = this.zMatrix.getEntry(i3, 0) / sqrt2;
        int i14 = 0;
        while (i14 < i4) {
            double d9 = d8;
            this.zMatrix.setEntry(i14, 0, (this.zMatrix.getEntry(i14, 0) * d8) - (this.lagrangeValuesAtNewPoint.getEntry(i14) * entry8));
            i14++;
            d8 = d9;
        }
        int i15 = 0;
        while (i15 < dimension) {
            int i16 = i4 + i15;
            arrayRealVector.setEntry(i16, this.bMatrix.getEntry(i3, i15));
            double entry9 = ((this.lagrangeValuesAtNewPoint.getEntry(i16) * entry6) - (arrayRealVector.getEntry(i16) * entry7)) / d2;
            int i17 = i15;
            int i18 = dimension;
            double entry10 = (((-d) * arrayRealVector.getEntry(i16)) - (this.lagrangeValuesAtNewPoint.getEntry(i16) * entry7)) / d2;
            int i19 = 0;
            while (i19 <= i16) {
                double d10 = entry6;
                int i20 = i17;
                double d11 = entry10;
                this.bMatrix.setEntry(i19, i20, this.bMatrix.getEntry(i19, i20) + (this.lagrangeValuesAtNewPoint.getEntry(i19) * entry9) + (arrayRealVector.getEntry(i19) * entry10));
                if (i19 >= i4) {
                    i2 = i4;
                    this.bMatrix.setEntry(i16, i19 - i4, this.bMatrix.getEntry(i19, i20));
                } else {
                    i2 = i4;
                }
                i19++;
                i17 = i20;
                entry6 = d10;
                entry10 = d11;
                i4 = i2;
            }
            int i21 = i4;
            i15 = i17 + 1;
            dimension = i18;
            entry6 = entry6;
            i3 = i;
        }
    }

    private void setup(double[] dArr, double[] dArr2) {
        printMethod();
        int length = getStartPoint().length;
        if (length < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(length), Integer.valueOf(2), true);
        }
        int i = length + 2;
        int i2 = length + 1;
        int[] iArr = {i, (i * i2) / 2};
        if (this.numberOfInterpolationPoints < iArr[0] || this.numberOfInterpolationPoints > iArr[1]) {
            throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, Integer.valueOf(this.numberOfInterpolationPoints), Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
        }
        this.boundDifference = new double[length];
        double d = TWO * this.initialTrustRegionRadius;
        double d2 = Double.POSITIVE_INFINITY;
        for (int i3 = 0; i3 < length; i3++) {
            this.boundDifference[i3] = dArr2[i3] - dArr[i3];
            d2 = Math.min(d2, this.boundDifference[i3]);
        }
        if (d2 < d) {
            this.initialTrustRegionRadius = d2 / 3.0d;
        }
        this.bMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints + length, length);
        this.zMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, (this.numberOfInterpolationPoints - length) - 1);
        this.interpolationPoints = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, length);
        this.originShift = new ArrayRealVector(length);
        this.fAtInterpolationPoints = new ArrayRealVector(this.numberOfInterpolationPoints);
        this.trustRegionCenterOffset = new ArrayRealVector(length);
        this.gradientAtTrustRegionCenter = new ArrayRealVector(length);
        this.lowerDifference = new ArrayRealVector(length);
        this.upperDifference = new ArrayRealVector(length);
        this.modelSecondDerivativesParameters = new ArrayRealVector(this.numberOfInterpolationPoints);
        this.newPoint = new ArrayRealVector(length);
        this.alternativeNewPoint = new ArrayRealVector(length);
        this.trialStepPoint = new ArrayRealVector(length);
        this.lagrangeValuesAtNewPoint = new ArrayRealVector(this.numberOfInterpolationPoints + length);
        this.modelSecondDerivativesValues = new ArrayRealVector((length * i2) / 2);
    }

    private static double[] fillNewArray(int i, double d) {
        double[] dArr = new double[i];
        Arrays.fill(dArr, d);
        return dArr;
    }

    /* access modifiers changed from: private */
    public static String caller(int i) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i];
        StringBuilder sb = new StringBuilder();
        sb.append(stackTraceElement.getMethodName());
        sb.append(" (at line ");
        sb.append(stackTraceElement.getLineNumber());
        sb.append(")");
        return sb.toString();
    }
}
