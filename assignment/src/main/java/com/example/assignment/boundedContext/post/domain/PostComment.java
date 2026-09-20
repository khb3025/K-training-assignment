package com.example.assignment.boundedContext.post.domain;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "POST_POST_COMMENT")
public class PostComment extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    @Column(columnDefinition = "TEXT")
    private String content;

    public PostComment(
            Post post,
            Member author,
            String content
    ) {
        this.post = post;
        this.author = author;
        this.content = content;
    }


}