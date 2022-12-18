package softwareArchitecture.termProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.service.ExistingStudyService;
import softwareArchitecture.termProject.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//@SessionAttributes("member")
@Controller
@RequiredArgsConstructor
public class LogInController {

    private final MemberService memberService;
    private final ExistingStudyService existingStudyService;

    @ModelAttribute("member")
    public Member setUpMemberForm(){
        return new Member();
    }

    @PostMapping(value = "/login")
    public String login(Model model,
                        @ModelAttribute("loginForm") @Valid LoginForm loginForm,
                        BindingResult result,
                        HttpServletRequest request
    ) {

        if (result.hasErrors())
            return "redirect:/studyList";

        Member member = memberService.login(loginForm.getIdentity(), loginForm.getPassword());
        // Id, Password 일치하지 않을 경우
        if (member == null)
            return "redirect:/studyList";

        HttpSession session = request.getSession();
        session.setAttribute("member", member);
        session.setAttribute("memberId", member.getId());

        return "redirect:/studyList";

    }

    @GetMapping(value = "/logout")
    public String login(Model model,
                        HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);
        }
        session.invalidate();

        return "redirect:/studyList";
    }
}
