package com.stripe.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;

public class DisputeDataDeserializer implements JsonDeserializer<Dispute> {
    public Dispute deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        EvidenceSubObject evidenceSubObject;
        Gson create = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        String str = null;
        if (jsonElement.isJsonNull()) {
            return null;
        }
        if (!jsonElement.isJsonObject()) {
            throw new JsonParseException("Dispute type was not an object, which is problematic.");
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("evidence");
        if (jsonElement2.isJsonPrimitive()) {
            JsonPrimitive asJsonPrimitive = jsonElement2.getAsJsonPrimitive();
            if (!asJsonPrimitive.isString()) {
                throw new JsonParseException("Evidence field on a dispute was a primitive non-string type.");
            }
            String asString = asJsonPrimitive.getAsString();
            evidenceSubObject = null;
            str = asString;
        } else if (jsonElement2.isJsonObject()) {
            evidenceSubObject = (EvidenceSubObject) create.fromJson((JsonElement) jsonElement2.getAsJsonObject(), EvidenceSubObject.class);
        } else if (!jsonElement2.isJsonNull()) {
            throw new JsonParseException("Evidence field on a dispute was a non-primitive, non-object type.");
        } else {
            evidenceSubObject = null;
        }
        asJsonObject.remove("evidence");
        Dispute dispute = (Dispute) create.fromJson(jsonElement, type);
        dispute.setEvidence(str);
        dispute.setEvidenceSubObject(evidenceSubObject);
        return dispute;
    }
}
