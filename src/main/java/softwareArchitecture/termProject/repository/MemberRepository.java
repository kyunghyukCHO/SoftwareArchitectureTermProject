package softwareArchitecture.termProject.repository;

import softwareArchitecture.termProject.domain.Member;

import java.util.List;

public interface MemberRepository {

    public void save(Member member);
    public Member findOne(Long memberId);
    public List<Member> findByIdentity(String memberIdentity);

//    public Member findByIdAndPw(String identity, String password);
}
