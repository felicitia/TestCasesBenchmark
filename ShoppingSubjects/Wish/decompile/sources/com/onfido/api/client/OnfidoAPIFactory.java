package com.onfido.api.client;

import com.onfido.api.client.common.TokenConstants;

public class OnfidoAPIFactory {
    public static OnfidoAPI create(String str, String str2, Fingerprint fingerprint) {
        if (str2 == null) {
            str2 = "https://api.onfido.com/v2/";
        }
        OnfidoService applicants = OnfidoFetcher.create(str, str2, fingerprint).applicants();
        ErrorParser newInstance = ErrorParserImpl.newInstance();
        OnfidoAPIImpl onfidoAPIImpl = new OnfidoAPIImpl(new UploadDocumentAPI(applicants, MultipartDocumentRequestCreator.newInstance()), new UploadLivePhotoAPI(applicants, MultipartLivePhotoRequestCreator.newInstance()), new UploadLiveVideoAPI(applicants, MultipartLiveVideoRequestCreator.newInstance()), new CheckAPI(applicants, MultipartCheckRequestCreator.newInstance()), new ApplicantAPI(applicants), newInstance);
        return onfidoAPIImpl;
    }

    public static OnfidoAPI create(String str) {
        return create(str, (Fingerprint) null);
    }

    public static OnfidoAPI create(String str, Fingerprint fingerprint) {
        if (str.equals(TokenConstants.ONFIDO_DEMO_TOKEN)) {
            return new OnfidoDemoAPIImpl();
        }
        String[] split = str.split(TokenConstants.ONFIDO_TOKEN_SEPARATOR);
        if (split[0].equals(TokenConstants.ONFIDO_LIVE_TOKEN_PREFIX) || split[0].equals(TokenConstants.ONFIDO_TEST_TOKEN_PREFIX)) {
            return create(str, null, fingerprint);
        }
        return create(str, String.format("https://api.%s.onfido.com/v2/", new Object[]{split[0]}), fingerprint);
    }

    public static OnfidoAPI create(String str, String str2) {
        return create(str, str2, null);
    }
}
