package com.example.assignment.boundedContext.post.in;

import com.example.assignment.boundedContext.post.app.PostFacade;
import com.example.assignment.share.member.event.MemberJoinedEvent;
import com.example.assignment.share.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@RequiredArgsConstructor
@Component
public class PostEventListener {

    private final PostFacade postFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event){
        postFacade.syncMember(event.getMemberDto());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberModifiedEvent event){
        postFacade.syncMember(event.getMemberDto());
    }
}
