package com.example.assignment.boundedContext.member.in;

import com.example.assignment.boundedContext.member.app.MemberFacade;
import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.share.post.event.PostCommentCreatedEvent;
import com.example.assignment.share.post.event.PostCreatedEvent;
import jakarta.persistence.EntityListeners;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberEventListener {

    private final MemberFacade memberFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreatedEvent event){
        Member postAuthor = memberFacade.findById(event.getPostDto().getAuthorId()).getData();
        if(postAuthor == null) {
            RuntimeException e = new RuntimeException(
                    "Member not found. authorId=" + event.getPostDto().getAuthorId()
            );
            log.error("MemberEventListener 회원 조회 실패",e);
            return;
        }
        postAuthor.increaseActivityScore(3);

    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCommentCreatedEvent event){
        Member postCommentAuthor = memberFacade.findById(event.getPostCommentDto().getAuthorId()).getData();
        if(postCommentAuthor == null) {
            RuntimeException e = new RuntimeException(
                    "Member not found. authorId=" + event.getPostCommentDto().getAuthorId()
            );
            log.error("MemberEventListener 회원 조회 실패",e);
            return;
        }
        postCommentAuthor.increaseActivityScore(1);
    }
}
