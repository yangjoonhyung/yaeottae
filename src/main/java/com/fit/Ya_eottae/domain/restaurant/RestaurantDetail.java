package com.fit.Ya_eottae.domain.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantDetail {

    @JsonProperty("firstmenu")
    private String firstmenu;
    @JsonProperty("infocenterfood")
    private String infocenterfood;
    @JsonProperty("opendatefood")
    private String opendatefood;
    private String opentimefood1;
    @JsonProperty("opentimefood")
    private String opentimefood;
    private String opentimefood2;
    @JsonProperty("parkingfood")
    private String parkingfood;
    @JsonProperty("reservationfood")
    private String reservationfood;
    @JsonProperty("restdatefood")
    private String restdatefood;
    @JsonProperty("treatmenu")
    private String treatmenu;

}
