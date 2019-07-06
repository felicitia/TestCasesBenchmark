package com.etsy.etsyapi;

import com.etsy.auto.value.jackson.ModelParser;
import com.etsy.auto.value.jackson.a;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/* compiled from: EtsyModelFactory */
public abstract class b implements a {
    public static b b() {
        return new a();
    }

    public static void a(ObjectMapper objectMapper) {
        b b = b();
        objectMapper.registerModule(b.a());
        ModelParser.a((a) b);
    }

    public static <T> T b(JsonParser jsonParser, Class cls) throws IOException {
        return ModelParser.a(jsonParser, cls);
    }
}
