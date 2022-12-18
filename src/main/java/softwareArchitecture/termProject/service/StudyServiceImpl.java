package softwareArchitecture.termProject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softwareArchitecture.termProject.domain.Study;
import softwareArchitecture.termProject.repository.StudyRepository;
import softwareArchitecture.termProject.repository.StudySearch;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;

    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Transactional
    @Override
    public void saveStudy(Study study) {
        studyRepository.save(study);
    }

    @Override
    public List<Study> findStudies() {
        return studyRepository.findAll();
    }

    @Override
    public Study findOne(Long studyId) {
        return studyRepository.findOne(studyId);
    }

}
