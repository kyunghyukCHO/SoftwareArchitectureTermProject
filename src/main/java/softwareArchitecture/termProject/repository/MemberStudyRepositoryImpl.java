package softwareArchitecture.termProject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.domain.MemberStudy;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberStudyRepositoryImpl implements MemberStudyRepository{

    public final EntityManager em;

    @Override
    public void save(MemberStudy memberStudy) {
        em.persist(memberStudy);
    }

    @Override
    public MemberStudy findById(Long memberStudyId) {
        return em.find(MemberStudy.class, memberStudyId);
    }

    @Override
    public List<MemberStudy> findByMemberId(Long memberId) {
        return em.createQuery("select ms from MemberStudy ms where ms.member.id = :memberId and ms.memberStudyStatus = softwareArchitecture.termProject.domain.MemberStudyStatus.PARTICIPATED", MemberStudy.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<MemberStudy> findByStudyId(Long studyId) {
        return em.createQuery("select ms from MemberStudy ms where ms.study.id = :studyId", MemberStudy.class)
                .setParameter("studyId", studyId)
                .getResultList();
    }

    @Override
    public MemberStudy findByMemberIdStudyId(Long memberId, Long studyId) {
        return em.createQuery("select ms from MemberStudy ms where ms.study.id = :studyId and ms.member.id = :memberId", MemberStudy.class)
                .setParameter("studyId", studyId)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }

    @Override
    public List<Member> findMembers(Long studyId) {
        return em.createQuery("select ms.member from MemberStudy ms where ms.study.id = :studyId and ms.memberStudyStatus = softwareArchitecture.termProject.domain.MemberStudyStatus.PARTICIPATED", Member.class)
                .setParameter("studyId", studyId)
                .getResultList();
    }

//    @Override
//    public MemberStudy findByMemberIdStudyId(Long memberId, Long studyId) {
//        return em.createQuery("select ms from MemberStudy ms where ms.member.id = :memberId and ms.study.id = :studyId", MemberStudy.class)
//                .setParameter("memberId", memberId)
//                .setParameter("studyId", studyId)
//                .getSingleResult();
//    }
//
//    @Override
//    public List<MemberStudy> findAllStudies() {
//        return em.createQuery("select ms from MemberStudy ms where ms.isOwner = true", MemberStudy.class)
//                .getResultList();
//    }


}
