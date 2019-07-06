package com.contextlogic.wish.api;

import com.contextlogic.wish.util.FileUtil;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FileUploadApiRequest extends ApiRequest {
    private String mFileUri;

    public FileUploadApiRequest(String str, String str2) {
        setUrl(str);
        this.mFileUri = str2;
    }

    public Request buildRequest() {
        File file = new File(this.mFileUri);
        MediaType parse = MediaType.parse("text/plain");
        try {
            parse = MediaType.parse(FileUtil.getMimeType(this.mFileUri));
        } catch (Throwable unused) {
        }
        return this.mRequestBuilder.post(RequestBody.create(parse, file)).build();
    }
}
