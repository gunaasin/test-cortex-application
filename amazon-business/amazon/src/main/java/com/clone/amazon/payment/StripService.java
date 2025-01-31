package com.clone.amazon.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public StripService(PaymentRepository paymentRepository,PaymentMapper paymentMapper){
        this.paymentRepository=paymentRepository;
        this.paymentMapper=paymentMapper;
    }

    @Value("${stripe.secretKey}")
    private String secretKey;

    @Value("${client.end-point}")
    private String CLI_END_POINT;


    public static Long convertDolor(Long inr) {
        return  inr * 100;

    }

    public PaymentResponse checkoutProduct(PaymentRequest paymentRequest){

        Stripe.apiKey=secretKey;
        var productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                            .setName(paymentRequest.name()).build();

        var priceData = SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("INR")
                                            .setUnitAmount(convertDolor(paymentRequest.amount()))
                                            .setProductData(productData)
                                            .build();

        var quantityData = SessionCreateParams.LineItem.builder()
                                            .setQuantity(paymentRequest.quantity())
                                            .setPriceData(priceData)
                                            .build();
        var param = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(CLI_END_POINT+"dummy")
                .setCancelUrl(CLI_END_POINT+"cart")
                .addLineItem(quantityData)
                .build();

        Session session = null;

        try {
            session = Session.create(param);

        } catch (StripeException e) {
           e.printStackTrace();
        }

        paymentRepository.save(paymentMapper.requestToPayment(paymentRequest));

        return PaymentResponse.builder()
                .status("Success")
                .message("Success")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }
}
