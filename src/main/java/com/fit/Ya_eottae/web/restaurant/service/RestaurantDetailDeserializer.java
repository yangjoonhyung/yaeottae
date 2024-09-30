package com.fit.Ya_eottae.web.restaurant.service;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit.Ya_eottae.domain.restaurant.RestaurantDetail;
import com.fit.Ya_eottae.domain.restaurant.RestaurantDetails;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RestaurantDetailDeserializer extends JsonDeserializer<RestaurantDetails> {

    private final ObjectMapper objectMapper;

    public RestaurantDetailDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public RestaurantDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = jsonNode.findValue("item");

        List<RestaurantDetail> restaurantDetails = Arrays.stream(objectMapper.treeToValue(itemNode, RestaurantDetail[].class)).toList();
        return new RestaurantDetails(restaurantDetails);
    }
}
