package com.apex.swimtime.constants;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SwimTimeResponse {
    private Integer swimmerID;

    private Integer timeID;

    private LocalDate date;

    private Integer distance;

    private StrokeEnum stroke;

    private Double time;

    private List<Double> splits;
}
