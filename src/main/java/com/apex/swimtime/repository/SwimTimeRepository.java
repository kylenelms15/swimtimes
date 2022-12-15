package com.apex.swimtime.repository;

import com.apex.swimtime.constants.RelationshipID;
import com.apex.swimtime.constants.SwimTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwimTimeRepository extends JpaRepository<SwimTime, RelationshipID> {

}
