package com.gri.alex.orderentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LineItem {

    @JsonProperty("itemCode")
    private String itemCode;

    @JsonProperty("quantity")
    private int quantity;

}
