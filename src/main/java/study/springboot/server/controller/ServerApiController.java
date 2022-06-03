package study.springboot.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import study.springboot.server.dto.Request;
import study.springboot.server.dto.User;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    /**
     * https://openapi.naver.com
     * /v1/search/local.json
     * ?query=\xea\xb0\x88\xeb\xb9\x84\x0a&display=10&start=1&sort=random
     */
    @GetMapping("/naver")
    public String naver() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "중국집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();
        System.out.println("uri = " + uri);

        RestTemplate template = new RestTemplate();
        RequestEntity<Void> request = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "FJ7Ood2nGx2rvNB0AsQX")
                .header("X-Naver-Client-Secret", "MYsgp0gGQK")
                .build();

        ResponseEntity<String> response = template.exchange(request, String.class);
        return response.getBody();
    }

    @GetMapping(value = "/hello")
    public User hello(@RequestParam("name") String name, @RequestParam("age") int age) {
        User user = new User(name, age);
        return user;
    }

    @PostMapping(value = "/user/{userId}/name/{userName}")
    public Request<User> post(HttpEntity<String> entity,
                              @RequestBody Request<User> user,
                              @PathVariable("userId") Long userId,
                              @PathVariable("userName") String userName,
                              @RequestHeader("x-authorization") String authorization,
                              @RequestHeader("custom-header") String customHeader) {
        log.info("entity = {}", entity);
        log.info("userId = {}, userName = {} ", userId, userName);
        log.info("authorization = {}, customHeader = {} ", authorization, customHeader);
        log.info("client request = {}", user);

        Request<User> response = new Request<>();
        response.setHeader(new Request.Header());
        response.setResponseBody(user.getResponseBody());
        return response;
    }
}
