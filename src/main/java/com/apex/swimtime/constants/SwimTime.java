package com.apex.swimtime.constants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="swimtime")
public class SwimTime {
    private Integer swimmerID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeID;

    private LocalDate date;

    private int distance;

    private StrokeEnum stroke;

    private String time;
}
