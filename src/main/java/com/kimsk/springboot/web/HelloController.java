package com.kimsk.springboot.web;

import com.kimsk.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public HelloResponseDto hello(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "amount", required = false, defaultValue = "0") int amount) {
        if (name != null) return new HelloResponseDto(name, amount);
        else return new HelloResponseDto("", 0);
    }
}
