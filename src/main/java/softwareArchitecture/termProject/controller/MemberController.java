package softwareArchitecture.termProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import softwareArchitecture.termProject.domain.Address;
import softwareArchitecture.termProject.domain.Member;
import softwareArchitecture.termProject.domain.MemberStudy;
import softwareArchitecture.termProject.domain.Phone;
import softwareArchitecture.termProject.service.ExistingStudyService;
import softwareArchitecture.termProject.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("member")
public class MemberController {

    private final MemberService memberService;
    private final ExistingStudyService existingStudyService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getZipcode(), memberForm.getStreet());
        Phone phone = new Phone(memberForm.getFrontNum(), memberForm.getMiddleNum(), memberForm.getLastNum());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setIdentity(memberForm.getIdentity());
        member.setPassword(memberForm.getPassword());
        member.setAge(memberForm.getAge());
        member.setEmail(memberForm.getEmail());
        member.setAddress(address);
        member.setPhone(phone);

        memberService.join(member);
        return "redirect:/studyList";
    }

    @GetMapping(value = "/members/{memberId}/myStudies")
    public String showMyStudies(@ModelAttribute("member") Member member,
                                Model model,
                                HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long memberId = (Long)session.getAttribute("memberId");

        List<MemberStudy> myStudies = existingStudyService.showMyStudies(memberId);

        model.addAttribute("member", member);
        model.addAttribute("myStudies", myStudies);
        return "memberDetail";
    }


}
