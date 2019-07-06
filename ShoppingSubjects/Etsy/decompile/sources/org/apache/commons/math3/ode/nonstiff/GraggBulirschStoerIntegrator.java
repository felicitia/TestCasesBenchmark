package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;

public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
    private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
    private double[][] coeff;
    private int[] costPerStep;
    private double[] costPerTimeUnit;
    private int maxChecks;
    private int maxIter;
    private int maxOrder;
    private int mudif;
    private double[] optimalStep;
    private double orderControl1;
    private double orderControl2;
    private boolean performTest;
    private int[] sequence;
    private double stabilityReduction;
    private double stepControl1;
    private double stepControl2;
    private double stepControl3;
    private double stepControl4;
    private boolean useInterpolationError;

    public GraggBulirschStoerIntegrator(double d, double d2, double d3, double d4) {
        super(METHOD_NAME, d, d2, d3, d4);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public GraggBulirschStoerIntegrator(double d, double d2, double[] dArr, double[] dArr2) {
        super(METHOD_NAME, d, d2, dArr, dArr2);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public void setStabilityCheck(boolean z, int i, int i2, double d) {
        this.performTest = z;
        if (i <= 0) {
            i = 2;
        }
        this.maxIter = i;
        if (i2 <= 0) {
            i2 = 1;
        }
        this.maxChecks = i2;
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stabilityReduction = 0.5d;
        } else {
            this.stabilityReduction = d;
        }
    }

    public void setControlFactors(double d, double d2, double d3, double d4) {
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stepControl1 = 0.65d;
        } else {
            this.stepControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.stepControl2 = 0.94d;
        } else {
            this.stepControl2 = d2;
        }
        if (d3 < 1.0E-4d || d3 > 0.9999d) {
            this.stepControl3 = 0.02d;
        } else {
            this.stepControl3 = d3;
        }
        if (d4 < 1.0001d || d4 > 999.9d) {
            this.stepControl4 = 4.0d;
        } else {
            this.stepControl4 = d4;
        }
    }

    public void setOrderControl(int i, double d, double d2) {
        if (i <= 6 || i % 2 != 0) {
            this.maxOrder = 18;
        }
        if (d < 1.0E-4d || d > 0.9999d) {
            this.orderControl1 = 0.8d;
        } else {
            this.orderControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.orderControl2 = 0.9d;
        } else {
            this.orderControl2 = d2;
        }
        initializeArrays();
    }

    public void addStepHandler(StepHandler stepHandler) {
        super.addStepHandler(stepHandler);
        initializeArrays();
    }

    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        super.addEventHandler(eventHandler, d, d2, i, univariateSolver);
        initializeArrays();
    }

    private void initializeArrays() {
        int i = this.maxOrder / 2;
        if (this.sequence == null || this.sequence.length != i) {
            this.sequence = new int[i];
            this.costPerStep = new int[i];
            this.coeff = new double[i][];
            this.costPerTimeUnit = new double[i];
            this.optimalStep = new double[i];
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.sequence[i2] = (4 * i2) + 2;
        }
        this.costPerStep[0] = this.sequence[0] + 1;
        for (int i3 = 1; i3 < i; i3++) {
            this.costPerStep[i3] = this.costPerStep[i3 - 1] + this.sequence[i3];
        }
        int i4 = 0;
        while (i4 < i) {
            this.coeff[i4] = i4 > 0 ? new double[i4] : null;
            for (int i5 = 0; i5 < i4; i5++) {
                double d = ((double) this.sequence[i4]) / ((double) this.sequence[(i4 - i5) - 1]);
                this.coeff[i4][i5] = 1.0d / ((d * d) - 1.0d);
            }
            i4++;
        }
    }

    public void setInterpolationControl(boolean z, int i) {
        this.useInterpolationError = z;
        if (i <= 0 || i >= 7) {
            this.mudif = 4;
        } else {
            this.mudif = i;
        }
    }

    private void rescale(double[] dArr, double[] dArr2, double[] dArr3) {
        int i = 0;
        if (this.vecAbsoluteTolerance == null) {
            while (i < dArr3.length) {
                dArr3[i] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
                i++;
            }
            return;
        }
        while (i < dArr3.length) {
            dArr3[i] = this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
            i++;
        }
    }

    private boolean tryStep(double d, double[] dArr, double d2, int i, double[] dArr2, double[][] dArr3, double[] dArr4, double[] dArr5, double[] dArr6) throws MaxCountExceededException, DimensionMismatchException {
        double d3;
        double[] dArr7 = dArr;
        int i2 = i;
        double[] dArr8 = dArr2;
        double[] dArr9 = dArr5;
        int i3 = this.sequence[i2];
        double d4 = d2 / ((double) i3);
        double d5 = 2.0d * d4;
        double d6 = d + d4;
        for (int i4 = 0; i4 < dArr7.length; i4++) {
            dArr6[i4] = dArr7[i4];
            dArr9[i4] = dArr7[i4] + (dArr3[0][i4] * d4);
        }
        computeDerivatives(d6, dArr9, dArr3[1]);
        double d7 = d6;
        int i5 = 1;
        while (i5 < i3) {
            if (2 * i5 == i3) {
                System.arraycopy(dArr9, 0, dArr4, 0, dArr7.length);
            } else {
                double[] dArr10 = dArr4;
            }
            double d8 = d7 + d4;
            double d9 = d4;
            for (int i6 = 0; i6 < dArr7.length; i6++) {
                double d10 = dArr9[i6];
                dArr9[i6] = dArr6[i6] + (dArr3[i5][i6] * d5);
                dArr6[i6] = d10;
            }
            int i7 = i5 + 1;
            computeDerivatives(d8, dArr9, dArr3[i7]);
            if (!this.performTest || i5 > this.maxChecks || i2 >= this.maxIter) {
                d3 = d5;
            } else {
                double d11 = 0.0d;
                d3 = d5;
                double d12 = 0.0d;
                for (int i8 = 0; i8 < dArr8.length; i8++) {
                    double d13 = dArr3[0][i8] / dArr8[i8];
                    d12 += d13 * d13;
                }
                for (int i9 = 0; i9 < dArr8.length; i9++) {
                    double d14 = (dArr3[i7][i9] - dArr3[0][i9]) / dArr8[i9];
                    d11 += d14 * d14;
                }
                if (d11 > 4.0d * FastMath.max(1.0E-15d, d12)) {
                    return false;
                }
            }
            i5 = i7;
            d7 = d8;
            d4 = d9;
            d5 = d3;
            i2 = i;
            dArr8 = dArr2;
        }
        double d15 = d4;
        for (int i10 = 0; i10 < dArr7.length; i10++) {
            dArr9[i10] = 0.5d * (dArr6[i10] + dArr9[i10] + (dArr3[i3][i10] * d15));
        }
        return true;
    }

    private void extrapolate(int i, int i2, double[][] dArr, double[] dArr2) {
        int i3 = i2;
        double[] dArr3 = dArr2;
        int i4 = 1;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            for (int i5 = 0; i5 < dArr3.length; i5++) {
                int i6 = i3 - i4;
                int i7 = i6 - 1;
                dArr[i7][i5] = dArr[i6][i5] + (this.coeff[i3 + i][i4 - 1] * (dArr[i6][i5] - dArr[i7][i5]));
            }
            i4++;
        }
        for (int i8 = 0; i8 < dArr3.length; i8++) {
            dArr3[i8] = dArr[0][i8] + (this.coeff[i3 + i][i3 - 1] * (dArr[0][i8] - dArr3[i8]));
        }
    }

    /* JADX WARNING: type inference failed for: r24v1 */
    /* JADX WARNING: type inference failed for: r0v35, types: [boolean] */
    /* JADX WARNING: type inference failed for: r21v3 */
    /* JADX WARNING: type inference failed for: r24v2 */
    /* JADX WARNING: type inference failed for: r21v4 */
    /* JADX WARNING: type inference failed for: r25v0 */
    /* JADX WARNING: type inference failed for: r25v1 */
    /* JADX WARNING: type inference failed for: r15v18 */
    /* JADX WARNING: type inference failed for: r13v9, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r0v92 */
    /* JADX WARNING: type inference failed for: r0v93, types: [int] */
    /* JADX WARNING: type inference failed for: r21v8 */
    /* JADX WARNING: type inference failed for: r21v9 */
    /* JADX WARNING: type inference failed for: r21v10 */
    /* JADX WARNING: type inference failed for: r21v11 */
    /* JADX WARNING: type inference failed for: r0v118, types: [int] */
    /* JADX WARNING: type inference failed for: r21v12 */
    /* JADX WARNING: type inference failed for: r24v12 */
    /* JADX WARNING: type inference failed for: r21v13 */
    /* JADX WARNING: type inference failed for: r21v14 */
    /* JADX WARNING: type inference failed for: r25v2 */
    /* JADX WARNING: type inference failed for: r0v126 */
    /* JADX WARNING: type inference failed for: r0v127 */
    /* JADX WARNING: type inference failed for: r24v13 */
    /* JADX WARNING: type inference failed for: r24v14 */
    /* JADX WARNING: type inference failed for: r24v15 */
    /* JADX WARNING: type inference failed for: r24v16 */
    /* JADX WARNING: type inference failed for: r24v17 */
    /* JADX WARNING: type inference failed for: r21v15 */
    /* JADX WARNING: type inference failed for: r25v3 */
    /* JADX WARNING: type inference failed for: r21v16 */
    /* JADX WARNING: type inference failed for: r21v17 */
    /* JADX WARNING: type inference failed for: r21v18 */
    /* JADX WARNING: type inference failed for: r21v19 */
    /* JADX WARNING: type inference failed for: r0v138 */
    /* JADX WARNING: type inference failed for: r21v20 */
    /* JADX WARNING: type inference failed for: r24v18 */
    /* JADX WARNING: type inference failed for: r21v21 */
    /* JADX WARNING: type inference failed for: r25v4 */
    /* JADX WARNING: type inference failed for: r0v139 */
    /* JADX WARNING: type inference failed for: r24v19 */
    /* JADX WARNING: type inference failed for: r24v20 */
    /* JADX WARNING: type inference failed for: r24v21 */
    /* JADX WARNING: type inference failed for: r24v22 */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0351, code lost:
        r22 = true;
        r21 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x03a2, code lost:
        if (r0 > 1.0d) goto L_0x03a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x03a6, code lost:
        r41 = r7;
        r43 = r8;
        r40 = r9;
        r9 = r10;
        r11 = r15;
        r30 = r23;
        r45 = r49;
        r13 = r50;
        r10 = r51;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x024e, code lost:
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x02f1, code lost:
        if (r12.isLastStep == false) goto L_0x03a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x031f, code lost:
        r21 = 0;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r13v9, types: [int, boolean]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [int, ?[int, float, boolean, short, byte, char, OBJECT, ARRAY], ?[int, short, byte, char], boolean]
      mth insns count: 840
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0550  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x0697  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x06b2  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x06b5  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x06ba  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x06ce A[LOOP:3: B:24:0x0154->B:220:0x06ce, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x06c0 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 21 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE r64, double r65) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r63 = this;
            r12 = r63
            r13 = r64
            r63.sanityChecks(r64, r65)
            r63.setEquations(r64)
            double r0 = r64.getTime()
            int r2 = (r65 > r0 ? 1 : (r65 == r0 ? 0 : -1))
            r10 = 1
            if (r2 <= 0) goto L_0x0015
            r9 = r10
            goto L_0x0016
        L_0x0015:
            r9 = 0
        L_0x0016:
            double[] r8 = r64.getCompleteState()
            java.lang.Object r0 = r8.clone()
            r7 = r0
            double[] r7 = (double[]) r7
            int r0 = r7.length
            double[] r6 = new double[r0]
            int r0 = r7.length
            double[] r4 = new double[r0]
            int r0 = r7.length
            double[] r5 = new double[r0]
            int r0 = r7.length
            double[] r3 = new double[r0]
            int[] r0 = r12.sequence
            int r0 = r0.length
            int r0 = r0 - r10
            double[][] r1 = new double[r0][]
            int[] r0 = r12.sequence
            int r0 = r0.length
            int r0 = r0 - r10
            double[][] r2 = new double[r0][]
            r0 = 0
        L_0x003a:
            int[] r11 = r12.sequence
            int r11 = r11.length
            int r11 = r11 - r10
            if (r0 >= r11) goto L_0x004d
            int r11 = r7.length
            double[] r11 = new double[r11]
            r1[r0] = r11
            int r11 = r7.length
            double[] r11 = new double[r11]
            r2[r0] = r11
            int r0 = r0 + 1
            goto L_0x003a
        L_0x004d:
            int[] r0 = r12.sequence
            int r0 = r0.length
            double[][][] r11 = new double[r0][][]
            r0 = 0
        L_0x0053:
            int[] r10 = r12.sequence
            int r10 = r10.length
            if (r0 >= r10) goto L_0x0088
            int[] r10 = r12.sequence
            r10 = r10[r0]
            r16 = 1
            int r10 = r10 + 1
            double[][] r10 = new double[r10][]
            r11[r0] = r10
            r10 = r11[r0]
            r16 = 0
            r10[r16] = r6
            r27 = r1
            r10 = 0
        L_0x006d:
            int[] r1 = r12.sequence
            r1 = r1[r0]
            if (r10 >= r1) goto L_0x0081
            r1 = r11[r0]
            int r10 = r10 + 1
            r28 = r2
            int r2 = r8.length
            double[] r2 = new double[r2]
            r1[r10] = r2
            r2 = r28
            goto L_0x006d
        L_0x0081:
            r28 = r2
            int r0 = r0 + 1
            r1 = r27
            goto L_0x0053
        L_0x0088:
            r27 = r1
            r28 = r2
            if (r7 == r8) goto L_0x0093
            int r0 = r8.length
            r1 = 0
            java.lang.System.arraycopy(r8, r1, r7, r1, r0)
        L_0x0093:
            r10 = 2
            int r0 = r8.length
            double[] r1 = new double[r0]
            int[] r0 = r12.sequence
            int r0 = r0.length
            int r0 = r0 * r10
            r2 = 1
            int r0 = r0 + r2
            int r2 = r8.length
            int[] r0 = new int[]{r0, r2}
            java.lang.Class<double> r2 = double.class
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r2, r0)
            r29 = r0
            double[][] r29 = (double[][]) r29
            int r0 = r12.mainSetDimension
            double[] r2 = new double[r0]
            r12.rescale(r7, r7, r2)
            double[] r0 = r12.vecRelativeTolerance
            if (r0 != 0) goto L_0x00c0
            r30 = r11
            double r10 = r12.scalRelativeTolerance
            r33 = r2
            r32 = r3
            goto L_0x00cd
        L_0x00c0:
            r30 = r11
            double[] r0 = r12.vecRelativeTolerance
            r10 = 0
            r16 = r0[r10]
            r33 = r2
            r32 = r3
            r10 = r16
        L_0x00cd:
            r2 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            double r2 = org.apache.commons.math3.util.FastMath.max(r2, r10)
            double r2 = org.apache.commons.math3.util.FastMath.log10(r2)
            int[] r0 = r12.sequence
            r34 = 0
            r36 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r0 = r0.length
            r10 = 2
            int r0 = r0 - r10
            r10 = 4603579539098121011(0x3fe3333333333333, double:0.6)
            double r10 = r10 * r2
            double r2 = r36 - r10
            double r2 = org.apache.commons.math3.util.FastMath.floor(r2)
            int r2 = (int) r2
            int r0 = org.apache.commons.math3.util.FastMath.min(r0, r2)
            r2 = 1
            int r10 = org.apache.commons.math3.util.FastMath.max(r2, r0)
            org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator r11 = new org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator
            org.apache.commons.math3.ode.EquationsMapper r23 = r64.getPrimaryMapper()
            org.apache.commons.math3.ode.EquationsMapper[] r24 = r64.getSecondaryMappers()
            r16 = r11
            r17 = r7
            r18 = r6
            r19 = r4
            r20 = r1
            r21 = r29
            r22 = r9
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24)
            double r2 = r64.getTime()
            r11.storeTime(r2)
            double r2 = r64.getTime()
            r12.stepStart = r2
            r16 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            double r2 = r64.getTime()
            r0 = r12
            r39 = r1
            r38 = r10
            r13 = r27
            r10 = r28
            r40 = r33
            r1 = r2
            r18 = r32
            r3 = r8
            r41 = r4
            r19 = r5
            r4 = r65
            r0.initIntegration(r1, r3, r4)
            double[] r0 = r12.costPerTimeUnit
            r1 = 0
            r0[r1] = r34
            r12.isLastStep = r1
            r21 = r16
            r1 = r34
            r0 = 1
            r16 = 0
            r17 = 1
            r20 = 0
        L_0x0154:
            if (r0 == 0) goto L_0x0196
            r11.shift()
            if (r16 != 0) goto L_0x0160
            double r3 = r12.stepStart
            r12.computeDerivatives(r3, r7, r6)
        L_0x0160:
            if (r17 == 0) goto L_0x0186
            r23 = 2
            int r0 = r23 * r38
            r24 = 1
            int r2 = r0 + 1
            double r4 = r12.stepStart
            r0 = r12
            r1 = r9
            r3 = r40
            r42 = r6
            r6 = r7
            r43 = r7
            r7 = r42
            r44 = r8
            r8 = r19
            r45 = r11
            r11 = r9
            r9 = r18
            double r0 = r0.initializeStep(r1, r2, r3, r4, r6, r7, r8, r9)
            r1 = r0
            goto L_0x0193
        L_0x0186:
            r42 = r6
            r43 = r7
            r44 = r8
            r45 = r11
            r23 = 2
            r24 = 1
            r11 = r9
        L_0x0193:
            r26 = 0
            goto L_0x01a5
        L_0x0196:
            r42 = r6
            r43 = r7
            r44 = r8
            r45 = r11
            r23 = 2
            r24 = 1
            r11 = r9
            r26 = r0
        L_0x01a5:
            r12.stepSize = r1
            if (r11 == 0) goto L_0x01b2
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            int r0 = (r3 > r65 ? 1 : (r3 == r65 ? 0 : -1))
            if (r0 > 0) goto L_0x01bd
        L_0x01b2:
            if (r11 != 0) goto L_0x01c3
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            int r0 = (r3 > r65 ? 1 : (r3 == r65 ? 0 : -1))
            if (r0 >= 0) goto L_0x01c3
        L_0x01bd:
            double r3 = r12.stepStart
            double r3 = r65 - r3
            r12.stepSize = r3
        L_0x01c3:
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            if (r11 == 0) goto L_0x01d3
            int r0 = (r3 > r65 ? 1 : (r3 == r65 ? 0 : -1))
            if (r0 < 0) goto L_0x01d1
        L_0x01ce:
            r0 = r24
            goto L_0x01d8
        L_0x01d1:
            r0 = 0
            goto L_0x01d8
        L_0x01d3:
            int r0 = (r3 > r65 ? 1 : (r3 == r65 ? 0 : -1))
            if (r0 > 0) goto L_0x01d1
            goto L_0x01ce
        L_0x01d8:
            r12.isLastStep = r0
            r0 = -1
            r6 = r0
            r27 = r1
            r31 = r21
            r21 = r24
            r9 = r38
            r22 = 0
        L_0x01e6:
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r21 == 0) goto L_0x03eb
            int r6 = r6 + 1
            double r1 = r12.stepStart
            double r4 = r12.stepSize
            r33 = r30[r6]
            if (r6 != 0) goto L_0x01fb
            r25 = 0
            r0 = r29[r25]
        L_0x01f8:
            r38 = r0
            goto L_0x0202
        L_0x01fb:
            r25 = 0
            int r0 = r6 + -1
            r0 = r13[r0]
            goto L_0x01f8
        L_0x0202:
            if (r6 != 0) goto L_0x0207
            r46 = r41
            goto L_0x020d
        L_0x0207:
            int r0 = r6 + -1
            r0 = r10[r0]
            r46 = r0
        L_0x020d:
            r0 = r12
            r3 = r43
            r47 = r6
            r14 = r7
            r7 = r40
            r8 = r33
            r48 = r9
            r9 = r38
            r14 = r10
            r15 = r24
            r10 = r46
            r15 = r11
            r50 = r13
            r13 = r25
            r23 = r30
            r49 = r45
            r11 = r19
            boolean r0 = r0.tryStep(r1, r3, r4, r6, r7, r8, r9, r10, r11)
            if (r0 != 0) goto L_0x0253
            double r0 = r12.stepSize
            double r2 = r12.stabilityReduction
            double r0 = r0 * r2
            double r0 = r12.filterStep(r0, r15, r13)
            double r27 = org.apache.commons.math3.util.FastMath.abs(r0)
            r21 = r13
            r10 = r14
            r11 = r15
            r30 = r23
            r6 = r47
            r9 = r48
            r45 = r49
            r13 = r50
        L_0x024c:
            r22 = 1
        L_0x024e:
            r23 = 2
            r24 = 1
            goto L_0x01e6
        L_0x0253:
            r6 = r47
            if (r6 <= 0) goto L_0x03de
            r7 = r41
            r12.extrapolate(r13, r6, r14, r7)
            r9 = r40
            r8 = r43
            r12.rescale(r8, r7, r9)
            r0 = r13
            r1 = r34
        L_0x0266:
            int r3 = r12.mainSetDimension
            if (r0 >= r3) goto L_0x027d
            r3 = r7[r0]
            r5 = r14[r13]
            r10 = r5[r0]
            double r3 = r3 - r10
            double r3 = org.apache.commons.math3.util.FastMath.abs(r3)
            r10 = r9[r0]
            double r3 = r3 / r10
            double r3 = r3 * r3
            double r1 = r1 + r3
            int r0 = r0 + 1
            goto L_0x0266
        L_0x027d:
            int r0 = r12.mainSetDimension
            double r3 = (double) r0
            double r1 = r1 / r3
            double r0 = org.apache.commons.math3.util.FastMath.sqrt(r1)
            r2 = 4831355200913801216(0x430c6bf526340000, double:1.0E15)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x03b8
            r2 = 1
            if (r6 <= r2) goto L_0x0297
            int r3 = (r0 > r31 ? 1 : (r0 == r31 ? 0 : -1))
            if (r3 <= 0) goto L_0x0297
            goto L_0x03b8
        L_0x0297:
            r3 = 4616189618054758400(0x4010000000000000, double:4.0)
            double r3 = r3 * r0
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r31 = org.apache.commons.math3.util.FastMath.max(r3, r10)
            r4 = 2
            int r3 = r4 * r6
            int r3 = r3 + r2
            double r2 = (double) r3
            double r2 = r10 / r2
            double r4 = r12.stepControl2
            r51 = r14
            double r13 = r12.stepControl1
            double r13 = r0 / r13
            double r13 = org.apache.commons.math3.util.FastMath.pow(r13, r2)
            double r4 = r4 / r13
            double r13 = r12.stepControl3
            double r2 = org.apache.commons.math3.util.FastMath.pow(r13, r2)
            double r13 = r12.stepControl4
            double r13 = r2 / r13
            double r2 = r10 / r2
            double r2 = org.apache.commons.math3.util.FastMath.min(r2, r4)
            double r2 = org.apache.commons.math3.util.FastMath.max(r13, r2)
            double[] r4 = r12.optimalStep
            double r10 = r12.stepSize
            double r10 = r10 * r2
            r2 = 1
            double r10 = r12.filterStep(r10, r15, r2)
            double r2 = org.apache.commons.math3.util.FastMath.abs(r10)
            r4[r6] = r2
            double[] r2 = r12.costPerTimeUnit
            int[] r3 = r12.costPerStep
            r3 = r3[r6]
            double r3 = (double) r3
            double[] r5 = r12.optimalStep
            r10 = r5[r6]
            double r3 = r3 / r10
            r2[r6] = r3
            r10 = r48
            int r2 = r6 - r10
            switch(r2) {
                case -1: goto L_0x0356;
                case 0: goto L_0x0319;
                case 1: goto L_0x02f5;
                default: goto L_0x02ed;
            }
        L_0x02ed:
            if (r17 != 0) goto L_0x039e
            boolean r2 = r12.isLastStep
            if (r2 == 0) goto L_0x03a6
            goto L_0x039e
        L_0x02f5:
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x031f
            r0 = 1
            if (r10 <= r0) goto L_0x0312
            double[] r0 = r12.costPerTimeUnit
            int r1 = r10 + -1
            r1 = r0[r1]
            double r3 = r12.orderControl1
            double[] r0 = r12.costPerTimeUnit
            r13 = r0[r10]
            double r3 = r3 * r13
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0312
            int r0 = r10 + -1
            r10 = r0
        L_0x0312:
            double[] r0 = r12.optimalStep
            r27 = r0[r10]
            r22 = 1
            goto L_0x031f
        L_0x0319:
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x0323
        L_0x031f:
            r21 = 0
            goto L_0x03a6
        L_0x0323:
            int[] r2 = r12.sequence
            int r3 = r6 + 1
            r2 = r2[r3]
            double r2 = (double) r2
            int[] r4 = r12.sequence
            r5 = 0
            r4 = r4[r5]
            double r4 = (double) r4
            double r2 = r2 / r4
            double r2 = r2 * r2
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x03a6
            r0 = 1
            if (r10 <= r0) goto L_0x034d
            double[] r0 = r12.costPerTimeUnit
            int r1 = r10 + -1
            r1 = r0[r1]
            double r3 = r12.orderControl1
            double[] r0 = r12.costPerTimeUnit
            r13 = r0[r10]
            double r3 = r3 * r13
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x034d
            int r0 = r10 + -1
            r10 = r0
        L_0x034d:
            double[] r0 = r12.optimalStep
            r27 = r0[r10]
        L_0x0351:
            r21 = 0
            r22 = 1
            goto L_0x03a6
        L_0x0356:
            r2 = 1
            if (r10 <= r2) goto L_0x03a6
            if (r20 != 0) goto L_0x03a6
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x0362
            goto L_0x031f
        L_0x0362:
            int[] r2 = r12.sequence
            r2 = r2[r10]
            double r2 = (double) r2
            int[] r4 = r12.sequence
            int r5 = r10 + 1
            r4 = r4[r5]
            double r4 = (double) r4
            double r2 = r2 * r4
            int[] r4 = r12.sequence
            r5 = 0
            r4 = r4[r5]
            int[] r11 = r12.sequence
            r11 = r11[r5]
            int r4 = r4 * r11
            double r4 = (double) r4
            double r2 = r2 / r4
            double r2 = r2 * r2
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x03a6
            r0 = 1
            if (r6 <= r0) goto L_0x0397
            double[] r0 = r12.costPerTimeUnit
            int r1 = r6 + -1
            r1 = r0[r1]
            double r3 = r12.orderControl1
            double[] r0 = r12.costPerTimeUnit
            r10 = r0[r6]
            double r3 = r3 * r10
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0397
            int r0 = r6 + -1
            goto L_0x0398
        L_0x0397:
            r0 = r6
        L_0x0398:
            double[] r1 = r12.optimalStep
            r27 = r1[r0]
            r10 = r0
            goto L_0x0351
        L_0x039e:
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x03a6
            goto L_0x031f
        L_0x03a6:
            r41 = r7
            r43 = r8
            r40 = r9
            r9 = r10
            r11 = r15
            r30 = r23
            r45 = r49
            r13 = r50
            r10 = r51
            goto L_0x024e
        L_0x03b8:
            r51 = r14
            r10 = r48
            double r0 = r12.stepSize
            double r2 = r12.stabilityReduction
            double r0 = r0 * r2
            r2 = 0
            double r0 = r12.filterStep(r0, r15, r2)
            double r27 = org.apache.commons.math3.util.FastMath.abs(r0)
            r41 = r7
            r43 = r8
            r40 = r9
            r9 = r10
            r11 = r15
            r30 = r23
            r45 = r49
            r13 = r50
            r10 = r51
            r21 = 0
            goto L_0x024c
        L_0x03de:
            r10 = r48
            r9 = r10
            r10 = r14
            r11 = r15
            r30 = r23
            r45 = r49
            r13 = r50
            goto L_0x024e
        L_0x03eb:
            r51 = r10
            r15 = r11
            r50 = r13
            r23 = r30
            r7 = r41
            r8 = r43
            r49 = r45
            r10 = r9
            r9 = r40
            if (r22 != 0) goto L_0x0408
            double r0 = r12.stepStart
            double r2 = r12.stepSize
            double r0 = r0 + r2
            r11 = r39
            r12.computeDerivatives(r0, r7, r11)
            goto L_0x040a
        L_0x0408:
            r11 = r39
        L_0x040a:
            double r0 = r63.getMaxStep()
            if (r22 != 0) goto L_0x053c
            r2 = 1
        L_0x0411:
            if (r2 > r6) goto L_0x041e
            r3 = 0
            r4 = r29[r3]
            r13 = r50
            r12.extrapolate(r3, r2, r13, r4)
            int r2 = r2 + 1
            goto L_0x0411
        L_0x041e:
            r13 = r50
            r2 = 2
            int r3 = r2 * r6
            int r2 = r12.mudif
            int r3 = r3 - r2
            int r3 = r3 + 3
            r2 = 0
        L_0x0429:
            if (r2 >= r3) goto L_0x04ef
            int r4 = r2 / 2
            int[] r5 = r12.sequence
            r5 = r5[r4]
            r52 = r0
            double r0 = (double) r5
            double r0 = r0 * r36
            double r0 = org.apache.commons.math3.util.FastMath.pow(r0, r2)
            r5 = r23[r4]
            int r5 = r5.length
            r14 = 2
            int r5 = r5 / r14
            r55 = r10
            r54 = r15
            r14 = r44
            r15 = 0
        L_0x0446:
            int r10 = r14.length
            if (r15 >= r10) goto L_0x045c
            int r10 = r2 + 1
            r10 = r29[r10]
            r17 = r23[r4]
            int r21 = r5 + r2
            r17 = r17[r21]
            r24 = r17[r15]
            double r24 = r24 * r0
            r10[r15] = r24
            int r15 = r15 + 1
            goto L_0x0446
        L_0x045c:
            r0 = 1
        L_0x045d:
            int r1 = r6 - r4
            if (r0 > r1) goto L_0x049c
            int[] r1 = r12.sequence
            int r5 = r0 + r4
            r1 = r1[r5]
            r56 = r11
            double r10 = (double) r1
            double r10 = r10 * r36
            double r10 = org.apache.commons.math3.util.FastMath.pow(r10, r2)
            r1 = r23[r5]
            int r1 = r1.length
            r15 = 2
            int r1 = r1 / r15
            r57 = r8
            r15 = 0
        L_0x0478:
            int r8 = r14.length
            if (r15 >= r8) goto L_0x048e
            int r8 = r0 + -1
            r8 = r13[r8]
            r17 = r23[r5]
            int r21 = r1 + r2
            r17 = r17[r21]
            r24 = r17[r15]
            double r24 = r24 * r10
            r8[r15] = r24
            int r15 = r15 + 1
            goto L_0x0478
        L_0x048e:
            int r1 = r2 + 1
            r1 = r29[r1]
            r12.extrapolate(r4, r0, r13, r1)
            int r0 = r0 + 1
            r11 = r56
            r8 = r57
            goto L_0x045d
        L_0x049c:
            r57 = r8
            r56 = r11
            r0 = 0
        L_0x04a1:
            int r1 = r14.length
            if (r0 >= r1) goto L_0x04b2
            int r1 = r2 + 1
            r1 = r29[r1]
            r4 = r1[r0]
            double r10 = r12.stepSize
            double r4 = r4 * r10
            r1[r0] = r4
            int r0 = r0 + 1
            goto L_0x04a1
        L_0x04b2:
            int r2 = r2 + 1
            int r0 = r2 / 2
        L_0x04b6:
            if (r0 > r6) goto L_0x04e1
            r1 = r23[r0]
            int r1 = r1.length
            r4 = 1
            int r1 = r1 - r4
            r4 = 2
        L_0x04be:
            int r10 = r4 * r2
            if (r1 < r10) goto L_0x04de
            r5 = 0
        L_0x04c3:
            int r8 = r14.length
            if (r5 >= r8) goto L_0x04db
            r8 = r23[r0]
            r8 = r8[r1]
            r10 = r8[r5]
            r15 = r23[r0]
            int r17 = r1 + -2
            r15 = r15[r17]
            r24 = r15[r5]
            double r10 = r10 - r24
            r8[r5] = r10
            int r5 = r5 + 1
            goto L_0x04c3
        L_0x04db:
            int r1 = r1 + -1
            goto L_0x04be
        L_0x04de:
            int r0 = r0 + 1
            goto L_0x04b6
        L_0x04e1:
            r44 = r14
            r0 = r52
            r15 = r54
            r10 = r55
            r11 = r56
            r8 = r57
            goto L_0x0429
        L_0x04ef:
            r52 = r0
            r57 = r8
            r55 = r10
            r56 = r11
            r54 = r15
            r14 = r44
            r4 = 2
            if (r3 < 0) goto L_0x0539
            r8 = r49
            r11 = r8
            org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator r11 = (org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator) r11
            double r0 = r12.stepSize
            r11.computeCoefficients(r3, r0)
            boolean r0 = r12.useInterpolationError
            if (r0 == 0) goto L_0x054c
            double r0 = r11.estimateError(r9)
            double r10 = r12.stepSize
            int r3 = r3 + 4
            double r2 = (double) r3
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r2 = r24 / r2
            double r2 = org.apache.commons.math3.util.FastMath.pow(r0, r2)
            r4 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r2 = org.apache.commons.math3.util.FastMath.max(r2, r4)
            double r10 = r10 / r2
            double r2 = org.apache.commons.math3.util.FastMath.abs(r10)
            r4 = 4621819117588971520(0x4024000000000000, double:10.0)
            int r10 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r10 <= 0) goto L_0x0537
            r10 = r2
            r27 = r10
            r22 = 1
            goto L_0x054e
        L_0x0537:
            r10 = r2
            goto L_0x054e
        L_0x0539:
            r8 = r49
            goto L_0x054c
        L_0x053c:
            r52 = r0
            r57 = r8
            r55 = r10
            r56 = r11
            r54 = r15
            r14 = r44
            r8 = r49
            r13 = r50
        L_0x054c:
            r10 = r52
        L_0x054e:
            if (r22 != 0) goto L_0x0697
            double r0 = r12.stepStart
            double r2 = r12.stepSize
            double r0 = r0 + r2
            r8.storeTime(r0)
            r0 = r12
            r1 = r8
            r2 = r7
            r3 = r56
            r15 = 2
            r4 = r65
            double r0 = r0.acceptStep(r1, r2, r3, r4)
            r12.stepStart = r0
            double r0 = r12.stepStart
            r8.storeTime(r0)
            int r0 = r14.length
            r1 = r57
            r2 = 0
            java.lang.System.arraycopy(r7, r2, r1, r2, r0)
            int r0 = r14.length
            r3 = r42
            r4 = r56
            java.lang.System.arraycopy(r4, r2, r3, r2, r0)
            r2 = 1
            if (r6 != r2) goto L_0x0597
            if (r20 == 0) goto L_0x0589
            r58 = r3
            r59 = r4
            r60 = r7
            r61 = r8
            r3 = r15
            goto L_0x0593
        L_0x0589:
            r58 = r3
            r59 = r4
            r60 = r7
            r61 = r8
            r2 = r15
            r3 = r2
        L_0x0593:
            r0 = r55
            goto L_0x061c
        L_0x0597:
            r0 = r55
            if (r6 > r0) goto L_0x05e0
            double[] r5 = r12.costPerTimeUnit
            int r16 = r6 + -1
            r24 = r5[r16]
            r58 = r3
            double r2 = r12.orderControl1
            double[] r5 = r12.costPerTimeUnit
            r26 = r5[r6]
            double r2 = r2 * r26
            int r5 = (r24 > r2 ? 1 : (r24 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x05ba
            r59 = r4
            r60 = r7
            r61 = r8
            r3 = r15
            r2 = r16
            goto L_0x061c
        L_0x05ba:
            double[] r2 = r12.costPerTimeUnit
            r24 = r2[r6]
            double r2 = r12.orderControl2
            double[] r5 = r12.costPerTimeUnit
            r16 = r5[r16]
            double r2 = r2 * r16
            int r5 = (r24 > r2 ? 1 : (r24 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x05d7
            int r2 = r6 + 1
            int[] r3 = r12.sequence
            int r3 = r3.length
            int r3 = r3 - r15
            int r2 = org.apache.commons.math3.util.FastMath.min(r2, r3)
            r59 = r4
            goto L_0x05da
        L_0x05d7:
            r59 = r4
            r2 = r6
        L_0x05da:
            r60 = r7
            r61 = r8
            r3 = r15
            goto L_0x061c
        L_0x05e0:
            r58 = r3
            int r2 = r6 + -1
            if (r6 <= r15) goto L_0x05fc
            double[] r3 = r12.costPerTimeUnit
            int r5 = r6 + -2
            r16 = r3[r5]
            r59 = r4
            double r3 = r12.orderControl1
            double[] r15 = r12.costPerTimeUnit
            r24 = r15[r2]
            double r3 = r3 * r24
            int r15 = (r16 > r3 ? 1 : (r16 == r3 ? 0 : -1))
            if (r15 >= 0) goto L_0x05fe
            r2 = r5
            goto L_0x05fe
        L_0x05fc:
            r59 = r4
        L_0x05fe:
            double[] r3 = r12.costPerTimeUnit
            r4 = r3[r6]
            r60 = r7
            r61 = r8
            double r7 = r12.orderControl2
            double[] r3 = r12.costPerTimeUnit
            r15 = r3[r2]
            double r7 = r7 * r15
            int r3 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x061b
            int[] r2 = r12.sequence
            int r2 = r2.length
            r3 = 2
            int r2 = r2 - r3
            int r2 = org.apache.commons.math3.util.FastMath.min(r6, r2)
            goto L_0x061c
        L_0x061b:
            r3 = 2
        L_0x061c:
            if (r20 == 0) goto L_0x0638
            int r2 = org.apache.commons.math3.util.FastMath.min(r2, r6)
            double r4 = r12.stepSize
            double r4 = org.apache.commons.math3.util.FastMath.abs(r4)
            double[] r0 = r12.optimalStep
            r6 = r0[r2]
            double r4 = org.apache.commons.math3.util.FastMath.min(r4, r6)
            r62 = r2
            r27 = r4
            r7 = r54
            r2 = 0
            goto L_0x068f
        L_0x0638:
            if (r2 > r6) goto L_0x0644
            double[] r0 = r12.optimalStep
            r4 = r0[r2]
            r62 = r2
            r7 = r54
            r2 = 0
            goto L_0x068d
        L_0x0644:
            if (r6 >= r0) goto L_0x0674
            double[] r0 = r12.costPerTimeUnit
            r4 = r0[r6]
            double r7 = r12.orderControl2
            double[] r0 = r12.costPerTimeUnit
            int r15 = r6 + -1
            r15 = r0[r15]
            double r7 = r7 * r15
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0674
            double[] r0 = r12.optimalStep
            r4 = r0[r6]
            int[] r0 = r12.costPerStep
            int r7 = r2 + 1
            r0 = r0[r7]
            double r7 = (double) r0
            double r4 = r4 * r7
            int[] r0 = r12.costPerStep
            r0 = r0[r6]
            double r6 = (double) r0
            double r4 = r4 / r6
            r7 = r54
            r0 = 0
            double r4 = r12.filterStep(r4, r7, r0)
            r62 = r2
            r2 = r0
            goto L_0x068d
        L_0x0674:
            r7 = r54
            double[] r0 = r12.optimalStep
            r4 = r0[r6]
            int[] r0 = r12.costPerStep
            r0 = r0[r2]
            r62 = r2
            double r2 = (double) r0
            double r4 = r4 * r2
            int[] r0 = r12.costPerStep
            r0 = r0[r6]
            double r2 = (double) r0
            double r4 = r4 / r2
            r2 = 0
            double r4 = r12.filterStep(r4, r7, r2)
        L_0x068d:
            r27 = r4
        L_0x068f:
            r3 = r27
            r38 = r62
            r0 = 1
            r16 = 1
            goto L_0x06ac
        L_0x0697:
            r60 = r7
            r61 = r8
            r58 = r42
            r7 = r54
            r0 = r55
            r59 = r56
            r1 = r57
            r2 = 0
            r38 = r0
            r0 = r26
            r3 = r27
        L_0x06ac:
            double r3 = org.apache.commons.math3.util.FastMath.min(r3, r10)
            if (r7 != 0) goto L_0x06b3
            double r3 = -r3
        L_0x06b3:
            if (r22 == 0) goto L_0x06ba
            r12.isLastStep = r2
            r20 = 1
            goto L_0x06bc
        L_0x06ba:
            r20 = r2
        L_0x06bc:
            boolean r5 = r12.isLastStep
            if (r5 == 0) goto L_0x06ce
            double r2 = r12.stepStart
            r5 = r64
            r5.setTime(r2)
            r5.setCompleteState(r1)
            r63.resetInternalState()
            return
        L_0x06ce:
            r17 = r2
            r40 = r9
            r8 = r14
            r30 = r23
            r21 = r31
            r10 = r51
            r6 = r58
            r39 = r59
            r41 = r60
            r11 = r61
            r9 = r7
            r7 = r1
            r1 = r3
            goto L_0x0154
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate(org.apache.commons.math3.ode.ExpandableStatefulODE, double):void");
    }
}
