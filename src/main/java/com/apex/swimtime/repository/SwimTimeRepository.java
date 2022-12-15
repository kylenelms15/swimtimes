package com.apex.swimtime.repository;

import com.apex.swimtime.constants.RelationshipID;
import com.apex.swimtime.constants.SwimTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SwimTimeRepository extends JpaRepository<SwimTime, Integer> {

    @Query(value = "SELECT timeID FROM SwimTime WHERE swimmerID = :swimmerID")
    List<Integer> findBySwimmerID(Integer swimmerID);
}
