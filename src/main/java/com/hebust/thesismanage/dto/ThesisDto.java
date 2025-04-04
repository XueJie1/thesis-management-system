package com.hebust.thesismanage.dto;

import com.hebust.thesismanage.entity.Thesis;
import lombok.Data;

@Data
public class ThesisDto extends Thesis {
    private String studentName;
    private String teacherName;
    private String departmentName;
    private String majorName;
}
