package com.waleed.task19.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotEmpty(message = "Area cannot be empty")
    private String area;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @NotEmpty(message = "Building number cannot be empty")
    private String buildingNumber;
}