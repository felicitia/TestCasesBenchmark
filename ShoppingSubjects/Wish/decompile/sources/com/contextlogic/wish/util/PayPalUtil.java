package com.contextlogic.wish.util;

import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PostalAddress;
import com.contextlogic.wish.api.ApiRequest;

public class PayPalUtil {
    public static void addNonceParams(PayPalAccountNonce payPalAccountNonce, ApiRequest apiRequest) {
        String email = payPalAccountNonce.getEmail();
        String payerId = payPalAccountNonce.getPayerId();
        StringBuilder sb = new StringBuilder();
        sb.append(payPalAccountNonce.getFirstName());
        sb.append(" ");
        sb.append(payPalAccountNonce.getLastName());
        String sb2 = sb.toString();
        String phone = payPalAccountNonce.getPhone();
        PostalAddress billingAddress = payPalAccountNonce.getBillingAddress();
        String nonce = payPalAccountNonce.getNonce();
        if (nonce != null) {
            apiRequest.addParameter("payment_method_nonce", (Object) nonce);
        }
        if (email != null) {
            apiRequest.addParameter("payer_email", (Object) email);
        }
        if (payerId != null) {
            apiRequest.addParameter("payer_id", (Object) payerId);
        }
        if (billingAddress != null) {
            apiRequest.addParameter("full_name", (Object) sb2);
            apiRequest.addParameter("street_address1", (Object) billingAddress.getStreetAddress());
            apiRequest.addParameter("street_address2", (Object) billingAddress.getExtendedAddress());
            apiRequest.addParameter("city", (Object) billingAddress.getLocality());
            apiRequest.addParameter("state", (Object) billingAddress.getRegion());
            apiRequest.addParameter("zipcode", (Object) billingAddress.getPostalCode());
            apiRequest.addParameter("country", (Object) billingAddress.getCountryCodeAlpha2());
            apiRequest.addParameter("phone_number", (Object) phone);
        }
    }
}
