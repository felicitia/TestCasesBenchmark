package com.onfido.android.sdk.capture.ui.camera;

import android.animation.IntEvaluator;
import android.animation.TypeEvaluator;
import kotlin.jvm.internal.Intrinsics;

public final class DoubleIntArrayEvaluator implements TypeEvaluator<int[]> {
    private IntEvaluator a = new IntEvaluator();

    public int[] evaluate(float f, int[] iArr, int[] iArr2) {
        Intrinsics.checkParameterIsNotNull(iArr, "startValues");
        Intrinsics.checkParameterIsNotNull(iArr2, "endValues");
        if (iArr.length != iArr2.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            Integer evaluate = this.a.evaluate(f, Integer.valueOf(iArr[i]), Integer.valueOf(iArr2[i]));
            Intrinsics.checkExpressionValueIsNotNull(evaluate, "evaluator.evaluate(fract…tValues[i], endValues[i])");
            iArr3[i] = evaluate.intValue();
        }
        return iArr3;
    }
}
