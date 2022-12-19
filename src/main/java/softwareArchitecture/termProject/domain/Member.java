package softwareArchitecture.termProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String identity;
    private String password;
    private String name;
    private int age;
    private String email;

    @Embedded
    private Phone phone;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberStudy> memberStudyList = new ArrayList<>();
}
