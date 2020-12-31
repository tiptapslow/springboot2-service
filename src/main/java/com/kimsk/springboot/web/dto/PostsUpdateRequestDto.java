package com.kimsk.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


//requestDto에는 NoArgsConstructor가 필요하다 request들어왔을 때 만들어줘야하기때문
@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    //글 작성자와 id 등은 수정할 수 없다.
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
