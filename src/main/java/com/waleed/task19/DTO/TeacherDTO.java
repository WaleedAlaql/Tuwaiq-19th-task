package com.waleed.task19.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 22, message = "Age must be at least 22")
    private Integer age;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    private Double salary;
}