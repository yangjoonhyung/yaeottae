package com.fit.Ya_eottae.web.restaurant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit.Ya_eottae.domain.restaurant.RestaurantDetail;
import com.fit.Ya_eottae.domain.restaurant.RestaurantDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Slf4j
@Service
public class RestaurantDetailService {

    public RestaurantDetails parsingJsonRestaurantDetails(String json) {
        RestaurantDetails result = new RestaurantDetails(new ArrayList<>());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RestaurantDetails restaurantDetails = objectMapper.readValue(json, RestaurantDetails.class);

            for (RestaurantDetail restaurantDetail : restaurantDetails.getRestaurantDetails()) {
                result.getRestaurantDetails().add(restaurantDetail);
            }

            for (RestaurantDetail restaurantDetail : result.getRestaurantDetails()) {
                decodeCategory(restaurantDetail); // 여기에서 decodeCategory 메서드를 호출
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(7000);
        urlConnection.setReadTimeout(7000);
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    private String readStreamToString(InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String readLine;
        while ((readLine = br.readLine()) != null) {
            result.append(readLine).append("\n\r");
        }

        br.close();
        return result.toString();
    }

    /* 외부 API에서 JSON 데이터를 호출 */
    public String callApi(String contentId, String firstMenu, String infoCenterFood, String openDateFood, String openTimeFood, String parkingFood
            , String reservationFood, String restDateFood, String treatMenu, String serviceKey, String callBackUrl, String dataType) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = callBackUrl +
                "?serviceKey=" + serviceKey +
                "&_type=" + dataType +
                "&contentId=" + contentId +
                "&contentTypeId=39" +
//                "&firstmenu=" + firstMenu +
//                "&infocenterfood=" + infoCenterFood +
//                "&opendatefood=" + openDateFood +
//                "&opentimefood=" + openTimeFood +
//                "&parkingfood=" + parkingFood +
//                "&reservationfood=" + reservationFood +
//                "&restdatefood=" + restDateFood +
//                "&treatmenu=" + treatMenu +
                "&MobileApp=AppTest" +
                "&MobileOS=ETC";

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);

            if (stream != null) stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            log.info("API URL={}", urlStr);
        }

        return result;
    }

    private RestaurantDetail decodeCategory(RestaurantDetail restaurantDetail) {

        String opentimefood = restaurantDetail.getOpentimefood();
        String[] strings = opentimefood.split("<br>");

        String name1 = strings[0];
        String name2 = strings[1];

        restaurantDetail.setOpentimefood1(name1);
        restaurantDetail.setOpentimefood2(name2);
        return restaurantDetail;
    }


}
