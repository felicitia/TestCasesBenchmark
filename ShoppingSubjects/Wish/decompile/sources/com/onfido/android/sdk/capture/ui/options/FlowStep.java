package com.onfido.android.sdk.capture.ui.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public class FlowStep implements Serializable {
    public static final FlowStep CAPTURE_DOCUMENT = new FlowStep(FlowAction.CAPTURE_DOCUMENT);
    public static final FlowStep CAPTURE_FACE = new FlowStep(FlowAction.CAPTURE_FACE);
    public static final Companion Companion = new Companion(null);
    public static final FlowStep FINAL = new FlowStep(FlowAction.FINAL);
    public static final FlowStep WELCOME = new FlowStep(FlowAction.WELCOME);
    private BaseOptions a;
    private final FlowAction b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<FlowStep> getDefaultFlow() {
            Object[] objArr = (Object[]) FlowAction.Companion.getDefaultFlow();
            Collection arrayList = new ArrayList(objArr.length);
            for (Object obj : objArr) {
                arrayList.add(new FlowStep((FlowAction) obj));
            }
            List<FlowStep> unmodifiableList = Collections.unmodifiableList((List) arrayList);
            Intrinsics.checkExpressionValueIsNotNull(unmodifiableList, "Collections.unmodifiable…aultFlow.map(::FlowStep))");
            return unmodifiableList;
        }
    }

    public FlowStep(FlowAction flowAction) {
        Intrinsics.checkParameterIsNotNull(flowAction, "action");
        this.b = flowAction;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlowStep)) {
            return false;
        }
        FlowStep flowStep = (FlowStep) obj;
        return !(Intrinsics.areEqual(this.b, flowStep.b) ^ true) && !(Intrinsics.areEqual(this.a, flowStep.a) ^ true);
    }

    public final FlowAction getAction() {
        return this.b;
    }

    public final BaseOptions getOptions() {
        return this.a;
    }

    public int hashCode() {
        int hashCode = this.b.hashCode() * 31;
        BaseOptions baseOptions = this.a;
        return hashCode + (baseOptions != null ? baseOptions.hashCode() : 0);
    }

    public final void setOptions(BaseOptions baseOptions) {
        this.a = baseOptions;
    }

    public String toString() {
        BaseOptions baseOptions = this.a;
        if (baseOptions != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(this.b.name());
            sb.append(" withOptions: ");
            sb.append(baseOptions);
            String sb2 = sb.toString();
            if (sb2 != null) {
                return sb2;
            }
        }
        return this.b.name();
    }
}
