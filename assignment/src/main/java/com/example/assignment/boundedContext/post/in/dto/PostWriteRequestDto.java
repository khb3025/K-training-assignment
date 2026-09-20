package com.example.assignment.boundedContext.post.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostWriteRequestDto {
    private final String title;
    private final String content;
    private final long authorId;
}
