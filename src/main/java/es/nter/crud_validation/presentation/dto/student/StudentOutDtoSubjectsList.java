package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.domain.models.Branch;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoOnly;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutDtoSubjectsList {
    private Long id;
    private int numHoursWeek;
    private String comments;
    private Branch branch;

    private List<SubjectOutDtoOnly> subjectOutDtoOnlyList;


}
