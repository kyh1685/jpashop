package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // !Test 클래스에 있으면! 기본적으로 Rollback을 해서 DB에 영향을 안 미침
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        //em.flush(); // 영속성 컨텍스트에 있던 쿼리가 DB로 나가지만 테스트가 끝나면 @Transactional가 롤백해줌
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        //memberService.join(member2); // 예외가 발생해야 한다!!

        // then
        // fail("예외가 발생해야 한다."); 이 라인까지 오면 실패
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // assertThrows(Class<> classType, Executable executable)
        // classType는 발생할 예외 클래스, executable은 실행할 코드
        // 예외가 발생할 경우 classType이 Exception과 같은 타입인지 체크
    }
}