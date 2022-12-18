package softwareArchitecture.termProject.service;

import softwareArchitecture.termProject.domain.MemberStudy;
import softwareArchitecture.termProject.domain.Study;
import softwareArchitecture.termProject.repository.StudySearch;

import java.util.List;

public interface StudyService {

    public void saveStudy(Study study);

    public List<Study> findStudies();

    public Study findOne(Long studyId);

}
