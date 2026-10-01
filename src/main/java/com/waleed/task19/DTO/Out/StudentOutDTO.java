package com.waleed.task19.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutDTO {
    private String name;
    private Integer age;
    private String major;
}