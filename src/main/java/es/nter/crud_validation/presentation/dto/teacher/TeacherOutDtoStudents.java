package es.nter.crud_validation.presentation.dto.teacher;


import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutDtoStudents {
    private Long id;
    private String comments;
    private String branch;

    private List<StudentOutDtoOnly> studentOutDtoOnlyList;
}
