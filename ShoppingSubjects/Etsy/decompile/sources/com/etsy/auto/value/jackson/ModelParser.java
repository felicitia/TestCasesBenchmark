package com.etsy.auto.value.jackson;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import org.apache.commons.lang3.StringEscapeUtils;

public class ModelParser {
    private static ModelParser a;
    private a b;

    private static class UnsupportedFormatException extends IOException {
        public UnsupportedFormatException(String str) {
            super(str);
        }
    }

    public static void a(a aVar) {
        a = new ModelParser(aVar);
    }

    private ModelParser(a aVar) {
        this.b = aVar;
    }

    public static String a(JsonParser jsonParser) throws IOException {
        return StringEscapeUtils.unescapeHtml4(jsonParser.getValueAsString());
    }

    public static <T> T a(JsonParser jsonParser, Class<T> cls) throws IOException {
        if (cls == null) {
            return null;
        }
        T a2 = a.b.a(jsonParser, cls);
        if (a2 != null) {
            return a2;
        }
        return jsonParser.getCodec().readValue(jsonParser, cls);
    }
}
