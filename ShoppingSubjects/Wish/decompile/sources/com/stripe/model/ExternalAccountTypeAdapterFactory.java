package com.stripe.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class ExternalAccountTypeAdapterFactory implements TypeAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (!ExternalAccount.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        final TypeAdapter adapter = gson.getAdapter(JsonElement.class);
        final TypeAdapter delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(ExternalAccount.class));
        final TypeAdapter delegateAdapter2 = gson.getDelegateAdapter(this, TypeToken.get(AlipayAccount.class));
        final TypeAdapter delegateAdapter3 = gson.getDelegateAdapter(this, TypeToken.get(BankAccount.class));
        final TypeAdapter delegateAdapter4 = gson.getDelegateAdapter(this, TypeToken.get(BitcoinReceiver.class));
        final TypeAdapter delegateAdapter5 = gson.getDelegateAdapter(this, TypeToken.get(Card.class));
        AnonymousClass1 r0 = new TypeAdapter<ExternalAccount>() {
            public void write(JsonWriter jsonWriter, ExternalAccount externalAccount) throws IOException {
                delegateAdapter.write(jsonWriter, externalAccount);
            }

            public ExternalAccount read(JsonReader jsonReader) throws IOException {
                JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                String asString = asJsonObject.getAsJsonPrimitive("object").getAsString();
                if (asString.equals("alipay_account")) {
                    return (ExternalAccount) delegateAdapter2.fromJsonTree(asJsonObject);
                }
                if (asString.equals("bank_account")) {
                    return (ExternalAccount) delegateAdapter3.fromJsonTree(asJsonObject);
                }
                if (asString.equals("bitcoin_receiver")) {
                    return (ExternalAccount) delegateAdapter4.fromJsonTree(asJsonObject);
                }
                if (asString.equals("card")) {
                    return (ExternalAccount) delegateAdapter5.fromJsonTree(asJsonObject);
                }
                return (ExternalAccount) delegateAdapter.fromJsonTree(asJsonObject);
            }
        };
        return r0.nullSafe();
    }
}
