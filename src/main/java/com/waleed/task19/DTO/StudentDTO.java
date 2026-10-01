package com.waleed.task19.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @NotEmpty(message = "Student name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 10, message = "Age must be at least 10")
    private Integer age;

    @NotEmpty(message = "Major cannot be empty")
    private String major;
}