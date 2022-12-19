package com.apex.swimtime.model;

import com.apex.swimtime.constants.StrokeEnum;
import com.apex.swimtime.model.Split;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SwimTimeRequest {

    private Integer swimmerID;

    private Integer timeID;

    @NotBlank(message = "Date is required")
    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Distance is required")
    @NotNull(message = "Distance is required")
    private Integer distance;

    @NotBlank(message = "Stroke is required")
    @NotNull(message = "Stroke is required")
    private StrokeEnum stroke;

    @NotBlank(message = "Time is required")
    @NotNull(message = "Time is required")
    private Double time;

    private Split splits;
}
