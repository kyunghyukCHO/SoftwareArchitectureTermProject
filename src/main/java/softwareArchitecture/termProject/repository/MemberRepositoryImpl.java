package softwareArchitecture.termProject.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import softwareArchitecture.termProject.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    public final EntityManager em;


    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    @Override
    public List<Member> findByIdentity(String memberIdentity) {
        return em.createQuery("select m from Member m where m.identity = :memberIdentity", Member.class)
                .setParameter("memberIdentity", memberIdentity)
                .getResultList();
    }

//    @Override
//    public Member findByIdAndPw(String identity, String password) {
//        return em.createQuery("select m from Member m where m.identity = :identity and m.password = :password", Member.class)
//                .setParameter("identity", identity)
//                .setParameter("password", password)
//                .getSingleResult();
//    }
}
