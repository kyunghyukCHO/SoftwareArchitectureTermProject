package softwareArchitecture.termProject.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class StudyIntroduction {
    private String introduction; // TEXT 저장 형태 로직
    // 이미지 로직 추가되야 함..

    public StudyIntroduction() { }

    public StudyIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
