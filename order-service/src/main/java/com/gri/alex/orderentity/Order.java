package com.gri.alex.orderentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Order {

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("items")
    private List<LineItem> items;

    @JsonProperty("shippingAddress")
    private String shippingAddress;

}
