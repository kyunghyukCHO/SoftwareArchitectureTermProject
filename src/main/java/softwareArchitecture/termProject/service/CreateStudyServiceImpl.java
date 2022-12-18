package softwareArchitecture.termProject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softwareArchitecture.termProject.domain.*;
import softwareArchitecture.termProject.repository.MemberRepository;
import softwareArchitecture.termProject.repository.MemberStudyRepository;
import softwareArchitecture.termProject.repository.StudyRepository;

import java.util.List;

@Service
@Transactional
public class CreateStudyServiceImpl implements CreateStudyService {
    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final MemberStudyRepository memberStudyRepository;

    public CreateStudyServiceImpl(MemberRepository memberRepository, StudyRepository studyRepository, MemberStudyRepository memberStudyRepository) {
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
        this.memberStudyRepository = memberStudyRepository;
    }


    @Override
    public Long createStudy(Long memberId, Long studyId) {
        Member member = memberRepository.findOne(memberId);
        Study study = studyRepository.findOne(studyId);
        MemberStudy memberStudy = MemberStudy.createStudy(member, study);
        memberStudy.setMemberStudyStatus(MemberStudyStatus.PARTICIPATED);
        memberStudyRepository.save(memberStudy);
        return memberStudy.getId();
    }

    @Override
    public void deleteStudy(Long studyId) {
        List<MemberStudy> memberStudies = memberStudyRepository.findByStudyId(studyId);
        Study study = studyRepository.findOne(studyId);
        study.setStudyStatus(StudyStatus.DELETED);

        for (MemberStudy ms : memberStudies) {
            ms.setMemberStudyStatus(MemberStudyStatus.REJECTED);
        }
    }
}
