package com.onfido.android.sdk.capture;

import com.onfido.android.sdk.capture.errors.MultipleApplicantsException;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.api.client.data.Applicant;
import java.io.Serializable;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public class OnfidoConfig implements Serializable {
    public static final Companion Companion = new Companion(null);
    private final Applicant a;
    private final FlowStep[] b;
    private final String c;
    private final String d;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Applicant a;
        private FlowStep[] b;
        private String c;
        private String d;

        static final class b extends Lambda implements Function0<Unit> {
            final /* synthetic */ Builder a;
            final /* synthetic */ String b;

            b(Builder builder, String str) {
                this.a = builder;
                this.b = str;
                super(0);
            }

            public final void a() {
                this.a.a = Applicant.builder().withId(this.b).build();
            }

            public /* synthetic */ Object invoke() {
                a();
                return Unit.INSTANCE;
            }
        }

        private final void a(boolean z, Function0<Unit> function0, Exception exc) {
            if (z) {
                function0.invoke();
                return;
            }
            throw exc;
        }

        public final OnfidoConfig build() {
            OnfidoConfig onfidoConfig = new OnfidoConfig(this.a, this.b, this.c, this.d, null);
            return onfidoConfig;
        }

        public final Builder withApplicant(String str) {
            Intrinsics.checkParameterIsNotNull(str, "id");
            a(this.a == null, new b(this, str), new MultipleApplicantsException());
            return this;
        }

        public final Builder withCustomFlow(FlowStep[] flowStepArr) {
            Intrinsics.checkParameterIsNotNull(flowStepArr, "steps");
            this.b = flowStepArr;
            return this;
        }

        public final Builder withToken(String str) {
            Intrinsics.checkParameterIsNotNull(str, "token");
            this.d = str;
            return this;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Builder builder() {
            return new Builder();
        }
    }

    private OnfidoConfig(Applicant applicant, FlowStep[] flowStepArr, String str, String str2) {
        this.a = applicant;
        this.b = flowStepArr;
        this.c = str;
        this.d = str2;
    }

    public /* synthetic */ OnfidoConfig(Applicant applicant, FlowStep[] flowStepArr, String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(applicant, flowStepArr, str, str2);
    }

    public static final Builder builder() {
        return Companion.builder();
    }

    public Applicant getApplicant() {
        return this.a;
    }

    public final String getBaseUrl() {
        return this.c;
    }

    public List<FlowStep> getFlowSteps() {
        FlowStep[] flowStepArr = this.b;
        if (flowStepArr != null) {
            List<FlowStep> list = ArraysKt.toList((Object[]) flowStepArr);
            if (list != null) {
                return list;
            }
        }
        return FlowStep.Companion.getDefaultFlow();
    }

    public final String getToken() {
        return this.d;
    }
}
