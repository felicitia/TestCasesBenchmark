package com.onfido.api.client;

import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentUpload;
import java.util.Map;
import retrofit2.Call;

class UploadDocumentAPI {
    private final MultipartDocumentRequestCreator documentRequestCreator;
    private final OnfidoService onfidoService;

    UploadDocumentAPI(OnfidoService onfidoService2, MultipartDocumentRequestCreator multipartDocumentRequestCreator) {
        this.onfidoService = onfidoService2;
        this.documentRequestCreator = multipartDocumentRequestCreator;
    }

    /* access modifiers changed from: 0000 */
    public Call<DocumentUpload> upload(Applicant applicant, String str, DocType docType, String str2, byte[] bArr, Map<ValidationType, ValidationLevel> map, DocSide docSide, String str3, String str4) {
        return this.onfidoService.upload(applicant.getId(), this.documentRequestCreator.createMultipartRequestBody(str, docType, str2, bArr, map, docSide, str3, str4));
    }
}
