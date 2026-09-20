package com.example.assignment.share.post.dto;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.post.domain.Post;
import com.example.assignment.boundedContext.post.domain.PostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostCommentDto {
    private final long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String content;
    private final long authorId;
    private final long postId;

    public PostCommentDto(
        PostComment postComment
    ){
        this.id = postComment.getId();
        this.createDate = postComment.getCreateDate();
        this.modifyDate = postComment.getModifyDate();
        this.content = postComment.getContent();
        this.authorId = postComment.getAuthor().getId();
        this.postId = postComment.getPost().getId();
    }
}
