package softwareArchitecture.termProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import softwareArchitecture.termProject.domain.*;
import softwareArchitecture.termProject.repository.StudySearch;
import softwareArchitecture.termProject.service.CreateStudyService;
import softwareArchitecture.termProject.service.ExistingStudyService;
import softwareArchitecture.termProject.service.StudyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CreateStudyController {

    private final CreateStudyService createStudyService;
    private final ExistingStudyService existingStudyService;
    private final StudyService studyService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("studyForm", new StudyForm());
        return "study/createStudyForm";
    }

    @PostMapping("/new")
    public String create(@Valid StudyForm studyForm, BindingResult result,
                         HttpServletRequest request,
                         Model model) {

        if (result.hasErrors()) {
            return "study/createStudyForm";
        }

        Address address = new Address(studyForm.getCity(), studyForm.getStreet(), studyForm.getZipcode());

        LocalTime startTime = LocalTime.of(studyForm.getStartHour(), studyForm.getStartMinute());
        LocalTime endTime = LocalTime.of(studyForm.getEndHour(), studyForm.getEndMinute());

        StudyIntroduction studyIntroduction = new StudyIntroduction(studyForm.getIntroductionText());

        String category_name = studyForm.getCategory();
        Category category = null;
        if (category_name.equals("SPRING"))
            category = Category.SPRING;
        else if (category_name.equals("NETWORK"))
            category = Category.NETWORK;
        else if (category_name.equals("NODEJS"))
            category = Category.NODEJS;
        else if (category_name.equals("OS"))
            category = Category.OS;
        else if (category_name.equals("DATA_STRUCTURE"))
            category = Category.DATA_STRUCTURE;
        else if (category_name.equals("DATABASE"))
            category = Category.DATABASE;

        Study study = new Study();

        study.setCategory(category);

        study.setName(studyForm.getName());
        study.setCapacity(studyForm.getCapacity());
        study.setStartTime(startTime);
        study.setEndTime(endTime);
        study.setStudyStatus(StudyStatus.RECRUITING);
        study.setStudyIntroduction(studyIntroduction);
        study.setAddress(address);

        study.setPresent(1);

      ///////////  study.setCategory(studyForm.getCategory());

        studyService.saveStudy(study);
        Long studyId = study.getId();
        HttpSession session = request.getSession();
        Long memberId = (Long)session.getAttribute("memberId");


        Long memberStudyId = createStudyService.createStudy(memberId, studyId);
        MemberStudy memberStudy = existingStudyService.findOne(memberStudyId);
        memberStudy.setMemberStudyStatus(MemberStudyStatus.PARTICIPATED);

        return "redirect:/studyList";
    }

    @PostMapping(value = "/studies/{memberStudyId}/delete")
    public String deleteStudy(@PathVariable("memberStudyId") Long memberStudyId) {
        createStudyService.deleteStudy(memberStudyId);
        return "redirect:/studyList";
    }

}
