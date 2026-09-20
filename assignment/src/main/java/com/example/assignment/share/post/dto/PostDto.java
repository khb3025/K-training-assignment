package com.example.assignment.share.post.dto;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDto {
    private final long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final long authorId;
    private final String title;
    private final String content;

    public PostDto(Post post){
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.authorId = post.getAuthor().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
