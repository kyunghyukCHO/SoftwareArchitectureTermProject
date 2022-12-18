package softwareArchitecture.termProject.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberStudy {

    @Id
    @GeneratedValue
    @Column(name = "memberStudy_id")
    private Long id;
    private boolean isOwner; // Boolean Type 이 아닌 Member 객체 참조 가능?

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    private LocalDateTime createDate; // 스터디 생성 시간

    private MemberStudyStatus memberStudyStatus;

    /*
    연관관계 매소드
     */
    public void setMember(Member member) {
        this.member = member;
        member.getMemberStudyList().add(this);
    }

    /*
    생성 매소드
     */
    public static MemberStudy createStudy(Member member, Study study) {
        MemberStudy memberStudy = new MemberStudy();
        memberStudy.setMember(member);
        memberStudy.setStudy(study);
        study.setStudyStatus(StudyStatus.RECRUITING);
        memberStudy.setOwner(true);
        memberStudy.setMemberStudyStatus(MemberStudyStatus.PARTICIPATED);
        memberStudy.setCreateDate(LocalDateTime.now());
        return memberStudy;
    }

    /*
    삭제 매소드
     */
    public void deleteStudy() {
        // 해당 memberStudy만 삭제 된 꼴인가???
        // study 의 status 를 바꿨으니 전체 다 조회가 된건가???
        this.study.setStudyStatus(StudyStatus.DELETED);
    }

}
