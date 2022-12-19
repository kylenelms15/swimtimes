package com.apex.swimtime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="split")
public class Split {
    @Id
    private Integer timeID;

    @Column(name = "splits")
    private Double[] splits;

}
