package com.example.assignment.share.post.event;

import com.example.assignment.share.post.dto.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostCommentCreatedEvent {
    private final PostCommentDto postCommentDto;
}
