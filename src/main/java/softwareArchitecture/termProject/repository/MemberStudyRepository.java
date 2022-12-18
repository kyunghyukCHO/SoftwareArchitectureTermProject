package softwareArchitecture.termProject.repository;

import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.domain.MemberStudy;

import java.util.List;

public interface MemberStudyRepository {

    public void save(MemberStudy memberStudy);

    public MemberStudy findById(Long memberStudyId);

    public List<MemberStudy> findByMemberId(Long memberId);

    public List<MemberStudy> findByStudyId(Long studyId);

    public MemberStudy findByMemberIdStudyId(Long memberId, Long studyId);

    public List<Member> findMembers(Long studyId);


//    public MemberStudy findByMemberIdStudyId(Long memberId, Long StudyId);
//
//    public List<MemberStudy> findAllStudies();

}
