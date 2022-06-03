package study.springboot.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
    public User post(@RequestBody User user,
                     @PathVariable("userId") Long userId,
                     @PathVariable("userName") String userName) {
        log.info("userId = {}, userName = {} ", userId, userName);
        log.info("client request = {}", user);
        return user;
    }
}
