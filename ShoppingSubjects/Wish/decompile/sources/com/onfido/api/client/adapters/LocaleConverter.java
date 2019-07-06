package com.onfido.api.client.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleConverter implements JsonDeserializer<Locale>, JsonSerializer<Locale> {
    private static final Map<String, Locale> LOCALE_MAP;

    static {
        String[] iSOCountries = Locale.getISOCountries();
        LOCALE_MAP = new HashMap(iSOCountries.length);
        for (String locale : iSOCountries) {
            Locale locale2 = new Locale("", locale);
            LOCALE_MAP.put(locale2.getISO3Country().toUpperCase(), locale2);
        }
    }

    private static String iso3CountryCodeToIso2CountryCode(String str) {
        if (!LOCALE_MAP.containsKey(str)) {
            return null;
        }
        return ((Locale) LOCALE_MAP.get(str)).getCountry();
    }

    public Locale deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String asString = jsonElement.getAsString();
        if (asString == null) {
            return null;
        }
        return new Locale("", iso3CountryCodeToIso2CountryCode(asString.toUpperCase()));
    }

    public JsonElement serialize(Locale locale, Type type, JsonSerializationContext jsonSerializationContext) {
        if (locale == null) {
            return null;
        }
        return new JsonPrimitive(locale.getISO3Country().toUpperCase());
    }
}
