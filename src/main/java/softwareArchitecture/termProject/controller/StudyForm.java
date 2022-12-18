package softwareArchitecture.termProject.controller;

import lombok.Getter;
import lombok.Setter;
import softwareArchitecture.termProject.domain.Category;
import softwareArchitecture.termProject.domain.StudyIntroduction;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Getter @Setter
public class StudyForm {

    @NotEmpty(message = "스터디 이름은 필수 입니다")
    private String name;

    @Min(value = 2, message = "스터디 인원은 최소 2명 이상입니다.")
    private int capacity;

    private String city;
    private String street;
    private String zipcode;

    private String category;

    private int startHour;
    private int startMinute;

    private int endHour;
    private int endMinute;

    private String introductionText;
}
