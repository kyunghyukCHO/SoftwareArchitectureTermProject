package softwareArchitecture.termProject.service;

import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.domain.MemberStudy;
import softwareArchitecture.termProject.domain.Study;
import softwareArchitecture.termProject.repository.StudySearch;

import java.util.List;

public interface ExistingStudyService {

    Long participateStudy(Long memberId, Long studyId);

    void quitStudy(Long memberStudyId);

    List<Study> showAllStudies();

    List<MemberStudy> showMyStudies(Long memberId);

    MemberStudy findOne(Long memberStudyId);

    List<Member> findMembers(Long studyId);

    Study findStudy(Long studyId);

    // 검색
    List<Study> findStudies(StudySearch studySearch);

    Long findMemberStudy(Long memberId, Long studyId);

}
