package softwareArchitecture.termProject.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    @NotEmpty(message = "아이디는 필수 입니다")
    private String identity;

    @NotEmpty(message = "패스워드는 필수 입니다")
    private String password;

    private int age;
    private String email;

    private int frontNum;
    private int middleNum;
    private int lastNum;

    private String city;
    private String street;
    private String zipcode;
}
