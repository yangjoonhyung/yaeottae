package com.fit.Ya_eottae.domain.restaurant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class RestaurantDetails {

    @JsonProperty("item")
    private List<RestaurantDetail> restaurantDetails;

    @JsonCreator
    public RestaurantDetails(@JsonProperty("response")JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.restaurantDetails = Arrays.stream(objectMapper.treeToValue(itemNode, RestaurantDetail[].class)).toList();
    }

}
