package com.waleed.task19.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherOutDTO {
    private String name;
    private Integer age;
    private String email;
    private Double salary;
    private String area;
}