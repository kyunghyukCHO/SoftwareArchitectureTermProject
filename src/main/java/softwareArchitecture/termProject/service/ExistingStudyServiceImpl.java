package softwareArchitecture.termProject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.domain.MemberStudy;
import softwareArchitecture.termProject.domain.MemberStudyStatus;
import softwareArchitecture.termProject.domain.Study;
import softwareArchitecture.termProject.repository.MemberRepository;
import softwareArchitecture.termProject.repository.MemberStudyRepository;
import softwareArchitecture.termProject.repository.StudyRepository;
import softwareArchitecture.termProject.repository.StudySearch;

import java.util.List;

@Service
@Transactional
public class ExistingStudyServiceImpl implements ExistingStudyService{

    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final MemberStudyRepository memberStudyRepository;

    public ExistingStudyServiceImpl(MemberRepository memberRepository, StudyRepository studyRepository, MemberStudyRepository memberStudyRepository) {
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
        this.memberStudyRepository = memberStudyRepository;
    }

    @Override
    public Long participateStudy(Long memberId, Long studyId) {
        Member member = memberRepository.findOne(memberId);
        Study study = studyRepository.findOne(studyId);
        int present = study.getPresent();
        study.setPresent(present + 1);

        MemberStudy memberStudy = MemberStudy.createStudy(member, study);
        memberStudy.setOwner(false);
        memberStudy.setMemberStudyStatus(MemberStudyStatus.PARTICIPATED);

        memberStudyRepository.save(memberStudy);

        return memberStudy.getId();
    }

    @Override
    public void quitStudy(Long memberStudyId, Long memberId) {
        MemberStudy memberStudy = memberStudyRepository.findById(memberStudyId);
        memberStudy.setMemberStudyStatus(MemberStudyStatus.REJECTED);
    }

    @Override
    public List<Study> showAllStudies() {
        List<Study> allStudies = studyRepository.findAll();
        return allStudies;
    }

    @Override
    public List<MemberStudy> showMyStudies(Long memberId) {
        List<MemberStudy> myStudies = memberStudyRepository.findByMemberId(memberId);
        return myStudies;
    }

    @Override
    public MemberStudy findOne(Long memberStudyId) {
        MemberStudy memberStudy = memberStudyRepository.findById(memberStudyId);
        return memberStudy;
    }

    @Override
    public List<Member> findMembers(Long studyId) {
        List<Member> members = memberStudyRepository.findMembers(studyId);
        return members;
    }

    @Override
    public Study findStudy(Long studyId) {
        Study study = studyRepository.findOne(studyId);
        return study;
    }

    @Override
    public List<Study> findStudies(StudySearch studySearch) {
        return studyRepository.findAllByString(studySearch);
    }

    @Override
    public Long findMemberStudy(Long memberId, Long studyId) {
        MemberStudy memberStudy = memberStudyRepository.findByMemberIdStudyId(memberId, studyId);
        return memberStudy.getId();
    }
}
