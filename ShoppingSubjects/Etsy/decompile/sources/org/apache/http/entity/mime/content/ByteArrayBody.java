package org.apache.http.entity.mime.content;

import com.etsy.android.lib.requests.HttpUtil;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.entity.mime.MIME;

public class ByteArrayBody extends AbstractContentBody {
    private final byte[] data;
    private final String filename;

    public String getCharset() {
        return null;
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public ByteArrayBody(byte[] bArr, String str, String str2) {
        super(str);
        if (bArr == null) {
            throw new IllegalArgumentException("byte[] may not be null");
        }
        this.data = bArr;
        this.filename = str2;
    }

    public ByteArrayBody(byte[] bArr, String str) {
        this(bArr, HttpUtil.DEFAULT_CONTENT_TYPE, str);
    }

    public String getFilename() {
        return this.filename;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.data);
    }

    public long getContentLength() {
        return (long) this.data.length;
    }
}
