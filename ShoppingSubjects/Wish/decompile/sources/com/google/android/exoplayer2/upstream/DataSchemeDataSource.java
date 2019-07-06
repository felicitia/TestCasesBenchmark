package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.ParserException;
import java.io.IOException;
import java.net.URLDecoder;

public final class DataSchemeDataSource implements DataSource {
    private int bytesRead;
    private byte[] data;
    private DataSpec dataSpec;

    public long open(DataSpec dataSpec2) throws IOException {
        this.dataSpec = dataSpec2;
        Uri uri = dataSpec2.uri;
        String scheme = uri.getScheme();
        if (!"data".equals(scheme)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported scheme: ");
            sb.append(scheme);
            throw new ParserException(sb.toString());
        }
        String[] split = uri.getSchemeSpecificPart().split(",");
        if (split.length > 2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unexpected URI format: ");
            sb2.append(uri);
            throw new ParserException(sb2.toString());
        }
        String str = split[1];
        if (split[0].contains(";base64")) {
            try {
                this.data = Base64.decode(str, 0);
            } catch (IllegalArgumentException e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Error while parsing Base64 encoded string: ");
                sb3.append(str);
                throw new ParserException(sb3.toString(), e);
            }
        } else {
            this.data = URLDecoder.decode(str, "US-ASCII").getBytes();
        }
        return (long) this.data.length;
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int length = this.data.length - this.bytesRead;
        if (length == 0) {
            return -1;
        }
        int min = Math.min(i2, length);
        System.arraycopy(this.data, this.bytesRead, bArr, i, min);
        this.bytesRead += min;
        return min;
    }

    public Uri getUri() {
        if (this.dataSpec != null) {
            return this.dataSpec.uri;
        }
        return null;
    }

    public void close() throws IOException {
        this.dataSpec = null;
        this.data = null;
    }
}
