package com.example.assignment.share.post.event;

import com.example.assignment.share.post.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreatedEvent {
    private final PostDto postDto;
}
