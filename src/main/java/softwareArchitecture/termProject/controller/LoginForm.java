package softwareArchitecture.termProject.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {
    @NotEmpty(message = "아이디는 필수 입니다")
    private String identity;

    @NotEmpty(message = "패스워드는 필수 입니다")
    private String password;
}
