package softwareArchitecture.termProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Long join(Member member) {
        checkDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Member login(String identity, String password) {
        List<Member> members = memberRepository.findByIdentity(identity);
        if (members.size() != 1)
            return null;

        Member member = members.get(0);
        if (!member.getPassword().equals(password))
            return null;

        return member;

    }

    @Override
    public void checkDuplicatedMember(Member member) {
        List<Member> findMembers = memberRepository.findByIdentity(member.getIdentity());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    @Override
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
