package com.ost.ecommerce.stripe.controller.dto;

import com.google.gson.annotations.SerializedName;
import com.stripe.model.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    @SerializedName("card")
    Card card;
    @SerializedName("client_ip")
    String clientIp;
    @SerializedName("created")
    Long created;
    @SerializedName("email")
    String email;
    @SerializedName("id")
    String id;
    @SerializedName("livemode")
    Boolean livemode;
    @SerializedName("object")
    String object;
    @SerializedName("type")
    String type;
    @SerializedName("used")
    Boolean used;
}
