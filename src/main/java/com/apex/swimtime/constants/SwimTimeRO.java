package com.apex.swimtime.constants;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SwimTimeRO {
    private Integer swimmerID;

    private Integer timeID;

    private LocalDate date;

    private Integer distance;

    private StrokeEnum stroke;

    private Double time;

    private Split splits;
}
