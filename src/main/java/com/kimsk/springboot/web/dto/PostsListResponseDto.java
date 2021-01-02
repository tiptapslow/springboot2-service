package com.kimsk.springboot.web.dto;

import com.kimsk.springboot.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostsListResponseDto {
    private final Long id;
    private final String title;
    private final LocalDateTime modifiedDate;
    private final String author;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
        this.author = entity.getAuthor();
    }
}
