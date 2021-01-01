package com.kimsk.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//json 응답이 아니므로 RestController가 아닌 Controller를 사용한다.
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
