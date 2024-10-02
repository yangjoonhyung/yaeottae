package com.fit.Ya_eottae.web.restaurant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit.Ya_eottae.domain.restaurant.Restaurant;
import com.fit.Ya_eottae.domain.restaurant.RestaurantDetails;
import com.fit.Ya_eottae.domain.restaurant.Restaurants;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
public class RestaurantService {

    public Restaurants parsingJsonRestaurants(String json) {
        Restaurants result = new Restaurants(new ArrayList<>());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Restaurants restaurants = objectMapper.readValue(json, Restaurants.class);

            for (Restaurant restaurant : restaurants.getRestaurants()) {
                result.getRestaurants().add(restaurant);
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

        // HTTP 응답 코드 확인
        int responseCode = urlConnection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + responseCode);
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
    public String callApi(String arr1, String arr2, String areaCode, String contentId, String contentTypeId,  String firstImage, String firstImage2,
                          String mapX, String mapY, String tel, String title, String serviceKey, String callBackUrl, String dataType) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = callBackUrl +
                "?serviceKey=" + serviceKey +
                "&_type=" + dataType +
                "&numOfRows=10000" +
                "&arrange=C" +
                "&contentTypeId=39" +
//                "&arr1=" + arr1 +
//                "&arr2=" + arr2 +
                "&areaCode=" + areaCode +
//                "contentid=" + contentId +
//                "&contenttypeid=" + contentTypeId +
//                "&firstImage=" + firstImage +
//                "&firstImage2=" + firstImage2 +
//                "&mapx=" + mapX +
//                "&mapy=" + mapY +
//                "&tel=" + tel +
//                "&title=" + title +
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
        }

        return result;
    }

    /* 외부 API에서 JSON 데이터를 호출 */
    public String callApiArea(String arr1, String arr2, String areaCode, String contentId, String contentTypeId,  String firstImage, String firstImage2,
                          String mapX, String mapY, String tel, String title, String serviceKey, String callBackUrl, String dataType) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = callBackUrl +
                "?serviceKey=" + serviceKey +
                "&_type=" + dataType +
                "&numOfRows=100" +
                "&arrange=C" +
                "&contentTypeId=39" +
//                "&arr1=" + arr1 +
//                "&arr2=" + arr2 +
                "&areaCode=" + areaCode +
//                "contentid=" + contentId +
//                "&contenttypeid=" + contentTypeId +
//                "&firstImage=" + firstImage +
//                "&firstImage2=" + firstImage2 +
//                "&mapx=" + mapX +
//                "&mapy=" + mapY +
//                "&tel=" + tel +
//                "&title=" + title +
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
        }

        return result;
    }

}
