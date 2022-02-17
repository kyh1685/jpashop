package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // JPA의 모든 데이터 변경, 로직들은 트랜잭션 내에서 실행돼야 함. 스프링이 제공하는 거 사용하기
@RequiredArgsConstructor // final 붙은 필드로만 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository;

    /*@Autowired // 생성자가 하나면 @Autowired 생략 가능
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 최적화하려면 숫자를 가져오는게 낫다고 함
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOnt(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
