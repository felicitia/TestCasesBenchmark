package com.onfido.android.sdk.capture.utils;

import android.content.res.Resources;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import kotlin.jvm.internal.Intrinsics;

public final class JSONResourceReader {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final String b = "JSONResourceReader";
    private final String a;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String a() {
            return JSONResourceReader.b;
        }
    }

    public JSONResourceReader(Resources resources, int i) {
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        InputStream openRawResource = resources.openRawResource(i);
        StringWriter stringWriter = new StringWriter();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringWriter.write(readLine);
                } else {
                    try {
                        break;
                    } catch (Exception e) {
                        Log.e(Companion.a(), "Unhandled exception while using JSONResourceReader", e);
                    }
                }
            }
        } catch (Exception e2) {
            Log.e(Companion.a(), "Unhandled exception while using JSONResourceReader", e2);
        } finally {
            try {
                openRawResource.close();
            } catch (Exception e3) {
                Log.e(Companion.a(), "Unhandled exception while using JSONResourceReader", e3);
            }
        }
        String stringWriter2 = stringWriter.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringWriter2, "writer.toString()");
        this.a = stringWriter2;
    }

    public final String getJsonString() {
        return this.a;
    }
}
