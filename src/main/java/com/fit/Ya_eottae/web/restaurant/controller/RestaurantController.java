package com.fit.Ya_eottae.web.restaurant.controller;

import com.fit.Ya_eottae.domain.restaurant.Restaurant;
import com.fit.Ya_eottae.domain.restaurant.RestaurantDetails;
import com.fit.Ya_eottae.domain.restaurant.Restaurants;
import com.fit.Ya_eottae.domain.restaurant.region.AreaCode;
import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.repository.reviewrepository.ReviewRepository;
import com.fit.Ya_eottae.web.restaurant.service.RestaurantDetailService;
import com.fit.Ya_eottae.web.restaurant.service.RestaurantService;
import com.fit.Ya_eottae.web.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    @Value("${openApi.serviceKey}")
    private String serviceKey;

    @Value("${openApi.callBackUrl1}")
    private String callBackUrl;

    @Value("${openApi.callBackUrl2}")
    private String callBackUrl2;

    @Value("${openApi._type}")
    private String type;

    @ModelAttribute("areaCode")
    public List<AreaCode> areaCodes() {
        List<AreaCode> areaCodes = new ArrayList<>();
        areaCodes.add(new AreaCode("", "광역시"));
        areaCodes.add(new AreaCode("1", "서울"));
        areaCodes.add(new AreaCode("2", "인천"));
        areaCodes.add(new AreaCode("3", "대전"));
        areaCodes.add(new AreaCode("4", "대구"));
        areaCodes.add(new AreaCode("5", "광주"));
        areaCodes.add(new AreaCode("6", "부산"));
        areaCodes.add(new AreaCode("7", "울산"));
        areaCodes.add(new AreaCode("8", "세종"));
        areaCodes.add(new AreaCode("31", "경기도"));
        areaCodes.add(new AreaCode("32", "강원도"));
        areaCodes.add(new AreaCode("33", "충청북도")); // 북도
        areaCodes.add(new AreaCode("34", "충청남도")); // 남도
        areaCodes.add(new AreaCode("35", "경상북도")); // 북도
        areaCodes.add(new AreaCode("36", "경상남도"));
        areaCodes.add(new AreaCode("37", "전라북도")); // 남도
        areaCodes.add(new AreaCode("38", "전라남도")); // dd
        areaCodes.add(new AreaCode("39", "제주도"));
        return areaCodes;
    }

    private final RestaurantService restaurantService;
    private final RestaurantDetailService restaurantDetailService;
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @GetMapping("/jeollanam-do/restaurant")
    public String jeollanamdo(
            @RequestParam(value = "arr1", required = false) String arr1,
            @RequestParam(value = "arr2", required = false) String arr2,
            @RequestParam(value = "areacode", required = false) String areaCode,
            @RequestParam(value = "contentid", required = false) String contentId,
            @RequestParam(value = "contenttypeid", required = false) String contentTypeId,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "title", required = false) String title,
            Model model
    ) {
        String jsonResponse = restaurantService.callApiArea(
                arr1, arr2, "38", contentId, contentTypeId,
                firstImage, firstImage2, mapX, mapY, tel, title,
                serviceKey, callBackUrl, type
        );

        Restaurants restaurants = restaurantService.parsingJsonRestaurants(jsonResponse);
        List<Restaurant> restaurantList = restaurants.getRestaurants();

        List<Review> reviewList = reviewRepository.findByRestaurantId(contentId);
        int reviewCount = reviewList.size();

        log.info("aaa={}", restaurantList.getFirst());

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("restaurantList", restaurantList);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(contentId));

        return "restaurant/restaurants";
    }


    @GetMapping("/geongsangbookdo/restaurant")
    public String geongsangbookdo(
            @RequestParam(value = "arr1", required = false) String arr1,
            @RequestParam(value = "arr2", required = false) String arr2,
            @RequestParam(value = "areacode", required = false) String areaCode,
            @RequestParam(value = "contentid", required = false) String contentId,
            @RequestParam(value = "contenttypeid", required = false) String contentTypeId,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "title", required = false) String title,
            Model model
    ) {
        String jsonResponse = restaurantService.callApiArea(
                arr1, arr2, "35", contentId, contentTypeId,
                firstImage, firstImage2, mapX, mapY, tel, title,
                serviceKey, callBackUrl, type
        );

        Restaurants restaurants = restaurantService.parsingJsonRestaurants(jsonResponse);
        List<Restaurant> restaurantList = (areaCode != null) ? restaurants.getRestaurants().stream().filter(
                restaurant -> restaurant.getAreacode().equals(areaCode)).collect(Collectors.toList()) :
                restaurants.getRestaurants();

        List<Review> reviewList = reviewRepository.findByRestaurantId(contentId);
        int reviewCount = reviewList.size();

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("restaurantList", restaurantList);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(contentId));

        return "restaurant/restaurants";
    }

    @GetMapping("/chungcheongnamdo/restaurant")
    public String chungcheongnamdo(
            @RequestParam(value = "arr1", required = false) String arr1,
            @RequestParam(value = "arr2", required = false) String arr2,
            @RequestParam(value = "areacode", required = false) String areaCode,
            @RequestParam(value = "contentid", required = false) String contentId,
            @RequestParam(value = "contenttypeid", required = false) String contentTypeId,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "title", required = false) String title,
            Model model
    ) {
        String jsonResponse = restaurantService.callApiArea(
                arr1, arr2, "34", contentId, contentTypeId,
                firstImage, firstImage2, mapX, mapY, tel, title,
                serviceKey, callBackUrl, type
        );

        Restaurants restaurants = restaurantService.parsingJsonRestaurants(jsonResponse);
        List<Restaurant> restaurantList = (areaCode != null) ? restaurants.getRestaurants().stream().filter(
                restaurant -> restaurant.getAreacode().equals(areaCode)).collect(Collectors.toList()) :
                restaurants.getRestaurants();

        List<Review> reviewList = reviewRepository.findByRestaurantId(contentId);
        int reviewCount = reviewList.size();

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("restaurantList", restaurantList);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(contentId));

        return "restaurant/restaurants";
    }

    @GetMapping("/jeonlabookdo/restaurant")
    public String jeonlabookdo(
            @RequestParam(value = "arr1", required = false) String arr1,
            @RequestParam(value = "arr2", required = false) String arr2,
            @RequestParam(value = "areacode", required = false) String areaCode,
            @RequestParam(value = "contentid", required = false) String contentId,
            @RequestParam(value = "contenttypeid", required = false) String contentTypeId,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "title", required = false) String title,
            Model model
    ) {
        String jsonResponse = restaurantService.callApiArea(
                arr1, arr2, "37", contentId, contentTypeId,
                firstImage, firstImage2, mapX, mapY, tel, title,
                serviceKey, callBackUrl, type
        );

        Restaurants restaurants = restaurantService.parsingJsonRestaurants(jsonResponse);
        List<Restaurant> restaurantList = (areaCode != null) ? restaurants.getRestaurants().stream().filter(
                restaurant -> restaurant.getAreacode().equals(areaCode)).collect(Collectors.toList()) :
                restaurants.getRestaurants();

        List<Review> reviewList = reviewRepository.findByRestaurantId(contentId);
        int reviewCount = reviewList.size();

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("restaurantList", restaurantList);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(contentId));

        return "restaurant/restaurants";
    }

    @GetMapping("/chungcheongbookdo/restaurant")
    public String chungcheongbookdo(
            @RequestParam(value = "arr1", required = false) String arr1,
            @RequestParam(value = "arr2", required = false) String arr2,
            @RequestParam(value = "areacode", required = false) String areaCode,
            @RequestParam(value = "contentid", required = false) String contentId,
            @RequestParam(value = "contenttypeid", required = false) String contentTypeId,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "title", required = false) String title,
            Model model
    ) {
        String jsonResponse = restaurantService.callApiArea(
                arr1, arr2, "33", contentId, contentTypeId,
                firstImage, firstImage2, mapX, mapY, tel, title,
                serviceKey, callBackUrl, type
        );

        Restaurants restaurants = restaurantService.parsingJsonRestaurants(jsonResponse);
        List<Restaurant> restaurantList = (areaCode != null) ? restaurants.getRestaurants().stream().filter(
                restaurant -> restaurant.getAreacode().equals(areaCode)).collect(Collectors.toList()) :
                restaurants.getRestaurants();

        List<Review> reviewList = reviewRepository.findByRestaurantId(contentId);
        int reviewCount = reviewList.size();

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("restaurantList", restaurantList);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(contentId));

        return "restaurant/restaurants";
    }

    @GetMapping("/restaurants")
    public String restaurantList(
            @RequestParam(value = "addr1", required = false) String addr1,
            @RequestParam(value = "addr2", required = false) String addr2,
            @RequestParam(value = "areacode", required = false) String areaCode,
            @RequestParam(value = "contentid", required = false) String contentId,
            @RequestParam(value = "contenttypeid", required = false) String contentTypeId,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "50") int size,
            Model model
    ) {
        // 외부 API 호출
        String jsonResponse = restaurantService.callApi(
                addr1, addr2, areaCode, contentId, contentTypeId,
                firstImage, firstImage2, mapX, mapY, tel, title,
                serviceKey, callBackUrl, type
        );

        Restaurants restaurants = restaurantService.parsingJsonRestaurants(jsonResponse);
        List<Restaurant> restaurantList = restaurants.getRestaurants();

        List<Review> reviewList = reviewRepository.findByRestaurantId(contentId);
        int reviewCount = reviewList.size();

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(contentId));
        model.addAttribute("restaurantList", restaurantList);


        if (areaCode != null) {
            String json = restaurantService.callApiArea(addr1, addr2, areaCode, contentId, contentTypeId,
                    firstImage, firstImage2, mapX, mapY, tel, title,
                    serviceKey, callBackUrl, type);

            Restaurants areaRestaurant = restaurantService.parsingJsonRestaurants(json);
            List<Restaurant> areaRestaurantList = areaRestaurant.getRestaurants();
            model.addAttribute("restaurantList", areaRestaurantList);
        }

        return "restaurant/restaurants";
    }

    @GetMapping("/search")
    public String restaurantListSearch(
            @RequestParam(value = "addr1", required = false) String addr1,
            @RequestParam(value = "addr2", required = false) String addr2,
            @RequestParam(value = "areacode", required = false) String areaCode,
            @RequestParam(value = "contentid", required = false) String contentId,
            @RequestParam(value = "contenttypeid", required = false) String contentTypeId,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "title", required = false) String title,
            HttpServletRequest request,
            Model model
    ) {
        // 외부 API 호출
        String jsonResponse = restaurantService.callApi(
                addr1, addr2, areaCode, contentId, contentTypeId,
                firstImage, firstImage2, mapX, mapY, tel, title,
                serviceKey, callBackUrl, type
        );

        Restaurants restaurants = restaurantService.parsingJsonRestaurants(jsonResponse);
        List<Restaurant> restaurantList = restaurants.getRestaurants();

        HttpSession session = request.getSession();
        String searchWord = (String) session.getAttribute("searchWord");

        List<Restaurant> searchRestaurant = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getTitle().contains(searchWord)) {
                searchRestaurant.add(restaurant);
            }
        }

        log.info("size={}", searchRestaurant.size());

        List<Review> reviewList = reviewRepository.findByRestaurantId(contentId);
        int reviewCount = reviewList.size();

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("restaurantList", searchRestaurant);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(contentId));

        return "restaurant/search-restaurants";
    }

    @GetMapping("/{restaurantId}")
    public String restaurantDetail(
            @PathVariable("restaurantId") String restaurantId,
            @RequestParam(value = "mapx", required = false) String mapX,
            @RequestParam(value = "mapy", required = false) String mapY,
            @RequestParam(value = "firstimage", required = false) String firstImage,
            @RequestParam(value = "firstimage2", required = false) String firstImage2,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "addr1", required = false) String addr1,
            @RequestParam(value = "firstmenu", required = false) String firstMenu,
            @RequestParam(value = "infocenterfood", required = false) String infoCenterFood,
            @RequestParam(value = "opendatafood", required = false) String openDataFood,
            @RequestParam(value = "opentimefood", required = false) String openTimeFood,
            @RequestParam(value = "parkingfood", required = false) String parkingFood,
            @RequestParam(value = "reservationfood", required = false) String reservationFood,
            @RequestParam(value = "restdatefood", required = false) String restDateFood,
            @RequestParam(value = "treadfood", required = false) String treatFood,
            Model model
    ) {
        String json = restaurantDetailService.callApi(restaurantId, firstMenu, infoCenterFood, openDataFood, openTimeFood, parkingFood,
                reservationFood, restDateFood, treatFood, serviceKey, callBackUrl2, type);

        RestaurantDetails restaurantDetails = restaurantDetailService.parsingJsonRestaurantDetails(json);
        model.addAttribute("restaurant", restaurantDetails.getRestaurantDetails().getFirst());

        List<Review> reviewList = reviewRepository.findByRestaurantId(restaurantId);

        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("reviewPoint", reviewService.avgOneReviewPoint(restaurantId));
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("reviewListSize", reviewList.size());
        model.addAttribute("mapX", mapX);
        model.addAttribute("mapY", mapY);
        model.addAttribute("firstImage", firstImage);
        model.addAttribute("firstImage2", firstImage2);
        model.addAttribute("title", title);
        model.addAttribute("addr1", addr1);

        return "restaurant/restaurant-detail";
    }

}
