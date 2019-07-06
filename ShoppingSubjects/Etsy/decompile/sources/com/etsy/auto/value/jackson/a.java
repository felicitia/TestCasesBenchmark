package com.etsy.auto.value.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.Module;
import java.io.IOException;

/* compiled from: JacksonModelFactory */
public interface a {
    Module a();

    <T> T a(JsonParser jsonParser, Class<T> cls) throws IOException;
}
