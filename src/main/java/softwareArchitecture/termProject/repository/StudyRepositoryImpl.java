package softwareArchitecture.termProject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import softwareArchitecture.termProject.domain.Study;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepository{

    public final EntityManager em;


    @Override
    public void save(Study study) {
        em.persist(study);
    }

    @Override
    public Study findOne(Long studyId) {
        return em.find(Study.class, studyId);
    }

    @Override
    public List<Study> findAll() {
        return em.createQuery("select s from Study s where s.studyStatus = softwareArchitecture.termProject.domain.StudyStatus.RECRUITING", Study.class)
                .getResultList();
    }

    @Override
    public List<Study> findAllByString(StudySearch studySearch) {
        //language=JPAQL
        String jpql = "select s From Study s";
        boolean isFirstCondition = true;

        if (studySearch.getCategory() == null && studySearch.getStudyName() == null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " s.studyStatus = softwareArchitecture.termProject.domain.StudyStatus.RECRUITING";
        }

        // 스터디 카테고리 검색
        if (studySearch.getCategory() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " s.category = :category";
            jpql += " and s.studyStatus = softwareArchitecture.termProject.domain.StudyStatus.RECRUITING";
        }

        //스터디 이름 검색
        if (StringUtils.hasText(studySearch.getStudyName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " s.name like :name";
            jpql += " and s.studyStatus = softwareArchitecture.termProject.domain.StudyStatus.RECRUITING";
        }
        TypedQuery<Study> query = em.createQuery(jpql, Study.class)
                .setMaxResults(1000); //최대 1000건

        if (studySearch.getCategory() != null) {
            query = query.setParameter("category", studySearch.getCategory());
        }
        if (StringUtils.hasText(studySearch.getStudyName())) {
            query = query.setParameter("name", "%"+studySearch.getStudyName()+"%");
        }
        return query.getResultList();
    }
}
