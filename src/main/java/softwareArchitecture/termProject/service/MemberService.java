package softwareArchitecture.termProject.service;

import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.domain.MemberStudy;
import softwareArchitecture.termProject.domain.Study;

public interface MemberService {

    public Long join(Member member);

    public void checkDuplicatedMember(Member member);

    public Member login(String identity, String password);

    public Member findOne(Long memberId);

//    public MemberStudy findMyStudies(Study study);

}
