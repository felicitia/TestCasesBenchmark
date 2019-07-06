package com.contextlogic.wish.analytics;

import com.contextlogic.wish.api.service.standalone.CommerceLogService;
import java.util.HashMap;

public class CommerceLogger {

    public enum Action {
        UPDATE_STRIPE_BILLING_INFO(1),
        UPDATE_BRAINTREE_BILLING_INFO(2),
        NATIVE_SAVE_SHIPPING_INFO(3),
        NATIVE_SAVE_BILLING_INFO(4),
        NATIVE_SAVE_TABBED_BILLING_INFO(5),
        UPDATE_SHIPPING_INFO(6),
        UPDATE_BOLETO_BILLING_INFO(7),
        UPDATE_FAMILY_BILLING_INFO(23),
        NATIVE_GET_BRAINTREE_CLIENT_TOKEN(31),
        UPDATE_OXXO_BILLING_INFO(34),
        NATIVE_EBANX_SET_CVV(52);
        
        private int value;

        private Action(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum Result {
        SUCCESS(1),
        DECLINE_CVV(2),
        DECLINE_ZIP(3),
        DECLINE_GENERAL(4),
        INVALID_ZIP(5),
        LONG_ZIP(6),
        SHIPPING_MISSING(7),
        PROCESSING_ERROR(8),
        DECLINE_FRAUD(9),
        CUSTOMER_ID_TAKEN(10),
        MISSING_FIELDS(11),
        INVALID_FIELD_DATA(12),
        API_ERROR(13),
        STRIPE_SDK_ERROR(14),
        BALANCED_SDK_ERROR(44),
        EBANX_SDK_ERROR(45),
        ADYEN_SDK_ERROR(46),
        BRAINTREE_SDK_ERROR(55);
        
        private int value;

        private Result(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static void logSuccess(Action action, Result result, HashMap<String, String> hashMap) {
        logAction(true, action, result, hashMap);
    }

    public static void logError(Action action, Result result, HashMap<String, String> hashMap) {
        logAction(false, action, result, hashMap);
    }

    private static void logAction(boolean z, Action action, Result result, HashMap<String, String> hashMap) {
        new CommerceLogService().requestService(z, action.getValue(), result.getValue(), hashMap, null, null);
    }
}
