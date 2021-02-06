package com.kimsk.springboot.web;

import com.kimsk.springboot.config.auth.dto.SessionUser;
import com.kimsk.springboot.service.PostsService;
import com.kimsk.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

//json 응답이 아니므로 Rest Controller 가 아닌 Controller 를 사용한다.
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    //글 등록 폼 페이지 전달
    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }

    //수정 폼 페이지 전달
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
