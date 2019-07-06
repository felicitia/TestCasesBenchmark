package com.stripe.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class StripeObject {
    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(EventData.class, new EventDataDeserializer()).create();

    public String toString() {
        return String.format("<%s@%s id=%s> JSON: %s", new Object[]{getClass().getName(), Integer.valueOf(System.identityHashCode(this)), getIdString(), PRETTY_PRINT_GSON.toJson((Object) this)});
    }

    private Object getIdString() {
        try {
            return getClass().getDeclaredField("id").get(this);
        } catch (SecurityException unused) {
            return "";
        } catch (NoSuchFieldException unused2) {
            return "";
        } catch (IllegalArgumentException unused3) {
            return "";
        } catch (IllegalAccessException unused4) {
            return "";
        }
    }
}
