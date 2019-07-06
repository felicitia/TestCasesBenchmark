package com.onfido.android.sdk.capture.upload;

import com.onfido.android.sdk.capture.DocumentType;
import java.io.Serializable;

public final class Captures implements Serializable {
    private Document a;

    public static final class Document implements Serializable {
        private DocumentSide a;
        private DocumentSide b;
        private DocumentType c;

        public final DocumentSide getBack() {
            return this.b;
        }

        public final DocumentSide getFront() {
            return this.a;
        }

        public final DocumentType getType() {
            return this.c;
        }

        public final void setBack(DocumentSide documentSide) {
            this.b = documentSide;
        }

        public final void setFront(DocumentSide documentSide) {
            this.a = documentSide;
        }

        public final void setType(DocumentType documentType) {
            this.c = documentType;
        }
    }

    public final Document getDocument() {
        return this.a;
    }

    public final void setDocument(Document document) {
        this.a = document;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document:\n\tFront: ");
        Document document = this.a;
        DocumentType documentType = null;
        sb.append(document != null ? document.getFront() : null);
        sb.append("\n\tBack: ");
        Document document2 = this.a;
        sb.append(document2 != null ? document2.getBack() : null);
        sb.append("\n\tType: ");
        Document document3 = this.a;
        if (document3 != null) {
            documentType = document3.getType();
        }
        sb.append(documentType);
        return sb.toString();
    }
}
