package softwareArchitecture.termProject.repository;

import softwareArchitecture.termProject.domain.Category;
import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.domain.Study;

import java.util.List;

public interface StudyRepository {
    public void save(Study study);

    public Study findOne(Long studyId);

    public List<Study> findAll();

    List<Study> findAllByString(StudySearch studySearch);

}
