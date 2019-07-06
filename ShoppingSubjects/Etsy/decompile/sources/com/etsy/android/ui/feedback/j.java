package com.etsy.android.ui.feedback;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Feedback;
import kotlin.jvm.internal.p;

/* compiled from: FeedbackRepository.kt */
public abstract class j {

    /* compiled from: FeedbackRepository.kt */
    public static final class a extends j {
        private final int a;
        private final String b;
        private final com.etsy.android.lib.core.a.a<Feedback> c;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof a) {
                a aVar = (a) obj;
                return (this.a == aVar.a) && p.a((Object) this.b, (Object) aVar.b) && p.a((Object) this.c, (Object) aVar.c);
            }
        }

        public int hashCode() {
            int i = this.a * 31;
            String str = this.b;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            com.etsy.android.lib.core.a.a<Feedback> aVar = this.c;
            if (aVar != null) {
                i2 = aVar.hashCode();
            }
            return hashCode + i2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Failure(code=");
            sb.append(this.a);
            sb.append(", error=");
            sb.append(this.b);
            sb.append(", result=");
            sb.append(this.c);
            sb.append(")");
            return sb.toString();
        }

        public a(int i, String str, com.etsy.android.lib.core.a.a<Feedback> aVar) {
            p.b(aVar, "result");
            super(null);
            this.a = i;
            this.b = str;
            this.c = aVar;
        }
    }

    /* compiled from: FeedbackRepository.kt */
    public static final class b extends j {
        private final String a;

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (kotlin.jvm.internal.p.a((java.lang.Object) r1.a, (java.lang.Object) ((com.etsy.android.ui.feedback.j.b) r2).a) != false) goto L_0x0015;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r2) {
            /*
                r1 = this;
                if (r1 == r2) goto L_0x0015
                boolean r0 = r2 instanceof com.etsy.android.ui.feedback.j.b
                if (r0 == 0) goto L_0x0013
                com.etsy.android.ui.feedback.j$b r2 = (com.etsy.android.ui.feedback.j.b) r2
                java.lang.String r0 = r1.a
                java.lang.String r2 = r2.a
                boolean r2 = kotlin.jvm.internal.p.a(r0, r2)
                if (r2 == 0) goto L_0x0013
                goto L_0x0015
            L_0x0013:
                r2 = 0
                return r2
            L_0x0015:
                r2 = 1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.feedback.j.b.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.a;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Success(response=");
            sb.append(this.a);
            sb.append(")");
            return sb.toString();
        }

        public b(String str) {
            p.b(str, ResponseConstants.RESPONSE);
            super(null);
            this.a = str;
        }
    }

    private j() {
    }

    public /* synthetic */ j(o oVar) {
        this();
    }
}
