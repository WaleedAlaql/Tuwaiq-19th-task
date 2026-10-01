package com.waleed.task19.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @NotEmpty(message = "Course name cannot be empty")
    private String name;
}