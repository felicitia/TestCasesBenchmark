package com.stripe.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.stripe.net.APIResource;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EventDataDeserializer implements JsonDeserializer<EventData> {
    static final Map<String, Class> objectMap = new HashMap();

    static {
        objectMap.put("account", Account.class);
        objectMap.put("charge", Charge.class);
        objectMap.put("discount", Discount.class);
        objectMap.put("customer", Customer.class);
        objectMap.put("invoice", Invoice.class);
        objectMap.put("invoiceitem", InvoiceItem.class);
        objectMap.put("plan", Plan.class);
        objectMap.put("subscription", Subscription.class);
        objectMap.put("token", Token.class);
        objectMap.put("coupon", Coupon.class);
        objectMap.put("transfer", Transfer.class);
        objectMap.put("dispute", Dispute.class);
        objectMap.put("refund", Refund.class);
        objectMap.put("recipient", Recipient.class);
        objectMap.put("summary", Summary.class);
        objectMap.put("fee", Fee.class);
        objectMap.put("bank_account", BankAccount.class);
        objectMap.put("balance", Balance.class);
        objectMap.put("card", Card.class);
        objectMap.put("balance_transaction", BalanceTransaction.class);
    }

    private Object deserializeJsonPrimitive(JsonPrimitive jsonPrimitive) {
        if (jsonPrimitive.isBoolean()) {
            return Boolean.valueOf(jsonPrimitive.getAsBoolean());
        }
        if (jsonPrimitive.isNumber()) {
            return jsonPrimitive.getAsNumber();
        }
        return jsonPrimitive.getAsString();
    }

    private Object[] deserializeJsonArray(JsonArray jsonArray) {
        Object[] objArr = new Object[jsonArray.size()];
        Iterator it = jsonArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            objArr[i] = deserializeJsonElement((JsonElement) it.next());
            i = i2;
        }
        return objArr;
    }

    private Object deserializeJsonElement(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return null;
        }
        if (jsonElement.isJsonObject()) {
            HashMap hashMap = new HashMap();
            populateMapFromJSONObject(hashMap, jsonElement.getAsJsonObject());
            return hashMap;
        } else if (jsonElement.isJsonPrimitive()) {
            return deserializeJsonPrimitive(jsonElement.getAsJsonPrimitive());
        } else {
            if (jsonElement.isJsonArray()) {
                return deserializeJsonArray(jsonElement.getAsJsonArray());
            }
            PrintStream printStream = System.err;
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown JSON element type for element ");
            sb.append(jsonElement);
            sb.append(". ");
            sb.append("If you're seeing this messaage, it's probably a bug in the Stripe Java ");
            sb.append("library. Please contact us by email at support@stripe.com.");
            printStream.println(sb.toString());
            return null;
        }
    }

    private void populateMapFromJSONObject(Map<String, Object> map, JsonObject jsonObject) {
        for (Entry entry : jsonObject.entrySet()) {
            map.put((String) entry.getKey(), deserializeJsonElement((JsonElement) entry.getValue()));
        }
    }

    public EventData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        EventData eventData = new EventData();
        for (Entry entry : jsonElement.getAsJsonObject().entrySet()) {
            String str = (String) entry.getKey();
            JsonElement jsonElement2 = (JsonElement) entry.getValue();
            if ("previous_attributes".equals(str)) {
                HashMap hashMap = new HashMap();
                if (jsonElement2.getAsJsonObject() != null) {
                    populateMapFromJSONObject(hashMap, jsonElement2.getAsJsonObject());
                }
                eventData.setPreviousAttributes(hashMap);
            } else if ("object".equals(str)) {
                Class<StripeRawJsonObject> cls = (Class) objectMap.get(jsonElement2.getAsJsonObject().get("object").getAsString());
                Gson gson = APIResource.GSON;
                JsonElement jsonElement3 = (JsonElement) entry.getValue();
                if (cls == null) {
                    cls = StripeRawJsonObject.class;
                }
                eventData.setObject((StripeObject) gson.fromJson(jsonElement3, cls));
            }
        }
        return eventData;
    }
}
