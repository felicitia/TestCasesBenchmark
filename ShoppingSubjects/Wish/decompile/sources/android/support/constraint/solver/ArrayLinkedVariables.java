package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable.Type;
import java.util.Arrays;

public class ArrayLinkedVariables {
    private int ROW_SIZE = 8;
    private SolverVariable candidate = null;
    int currentSize = 0;
    private int[] mArrayIndices = new int[this.ROW_SIZE];
    private int[] mArrayNextIndices = new int[this.ROW_SIZE];
    private float[] mArrayValues = new float[this.ROW_SIZE];
    private final Cache mCache;
    private boolean mDidFillOnce = false;
    private int mHead = -1;
    private int mLast = -1;
    private final ArrayRow mRow;

    ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable);
        } else if (this.mHead == -1) {
            this.mHead = 0;
            this.mArrayValues[this.mHead] = f;
            this.mArrayIndices[this.mHead] = solverVariable.id;
            this.mArrayNextIndices[this.mHead] = -1;
            solverVariable.usageInRowCount++;
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
                if (this.mLast >= this.mArrayIndices.length) {
                    this.mDidFillOnce = true;
                    this.mLast = this.mArrayIndices.length - 1;
                }
            }
        } else {
            int i = this.mHead;
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.currentSize) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    this.mArrayValues[i] = f;
                    return;
                }
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
                i2++;
            }
            int i4 = this.mLast + 1;
            if (this.mDidFillOnce) {
                if (this.mArrayIndices[this.mLast] == -1) {
                    i4 = this.mLast;
                } else {
                    i4 = this.mArrayIndices.length;
                }
            }
            if (i4 >= this.mArrayIndices.length && this.currentSize < this.mArrayIndices.length) {
                int i5 = 0;
                while (true) {
                    if (i5 >= this.mArrayIndices.length) {
                        break;
                    } else if (this.mArrayIndices[i5] == -1) {
                        i4 = i5;
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            if (i4 >= this.mArrayIndices.length) {
                i4 = this.mArrayIndices.length;
                this.ROW_SIZE *= 2;
                this.mDidFillOnce = false;
                this.mLast = i4 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i4] = solverVariable.id;
            this.mArrayValues[i4] = f;
            if (i3 != -1) {
                this.mArrayNextIndices[i4] = this.mArrayNextIndices[i3];
                this.mArrayNextIndices[i3] = i4;
            } else {
                this.mArrayNextIndices[i4] = this.mHead;
                this.mHead = i4;
            }
            solverVariable.usageInRowCount++;
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            if (this.currentSize >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
            }
            if (this.mLast >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
                this.mLast = this.mArrayIndices.length - 1;
            }
        }
    }

    public final void add(SolverVariable solverVariable, float f) {
        if (f != 0.0f) {
            if (this.mHead == -1) {
                this.mHead = 0;
                this.mArrayValues[this.mHead] = f;
                this.mArrayIndices[this.mHead] = solverVariable.id;
                this.mArrayNextIndices[this.mHead] = -1;
                solverVariable.usageInRowCount++;
                this.currentSize++;
                if (!this.mDidFillOnce) {
                    this.mLast++;
                    if (this.mLast >= this.mArrayIndices.length) {
                        this.mDidFillOnce = true;
                        this.mLast = this.mArrayIndices.length - 1;
                    }
                }
                return;
            }
            int i = this.mHead;
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.currentSize) {
                int i4 = this.mArrayIndices[i];
                if (i4 == solverVariable.id) {
                    float[] fArr = this.mArrayValues;
                    fArr[i] = fArr[i] + f;
                    if (this.mArrayValues[i] == 0.0f) {
                        if (i == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i];
                        } else {
                            this.mArrayNextIndices[i3] = this.mArrayNextIndices[i];
                        }
                        this.mCache.mIndexedVariables[i4].removeClientEquation(this.mRow);
                        if (this.mDidFillOnce) {
                            this.mLast = i;
                        }
                        solverVariable.usageInRowCount--;
                        this.currentSize--;
                    }
                    return;
                }
                if (this.mArrayIndices[i] < solverVariable.id) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
                i2++;
            }
            int i5 = this.mLast + 1;
            if (this.mDidFillOnce) {
                if (this.mArrayIndices[this.mLast] == -1) {
                    i5 = this.mLast;
                } else {
                    i5 = this.mArrayIndices.length;
                }
            }
            if (i5 >= this.mArrayIndices.length && this.currentSize < this.mArrayIndices.length) {
                int i6 = 0;
                while (true) {
                    if (i6 >= this.mArrayIndices.length) {
                        break;
                    } else if (this.mArrayIndices[i6] == -1) {
                        i5 = i6;
                        break;
                    } else {
                        i6++;
                    }
                }
            }
            if (i5 >= this.mArrayIndices.length) {
                i5 = this.mArrayIndices.length;
                this.ROW_SIZE *= 2;
                this.mDidFillOnce = false;
                this.mLast = i5 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i5] = solverVariable.id;
            this.mArrayValues[i5] = f;
            if (i3 != -1) {
                this.mArrayNextIndices[i5] = this.mArrayNextIndices[i3];
                this.mArrayNextIndices[i3] = i5;
            } else {
                this.mArrayNextIndices[i5] = this.mHead;
                this.mHead = i5;
            }
            solverVariable.usageInRowCount++;
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            if (this.mLast >= this.mArrayIndices.length) {
                this.mDidFillOnce = true;
                this.mLast = this.mArrayIndices.length - 1;
            }
        }
    }

    public final float remove(SolverVariable solverVariable) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        if (this.mHead == -1) {
            return 0.0f;
        }
        int i = this.mHead;
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.currentSize) {
            int i4 = this.mArrayIndices[i];
            if (i4 == solverVariable.id) {
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    this.mArrayNextIndices[i3] = this.mArrayNextIndices[i];
                }
                this.mCache.mIndexedVariables[i4].removeClientEquation(this.mRow);
                solverVariable.usageInRowCount--;
                this.currentSize--;
                this.mArrayIndices[i] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i;
                }
                return this.mArrayValues[i];
            }
            i2++;
            i3 = i;
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    public final void clear() {
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    /* access modifiers changed from: 0000 */
    public final boolean containsKey(SolverVariable solverVariable) {
        if (this.mHead == -1) {
            return false;
        }
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return true;
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void invert() {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] * -1.0f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void divideByAmount(float f) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateClientEquations(ArrayRow arrayRow) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            this.mCache.mIndexedVariables[this.mArrayIndices[i]].addClientEquation(arrayRow);
            i = this.mArrayNextIndices[i];
            i2++;
        }
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.usageInRowCount <= 1;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0062 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.constraint.solver.SolverVariable chooseSubject(android.support.constraint.solver.LinearSystem r15) {
        /*
            r14 = this;
            int r0 = r14.mHead
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = r1
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x000a:
            r9 = -1
            if (r0 == r9) goto L_0x008e
            int r9 = r14.currentSize
            if (r2 >= r9) goto L_0x008e
            float[] r9 = r14.mArrayValues
            r9 = r9[r0]
            r10 = 981668463(0x3a83126f, float:0.001)
            int r11 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r11 >= 0) goto L_0x0029
            r10 = -1165815185(0xffffffffba83126f, float:-0.001)
            int r10 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r10 <= 0) goto L_0x0032
            float[] r9 = r14.mArrayValues
            r9[r0] = r3
        L_0x0027:
            r9 = 0
            goto L_0x0032
        L_0x0029:
            int r10 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x0032
            float[] r9 = r14.mArrayValues
            r9[r0] = r3
            goto L_0x0027
        L_0x0032:
            android.support.constraint.solver.Cache r10 = r14.mCache
            android.support.constraint.solver.SolverVariable[] r10 = r10.mIndexedVariables
            int[] r11 = r14.mArrayIndices
            r11 = r11[r0]
            r10 = r10[r11]
            android.support.constraint.solver.SolverVariable$Type r11 = r10.mType
            android.support.constraint.solver.SolverVariable$Type r12 = android.support.constraint.solver.SolverVariable.Type.UNRESTRICTED
            r13 = 1
            if (r11 != r12) goto L_0x0062
            if (r1 != 0) goto L_0x004d
            boolean r1 = r14.isNew(r10, r15)
        L_0x0049:
            r6 = r1
            r5 = r9
            r1 = r10
            goto L_0x0086
        L_0x004d:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x0056
            boolean r1 = r14.isNew(r10, r15)
            goto L_0x0049
        L_0x0056:
            if (r6 != 0) goto L_0x0086
            boolean r11 = r14.isNew(r10, r15)
            if (r11 == 0) goto L_0x0086
            r5 = r9
            r1 = r10
            r6 = 1
            goto L_0x0086
        L_0x0062:
            if (r1 != 0) goto L_0x0086
            int r11 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r11 >= 0) goto L_0x0086
            if (r4 != 0) goto L_0x0072
            boolean r4 = r14.isNew(r10, r15)
        L_0x006e:
            r8 = r4
            r7 = r9
            r4 = r10
            goto L_0x0086
        L_0x0072:
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x007b
            boolean r4 = r14.isNew(r10, r15)
            goto L_0x006e
        L_0x007b:
            if (r8 != 0) goto L_0x0086
            boolean r11 = r14.isNew(r10, r15)
            if (r11 == 0) goto L_0x0086
            r7 = r9
            r4 = r10
            r8 = 1
        L_0x0086:
            int[] r9 = r14.mArrayNextIndices
            r0 = r9[r0]
            int r2 = r2 + 1
            goto L_0x000a
        L_0x008e:
            if (r1 == 0) goto L_0x0091
            return r1
        L_0x0091:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.ArrayLinkedVariables.chooseSubject(android.support.constraint.solver.LinearSystem):android.support.constraint.solver.SolverVariable");
    }

    /* access modifiers changed from: 0000 */
    public void updateFromRow(ArrayRow arrayRow, ArrayRow arrayRow2) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.currentSize) {
                if (this.mArrayIndices[i] == arrayRow2.variable.id) {
                    float f = this.mArrayValues[i];
                    remove(arrayRow2.variable);
                    ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                    int i3 = arrayLinkedVariables.mHead;
                    int i4 = 0;
                    while (i3 != -1 && i4 < arrayLinkedVariables.currentSize) {
                        add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f);
                        i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                        i4++;
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    arrayRow2.variable.removeClientEquation(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateFromSystem(ArrayRow arrayRow, ArrayRow[] arrayRowArr) {
        int i = this.mHead;
        while (true) {
            int i2 = 0;
            while (i != -1 && i2 < this.currentSize) {
                SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable.definitionId != -1) {
                    float f = this.mArrayValues[i];
                    remove(solverVariable);
                    ArrayRow arrayRow2 = arrayRowArr[solverVariable.definitionId];
                    if (!arrayRow2.isSimpleDefinition) {
                        ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                        int i3 = arrayLinkedVariables.mHead;
                        int i4 = 0;
                        while (i3 != -1 && i4 < arrayLinkedVariables.currentSize) {
                            add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[i3]], arrayLinkedVariables.mArrayValues[i3] * f);
                            i3 = arrayLinkedVariables.mArrayNextIndices[i3];
                            i4++;
                        }
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    arrayRow2.variable.removeClientEquation(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                    i2++;
                }
            }
            return;
        }
    }

    /* access modifiers changed from: 0000 */
    public SolverVariable getPivotCandidate(boolean[] zArr, SolverVariable solverVariable) {
        int i = this.mHead;
        int i2 = 0;
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if ((zArr == null || !zArr[solverVariable3.id]) && solverVariable3 != solverVariable && (solverVariable3.mType == Type.SLACK || solverVariable3.mType == Type.ERROR)) {
                    float f2 = this.mArrayValues[i];
                    if (f2 < f) {
                        solverVariable2 = solverVariable3;
                        f = f2;
                    }
                }
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return solverVariable2;
    }

    /* access modifiers changed from: 0000 */
    public final SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.currentSize) {
            if (i3 == i) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final float getVariableValue(int i) {
        int i2 = this.mHead;
        int i3 = 0;
        while (i2 != -1 && i3 < this.currentSize) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
            i3++;
        }
        return 0.0f;
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return 0.0f;
    }

    public String toString() {
        String str = "";
        int i = this.mHead;
        int i2 = 0;
        while (i != -1 && i2 < this.currentSize) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" -> ");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(this.mArrayValues[i]);
            sb3.append(" : ");
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(sb4);
            sb5.append(this.mCache.mIndexedVariables[this.mArrayIndices[i]]);
            str = sb5.toString();
            i = this.mArrayNextIndices[i];
            i2++;
        }
        return str;
    }
}
