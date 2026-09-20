package com.example.assignment.boundedContext.post.domain;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.global.jpa.entity.BaseIdAndTime;
import com.example.assignment.share.post.dto.PostCommentDto;
import com.example.assignment.share.post.event.PostCommentCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "POST_POST")
public class Post extends BaseIdAndTime {

    @ManyToOne
    private Member author;
    private String title;
    @Column( columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(
        mappedBy = "post",
        cascade = { CascadeType.REMOVE, CascadeType.PERSIST },
        orphanRemoval = true
    )
    private List<PostComment> comments;

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void addComment(Member author, String content) {
        PostComment comment = new PostComment(this, author, content);

        this.comments.add(
            comment
        );
        // 이벤트 발행
        super.publishEvent(
            new PostCommentCreatedEvent(
                new PostCommentDto(comment)
            )
        );
    }
}
