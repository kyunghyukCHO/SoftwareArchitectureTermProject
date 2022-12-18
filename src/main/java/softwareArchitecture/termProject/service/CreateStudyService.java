package softwareArchitecture.termProject.service;

import softwareArchitecture.termProject.domain.MemberStudy;

public interface CreateStudyService {

    public Long createStudy(Long memberId, Long studyId);

    public void deleteStudy(Long studyId);

}

