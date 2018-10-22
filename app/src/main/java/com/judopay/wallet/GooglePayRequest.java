package com.judopay.wallet;

import com.judopay.api.ParameterError;
import com.judopay.model.AndroidPayRequest;
import com.judopay.model.Currency;
import com.judopay.model.Wallet;

import java.util.Map;

import static com.judopay.arch.Preconditions.checkNotEmpty;
import static java.util.UUID.randomUUID;

public class GooglePayRequest extends BasePaymentRequest {

    private final GooglePayWalletDetailsRequest googlePayPayment;

    public GooglePayRequest(GooglePayWalletDetailsRequest googlePayPayment) {
        this.googlePayPayment = googlePayPayment;
    }

    public GooglePayWalletDetailsRequest getWallet() { return googlePayPayment; }

    public static class Builder {

        private String judoId;
        private String amount;
        private String currency;
        private String consumerReference;
        private Map<String, String> metaData;

        private String encryptedPayload;

        /**
         * @param encrypted the wallet information received from Android Pay containing the encrypted payload
         * @return The Builder for creating the {@link GooglePayRequest} instance
         */
        public GooglePayRequest.Builder setWallet(String encrypted) {
            this.encryptedPayload = encrypted;
            return this;
        }

        /**
         * @param amount the amount for the transaction
         * @return The Builder for creating the {@link GooglePayRequest} instance
         */
        public GooglePayRequest.Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        /**
         * @param currency A currency code string as defined in the list of supported currencies in {@link Currency}
         * @return The Builder for creating the {@link GooglePayRequest} instance
         */
        public GooglePayRequest.Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        /**
         * @param judoId the judo ID of the judo account
         * @return The Builder for creating the {@link GooglePayRequest} instance
         */
        public GooglePayRequest.Builder setJudoId(String judoId) {
            this.judoId = judoId;
            return this;
        }

        /**
         * @param consumerReference your reference to identify the transaction
         * @return The Builder for creating the {@link GooglePayRequest} instance
         */
        public GooglePayRequest.Builder setConsumerReference(String consumerReference) {
            this.consumerReference = consumerReference;
            return this;
        }

        /**
         * @param metaData meta data to be passed with the transaction that will be returned in the response
         * @return The Builder for creating the {@link GooglePayRequest} instance
         */
        public GooglePayRequest.Builder setMetaData(Map<String, String> metaData) {
            this.metaData = metaData;
            return this;
        }

        /**
         * creates the {@link GooglePayRequest} instance with the fields from the {@link GooglePayRequest.Builder}
         *
         * @return the built {@link GooglePayRequest} instance
         */
        public GooglePayRequest build() {
            checkNotNull(encryptedPayload);
            checkNotEmpty(amount);
            checkNotEmpty(judoId);
            checkNotEmpty(currency);
            checkNotEmpty(consumerReference);

            GooglePayWalletDetailsRequest details = new GooglePayWalletDetailsRequest(encryptedPayload);

            GooglePayRequest request = new GooglePayRequest(details);

            request.judoId = judoId;
            request.amount = amount;
            request.currency = currency;
            request.yourPaymentMetaData = metaData;
            request.yourConsumerReference = consumerReference;

            return request;
        }
    }

}

abstract class BasePaymentRequest extends Request {

    String amount;
    String currency;
    String judoId;
    String yourConsumerReference;
    Map<String, String> yourPaymentMetaData;

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getJudoId() {
        return judoId;
    }

    public String getYourConsumerReference() {
        return yourConsumerReference;
    }

    public Map<String, String> getMetaData() {
        return yourPaymentMetaData;
    }

}

