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
public class Restaurants {

    @JsonProperty("item")
    private List<Restaurant> restaurants;

    @JsonCreator

    public Restaurants(@JsonProperty("response") JsonNode node) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode itemNode = node.findValue("item");
        this.restaurants = Arrays.stream(objectMapper.treeToValue(itemNode, Restaurant[].class)).toList();

    }
}
