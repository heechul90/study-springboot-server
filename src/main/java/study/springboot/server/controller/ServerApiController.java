package study.springboot.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.springboot.server.dto.User;

@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping(value = "/hello")
    public User hello(@RequestParam("name") String name, @RequestParam("age") int age) {
        User user = new User(name, age);
        return user;
    }
}
