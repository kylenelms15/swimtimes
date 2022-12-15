package com.apex.swimtime.constants;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class RelationshipID implements Serializable {
    private Integer swimmerID;

    private Integer timeID;
}
