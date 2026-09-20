package com.example.assignment.boundedContext.post.in.dto;

import com.example.assignment.boundedContext.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostWriteResponseDto {
    private final long id;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;

    public PostWriteResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createDate = post.getCreateDate();
    }
}
