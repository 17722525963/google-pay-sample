package com.judopay.wallet;

import com.judopay.model.Receipt;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Single;

public interface PartnerApi {

    @POST("transactions/payments")
    Single<Receipt> googlePayTransaction(@Body GooglePayRequest requestBody);

}
