package softwareArchitecture.termProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Study {

    @Id
    @GeneratedValue
    @Column(name = "stduy_id")
    private Long id;
    private String name;
    private int capacity;
    private int present;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private StudyStatus studyStatus;

    @Embedded
    private Address address;

    @Embedded
    private StudyIntroduction studyIntroduction;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<MemberStudy> memberStudyList = new ArrayList<>();

}
