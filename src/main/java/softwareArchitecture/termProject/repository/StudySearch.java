package softwareArchitecture.termProject.repository;

import lombok.Getter;
import lombok.Setter;
import softwareArchitecture.termProject.domain.Category;
import softwareArchitecture.termProject.domain.StudyCategory;

@Getter @Setter
public class StudySearch {

    private Category category;

    private String studyName;


}
