package com.apex.swimtime.constants;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SwimTimeRequest {
    private Integer swimmerID;

    private Integer timeID;

    private LocalDate date;

    private Integer distance;

    private StrokeEnum stroke;

    private Double time;

    private Split splits;
}
