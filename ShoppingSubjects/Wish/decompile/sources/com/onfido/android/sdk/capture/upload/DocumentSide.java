package com.onfido.android.sdk.capture.upload;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.api.client.data.DocSide;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

public final class DocumentSide implements Serializable {
    private final String a;
    private final DocSide b;
    private final DocumentType c;

    public DocumentSide(String str, DocSide docSide, DocumentType documentType) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(docSide, "side");
        Intrinsics.checkParameterIsNotNull(documentType, "type");
        this.a = str;
        this.b = docSide;
        this.c = documentType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r2.c, r3.c) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.onfido.android.sdk.capture.upload.DocumentSide
            if (r0 == 0) goto L_0x0027
            com.onfido.android.sdk.capture.upload.DocumentSide r3 = (com.onfido.android.sdk.capture.upload.DocumentSide) r3
            java.lang.String r0 = r2.a
            java.lang.String r1 = r3.a
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            com.onfido.api.client.data.DocSide r0 = r2.b
            com.onfido.api.client.data.DocSide r1 = r3.b
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            com.onfido.android.sdk.capture.DocumentType r0 = r2.c
            com.onfido.android.sdk.capture.DocumentType r3 = r3.c
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r3 = 0
            return r3
        L_0x0029:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.upload.DocumentSide.equals(java.lang.Object):boolean");
    }

    public final DocSide getSide() {
        return this.b;
    }

    public final DocumentType getType() {
        return this.c;
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        DocSide docSide = this.b;
        int hashCode2 = (hashCode + (docSide != null ? docSide.hashCode() : 0)) * 31;
        DocumentType documentType = this.c;
        if (documentType != null) {
            i = documentType.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DocumentSide(id=");
        sb.append(this.a);
        sb.append(", side=");
        sb.append(this.b);
        sb.append(", type=");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }
}
