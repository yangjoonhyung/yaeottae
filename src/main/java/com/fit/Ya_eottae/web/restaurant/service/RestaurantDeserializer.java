package com.fit.Ya_eottae.web.restaurant.service;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit.Ya_eottae.domain.restaurant.Restaurant;
import com.fit.Ya_eottae.domain.restaurant.Restaurants;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RestaurantDeserializer extends JsonDeserializer<Restaurants> {

    private final ObjectMapper objectMapper;

    public RestaurantDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Restaurants deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = jsonNode.findValue("item");

        List<Restaurant> restaurants = Arrays.stream(objectMapper.treeToValue(itemNode, Restaurant[].class)).toList();

        return new Restaurants(restaurants);
    }
}
