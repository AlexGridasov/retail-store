package com.gri.alex.orderentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Invoice {

    @JsonProperty("invoiceId")
    private String invoiceId;

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("price")
    private double price;

    @JsonProperty("message")
    private String message;

}
