package study.springboot.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import study.springboot.server.dto.Request;
import study.springboot.server.dto.User;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

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
