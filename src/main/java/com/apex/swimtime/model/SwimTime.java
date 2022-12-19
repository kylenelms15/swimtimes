package com.apex.swimtime.model;

import com.apex.swimtime.constants.StrokeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    private Integer distance;

    private StrokeEnum stroke;

    private Double time;

    //TODO: private CourseEnum course
}
