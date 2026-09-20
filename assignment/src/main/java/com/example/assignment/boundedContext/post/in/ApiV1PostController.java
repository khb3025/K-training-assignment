package com.example.assignment.boundedContext.post.in;

import com.example.assignment.boundedContext.post.app.PostFacade;
import com.example.assignment.boundedContext.post.in.dto.PostWriteRequestDto;
import com.example.assignment.boundedContext.post.in.dto.PostWriteResponseDto;
import com.example.assignment.global.RsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class ApiV1PostController {

    private final PostFacade postFacade;

    @PostMapping("/write")
    private RsData<PostWriteResponseDto> postWrite(
            @RequestBody PostWriteRequestDto postWriteRequestDto
    ){
        return postFacade.write(
                postWriteRequestDto.getAuthorId(),
                postWriteRequestDto.getTitle(),
                postWriteRequestDto.getContent()
        );
    }
}
