package org.scribe.e;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: StreamUtils */
public class d {
    public static String a(InputStream inputStream) {
        int read;
        c.a((Object) inputStream, "Cannot get String from a null object");
        try {
            char[] cArr = new char[65536];
            StringBuilder sb = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            do {
                read = inputStreamReader.read(cArr, 0, cArr.length);
                if (read > 0) {
                    sb.append(cArr, 0, read);
                    continue;
                }
            } while (read >= 0);
            inputStreamReader.close();
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException("Error while reading response body", e);
        }
    }
}
