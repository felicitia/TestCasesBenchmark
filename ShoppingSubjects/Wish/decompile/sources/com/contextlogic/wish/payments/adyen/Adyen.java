package com.contextlogic.wish.payments.adyen;

import com.contextlogic.wish.api.datacenter.ConfigDataCenter;

public class Adyen {
    public static String encryptData(String str) throws EncrypterException {
        return new ClientSideEncrypter(ConfigDataCenter.getInstance().getPaymentProcessorData().getAdyenKey()).encrypt(str);
    }
}
