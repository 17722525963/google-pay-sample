package com.judopay.wallet;

public class GooglePayWalletDetailsRequest {

    private final String encryptedPayload;

    public GooglePayWalletDetailsRequest(String encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
    }

    public String getEncryptedPayload() { return encryptedPayload; }

}