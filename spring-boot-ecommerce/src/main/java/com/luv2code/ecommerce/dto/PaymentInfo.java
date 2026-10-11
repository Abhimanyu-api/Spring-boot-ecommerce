package com.luv2code.ecommerce.dto;

import lombok.Data;

@Data
public class PaymentInfo {

    private int amount; //strip convert into lower so int can handel
    private String currency;
    private String receiptEmail;
}
